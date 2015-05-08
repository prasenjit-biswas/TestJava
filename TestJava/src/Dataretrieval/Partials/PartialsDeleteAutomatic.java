package Dataretrieval.Partials;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import Dataretrieval.Submission.SubmissionUtility;
import Dataretrieval.Test.OracleConnection;



/**
 * <h3>This class gets the BLOB records from Partials table, parses them and
 * prints/displays them</h3>
 * <ul>
 * <li>get DB connection
 * <li>execute Partials SELECT query
 * <li>iterate result-set and fetch BLOB data
 * <li>read the values and put them in HashMap as key-value pairs
 * <li>prints the name-value(s) from HashMap
 * </ul>
 * 
 * @author 497647
 * 
 */
public class PartialsDeleteAutomatic
{
	static String ORACLE_DB = "oracle";
	static String database = ORACLE_DB; 
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	final static String ATTEMPTDATA_QUERY_DUPLICATE = 
			"SELECT AD.TESTID," +
			"       AD.ACTIVITYID," +
			"       AD.ATTEMPTNO," +
			"       AD.QUESTIONID," +
			"       AD.USERID," +
			"       AD.SECTIONID," +
			"       COUNT(*)" +
			"  FROM ATTEMPT A, ATTEMPTDATA AD" +
			" WHERE A.TESTID = ? " +
			" AND a.attemptno = ?" +
			" AND ad.userid = ? " +
			//" AND ad.questionid = ? " +
			//" AND a.submissionid IS NOT NULL " +
			"   AND A.ATTEMPT_PK = AD.ATTEMPT_PK" +
			"  GROUP BY AD.TESTID," +
			"          AD.ACTIVITYID," +
			"          AD.ATTEMPTNO," +
			"          AD.QUESTIONID," +
			"          AD.USERID," +
			"          AD.SECTIONID" +
			" HAVING COUNT(*) > 2";
	
	final static String ATTEMPTDATA_QUERY_FIRST = "SELECT rowid,ad.* FROM attemptdata ad WHERE TESTID = ? AND userid = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? and ATTEMPTDATA_PK > 0 ORDER BY createdtime";
	final static String ATTEMPTDATA_QUERY_DELETE = "DELETE FROM attemptdata WHERE rowid = ? and attemptdata_pk = ?";

