package Dataretrieval.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class OracleConnection extends DBConnection{
  /** Classware DB */
  /*
  public static Connection getConnection() throws Exception {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@eqxoracle1.eppg.com:1522:clssdev";
    String username = "classwarestage";
    String password = "classwarestage";
    Class.forName(driver);
    return DriverManager.getConnection(url, username, password);
  }
  */
	
   /** EZTO Dev DB */
  	
  public static Connection getConnection() throws Exception {
    Class.forName(DBConnection.Oracledriver);
    return DriverManager.getConnection(DBConnection.Oracleurl,DBConnection.Oracleusername,DBConnection.Oraclepassword);
    
	  /*Properties h = new Properties();
	  h.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
	  h.put(Context.PROVIDER_URL,"t3://nj09mhe5480-mgt.edmz.mcgraw-hill.com:22101");
	  h.put(Context.SECURITY_PRINCIPAL, "eztdev_admin");
	  h.put(Context.SECURITY_CREDENTIALS, "eztdev2011");
	  Context initContext = new InitialContext(h);
	  // initCtx = new javax.naming.InitialContext();
	  javax.sql.DataSource datasource = null;
	  datasource = (javax.sql.DataSource) initContext.lookup("jdbc/EZTESTDS");
	  return datasource.getConnection();*/
  }
  
  
  public static Connection getProdDRConnection() throws Exception {
    Class.forName(DBConnection.Oracledriver);
    return DriverManager.getConnection(DBConnection.OracleProdDRurl,DBConnection.OracleProdDRusername,DBConnection.OracleProdDRpassword);
  }
  
  /*public static Connection getSparkConnection() throws Exception {
	    Class.forName(DBConnection.Oracledriver);
	    return DriverManager.getConnection(DBConnection.Sparkurl,DBConnection.Sparkusername,DBConnection.Sparkpassword);
	  }*/
  /*
  public static void main(String args[]) {
	try{  
      OracleConnection oracleConnection = new OracleConnection();
      System.out.println(" Sucessful Connection ");
	}
	catch(Exception ex){
	  ex.printStackTrace();	
	}
  }
  */
}