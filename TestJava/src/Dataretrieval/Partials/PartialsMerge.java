package Dataretrieval.Partials;


import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


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
public class PartialsMerge
{
	static String ORACLE_DB = "oracle";
	static String database = ORACLE_DB; 
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	final static String ATTEMPTDATA_QUERY_FIRST = "SELECT rowid,ad.* FROM attemptdata ad WHERE TESTID = ? AND userid = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? and ATTEMPTDATA_PK > 0 ORDER BY createdtime";
	final static String ATTEMPTDATA_QUERY_SECOND = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? and ATTEMPTDATA_PK = ? and ATTEMPTDATA_PK > 0";
	//final static String ATTEMPTDATA_QUERY_SECOND = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? AND updatedtime IS NOT NULL ORDER BY updatedtime DESC";
	final static String ATTEMPTDATA_QUERY_UPDATE = "UPDATE attemptdata SET params = ? WHERE rowid = ? and attemptdata_pk = ?";
	final static String ATTEMPTDATA_QUERY_DELETE = "DELETE FROM attemptdata WHERE rowid = ? and attemptdata_pk = ?";
	final static String ATTEMPTDATA_QUERY_INSERT = "INSERT INTO attemptdata" +
														"(attemptdata_pk, attempt_pk, testid, activityid, attemptno, userid, sectionid, questionid, itemscore, params, maxpoints, formulaanswer, recordedvalue, followup, feedback, html, instructorcomment, createdtime, updatedtime, userresponse)  " +
														" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

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
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		pst = con.prepareStatement(ATTEMPTDATA_QUERY_FIRST);
		pst2 = con.prepareStatement(ATTEMPTDATA_QUERY_SECOND);
		
		String testID = "13252698274667470";
		String userID = "9611544";
		String activityID = "310157369";
		String attemptNO = "1";
		String sectionID = "6496007";
		
		String questionID = "13252698273299536";
		
		/* 
		 * This is any subsequent attempt pk of any attempt 
		 * which needs to update the 1st record of that 
		 * same attempt & needs to be removed
		 * */
		long attemptDataPK = 13252698300785299l;
		
		pst.setString(1, testID);//TESTID
		pst.setString(2, userID);//USERID
		pst.setString(3, activityID);//activityid
		pst.setString(4, attemptNO);//attemptno
		pst.setString(5, sectionID);//sectionid
		pst.setString(6, questionID);//questionid
		
		pst2.setString(1, testID);
		pst2.setString(2, userID);
		pst2.setString(3, activityID);
		pst2.setString(4, attemptNO);
		pst2.setString(5, sectionID);
		pst2.setString(6, questionID);
		pst2.setLong(7, attemptDataPK);

		rs = pst.executeQuery();
		rs2 = pst2.executeQuery();
		
		ConcurrentHashMap nameValueMapFirst = null;
		ConcurrentHashMap nameValueMapSecond = null;
		