	public static void main(String[] args) throws Exception {

		Connection con = null;
		try {
			// get DB connection
			con = getConnection();
			con.setAutoCommit(false);
			getPartials(con);
			con.commit();
			
		} catch (Exception exException) {
			System.out.print(exException);
			exException.printStackTrace();
			if (con != null) {
				con.rollback();
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	private static Connection getConnection() throws SQLException, Exception {
		Connection con = null;
		System.out.println("database : " + database);
		con = OracleConnection.getConnection();
		return con;
	}

	private static void getPartials(Connection con) throws Exception {
		PreparedStatement pst = null;
		PreparedStatement pstSelectDuplicate = null;
		ResultSet rs = null;
		ResultSet rsSelectDuplicate = null;
		
		String testID = "13252698274667470";
		String userID = "";
		String activityID = "";
		String attemptNO = "2";
		String sectionID = "";
		String questionID = "";
		
		File file = new File("/compare_logs/delete_mismatch/"+testID);
		file.mkdirs();
		
		pstSelectDuplicate = con.prepareStatement(ATTEMPTDATA_QUERY_DUPLICATE);
		pstSelectDuplicate.setString(1, testID);
		pstSelectDuplicate.setString(2, attemptNO);
		pstSelectDuplicate.setString(3, userID);
		//pstSelectDuplicate.setString(4, questionID);
		
		rsSelectDuplicate = pstSelectDuplicate.executeQuery();
		
		List<AttemptDataTO> listAttemptDataTO = new ArrayList<AttemptDataTO>();
		while(rsSelectDuplicate.next()){
			AttemptDataTO attemptDataTO = new AttemptDataTO();
			attemptDataTO.setTestID(rsSelectDuplicate.getString("testid"));
			attemptDataTO.setUserID(rsSelectDuplicate.getString("userid"));
			attemptDataTO.setActivityID(rsSelectDuplicate.getString("activityid"));
			attemptDataTO.setSectionID(rsSelectDuplicate.getString("sectionid"));
			attemptDataTO.setAttemptNo(rsSelectDuplicate.getString("attemptno"));
			attemptDataTO.setQuestionID(rsSelectDuplicate.getString("questionid"));
			listAttemptDataTO.add(attemptDataTO);
		}
		
		if(rsSelectDuplicate != null){
			rsSelectDuplicate.close();
		}
		
		if(pstSelectDuplicate != null){
			pstSelectDuplicate.close();
		}
		
		for(AttemptDataTO attemptDataTO1 : listAttemptDataTO){
			boolean breakFor = false;
			userID = attemptDataTO1.getUserID();
			activityID = attemptDataTO1.getActivityID();
			attemptNO = attemptDataTO1.getAttemptNo();
			sectionID = attemptDataTO1.getSectionID();
			questionID = attemptDataTO1.getQuestionID();
			
			pst = con.prepareStatement(ATTEMPTDATA_QUERY_FIRST);
			
			pst.setString(1, testID);//TESTID
			pst.setString(2, userID);//USERID
			pst.setString(3, activityID);//activityid
			pst.setString(4, attemptNO);//attemptno
			pst.setString(5, sectionID);//sectionid
			pst.setString(6, questionID);//questionid
			
			rs = pst.executeQuery();
			
			ConcurrentHashMap nameValueMapFirst = null;
			ConcurrentHashMap nameValueMapFirstFinal = null;
			List<AttemptDataTO> attemptDataTOList = new ArrayList<AttemptDataTO>();
			
			while (rs.next()) {
				AttemptDataTO attemptDataTO = new AttemptDataTO();
				attemptDataTOList.add(attemptDataTO);
				attemptDataTO.setRowID(rs.getRowId("rowid"));
				attemptDataTO.setAttemptDataPK(rs.getLong("attemptdata_pk"));
				attemptDataTO.setAttemptPK(rs.getLong("attempt_pk"));
				attemptDataTO.setTestID(rs.getString("testid"));
				attemptDataTO.setActivityID(rs.getString("activityid"));
				attemptDataTO.setAttemptNo(rs.getString("attemptno"));
				attemptDataTO.setUserID(rs.getString("userid"));
				attemptDataTO.setSectionID(rs.getString("sectionid"));
				attemptDataTO.setQuestionID(rs.getString("questionid"));
				attemptDataTO.setItemScore(rs.getLong("itemscore"));
				attemptDataTO.setMaxPoints(rs.getLong("maxpoints"));
				attemptDataTO.setFormulaAnswer(rs.getString("formulaanswer"));
				attemptDataTO.setRecordedValue(rs.getBytes("recordedvalue"));
				attemptDataTO.setFollowup(rs.getBytes("followup"));
				attemptDataTO.setFeedback(rs.getBytes("feedback"));
				attemptDataTO.setHtml(rs.getBytes("html"));
				attemptDataTO.setInstructorComment(rs.getBytes("instructorcomment"));
				attemptDataTO.setUserResponse(rs.getBytes("userresponse"));
				attemptDataTO.setCreatedTime(rs.getLong("createdtime"));
				attemptDataTO.setUpdatedTime(rs.getLong("updatedtime"));
				byte[] barray = rs.getBytes("params");
				ByteArrayInputStream bos = new ByteArrayInputStream(barray);
				nameValueMapFirst = SubmissionUtility.hashFromStream(bos);
				attemptDataTO.setParams(nameValueMapFirst);
				bos = new ByteArrayInputStream(barray);
				nameValueMapFirstFinal = SubmissionUtility.hashFromStream(bos);
				attemptDataTO.setOriginalParams(nameValueMapFirstFinal);
			}
			
			rs.close();
			pst.close();
			
			
			AttemptDataTO firstAttemptDataTO = attemptDataTOList.get(0);
			
			
			for(int j=1; j<attemptDataTOList.size(); j++){
				AttemptDataTO attemptDataTO2 = attemptDataTOList.get(j);
				
				for(Iterator<String> itr = attemptDataTO2.getParams().keySet().iterator(); itr.hasNext();){
					//boolean flag = false;
					String keyNext = itr.next();
					String valueNext = (String)attemptDataTO2.getParams().get(keyNext);
					
					String valueFirst = (String)firstAttemptDataTO.getParams().get(keyNext);
					if(!valueNext.equals(valueFirst)){
						breakFor = true;
						PrintWriter pw = new PrintWriter(new File("/compare_logs/delete_mismatch/"+testID + "/" + attemptDataTOList.get(0).getQuestionID() + "_" + attemptDataTOList.get(0).getUserID() + "_mismatch.txt"));
						pw.println("Data Mismatch for " + attemptDataTO2);
						pw.println(" Data Mismatch for key : " + keyNext);
						pw.println(" First Record Value : " + valueFirst + ", Next Record Value : " + valueNext);
						pw.flush();
						pw.close();
						break;
					}
				}	
				if(breakFor){
					break;
				}
			}
			
			if(!breakFor){
				deleteAttemptData(con, attemptDataTOList);
			}
		}
		// delete-insert starts
	}

	private static void deleteAttemptData(Connection connection, List<AttemptDataTO> attemptDataTOList) 
	throws Exception{
		PreparedStatement psDelete = null;
		
		psDelete = connection.prepareStatement(ATTEMPTDATA_QUERY_DELETE);
		for(int i = 1; i < attemptDataTOList.size(); i++){
			psDelete.setRowId(1, attemptDataTOList.get(i).getRowID());
			psDelete.setLong(2, attemptDataTOList.get(i).getAttemptDataPK());
			psDelete.addBatch();
		}
		
		psDelete.executeBatch();
	}
}

