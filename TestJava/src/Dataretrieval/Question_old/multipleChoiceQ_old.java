package Dataretrieval.Question;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class multipleChoiceQ_old extends Question{

	private boolean		includeOther= false,
	randomizeChoiceOrder = false,
	lockChoiceOrder = false,
	horizontal= false, 
	twocolumn= false;


	private int			branchUnanswered = -1,
	otherBoxSize = 40;
	private ArrayList<String>		branchTo;

	public static String		TYPE_IDENTIFIER				= "MC";

	static String		PRESENTATION				= "layout";
	static String		PRESENTATION_HORIZ			= "horizontal";
	static String		PRESENTATION_TWOCOLUMN		= "two column";
	static String		PRESENTATION_VERT			= "vertical";

	static String		ORDER						= "order";
	static String		ORDER_NORMAL				= "as listed";
	static String		ORDER_SCRAM					= "scrambled";
	static String		ORDER_LOCK					= "lock as listed";

	static String		OTHER						= "otherItem";

	public ArrayList<String> getBranchTo() {
		return branchTo;
	}
	public void setBranchTo(ArrayList<String> branchTo) {
		this.branchTo = branchTo;
	}

	public boolean isIncludeOther() {
		return includeOther;
	}
	public void setIncludeOther(boolean includeOther) {
		this.includeOther = includeOther;
	}
	public boolean isRandomizeChoiceOrder() {
		return randomizeChoiceOrder;
	}
	public void setRandomizeChoiceOrder(boolean randomizeChoiceOrder) {
		this.randomizeChoiceOrder = randomizeChoiceOrder;
	}
	public boolean isLockChoiceOrder() {
		return lockChoiceOrder;
	}
	public void setLockChoiceOrder(boolean lockChoiceOrder) {
		this.lockChoiceOrder = lockChoiceOrder;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean isTwocolumn() {
		return twocolumn;
	}
	public void setTwocolumn(boolean twocolumn) {
		this.twocolumn = twocolumn;
	}
	public int getBranchUnanswered() {
		return branchUnanswered;
	}
	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}
	public int getOtherBoxSize() {
		return otherBoxSize;
	}
	public void setOtherBoxSize(int otherBoxSize) {
		this.otherBoxSize = otherBoxSize;
	}
	public multipleChoiceQ_old() {}

	public static multipleChoiceQ_old getQuestionMCQ (Question result, DataInputStream theInput, int format) throws Exception{
		multipleChoiceQ_old multipleChoiceq = null;
		try{
			multipleChoiceq = (multipleChoiceQ_old)Question.getQuestion(result, theInput, format);

			ArrayList<String> branchTo= new ArrayList<String>();

			// read the booleans
			//includeOther= theInput.readBoolean();

			if (format >= 402)		
				//randomizeChoiceOrder= theInput.readBoolean();	
				multipleChoiceq.setRandomizeChoiceOrder(theInput.readBoolean());

			if (format >= 436)		
				//lockChoiceOrder= theInput.readBoolean();			
				multipleChoiceq.setLockChoiceOrder(theInput.readBoolean());


			// read the ints
			if (format >= 5) {
				if (format > 8) {
					int count= theInput.readInt();
					for (int i=0;i<count;i++){
						branchTo.add( theInput.readUTF() );
					}
					//branchUnanswered= theInput.readInt();
					multipleChoiceq.setBranchUnanswered(theInput.readInt());
				}
				else {
					for(int i= 0 ; i<20 ; i++){
						branchTo.add( Integer.toString(theInput.readInt()) );
					}
					//branchUnanswered= theInput.readInt();
					multipleChoiceq.setBranchUnanswered(theInput.readInt());
				}
			}
			else {
				for(int i= 0 ; i<20 ; i++)
					branchTo.add( "-1" );			
			}
			multipleChoiceq.setBranchTo(branchTo);
			if (format > 8)
				//otherBoxSize= theInput.readInt();
				multipleChoiceq.setOtherBoxSize(theInput.readInt());

			// read additional subclasses here
			ArrayList correctAnswerFeedbackList = new ArrayList();
			ArrayList choiceList = multipleChoiceq.getChoices();
			ArrayList pointlList = multipleChoiceq.getPoints();
			int maxPoints = multipleChoiceq.getMaxPoints();
			if (format < 405) {
				maxPoints= 0;
				//correctAnswerFeedback= new VectorAdapter();
				for (int i=0 ; i<choiceList.size() ; i++) {
					try {

						String pstr= (String)pointlList.get(i);
						int pts= Integer.parseInt( pstr );
						maxPoints= Math.max( maxPoints, pts );	// calculate maxPoints

						String theChoice= (String)choiceList.get(i);

						if (pts > 1)
							correctAnswerFeedbackList.add( "<I>" + theChoice + "</I> would be awarded " + pstr + " points<BR>" );
						else if (pts == 1)
							correctAnswerFeedbackList.add( "<I>" + theChoice + "</I> would be awarded " + pstr + " point<BR>" );

					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				multipleChoiceq.setCorrectAnswerFeedback(correctAnswerFeedbackList);
			}

			if (format >= 419) {
				//horizontal= theInput.readBoolean();
				multipleChoiceq.setHorizontal(theInput.readBoolean());
			}
			if (format >= 432){
				//twocolumn= theInput.readBoolean();
				multipleChoiceq.setTwocolumn(theInput.readBoolean());
			}

			String useLayout= PRESENTATION_VERT;
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			boolean		includeOther= false,
			randomizeChoiceOrder = false,
			lockChoiceOrder = false,
			horizontal= false, 
			twocolumn= false;

			if (horizontal){
				useLayout= PRESENTATION_HORIZ;
			}
			else if (twocolumn){
				useLayout= PRESENTATION_TWOCOLUMN;
			}

			String layout= questionProperties.getString( PRESENTATION, useLayout );
			questionProperties.setString( PRESENTATION, layout );

			String useOrder= ORDER_NORMAL;
			if (randomizeChoiceOrder){
				useOrder= ORDER_SCRAM;
			}
			else if (lockChoiceOrder){
				useOrder= ORDER_LOCK;
			}
			String order= questionProperties.getString( ORDER, useOrder );
			questionProperties.setString( ORDER, order );
			int		otherBoxSize = 40;
			String incOther= questionProperties.getString( OTHER, (includeOther ? Integer.toString(otherBoxSize) : "") );
			try
			{
				multipleChoiceq.setOtherBoxSize(Integer.parseInt(incOther));
				//includeOther= true;
				multipleChoiceq.setIncludeOther(true);
			}
			catch (NumberFormatException n)
			{
				incOther= "";
				//includeOther= false;
				multipleChoiceq.setIncludeOther(false);
			}
			questionProperties.setString( OTHER, incOther );
			multipleChoiceq.setQuestionProperties(questionProperties);
			//print_MultiplechoiceQ(multipleChoiceq);	
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return multipleChoiceq;
	}
	private static void print_MultiplechoiceQ(multipleChoiceQ_old multipleChoiceq) {
		System.out.println("########### Printing MultiplechoiceQ Starts ################");
		System.out.println(" includeOther : " + multipleChoiceq.includeOther);
		System.out.println(" randomizeChoiceOrder : " + multipleChoiceq.randomizeChoiceOrder);
		System.out.println(" lockChoiceOrder : " + multipleChoiceq.lockChoiceOrder);
		System.out.println("..... printing branchTo ....");
		for(String isbranchToStr : multipleChoiceq.getBranchTo())
		{
			System.out.println(" branchTo : " + isbranchToStr);
		}
		System.out.println(" branchUnanswered : " + multipleChoiceq.getBranchUnanswered());
		System.out.println(" otherBoxSize : " +multipleChoiceq.getOtherBoxSize());
		System.out.println(" horizontal : " + multipleChoiceq.horizontal);
		System.out.println(" twocolumn : " + multipleChoiceq.twocolumn);
		System.out.println("..... printing Question Properties in MultipleChoiceQ....");

		richPropertiesUtil questionProperties = multipleChoiceq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}

		System.out.println("########### Printing MultiplechoiceQ End ################");


	}
}
