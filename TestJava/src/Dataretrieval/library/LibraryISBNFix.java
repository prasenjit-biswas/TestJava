package Dataretrieval.library;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;



public class LibraryISBNFix {
	
	static String mysqldriver = "com.mysql.jdbc.Driver";
	
	
	static final String oraurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	static final String oradriver   = "oracle.jdbc.driver.OracleDriver";
	static final String orauser     = "eztoapp";
	static final String orapass     = "eztoapp123";
	
	
	/** My SQL Dev */
	/*
	static String mysqlurl = "jdbc:mysql://nvldb06.eppg.com:3306/";
	static String mysqldb = "ezto_dev";
	static String mysqluser = "ezto_dev";
	static String mysqlpass = "pixie";
	*/
	//static String siteid    = "muon:";
	
	/** My SQL Demo */
	
	static String mysqlurl = "jdbc:mysql://nvldb09.eppg.com:3306/";
	static String mysqldb = "demo_prep";
	static String mysqluser = "demo_prep";
	static String mysqlpass = "pixie";
	
	//static String siteid    = "muon:";
	
	/*
	static final String  oraurl        = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb01-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb02-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb03-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb04-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb05-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod)) )";
	static final String oradriver      = "oracle.jdbc.driver.OracleDriver";
    static final String orauser        = "ezto";
	static final String orapass        = "eztoprod";
	*/
	
		
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztoload";
    */
	
	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = MYSQL_DB;

	
	public static void main(String argv[]) throws Exception{
    
		Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;       
	    
	    try {
	    	con = getConnection();
	    	correctISBN(con);
	    } catch(SQLException exSQLException){
		    exSQLException.printStackTrace();
		} catch(Exception exException){
		    exException.printStackTrace();
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
		  return con;
	}	
	
	
	static String	LONG_STRING		= "***UTF String Too Long for writeUTF***";
	
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
	
	public static ConcurrentHashMap hashFromStream( InputStream bStream )
	{
		ConcurrentHashMap result= new ConcurrentHashMap();		
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
				//System.out.println("IOException in tp_sql.hashFromStream()");
				//i.printStackTrace();
			}
		}		
		return( result );
	}
	
	
	
	
	private static void correctISBN(Connection con) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map mediaMap = null;
		ConcurrentHashMap librarydata = null;
		String finalPath = "";
		String entryType = "";
		String isbn = "";
		String entryid = "";
		boolean gotOneEntry = false;
		HashMap<String,String> isbnMap = new HashMap<String,String>();
		PreparedStatement psUpdate = con.prepareStatement("UPDATE library SET titleid = ? WHERE entryid = ?");
		try{
		  ps = con.prepareStatement(" select entryid, entryType, titleid, parentid, data from library l where l.entrytype = 1 and length(l.titleid) > 13 ");
		  rs = ps.executeQuery();
		  while(rs.next()){
			String URL = rs.getString("titleid");
			String parentid =  rs.getString("parentid");
			librarydata =  hashFromStream(rs.getBinaryStream("data"));
			entryType = rs.getString("entryType");
			isbn = (String)librarydata.get("isbn");
			entryid =  rs.getString("entryid");
			isbnMap.put(entryid, isbn);
			System.out.println(" ISBN " + librarydata.get("isbn")  + " entryid " + rs.getString("entryid"));			
		  }
		  
		  Iterator it = isbnMap.entrySet().iterator();
		  
		  
		  while(it.hasNext()){
		    Map.Entry<String,String> pairs = (Entry<String, String>) it.next();  
			System.out.println(" Key " + pairs.getKey() + " Value " + pairs.getValue()); 
			psUpdate.setString(1, pairs.getValue());
			psUpdate.setString(2, pairs.getKey());
			psUpdate.addBatch();
			gotOneEntry = true;
			
		  }
		  
		  if(gotOneEntry)
		  {
		    psUpdate.executeBatch();		    
		  }
		   
		  
		}
		catch(Exception ex){		  
		  ex.printStackTrace(); 	
		}
		finally{
		   	if(rs != null){
		      rs.close();
		    }
			if(ps != null){
		      ps.close();
			}			
		}
	 }	 

}


