package utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Dataretrieval.util.OracleConnection;

public class LoadQuestion_Oracle extends Thread{
	static File myFile = new File("./Oracle_getQuestion.csv");
	static FileWriter fw = null;
	static String lineSep = System.getProperty("line.separator");
	static long counter = 0;
  public static void main(String argv[]) throws Exception{
	 //int noOfThreads = 10;
	int noOfThreads = Integer.parseInt(argv[0]);
	fw = new FileWriter(myFile,true);
	fw.append(lineSep + "Test Start Time," + new Timestamp(System.currentTimeMillis()).toString());
	fw.append(lineSep + "Thread Count," + noOfThreads);
	//System.out.println("noOfThreads: " + noOfThreads);
	counter = noOfThreads;
     for(int i=0; i<noOfThreads; i++) {
    	 LoadQuestion_Oracle loadQuestionOracle = new LoadQuestion_Oracle();
    	 loadQuestionOracle.start();
     }
    
  }
	
	private static void getQuestionForTest() throws Exception{
	  Connection con = null;
	  PreparedStatement pst = null;
	  ResultSet rs = null;
	  long bankid = 0;
	  try{
		con = OracleConnection.getConnection();
		pst = con.prepareStatement("SELECT bankid FROM tests WHERE testid = ?");
	    pst.setString(1, "12485782279706112");
	    rs = pst.executeQuery();
	    if(rs.next()){
	    	bankid = rs.getLong("bankid");
	    }
	    
	    OracleConnection.releaseResources(null, pst, rs);
	    
	    pst = con.prepareStatement("SELECT questionID, format, data FROM questions q WHERE q.bankid = ?");
    	pst.setLong(1, bankid);
		long start = System.currentTimeMillis();
    	rs = pst.executeQuery();
    	long endQueryExecutionTime = System.currentTimeMillis();
    	//System.out.println("Time taken for executeQuery  : " + (end - start));
    	//fw.append(lineSep + "getQuestion Query Execution Time Taken," + (end - start));
    	DataInputStream input= null;
    	int count = 0;
    	System.out.println("Loading questions starts at : " + new Timestamp(System.currentTimeMillis()));
        while (rs.next()) {
        	//format = rs.getInt("format");
        	input= new DataInputStream(rs.getBinaryStream("data"));
        	count ++;
        }
        long end = System.currentTimeMillis();
        //System.out.println("Total Time taken : " + (end - start));
        fw.append(lineSep + "getQuestion Query Execution Time Taken," + (endQueryExecutionTime - start)).append(",getQuestion Total Time Taken," + (end - start));
        counter --;
        System.out.println("Loading questions completes at : " + new Timestamp(System.currentTimeMillis()));
        //System.out.println("Total count : " + count);
		}finally{
			if(counter == 0){			
				fw.close();
			}
			OracleConnection.releaseResources(con, pst, rs);
		}
	}

	@Override
	public void run() {
		try {
	    	
	    	
	    	getQuestionForTest();
	    	
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
