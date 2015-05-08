package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class matchingQ extends Question{
	public richPropertiesUtil	questionProperties = null;
	static String	EDIT_PTS = "EDITmaPTS",
	EDIT_CHOICE = "EDITmaCHOICE",
	EDIT_DETRACTOR = "EDITmaDET",
	EDIT_MATCH = "EDITmaMATCH",
	EDIT_FEED_MATCH = "EDITmaFEEDmatch",
	EDIT_FEED_UNMATCH = "EDITmaFEEDunmatch",
	EDIT_BRANCH_SCORE = "EDITmaBRANCHscore",
	EDIT_BRANCH_GT = "EDITmaBRANCHgt",
	EDIT_BRANCH_LE = "EDITmaBRANCHle",
	EDIT_DEL = "EDITmaDelete",
	EDIT_DELDET = "EDITmaDELDET",
	EDIT_SUPP = "EDITmaSupress";

	static String	SUPPRESS_CHOICES		= "suppressChoices";
	private ArrayList<String>	matches;		//v4.1.2a5 added detractors
	private ArrayList<String> detractors;
	private int		branchScore = 0,
	branchGreater = -1,
	branchLessEqual = -1;
	private boolean	supressChoices = false;

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

	public boolean isSupressChoices() {
		return supressChoices;
	}

	public void setSupressChoices(boolean supressChoices) {
		this.supressChoices = supressChoices;
	}
	public ArrayList<String> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList matches) {
		this.matches = matches;
	}

	public ArrayList<String> getDetractors() {
		return detractors;
	}

	public void setDetractors(ArrayList detractors) {
		this.detractors = detractors;
	}

	public matchingQ(){}
	public static matchingQ getMatchingQ(Question result, DataInputStream theInput, int format) throws Exception {


		matchingQ matchingq = new matchingQ();
		try{
			matchingq = (matchingQ)matchingq.getQuestion(result, theInput, format);
			try {

				// read the ints
				if (format >= 5) {
					/*branchScore= theInput.readInt();
				branchGreater= theInput.readInt();
				branchLessEqual= theInput.readInt();*/
					matchingq.setBranchScore(theInput.readInt());
					matchingq.setBranchGreater(theInput.readInt());
					matchingq.setBranchLessEqual(theInput.readInt());
				}

				if (format >= 45) {
					//supressChoices= theInput.readBoolean();
					matchingq.setSupressChoices(theInput.readBoolean());
				}

				// read matches
				ArrayList<String> matches = new ArrayList<String>();
				int matchesCount= theInput.readInt();
				for (int i=0 ; i<matchesCount ; i++){
					matches.add( theInput.readUTF() );
				}
				matchingq.setMatches(matches);
				ArrayList<String> detractors = new ArrayList<String>();
				if (format >= 407) {
					int detCount= theInput.readInt();
					for (int i=0 ; i<detCount ; i++)
						detractors.add( theInput.readUTF() );
				}
				matchingq.setDetractors(detractors);

				// read additional subclasses here

				ArrayList correctAnswerFeedbackList = new ArrayList();
				ArrayList  choiceList = matchingq.getChoices();
				int maxPoints = matchingq.getMaxPoints();
				ArrayList feedbackList = matchingq.getFeedback();
				ArrayList pointlList = new ArrayList();
				pointlList = matchingq.getPoints();
				if (format < 20) {
					for (int i=0 ; i<choiceList.size() ; i++)
						((VectorAdapter) feedbackList).setElementAt( fmReturn + (String)feedbackList.get(i), i );
				}

				if (format < 404) {
					// v4.1.1a3
					maxPoints= 0;
					correctAnswerFeedbackList= new ArrayList();
					for (int i=0 ; i<choiceList.size() ;) {
						String theChoice= (String)choiceList.get(i);
						String theMatch= (String)matches.get(i);

						if ((theChoice.length() == 0) || (theMatch.length() == 0)) {		// remove empty choices
							choiceList.remove(i);
							matches.remove(i);
							feedbackList.remove(i);
							pointlList.remove(i);
						}
						else {																// calculate maxPoints
							try {
								String pstr= (String)pointlList.get(i);
								int pts= Integer.parseInt( pstr );
								maxPoints += Math.max( 0, pts );
								if (pts > 0)
									correctAnswerFeedbackList.add( pstr + " points awarded for <I>" + theChoice + "::" + theMatch + "</I><BR>" );
							} catch (NumberFormatException e) {}
							i++;
						}
					}
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			//supressChoices= questionProperties.getBoolean( SUPPRESS_CHOICES, supressChoices );
			matchingq.setSupressChoices(questionProperties.getBoolean( SUPPRESS_CHOICES, matchingq.supressChoices ));
			questionProperties.setBoolean( SUPPRESS_CHOICES, matchingq.supressChoices );
			matchingq.setQuestionProperties(questionProperties);
			print_matchingQ(matchingq);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return matchingq;

	}

	private static void print_matchingQ(matchingQ matchingq) {
		System.out.println("########### Printing matchingQ Start ################");
		System.out.println(" branchScore : " + matchingq.getBranchScore());
		System.out.println(" branchGreater : " + matchingq.getBranchGreater());
		System.out.println(" branchLessEqual : " + matchingq.getBranchLessEqual());
		System.out.println(" supressChoices : " + matchingq.isSupressChoices());
		System.out.println(" matches : " + matchingq.getMatches());
		System.out.println(" detractors : " + matchingq.getDetractors());
		richPropertiesUtil questionProperties = matchingq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing matchingQ End ################");

	}

}
