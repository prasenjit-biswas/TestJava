package utility;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;



public class PartialTO {

	private long attemptPK;
	private String testID = "";
	private String userID = "";
	private String studentID = "";
	private String activityID = "";
	private String sectionID = "";
	private String attemptNo = "";
	private long submissionID = 0l;
	private boolean submission = false;
	private int score = 0;
	private int maxScore = 0;
	private int totalCorrect = 0;
	private int percentageScore = 0;
	private String scoreString = "";
	private long submissionTime = 0l;
	private long elapsedTime = 0l;
	private String timeZone = "";
	private String dateFormat = "";
	private ConcurrentHashMap groupEvaluations;
	private Map<String,String> testParameter;
	private QuestionTO questionTO;
	
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	public String getSectionID() {
		return sectionID;
	}

	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}

	public String getAttemptNo() {
		return attemptNo;
	}

	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}

	public long getSubmissionID() {
		return submissionID;
	}
	public void setSubmissionID(long submissionID) {
		this.submissionID = submissionID;
	}
	public boolean isSubmission() {
		return submission;
	}
	public void setSubmission(boolean submission) {
		this.submission = submission;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getTotalCorrect() {
		return totalCorrect;
	}
	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}
	public int getPercentageScore() {
		return percentageScore;
	}
	public void setPercentageScore(int percentageScore) {
		this.percentageScore = percentageScore;
	}
	public String getScoreString() {
		return scoreString;
	}
	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}
	public long getSubmissionTime() {
		return submissionTime;
	}
	public void setSubmissionTime(long submissionTime) {
		this.submissionTime = submissionTime;
	}
	public long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public ConcurrentHashMap getGroupEvaluations() {
		if(groupEvaluations == null){
			groupEvaluations = new ConcurrentHashMap();
		}
		return groupEvaluations;
	}
	public void setGroupEvaluations(ConcurrentHashMap groupEvaluations) {
		this.groupEvaluations = groupEvaluations;
	}

	

	public Map<String, String> getTestParameter() {
		if(testParameter == null){
			testParameter = new CustomMap<String, String>();
		}
		return testParameter;
	}
	public void setTestParameter(Map<String, String> testParameter) throws Exception {
		if(!(testParameter instanceof CustomMap)){
			throw new Exception("Object of CustomMap should be passed to PartialTO.setTestParamater()");
		}
		this.testParameter = testParameter;
	}
	public QuestionTO getQuestionTO() {
		if(questionTO == null){
			questionTO = new QuestionTO();
		}
		return questionTO;
	}
	public void setQuestionTO(QuestionTO questionTO) {
		this.questionTO = questionTO;
	}

	@Override
	public String toString() {
		System.out.println(" SUBMISSION ID : " + submissionID);
		System.out.println(" IS SUBMISSION : " + submission);
		if (testParameter != null) {
			Iterator<String> iterator = testParameter.keySet().iterator();
			System.out.println(" PRINTING FOR TEST PARAMS");
			while (iterator.hasNext()) {
				String key = iterator.next();
				System.out.println("Key : " + key + "Value : " + testParameter.get(key));
			}
		}
		if (questionTO != null) {
			Map<String, QuestionParameters> questionMap = questionTO.getQuestionMap();
			Iterator<String> iterator = questionMap.keySet().iterator();
			System.out.println(" PRINTING FOR QUESTION PARAMS");
			while (iterator.hasNext()) {
				String key = iterator.next();
				CustomMap<String, String> questionParam = (CustomMap<String, String>)questionMap.get(key).getQuestionParameters();
				Iterator<String> iter = questionParam.keySet().iterator();
				while(iter.hasNext()){
					String key2 = iter.next();
					System.out.println("Key : " + key2 + " Value : " + questionParam.getParam(key2));
				}
			}
		}
		return super.toString();
	}
	
	public String textDump(){
		StringBuilder stringBuilder = new StringBuilder();
		CustomMap<String, String> testParam = (CustomMap<String, String>)this.getTestParameter();
		//TreeMap<String, String> sortedMap = new TreeMap<String, String>(testParam);
		Iterator<Map.Entry<String, String>> iterator = testParam.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, String> entrySet = iterator.next();
			stringBuilder.append(entrySet);
			stringBuilder.append("\n");
		}
		Map<String, QuestionParameters> questMap = this.getQuestionTO().getQuestionMap();
		Iterator<String> iteratorQuestMap = this.getQuestionTO().getQuestionMap().keySet().iterator();
		while(iteratorQuestMap.hasNext()){
			String key = iteratorQuestMap.next();
			CustomMap<String, String> questParamMap = (CustomMap<String, String>)questMap.get(key).getQuestionParameters();
			Iterator<Map.Entry<String, String>> iteratorQuestParam = questParamMap.entrySet().iterator();
			while(iteratorQuestParam.hasNext()){
				Map.Entry<String, String> questionParamEntrySet = iteratorQuestParam.next();
				stringBuilder.append(questionParamEntrySet);
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}
}
