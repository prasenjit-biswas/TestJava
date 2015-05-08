package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Dataretrieval.util.DBConnection;


public class TestQueryUnionAll {
	
	public static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztodev";
	
	
	public static final String INSERT_SELECT_TEST_QUESTION_XREF = "INSERT INTO test_question_xref(testid, questionid, questiontype, TYPEIDENTIFIER" +
	", points, reftag, creationtime , createdby, maxpoints, pickerxml, pickerxmltimestamp) " +
	"SELECT ?, ?, questiontype, TYPEIDENTIFIER, ?, reftag, SYSDATE, createdby, maxpoints, pickerxml, SYSDATE " +
	"FROM test_question_xref WHERE testid = ? AND questionid = ?";

	public static final String SELECT_TEST_QUESTION_XREF ="SELECT questionid, points FROM test_question_xref WHERE" +
														 " testid =?";
	
	
	
	
	
	
	public static void main(String args[])
	{
	   Connection con = null;
	   try{
		   con = getConnection(); 
		   List<Map<String,String>> returnList = getData(con);
		   InsertData(con,returnList);
		   releaseResources(con);
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }finally{
		   releaseResources(con);
	   }
       		
	}
	
	
	public static List<Map<String,String>> getData(Connection con){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		try{
	        pstmt = con.prepareStatement(SELECT_TEST_QUESTION_XREF);
			pstmt.setString(1, "13252698006312435");
			rs = pstmt.executeQuery();
	    	while(rs.next()){
	    		Map<String, String> map = new HashMap<String,String>();
	    	 	String questionid = rs.getString("questionid");
	    		String points = rs.getString("points");
	    		map.put("questionid", questionid);
	    		map.put("points", points);
	    		returnList.add(map);
	     	}
	    	releaseResources(rs);
	    	releaseResources(pstmt);
	       }catch(Exception e){
	    	  System.out.println("Exception in getData..."); 
	    	  e.printStackTrace();
	       }finally{
	    	  releaseResources(rs);
		      releaseResources(pstmt); 
	       }  	
	      return returnList;
	}
	
	public static void InsertData(Connection con ,List<Map<String,String>> returnList){
		PreparedStatement pstmt = null;
		try{
			long startTime = (new java.util.Date()).getTime();
			StringBuilder insertQuery = new StringBuilder("");
			insertQuery.append("INSERT INTO test_question_xref(testid, questionid, questiontype, TYPEIDENTIFIER");
			insertQuery.append(", points, reftag, creationtime , createdby, maxpoints, pickerxml, pickerxmltimestamp) ");
			if(returnList!= null && !returnList.isEmpty()){
			   int length = returnList.size();
			   int count = 0;
			   Iterator<Map<String,String>> itr = returnList.iterator();
			   while(itr.hasNext()){
				   Map<String, String> valueMap = itr.next();
				   String questionid = valueMap.get("questionid");
				   String points = valueMap.get("points");
				   insertQuery.append("\n");
				   insertQuery.append("SELECT '"+123+"' testid , '"+(123+count)+"' questionid , questiontype, TYPEIDENTIFIER, "+points+" points , reftag, SYSDATE creationtime, createdby, maxpoints, pickerxml, SYSDATE pickerxmltimestamp");
				   insertQuery.append(" FROM test_question_xref WHERE testid = '13252698006312435' AND questionid = '"+questionid+"'");
				   if(count == (length-1)){
					   //insertQuery.append(" ; ");   
				   }else{
					   insertQuery.append("\n");
					   insertQuery.append("  UNION ALL   ");
					  
				   }
				   count++;  
			   }	
				
			}
			System.out.println("InsertQuesry insertQuery : "+insertQuery);
			pstmt = con.prepareStatement(insertQuery.toString());
			long endJavaExceuteTime = (new java.util.Date()).getTime();
			pstmt.executeUpdate();
			long endDBExceuteTime = (new java.util.Date()).getTime();
			releaseResources(pstmt);
			System.out.println("Time Taken for JAVA : "+(endJavaExceuteTime - startTime)+ " ms");
			System.out.println("Time Taken for DB : "+(endDBExceuteTime - endJavaExceuteTime)+ " ms");
			System.out.println("Time Taken for DB+JAVA : "+(endDBExceuteTime - startTime)+ " ms");
			
			//pstmt = con.prepareStatement(sql);
		}catch(Exception ex){
			System.out.println("Exception in InsertBlock...");
			ex.printStackTrace();
		}finally{
			releaseResources(pstmt);
		}
	}
	
	
	public static Connection getConnection() throws Exception {
	    Class.forName(Oracledriver);
	    return DriverManager.getConnection(Oracleurl,Oracleusername,Oraclepassword);
    }
	
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
}
