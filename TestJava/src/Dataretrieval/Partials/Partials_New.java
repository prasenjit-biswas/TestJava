package Dataretrieval.Partials;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import utility.PartialTO;
import Dataretrieval.util.OracleConnection;

import com.mcgrawhill.ezto.utilities.CustomMap;
//import Dataretrieval.Test.OracleConnection;






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
public class Partials_New
{

	

	static String ORACLE_DB = "oracle";
	//static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB; 
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	final static String ATTEMPT_QUERY = "SELECT * FROM attempt WHERE TESTID = ? AND userid = ? AND activityid = ? AND attemptno=? and sectionid=? ";
	final static String ATTEMPTDATA_QUERY = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=? ";
	//final static String ATTEMPTDATA_QUERY = "SELECT * FROM attemptdata WHERE TESTID = ? AND USERID = ? AND activityid = ? AND attemptno=? and sectionid=?";
	

	public static void main(String[] args) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String,String> partial = null;
		Map<String,String> encryptedPartial = new HashMap<String,String>();
		Map<String,String> decryptedPartial = new HashMap<String,String>();
		PrintWriter pw = new PrintWriter(new File("C:/encryptedpartial.txt"));
		try {
			// get DB connection
			
			con = getConnection();
			getPartials(con);
			/*DESEncryption myEncryptor= new DESEncryption();
			
			Iterator<String> itr = partial.keySet().iterator();
			
			while(itr.hasNext()){
			  String key = itr.next();
			  String value = partial.get(key);
			  pw.println(" =========================");
			  pw.println(" key " + key);
			  pw.println(" value " + value);
			  String encryptedKey = myEncryptor.encrypt(key);
			  String encryptedValue = myEncryptor.encrypt(value);
			  pw.println(" encryptedKey " + encryptedKey);
			  pw.println(" encryptedValue " + encryptedValue);
			  encryptedPartial.put(encryptedKey,encryptedValue);
			  String decryptedkey = myEncryptor.decrypt(encryptedKey);
			  String decryptedvalue = myEncryptor.decrypt(encryptedValue);
			  pw.println(" decryptedkey " + decryptedkey);
			  pw.println(" decryptedvalue " + decryptedvalue);

			  decryptedPartial.put(decryptedkey, decryptedvalue);
			}
			
			pw.flush();
			pw.close();*/
			
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
		/*if (database.equals(ORACLE_DB)) {
			Class.forName(oradriver);
			con = DriverManager.getConnection(oraurl, orauser, orapass);
		} else if (database.equals(MYSQL_DB)) {
			Class.forName(mysqldriver);
			con = DriverManager.getConnection(mysqlurl + mysqldb, mysqluser,
					mysqlpass);
		}*/
		return con;
	}

	private static void getPartials(Connection con) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		pst = con.prepareStatement(ATTEMPT_QUERY);
		pst2 = con.prepareStatement(ATTEMPTDATA_QUERY);
		
		String testID = "13252698014282302";
		String userID = "13637";
		String activityID = "63864424";
		String attemptNO = "1";
		String sectionID = "32339106";		
		//String attemptdata_pk = "13252698283555451";
		
		pst.setString(1, testID);//TESTID
		pst.setString(2, userID);//USERID
		pst.setString(3, activityID);//activityid
		pst.setString(4, attemptNO);//attemptno
		pst.setString(5, sectionID);//sectionid
		
		pst2.setString(1, testID);
		pst2.setString(2, userID);
		pst2.setString(3, activityID);
		pst2.setString(4, attemptNO);
		pst2.setString(5, sectionID);
		//pst2.setString(6, attemptdata_pk);
		// execute Attempt SELECT query
		rs = pst.executeQuery();
		rs2 = pst2.executeQuery();
		InputStream theData = null;
		
		PrintWriter pw = new PrintWriter(new File("C:/partial.txt"));

		// iterate result-set and fetch BLOB data
		//System.out.println("########### PRINTING ATTEMPT ##############");
		PartialTO partialTO = new PartialTO();
		pw.println("########### PRINTING ATTEMPT ##############");
		if (rs.next()) {
			System.out.println("inside if");
			byte[] barray = rs.getBytes("params");
			ByteArrayInputStream bos = new ByteArrayInputStream(barray);
			Map<String, String> nameValueMap = tp_utility.hashFromStream(bos);
			Iterator<String> iterator = nameValueMap.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				String partialvalue = nameValueMap.get(key);
				System.out.println(key+"-->"+partialvalue);
				//pw.println(key+"-->"+partialvalue);
			}
		}
		//System.out.println("########### PRINTING ATTEMPT DATA ##############");
		pw.println("########### PRINTING ATTEMPT DATA ##############");
		while (rs2.next()) {
			byte[] barray = rs2.getBytes("params");
			ByteArrayInputStream bos = new ByteArrayInputStream(barray);
			Map<String, String> nameValueMap = tp_utility.hashFromStream(bos);
			Iterator<String> iterator = nameValueMap.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				String partialvalue = nameValueMap.get(key);
				System.out.println(key+"-->"+partialvalue);
				//pw.println(key+"-->"+partialvalue);
			}
		}
		System.out.println(" studentName :::: " + ((CustomMap<String, String>)partialTO.getTestParameter()).getParam("studentName"));
		
		pw.flush();
		pw.close();
	}
	private static ConcurrentHashMap hashFromStream( InputStream bStream )
	{
		ConcurrentHashMap result= new ConcurrentHashMap();
		
		if (bStream != null) 
		{
			try 
			{
				//DataInputStream input= new DataInputStream(bStream);
				BufferedReader input = new BufferedReader(new InputStreamReader(bStream, "UTF-8"));
				
				int count= input.read();
				for (int i= 0 ; i< count ; i++) 
				{
					String key= input.readLine();
					String value= readString(input);
					result.put( key, value );
				}
				bStream.close();
			} 
			catch (IOException i) 
			{
				//System.out.println("IOException in tp_sql.hashFromStream()");
				//i.printStackTrace();
			}
		}
		
		return( result );
	}	
			
	public static String readString( BufferedReader input )
	throws IOException{
		String result= input.readLine();
		/*if (result.equals(LONG_STRING))
		{
			//System.out.println("inputting " + LONG_STRING);
			int arrayLength= input.read();
			char[] barray= new char[ arrayLength ];
			input.read( barray, 0, arrayLength );
			result= new String( barray, "UTF-8" );
		}*/
		return(result);
	}
}

