package Dataretrieval.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection extends DBConnection{
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
    Class.forName(DBConnection.MySqldriver);
    return DriverManager.getConnection(DBConnection.mySqlJDBCURL,DBConnection.mySqlusername,DBConnection.mySqlpassword);
  }
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