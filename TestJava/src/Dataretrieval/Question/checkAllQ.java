package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class checkAllQ extends Question{

	public static String		TYPE_IDENTIFIER				= "CA" ;

	static char		filemakerReturn[] 		= { (char)0x0b };
	static char		filemakerRecordSep[] 	= { (char)0x1d };

	/** 
	 * Constant String containing the return character used for v2 FileMaker imports. 
	 * Also used as a field delimiter between tokens when serializing lists of objects into Strings.
	 */
	static String	fmReturn = new String( filemakerReturn );
	static String	fmRecSep = new String( filemakerRecordSep ),

	EDIT_PTS = "EDITcaPTSckd",
	EDIT_PTS2 = "EDITcaPTSunckd",
	EDIT_CHOICE = "EDITcaCHOICE",
	EDIT_FEED = "EDITcaFEEDckd",
	EDIT_FEED2 = "EDITcaFEEDunckd",
	EDIT_BRANCH_SCORE = "EDITcaBRANCHscore",
	EDIT_BRANCH_GT = "EDITcaBRANCHgt",
	EDIT_BRANCH_LE = "EDITcaBRANCHle",
	EDIT_DEL = "EDITcaDelete";				

	private int	branchScore = 0,
	branchGreater = -1,
	branchLessEqual = -1;
	public int		QUESTION_TYPE_checkAll = 5;
	public  int		QUESTION_TYPE_undefined = 0;
	private int		type = QUESTION_TYPE_undefined;



	public int getBranchScore() {
		return branchScore;
	}

	public void setBranchScore(int branchScore) {
		this.branchScore = branchScore;
	}

	public int getBranchGreater() {
		return branchGreater;
	}

	public void setBranchGreater(int branchGreater) {
		this.branchGreater = branchGreater;
	}

	public int getBranchLessEqual() {
		return branchLessEqual;
	}

	public void setBranchLessEqual(int branchLessEqual) {
		this.branchLessEqual = branchLessEqual;
	}


	public checkAllQ() {}

	public static checkAllQ getQuestioncheckAll(Question result, DataInputStream theInput, int format) throws Exception {
		checkAllQ checkAllq = new checkAllQ();
		checkAllq = (checkAllQ)Question.getQuestion(result, theInput, format);
		try {

			// read the ints
			if (format >= 5) {
				/*branchScore= theInput.readInt();
				branchGreater= theInput.readInt();
				branchLessEqual= theInput.readInt();*/
				checkAllq.setBranchScore(theInput.readInt());
				checkAllq.setBranchGreater(theInput.readInt());
				checkAllq.setBranchLessEqual(theInput.readInt());
			}
			ArrayList  choiceList = checkAllq.getChoices();
			ArrayList pointlList = checkAllq.getPoints();
			ArrayList feedbackList = checkAllq.getFeedback();
			int maxPoints = checkAllq.getMaxPoints();
			ArrayList<String> correctAnswerFeedbackList = new ArrayList<String>();

			if (format < 16) {

				for (int i=0 ; i<choiceList.size() ; i++) {
					String thePts= (String)pointlList.get(i);

					String theFeedback= (String)feedbackList.get(i);
					try {
						int pts= Integer.parseInt( thePts );

						if (pts == 0) {	// incorrect if chosen
							feedbackList.set( i,theFeedback + fmReturn );
							pointlList.set(i, "0" + fmReturn + thePts );
						}
						else {			// correct if chosen
							feedbackList.set( i,fmReturn + theFeedback );
							pointlList.set( i, thePts + fmReturn + "0" );
						}

					} catch (NumberFormatException n) {
						feedbackList.set(i, theFeedback + fmReturn );
						pointlList.set(i, "0" + fmReturn );
					}
				}
				checkAllq.setCorrectAnswerFeedback(correctAnswerFeedbackList);
			}

			// read additional subclasses here

			if (format < 404) {	// fix duplicated info in correctAnswerFeedback vector

				maxPoints= 0;
				//correctAnswerFeedback= new VectorAdapter();

				for (int i=0 ; i<choiceList.size() ; i++) {
					String theChoice= (String)choiceList.get(i);
					try {
						richMediaUtil theMedia= new richMediaUtil( theChoice );
						theMedia.setChoice(true);
						//theChoice= theMedia.html("");
					} catch (IOException e) {}

					if (theChoice.length() > 0) {

						// calculate maxPoints
						try {
							String pstr= (String)pointlList.get(i);

							String theCorrectFeedback= "";

							int ptsCkd= Integer.parseInt( pstr.substring(0,pstr.indexOf(fmReturn)) );
							if (ptsCkd <= 0)
								theCorrectFeedback += "<I>" + theChoice + "</I> should remain unchecked<BR>";
							else if (ptsCkd == 1)
								theCorrectFeedback += "one point awarded for checking <I>" + theChoice + "</I><BR>";
							else
								theCorrectFeedback += Integer.toString(ptsCkd) + " points awarded for checking <I>" + theChoice + "</I><BR>";

							int ptsUnckd= Integer.parseInt( pstr.substring(pstr.indexOf(fmReturn)+1) );
							if (ptsUnckd <= 0) {
								if (ptsCkd > 0)
									theCorrectFeedback += "<I>" + theChoice + "</I> should be checked<BR>";
							}
							else if (ptsUnckd == 1)
								theCorrectFeedback += "one point awarded for NOT checking <I>" + theChoice + "</I><BR>";
							else
								theCorrectFeedback += Integer.toString(ptsUnckd) + " points awarded for NOT checking <I>" + theChoice + "</I><BR>";

							correctAnswerFeedbackList.add( theCorrectFeedback );

							int theMax= Math.max( ptsCkd, ptsUnckd );
							maxPoints += Math.max( 0, theMax );

						} catch (NumberFormatException e) {
							e.printStackTrace();
						}

					}
				}

			}
			print_checkAllQ(checkAllq);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checkAllq;

	}

	private static void print_checkAllQ(checkAllQ checkAllq) {
		System.out.println("############## printing data checkAllQ start");
		System.out.println(" branchScore : " + checkAllq.getBranchScore());
		System.out.println(" branchGreater : " + checkAllq.getBranchGreater());
		System.out.println(" branchLessEqual : " + checkAllq.getBranchLessEqual());
		System.out.println("############## printing data checkAllQ end");

	}
	
	public String formalTypeString2( Connection con) {
		return( "Check All That Apply" );
	}
}
