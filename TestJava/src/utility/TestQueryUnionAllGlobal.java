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

public class TestQueryUnionAllGlobal {
	public static final String Oracledriver  = "oracle.jdbc.driver.OracleDriver";
	public static final String Oracleurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	public static final String Oracleusername = "ezto";
	public static final String Oraclepassword = "eztodev";
	
	public static final String SELECT_TEST_QUESTION_XREF ="SELECT questionid, points FROM test_question_xref WHERE" +
	 " testid =?";

	static int count = 0;
    static long totalTime = 0l;
	public static void main(String args[])
	{
        List<String> testList = getTest();
        Iterator<String> itr = testList.iterator();
        Connection con = null;
        
        try{
        	con = getConnection();
        	con.setAutoCommit(false);
        	int testCounter =1;
        	long start = (new java.util.Date()).getTime(); 
        	while(itr.hasNext()){
        		String testid = itr.next();
        		List<Map<String,String>> returnList = getData(con, testid);
     		    InsertDataInGlobalTable(con,returnList, testid);
     		    InsertXrefTable(con,testCounter,testid);
     		   testCounter ++;
        	}
        	long end = (new java.util.Date()).getTime();
        	System.out.println("Total time taken : "+totalTime);
        	System.out.println("Total time taken with SELECT : "+(end - start ));
        	con.rollback();
 		    releaseResources(con);
        }catch(Exception ex){
 		   ex.printStackTrace();
 	   }finally{
 		   releaseResources(con);
 	   }
        
        
       
	   
       		
	}
	
	
	public static List<String> getTest(){
		List<String> a =new ArrayList<String>();
		a.add("13252698006313162");
		a.add("13252698006313204");
		a.add("13252698006362764");
		a.add("13252698006362806");
		a.add("13252698006313326");
		a.add("13252698006313368");
		a.add("13252698006362928");
		a.add("13252698006362970");
		a.add("13252698006313490");
		a.add("13252698006313532");
		a.add("13252698006363092");
		a.add("13252698006363134");
		a.add("13252698006313654");
		a.add("13252698006313696");
		a.add("13252698006363256");
		a.add("13252698006363298");
		a.add("13252698006312670");
		a.add("13252698006312712");
		a.add("13252698006362272");
		a.add("13252698006362314");
		a.add("13252698006313818");
		a.add("13252698006313860");
		a.add("13252698006363420");
		a.add("13252698006363462");
		a.add("13252698006313982");
		a.add("13252698006314024");
		a.add("13252698006363584");
		a.add("13252698006363626");
		a.add("13252698006314146");
		a.add("13252698006314188");
		a.add("13252698006363748");
		a.add("13252698006363790");
		a.add("13252698006314310");
		a.add("13252698006314352");
		a.add("13252698006363912");
		a.add("13252698006363954");
		a.add("13252698006314474");
		a.add("13252698006314516");
		a.add("13252698006364076");
		a.add("13252698006364118");
		a.add("13252698006212108");
		a.add("13252698006212150");
		a.add("13252698006312834");
		a.add("13252698006312876");
		a.add("13252698006362436");
		a.add("13252698006362478");
		a.add("13252698006312998");
		a.add("13252698006313040");
		a.add("13252698006362600");
		a.add("13252698006362642");
		a.add("13252698006312435");
		a.add("13252698006312547");
		return a;
	}
	
	
	
	
	
	
public static List<Map<String,String>> getData(Connection con, String testid){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		try{
	        pstmt = con.prepareStatement(SELECT_TEST_QUESTION_XREF);
			pstmt.setString(1, testid);
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


  
   public static void InsertDataInGlobalTable(Connection con, List<Map<String,String>> returnList, String testid)
   {
	   PreparedStatement pstmt =null;
	   try{
		   long start = (new java.util.Date()).getTime();
		   StringBuilder sb = new StringBuilder("");
		   sb.append("INSERT INTO copy_course_temp (sourcetid,sourceqid, targetqid, points)");
		   if(returnList != null && !returnList.isEmpty())
		   {
			 Iterator<Map<String,String>> itr = returnList.iterator();
			 
			 int length = returnList.size();
			 int loopCounter =0;
		     while(itr.hasNext())
		     {
		    	 Map<String,String> valueMap = itr.next();
		    	 String questionid = valueMap.get("questionid");
				 String points = valueMap.get("points"); 
		    	 sb.append(" \n");
		    	 sb.append("SELECT '"+testid+"' sourcetid, '"+ questionid +"' sourceqid , '"+(123+count) +"' targetqid , "+points +" points");
		    	 sb.append(" FROM dual ");
		    	 if(loopCounter == (length - 1)){
		    		 
		    	 }else{
		    		 sb.append(" \n");
		    		 sb.append("UNION ALL");
		    	 }
		    	 count ++; 
		    	 loopCounter ++;
		     }
		   }
		   //System.out.println(" Global Table : " +sb);
		   long endJavaTime = (new java.util.Date()).getTime();
		   pstmt = con.prepareStatement(sb.toString());
		   pstmt.executeUpdate();
		   long endDBTime = (new java.util.Date()).getTime();
		   releaseResources(pstmt); 
		   /*System.out.println(" Global Table : Time taken for JAVA : "+(endJavaTime - start)+" ms");
		   System.out.println(" Global Table : Time taken for DB : "+(endDBTime - endJavaTime)+" ms");*/
		   //System.out.println(" Global Table : Time taken for JAVA+DB : "+(endDBTime - start)+" ms");
		   totalTime = totalTime + (endDBTime - start);
		   
	   }catch(Exception e){
		   System.out.println("Exception in Inserting GlobalTable : ");
		   e.printStackTrace();
	   }finally{
		   releaseResources(pstmt);  
	   }
   }



  public static void InsertXrefTable(Connection con, int testCounter, String testid)
  {   
	  PreparedStatement pstmt = null;
	  try{
		  long start = (new java.util.Date()).getTime();
		  StringBuilder sb = new StringBuilder();
		  sb.append("INSERT INTO test_question_xref(testid, questionid, questiontype, TYPEIDENTIFIER, points, reftag, creationtime , createdby, maxpoints, pickerxml, pickerxmltimestamp)");
	      sb.append("SELECT  '"+(124+testCounter)+"' testid, (SELECT targetQid FROM copy_course_temp WHERE sourcetid = tqx.testid AND sourceQid = tqx.questionid) questionid, ");
	      sb.append("questiontype, TYPEIDENTIFIER ");
	      sb.append(", (SELECT POINTS FROM copy_course_temp WHERE sourcetid = tqx.testid AND sourceQid = tqx.questionid ) points");
	      sb.append(", reftag, SYSDATE creationtime , createdby, maxpoints, pickerxml, SYSDATE pickerxmltimestamp");
	      sb.append(" FROM test_question_xref tqx WHERE tqx.testid = '"+testid+"' ");
	      
	      long endJavaTime = (new java.util.Date()).getTime();
	      pstmt = con.prepareStatement(sb.toString());
	      pstmt.executeUpdate();
	      long endDBTime = (new java.util.Date()).getTime();
	     
	      /*System.out.println(" Xref Table : Time taken for JAVA : "+(endJavaTime - start)+" ms");
		  System.out.println(" Xref Table : Time taken for DB : "+(endDBTime - endJavaTime)+" ms");*/
		  //System.out.println(" Xref Table : Time taken for JAVA+DB : "+(endDBTime - start)+" ms");
	      totalTime = totalTime + (endDBTime - start);
	  }catch(Exception e){
		  System.out.println(" Exception in InsertXrefTable ");
		  e.printStackTrace();
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
