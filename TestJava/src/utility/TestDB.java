package utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import Dataretrieval.util.tp_utility;



public class TestDB {
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	
	
	/*
	static String mysqlurl = "jdbc:mysql://nvldb09.eppg.com:3306/";
	static String mysqldb = "demo_prep";
	
	static String mysqluser = "demo_prep";
	static String mysqlpass = "pixie";
	*/
	/*
	static String mysqlurl = "jdbc:mysql://ewnvldb15.eppg.com:3306/";
	static String mysqldb = "aris_spark_pqa";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	static String mysqluser = "aris_spark_pqa";
	static String mysqlpass = "aris_spark_pqa";	
	*/
	/** EZTO Dev*/
	///*
	static String oraurl = "jdbc:oracle:thin:@nj09mhe0234-vip.eppg.com:1521:eztodev1";
	static String orauser = "ezto";
	static String orapass = "eztodev";
		
	static String mysqlurl = "jdbc:mysql://nvldb06.eppg.com:3306/";
	static String mysqldb = "ezto_dev";
	static String mysqluser = "ezto_dev";
	static String mysqlpass = "pixie";
	
	//*/
	
	/** EZTO QALive*/
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	static String orauser = "ezto";
	static String orapass = "ezto123";
	*/
	/** EZTO QALive Temp*/
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	static String orauser = "eztmp";
	static String orapass = "eztmp";
	*/
	
	/** EZTO PQA*/
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztoload";
	*/
	
	/** EZTO Prod*/
	/*
	public static final String oraurl = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb01-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb02-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb03-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb04-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb05-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod)) )";
	public static final String orauser = "ezto";
	public static final String orapass = "eztoprod";
	
	
	static String mysqlurl = "jdbc:mysql://ewnvldb13.eppg.com:3306/";
	static String mysqldb = "aris_spark";	
	static String mysqluser = "aris_spark";
	static String mysqlpass = "pixie";
	*/
	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB;
	
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	static String LICENSE_TYPE_AMAZON_KEYS	= "amazonKeys";
	
	public static void main(String argv[]) throws Exception{
    
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;       
    
    try {
    	con = getConnection();
    	
    	//getFlash(con);
    	//getQuestion(con);
    	//getTest1(con);
    	//getTest(con);
    	//getTrackback(con);
    	//getPartials(con);
    	//propagateQuestion(con);
    	//getPartial(con, "13252698027166540", "2850831_188099360_2_4608547");
    	//getTestDate(con);
    	//myPoolsInfo(con);
    	testingFB(con);
    	//getGroupMembers(con);
        /*
        DataInputStream theInput= new DataInputStream(new ByteArrayInputStream(theBytes.toByteArray()));
        
        int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++){
			System.out.println("names (" + (i+1) + ") : "+ theInput.readUTF());
		}
			
		// read the values
		int valueCount= theInput.readInt();
		for (int i=0 ; i<valueCount ; i++){
			System.out.println("values (" + (i+1) + ") : "+ readString(theInput));
		}
		*/
        
    } catch(SQLException exSQLException){
    	System.out.print(exSQLException);
	    exSQLException.printStackTrace();
    	if(con != null){
    	  con.rollback();
	    }
	} catch(Exception exException){
		System.out.print(exException);
	    exException.printStackTrace();
		if(con != null){
    	  con.rollback();
	    }
    }finally{
    	if(rs != null){
    	  rs.close();
    	}
		if(pst != null){
    	  pst.close();
		}
		if(con != null){
		  con.close();
		}
    }
  }
	
