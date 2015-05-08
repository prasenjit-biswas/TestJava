package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import Dataretrieval.util.DBConnection;
import Dataretrieval.util.OracleConnection;

public class LoadTest_Oracle extends Thread{
	static File myFile = null;
	static FileWriter fw = null;
	static String lineSep = System.getProperty("line.separator");
	static long counter = 0;
  public static void main(String argv[]) throws Exception{
	 //int noOfThreads = 10;
	int noOfThreads = Integer.parseInt(argv[0]);
	myFile = new File("./Oracle_getTest_13009757387946758_" + noOfThreads +".csv");
	fw = new FileWriter(myFile,true);
	fw.append(lineSep + "Test Start Time," + new Timestamp(System.currentTimeMillis()).toString());
	fw.append(lineSep + "Oracle Thread Count," + noOfThreads);
	//System.out.println("noOfThreads: " + noOfThreads);
	counter = noOfThreads;
     for(int i=0; i<noOfThreads; i++) {
    	 LoadTest_Oracle loadTestOracle = new LoadTest_Oracle();
    	 loadTestOracle.start();
     }
    
  }
	
	private static void getTest() throws Exception{
	  Connection con = null;
	  PreparedStatement pst = null;
	  ResultSet rs = null;
	  String testid = "13009757387946758";
	  long groupid = 0;
	  long bankid = 0;
	  Vector idlist = new Vector();
	  try{
		con = OracleConnection.getConnection();
		long start = System.currentTimeMillis();
		pst = con.prepareStatement("SELECT * FROM tests WHERE testID=?");
	    pst.setString(1, testid);
	    long startQueryTimeTests = System.currentTimeMillis();
	    rs = pst.executeQuery();
	    long endQueryTimeTests = System.currentTimeMillis();
	    if(rs.next()){
	    	rs.getString("title");
			rs.getInt("enabled");
			rs.getLong("modified");
			rs.getString("testID");
			rs.getBinaryStream("data");
			rs.getLong("beginDate");					
			rs.getLong("endDate");
			rs.getLong("recallDate");
			rs.getLong("elapsed");
			rs.getLong("indexID");
			rs.getString("owner");
			rs.getLong("oemID");
			rs.getString("url");
			bankid = rs.getLong("bankID");
			rs.getLong("creation");
			rs.getLong("managers");
			rs.getLong("participants");
			rs.getLong("computers");
	    }
	    long endTotalTimeTests = System.currentTimeMillis();
	    DBConnection.releaseResources(null, pst, rs);
	    
	    pst = con.prepareStatement("SELECT * from groups WHERE testID = ? ORDER BY orderID");
    	pst.setString(1, testid);
    	long startQueryTimeGroups = System.currentTimeMillis();
		rs = pst.executeQuery();
		long endQueryTimeGroups = System.currentTimeMillis();
    	if (rs.next()) {
        	//format = rs.getInt("format");
    		groupid= rs.getLong("groupID");
        }
    	long endTotalTimeGroups = System.currentTimeMillis();
    	DBConnection.releaseResources(null, pst, rs);
    	
    	pst = con.prepareStatement("SELECT * from groupmembers WHERE groupID = ? ORDER BY orderID");
    	pst.setLong(1, groupid);
    	long startQueryTimeGroupMembers = System.currentTimeMillis();
		rs = pst.executeQuery();
		long endQueryTimeGroupMembers = System.currentTimeMillis();
    	while (rs.next()){
			idlist.addElement( rs.getString("questionID") );
    	}
    	long endTotalTimeGroupMembers = System.currentTimeMillis();
    	DBConnection.releaseResources(null, pst, rs);
    	
    	pst = con.prepareStatement("SELECT * FROM questionbanks WHERE bankID = ?");
    	pst.setLong(1, bankid);
    	long startQueryTimeQBanks = System.currentTimeMillis();
		rs = pst.executeQuery();
		long endQueryTimeQBanks = System.currentTimeMillis();
    	while (rs.next()){
    		rs.getLong("modtime");
			rs.getLong("managers");
			rs.getInt("publicFlag");
			rs.getString("owner");
    	}
    	long endTotalTimeQBanks = System.currentTimeMillis();
    	DBConnection.releaseResources(null, pst, rs);
    	
    	pst = con.prepareStatement("SELECT questionID, format, data FROM questions WHERE bankID = ?");
    	pst.setLong(1, bankid);
    	long startQueryTimeQuestions = System.currentTimeMillis();
		rs = pst.executeQuery();
		long endQueryTimeQuestions = System.currentTimeMillis();
		InputStream bStream = null;
		int format = 0;
    	while(rs.next()){
    		bStream = rs.getBinaryStream("data");
    		format = rs.getInt("format");
    	}
    	long endTotalTimeQuestions = System.currentTimeMillis();
    	//DBConnection.releaseResources(null, pst, rs);
    	//long endQueryExecutionTime = System.currentTimeMillis();
    	//System.out.println("Time taken for executeQuery  : " + (end - start));
    	//fw.append(lineSep + "getQuestion Query Execution Time Taken," + (end - start));
    	//DataInputStream input= null;
    	//int count = 0;
    	//System.out.println("Loading questions starts at : " + new Timestamp(System.currentTimeMillis()));
        
        long end = System.currentTimeMillis();
        System.out.println("Total Time taken : " + (end - start));
        StringBuffer sb = new StringBuffer();
        sb.append(lineSep + "QueryTimeTests," + (endQueryTimeTests - startQueryTimeTests))
          .append(",TotalTimeTests," + (endTotalTimeTests - startQueryTimeTests))
          .append(",QueryTimeGroups," + (endQueryTimeGroups - startQueryTimeGroups))
          .append(",TotalTimeGroups," + (endTotalTimeGroups - startQueryTimeGroups))
          .append(",QueryTimeGroupMembers," + (endQueryTimeGroupMembers - startQueryTimeGroupMembers))
          .append(",TotalTimeGroupMembers," + (endTotalTimeGroupMembers - startQueryTimeGroupMembers))
          .append(",QueryTimeQBanks," + (endQueryTimeQBanks - startQueryTimeQBanks))
          .append(",TotalTimeQBanks," + (endTotalTimeQBanks - startQueryTimeQBanks))
          .append(",QueryTimeQuestions," + (endQueryTimeQuestions - startQueryTimeQuestions))          
          .append(",TotalTimeQuestions," + (endTotalTimeQuestions - startQueryTimeQuestions))
          .append(",getTest Total Time Taken," + (end - start));
        fw.append(sb.toString());
        counter --;
        //System.out.println("Loading questions completes at : " + new Timestamp(System.currentTimeMillis()));
        //System.out.println("Total count : " + count);
		}finally{
			if(counter == 0){			
				fw.close();
			}
			DBConnection.releaseResources(con, pst, rs);
		}
	}

	@Override
	public void run() {
		try {
	    	getTest();
	    } catch(SQLException exSQLException){
	    	System.out.print(exSQLException);
		    exSQLException.printStackTrace();
	    	
		} catch(Exception exException){
			System.out.print(exException);
		    exException.printStackTrace();
			
	    }finally{
	    	
	    }
		
	}
}
