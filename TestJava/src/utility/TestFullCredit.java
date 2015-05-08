package utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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
import Dataretrieval.Question.richPropertiesUtil;




public class TestFullCredit extends Thread {
	/**Add Start for JDBC connection*/

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
	public static String SERVER = EMPTY_STRING;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Connection con;
	
	public static String FLAGGED_ITEMS = "flaggedItems";
	public static String ADJUSTMENT_PROPERTY = "adjustment";
	public static String ADJUSTMENT_FULLCREDIT = "fullCredit";
	public static String ADJUSTMENT_MANUAL = "manualScore";
	public static String	LONG_STRING		= "***UTF String Too Long for writeUTF***";
	public static String DESTINATIONTID = "DESTINATIONTID";
	public static String DESTINATIONQID = "DESTINATIONQID";
	public static String SOURCETID = "SOURCETID";
	public static String SOURCEQID = "SOURCEQID";
	public static String INSERT_FULLCREDIT = "INSERT INTO fullcredit (testID, questionID, data) VALUES (?,?,?)";
	public static String UPDATE_FULLCREDIT = "UPDATE fullcredit SET data = ? WHERE testid = ? AND questionid = ?";
	public static String SELECT_FULLCREDIT = "SELECT data FROM fullcredit WHERE testid = ? and questionid = ? ";
	public static String INSERT_ERROR_TABLE = "INSERT INTO error_migration_xref ( testid, questionid, message ) values(?, ?, ?) ";
	public static String SELECT_TRACKBACK = "SELECT destinationtid, destinationqid FROM trackback WHERE sourcetid = ? AND sourceqid = ? ";
	public static String UPDATE_MEDIA_UTIL = "UPDATE media_util SET status = ? , testcount = ? WHERE sourcetid = ? AND sourceqid = ?";
	public static String SELECT_MEDIA_UTIL = "SELECT sourcetid, sourceqid FROM media_util WHERE status is null";
	public static String UPDATE = "update";
	public static String INSERT = "insert";
	public static TestFullCredit testFullCredit = null;
	public static String ERROR_STR_FOR_NOTIFYCONNECT = "Error in notifyConnect for";
	public static String MEDIA_COUNT = "SELECT  COUNT(DISTINCT m.testid) testCount FROM mediaconsumers m " +
    							"WHERE m.mediaid IN (SELECT mediaid FROM mediaconsumers " +
    							"WHERE testid = ?  AND consumerid = ?) " +
    							"AND m.testid NOT IN (SELECT DISTINCT tr.destinationtid FROM trackback tr " +
    							"WHERE tr.sourcetid = ? AND tr.sourceqid = ? )" +
    							"AND m.consumerid = '0'";
	
	
	
