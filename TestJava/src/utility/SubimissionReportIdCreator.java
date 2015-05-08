package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import Dataretrieval.util.OracleConnection;

public class SubimissionReportIdCreator {

	
	public static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "EZTODPDEV";
	
	public static final String OracleProdDRurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = localhost)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod_nj)))";
	public static final String OracleProdDRusername = "ezto";
	public static final String OracleProdDRpassword = "EZTOPRODDP";
	
	
	
	
	static String SELECT_TEST_QUERY = "SELECT testid, newTest where modified > ? AND modified <= ?";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	SubimissionReportIdCreator(long startTime, long endTime) throws Exception{
		try{
			con = getDevConnection();
			pstmt = con.prepareStatement(SELECT_TEST_QUERY);	
			pstmt.setLong(1, startTime);
			pstmt.setLong(2, endTime);
			rs = pstmt.executeQuery();
		}catch(Exception ex){
			System.out.println("error in SubimissionReportIdCreator constructor "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public synchronized Map<String, String> getTestMap() throws Exception{
		Map<String, String> testMap = new HashMap<String, String>();
		if (!rs.next()) {
			notifyAll(); 
			return null;
		}else {
				try {
					String tid = rs.getString("testid");
					String newTest = rs.getString("newTest");
					if (tid != null && !("").equals(tid)) {
						testMap.put(tid, newTest);
					}
				} catch (Exception ex) {
					System.out.println("Exception while getting questionid "+ ex.getMessage());
					ex.printStackTrace();
					throw ex;
				}
				return testMap;
		}
	} 
	
	
	 public static Connection getDevConnection() throws Exception {
		    Class.forName(Oracledriver);
		    return DriverManager.getConnection(Oracleurl,Oracleusername,Oraclepassword);
	 }
	
	 public static Connection getProdDRConnection() throws Exception {
		    Class.forName(Oracledriver);
		    return DriverManager.getConnection(OracleProdDRurl,OracleProdDRusername,OracleProdDRpassword);
	 }
}
