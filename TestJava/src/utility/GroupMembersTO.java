package utility;

public class GroupMembersTO{
	private long groupID;
	private long bankID;
	private long orderID;
	private String questionID;
	private String testID;
	private String actualQuestionID;
	private boolean valid;
	
	public long getGroupID() {
		return groupID;
	}
	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}
	public long getBankID() {
		return bankID;
	}
	public void setBankID(long bankID) {
		this.bankID = bankID;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getTestID() {
		return testID;
	}
	public void setTestID(String testID) {
		this.testID = testID;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getActualQuestionID() {
		return actualQuestionID;
	}
	public void setActualQuestionID(String actualQuestionID) {
		this.actualQuestionID = actualQuestionID;
	}
	
	@Override
	public String toString() {
		return "groupID - " + groupID + 
			   " , bankID - " + bankID + 
			   " , orderID - " + orderID + 
			   " , questionID - " + questionID + 
			   " , testID - " + testID + 
			   " , actualQuestionID - " + actualQuestionID;
	}
	
}