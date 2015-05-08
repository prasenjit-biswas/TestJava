package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDBUtil {
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	
	/** EZTO DEMO*/
	
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb01-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb02-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb03-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = sprkdemo)))";
	static String orauser = "sparkdemo";
	static String orapass = "sparky";
	
	static String mysqlurl = "jdbc:mysql://nvldb09.eppg.com:3306/";
	static String mysqldb = "demo_prep";
	static String mysqluser = "demo_prep";
	static String mysqlpass = "pixie";
	
	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB;
	
	public static void main(String argv[]) throws Exception{
    
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;       
    
    try {
    	con = getConnection();
    	
    	//pst = con.prepareStatement("select testid from tests where testid = ''");	    
    	pst = con.prepareStatement("select activity_id from activity where activity_id = 195672943");
	    rs = pst.executeQuery();
		
	    if (rs.next())
		{
			//System.out.println(" testid : " + rs.getString("testid"));
	    	System.out.println(" activity_id : " + rs.getString("activity_id"));
		}
        
    } catch(SQLException exSQLException){
    	System.out.print(exSQLException);
	    exSQLException.printStackTrace();
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
  }
