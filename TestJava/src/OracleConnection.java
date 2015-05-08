import java.sql.Connection;
import java.sql.DriverManager;

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
    //Class.forName(DBConnection.Oracledriver);
    //return DriverManager.getConnection(DBConnection.Oracleurl,DBConnection.Oracleusername,DBConnection.Oraclepassword);
	  System.out.println(" DBConnection.Oracleurl : " + DBConnection.Oracleurl);
	  System.out.println(" DBConnection.Oracleusername : " + DBConnection.Oracleusername);
	  System.out.println(" DBConnection.Oraclepassword : " + DBConnection.Oraclepassword);
	  return null;
  }
  
  public static void main(String args[]) {
	try{  
      Connection con = null;
      OracleConnection oracleConnection = new OracleConnection();
      con = oracleConnection.getConnection();
      System.out.println(" Sucessful Connection ");
	}
	catch(Exception ex){
	  ex.printStackTrace();	
	}
  }
}