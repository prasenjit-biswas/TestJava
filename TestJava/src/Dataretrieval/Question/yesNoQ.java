package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;



public class yesNoQ extends Question{

	public static String		TYPE_IDENTIFIER			= "YN";
	public richPropertiesUtil	questionProperties = null;
	public static String		FOLLOWUP_TAG				= "followupTag";
	public static String		INCLUDE_NOTAPPLICABLE		= "includeNotApplicable";


	private boolean				includeFollowup = false,
	includeNotApplicable = false;


	private int					branchYes = -1,
	branchNo = -1,
	branchUnanswered = -1;
	private String				followupTag = "";

	public boolean isIncludeFollowup() {
		return includeFollowup;
	}
	public void setIncludeFollowup(boolean includeFollowup) {
		this.includeFollowup = includeFollowup;
	}
	public boolean isIncludeNotApplicable() {
		return includeNotApplicable;
	}
	public void setIncludeNotApplicable(boolean includeNotApplicable) {
		this.includeNotApplicable = includeNotApplicable;
	}
	public int getBranchYes() {
		return branchYes;
	}
	public void setBranchYes(int branchYes) {
		this.branchYes = branchYes;
	}
	public int getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	public int getBranchUnanswered() {
		return branchUnanswered;
	}
	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}
	public String getFollowupTag() {
		return followupTag;
	}
	public void setFollowupTag(String followupTag) {
		this.followupTag = followupTag;
	}

	public yesNoQ() {}

	public static yesNoQ getQuestionYesNoQ(Question result, DataInputStream theInput, int format )

	{
		yesNoQ yesNoq = new yesNoQ();
		try {	
			yesNoq = (yesNoQ)Question.getQuestion(result, theInput, format);
			//type= QUESTION_TYPE_yesNo;

			try {

				// read the booleans
				/*includeFollowup= theInput.readBoolean();			
		includeNotApplicable= theInput.readBoolean();*/
				yesNoq.setIncludeFollowup(theInput.readBoolean());
				yesNoq.setIncludeNotApplicable(theInput.readBoolean());

				// read the ints
				if (format >= 5) {
					/*branchYes= theInput.readInt();
			branchNo= theInput.readInt();
			branchUnanswered= theInput.readInt();*/
					yesNoq.setBranchYes(theInput.readInt());
					yesNoq.setBranchNo(theInput.readInt());
					yesNoq.setBranchUnanswered(theInput.readInt());

					//System.out.println( "Branching is to " + branchYes + "on yes, to " + branchNo + " on no and " + branchUnanswered + " if unanswered." );
				}


				// read the strings
				//followupTag= theInput.readUTF();
				yesNoq.setFollowupTag(theInput.readUTF());

				// read additional subclasses here
				ArrayList correctAnswerFeedbackList = new ArrayList();
				int maxPoints = yesNoq.getMaxPoints();
				ArrayList pointlList = new ArrayList();
				pointlList = yesNoq.getPoints();
				if (format < 405)  {

					{
						//correctAnswerFeedback= new VectorAdapter();
						int yesPts= 0;
						int noPts= 0;
						try {
							yesPts= Integer.parseInt((String)pointlList.get(0));
							noPts= Integer.parseInt((String)pointlList.get(1));
						} catch (NumberFormatException n) {}

						maxPoints= Math.max( yesPts, noPts );
						//	correctAnswerFeedback= new VectorAdapter();
						if (maxPoints > 0) {
							if (yesPts > 0)
								correctAnswerFeedbackList.add( (String)pointlList.get(0) + " points awarded for selecting <I>Yes</I><BR>\r" );
							else if (noPts > 0)
								correctAnswerFeedbackList.add( (String)pointlList.get(1) + " points awarded for selecting <I>No</I><BR>\r" );
						}
					}
					yesNoq.setCorrectAnswerFeedback(correctAnswerFeedbackList);
				}

			} catch (IOException e) {

				throw (new Exception( "IOException reading yes or no question" ) );

			}
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			String useFollowup= questionProperties.getString( FOLLOWUP_TAG, "" );

			if (useFollowup.length() > 0)
			{
				//followupTag= useFollowup;
				//includeFollowup= true;
				yesNoq.setFollowupTag(useFollowup);
				yesNoq.setIncludeFollowup(true);
			}
			questionProperties.setString( FOLLOWUP_TAG, useFollowup );

			//includeNotApplicable= questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, includeNotApplicable );
			yesNoq.setIncludeNotApplicable(questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, yesNoq.includeNotApplicable ));
			questionProperties.setBoolean( INCLUDE_NOTAPPLICABLE, yesNoq.includeNotApplicable );
			yesNoq.setQuestionProperties(questionProperties);
			print_yesNoq(yesNoq);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return yesNoq;

	}
	private static void print_yesNoq(yesNoQ yesNoq) {
		System.out.println("########### Printing yesNoQ Start ################");
		System.out.println(" includeFollowup : " + yesNoq.includeFollowup);
		System.out.println(" includeNotApplicable : " + yesNoq.includeNotApplicable);
		System.out.println(" branchYes : " + yesNoq.getBranchYes());
		System.out.println(" branchNo : " + yesNoq.getBranchNo());
		System.out.println(" branchUnanswered : " + yesNoq.getBranchUnanswered());
		System.out.println(" followupTag : " + yesNoq.getFollowupTag());
		System.out.println("..... printing Question Properties in YesNoQ....");
		richPropertiesUtil questionProperties = yesNoq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing yesNoQ End ################");

	}}
