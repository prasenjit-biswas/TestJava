package Dataretrieval.Submission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import Dataretrieval.util.OracleConnection;

public class SubmissionUtility {
	private String		userID = "";
	private String	scoreString = "";
	private String	sqlID = "";
	private int			score = 0,
	maxScore = 0,
	totalCorrect = 0,
	percentageScore = 0;
	public parametersUtil	formVariables;
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	private java.util.Date	submissionTime = null;
	public long			elapsedTime= 0;
	public ArrayList		responses;


	public ConcurrentHashMap<String,String>	responseLocations,				// used internally, no need to save
	groupEvaluations;
	
	public ConcurrentHashMap<String,String> getResponseLocations() {
		return responseLocations;
	}


	public void setResponseLocations(ConcurrentHashMap<String,String> responseLocations) {
		this.responseLocations = responseLocations;
	}


	public ConcurrentHashMap<String,String> getGroupEvaluations() {
		return groupEvaluations;
	}


	public void setGroupEvaluations(ConcurrentHashMap<String,String> groupEvaluations) {
		this.groupEvaluations = groupEvaluations;
	}


	public ArrayList getResponses() {
		return responses;
	}


	public void setResponses(ArrayList responses) {
		this.responses = responses;
	}
	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getScoreString() {
		return scoreString;
	}


	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}


	public String getSqlID() {
		return sqlID;
	}


	public void setSqlID(String sqlID) {
		this.sqlID = sqlID;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getMaxScore() {
		return maxScore;
	}


	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}


	public int getTotalCorrect() {
		return totalCorrect;
	}


	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}


	public SubmissionUtility() {
		responses= new ArrayList();
	}


	public static void main(String argv[]) throws Exception{

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		HashMap<String, String> thisData= null;
		//String testID= "13044417596096631";
		String submissionID = "13252698289382008"; // 13252698032711123, 
		
		try {
			con = OracleConnection.getConnection();
			//getSubmissions(con,testID,submissionID);
			getSubmissions(con,submissionID);


		} catch(SQLException exSQLException){
			System.out.print(exSQLException);
			exSQLException.printStackTrace();
			if(con != null){
				//con.rollback();
			}
		} catch(Exception exException){
			System.out.print(exException);
			exException.printStackTrace();
			if(con != null){
				//con.rollback();
			}
		}finally{
			if(rs != null){
				rs.close();
			}
			if(pst != null){
				pst.close();
			}
			if(con != null){
				con.close();
			}
		}
	}



	static String readString( DataInputStream input )
	throws IOException{
		String result= input.readUTF();
		if (result.equals(LONG_STRING))
		{
			//System.out.println("inputting " + LONG_STRING);
			int arrayLength= input.readInt();
			byte[] barray= new byte[ arrayLength ];
			input.read( barray, 0, arrayLength );
			result= new String( barray, "UTF-8" );
		}
		return(result);
	}

	public static SubmissionUtility getSubmissions(Connection con,String testID,String submissionID) throws Exception{
		PreparedStatement pst = null;
		SubmissionUtility theResult = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement("select * from submissions where testID = ? and submissionID = ?");
			pst.setString(1, testID);
			pst.setString(2, submissionID);

			rs = pst.executeQuery();

			if (rs.next()){
				theResult = SubmissionUtility.getSubmission(rs);
			}
			if(theResult != null){
				responseUtility.getResponses( con, theResult);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return theResult;
	}
	
	public static SubmissionUtility getSubmissions(Connection con,String submissionID) throws Exception{
		PreparedStatement pst = null;
		SubmissionUtility theResult = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement("select * from submissions where submissionID = ? ");
			pst.setString(1, submissionID);

			rs = pst.executeQuery();

			if (rs.next()){
				theResult = SubmissionUtility.getSubmission(rs);
			}
			if(theResult != null){
				responseUtility.getResponses( con, theResult);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return theResult;
	}


	public static SubmissionUtility getSubmission(ResultSet rs) throws SQLException {


		SubmissionUtility theSubmission= new SubmissionUtility();

		theSubmission.sqlID= rs.getString("submissionID");

		theSubmission.userID= rs.getString("userID");

		theSubmission.score= rs.getInt("score");
		theSubmission.maxScore= rs.getInt("maxscore");
		theSubmission.totalCorrect= rs.getInt("totalcorrect");
		theSubmission.percentageScore= rs.getInt("pctscore");

		theSubmission.submissionTime= new java.util.Date( rs.getLong("submissionTime") );
		theSubmission.elapsedTime= rs.getLong("elapsedTime");
		theSubmission.formVariables= parametersFromStream(rs.getBinaryStream("params"));
		theSubmission.groupEvaluations= hashFromStream(rs.getBinaryStream("groupScores"));
	
		print_Submission(theSubmission);
		return( theSubmission );


	}

	private static void print_Submission(SubmissionUtility theSubmission) {
		System.out.println("############## Printing data for Submission start ###############");
		System.out.println(" sqlID : " + theSubmission.sqlID);
		System.out.println(" userID : " + theSubmission.userID);
		System.out.println(" score : " + theSubmission.score);
		System.out.println(" max Score : " + theSubmission.maxScore);
		System.out.println(" total Correct : " + theSubmission.totalCorrect);
		System.out.println(" percentage Score : " + theSubmission.percentageScore);
		System.out.println(" submission Time : " + theSubmission.submissionTime);
		System.out.println(" elapsed Time : " + theSubmission.elapsedTime);
		System.out.println(" form Variables : " + theSubmission.formVariables);
		Map<String,String> groupEvaluations = new ConcurrentHashMap<String,String>();
		groupEvaluations = theSubmission.getGroupEvaluations();
		//Set s = groupEvaluations.entrySet();
		Iterator<Entry<String, String>> grpEval = groupEvaluations.entrySet().iterator();
		//Iterator<String> grpEval = s.iterator();
		 while(grpEval.hasNext()){
	            //Entry<String, String> key = grpEval.next();
	            System.out.println(" group Evaluations : " + grpEval.next());
	         
	        }

		System.out.println("############## Printing data for Submission end ###############");
	}


	public static ConcurrentHashMap hashFromStream( InputStream bStream )
	{
		ConcurrentHashMap result= new ConcurrentHashMap();

		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);

				int count= input.readInt();
				for (int i= 0 ; i< count ; i++) 
				{
					String key= input.readUTF();
					String value= readString(input);
					result.put( key, value );
				}
				bStream.close();
			} 
			catch (IOException i) 
			{
				//System.out.println("IOException in tp_sql.hashFromStream()");
				//i.printStackTrace();
			}
		}

		return( result );
	}

	public static parametersUtil parametersFromStream( InputStream bStream )
	{
		parametersUtil result= new parametersUtil();

		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				result= new parametersUtil( input );
			} 
			catch (IOException i) 
			{
				System.out.println("IOException in tp_sql.parametersFromStream()");
			}
		}
		return( result );
	}


	public static String stringFromStream( InputStream bStream )
	{
		String result= "";

		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				result= readString( input );
				bStream.close();
			} 
			catch (IOException i) 
			{
				i.printStackTrace();
				System.out.println("IOException in tp_sql.stringFromStream()");
				
			}
		}
		return( result );
	}
	public static void writeString( String theString, DataOutputStream out )
	throws IOException
	{
		if (theString.length() > 65535) 
		{
			//System.out.println("outputting " + LONG_STRING);
			out.writeUTF( LONG_STRING );
			byte[] barray= theString.getBytes("UTF-8");
			out.writeInt( barray.length );
			out.write( barray, 0, barray.length);
		}
		else
			out.writeUTF( theString );
	}
	public static void vectorFromStream( InputStream bStream )
	{
		try 
		{
			DataInputStream input= new DataInputStream(bStream);

			int count= input.readInt();
			for (int i=0 ; i<count ; i++)
				System.out.println(readString( input ));
		}
		catch (IOException i) 
		{
			System.out.println("IOException in tp_sql.vectorFromStream()");
		}
	}

}
