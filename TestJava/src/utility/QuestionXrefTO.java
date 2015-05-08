package com.mcgrawhill.ezto.utilities;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestionXrefTO {

	private String questionID;
	private String testID;
	private int questionType;
	private String questionTypeIdentifier;
	private int localID;
	private String source;
	private String points;
	private String refTag;
	private Date creationTime;
	private Date modifiedTime;
	private String createdBy;
	private String modifiedBy;
	private String modified;
	private String sourceInfo;
	private String maxpoint;
	private String customQuestionID;
	private String customQuestionRebuild;
	
	private String selectionTitle;
	private String displayType;
	private String actualType;
	private String hasFormula;
	private int lines;
	private String shortanswer;
	private Map<String, String> questionProperties ;
	//private String flashType;
	//private String instructorInfo;
	private String localRandoms;
	//private String iterationlevel;
	private String isChat;
	private String isChemDraw;
	private List<Map<String, String>> contentLinks;
	private List<Map<String, String>> answers;
	private String sourceTestID;
	private String sourceQuestionID;
	private String pickerXML;
	
	public QuestionXrefTO(){
		questionID = "";
		testID = "";
		questionType =0;
		questionTypeIdentifier = "";
		localID =0;
		source = "";
		points = "";
		refTag = "";
		creationTime = null;
		modifiedTime = null;
		createdBy = "";
		modified = "";
		sourceInfo = "";
		maxpoint = "";
		customQuestionID = "";
		customQuestionRebuild ="";
		
		selectionTitle = "";
		displayType = "";
		actualType = "";
		hasFormula = "";
		lines = 0;
		shortanswer = "";
		questionProperties = new HashMap<String, String>() ;
		//flashType = "";;
		//instructorInfo = "";;
		localRandoms = "";
		//iterationlevel = "";
		isChat = "";
		isChemDraw = "";
		contentLinks = new ArrayList<Map<String, String>>();
		answers = new ArrayList<Map<String, String>>();
		pickerXML = "";
	
	}
	
	public String getPickerXML() {
		return pickerXML;
	}

	public void setPickerXML(String pickerXML) {
		this.pickerXML = pickerXML;
	}

	public String getCustomQuestionID() {
		return customQuestionID;
	}

	public void setCustomQuestionID(String customQuestionID) {
		this.customQuestionID = customQuestionID;
	}

	public String getCustomQuestionRebuild() {
		return customQuestionRebuild;
	}

	public void setCustomQuestionRebuild(String customQuestionRebuild) {
		this.customQuestionRebuild = customQuestionRebuild;
	}

	public String getSourceTestID() {
		return sourceTestID;
	}

	public void setSourceTestID(String sourceTestID) {
		this.sourceTestID = sourceTestID;
	}

	public String getSourceQuestionID() {
		return sourceQuestionID;
	}

	public void setSourceQuestionID(String sourceQuestionID) {
		this.sourceQuestionID = sourceQuestionID;
	}

	public String getSelectionTitle() {
		return selectionTitle;
	}

	public void setSelectionTitle(String selectionTitle) {
		this.selectionTitle = selectionTitle;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getActualType() {
		return actualType;
	}

	public void setActualType(String actualType) {
		this.actualType = actualType;
	}

	public String getHasFormula() {
		return hasFormula;
	}

	public void setHasFormula(String hasFormula) {
		this.hasFormula = hasFormula;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public String getShortanswer() {
		return shortanswer;
	}

	public void setShortanswer(String shortanswer) {
		this.shortanswer = shortanswer;
	}

	public Map<String, String> getQuestionProperties() {
		return questionProperties;
	}

	public void setQuestionProperties(Map<String, String> questionProperties) {
		this.questionProperties = questionProperties;
	}

	/*public String getFlashType() {
		return flashType;
	}

	public void setFlashType(String flashType) {
		this.flashType = flashType;
	}

	public String getInstructorInfo() {
		return instructorInfo;
	}
	*/

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
	public void setPointsBD(BigDecimal points) {
		setPoints(points.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}
	
	public int getPoints(int defaultValue) {
		if(points != null && !("").equals(points)){
			return Integer.parseInt(points);
		} else {
			return defaultValue;
		}
	}
   
	public BigDecimal getPoints(BigDecimal defaultValue) {
		if(points != null && !("").equals(points)){
			return new BigDecimal(points).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			return defaultValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	
	/*
	public void setInstructorInfo(String instructorInfo) {
		this.instructorInfo = instructorInfo;
	}
	*/
	public String getLocalRandoms() {
		return localRandoms;
	}

	public void setLocalRandoms(String localRandoms) {
		this.localRandoms = localRandoms;
	}
	/*
	public String getIterationlevel() {
		return iterationlevel;
	}

	public void setIterationlevel(String iterationlevel) {
		this.iterationlevel = iterationlevel;
	}
	*/
	public String getIsChat() {
		return isChat;
	}

	public void setIsChat(String isChat) {
		this.isChat = isChat;
	}

	public String getIsChemDraw() {
		return isChemDraw;
	}

	public void setIsChemDraw(String isChemDraw) {
		this.isChemDraw = isChemDraw;
	}

	public List<Map<String, String>> getContentLinks() {
		return contentLinks;
	}

	public void setContentLinks(List<Map<String, String>> contentLinks) {
		this.contentLinks = contentLinks;
	}

	public List<Map<String, String>> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Map<String, String>> answers) {
		this.answers = answers;
	}

	public String getMaxpoint() {
		if(("").equals(maxpoint)){
			maxpoint = null;
		}
		return maxpoint;
	}
	public void setMaxpoint(String maxpoint) {
		this.maxpoint = maxpoint;
	}
	public String getSourceInfo() {
		return sourceInfo;
	}
	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getQuestionTypeIdentifier() {
		return questionTypeIdentifier;
	}
	public void setQuestionTypeIdentifier(String questionTypeIdentifier) {
		this.questionTypeIdentifier = questionTypeIdentifier;
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
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	/*public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}*/
	public int getLocalID() {
		return localID;
	}

	public void setLocalID(int localID) {
		this.localID = localID;
	}

	public String getRefTag() {
		return refTag;
	}
	public void setRefTag(String refTag) {
		this.refTag = refTag;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	
}
