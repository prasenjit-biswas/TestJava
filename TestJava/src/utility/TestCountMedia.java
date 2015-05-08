package utility;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestCountMedia {
    
	public static final	String DATABASE_ORACLE	= "oracle";
	public static final	String DATABASE_MYSQL	= "mysql";
	public static final String EMPTY_STRING = "";
	static String database = EMPTY_STRING;
	public static  String Oracledriver  ="oracle.jdbc.driver.OracleDriver";
	public static  String Oracleurl      = EMPTY_STRING;
	public static  String Oracleusername = EMPTY_STRING;
	public static  String Oraclepassword = EMPTY_STRING;
	
	static String mysqldriver = "com.mysql.jdbc.Driver";
	public static  String mySqlJDBCURL = EMPTY_STRING;
	public static  String mySqlusername = EMPTY_STRING;
	public static  String mySqlpassword = EMPTY_STRING;
	//**Add End for JDBC connection*//*
	
	public static String DESTINATIONTID = "DESTINATIONTID";
	public static String DESTINATIONQID = "DESTINATIONQID";
	public static String SOURCETID = "SOURCETID";
	public static String SOURCEQID = "SOURCEQID";
	
	
	public static String query = "SELECT  COUNT(DISTINCT m.testid) testCount FROM mediaconsumers m " +
			              "WHERE m.mediaid IN (SELECT mediaid FROM mediaconsumers " +
			              "WHERE testid = ?  AND consumerid = ?) " +
			              "AND m.testid NOT IN (SELECT DISTINCT tr.destinationtid FROM trackback tr " +
			              "WHERE tr.sourcetid = ? AND tr.sourceqid = ? )" +
			              "AND m.consumerid = '0'";
	
	public static String INSERT_ERROR_TABLE = "INSERT INTO error_migration_xref ( testid, questionid, message ) values(?, ?, ?) ";
	
	
	public static void main(String[] args) 
	{
		Connection con = null;
		try{
			TestCountMedia testCountMedia = new TestCountMedia();
			testCountMedia.loadProperty(args[0], args[1]);
			con = getConnection();
			List<Map<String,String>> listOfsourceTestQuestionMap = testCountMedia.getSourceTestQuestionID();
			Map<String,String> sourceTestQuestionMap = null;
			//System.out.println("  Testid,	    Questionid,	     TestIdCount");
			if(listOfsourceTestQuestionMap != null && !listOfsourceTestQuestionMap.isEmpty())
			{
				Iterator<Map<String,String>> listOfsourceTestQuestionMapItr = listOfsourceTestQuestionMap.iterator();	
				while(listOfsourceTestQuestionMapItr.hasNext())
				{ 
					sourceTestQuestionMap =  listOfsourceTestQuestionMapItr.next();
					String sourceTid = sourceTestQuestionMap.get(SOURCETID);
					String sourceQid = sourceTestQuestionMap.get(SOURCEQID);
		           try{
					calculateTestId( con, sourceTid, sourceQid);
		           }catch(Exception e){
		        	   e.printStackTrace();
		               putDataInErrorTable(sourceTid, sourceQid, e.getMessage());
		               con = getConnection();
		           }
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			releaseResources(con);
		}
	}
	
	
	
	public static void calculateTestId(Connection con, String testid, String questionid) throws Exception
	{
	    PreparedStatement pstmt  = null;
	    ResultSet rs = null;
	    int countTestId =0;
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, testid);
			pstmt.setString(2, questionid);
			pstmt.setString(3, testid);
			pstmt.setString(4, questionid);
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				countTestId = rs.getInt("testCount");
			}
			System.out.println(testid+", "+questionid+",  "+countTestId);
		}catch(Exception ex){
			throw new Exception("Error in TestCountMedia.calculateTestId for Testid = "+testid+" and Questionid = "+questionid);
		}
	}
	
	
	
	private List<Map<String,String>> getSourceTestQuestionID(){
		  List<Map<String,String>> listOfsourceTestQuestionMap = new ArrayList<Map<String,String>>();
		  Iterator<Map<String,String>> listOfsourceTestQuestionMapItr = null;
		  Map<String, String> sourceTestQuestionMap = new HashMap<String, String>();
		  sourceTestQuestionMap.put(SOURCETID, "13252698002613196");
		  sourceTestQuestionMap.put(SOURCEQID, "13252698001817769");
		  listOfsourceTestQuestionMap.add(sourceTestQuestionMap);
		  return listOfsourceTestQuestionMap;
	  }

	
	
	
	
	public static void putDataInErrorTable(String testid, String questionid, String message){
		Connection errorCon = null;
		  PreparedStatement pstmt = null;
		  try{
			  System.out.println("TestFullCredit.putValuesInErrorTable : Inserting values in error_migration_xref ");
			  errorCon = getConnection();
			  pstmt = errorCon.prepareStatement(INSERT_ERROR_TABLE);
			  pstmt.setString(1, testid);
			  pstmt.setString(2, questionid);
			  pstmt.setString(3, message);
			  pstmt.executeUpdate();
		  }catch(Exception e){
			  System.out.println("TestFullCredit.putValuesInErrorTable:  Error in Inserting values in ERROR_MIGATION_TABLE : ");
			  e.printStackTrace();
		  }finally{
			  releaseResources(pstmt);
			  releaseResources(errorCon);
		  }
	}
	
	
	
	
	
	
	/** this method is designed to get database and server names from property file for a specific 
	   * environment
	 * @param env
	 * @param database
	 * @throws Exception
	 */
	  private void loadProperty(String env , String database) throws Exception
	  {
		  System.out.println("Environment : "+ env);
		  Properties prop = new Properties();
		  prop.load(new FileInputStream("C:/PrasenjitWorkSpace/TestJava/src/utility/FullCredit_"+env+".properties"));
		  if(prop == null){
			  System.out.println("Propety File value is Null ");
			  System.exit(0);
		  }
		  if(database == null || (EMPTY_STRING).equals(database)){
			  System.out.println("database value in the argument is empty or Null  ");
			  System.exit(0);
		  }
		  if("oracle".equalsIgnoreCase(database)) {
			  this.database = DATABASE_ORACLE;
			  this.Oracleurl = prop.getProperty("oracleurl");
			  if(this.Oracleurl == null || (EMPTY_STRING).equals(this.Oracleurl)) {
				  System.out.println("Please provide oracleurl value in property file");
				  System.exit(0);
			  }
			  this.Oracleusername = prop.getProperty("oracleusername");
			  if(this.Oracleusername == null || (EMPTY_STRING).equals(this.Oracleusername)) {
				  System.out.println("Please provide Oracleusername value in property file");
				  System.exit(0);
			  }
			  this.Oraclepassword = prop.getProperty("oraclepassword");
			  if(this.Oraclepassword == null || (EMPTY_STRING).equals(this.Oraclepassword)) {
				  System.out.println("Please provide Oraclepassword value in property file");
				  System.exit(0);
			  }
		  }else if("mysql".equalsIgnoreCase(database)) {
			  this.database = DATABASE_MYSQL;
			  this.mySqlJDBCURL = prop.getProperty("mySqlJDBCURL");
			  if(this.mySqlJDBCURL == null || (EMPTY_STRING).equals(this.mySqlJDBCURL)){
				  System.out.println("Please provide mySqlJDBCURL value in property file");
				  System.exit(0);
			  }
			  this.mySqlusername = prop.getProperty("mySqlusername");
			  if(this.mySqlusername == null || (EMPTY_STRING).equals(this.mySqlusername)){
				  System.out.println("Please provide mySqlusername value in property file");
				  System.exit(0);
			  }
			  this.mySqlpassword = prop.getProperty("mySqlpassword");
			  if(this.mySqlpassword == null || (EMPTY_STRING).equals(this.mySqlpassword)){
				  System.out.println("Please provide mySqlpassword value in property file");
				  System.exit(0);
			  }
		  }
		  
	  }
	  
	  /**Add Start for Database Operations*/
	  
	  
	  public static Connection getConnection() throws SQLException, Exception{
		  Connection con = null;
		  System.out.println("database : " + database);
		  if(DATABASE_ORACLE.equals(database)){
	    	Class.forName(Oracledriver);
	    	con = DriverManager.getConnection(Oracleurl, Oracleusername, Oraclepassword);
	      } else if(DATABASE_MYSQL.equals(database)){
	    	Class.forName(mysqldriver);
	    	con = DriverManager.getConnection(mySqlJDBCURL, mySqlusername, mySqlpassword);
	      }
		  return con;
		}
	  
	  
	  public static void releaseResources(Connection jConnection){
			if (jConnection != null) {
	          try {
	          		jConnection.close();
	          } catch (SQLException e) {
	          	
	                e.printStackTrace();
	                System.out.println(" Exception while closing connection  " + e.getMessage());
	          	
	          }
	          jConnection = null;
	      }
	  }

	  public static void releaseResources(Statement stmt){
		  if (stmt != null) {
			  try {
	          		stmt.close();
	          } catch (SQLException e) {
	          	
	          	  e.printStackTrace();
	                System.out.println(" Exception while closing statement " + e.getMessage());
	          	
	          }
	          stmt = null;
		  }
	  }
		
	  public static void releaseResources(ResultSet rst){
		  if (rst != null) {
	          try {
	          	rst.close();
	          } catch (SQLException e) {
	          	
	          	  e.printStackTrace();
	                System.out.println(" Exception while closing ResultSet " + e.getMessage());
	          	
	          }
	          rst = null;
		  }
	  }
	  
	  public static void rollback(Connection jConnection){
		  if(DATABASE_ORACLE.equals(database) && jConnection != null){
				try {
					jConnection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(" Exception while rollback : " + e.getMessage());
				}
		  }
	  }
			
	  public static void setAutoCommit(Connection jConnection, boolean autoCommit){
		  if(DATABASE_ORACLE.equals(database) && jConnection != null){
				try {
					jConnection.setAutoCommit(autoCommit);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(" Exception while setAutoCommit : " + e.getMessage());
				}
		  }
	  }
		
	  public static void commit(Connection jConnection){
		  if(DATABASE_ORACLE.equals(database) && jConnection != null){
				try {
					jConnection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(" Exception while commit : " + e.getMessage());
				}
		  }
	  }
	   /**Add End for Database Operations*/

}
