package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class surveyQ extends Question{
	public richPropertiesUtil	questionProperties = null;
	public static int		QUESTION_TYPE_undefined = 0;
	public int		type = QUESTION_TYPE_undefined;
	public static int		QUESTION_TYPE_survey = 6;
	private boolean		includeFollowup = false,
	includeNotApplicable = false,

	useHorizontalOrientation = false,
	useHorizontalOrientationWLabels = false,
	suppressNumbers = false,
	suppressText= false;
	static String		FOLLOWUP_TAG				= "followupTag";
	static String		INCLUDE_NOTAPPLICABLE		= "includeNotApplicable";
	static String		SUPPRESS_NUMBERS			= "suppressNumbers";
	static String		SUPPRESS_TEXT				= "suppressText";

	static String		PRESENTATION				= "presentation";
	static String		PRESENTATION_VERT			= "vertical";
	static String		PRESENTATION_HORIZ			= "horizontal";
	static String		PRESENTATION_HORIZ_LABELED	= "horizontal with labels";

	static String		LEFT_LABEL					= "leftLabel";
	static String		RIGHT_LABEL					= "rightLabel";

	private String		followupTag = "",
	leftLabel = "",
	rightLabel = "";

	private int			branchUnanswered = -1;

	private ArrayList		branchTo;

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
	public boolean isUseHorizontalOrientation() {
		return useHorizontalOrientation;
	}
	public void setUseHorizontalOrientation(boolean useHorizontalOrientation) {
		this.useHorizontalOrientation = useHorizontalOrientation;
	}
	public boolean isUseHorizontalOrientationWLabels() {
		return useHorizontalOrientationWLabels;
	}
	public void setUseHorizontalOrientationWLabels(
			boolean useHorizontalOrientationWLabels) {
		this.useHorizontalOrientationWLabels = useHorizontalOrientationWLabels;
	}
	public boolean isSuppressNumbers() {
		return suppressNumbers;
	}
	public void setSuppressNumbers(boolean suppressNumbers) {
		this.suppressNumbers = suppressNumbers;
	}
	public boolean isSuppressText() {
		return suppressText;
	}
	public void setSuppressText(boolean suppressText) {
		this.suppressText = suppressText;
	}
	public String getFollowupTag() {
		return followupTag;
	}
	public void setFollowupTag(String followupTag) {
		this.followupTag = followupTag;
	}
	public int getBranchUnanswered() {
		return branchUnanswered;
	}
	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}
	public ArrayList getBranchTo() {
		return branchTo;
	}
	public void setBranchTo(ArrayList branchTo) {
		this.branchTo = branchTo;
	}
	public String getLeftLabel() {
		return leftLabel;
	}
	public void setLeftLabel(String leftLabel) {
		this.leftLabel = leftLabel;
	}
	public String getRightLabel() {
		return rightLabel;
	}
	public void setRightLabel(String rightLabel) {
		this.rightLabel = rightLabel;
	}
	public surveyQ(){}
	public static surveyQ getSurveyQ(Question result, DataInputStream theInput, int format) {
		surveyQ surveyq = new surveyQ();
		try{	

			surveyq = (surveyQ)Question.getQuestion(result, theInput, format);
			try {

				// read the booleans
				/*includeFollowup= theInput.readBoolean();			
			     includeNotApplicable= theInput.readBoolean();*/
				surveyq.setIncludeFollowup(theInput.readBoolean());
				surveyq.setIncludeNotApplicable(theInput.readBoolean());

				if (format >= 29)
					//useHorizontalOrientation= theInput.readBoolean();
					surveyq.setUseHorizontalOrientation(theInput.readBoolean());
				if (format >= 46)
					//useHorizontalOrientationWLabels= theInput.readBoolean();
					surveyq.setUseHorizontalOrientationWLabels(theInput.readBoolean());
				ArrayList<String> branchTo= new ArrayList<String>();
				// read the ints
				if (format >= 5) {
					if (format > 8) {
						int count= theInput.readInt();
						for (int i=0;i<count;i++){
							branchTo.add( theInput.readUTF() );	
						}
						//branchUnanswered= theInput.readInt();
						surveyq.setBranchUnanswered(theInput.readInt());
					}
					else {
						for(int i= 0 ; i<20 ; i++){
							branchTo.add( Integer.toString(theInput.readInt()) );	
						}
						surveyq.setBranchUnanswered(theInput.readInt());
					}
				}
				else {
					for(int i= 0 ; i<20 ; i++)
						branchTo.add( "-1" );			
				}
				// read the strings
				//followupTag= theInput.readUTF();
				surveyq.setFollowupTag(theInput.readUTF());
				if (format >= 46) {
					/*leftLabel= theInput.readUTF();
					rightLabel= theInput.readUTF();*/
					surveyq.setLeftLabel(theInput.readUTF());
					surveyq.setRightLabel(theInput.readUTF());
				}

				if (format >= 37) {
					/*suppressNumbers= theInput.readBoolean();
					suppressText= theInput.readBoolean();*/
					surveyq.setSuppressNumbers(theInput.readBoolean());
					surveyq.setSuppressText(theInput.readBoolean());
				}

				// read additional subclasses here

			} catch (IOException e) {

				e.printStackTrace();

			}
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			String useFollowup= questionProperties.getString( FOLLOWUP_TAG, "" );
			if (useFollowup.length() > 0)
			{
				/*followupTag= useFollowup;
				includeFollowup= true;*/
				surveyq.setFollowupTag(useFollowup);
				surveyq.setIncludeFollowup(true);
			}
			questionProperties.setString( FOLLOWUP_TAG, surveyq.getFollowupTag() );

			/*includeNotApplicable= questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, includeNotApplicable );
			questionProperties.setBoolean( INCLUDE_NOTAPPLICABLE, includeNotApplicable );*/
			surveyq.setIncludeNotApplicable(questionProperties.getBoolean( INCLUDE_NOTAPPLICABLE, surveyq.includeNotApplicable ));
			questionProperties.setBoolean( INCLUDE_NOTAPPLICABLE, surveyq.includeNotApplicable );

			//leftLabel= questionProperties.getString( LEFT_LABEL, leftLabel );
			surveyq.setLeftLabel(questionProperties.getString( LEFT_LABEL, surveyq.getLeftLabel() ));
			questionProperties.setString( LEFT_LABEL, surveyq.getLeftLabel() );

			//rightLabel= questionProperties.getString( RIGHT_LABEL, rightLabel );
			surveyq.setRightLabel(questionProperties.getString( RIGHT_LABEL, surveyq.getRightLabel() ));
			questionProperties.setString( RIGHT_LABEL, surveyq.getRightLabel() );

			//suppressNumbers= questionProperties.getBoolean( SUPPRESS_NUMBERS, suppressNumbers );
			surveyq.setSuppressNumbers(questionProperties.getBoolean( SUPPRESS_NUMBERS, surveyq.isSuppressNumbers() ));
			questionProperties.setBoolean( SUPPRESS_NUMBERS, surveyq.isSuppressNumbers() );

			//suppressText= questionProperties.getBoolean( SUPPRESS_TEXT, suppressText );
			surveyq.setSuppressText(questionProperties.getBoolean( SUPPRESS_TEXT, surveyq.suppressText ));
			questionProperties.setBoolean( SUPPRESS_TEXT, surveyq.suppressText );

			String usePreso= questionProperties.getString( PRESENTATION, "" );
			if (usePreso.length() > 0)
			{
				/*useHorizontalOrientation= usePreso.equals(PRESENTATION_HORIZ);
				useHorizontalOrientationWLabels= usePreso.equals(PRESENTATION_HORIZ_LABELED);*/
				surveyq.setUseHorizontalOrientation(usePreso.equals(PRESENTATION_HORIZ));
				surveyq.setUseHorizontalOrientationWLabels(usePreso.equals(PRESENTATION_HORIZ_LABELED));
			}
			String presentation= PRESENTATION_VERT;
			if (surveyq.isUseHorizontalOrientation()) {
				presentation= PRESENTATION_HORIZ;
			}
			else if (surveyq.useHorizontalOrientationWLabels) presentation= PRESENTATION_HORIZ_LABELED;
			questionProperties.setString( PRESENTATION, presentation );
			surveyq.setQuestionProperties(questionProperties);
			//print_surveyQ(surveyq);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return surveyq;

	}
	private static void print_surveyQ(surveyQ surveyq) {
		System.out.println("########### Printing surveyQ Start ################");
		System.out.println(" includeFollowup : " + surveyq.includeFollowup);
		System.out.println(" includeNotApplicable : " + surveyq.includeNotApplicable);
		System.out.println(" useHorizontalOrientation : " + surveyq.useHorizontalOrientation);
		System.out.println(" useHorizontalOrientationWLabels : " + surveyq.useHorizontalOrientationWLabels);
		System.out.println(" branchUnanswered : " + surveyq.getBranchUnanswered());
		System.out.println(" followupTag : " + surveyq.getFollowupTag());
		System.out.println(" leftLabel : " + surveyq.getLeftLabel());
		System.out.println(" rightLabel : " + surveyq.getRightLabel());
		System.out.println(" suppressNumbers : " + surveyq.isSuppressNumbers());
		System.out.println(" suppressText : " + surveyq.isSuppressText());
		System.out.println("..... printing Question Properties in surveyQ....");
		richPropertiesUtil questionProperties = surveyq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing surveyQ End ################");

	}}