	public static void main(String args[])
	{   
	  int noOfThreads = 0;
	  String environment = EMPTY_STRING;
	  String dbName = EMPTY_STRING;
	  if (args != null && args.length == 3) {
			try {
				noOfThreads = Integer.parseInt(args[0]);
				environment = args[1];
				dbName = args[2];
			} catch (NumberFormatException nfe) {
				System.out.println("Please provide valid thread arguments");
				System.exit(1);
			}
		} else {
			System.out.println("Please provide Environment and dbName along with no of threads in the command line");
			System.exit(0);
		}
	  
	  try{
		  testFullCredit =new TestFullCredit();
		  testFullCredit.loadProperty( environment,dbName );
		  testFullCredit.getSourceTestQuestionID();
  		  
  		  for(int i =0;i<noOfThreads;i++)
  		  {
  			TestFullCredit testFullCreditThread = new TestFullCredit();
    		Thread t = new Thread(testFullCreditThread);
			t.start();
  		  }
	  }catch(Exception ex)
	  {
		System.out.println("Exception while getting results from media_util.");
		ex.printStackTrace();
	  }
   }
  
  
  
  
  public void run() 
  {
	  Connection con =null;
	  try{
		  con = getConnection();
		  setAutoCommit(con, false);
		  Map<String, String> sourceTestQuestionMap = null;
		  while((sourceTestQuestionMap = testFullCredit.getSourceTestQuestionIDForThread()) != null)
		  {
			  String sourceTid = sourceTestQuestionMap.get(SOURCETID);
			  String sourceQid = sourceTestQuestionMap.get(SOURCEQID);
			  try{
				  int testCount = calculateTestId(con, sourceTid, sourceQid);
				  List<Map<String, String>> destTestQList =
					  PickDestTestsFromTrackBack(con,sourceTid,sourceQid);
				  if(destTestQList != null && !destTestQList.isEmpty())
				  {
					  InsertFullCredit(con,destTestQList); 
				  }
				  updateStatusinMediaUtil(con, sourceTid, sourceQid, "P", testCount);
				  commit(con);
			  }catch(Exception e){
				  rollback(con);
				  putValuesInErrorTable(sourceTid,sourceQid, e.getMessage() );
				  updateStatusinMediaUtil(null, sourceTid, sourceQid, "E", 0 );
				  con = getConnection();
				  setAutoCommit(con, false);
			  }
		  }
		  
	  	}catch(Exception ex){
	  		ex.printStackTrace(); 
	  	}finally{
		  releaseResources(con);
	  	}
	  
  }
  
  
  public synchronized Map<String,String> getSourceTestQuestionIDForThread() throws Exception
  {
	  Map<String, String> sourceTestQuestionMap = null;
	  if(rs != null && rs.next())
	  {
		  sourceTestQuestionMap = new HashMap<String, String>();
		  sourceTestQuestionMap.put(SOURCETID, rs.getString("sourcetid"));
		  sourceTestQuestionMap.put(SOURCEQID, rs.getString("sourceqid"));
	  }else{
		  notifyAll();
	  }
	  
	  return sourceTestQuestionMap;
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
	  //System.out.println(" name -> "+System.getProperty("user.dir")); 
	  prop.load(new FileInputStream("./src/utility/FullCredit_"+env+".properties"));
	  //prop.load(new FileInputStream("FullCredit_"+env+".properties"));
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
	  this.SERVER = prop.getProperty("SERVER");
	  if(this.SERVER == null || (EMPTY_STRING).equals(this.SERVER)){
		  System.out.println("Please provide SERVER value in property file");
		  System.exit(0);
	  }
  }
  
  
  
  
  
  /** This method is designed to retrieve Source Testid and Source QuestionId 
 * @return List<Map<String, String>> Object
 */
 
  private void getSourceTestQuestionID()
  {
	  try{
		  con = getConnection();
		  pstmt = con.prepareStatement(SELECT_MEDIA_UTIL);
		  rs = pstmt.executeQuery();
	  }catch(Exception e){
		  System.out.println("Error in TestFullCredit.getSourceTestQuestionID fetching values from media_util table.");
		  e.printStackTrace();
	  }
  }
  
  

  
  /** This method is written to retrieve all the Connect test and questionids from TrackBack for
   * specific Library testid and questionsids
 * @param Connection con
 * @param String sourcetid
 * @param String sourceqid
 * @return List<Map<String, String>> Object
 * @throws Exception
 */
  public static List<Map<String, String>> PickDestTestsFromTrackBack(Connection con, String sourcetid, String sourceqid) throws Exception
  {
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 List<Map<String, String>> destTestQList = new ArrayList<Map<String, String>>();
	 try{
		 System.out.println("Sourcetid and sourceqid : "+sourcetid+" _ "+sourceqid);
		 pstmt = con.prepareStatement(SELECT_TRACKBACK);
		 pstmt.setString(1, sourcetid);
		 pstmt.setString(2, sourceqid);
		 rs = pstmt.executeQuery();
		 while(rs.next()){
			  Map<String, String> destTestQMap = new HashMap<String, String>();
			  String destinationtid = rs.getString("destinationtid");
			  String destinationqid = rs.getString("destinationqid");
			  System.out.println("destinationtid and destionationqid : "+destinationtid+" _ "+destinationqid);
			  if(destinationtid != null && !(EMPTY_STRING).equals(destinationtid) && destinationqid != null && !(EMPTY_STRING).equals(destinationqid)){
				  destTestQMap.put(DESTINATIONTID, destinationtid);
				  destTestQMap.put(DESTINATIONQID, destinationqid);
				  destTestQList.add(destTestQMap);
			  }
		 }
		 releaseResources(pstmt);
		 releaseResources(rs); 
	 }catch(Exception ex){
		System.out.println(" TestFullCredit.PickDestTestsFromTrackBack - Exception. "); 
		ex.printStackTrace();
		throw new Exception("Error in PickDestTestsFromTrackBack for SourceTestid = "+sourcetid+" and SourceQuestionid = "+sourceqid);
	 }finally{
		 releaseResources(pstmt);
		 releaseResources(rs);
	 }
	 return destTestQList;
  }
  
  
  
  
  
