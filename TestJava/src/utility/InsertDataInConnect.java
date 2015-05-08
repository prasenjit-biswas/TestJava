package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class InsertDataInConnect {
	
	public static final String Oracledriver = "oracle.jdbc.driver.OracleDriver";
	public static final String OracleSparkDevurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = 172.16.138.64)(PORT = 1522)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = SPRKQA)))";;
	public static final String OracleSparkDevUsername = "sparkqastaging";
	public static final String OracleSparkDevPassword = "sparkqastaging";
	
	public static final String GET_CONNECTEZTQALIVE_DATA = "SELECT key,value FROM connecteztqalive_properties";
	public static final String UPDATE_APP_PROPERTIES_TABLE = "UPDATE app_properties SET connecteztqalive = ? WHERE key = ?";
	
	
	public static void main(String[] args) {
		Connection con = null;
		try{
			con = getConnection();
			Map<String, String> connectEZTMap = getValuesFromConnectEZT(con);
			System.out.println("connectEZTMap size() : "+connectEZTMap.size());
			updateAppProperties(con, connectEZTMap);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(con != null){
					con.close();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	public static Map<String, String> getValuesFromConnectEZT(Connection con) throws Exception{
		Map<String, String> connectEZTQaliveMap = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = con.prepareStatement(GET_CONNECTEZTQALIVE_DATA);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String key = rs.getString("key");
				String value = rs.getString("value");
				if(key != null && !("").equals(key)){
					connectEZTQaliveMap.put(key, value);
				}
			}
		}catch(Exception ex){
			System.out.println(" Exception occured when trying to get data from CONNECTEZTQALIVE Table.");
			ex.printStackTrace();
			throw ex;
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(rs != null){
					rs.close();
				}
			}catch(Exception ex){
				System.out.println(" Exception occured when trying to close resources.");
			}
		}
		return connectEZTQaliveMap;
	}
	
	
	public static void updateAppProperties(Connection con, Map<String, String>connectEZTMap) throws Exception{
		PreparedStatement pstmt = null;
		try{
			con.setAutoCommit(false);
			int count = 0;
			if(connectEZTMap != null && !connectEZTMap.isEmpty()){
				pstmt = con.prepareStatement(UPDATE_APP_PROPERTIES_TABLE);
				
				Iterator<String> keyItr = connectEZTMap.keySet().iterator();
				while(keyItr.hasNext()){
					String key = keyItr.next();
					String value = connectEZTMap.get(key);
					if(value != null){
						System.out.println(" updating key : "+key+" with value : "+value);
						pstmt.setString(1, value);
						pstmt.setString(2, key);
						pstmt.addBatch();
						count ++;
					}
				}
			}
			
			if(count > 0){
				System.out.println(" Batch Count : "+count);
				pstmt.executeBatch();
			}
			
		}catch(Exception ex){
			System.out.println(" Exception occured updateAppProperties");
			ex.printStackTrace();
			throw ex;
		}finally{
			if(pstmt != null){
				pstmt.close();
			}
		}
	}
	
	
	public static Connection getConnection() throws Exception{
		Class.forName(Oracledriver);
	    return DriverManager.getConnection(OracleSparkDevurl,OracleSparkDevUsername,OracleSparkDevPassword);
	}
}