	public static Connection getConnection() throws SQLException, Exception{
	  Connection con = null;
	  System.out.println("database : " + database);
	  if(database.equals(ORACLE_DB)){
    	Class.forName(oradriver);
        con = DriverManager.getConnection(oraurl, orauser, orapass);
      } else if(database.equals(MYSQL_DB)){
    	Class.forName(mysqldriver);
        con = DriverManager.getConnection(mysqlurl + mysqldb, mysqluser, mysqlpass);
      }
	  con.setAutoCommit(false);
	  return con;
	}
	
	
	
	
	
	
	private static void testingFB(Connection con) throws Exception{
		PreparedStatement insertPstmt = con.prepareStatement("INSERT INTO source_dest_question_mapping(sourceTID, sourceQID, destinationTID, destinationQID, timestamp) VALUES (?, ?, ?, ?, ?)");
		PreparedStatement deletePstmt = con.prepareStatement("DELETE FROM source_dest_question_mapping where sourceTID = ? and sourceQID = ?");
		insertPstmt.executeBatch();
		deletePstmt.execute();
		System.out.println("completed testingFB123");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static Element myPoolsInfo(Connection con ) throws Exception
	{
	Element textbookInfo= new Element("textbook");

	Element isbnElement= new Element("isbn");
	textbookInfo.addContent(isbnElement);
	isbnElement.addContent( "MY_QUESTION_POOLS" );

	Element authorElement= new Element("author");
	textbookInfo.addContent(authorElement);
	authorElement.addContent( "USER_ID" );

	Element titleElement= new Element("title");
	textbookInfo.addContent(titleElement);
	titleElement.addContent( "MY_QUESTION_POOLS_TITLE" );

	Element editionElement= new Element("edition");
	textbookInfo.addContent(editionElement);

	Element localElement= new Element("country");
	textbookInfo.addContent(localElement);
	
	// add the test info
	//String currentTestID= theHandler.getParam(classware_hm.TEST_ID);
	//Connection con= null;
	PreparedStatement stmt = null;
	ResultSet ls= null;
	//PreparedStatement pstmt = null;
	//ResultSet cs= null;
	try
	{
		tp_sortedListUtil poolList= new tp_sortedListUtil();
	
		//con= theHandler.getConnection();
		//Statement stmt= con.createStatement();
		long start = System.currentTimeMillis();
		System.out.println("time starts : " + start);
		//System.out.println("");
		String userid = "6415663";//13633 - 6415663
		//pstmt= con.prepareStatement("SELECT COUNT(*) FROM questions WHERE bankID=?");
	
		//ResultSet ls= stmt.executeQuery("SELECT testID, bankID, title FROM tests WHERE indexID=" + Long.toString(classware_hm.CLASSWARE_INDEX_ID) + " AND owner LIKE '" + theHandler.getParam(classware_hm.USER_ID) + "|%'");
		stmt= con.prepareStatement("SELECT testID, bankID, title FROM tests t WHERE indexID=? AND owner LIKE ? " +
								   "and exists (SELECT 1 FROM questions q WHERE q.bankID = t.bankid) order by t.title");
		stmt.setLong(1, 4);
		stmt.setString(2, new StringBuffer(userid).append("|%").toString());
		ls= stmt.executeQuery();
		int i=0;
		while (ls.next())
		{
			String testID= ls.getString("testID");
			//if (testID.equals(currentTestID)) continue;
			
			String theTitle= ls.getString("title");
			
			// only include populated assignments
			/*
			pstmt.setLong(1, ls.getLong("bankID"));
			if(cs != null) {
				cs.close();
			}
			cs= pstmt.executeQuery();
			if (!cs.next()) continue;
			if (cs.getInt(1) == 0) continue;
			*/
			Element theInfo= new Element("pool");
			
			Element titleElement2= new Element("title");
			theInfo.addContent( titleElement2 );
			titleElement2.addContent( theTitle );
			
			Element ezidElement= new Element("ezid");
			theInfo.addContent( ezidElement );
			System.out.println("i : " + (++i));
			ezidElement.addContent( testID );
			
			Element pathElement= new Element("pathString");
			theInfo.addContent( pathElement );

			poolList.add( theTitle, theInfo );
		}
		//pstmt.close();
		long end = System.currentTimeMillis();
		System.out.println("time taken 1 : " + (end - start));
		
		ArrayList theSort= poolList.sortedVector();
		for (i=0; i< theSort.size(); i++)
			textbookInfo.addContent( (Element)theSort.get(i) );
		
		end = System.currentTimeMillis();
		System.out.println("time taken 2 : " + (end - start));
		//new Date((end - start));
		//stmt.close();
	
		return( textbookInfo );
	}
	catch (SQLException s)
	{
		s.printStackTrace();
		
	}finally{
		
	}

	throw new Exception("hm_picker_r3a.myPoolsInfo Exception");
}
	
	public static void getDupGroupmembers(Connection con) throws Exception{
		PreparedStatement pst = null;	    
		ResultSet rst = null;
	    pst = con.prepareStatement("SELECT rowid,groupid,testid FROM groups g WHERE rowid <  " +
	    							"( SELECT MAX(rowid) from groups g1 where g1.testid = g.testid AND g1.groupid = g.groupid " +
	    							"AND g1.testid = '13252698032612113')");
	    
	    rst = pst.executeQuery();
	    
	    
	    while(rst.next()){
	    	
	    	rst.getLong("groupid");
	    	rst.getString("TESTID");
	    	RowId rowId = rst.getRowId("rowid");
	    	
	    	System.out.println(new String(rowId.getBytes()));
	    }
	    
	    
	}
	
	public static void deleteDupGroupmembers(Connection con) throws Exception{
		PreparedStatement pst = null;	    
		ResultSet rst = null;
	    pst = con.prepareStatement("SELECT groupid, BANKID, ORDERID, QUESTIONID, TESTID, REALQUESTIONID " +
	    							"from groupmembers gm where testid = '13252698032614674' " +
	    							"and groupid IN (13252698032315849, 13252698032320840, 13252698032409972) " +
	    							"GROUP BY groupid, BANKID, ORDERID, QUESTIONID, TESTID, REALQUESTIONID " +
	    							"HAVING COUNT(*) > 1");
	    
	    rst = pst.executeQuery();
	    GroupMembersTO groupMembersTO = null;
	    ArrayList<GroupMembersTO> groupMembersTOList = new ArrayList<GroupMembersTO>();
	    while(rst.next()){
	    	groupMembersTO = new GroupMembersTO();
	    	
	    	groupMembersTO.setGroupID(rst.getLong("groupid"));
	    	groupMembersTO.setBankID(rst.getLong("BANKID"));
	    	groupMembersTO.setOrderID(rst.getLong("ORDERID"));
	    	groupMembersTO.setQuestionID(rst.getString("QUESTIONID"));
	    	groupMembersTO.setTestID(rst.getString("TESTID"));
	    	groupMembersTO.setActualQuestionID(rst.getString("REALQUESTIONID"));
	    	
	    	System.out.println(groupMembersTO);
	    	
	    	groupMembersTOList.add(groupMembersTO);
	    }
	    
	    pst.close();
	    rst.close();
	    
	    pst = con.prepareStatement("DELETE FROM GROUPmemberS gm where " +
	    						   "gm.groupid = ? AND gm.BANKID = ? AND gm.ORDERID = ? AND gm.QUESTIONID = ? AND gm.REALQUESTIONID = ? AND gm.testid = ? " +
	    						   "AND ROWID < (SELECT MAX(ROWID) FROM GROUPmemberS gm1 where " +
	    						   "gm1.groupid = ? AND gm1.BANKID = ? AND gm1.ORDERID = ? AND gm1.QUESTIONID = ? AND gm1.REALQUESTIONID = ? AND gm1.testid = ? )");
		
	    System.out.println("\n############ Deletion\n");
	    
	    for(GroupMembersTO groupMembersTO1 : groupMembersTOList){
	    	pst.setLong(1, groupMembersTO1.getGroupID());
	    	pst.setLong(2, groupMembersTO1.getBankID());
	    	pst.setLong(3, groupMembersTO1.getOrderID());
	    	pst.setString(4, groupMembersTO1.getQuestionID());
	    	pst.setString(5, groupMembersTO1.getActualQuestionID());
	    	pst.setString(6, groupMembersTO1.getTestID());
	    	
	    	pst.setLong(7, groupMembersTO1.getGroupID());
	    	pst.setLong(8, groupMembersTO1.getBankID());
	    	pst.setLong(9, groupMembersTO1.getOrderID());
	    	pst.setString(10, groupMembersTO1.getQuestionID());
	    	pst.setString(11, groupMembersTO1.getActualQuestionID());
	    	pst.setString(12, groupMembersTO1.getTestID());
	    	
	    	System.out.println(groupMembersTO1);
	    	
	    	int count = pst.executeUpdate();
	    	System.out.println("count : " + count);
	    }
	    
	    con.commit();
	    
	    pst.close();
		
    		
    }
	
	public static void getGroupsGroupMembersIDCreator(Connection con) throws Exception{
		Statement ps = null;
		ResultSet rst = null;
	    
	    ps = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);	    
	    
		if(database.equals(MYSQL_DB)){
		  //ps.setFetchSize(Integer.MIN_VALUE);
		}
	    
		long start = System.currentTimeMillis();
		
	    rst = ps.executeQuery("SELECT distinct groupid, name, testid FROM groups limit 500");
		
	    long end = System.currentTimeMillis();
	    System.out.println(" time 1 : " + (end - start));
		while (rst.next())
		{
			//System.out.println(" groupid : " + rst.getLong("groupid"));
			//System.out.println(" name : " + rst.getString("name"));
			//System.out.println(" testid : " + rst.getString("testid"));
		}
		end = System.currentTimeMillis();
	    System.out.println(" time 2 : " + (end - start));
	   
		if(rst != null){
			rst.close();
    	}
		if(ps != null){
			ps.close();
		}
		if(con != null){
      	  con.close();
  		}
    }
	
