package Dataretrieval.Submission;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
public class PartialsCompare
{
	static String ORACLE_DB = "oracle";
	static String database = ORACLE_DB; 
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	//final static String ATTEMPTDATA_QUERY_FIRST = "SELECT * FROM attemptdata WHERE TESTID = ? AND userid = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? ORDER BY createdtime";
	//final static String ATTEMPTDATA_QUERY_SECOND = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? AND updatedtime IS NOT NULL ORDER BY updatedtime DESC";
	//final static String ATTEMPTDATA_QUERY_SECOND = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and questionid = ? ORDER BY createdtime DESC";
	final static String ATTEMPTDATA_QUERY_FIRST = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and ATTEMPTDATA_PK = ? ";
	final static String ATTEMPTDATA_QUERY_SECOND = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? and ATTEMPTDATA_PK = ?";
	
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
		pst2 = con.prepareStatement(ATTEMPTDATA_QUERY_SECOND);
		
		String testID = "13252698274113334";
		String userID = "9632320";
		String activityID = "309717477";
		String attemptNO = "1";
		String sectionID = "6322539";
		//String questionID = "13252698187938536";
		
		/*String testID = "13252698280481078";
		String userID = "7394045";
		String activityID = "316781181";
		String attemptNO = "1";
		String sectionID = "6500805";*/
		//String questionID = "13252698229805343";
		String attemptdata_pk1 = "13252698274303465";
		String attemptdata_pk2 = "13252698275380973";
		
		pst.setString(1, testID);//TESTID
		pst.setString(2, userID);//USERID
		pst.setString(3, activityID);//activityid
		pst.setString(4, attemptNO);//attemptno
		pst.setString(5, sectionID);//sectionid
		pst.setString(6, attemptdata_pk1);//questionid
		
		pst2.setString(1, testID);
		pst2.setString(2, userID);
		pst2.setString(3, activityID);
		pst2.setString(4, attemptNO);
		pst2.setString(5, sectionID);
		pst2.setString(6, attemptdata_pk2);

		rs = pst.executeQuery();
		rs2 = pst2.executeQuery();
		
		Map<String, String> nameValueMapFirst = null;
		Map<String, String> nameValueMapSecond = null;
		
		Map<String, String> nameValueMapFirstFinal = null;
		Map<String, String> nameValueMapSecondFinal = null;
		
		if (rs.next()) {
			System.out.println("inside if");
			byte[] barray = rs.getBytes("params");
			ByteArrayInputStream bos = new ByteArrayInputStream(barray);
			nameValueMapFirst = SubmissionUtility.hashFromStream(bos);
			bos = new ByteArrayInputStream(barray);
			nameValueMapFirstFinal = SubmissionUtility.hashFromStream(bos);
		}
		if (rs2.next()) {
			byte[] barray = rs2.getBytes("params");
			ByteArrayInputStream bos = new ByteArrayInputStream(barray);
			nameValueMapSecond = SubmissionUtility.hashFromStream(bos);
			bos = new ByteArrayInputStream(barray);
			nameValueMapSecondFinal = SubmissionUtility.hashFromStream(bos);
		}
		for(Iterator<String> i = nameValueMapFirst.keySet().iterator(); i.hasNext();){
			boolean flag = false;
			String keyFirst = i.next();
			String valueFirst = nameValueMapFirst.get(keyFirst);
			for(Iterator<String> j = nameValueMapSecond.keySet().iterator(); j.hasNext();){
				String keySecond = j.next();
				String valueSecond = nameValueMapSecond.get(keySecond);
				if(keyFirst.equals(keySecond)){
					if(!valueFirst.equals(valueSecond)){
						System.out.println(" Data Mismatch for key : " + keyFirst);
						System.out.println(" First Record Value : " + valueFirst + ", Second Record Value : " + valueSecond);
					}/*else{
						System.out.println(" Data Match for key : " + keyFirst);
						System.out.println(" First Record Value : " + valueFirst + ", Second Record Value : " + valueSecond);
					}*/
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
		for(Iterator<String> i = nameValueMapFirst.keySet().iterator(); i.hasNext();){
			String keyFirst = i.next();
			String valueFirst = nameValueMapFirst.get(keyFirst);
			System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
		}
		
		System.out.println(" ########## Second Record ############");
		for(Iterator<String> i = nameValueMapSecond.keySet().iterator(); i.hasNext();){
			String keySecond = i.next();
			String valueSecond = nameValueMapSecond.get(keySecond);
			System.out.println(" Key : " + keySecond + " Value : " + valueSecond);
		}
		
		System.out.println("###### PRINTING FINAL MAP ############");
		ConcurrentHashMap finalMap = (ConcurrentHashMap)nameValueMapFirstFinal;
		finalMap.putAll(nameValueMapSecondFinal);
		for(Iterator<String> i = finalMap.keySet().iterator(); i.hasNext();){
			String keyFirst = i.next();
			String valueFirst = (String)finalMap.get(keyFirst);
			System.out.println(" Key : " + keyFirst + " Value : " + valueFirst);
		}
	}
			
	/*public static String readString( BufferedReader input )
	throws IOException{
		String result= input.readLine();
		return(result);
	}*/
}

