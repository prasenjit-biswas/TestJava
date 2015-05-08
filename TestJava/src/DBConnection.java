import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
	public static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	public static final String MySqldriver   = "com.mysql.jdbc.Driver";
	/* DEV **/
	
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztodev";
	
	public static final String mySqlJDBCURL = "jdbc:mysql://nvldb06.eppg.com/ezto_dev";
	public static final String mySqlusername = "ezto_dev";
	public static final String mySqlpassword = "pixie";
	
	/*
	public static  String Oracleurl      = "";
	public static  String Oracleusername = "";
	public static  String Oraclepassword = "";
	
	public static  String mySqlJDBCURL = "";
	public static  String mySqlusername = "";
	public static  String mySqlpassword = "";
	
	public DBConnection(){
		Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
		Oracleusername = "ezto";
		Oraclepassword = "eztodev";
		
		mySqlJDBCURL = "jdbc:mysql://nvldb06.eppg.com/ezto_dev";
		mySqlusername = "ezto_dev";
		mySqlpassword = "pixie";
	}
	
	static{
		Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/env.properties"));
	}
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
	
	public static void releaseResources(Connection con, PreparedStatement pst, ResultSet rs){
	  try{
		if(rs != null){
    	  rs.close();
    	}
		if(pst != null){
    	  pst.close();
		}
		if(con != null){
		  con.close();
		}
	  }catch(SQLException sqle){
		  sqle.printStackTrace();
	  }
	}
}	