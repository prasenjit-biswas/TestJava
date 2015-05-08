package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class trueFalseQ extends Question{

	public static int		QUESTION_TYPE_trueFalse = 2;
	public static int		QUESTION_TYPE_undefined = 0;
	public int		type = QUESTION_TYPE_undefined;
	public static String		TYPE_IDENTIFIER			= "TF";

	static String		FOLLOWUP_TAG				= "followupTag";
	static String		INCLUDE_NOTAPPLICABLE		= "includeNotApplicable";


	private boolean		includeFollowup = false,
	includeNotApplicable = false;


	private String		followupTag = "";

	private int			branchTrue = -1,
	branchFalse = -1,
	branchUnanswered = -1;
	public richPropertiesUtil	questionProperties = null;


	public String getFollowupTag() {
		return followupTag;
	}
	public void setFollowupTag(String followupTag) {
		this.followupTag = followupTag;
	}
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
	public int getBranchTrue() {
		return branchTrue;
	}
	public void setBranchTrue(int branchTrue) {
		this.branchTrue = branchTrue;
	}
	public int getBranchFalse() {
		return branchFalse;
	}
	public void setBranchFalse(int branchFalse) {
		this.branchFalse = branchFalse;
	}
	public int getBranchUnanswered() {
		return branchUnanswered;
	}
	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}

	public trueFalseQ() {}
	public static trueFalseQ getQuestionTFQ(Question result, DataInputStream theInput, int format) throws Exception{
		trueFalseQ trueFalseq = new trueFalseQ();
		try{
			trueFalseq = (trueFalseQ)Question.getQuestion(result, theInput, format);
			//type= QUESTION_TYPE_trueFalse;

			try {

				// read the booleans
				//includeFollowup= theInput.readBoolean();			
				//includeNotApplicable= theInput.readBoolean();
				trueFalseq.setIncludeFollowup(theInput.readBoolean());
				trueFalseq.setIncludeNotApplicable(theInput.readBoolean());

				// read the ints
				if (format >= 5) {
					/*branchTrue= theInput.readInt();
				branchFalse= theInput.readInt();
				branchUnanswered= theInput.readInt();*/
					trueFalseq.setBranchTrue(theInput.readInt());
					trueFalseq.setBranchFalse(theInput.readInt());
					trueFalseq.setBranchUnanswered(theInput.readInt());
				}

				// read the strings
				//followupTag= theInput.readUTF();
				trueFalseq.setFollowupTag(theInput.readUTF());

				// read additional subclasses here
				ArrayList correctAnswerFeedbackList = new ArrayList();
				int maxPoints = trueFalseq.getMaxPoints();
				ArrayList pointlList = trueFalseq.getPoints();
				if (format < 405)  {

					//synchronized ( correctAnswerFeedback ) {
					//	correctAnswerFeedback= new VectorAdapter();
					int truePts= 0;
					int falsePts= 0;
					try {
						truePts= Integer.parseInt((String)pointlList.get(0));
						falsePts= Integer.parseInt((String)pointlList.get(1));
					} catch (NumberFormatException n) {}

					maxPoints= Math.max( truePts, falsePts );
					if (maxPoints > 0) {
						if (truePts > 0)
							correctAnswerFeedbackList.add( (String)pointlList.get(0) + " points awarded for selecting <I>True</I><BR>\r" );
						else if (falsePts > 0)
							correctAnswerFeedbackList.add( (String)pointlList.get(1) + " points awarded for selecting <I>False</I><BR>\r" );
					}
					trueFalseq.setCorrectAnswerFeedback(correctAnswerFeedbackList);
				}


			} catch (IOException e) {
				e.printStackTrace();
				throw (new Exception( "IOException reading trueFalse question" ) );

			}
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			String useFollowup= questionProperties.getString( FOLLOWUP_TAG, "" );
			if (useFollowup.length() > 0)
			{
				//followupTag= useFollowup;
				//includeFollowup= true;
				trueFalseq.setFollowupTag(useFollowup);
				trueFalseq.setIncludeFollowup(true);
			}
			questionProperties.setString( FOLLOWUP_TAG, trueFalseq.getFollowupTag());

			//includeNotApplicable= questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, includeNotApplicable );
			trueFalseq.setIncludeNotApplicable(questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, trueFalseq.includeNotApplicable ));
			questionProperties.setBoolean( INCLUDE_NOTAPPLICABLE, trueFalseq.includeNotApplicable );
			questionProperties.setBoolean( "completeIncompleteGrading", false );
			trueFalseq.setQuestionProperties(questionProperties);
			print_trueFalseQ(trueFalseq);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return trueFalseq;

	}
	private static void print_trueFalseQ(trueFalseQ trueFalseq) {
		System.out.println("########### Printing trueFalseQ Start ################");
		System.out.println(" includeFollowup : " + trueFalseq.includeFollowup);
		System.out.println(" includeNotApplicable : " + trueFalseq.includeNotApplicable);
		System.out.println(" branchTrue : " + trueFalseq.getBranchTrue());
		System.out.println(" branchFalse : " + trueFalseq.getBranchFalse());
		System.out.println(" branchUnanswered : " + trueFalseq.getBranchUnanswered());
		System.out.println(" followupTag : " + trueFalseq.getFollowupTag());
		System.out.println("..... printing Question Properties in trueFalseQ....");
		richPropertiesUtil questionProperties = trueFalseq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing trueFalseQ end ################");

	}}