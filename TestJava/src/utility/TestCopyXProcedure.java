package utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import Dataretrieval.util.OracleConnection;

public class TestCopyXProcedure {

	public static void main(String[] args) 
	{
		Connection con = null;
		try{
			con = OracleConnection.getConnection();
			con.setAutoCommit(false);
			TestCopyXProcedure testCopyXProcedure = new TestCopyXProcedure();
			
			List<Map<String,String>> questionDtlsList 
				= testCopyXProcedure.getXrefMap(con,testCopyXProcedure.getTestId());
			
			testCopyXProcedure.createSQL(con, questionDtlsList);
			con.rollback();
		}catch(Exception e){
			e.printStackTrace();
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OracleConnection.releaseResources(con);
		}
	}
    
	
	public List<String> getTestId() throws Exception
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int sectionId = 32333665;
		List<String> testList = new ArrayList<String>();
		try{
			con = OracleConnection.getSparkConnection();
			pstmt = con.prepareStatement("SELECT   act.native_ala_id " +
									"FROM Section_Assignment_Xref sax" +
									", assignment asg" +
									", activity act  " +
									"WHERE sax.section_id = ? " +
									"AND sax.assignment_id = asg.assignment_id " +
									"AND asg.assignment_id = act.assignment_id " +
									"AND asg.Is_Deleted = 'false'");
		
			pstmt.setInt(1, sectionId);
			rst = pstmt.executeQuery();
			while(rst.next()){
				testList.add(rst.getString("native_ala_id"));
			}
			/*List<String> testList = new ArrayList<String>();
			testList.add("13252698007915108");
			testList.add("13252698007915033");*/
			return testList;
		
		}finally{
			OracleConnection.releaseResources(rst);
			OracleConnection.releaseResources(pstmt);
			OracleConnection.releaseResources(con);
		}
	}
	
	public List<Map<String,String>> getXrefMap(Connection con,List<String> testList)
	{
		List<Map<String,String>> questionDtlsList = new ArrayList<Map<String,String>>();
	    try{
	    	if(testList != null && !testList.isEmpty()){
		    	Iterator<String> testItr = testList.iterator();	
		    	while(testItr.hasNext())
		    	{
		    	     String testId = testItr.next();
		    	     long newSequenceId = getEztoSequence(con); 
		    		 //Map<String,QuestionXrefTO> qMap = QuestionXrefDAO.getXrefMapForTestID(con,testId);
		    	     Map<String,QuestionXrefTO> qMap = getXrefMapForTestID(con,testId);
		    	     
		    	     
		    	     Iterator<String> keyQidItr = qMap.keySet().iterator();
		    	     while(keyQidItr.hasNext())
		    	     {
		    	    	String questionId = keyQidItr.next();
		    	    	QuestionXrefTO questionXrefTO =  qMap.get(questionId);
		    	    	Map<String, String> questionDtlsMap = new HashMap<String,String>();
		    	    	questionDtlsMap.put("sourceXrefTID", questionXrefTO.getTestID());
		    	    	questionDtlsMap.put("sourceXrefQuestionID", questionXrefTO.getQuestionID());
		    	    	questionDtlsMap.put("newQuestionID", questionXrefTO.getQuestionID());
		    	    	//Map<String, String> libMap = getLibTestQuestions(con,questionXrefTO.getTestID(), questionXrefTO.getQuestionID());
		    	    	//String libTestId =libMap.get("libTestId");
		    	    	//String libQuestionId = libMap.get("libQuestionId");       
		    	    	questionDtlsMap.put("sourceTestID", questionXrefTO.getQuestionID());
		    	    	questionDtlsMap.put("sourceQuestionID", questionXrefTO.getQuestionID());
		    	    	questionDtlsMap.put("originalQuestionID", questionXrefTO.getQuestionID());
		    	    	questionDtlsMap.put("points", null);
		    	    	questionDtlsMap.put("trackbackReqd", "Y");
		    	    	questionDtlsMap.put("sourceTid", testId );
		    	    	questionDtlsMap.put("destinationTid", String.valueOf(newSequenceId) );
		    	    	questionDtlsMap.put("newTestBankId", String.valueOf(newSequenceId) );
		    	    	questionDtlsList.add(questionDtlsMap);
		    	     }
		    	}
		    	
		    }
	    }catch(Exception sql){
	    	sql.printStackTrace();
	    }
	    return questionDtlsList;
	}
	
	
	
	public void createSQL(Connection con, List<Map<String,String>> questionDtlsList) throws Exception
	{
		StringBuilder insertGlobaTable = new StringBuilder();
		PreparedStatement pstmt = null;
		if(questionDtlsList != null && !questionDtlsList.isEmpty())
		{
			insertGlobaTable.append("INSERT INTO copy_course_temp (sourceXrefTID,sourceXrefQID, targetqid, libraryTID, libraryQID , originalQID, points, trackbackReqd, sourceTestID, targetTestID,testBankId)");
			Iterator<Map<String,String>> questionDtlMapItr =  questionDtlsList.iterator();	
			int loopCounter = 0 ;
			int length = questionDtlsList.size();
			while(questionDtlMapItr.hasNext())
			{
				Map<String,String> questionDtlsMap = questionDtlMapItr.next();
				String sourceXrefTestID = questionDtlsMap.get("sourceXrefTID");
				String sourceXrefQuestionID = questionDtlsMap.get("sourceXrefQuestionID");
				String newQuestionID = questionDtlsMap.get("newQuestionID");
				String sourceTestID = questionDtlsMap.get("sourceTestID");
				String sourceQuestionID = questionDtlsMap.get("sourceQuestionID");
				String originalQuestionID = questionDtlsMap.get("originalQuestionID");
				String points = null;
				String trackbackReqd = questionDtlsMap.get("trackbackReqd");
				String sourceTid = questionDtlsMap.get("sourceTid");
				String destinationTid = questionDtlsMap.get("destinationTid");
				String newTestBankId = questionDtlsMap.get("newTestBankId");
				
				insertGlobaTable.append("\n");
				insertGlobaTable.append("SELECT '").append(sourceXrefTestID).append("' sourceXrefTID, '")
								.append( sourceXrefQuestionID ).append("' sourceXrefQID , '")
								.append(newQuestionID).append("' targetqid , '")									
								.append(sourceTestID).append("' libraryTID , '")
								.append(sourceQuestionID).append("' libraryQID , '")									
								.append(originalQuestionID).append("' originalQID , ")
								.append(points ).append(" points , '")
								.append(trackbackReqd ).append("' trackbackReqd , '")
								.append(sourceTid ).append("' sourceTestID , '")
								.append(destinationTid ).append("' targetTestID , '")
								.append(newTestBankId ).append("' testBankId ");
				insertGlobaTable.append(" FROM dual ");
				if(loopCounter != (length - 1)){
					insertGlobaTable.append(" \n");
					insertGlobaTable.append("UNION ALL");
				}
				loopCounter++;
			}
			
			//System.out.println(" insertGlobaTable : "+insertGlobaTable);
		    
			/*FileWriter fw = new FileWriter(new File("C:\\PrasenjitWorkSpace\\TestJava\\src\\utility\\temp.txt"));
			fw.write(insertGlobaTable.toString());
			fw.close();*/
			
			long startGlobalDB = (new java.util.Date()).getTime(); 
 			pstmt = con.prepareStatement(insertGlobaTable.toString());
 		    pstmt.executeUpdate();
 		    long endGlobalDB = (new java.util.Date()).getTime();
			
 		   System.out.println("###### time taken for Global Table Insert : "+(endGlobalDB - startGlobalDB)+" ms.");
 		    
			long procedureStartTime = (new java.util.Date()).getTime();
			CallableStatement clStmt = con.prepareCall("{CALL EZT_DUP_TEST.duplicateTest1(?,?,?)}");            
			clStmt.setLong(1, System.currentTimeMillis());	
			clStmt.registerOutParameter(2, OracleTypes.NUMBER);
			clStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			clStmt.execute();
			String retCode = String.valueOf(clStmt.getLong(2));
			String retErr = clStmt.getString(3);
			if(!retCode.equals("0")){
				//_logger.error("########### Error while executing classware_course_sql.adjustAssignmentandCourseDates : " + retErr + " ##########");
				System.out.println("########### Error while executing EZT_DUP_TEST.duplicateTest : " + retErr + " ##########");
				throw new SQLException(retErr);
			}
			long procedureEndTime = (new java.util.Date()).getTime();
			System.out.println("###### time taken for EZT_DUP_TEST.duplicateTest1 : "+(procedureEndTime - procedureStartTime)+" ms.");
			
		}
		
	}
	
	public Map<String,QuestionXrefTO> getXrefMapForTestID(Connection con ,String testId) throws Exception
	{
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QuestionXrefTO questionXrefTO = null;
		 Map<String,QuestionXrefTO> questionXrefMap = new HashMap<String,QuestionXrefTO>();
		 try{
			 pstmt = con.prepareStatement("SELECT questionid FROM test_question_xref WHERE testid = ?");
			 pstmt.setString(1, testId);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				 String questionId = rs.getString("questionid");
				 questionXrefTO = new QuestionXrefTO();
				 questionXrefTO.setTestID(testId);
				 questionXrefTO.setQuestionID(questionId);
				 
				 questionXrefMap.put(questionId, questionXrefTO);
			 }
		 }finally{
			 OracleConnection.releaseResources(rs);
			 OracleConnection.releaseResources(pstmt);
		 }
		return questionXrefMap; 
	}
	
	
	
	public Map<String, String> getLibTestQuestions(Connection con ,String testId, String questionId)
	{
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 Map<String, String> libMap = new HashMap<String, String>();
		 try{
			 pstmt = con.prepareStatement("SELECT sourcetid, sourceqid  FROM trackback WHERE destinationtid = ? AND destinationqid = ?");
			 pstmt.setString(1, testId);
			 pstmt.setString(1, questionId);
			 rs = pstmt.executeQuery();
			 if(rs.next())
			 {
				String libTestId = rs.getString("sourcetid");
				String libQuestionId = rs.getString("sourceqid");
				libMap.put("libTestId", libTestId);
				libMap.put("libQuestionId", libQuestionId);
			 }
		 }catch(SQLException sql){
			 sql.printStackTrace();
		 }
		return libMap; 
	}
	
	private long getEztoSequence(Connection con) {
		long eztoSequence = 0l;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		try {
			stmt= con.prepareStatement("SELECT EZTO_SEQUENCE.nextval FROM dual");
			rs= stmt.executeQuery();
			if (rs.next()) {
				eztoSequence = rs.getLong(1);				
			}
			
		} catch (SQLException s) {
			System.out.println("unable to get EZTO_SEQUENCE.nextval FROM dual");
			s.printStackTrace();
		}finally{
			OracleConnection.releaseResources(stmt, rs);
		}
		return eztoSequence;
	}
	
}