  /** This method is written to perform FullCredit Table insertion for a list of
   * Connect testids and questionids.
 * @param Connection con
 * @param List<Map<String,String>> destTestQList
 * @throws Exception
 */
  public static void InsertFullCredit(Connection con, List<Map<String,String>> destTestQList) throws Exception
  {
	  PreparedStatement insertPstmt = null;
	  PreparedStatement updatePstmt = null;
	  PreparedStatement selectPstmt = null;
	  String destinationtid = EMPTY_STRING;
	  String destinationqid = EMPTY_STRING;
	  ResultSet rs = null;
	  try{
		  if(destTestQList != null && !destTestQList.isEmpty()){
			  Iterator<Map<String,String>> destMapItr = destTestQList.iterator();
			  int batchCounter = 0;
			  updatePstmt = con.prepareStatement(UPDATE_FULLCREDIT);
			  insertPstmt = con.prepareStatement(INSERT_FULLCREDIT);
			  while(destMapItr.hasNext())
			  {
				  String flagInsertUpdate = EMPTY_STRING;
				  Map<String,String> destMap = destMapItr.next();
				  if(destMap != null && !destMap.isEmpty())
				  {
					  destinationtid = destMap.get(DESTINATIONTID);
					  destinationqid = destMap.get(DESTINATIONQID);
					  /**checking if this testid and questionid combination is already present in fullcredit table*/
					  selectPstmt = con.prepareStatement(SELECT_FULLCREDIT);
					  selectPstmt.setString(1, destinationtid);  
					  selectPstmt.setString(2, destinationqid);
					  rs = selectPstmt.executeQuery();
					  if(rs != null && rs.next()) {
						  if(rs.getBinaryStream("data") != null){
							  richPropertiesUtil entryProps = richPropertiesUtil.newInstance(FLAGGED_ITEMS);
							  entryProps = new richPropertiesUtil(readString(new DataInputStream(rs.getBinaryStream("data"))));
							  String theAdjustment = entryProps.getString(ADJUSTMENT_PROPERTY, EMPTY_STRING);
							  if(theAdjustment != null && ((EMPTY_STRING).equals(theAdjustment) || ADJUSTMENT_MANUAL.equals(theAdjustment))){
								flagInsertUpdate = UPDATE;  
							  }
						  }else{
							  flagInsertUpdate = INSERT; 
						  }
					  }else{
						  flagInsertUpdate = INSERT; 
					  }
					  releaseResources(rs);
					  releaseResources(selectPstmt);
					  if(UPDATE.equalsIgnoreCase(flagInsertUpdate)){
						  /**if this testid and questionid combination is present in fullcredit table, we are updating values*/
						  System.out.println(" InsertFullCredit : Updating values in fullCredit Table. destinationtid : "+ destinationtid +" destinationqid : "+destinationqid);
						  richPropertiesUtil flaggedItems = richPropertiesUtil.newInstance(FLAGGED_ITEMS);
						  flaggedItems.setString(ADJUSTMENT_PROPERTY, ADJUSTMENT_FULLCREDIT);
						  ByteArrayOutputStream theOut = new ByteArrayOutputStream();
						  writeString(flaggedItems.toXML(), new DataOutputStream(theOut));
						  byte[] barray = theOut.toByteArray();
						  ByteArrayInputStream bStream = new ByteArrayInputStream(barray);
						  
						  updatePstmt.setBinaryStream(1, bStream, barray.length);
					      updatePstmt.setString(2, destinationtid);
						  updatePstmt.setString(3, destinationqid);
						  updatePstmt.addBatch();
						  //updateBatchCounter ++;
						  batchCounter ++;
					  }else if(INSERT.equalsIgnoreCase(flagInsertUpdate)){
						  /**if this testid and questionid combination is not present in fullcredit table, we are inserting values*/
						  System.out.println(" InsertFullCredit : Inserting values in fullCredit Table. destinationtid : "+ destinationtid +" destinationqid : "+destinationqid);
						  insertPstmt.setString(1, destinationtid);
						  insertPstmt.setString(2, destinationqid);
		    			  
						  richPropertiesUtil flaggedItems = richPropertiesUtil.newInstance(FLAGGED_ITEMS);
						  flaggedItems.setString(ADJUSTMENT_PROPERTY, ADJUSTMENT_FULLCREDIT);
						  ByteArrayOutputStream theOut = new ByteArrayOutputStream();
						  writeString(flaggedItems.toXML(), new DataOutputStream(theOut));
						  byte[] barray = theOut.toByteArray();
						  ByteArrayInputStream bStream = new ByteArrayInputStream(barray);
						  
						  insertPstmt.setBinaryStream(3, bStream, barray.length);
						  insertPstmt.addBatch();
						  //addBatchCounter ++;
						  batchCounter ++;					  
					  }
					 if((INSERT).equals(flagInsertUpdate) || (UPDATE).equals(flagInsertUpdate)){
						 notifyConnect(destinationtid, destinationqid);
					 }
				  }
				  if(batchCounter%1000 == 0){
					  updatePstmt.executeBatch();
					  insertPstmt.executeBatch();
					  commit(con);
					  releaseResources(insertPstmt);
					  releaseResources(updatePstmt);					  
					  insertPstmt = con.prepareStatement(INSERT_FULLCREDIT);
					  updatePstmt = con.prepareStatement(UPDATE_FULLCREDIT);
					}
			  }
			  if(batchCounter%1000 != 0){
				  updatePstmt.executeBatch();
				  insertPstmt.executeBatch();
				  commit(con);
				  releaseResources(insertPstmt);
				  releaseResources(updatePstmt);				  
				}
		  }
	  	}catch(Exception ex){
	  		System.out.println("TestFullCredit.InsertFullCredit, Exception in Inserting data in fullcredit.");
	  		ex.printStackTrace();
	  		if(ex != null && ex.getMessage() != null && !(EMPTY_STRING).equals(ex.getMessage()))
	  		{
	  			if(ex.getMessage().contains(ERROR_STR_FOR_NOTIFYCONNECT)){
	  				throw ex;
	  			}else{
	  				throw new Exception("Error in InsertFullCredit for destination Testid = "+destinationtid+" and Questionid = "+destinationqid);
	  			}
	  		}else{
	  			throw new Exception("Error in InsertFullCredit for destination Testid = "+destinationtid+" and Questionid = "+destinationqid);
	  		}
	   }finally{
		   releaseResources(rs);
		   releaseResources(insertPstmt);
		   releaseResources(updatePstmt);
		   releaseResources(selectPstmt);
	   }
  }
  
  
  
