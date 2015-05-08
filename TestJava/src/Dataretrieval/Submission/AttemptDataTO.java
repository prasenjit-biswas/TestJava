package Dataretrieval.Submission;

import java.sql.RowId;
import java.util.concurrent.ConcurrentHashMap;

public class AttemptDataTO {
	private RowId rowID;
	private long attemptDataPK = 0l;
	private long attemptPK = 0l;
	private String testID = "";
	private String activityID = "";
	private String attemptNo = "";
	private String userID = "";
	private String sectionID = "";
	private String questionID = "";
	private long itemScore = 0l;
	private ConcurrentHashMap params = null;
	private ConcurrentHashMap originalParams = null;
	private long maxPoints = 0l;
	private String formulaAnswer = "";
	private byte[] recordedValue = null;
	private byte[] followup = null;
	private byte[] feedback = null;
	private byte[] html = null;
	private byte[] instructorComment = null;
	private byte[] userResponse = null;
	private long createdTime = 0l;
	private long updatedTime = 0l;
	
	
	public RowId getRowID() {
		return rowID;
	}


	public void setRowID(RowId rowID) {
		this.rowID = rowID;
	}
	
	
	public long getAttemptDataPK() {
		return attemptDataPK;
	}


	public void setAttemptDataPK(long attemptDataPK) {
		this.attemptDataPK = attemptDataPK;
	}


	public long getAttemptPK() {
		return attemptPK;
	}


	public void setAttemptPK(long attemptPK) {
		this.attemptPK = attemptPK;
	}


	public String getTestID() {
		return testID;
	}


	public void setTestID(String testID) {
		this.testID = testID;
	}


	public String getActivityID() {
		return activityID;
	}


	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}


	public String getAttemptNo() {
		return attemptNo;
	}


	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getSectionID() {
		return sectionID;
	}


	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}


	public String getQuestionID() {
		return questionID;
	}


	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}


	public long getItemScore() {
		return itemScore;
	}


	public void setItemScore(long itemScore) {
		this.itemScore = itemScore;
	}


	public ConcurrentHashMap getParams() {
		return params;
	}


	public void setParams(ConcurrentHashMap params) {
		this.params = params;
	}


	public long getMaxPoints() {
		return maxPoints;
	}


	public void setMaxPoints(long maxPoints) {
		this.maxPoints = maxPoints;
	}


	public String getFormulaAnswer() {
		return formulaAnswer;
	}


	public void setFormulaAnswer(String formulaAnswer) {
		this.formulaAnswer = formulaAnswer;
	}


	public byte[] getRecordedValue() {
		return recordedValue;
	}


	public void setRecordedValue(byte[] recordedValue) {
		this.recordedValue = recordedValue;
	}


	public byte[] getFollowup() {
		return followup;
	}


	public void setFollowup(byte[] followup) {
		this.followup = followup;
	}


	public byte[] getFeedback() {
		return feedback;
	}


	public void setFeedback(byte[] feedback) {
		this.feedback = feedback;
	}


	public byte[] getHtml() {
		return html;
	}


	public void setHtml(byte[] html) {
		this.html = html;
	}


	public byte[] getInstructorComment() {
		return instructorComment;
	}


	public void setInstructorComment(byte[] instructorComment) {
		this.instructorComment = instructorComment;
	}


	public byte[] getUserResponse() {
		return userResponse;
	}


	public void setUserResponse(byte[] userResponse) {
		this.userResponse = userResponse;
	}


	public long getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}


	public long getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" AttemptPK : ").append(attemptPK).append("\nTestID : ").append(testID).append("\nActivityID : ").append(
				activityID).append("\nSectionID : ").append(sectionID).append(
				"\nAttemptNO : ").append(attemptNo).append("\nUserID : ").append(
				userID);
		return sb.toString();
	}


	public ConcurrentHashMap getOriginalParams() {
		return originalParams;
	}


	public void setOriginalParams(ConcurrentHashMap originalParams) {
		this.originalParams = originalParams;
	}
}
