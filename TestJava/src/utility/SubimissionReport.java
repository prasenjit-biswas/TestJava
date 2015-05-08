package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class SubimissionReport extends Thread {

	
	
	static String SELECT_ATTEMPT_RECORD_FOR_TESTID = "SELECT count(1) questionCount FROM attempt att, attemptdata atd WHERE att.testid = ? and atd.attempt_pk = att.attempt_pk and att.submissionid is not null";
	
	static String SELECT_SUBMISSION_RECORD_FOR_TESTID = "SELECT count(1) questionCount FROM submissions s, responses r WHERE s.testid = ? and r.testid = s.testid and s.submissionid is not null ";
	
	static String INSERT_ATTEMPTED_RECORD_FOR_TESTID = "INSERT INTO attemptrecord(testid, attemptcount, startdate, enddate) VALUES (?,?,?,?)";
	
	public static SubimissionReportIdCreator subimissionReportIdCreator;
	
	static String startDate;
	static String endDate;
	
	
	
	public void run(){
		Connection selectCon = null;
		Connection insertCon = null;
		Map<String, String> testMap = null;
		PreparedStatement insertPstmt = null;
		try{
			selectCon = SubimissionReportIdCreator.getDevConnection();
			insertCon = SubimissionReportIdCreator.getDevConnection();
			insertPstmt = insertCon.prepareStatement(INSERT_ATTEMPTED_RECORD_FOR_TESTID);
			long insertbatchCount = 0l;
			while((testMap = subimissionReportIdCreator.getTestMap()) != null){
				if(!testMap.isEmpty()){
					Iterator<String> keyItr = testMap.keySet().iterator();
					if(keyItr.hasNext()){
						long attemptCount = 0;
						String testID = keyItr.next();
						String newTest = testMap.get(testID);
						if(newTest != null && ("Y").equalsIgnoreCase(newTest)){
							attemptCount = createNewTestReport(selectCon, testID);
						}else{
							attemptCount = createOldTestReport(selectCon, testID);
						}
						insertAttemptedRedord(insertPstmt, testID, attemptCount);
						
						if(insertbatchCount%1000 == 0){
							insertPstmt.executeBatch();
							insertCon.commit();
						}
					}
				}
			}
			
			if(insertbatchCount%1000 != 0){
				insertPstmt.executeBatch();
				insertCon.commit();
			}
		}catch(Exception ex){
			System.out.println(" Exception in run "+ex.getMessage());
			ex.printStackTrace();
			insertCon.rollback();
		}finally{
			releaseResources(insertPstmt);
			releaseResources(selectCon);
			releaseResources(insertCon);
		}
	}
	
	
	
	
	public long createNewTestReport(Connection con, String testid) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long attemptCount = 0;
		try{
			pstmt = con.prepareStatement(SELECT_ATTEMPT_RECORD_FOR_TESTID);
			pstmt.setString(1, testid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attemptCount = rs.getLong("questionCount");
			}
			releaseResources(pstmt);
			releaseResources(rs);
			
		}catch(Exception ex){
			System.out.println(" Problem in createNewTestReport for testid : "+testid);
			ex.printStackTrace();
			throw ex;
		}finally{
			releaseResources(pstmt);
			releaseResources(rs);
		}
		return attemptCount;
	}
	
	
	
	
	public long createOldTestReport(Connection con, String testid) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long attemptCount = 0;
		try{
			pstmt = con.prepareStatement(SELECT_SUBMISSION_RECORD_FOR_TESTID);
			pstmt.setString(1, testid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attemptCount = rs.getLong("questionCount");
			}
			releaseResources(pstmt);
			releaseResources(rs);
			
		}catch(Exception ex){
			System.out.println(" Problem in createOldTestReport for testid : "+testid);
			ex.printStackTrace();
			throw ex;
		}finally{
			releaseResources(pstmt);
			releaseResources(rs);
		}
		return attemptCount;
	}
	
	
	
	public void insertAttemptedRedord(PreparedStatement pstmt, String testID, long attemptCount) throws Exception{
		try{
			
			pstmt.setString(1, testID);
			pstmt.setLong(2, Long.valueOf(attemptCount));
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			
			pstmt.addBatch();
		}catch(Exception ex){
			System.out.println(" Exception in insertAttemptedRedord for testid : "+testID);
			ex.printStackTrace();
			throw ex;
		}
	}
	
	
	
	public static void main(String[] args) {
		int noOfThreads = 0;
		long startTime = 0l;
		long endTime = 0l;
		
		try{
			if(args != null && args.length ==3){
				noOfThreads = Integer.parseInt(args[0]);
				startDate =  args[1];
				endDate =  args[2];
				if(startDate != null && !("").equals(startDate)){
					startTime = getTimeInMillis(startDate);
				}
				if(endDate != null && !("").equals(endDate)){
					endTime = getTimeInMillis(endDate);
				}
			}else{
				System.out.println(" Provide number Of threads, startDate and endDate.");
			}
		
			System.out.println(" startDate : "+startDate+" , startTime : "+startTime);
			System.out.println(" endDate : "+endDate+" , endTime : "+endTime);
			
			subimissionReportIdCreator = new SubimissionReportIdCreator(startTime, endTime);
			
			for (int i = 0; i < noOfThreads; i++) {
				SubimissionReport subimissionReport = new SubimissionReport();
				subimissionReport.start();
			}
		}catch(Exception ex){
			System.out.println(" Exception in main "+ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	
	public static long getTimeInMillis(String date) throws Exception{
		long timeInMillis = 0;
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Date date1 = formatter.parse(date);
		timeInMillis = date1.getTime();
		return timeInMillis;
	} 
	
	
	public void releaseResources(Connection con) {
		try{
			if(con != null){
				con.close();
			}
		}catch(Exception ex){
			System.out.println(" Problem in releasing Connection resource.");
			ex.printStackTrace();
		}
	}
	public void releaseResources(PreparedStatement pstmt) {
		try{
			if(pstmt != null){
				pstmt.close();
			}
		}catch(Exception ex){
			System.out.println(" Problem in releasing PreparedStatement resource.");
			ex.printStackTrace();
		}
	}
	public void releaseResources(ResultSet rst) {
		try{
			if(rst != null){
				rst.close();
			}
		}catch(Exception ex){
			System.out.println(" Problem in releasing ResultSet resource.");
			ex.printStackTrace();
		}
	}
	
	
	
}