  /** This method is designed to update Error_migration_xref table
   * if any error occurs.
 * @param testid
 * @param questionid
 * @param message
 */
  public static void putValuesInErrorTable(String testid, String questionid, String message)
  {
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
  
  
  
  
  /** this method is designed to update status in media_util table as P is processed and E if Error
 * @param con
 * @param sourcetid
 * @param sourceqid
 * @param status
 */
  public void updateStatusinMediaUtil(Connection con , String sourcetid, String sourceqid, String status, long testCount)
  {
	  PreparedStatement pstmt = null;
	  try
	  {
		if(con == null){
			con = getConnection();
		}
		pstmt = con.prepareStatement(UPDATE_MEDIA_UTIL);
		pstmt.setString(1, status);
		pstmt.setLong( 2, testCount);
		pstmt.setString(3, sourcetid);
		pstmt.setString(4, sourceqid);
		pstmt.executeUpdate();
		System.out.println(" Updating media_util for sourceTestid = "+sourcetid+" and questionid = "+sourceqid+" for status = "+status+" and testCount = "+testCount );
	  }catch(Exception ex){
		  System.out.println(" Exception occured in Upadting values in updateStatusinMediaUtil for sourceTestid = "+sourcetid+" And sourceqid = "+sourcetid);
		  ex.printStackTrace();
	  }finally{
		releaseResources(pstmt); 
		if(("E").equals(status))
		{
			releaseResources(con);	
		}
	  }
  }
  
  
  

	public static int calculateTestId(Connection con, String testid, String questionid) throws Exception
	{
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
		int countTestId =0;
		try{
			pstmt = con.prepareStatement(MEDIA_COUNT);
			pstmt.setString(1, testid);
			pstmt.setString(2, questionid);
			pstmt.setString(3, testid);
			pstmt.setString(4, questionid);
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()){
				countTestId = rs.getInt("testCount");
			}
		 System.out.println(" TestCount "+countTestId+" for source Testid = "+testid+" And questionid = "+questionid );
		 return countTestId;	
		}catch(Exception ex){
			throw new Exception("Error in TestCountMedia.calculateTestId for Testid = "+testid+" and Questionid = "+questionid);
		}
	}
  
  
  
