import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection extends DBConnection{

  public static Connection getConnection(){
	Connection con = null;  
    try {
      Class.forName(DBConnection.MySqldriver).newInstance();
      //con = DriverManager.getConnection("jdbc:mysql://nvldb06.eppg.com/ezto_dev","ezto_dev", "pixie");
      con = DriverManager.getConnection(DBConnection.mySqlJDBCURL, DBConnection.mySqlusername, DBConnection.mySqlpassword);
      /*
      if(!con.isClosed())
       System.out.println("Successfully connected to MySQL server using TCP/IP...");
      */      
   }
   catch(Exception e) {
      System.err.println("Exception: " + e.getMessage());
   }
   return con;  
  }
 	
  public static void main(String args[]) {
    Connection con = null;
    MySqlConnection mysqlConnection = new MySqlConnection();
    con = mysqlConnection.getConnection();    
    System.out.println("Successful Connection");
  }
}