package Dataretrieval.Submission;

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

import Dataretrieval.util.OracleConnection;










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
public class PartialsCompareMultiple
{
	static String ORACLE_DB = "oracle";
	static String database = ORACLE_DB; 
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	final static String ATTEMPTDATA_QUERY_FIRST = "SELECT rowid, ad.* FROM attemptdata ad WHERE TESTID = ? AND userid = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? order by createdtime";

	public static void main(String[] args) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// get DB connection
			
			con = getConnection();
			getPartials(con);
			
		} catch (SQLException exSQLException) {
			System.out.print(exSQLException);
			exSQLException.printStackTrace();
			if (con != null) {
				// con.rollback();
			}
		} catch (Exception exException) {
			System.out.print(exException);
			exException.printStackTrace();
			if (con != null) {
				// con.rollback();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
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
		
		String testID = "13252698276563811";
		String userID = "8130202";
		String activityID = "311819559";
		String attemptNO = "1";
		String sectionID = "6505113";
		String questionID = "13252698187938538";
		
		pst.setString(1, testID);//TESTID
		pst.setString(2, userID);//USERID
		pst.setString(3, activityID);//activityid
		pst.setString(4, attemptNO);//attemptno
		pst.setString(5, sectionID);//sectionid
		pst.setString(6, questionID);//questionid
		

		rs = pst.executeQuery();
		
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
			nameValueMapFirst = SubmissionUtility.hashFromStream(bos);
			attemptDataTO.setParams(nameValueMapFirst);
			bos = new ByteArrayInputStream(barray);
			nameValueMapFirstFinal = SubmissionUtility.hashFromStream(bos);
			attemptDataTO.setOriginalParams(nameValueMapFirstFinal);
		}
		File file = new File("/compare_logs/"+testID);
		file.mkdirs();
		for(int count = 1; count < attemptDataTOList.size(); count++){
			
			attemptDataTOList.get(0).setParams(attemptDataTOList.get(0).getOriginalParams());
			nameValueMapSecond = attemptDataTOList.get(count).getParams();
			PrintWriter pw = new PrintWriter(new File("/compare_logs/"+testID + "/" + questionID + "_" + attemptDataTOList.get(0).getAttemptDataPK() + "_" + attemptDataTOList.get(count).getAttemptDataPK() + ".txt"));
			for(Iterator<String> i = attemptDataTOList.get(0).getParams().keySet().iterator(); i.hasNext();){
				boolean flag = false;
				String keyFirst = i.next();
				String valueFirst = (String)attemptDataTOList.get(0).getParams().get(keyFirst);
					//nameValueMapSecond = attemptDataTOList.get(count).getParams();
					for(Iterator<String> j = nameValueMapSecond.keySet().iterator(); j.hasNext();){
						String keySecond = j.next();
						String valueSecond = (String)nameValueMapSecond.get(keySecond);
						if(keyFirst.equals(keySecond)){
							if(!valueFirst.equals(valueSecond)){
								//System.out.println(" Data Mismatch for key : " + keyFirst);
								pw.println(" Data Mismatch for key : " + keyFirst);
								//System.out.println(" First Record Value : " + valueFirst + ", Second Record Value : " + valueSecond);
								pw.println(" First Record Value : " + valueFirst + ", Second Record Value : " + valueSecond);
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
			
			//System.out.println(" Printing mutually exclusive data");
			pw.println(" Printing mutually exclusive data");
			
			//System.out.println(" ########## First Record ############");
			pw.println(" ########## First Record ############");
			for(Iterator<String> i = attemptDataTOList.get(0).getParams().keySet().iterator(); i.hasNext();){
				String keyFirst = i.next();
				String valueFirst = (String)attemptDataTOList.get(0).getParams().get(keyFirst);
				//System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
				pw.println(" Key : " + keyFirst + " Value : " + valueFirst);
			}
			
			//System.out.println(" ########## Second Record ############");
			pw.println(" ########## Second Record ############");
			for(Iterator<String> i = nameValueMapSecond.keySet().iterator(); i.hasNext();){
				String keySecond = i.next();
				String valueSecond = (String)nameValueMapSecond.get(keySecond);
				//System.out.println(" Key : " + keySecond + " Value : " + valueSecond);
				pw.println(" Key : " + keySecond + " Value : " + valueSecond);
			}
			
			//System.out.println("###### PRINTING FINAL MAP ############");
			pw.println("###### PRINTING FINAL MAP ############");
			ConcurrentHashMap finalMap = (ConcurrentHashMap)attemptDataTOList.get(0).getOriginalParams();
			finalMap.putAll(attemptDataTOList.get(count).getOriginalParams());
			attemptDataTOList.get(0).setOriginalParams(finalMap);
			for(Iterator<String> i = finalMap.keySet().iterator(); i.hasNext();){
				String keyFirst = i.next();
				String valueFirst = (String)finalMap.get(keyFirst);
				//System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
				pw.println(" Key : " + keyFirst + " Value : " + valueFirst);
			}
			pw.flush();
			pw.close();
		}
	}
}