  /**
 * @param String theString
 * @param DataOutputStream out
 * @throws IOException
 */
  public static void writeString( String theString, DataOutputStream out )
	throws IOException
  {
		if (theString.length() > 65535)	{
			out.writeUTF( LONG_STRING );
			byte[] barray= theString.getBytes("UTF-8");
			out.writeInt( barray.length );
			out.write( barray, 0, barray.length);
		}
		else {
			out.writeUTF( theString );
		}
  }
  
  
  /**
 * @param input
 * @return
 * @throws IOException
 */
public static String readString( DataInputStream input )
	throws IOException
 {
	String result= input.readUTF();
	if (result.equals(LONG_STRING))
	{
		//System.out.println("inputting " + LONG_STRING);
		int arrayLength= input.readInt();
		byte[] barray= new byte[ arrayLength ];
		input.read( barray, 0, arrayLength );
		result= new String( barray, "UTF-8" );
	}

	return(result);
 }


  /** this method is designed to update spark
 * @param testId
 * @param questionId
 */
  public static void notifyConnect(String testId, String questionId) throws Exception
  {
	  StringBuilder theURL = new StringBuilder(SERVER);
	  theURL.append("/connect").append("/handleBadItem.do")
	  .append("?qid=").append(questionId).append("&wid=").append(testId).append("&action=fullcredit");
	  System.out.println("The formed URL : "+theURL);
	  try{
	    URL url = new URL(theURL.toString());
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.connect();

		// read in the result
		BufferedInputStream classIn = new BufferedInputStream(connection.getInputStream(), 4096);
		long length = connection.getContentLength();
		if (length < 0)
			length = classIn.available(); // sometimes the wrong number is
											// returned, try this method

		if (length == -1) {
			classIn.close();
			System.out.println("  no response from Connect");
			throw new IOException("Data length = -1");
		} else {
			byte[] data = new byte[(int) length];
			int soFar = 0;
			int remaining;
			int incoming;
			while (soFar < length) {
				remaining = (int) length - soFar;
				incoming = classIn.read(data, soFar, Math.min(remaining, 4096));
				soFar += incoming;
			}
			classIn.close();

			String theResults = new String(data);
			System.out.println("  Spark returned " + theResults);

			if (theResults.toLowerCase().indexOf("true") < 0) {
				System.out.println("  Spark rejected adjusted credit award");
				throw new Exception();
			}
		}
	  }catch(Exception ex){
		  ex.printStackTrace();
		  throw new Exception(ERROR_STR_FOR_NOTIFYCONNECT+" destination testid  = "+testId+" and questionid = "+questionId );
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
