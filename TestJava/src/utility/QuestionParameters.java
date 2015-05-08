package com.mcgrawhill.ezto.utilities;

import java.util.Map;
import java.util.Vector;

public class QuestionParameters {
	private long attemptDataPK;
	private String questionID = "";
	private int points = 0;
	private int pointsMax = 0;
	private String correctFormulaAnswer = "";
	private Vector recordedValue;
	private String followupValue = "";
	private boolean followup = false;
	private Vector feedback;
	private Vector userResponse;
	private String html = "";
	private String comment = "";
	private Map<String,String> questionParameters;
	private long elapsed = 0l;
	private String manGraded = "";//to hold the manualgradingr6b status
	

	public long getAttemptDataPK() {
		return attemptDataPK;
	}

	public void setAttemptDataPK(long attemptDataPK) {
		this.attemptDataPK = attemptDataPK;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPointsMax() {
		return pointsMax;
	}
	public void setPointsMax(int pointsMax) {
		this.pointsMax = pointsMax;
	}
	public String getCorrectFormulaAnswer() {
		return correctFormulaAnswer;
	}
	public void setCorrectFormulaAnswer(String correctFormulaAnswer) {
		this.correctFormulaAnswer = correctFormulaAnswer;
	}
	public Vector getRecordedValue() {
		if(recordedValue == null){
			recordedValue = new Vector();
		}
		return recordedValue;
	}
	public void setRecordedValue(Vector recordedValue) {
		this.recordedValue = recordedValue;
	}
	public String getFollowupValue() {
		if(followupValue == null){
			followupValue = "";
		}
		return followupValue;
	}
	public void setFollowupValue(String followupValue) {
		this.followupValue = followupValue;
	}
	public boolean hasFollowup() {
		return followup;
	}
	public void setFollowup(boolean followup) {
		this.followup = followup;
	}
	public Vector getFeedback() {
		if(feedback == null){
			feedback = new Vector();
		}
		return feedback;
	}
	public void setFeedback(Vector feedback) {
		this.feedback = feedback;
	}
	public Vector getUserResponse() {
		if(userResponse == null){
			userResponse = new Vector();
		}
		return userResponse;
	}
	public void setUserResponse(Vector userResponse) {
		this.userResponse = userResponse;
	}
	public String getHtml() {
		if(html == null){
			html = "";
		}
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getComment() {
		if(comment == null){
			comment = "";
		}
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Map<String,String> getQuestionParameters() {
		if(questionParameters == null){
			questionParameters = new CustomMap<String, String>();
		}
		return questionParameters;
	}
	public void setQuestionParameters(Map<String,String> questionParameters) {
		this.questionParameters = questionParameters;
	}
	public long getElapsed() {
		return elapsed;
	}
	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}
	public String getManGraded() {
		return manGraded;
	}

	public void setManGraded(String manGraded) {
		this.manGraded = manGraded;
	}
}
