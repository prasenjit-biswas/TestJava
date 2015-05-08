package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

public class rankingQ extends Question{
	public richPropertiesUtil	questionProperties = null;
	public static String		TYPE_IDENTIFIER			= "RA";
	public static String		EDIT_UNBRANCH = "EDIT_rUnanswered",
	EDIT_CORRECTBRANCH = "EDIT_rCorrect",
	EDIT_INCORRECTBRANCH = "EDIT_rIncorrect",
	EDIT_PTS = "EDITrPTS",
	EDIT_FEEDBACK = "EDITrFEEDBACK",
	EDIT_CHOICE = "EDITrCHOICE",
	EDIT_DEL = "EDITrDelete",
	EDIT_PARTIAL = "EDITrPartial";
	public static String		NO_PARTIAL_CREDIT		= "noPartialCredit";
	private boolean		noPartialCredit = false;
	private int			branchCorrect = -1,
	branchIncorrect = -1,
	branchUnanswered = -1;

	public boolean isNoPartialCredit() {
		return noPartialCredit;
	}
	public void setNoPartialCredit(boolean noPartialCredit) {
		this.noPartialCredit = noPartialCredit;
	}
	public int getBranchCorrect() {
		return branchCorrect;
	}
	public void setBranchCorrect(int branchCorrect) {
		this.branchCorrect = branchCorrect;
	}
	public int getBranchIncorrect() {
		return branchIncorrect;
	}
	public void setBranchIncorrect(int branchIncorrect) {
		this.branchIncorrect = branchIncorrect;
	}
	public int getBranchUnanswered() {
		return branchUnanswered;
	}
	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}
	public rankingQ(){}
	public static rankingQ getRankingQ(Question result, DataInputStream theInput, int format) throws Exception{

		rankingQ rankingq = new rankingQ();
		rankingq = (rankingQ)Question.getQuestion(result, theInput, format);
		try {

			// read the booleans
			//noPartialCredit= theInput.readBoolean();	
			rankingq.setNoPartialCredit(theInput.readBoolean());

			// read the ints
			/*branchCorrect= theInput.readInt();
			branchIncorrect= theInput.readInt();
			branchUnanswered= theInput.readInt();*/
			rankingq.setBranchCorrect(theInput.readInt());
			rankingq.setBranchIncorrect(theInput.readInt());
			rankingq.setBranchUnanswered(theInput.readInt());

			// read additional subclasses here

		} catch (IOException e) {
			e.printStackTrace();
			throw (new IOException( "IOException reading ranking question" ) );

		}
		richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
		//noPartialCredit= questionProperties.getBoolean( NO_PARTIAL_CREDIT, noPartialCredit );
		rankingq.setNoPartialCredit(questionProperties.getBoolean( NO_PARTIAL_CREDIT, rankingq.isNoPartialCredit() ));
		questionProperties.setBoolean( NO_PARTIAL_CREDIT, rankingq.isNoPartialCredit() );
		rankingq.setQuestionProperties(questionProperties);
		print_rankingQ(rankingq);
		return rankingq;

	}
	private static void print_rankingQ(rankingQ rankingq) {
		System.out.println("########### Printing rankingQ Start ################");
		System.out.println(" noPartialCredit : " + rankingq.noPartialCredit);
		System.out.println(" branchCorrect : " + rankingq.getBranchCorrect());
		System.out.println(" branchIncorrect : " + rankingq.getBranchIncorrect());
		System.out.println(" branchUnanswered : " + rankingq.getBranchUnanswered());
		System.out.println("..... printing Question Properties in RankingQ....");
		richPropertiesUtil questionProperties = rankingq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing rankingQ End ################");

	}

}