		ConcurrentHashMap nameValueMapFirstFinal = null;
		ConcurrentHashMap nameValueMapSecondFinal = null;
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
			nameValueMapFirst = tp_utility.hashFromStream(bos);
			attemptDataTO.setParams(nameValueMapFirst);
			bos = new ByteArrayInputStream(barray);
			nameValueMapFirstFinal = tp_utility.hashFromStream(bos);
			attemptDataTO.setOriginalParams(nameValueMapFirstFinal);
		}
		if (rs2.next()) {
			byte[] barray = rs2.getBytes("params");
			ByteArrayInputStream bos = new ByteArrayInputStream(barray);
			nameValueMapSecond = tp_utility.hashFromStream(bos);
			bos = new ByteArrayInputStream(barray);
			nameValueMapSecondFinal = tp_utility.hashFromStream(bos);
		}
		for(Iterator<String> i = attemptDataTOList.get(0).getParams().keySet().iterator(); i.hasNext();){
			boolean flag = false;
			String keyFirst = i.next();
			String valueFirst = (String)attemptDataTOList.get(0).getParams().get(keyFirst);
			for(Iterator<String> j = nameValueMapSecond.keySet().iterator(); j.hasNext();){
				String keySecond = j.next();
				String valueSecond = (String)nameValueMapSecond.get(keySecond);
				if(keyFirst.equals(keySecond)){
					if(!valueFirst.equals(valueSecond)){
						System.out.println(" Data Mismatch for key : " + keyFirst);
						System.out.println(" First Record Value : " + valueFirst + ", Second Record Value : " + valueSecond);
					}
					j.remove();
					flag = true;
					break;
				}
			}
			if(flag){
				i.remove();
			}
		}
		
		System.out.println(" Printing mutually exclusive data");
		
		System.out.println(" ########## First Record ############");
		for(Iterator<String> i = attemptDataTOList.get(0).getParams().keySet().iterator(); i.hasNext();){
			String keyFirst = i.next();
			String valueFirst = (String)nameValueMapFirst.get(keyFirst);
			System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
		}
		
		System.out.println(" ########## Second Record ############");
		for(Iterator<String> i = nameValueMapSecond.keySet().iterator(); i.hasNext();){
			String keySecond = i.next();
			String valueSecond = (String)nameValueMapSecond.get(keySecond);
			System.out.println(" Key : " + keySecond + " Value : " + valueSecond);
		}
		
		System.out.println("###### PRINTING FINAL MAP ############");
		ConcurrentHashMap finalMap = (ConcurrentHashMap)attemptDataTOList.get(0).getOriginalParams();
		finalMap.putAll(nameValueMapSecondFinal);
		
		
		
		attemptDataTOList.get(0).setOriginalParams(finalMap);
		for(Iterator<String> i = finalMap.keySet().iterator(); i.hasNext();){
			String keyFirst = i.next();
			String valueFirst = (String)finalMap.get(keyFirst);
			System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
		}
		PreparedStatement ps = con.prepareStatement(ATTEMPTDATA_QUERY_UPDATE);
		ps.setBytes(1, tp_utility.hashToArray(attemptDataTOList.get(0).getOriginalParams()));
		ps.setRowId(2, attemptDataTOList.get(0).getRowID());
		ps.setLong(3, attemptDataTOList.get(0).getAttemptDataPK());
		ps.executeUpdate();
		
		deleteInsertAttemptData(con, attemptDataTOList);
		// delete-insert starts
	}

	private static void deleteInsertAttemptData(Connection connection, List<AttemptDataTO> attemptDataTOList) 
	throws Exception{
		//Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psDelete = null;
		
			//connection = OracleConnection.getConnection();
			psInsert = connection.prepareStatement(ATTEMPTDATA_QUERY_INSERT);
			psDelete = connection.prepareStatement(ATTEMPTDATA_QUERY_DELETE);
			//connection.setAutoCommit(false);
			
			
			
			for(int i = 1; i < attemptDataTOList.size(); i++){
				psInsert.setLong(1, (0 - attemptDataTOList.get(i).getAttemptDataPK()));
				psInsert.setLong(2, (0 - attemptDataTOList.get(i).getAttemptPK()));
				psInsert.setString(3, attemptDataTOList.get(i).getTestID());
				psInsert.setString(4, attemptDataTOList.get(i).getActivityID());
				psInsert.setString(5, attemptDataTOList.get(i).getAttemptNo());
				psInsert.setString(6, attemptDataTOList.get(i).getUserID());
				psInsert.setString(7, attemptDataTOList.get(i).getSectionID());
				psInsert.setString(8, attemptDataTOList.get(i).getQuestionID());
				psInsert.setLong(9, attemptDataTOList.get(i).getItemScore());
				psInsert.setBytes(10, tp_utility.hashToArray(attemptDataTOList.get(i).getParams()));
				psInsert.setLong(11, attemptDataTOList.get(i).getMaxPoints());
				psInsert.setString(12, attemptDataTOList.get(i).getFormulaAnswer());
				psInsert.setBytes(13, attemptDataTOList.get(i).getRecordedValue());
				psInsert.setBytes(14, attemptDataTOList.get(i).getFollowup());
				psInsert.setBytes(15, attemptDataTOList.get(i).getFeedback());
				psInsert.setBytes(16, attemptDataTOList.get(i).getHtml());
				psInsert.setBytes(17, attemptDataTOList.get(i).getInstructorComment());
				psInsert.setLong(18, attemptDataTOList.get(i).getCreatedTime());
				psInsert.setLong(19, attemptDataTOList.get(i).getUpdatedTime());
				psInsert.setBytes(20, attemptDataTOList.get(i).getUserResponse());
				psInsert.executeUpdate();
			}
			
			for(int i = 1; i < attemptDataTOList.size(); i++){
				psDelete.setRowId(1, attemptDataTOList.get(i).getRowID());
				psDelete.setLong(2, attemptDataTOList.get(i).getAttemptDataPK());
				psDelete.executeUpdate();
			}
			
		//connection.commit();
		/*catch(SQLException sqlex){
			sqlex.printStackTrace();
			System.out.println("Data could not be processed");
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("Data could not be processed");
			ex.printStackTrace();
			throws ex;
		}*/
	}
	
}

