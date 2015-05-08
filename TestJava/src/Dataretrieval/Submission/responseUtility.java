package Dataretrieval.Submission;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class responseUtility {
	public boolean	hasFollowup= false;

	private int		pointsMax = 0,
	points = 0;

	long			filePosition= 0;

	private String	questionID = "",
	html = "",
	followupValue = "",
	correctFormulaAnswer = "";

	private String comment = "";
	private ArrayList<String>	feedback;

	private ArrayList<String> userResponse;

	private ArrayList<String> recordedValue;

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public ArrayList<String> getFeedback() {
		return feedback;
	}


	public void setFeedback(ArrayList<String> feedback) {
		this.feedback = feedback;
	}


	public ArrayList<String> getUserResponse() {
		return userResponse;
	}


	public void setUserResponse(ArrayList<String> userResponse) {
		this.userResponse = userResponse;
	}


	public ArrayList<String> getRecordedValue() {
		return recordedValue;
	}


	public void setRecordedValue(ArrayList<String> recordedValue) {
		this.recordedValue = recordedValue;
	}


	/**
	 * Retrieves a single question {@link responseUtility} from the database. 
	 *
	 * @param rs
	 * ResultSet representing the result of an SQL query retrieving the response
	 *
	 * @return {@link responseUtility} object instance.
	 * @throws Exception 
	 *
	 * @see #getResponses
	 */
	public static responseUtility getResponse( ResultSet rs )
	throws Exception
	{
		responseUtility theResp= new responseUtility();

		theResp.questionID= rs.getString("questionID");
		theResp.points= rs.getInt("points");
		theResp.pointsMax= rs.getInt("maxpoints");

		theResp.correctFormulaAnswer= rs.getString("formulaAnswer");
		//v4.0.3 Oracle might return null here
		if (theResp.correctFormulaAnswer == null) {
			theResp.correctFormulaAnswer= "";
		}

		theResp.followupValue= SubmissionUtility.stringFromStream(rs.getBinaryStream("followup"));
		theResp.hasFollowup= (theResp.followupValue.length() > 0);

		theResp.html= SubmissionUtility.stringFromStream(rs.getBinaryStream("html"));

		theResp.recordedValue= vectorFromStream(rs.getBinaryStream("recordedValue"));

		theResp.userResponse= vectorFromStream(rs.getBinaryStream("response"));

		theResp.feedback= vectorFromStream(rs.getBinaryStream("feedback"));

		theResp.comment= SubmissionUtility.stringFromStream(rs.getBinaryStream("instructorComment"));
		//}
		print_Response(theResp);
		return( theResp );
	}


	public static void print_Response(responseUtility theResp) {
		System.out.println("############## Printing data for Response start ###############");
		System.out.println(" questionID : " + theResp.questionID);
		System.out.println(" points : " + theResp.points);
		System.out.println(" points Max : " + theResp.pointsMax);
		System.out.println(" correct FormulaAnswer : " + theResp.correctFormulaAnswer);
		System.out.println(" followUp Value : " + theResp.followupValue);
		System.out.println(" has Followup : " + theResp.hasFollowup);
		System.out.println(" Html : " + theResp.html);
		for(String strRecordedValue : theResp.getRecordedValue())
		{
		System.out.println(" recorded Value : " + strRecordedValue);
		}
		for(String strUserResponse : theResp.getUserResponse())
		{
		System.out.println(" user Response : " + strUserResponse);
		}
		for(String strFeedback : theResp.getFeedback()){
		System.out.println(" feedback : " + strFeedback);
		}
		System.out.println(" comment : " + theResp.comment);
		System.out.println("############## Printing data for Response end ###############");

	}


	public responseUtility() {
		feedback= new ArrayList<String>();
		userResponse= new ArrayList<String>();
		recordedValue= new ArrayList<String>();
	}


	public static ArrayList<String> vectorFromStream( InputStream bStream )
	{
		ArrayList<String> result= new ArrayList<String> ();

		try 
		{
			DataInputStream input= new DataInputStream(bStream);

			int count= input.readInt();
			for (int i=0 ; i<count ; i++)
				result.add( SubmissionUtility.readString( input ) );
		}
		catch (IOException i) 
		{
			i.printStackTrace();
			System.out.println("IOException in tp_sql.vectorFromStream()");
		}
		return( result );
	}

	public static void getResponses(Connection con, SubmissionUtility theSubmission) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			pstmt= con.prepareStatement( "SELECT * FROM responses WHERE submissionID = ?" );
			pstmt.setString(1,  theSubmission.getSqlID());

			rs= pstmt.executeQuery();

			while (rs.next()) {
				theSubmission.responses.add( getResponse( rs ) );
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		//pstmt.close();
	}

}
