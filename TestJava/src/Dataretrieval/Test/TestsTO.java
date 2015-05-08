package Dataretrieval.Test;

import java.io.InputStream;
import java.io.Serializable;

public class TestsTO implements Serializable{
	private long modified;
	private String testID;
	private InputStream data;
	private long beginDate;
	private long endDate;
	private long recallDate;
	private long elapsed;
	private long indexID;
	private String owner;
	private long oemID;
	private String url;
	private long bankID;
	private long creation;
	private long managers;
	private long participants;
	private long computers;
	
	private String title;
	private long format;
	private long submissionCount;
	private long perfectScores;
	private long maxPts;
	private long minPts;
	private long enabled;
	private long hidden;
	private long indexOrder;
	private float sumPts;
	private float sumPct;
	private float squaresPts;
	private float squaresPct;
	
	public String getTestID() {
		return testID;
	}
	public void setTestID(String testID) {
		this.testID = testID;
	}
	public InputStream getData() {
		return data;
	}
	public void setData(InputStream data) {
		this.data = data;
	}
	public long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public long getRecallDate() {
		return recallDate;
	}
	public void setRecallDate(long recallDate) {
		this.recallDate = recallDate;
	}
	public long getElapsed() {
		return elapsed;
	}
	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}
	public long getIndexID() {
		return indexID;
	}
	public void setIndexID(long indexID) {
		this.indexID = indexID;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getBankID() {
		return bankID;
	}
	public void setBankID(long bankID) {
		this.bankID = bankID;
	}
	public long getCreation() {
		return creation;
	}
	public void setCreation(long creation) {
		this.creation = creation;
	}
	public long getManagers() {
		return managers;
	}
	public void setManagers(long managers) {
		this.managers = managers;
	}
	public long getParticipants() {
		return participants;
	}
	public void setParticipants(long participants) {
		this.participants = participants;
	}
	public long getComputers() {
		return computers;
	}
	public void setComputers(long computers) {
		this.computers = computers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getFormat() {
		return format;
	}
	public void setFormat(long format) {
		this.format = format;
	}
	public long getSubmissionCount() {
		return submissionCount;
	}
	public void setSubmissionCount(long submissionCount) {
		this.submissionCount = submissionCount;
	}
	public long getPerfectScores() {
		return perfectScores;
	}
	public void setPerfectScores(long perfectScores) {
		this.perfectScores = perfectScores;
	}
	public long getMaxPts() {
		return maxPts;
	}
	public void setMaxPts(long maxPts) {
		this.maxPts = maxPts;
	}
	public long getMinPts() {
		return minPts;
	}
	public void setMinPts(long minPts) {
		this.minPts = minPts;
	}
	public long getEnabled() {
		return enabled;
	}
	public void setEnabled(long enabled) {
		this.enabled = enabled;
	}
	public long getHidden() {
		return hidden;
	}
	public void setHidden(long hidden) {
		this.hidden = hidden;
	}
	public long getIndexOrder() {
		return indexOrder;
	}
	public void setIndexOrder(long indexOrder) {
		this.indexOrder = indexOrder;
	}
	public float getSumPts() {
		return sumPts;
	}
	public void setSumPts(float sumPts) {
		this.sumPts = sumPts;
	}
	public float getSumPct() {
		return sumPct;
	}
	public void setSumPct(float sumPct) {
		this.sumPct = sumPct;
	}
	public float getSquaresPts() {
		return squaresPts;
	}
	public void setSquaresPts(float squaresPts) {
		this.squaresPts = squaresPts;
	}
	public float getSquaresPct() {
		return squaresPct;
	}
	public void setSquaresPct(float squaresPct) {
		this.squaresPct = squaresPct;
	}
	public long getModified() {
		return modified;
	}
	public void setModified(long modified) {
		this.modified = modified;
	}
	public long getOemID() {
		return oemID;
	}
	public void setOemID(long oemID) {
		this.oemID = oemID;
	}	
}
