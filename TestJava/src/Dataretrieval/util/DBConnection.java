package Dataretrieval.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection{
	public static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	public static final String MySqldriver  = "com.mysql.jdbc.Driver";
	/* DEV **/
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztodev";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://nvldb06.eppg.com/ezto_dev";
	public static final String mySqlusername = "ezto_dev";
	public static final String mySqlpassword = "pixie";
	
	public static final String Sparkurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = eqxoracle1.eppg.com)(PORT = 1522))(CONNECT_DATA = (SERVICE_NAME = SPRKQA)))";
	public static final String Sparkusername = "sparkqastaging";
	public static final String Sparkpassword = "sparkqastaging";
	*/
	
	/* PROD CLOUD **/
	/*
	/** Cloud Production Database **/

	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SID = eztoprod_va1)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "EZTOPRODDP";*/
	//DEMO
	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SID = eztodemo_va1)))";
	public static final String Oracleusername = "EZTO";
	public static final String Oraclepassword = "EZTODPDEMO";*/
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztodev";
	*/

	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SID = eztoqalv1)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztoqalv";*/
	
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "EZTODPDEV";
	
	//DEV2
	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = 10.221.0.122)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztstag2)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztoqalv";*/
	
	//DEV3
	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = 10.221.0.122)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztstag3)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztoqalv";*/
	
	//QASTG
	/*public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztostag)))";
	public static final String Oracleusername = "EZTO";
	public static final String Oraclepassword = "EZTODPSTAG";*/
	
	public static final String OracleProdDRurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod_nj)))";
	public static final String OracleProdDRusername = "ezto";
	public static final String OracleProdDRpassword = "EZTOPRODDP";
	
	
	
	public static final String mySqlJDBCURL = "";
	public static final String mySqlusername = "";
	public static final String mySqlpassword = "";
	
	public static final String Sparkurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = eqxoracle1.eppg.com)(PORT = 1522))(CONNECT_DATA = (SERVICE_NAME = SPRKQA)))";
	public static final String Sparkusername = "sparkqastaging";
	public static final String Sparkpassword = "sparkqastaging";
	
	
	
	
	
	/* PROD **/
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb01-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb02-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb03-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb04-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb05-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod)) )";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztoprod";
	
	
	public static final String mySqlJDBCURL = "jdbc:mysql://ewnvldb13.eppg.com/aris_spark";
	public static final String mySqlusername = "aris_spark";
	public static final String mySqlpassword = "pixie";
	*/
	
	/** Load database */
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoload)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztoload";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://ewnvldb15.eppg.com/aris_spark_pqa";
	public static final String mySqlusername = "aris_spark_pqa";
	public static final String mySqlpassword = "aris_spark_pqa";
	*/
	
	/** Cloud PQA database */
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SID = eztopqa_va1)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztopqa";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://ewnvldb15.eppg.com/aris_spark_pqa";
	public static final String mySqlusername = "aris_spark_pqa";
	public static final String mySqlpassword = "aris_spark_pqa";
	*/
	
	/** QAStage Database **/
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqa)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "ezto123";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://nvldb09.eppg.com/ezto_qa1";
	public static final String mySqlusername = "ezto_qa1";
	public static final String mySqlpassword = "pixie";
	*/
	
	/** QALive Database **/
	/*
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	public static final String Oracleusername = "eztoapp";
	public static final String Oraclepassword = "eztoapp123";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://nvldb06.eppg.com/ezto_qa2";
	public static final String mySqlusername = "ezto_qa2";
	public static final String mySqlpassword = "pixie";
	*/
	
	/** Prep database */
	/*
	public static final String Oracleurl      = "";
	public static final String Oracleusername = "";
	public static final String Oraclepassword = "";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://nvldb09.eppg.com/demo_prep";
	public static final String mySqlusername = "demo_prep";
	public static final String mySqlpassword = "pixie";
	*/
	
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
	
	public static void releaseResources(Connection con, PreparedStatement pst, ResultSet rs){
		releaseResources(rs);
		releaseResources(pst);
		releaseResources(con);
	}
	
	public static void releaseResources(PreparedStatement pst, ResultSet rs){
		releaseResources(rs);
		releaseResources(pst);
	}
}	