	public static InputStream readDataFromStream(InputStream dbin){
		  ByteArrayOutputStream bout = null;
		  byte[] buffer = null;
		  byte barray[]= null;
		  InputStream dataInput = null;
		  
		  try{
			bout= new ByteArrayOutputStream();
	  		buffer = new byte[32768];
			for(;;) {	
			  int size = dbin.read(buffer);
			  if (size == -1) break;
			  bout.write(buffer,0,size);
			}
			dbin.close();
			bout.flush();
			barray= bout.toByteArray();
			bout.close();
			
			dataInput= new DataInputStream(new ByteArrayInputStream(barray));
			
		  }catch(Exception ex){
			System.out.println("Exception in tp_sql.readDataFromStream");
			ex.printStackTrace();
		  }
		  return dataInput;
		}
	
	private static HashMap<String, String> hashFromStream( InputStream bStream ){
		HashMap<String, String>  result= new HashMap<String, String> ();
		
		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				
				int count= input.readInt();
				for (int i= 0 ; i< count ; i++) 
				{
					String key= input.readUTF();
					String value= readString(input);
					result.put( key, value );
				}
				bStream.close();
			} 
			catch (IOException i) 
			{
				System.out.println("IOException in tp_sql.hashFromStream()");
				i.printStackTrace();
			}
		}
		
		return( result );
	}
	
	static String readString( DataInputStream input )
	  throws IOException{
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
	
	public static void checkOrdering(Connection con) throws Exception{
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
	    ResultSet rst = null;
	    ResultSet rst1 = null;
	    ResultSet rst2 = null;
	    
	    pstmt= con.prepareStatement("SELECT testid, groupid from groups ");
	    pstmt1= con.prepareStatement("SELECT COUNT(*) FROM groupmembers gm WHERE testid = ? AND groupid = ? AND realquestionid IS NULL ");
	    pstmt2= con.prepareStatement("SELECT COUNT(*) FROM groupmembers gm WHERE testid = ? AND groupid = ? ");
		
	    rst = pstmt.executeQuery();
		
		while (rst.next())
		{
			String testid = rst.getString("testid");
			long groupid = rst.getLong("groupid");
			int count1 = 0;
			int count2 = 0;
			
			pstmt1.setString(1, testid);
			pstmt1.setLong(2, groupid);
			
			rst1 = pstmt1.executeQuery();
			if(rst1.next()){
				count1 = rst1.getInt(1);
			}
			
			pstmt2.setString(1, testid);
			pstmt2.setLong(2, groupid);
			
			rst2 = pstmt2.executeQuery();
			if(rst2.next()){
				count2 = rst2.getInt(1);
			}
			
			if(count1 > 0 && count1 < count2){
				System.out.println("testid : " + testid + " , groupid" + groupid);
				break;
			}
			rst1.close();
			rst2.close();
		}						 
	   
		if(rst1 != null){
			rst1.close();
    	}
		if(pstmt1 != null){
			pstmt1.close();
		}
		if(rst2 != null){
			rst2.close();
    	}
		if(pstmt2 != null){
			pstmt2.close();
		}
		if(con != null){
      	  con.close();
  		}
    }
	
	public static void getTrackback(Connection con) throws Exception{
		PreparedStatement stmt = null;
	    ResultSet consumers = null;
	    stmt= con.prepareStatement("SELECT destinationTID,destinationQID FROM trackback WHERE rownum < 50 ORDER BY destinationTID,destinationQID");
		
		consumers= stmt.executeQuery();
		Map<String, List<String>> trackbackMap = new HashMap<String, List<String>>();
		List<String> destinationQIDList = null;
		String prevDestTID = null;
		boolean newTID = false;
		while (consumers.next())
		{
			String destTID= consumers.getString("destinationTID");
			String destQID= consumers.getString("destinationQID");
			
			if(prevDestTID == null){ // first time initialization
				prevDestTID = destTID;
				newTID = true;
			} else if (!destTID.equalsIgnoreCase(prevDestTID)){
				newTID = true;
			} else{
				newTID = false;
			}
			
			if(newTID){
			  destinationQIDList = new ArrayList<String>();
			  destinationQIDList.add(destQID);
			  
			  trackbackMap.put(destTID, destinationQIDList);
			  prevDestTID = destTID;
			} else {
				destinationQIDList = trackbackMap.get(destTID);
				destinationQIDList.add(destQID);
			}
		}						 
	   
		for(String destTID : trackbackMap.keySet()){
			System.out.println("destTID - " + destTID);
			for(String destQID : trackbackMap.get(destTID)){
				System.out.println("destQID - " + destQID);
			}
		}
		
        if(consumers != null){
        	consumers.close();
    	}
		if(stmt != null){
			stmt.close();
		}
		if(con != null){
      	  con.close();
  		}
    }
	
	public static void getQuestion(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    int format = -1;
	    InputStream theData = null;
	    StringBuilder sb = new StringBuilder("SELECT questionID, format, data FROM questions WHERE questionID IN (")
	    .append(" '13111751492408300')");							 
	    //.append(" '13095157601136000')");
	    							 //.append(" '13081555399836500')");						     
								     //.append("'13081625153638013')");
		pst = con.prepareStatement(sb.toString());
    	//pst.setString(1, "13062445364771500");    	
		//pst.setString(1, "13091881394023400");
		
    	rs = pst.executeQuery();
    	
    	//System.out.println("before rs.next() : " + new Timestamp(System.currentTimeMillis()));
        while (rs.next()) {
        	//System.out.println("after rs.next() : " + new Timestamp(System.currentTimeMillis()));
        	format = rs.getInt("format");
        	//System.out.println("before rs.getBinaryStream : " + new Timestamp(System.currentTimeMillis()));
        	theData = rs.getBinaryStream("data");
        	//System.out.println("after rs.getBinaryStream : " + new Timestamp(System.currentTimeMillis()));
        	
        	DataInputStream input= new DataInputStream(theData);
            int questionType= input.readInt();
            
            System.out.println("format : " + format);
            System.out.println("questionType : " + questionType);
            question(input, format);
        }
        if(rs != null){
        	  rs.close();
        	}
    		if(pst != null){
        	  pst.close();
    		}
    		if(con != null){
      	  con.close();
  		}
    		//System.out.println("all are closed ");
    }
	
	public static void getFlash(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    int format = -1;
	    InputStream theData = null;
	    
	    pst = con.prepareStatement("SELECT count(*) name FROM flash WHERE modified > ?");
    	pst.setString(1, "1279556780545");    	
		
    	rs = pst.executeQuery();
    	
    	while (rs.next()) {
        	System.out.println("######## name : " + rs.getString("name"));
        	//question(input, format);
        }
        if(rs != null){
        	  rs.close();
        	}
    		if(pst != null){
        	  pst.close();
    		}
    		if(con != null){
      	  con.close();
  		}
    }
	
	public static void propagateQuestion(Connection con) throws Exception{
	  PreparedStatement pst = null;
	  ResultSet rst = null;
	  ArrayList<ArrayList<String>> testlist = new ArrayList<ArrayList<String>>();
	  ArrayList<String> testDtls = null;
	  try{
		con.setAutoCommit(false);
		pst = con.prepareStatement("SELECT testid, title FROM tests WHERE ROWNUM < 2");
		rst = pst.executeQuery();
		if(rst.next()){
		  testDtls = new ArrayList<String>();
		  
		  testDtls.add(rst.getString("title"));
		  testDtls.add(rst.getString("testid"));
		  
		  System.out.println("testid : " + testDtls.get(1) + " , title : " + testDtls.get(0));
		  
		  testlist.add(testDtls);
		}
		
		rst.close();
		pst.close();
		
	    pst = con.prepareStatement("update tests set title = ? where testid = ?");
    	
	    System.out.println("updating tests");
	    
    	for(ArrayList<String> tests : testlist){
    		
    	  //System.out.println("testid : " + tests.get(1) + " , title : " + tests.get(0));
    	  
    	  pst.setString(1, tests.get(0) + "_sujoy");    	
          pst.setString(2, tests.get(1));
          
    	  pst.addBatch();	
    	}
    	
    	pst.executeBatch();
    	
    	System.out.println("updating tests complete");
    	
    	//con.rollback();
    	
	  }catch(SQLException sqlex){
		//con.rollback();
		sqlex.printStackTrace();
      }catch(Exception ex){
    	//con.rollback();
		ex.printStackTrace();
      }
	  finally{
		if(rst != null){
		  rst.close();
		}
		if(pst != null){
    	  pst.close();
		}
    	/*
		if(con != null){
      	  con.close();
  		}
  		*/
      }
	}
	
	public static void getQuestionForTest(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;	    
		pst = con.prepareStatement("SELECT questionID, format, data FROM questions q WHERE q.bankid = (SELECT bankid FROM tests WHERE testid = ?)");
    	//pst.setString(1, "13062445364771500");    	
		pst.setString(1, "13077183473457400");
		
    	rs = pst.executeQuery();
    	DataInputStream input= null;
    	int count = 0;
    	System.out.println("Loading questions starts at : " + new Date());
        while (rs.next()) {
        	//format = rs.getInt("format");
        	input= new DataInputStream(rs.getBinaryStream("data"));
        	count ++;
        }
        System.out.println("Loading questions completes at : " + new Date());
        System.out.println("Total count : " + count);
        //question(input, format);
	}
	
	/*
	public void worksheet( DataInputStream theInput, int format )
	throws Exception{
	super( theInput, format );
	
	Vector answers= new Vector();

	try 
	{
		//explanation= theInput.readUTF();
		explanation= tp_sql.readString(theInput);
		
		int count= theInput.readInt();
		for (int i=0 ; i< count; i++)
		{
			worksheet_answer theAnswer= worksheet_answer.read(theInput, format);
			if (theAnswer != null) answers.addElement( theAnswer );
		}
		
		if (count != answers.size())
			System.out.println("count should be " + count + "; it actually is " + answers.size());
		
		// read additional subclasses here
	} 
	catch (IOException e) 
	{
		System.out.println("worksheet question reading error");
		e.printStackTrace();
		throw (new Exception( "IOException reading worksheet question" ) );
	}
  }
  */
	private static void question( DataInputStream theInput, int format )
	throws Exception {
		
	try {
		
		// read the booleans
		//usePopup= theInput.readBoolean();
		System.out.println("usePopup : " + theInput.readBoolean());
		
		if (format >= 8) {
			//questionIsHTML= theInput.readBoolean();
			System.out.println("questionIsHTML : " + theInput.readBoolean());
			//mathColumns= theInput.readBoolean();
			System.out.println("mathColumns : " + theInput.readBoolean());
			//indent= theInput.readBoolean();
			System.out.println("indent : " + theInput.readBoolean());
			//larger= theInput.readBoolean();
			System.out.println("larger : " + theInput.readBoolean());
			//smaller= theInput.readBoolean();
			System.out.println("smaller : " + theInput.readBoolean());
		}
		
		if (format >= 24){
			//autoWrap= theInput.readBoolean();
			System.out.println("autoWrap : " + theInput.readBoolean());
		}
		
		if (format >= 43) {
			//questionIsMathML= theInput.readBoolean();
			System.out.println("questionIsMathML : " + theInput.readBoolean());
			//questionIsTeX= theInput.readBoolean();
			System.out.println("questionIsTeX : " + theInput.readBoolean());
			//questionIsLaTeX= theInput.readBoolean();
			System.out.println("questionIsLaTeX : " + theInput.readBoolean());
		}
		
		// additional booleans go here
		if (format >= 434){
			//longQuestion= theInput.readBoolean();
			System.out.println("longQuestion : " + theInput.readBoolean());
		}
		
		
		// read the ints
		//int id= theInput.readInt();
		System.out.println("id : " + theInput.readInt());
		
		if (format >= 400){
			String sqlID= theInput.readUTF();
			System.out.println("sqlID : " + sqlID);
		}
		
		int storage= theInput.readInt();

		int maxPoints= theInput.readInt();
		if (format >= 43) {
			int boxWidth= theInput.readInt();
			int boxHeight= theInput.readInt();
		}

		// additional ints here
		
		
		// read the strings
		System.out.println("qtext : " + readString(theInput));
		
		//defaultAnswer= theInput.readUTF();
		System.out.println("defaultAnswer : " + theInput.readUTF());
		
		// formerly html was stored - now we use the string for v7select/classware titles
		//selectionTitle= theInput.readUTF();
		System.out.println("selectionTitle : " + theInput.readUTF());

		// additional strings here
		//if (format >= 414) source= theInput.readUTF();
		if (format >= 414)
		System.out.println("source : " + theInput.readUTF());
		
		//if (format >= 416) sourceInfo= theInput.readUTF();
		if (format >= 416)
		System.out.println("sourceInfo : " + theInput.readUTF());
		
		//if (format >= 423) referenceTag= theInput.readUTF();
		if (format >= 423)
		System.out.println("referenceTag : " + theInput.readUTF());
		
		//if (format >= 424) pageTag= theInput.readUTF();
		if (format >= 424)
		System.out.println("pageTag : " + theInput.readUTF());
		
		// read the questionMedia if any
		boolean hasMedia= theInput.readBoolean();
		System.out.println("hasMedia : " + hasMedia);
		//if (hasMedia)
			//questionMedia= new richMedia( theInput, format );
		
		
		// read choices
		int choiceCount= theInput.readInt();
		System.out.println("choiceCount : " + choiceCount);
		for (int i=0 ; i<choiceCount ; i++){
			//choices.addElement( theInput.readUTF() );
			System.out.println("choices " + (i+1) +" : " + theInput.readUTF());
		}
			
		
		// read points
		int pointsCount= theInput.readInt();
		System.out.println("pointsCount : " + pointsCount);
		for (int i=0 ; i<pointsCount ; i++){
			//points.addElement( theInput.readUTF() );
			System.out.println("pointsCount " + (i+1) +" : " + theInput.readUTF());
		}
			
		
		// read feedback
		int feedbackCount= theInput.readInt();
		System.out.println("feedbackCount : " + feedbackCount);
		for (int i=0 ; i<feedbackCount ; i++){
			//feedback.addElement( readString( theInput ) );
			System.out.println("feedback " + (i+1) +" : " + readString( theInput ));
		}
			
		
		// read the local randoms
		int localCount= theInput.readInt();
		System.out.println("localCount : " + localCount);
		for (int i=0 ; i<localCount ; i++){
			//localRandoms.addElement( new randomVariable( theInput, format ) );
			randomVariable(theInput, format );
		}		
		
		// read the correctAnswerFeedback Vector
		int answerFeedbackCount= theInput.readInt();
		System.out.println("answerFeedbackCount : " + answerFeedbackCount);
		for (int i=0 ; i<answerFeedbackCount ; i++){
			//correctAnswerFeedback.addElement( readString( theInput ) );
			System.out.println("correctAnswerFeedback " + (i+1) +" : " + readString( theInput ));
		}		
		
		// read additional subclasses here
		//calculatedVariables= new Vector();
		if (format >= 430)
		{
			int theCount= theInput.readInt();
			System.out.println("theCount 1 : " + theCount);
			for (int i=0 ; i<theCount ; i++){
				//calculatedVariables.addElement( new randomVariable( theInput, format ) );
				randomVariable(theInput, format );
			}
		}
	
		//hints= new Vector();
		if (format >= 437)
		{
			int theCount= theInput.readInt();
			System.out.println("theCount 2 : " + theCount);
			for (int i=0 ; i<theCount ; i++){
				//hints.addElement( tp_sql.readString( theInput ) );
				System.out.println("hints " + (i+1) +" : " + readString( theInput ));
			}
		}
	
		//contentLinks= new Vector();
		if (format >= 442)
		{
			int theCount= theInput.readInt();
			System.out.println("theCount 3 : " + theCount);
			for (int i=0 ; i<theCount ; i++)
			{
				Hashtable theLink= new Hashtable();
				//theLink.put(CONTENT_LINK_TITLE, tp_sql.readString( theInput ));
				System.out.println("CONTENT_LINK_TITLE : " + readString( theInput ));
				//theLink.put(CONTENT_LINK_URL, tp_sql.readString( theInput ));
				System.out.println("CONTENT_LINK_URL : " + readString( theInput ));
				//theLink.put(CONTENT_LINK_TYPE, tp_sql.readString( theInput ));
				String CONTENT_LINK_TYPE = readString( theInput );
				System.out.println("CONTENT_LINK_TYPE : " + CONTENT_LINK_TYPE);
				
				if (CONTENT_LINK_TYPE.equals("policy"))
				{
					//theLink.put(CONTENT_LINK_POLICY, tp_sql.readString( theInput ));
					System.out.println("CONTENT_LINK_POLICY : " + readString( theInput ));
					//theLink.put(CONTENT_LINK_HTML, "<!--none-->");
					
				}
				else{
					//theLink.put(CONTENT_LINK_HTML, tp_sql.readString( theInput ));
					System.out.println("CONTENT_LINK_HTML : " + readString( theInput ));
				}

				//contentLinks.addElement( theLink );
			}
		}
		
		//questionProperties= richProperties.newInstance("questionProperties");
		if (format >= 447){
			//questionProperties= new richProperties( tp_sql.readString(theInput) );
			System.out.println("questionProperties : " + readString( theInput ));
		}
		
	} 
	catch (IOException e)
	{
		throw (new Exception( "IOException reading question" ) );
	}
	
}

	
	
	public static void getTest(Connection con) throws SQLException, IOException{
	  PreparedStatement pst = null;
	  ResultSet rs = null;
	  int format = -1;
	  InputStream theData = null;
	  InputStream dbin= null;
	  try{
		pst = con.prepareStatement("select title from tests where testID = ?");
    	pst.setString(1, "13131007841842700");    	
    	
    	rs = pst.executeQuery();
    	
    	if(rs.next()){
    	  //dbin= rs.getBinaryStream("data");
    	  System.out.println(tp_utility.safeCDATA(rs.getString("title")));    	    
    	}
    	
    	/*
    	ByteArrayOutputStream bout= new ByteArrayOutputStream();
  		byte[] buffer = new byte[32768];
		for(;;) {	
			int size = dbin.read(buffer);
			if (size == -1) break;
			bout.write(buffer,0,size);
		}
		dbin.close();
		bout.flush();
		byte barray[]= bout.toByteArray();
		bout.close();
		*/
    	/*
    	if(dbin != null){
		  DataInputStream input= new DataInputStream(dbin);
		  format= input.readInt();
		  getTpGui(input, format);
        }
        */
	  }catch(SQLException sqle){
			sqle.printStackTrace();
			throw sqle;
		}
        //System.out.println("questionType : " + questionType);
	}
	
	public static void getTpGui(DataInputStream theInput, int format) throws IOException {
		boolean headerNeedsFormating= theInput.readBoolean();
		boolean instructionsNeedFormating= theInput.readBoolean();
		boolean footerNeedsFormating= theInput.readBoolean();
		
		boolean questionAtATime= theInput.readBoolean();
		boolean perQuestionFeedback= theInput.readBoolean();
		boolean submissionWarning= theInput.readBoolean();
		boolean branchingEnabled= theInput.readBoolean();
		boolean disableBack= theInput.readBoolean();
		
		if (format >= 11){
			boolean resumable= theInput.readBoolean();
		}
		if (format > 16) {
			if (format < 19) {
				boolean tmp= theInput.readBoolean();
				//tickleList= 0;
			}
		}
		
		boolean styleBold= theInput.readBoolean();
		boolean styleItalic= theInput.readBoolean();
		
		boolean rstyleBold;
		boolean rstyleItalic;
		if (format > 29) {
			rstyleBold= theInput.readBoolean();
			rstyleItalic= theInput.readBoolean();
		}
		else {
			rstyleBold= false;
			rstyleItalic= false;
		}
		
		if (format >= 34) {
			boolean astyleBold= theInput.readBoolean();
			boolean astyleItalic= theInput.readBoolean();

			boolean pstyleBold= theInput.readBoolean();
			boolean pstyleItalic= theInput.readBoolean();

			boolean fstyleBold= theInput.readBoolean();
			boolean fstyleItalic= theInput.readBoolean();
		}
		
		boolean hideEraseButton= theInput.readBoolean();
		boolean numberQuestions= theInput.readBoolean();
		
		boolean defaultIndent= theInput.readBoolean();
		boolean defaultUsePopup= theInput.readBoolean();
		boolean defaultMathColumns= theInput.readBoolean();
		
		if (format >= 14){
			boolean groupAtATime= theInput.readBoolean();
		}
		
		if (format >= 21){
			boolean anonymous= theInput.readBoolean();
		}
		
		if (format >= 22){
			boolean penalizeBack= theInput.readBoolean();
		}
		
		if (format >= 425){
			boolean suppressNameLine= theInput.readBoolean();
		}
		
		if (format >= 435){
			boolean showPoints= theInput.readBoolean();
		}

		if (format >= 436){
			boolean mcDistractorScrambling= theInput.readBoolean();
		}

		// additional booleans go here
		
		
		// read the ints
		int defaultType= theInput.readInt();
		int defaultGroup= theInput.readInt();
		int defaultMediaPosition= theInput.readInt();
		int defaultMediaHeight= theInput.readInt();
		int defaultMediaWidth= theInput.readInt();
		int defaultEssayHeight= theInput.readInt();
		int defaultEssayWidth= theInput.readInt();
		
		if (format > 30){
			int defaultStorage= theInput.readInt();
		}
		
		if (format > 18){
			int tickleList= theInput.readInt();
		}

		// additional ints here
		
		
		// read the strings
		String title= theInput.readUTF();
		String header= theInput.readUTF();
		String instructions= theInput.readUTF();
		String preparer= theInput.readUTF();
		String footer= theInput.readUTF();
		
		if (format >= 33) {
			String insidehead= theInput.readUTF();
			String bodystart= theInput.readUTF();
			String bodyend= theInput.readUTF();
		}

		String testBackground= theInput.readUTF();
		String testBackgroundGraphic= theInput.readUTF();
		String questionText= theInput.readUTF();
		System.out.println("questionText : " + questionText);
		
		String questionFont= theInput.readUTF();
		if (!questionFont.endsWith(",Arial Unicode MS")) questionFont += ",Arial Unicode MS";
		
		String questionSize= theInput.readUTF();
		
		String responseText;
		String responseFont;
		String responseSize;
		if (format < 30) {
			questionText= theInput.readUTF();
			
			responseText= questionText;
			responseFont= "Times,Times New Roman,Arial Unicode MS";
			responseSize= "3";
		}
		else {
			responseText= theInput.readUTF();
			
			responseFont= theInput.readUTF();
			if (!responseFont.endsWith(",Arial Unicode MS")) responseFont += ",Arial Unicode MS";
		
			responseSize= theInput.readUTF();
		}
		System.out.println("responseText : " + responseText);
		System.out.println("responseSize : " + responseSize);
		
		if (format >= 34) {
			String answerText= theInput.readUTF();
			String answerFont= theInput.readUTF();
			String answerSize= theInput.readUTF();

			String pointText= theInput.readUTF();
			String pointFont= theInput.readUTF();
			String pointSize= theInput.readUTF();

			String feedbackText= theInput.readUTF();
			String feedbackFont= theInput.readUTF();
			String feedbackSize= theInput.readUTF();
			
			System.out.println("answerText : " + answerText);
			System.out.println("pointText : " + pointText);
			System.out.println("feedbackText : " + feedbackText);
		}

		if (format >= 35) {
			String oemName= theInput.readUTF();
			String oemHeader= theInput.readUTF();
			String oemFooter= theInput.readUTF();
		}
		
		if (format >= 36) {
			String visitedLinks= theInput.readUTF();
			
			//unvisitedLinks= theInput.readUTF();
			
			// replaced unvisitedLinks with properties xml
			String newProps= readString(theInput);
			//System.out.println("newProps: " + newProps);
			//if (newProps.indexOf("<?xml") >= 0) testProperties= new richProperties( newProps );					
		}
		
		// additional strings here
		if (format >= 415) {
			String bankTitle= theInput.readUTF();
		}

		if (format >= 417) {
			String titleSize= theInput.readUTF();
		}

		// read additional subclasses here
		
		//customTypeLabels= defaultCustomLabels();
		if (format >= 418) {
			boolean showCustomTypeLabels= theInput.readBoolean();
			//readStringHashtable( theInput, customTypeLabels );
		}
		
		if (format >= 420) {
			boolean terseKey= theInput.readBoolean();
		}
		if (format >= 421) {
			boolean noKey= theInput.readBoolean();
		}
			
		if (format >= 437) {
			boolean showHints= theInput.readBoolean();
		}
		if (format >= 438){
			boolean allowPregrade= theInput.readBoolean();
		}
		if (format >= 439){
			boolean showGroupSummary= theInput.readBoolean();
		}
		
		if (format >= 441){
			String customReturnURL= theInput.readUTF();
		}
		
		if (format >= 443)
		{
			boolean external_ebook= theInput.readBoolean();
			boolean show_categories= theInput.readBoolean();
			boolean show_pages= theInput.readBoolean();
			boolean show_titles= theInput.readBoolean();
		}
		
		if (format >= 444)
		{
			String MC_delimiter= theInput.readUTF();
			boolean MC_delimiterForceCaps= theInput.readBoolean();
			boolean MC_delimiterForceBold= theInput.readBoolean();
		}

		if (format >= 445){
			boolean ESSA_suppressLines= theInput.readBoolean();
		}

		if (format >= 446){
			String ESSA_lineHeight= theInput.readUTF();
		}
		
		if (format >= 448)
		{
			boolean  hm_allowPrint= theInput.readBoolean();
			boolean  hm_allowPreview= theInput.readBoolean();
		}
        
        //System.out.println("questionType : " + questionType);
	}
	
	public static void getPartials(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    ByteArrayOutputStream theBytes= new ByteArrayOutputStream();
		pst = con.prepareStatement("SELECT * FROM partials WHERE testID = ? and userID = ? ORDER BY block");
    	pst.setString(1, "13252698000010545");
		pst.setString(2, "13637_58374573_2_32293905");
    	ArrayList<String> nameList = new ArrayList<String>();
    	ArrayList<String> valueList = new ArrayList<String>();
    	rs = pst.executeQuery();
    	
	    if (rs.next()) {
        	long dataSize= rs.getLong("datasize");
			
			InputStream theData= rs.getBinaryStream("params");
			InputStream outData = readDataFromStream(theData);
			
			byte[] barray= new byte[64000];
			int readCount= (int)dataSize;
			if (readCount > 64000) readCount= 64000;
			
			int charsread= 0;
			try
			{
				charsread= theData.read(barray, 0, 64000);
			}
			catch (IOException ignore) {}
			if (charsread > 0)
				theBytes.write(barray, 0, charsread);        	
        }
        
        DataInputStream theInput= new DataInputStream(new ByteArrayInputStream( theBytes.toByteArray() ));
        
        int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++){
			//System.out.println("names (" + (i+1) + ") : "+ theInput.readUTF());
			nameList.add(theInput.readUTF());
		}
			
		// read the values
		int valueCount= theInput.readInt();
		String result = null;
		for (int i=0 ; i<valueCount ; i++){
			result = readString(theInput);
			//System.out.println("values (" + (i+1) + ") : "+ result);
			valueList.add(result);
			/*
			if((i+1) == 122){
				System.out.println("values (" + (i+1) + ") in UTF-8 : " + new String(result.getBytes(), "UTF-8"));
			}
			
			if((i+1) == 131){
				System.out.println("values (" + (i+1) + ") in ISO-8859-1 : " + new String(result.getBytes(), "ISO-8859-1"));
			}
			*/
		}
		
		for(int i=0; i<nameList.size(); i++){
		  System.out.println(nameList.get(i) + " : " + valueList.get(i));
		}
	}
	
	public static void getGroupMembers(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    long start = new java.util.Date().getTime();
	    pst = con.prepareStatement("SELECT groupID, name, enabled, randomize, randomCount, summarize from groups WHERE testID = ? ORDER BY orderID");
    	pst.setString(1, "13086142155718831");
		
    	rs= pst.executeQuery();
		if (rs.next()) {
			do {
				questionGroup(con, rs);
			} while (rs.next());
		}
		long end = new java.util.Date().getTime();
		System.out.println(" Time take getGroupMembers " + (end - start) + "ms");
	}
	
	public static void questionGroup( Connection con, ResultSet rs)
	throws SQLException
{
	PreparedStatement pstmt= null;
	ResultSet qs= null;
	
	try {
		//idlist= new VectorAdapter();
		
		//long groupID= rs.getLong("groupID");
		//name= rs.getString("name");
		
		/*
		if (rs.getInt("enabled") == 1) enabled= true;
		
		if (rs.getInt("randomize") == 1) 
		{
			randomSelection= true;
			randomCount= rs.getInt("randomCount");
		}
		if (rs.getInt("summarize") == 1) summarize= true;
		*/
		pstmt= con.prepareStatement("SELECT questionID from groupmembers WHERE groupID = ? ORDER BY orderID");
		pstmt.setLong(1, rs.getLong("groupID"));
		
		qs= pstmt.executeQuery();
		while (qs.next()){
			//idlist.addElement( qs.getString("questionID") );
			qs.getString("questionID");
		}
		
		//tp_sql.releaseResources(pstmt,qs);
	} finally {
		if(qs != null){
			qs.close();
	    	}
			if(pstmt != null){
				pstmt.close();
			}		
	}
}
	
	public static void getSubmissions(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    ByteArrayOutputStream theBytes= new ByteArrayOutputStream();
	    InputStream theData= null;
		pst = con.prepareStatement("select * from submissions where testID = ? and submissionID = ?");
    	pst.setString(1, "13020022381575800");
    	pst.setString(2, "13020116037161500");
    	
    	rs = pst.executeQuery();
    	
	    if (rs.next()) {
        	theData= rs.getBinaryStream("params");
		}
        
        DataInputStream theInput= new DataInputStream(theData);
        
        int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++){
			System.out.println("names (" + (i+1) + ") : "+ theInput.readUTF());
		}
			
		// read the values
		int valueCount= theInput.readInt();
		for (int i=0 ; i<valueCount ; i++){
			System.out.println("values (" + (i+1) + ") : "+ readString(theInput));
		}
	}
	
	public static void getLicence(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    ByteArrayOutputStream theBytes= new ByteArrayOutputStream();
	    InputStream theData= null;
		pst = con.prepareStatement("select * from licenses");
    	rs = pst.executeQuery();
    	String thisType = null;
    	HashMap thisData = null;
    	
	    while (rs.next()) {
	    	thisData = hashFromStream(rs.getBinaryStream("data"));
	    	System.out.println("thisType : " + rs.getString("licenseType"));
	    	System.out.println("info : " + (String)thisData.get("info"));
	    	//thisType= rs.getString("licenseType");
        	//theData= rs.getBinaryStream("data");
		}
	    //HashMap thisData = hashFromStream(theData);
        
        //System.out.println("values : " + (String)thisData.get("info"));
		
	}
	
	public static void getResponses(Connection con) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    pst = con.prepareStatement("select * from responses where testID = ? and submissionID = ?");
    	pst.setString(1, "13020022381575800");
    	pst.setString(2, "13020116037161500");
    	
    	rs = pst.executeQuery();
    	
    	while(rs.next()){
    	System.out.println("\nquestionID : " + rs.getString("questionID"));
	    System.out.println("followupValue : " + stringFromStream(rs.getBinaryStream("followup")));
		//theResp.hasFollowup= (theResp.followupValue.length() > 0);
		
	    System.out.println("html : " + stringFromStream(rs.getBinaryStream("html")));
	    
	    System.out.println("recordedValue : ");
		vectorFromStream(rs.getBinaryStream("recordedValue"));
		
		System.out.println("response : ");
		vectorFromStream(rs.getBinaryStream("response"));
		
		System.out.println("feedback : ");
		vectorFromStream(rs.getBinaryStream("feedback"));
		
		System.out.println("comment : " + stringFromStream(rs.getBinaryStream("instructorComment")));
    	}
	}
	
	public static String stringFromStream( InputStream bStream )
	{
		String result= "";
		
		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				result= readString( input );
				bStream.close();
			} 
			catch (IOException i) 
			{
				System.out.println("IOException in tp_sql.stringFromStream()");
				//i.printStackTrace();
			}
		}
		return( result );
	}
	
	public static void vectorFromStream( InputStream bStream )
	{
		try 
		{
			DataInputStream input= new DataInputStream(bStream);
			
			int count= input.readInt();
			for (int i=0 ; i<count ; i++)
				System.out.println(readString( input ));
		}
		catch (IOException i) 
		{
			System.out.println("IOException in tp_sql.vectorFromStream()");
		}
	}
	
	private static void hashFromBlob(ResultSet rs, String columnName) throws SQLException{
		HashMap indexMap = hashFromStream(rs.getBinaryStream(columnName));
		Iterator itr = indexMap.keySet().iterator();
		while(itr.hasNext()){
			String key = (String)itr.next();
			System.out.println("key : " + key);
			System.out.println("Value : " + (String)indexMap.get(key));
		}
	}
	
	private static String substitute(
			String masterString,
			String lookupString,
			String replacementString
		) {
			String		resultString,
						tmp;
			
			int			start;
			

			if (masterString == null) return null;
			
			resultString= masterString;
			if (replacementString.indexOf(lookupString) >= 0) return resultString;
			
			start= resultString.indexOf( lookupString );
			while (start >= 0) {
				
				if (start==0) tmp= "";
				else tmp= resultString.substring(0,start);
				
				tmp += replacementString;
				if ( (start+lookupString.length()) < resultString.length() )
					tmp += resultString.substring( start+lookupString.length() );
				
				resultString= tmp;
				start= resultString.indexOf( lookupString );
			}
			
			return( resultString );
		}
	
	public static void randomVariable( DataInputStream theInput, int format )
	 {

	try {
		
		// read the doubles
		//startValue= theInput.readDouble();
		System.out.println("startValue : " + theInput.readDouble());
		//endValue= theInput.readDouble();
		System.out.println("endValue : " + theInput.readDouble());
		//incrementValue= theInput.readDouble();
		System.out.println("incrementValue : " + theInput.readDouble());
		//incrementAmount= theInput.readDouble();
		System.out.println("incrementAmount : " + theInput.readDouble());

		// additional doubles here
		
		
		// read the strings
		//name= theInput.readUTF();
		System.out.println("name : " + theInput.readUTF());

		// additional strings here
		
		
		if (format >= 26) {
			boolean usePool= theInput.readBoolean();
			System.out.println("usePool : " + usePool);
			if (usePool) {
				int count= theInput.readInt();
				//pool= new Vector();
				for (int i=0 ; i<count ; i++)
				{
					//String inValue= theInput.readUTF();
					System.out.println("inValue : " + theInput.readUTF());
					/*
					if (inValue.indexOf(",") >= 0)
					{
						StringTokenizer theTokens= new StringTokenizer(inValue, ",");
						Vector thisArray= new Vector();
						while (theTokens.hasMoreTokens())
							thisArray.addElement( rv_recall(theTokens.nextToken().trim()) );
						pool.addElement(thisArray);
					}
					else
						pool.addElement( rv_recall(inValue) );
					*/
				}
			}
		}
		
		if (format >= 426){
			//arrayed= theInput.readBoolean();
			System.out.println("arrayed : " + theInput.readBoolean());
		}
		
		//dependencies= new Vector();
		if (format >= 429)
		{
			int count= theInput.readInt();
			for (int i=0 ; i<count ; i++)
				//dependencies.addElement(theInput.readUTF());
				System.out.println("dependencies : " + theInput.readUTF());
		}
		
		// read additional subclasses here
		if (format >= 430)
		{
			boolean calculated= theInput.readBoolean();
			System.out.println("calculated : " + calculated);
			if (calculated){
				//calculation= theInput.readUTF();
				System.out.println("calculation : " + theInput.readUTF());
			}				
		}
	
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}
	
}
	
	public static void getPartial(Connection con, String sqlID, String userID) 
	{
		parameters savedParams = null;

		boolean debugging = true;
		if (debugging) System.out.println("getPartial(2)");
		
	//	Connection con= null;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		PreparedStatement pstmt = null;
		try 
		{
			//con = theHandler.getConnection();
			stmt= con.prepareStatement("SELECT * FROM partials WHERE testID=? AND userID=? ORDER BY block");
			stmt.setString(1, sqlID);
			stmt.setString(2, userID);
			rs= stmt.executeQuery();

			ByteArrayOutputStream theBytes = new ByteArrayOutputStream();

			int lastBlock = -1;
			boolean multiBlock= false;
			while (rs.next()) 
			{
				int block = rs.getInt("block");
				long dataSize = rs.getLong("datasize");

				if (debugging) 
				{
					System.out.println("  read partial block " + block + " with size " + Long.toString(dataSize) + " and timestamp " + Long.toString(rs.getLong("timestamp"))
							+ " for " + userID + " in " + sqlID);
				}

				if (block == lastBlock) 
				{
					if (debugging)
						System.out.println("  skipping");
					continue; // skip dups
				}
				lastBlock = block;

				if (block > 0) multiBlock= true;

				InputStream theData = rs.getBinaryStream("params");

				byte[] barray = new byte[64000];
				int readCount = (int) dataSize;
				if (readCount > 64000)
					readCount = 64000;

				int charsread = 0;
				try 
				{
					charsread = theData.read(barray, 0, 64000);
				} 
				catch (IOException ignore) {}
				
				if (charsread > 0)
					theBytes.write(barray, 0, charsread);
			}

			if (theBytes.size() == 0)
			{
				if (debugging)
					System.out.println("  null data");
				stmt.close();
				
			}
			
			System.out.println("");
			savedParams = parametersFromStream(new ByteArrayInputStream(theBytes.toByteArray()));
			
			byte barray[] = parametersToArray(savedParams);
			System.out.println("barray.length : " + barray.length);
			boolean needsMultiblockSave= (barray.length > 64000);
			System.out.println("needsMultiblockSave : " + needsMultiblockSave);
			if (savedParams.size() == 0) 
			{
				
				System.out.println("classware_hm.getPartial #2 (testID, userID) (" + sqlID + ", " + userID
						+ " Exception: failed in loading partial, starting attempt over");
				//return (null);
			}

			
		} 
		catch (SQLException s) 
		{
			System.out.println("classware_hm.getPartial(2)"+ s);
			s.printStackTrace();
		}
		finally
		{
			
		}

		// rebuild missing qlist - QCS#640
	}
	
	public static parameters parametersFromStream( InputStream bStream )
	{
		parameters result= new parameters();
		
		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				result= new parameters( input );
			} 
			catch (IOException i) 
			{
				System.out.println("IOException in tp_sql.parametersFromStream()");
			}
		}
		return( result );
	}
	
	public static byte[] parametersToArray( parameters theParams )
	{
		byte result[]= null;
		
		try 
		{
			ByteArrayOutputStream bStream= new ByteArrayOutputStream();
			DataOutputStream out= new DataOutputStream( bStream );
			
			theParams.write( out );
			out.flush();
			result= bStream.toByteArray();
			
			out.close();
			bStream.close();
		} 
		catch (IOException i) 
		{
			System.out.println("IOException in tp_sql.parametersToArray()");
		}
		return(result);
	}
	
}
