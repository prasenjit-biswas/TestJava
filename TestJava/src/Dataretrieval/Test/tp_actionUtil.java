package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;

public class tp_actionUtil extends Object{
	public static  boolean emailAnyone = false;
	public static  boolean emailList = false;
	public static String			emailAddresses= "";
	public static int ACTION_NONE = 0;
	public static int ACTION_FEEDBACK = 1;
	public static int		type = ACTION_NONE;
	private boolean returnScore;
	public boolean isReturnScore() {
		return returnScore;
	}

	public void setReturnScore(boolean returnScore) {
		this.returnScore = returnScore;
	}

	public boolean isReturnTotalCorrect() {
		return returnTotalCorrect;
	}

	public void setReturnTotalCorrect(boolean returnTotalCorrect) {
		this.returnTotalCorrect = returnTotalCorrect;
	}

	public boolean isReturnCorrectAnswer() {
		return returnCorrectAnswer;
	}

	public void setReturnCorrectAnswer(boolean returnCorrectAnswer) {
		this.returnCorrectAnswer = returnCorrectAnswer;
	}

	public boolean isReturnFeedback() {
		return returnFeedback;
	}

	public void setReturnFeedback(boolean returnFeedback) {
		this.returnFeedback = returnFeedback;
	}

	public boolean isFeedbackOnRecall() {
		return feedbackOnRecall;
	}

	public void setFeedbackOnRecall(boolean feedbackOnRecall) {
		this.feedbackOnRecall = feedbackOnRecall;
	}

	public boolean isEmailOwner() {
		return emailOwner;
	}

	public void setEmailOwner(boolean emailOwner) {
		this.emailOwner = emailOwner;
	}

	public boolean isShowStatistics() {
		return showStatistics;
	}

	public void setShowStatistics(boolean showStatistics) {
		this.showStatistics = showStatistics;
	}

	public boolean isCorrectOnRecall() {
		return correctOnRecall;
	}

	public void setCorrectOnRecall(boolean correctOnRecall) {
		this.correctOnRecall = correctOnRecall;
	}

	public boolean isScoreOnRecall() {
		return scoreOnRecall;
	}

	public void setScoreOnRecall(boolean scoreOnRecall) {
		this.scoreOnRecall = scoreOnRecall;
	}

	public boolean isDonotrecord() {
		return donotrecord;
	}

	public void setDonotrecord(boolean donotrecord) {
		this.donotrecord = donotrecord;
	}

	private boolean returnTotalCorrect;
	private boolean returnCorrectAnswer;
	private boolean returnFeedback;
	private boolean feedbackOnRecall;
	private boolean emailOwner;
	private boolean showStatistics;
	private boolean correctOnRecall;
	private boolean scoreOnRecall;
	private boolean donotrecord;
	
	public tp_actionUtil() {}
	
	public tp_actionUtil(  DataInputStream theInput, int format ) 
	 {
		
	try {
		returnScore= theInput.readBoolean();
		returnTotalCorrect= theInput.readBoolean();
		returnCorrectAnswer= theInput.readBoolean();
		returnFeedback= theInput.readBoolean();

		if (format >= 39) {
			feedbackOnRecall= theInput.readBoolean();
			emailOwner= theInput.readBoolean();
			if (format >= 409) emailAnyone= theInput.readBoolean();
			if (format >= 412) emailList= theInput.readBoolean();
			showStatistics= theInput.readBoolean();
		}
		if (format >= 40) {
			correctOnRecall= theInput.readBoolean();
			scoreOnRecall= theInput.readBoolean();
		}
		if (format >= 44) {
			donotrecord= theInput.readBoolean();
		}
		if (format >= 412)  emailAddresses= theInput.readUTF();
	} catch (IOException e) {
		//throw new Exception( "IOException reading submission action" );
		System.out.println("###### IOException in tp_actionUtil(  DataInputStream theInput, int format )");
		e.printStackTrace();
	}
	printData_tp_actionUtil();
}

	public void printData_tp_actionUtil() {
		System.out.println("############ printing tp_actionUtil start################");
		System.out.println(" returnScore  : " + returnScore);
		System.out.println(" returnTotalCorrect  : " + returnTotalCorrect);
		System.out.println(" returnCorrectAnswer  : " + returnCorrectAnswer);
		System.out.println(" returnFeedback  : " + returnFeedback);
		System.out.println(" feedbackOnRecall  : " + feedbackOnRecall);
		System.out.println(" emailOwner  : " + emailOwner);
		System.out.println(" emailAnyone  : " + emailAnyone);
		System.out.println(" emailList  : " + emailList);
		System.out.println(" showStatistics  : " + showStatistics);
		System.out.println(" correctOnRecall  : " + correctOnRecall);
		System.out.println(" scoreOnRecall  : " + scoreOnRecall);
		System.out.println(" donotrecord  : " + donotrecord);
		System.out.println(" emailAddresses  : " + emailAddresses);
		System.out.println("############ printing tp_actionUtil end ################");
	}
}
