package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;



public class tp_feedback_actionUtil extends tp_actionUtil{
	public static int ACTION_NONE = 0;
	public static int ACTION_FEEDBACK = 1;
	public static int type = ACTION_NONE;
	
	private ArrayList<String> isHTML;
	private ArrayList<String> compares;
	private ArrayList<String> feedbacks;
	private ArrayList<String> scores;
	private ArrayList<String> whatValue;
	private ArrayList<String> stopComparing;
	
	public tp_feedback_actionUtil( DataInputStream theInput, int format ) 
	throws Exception
 {
	super( theInput, format );
	
	try {
		type= tp_actionUtil.ACTION_FEEDBACK;
		
		isHTML= new ArrayList<String>();
		compares= new ArrayList<String>();
		feedbacks= new ArrayList<String>();
		scores= new ArrayList<String>();
		whatValue= new ArrayList<String>();
		stopComparing= new ArrayList<String>();
		
		int count= theInput.readInt();
		for (int i=0 ; i<count ; i++) {
			isHTML.add( theInput.readUTF() );
			compares.add( theInput.readUTF() );
			feedbacks.add( theInput.readUTF() );
			scores.add( theInput.readUTF() );
			whatValue.add( theInput.readUTF() );
			stopComparing.add( theInput.readUTF() );
		}

	} catch (IOException e) {
		e.printStackTrace();
		throw (new Exception( "IOException reading tp_feedback_action" ) );
		
	}
	printData_tp_actionUtil();
	print_tp_feedback_actionUtil();
}

	private void print_tp_feedback_actionUtil() {
		System.out.println("############ priniting tp_feedback_actionUtil starts ################");
		
		System.out.println(" printing isHTML....");
		for(String isHTMLStr : isHTML){
			System.out.println("isHTML : " + isHTMLStr);
		}
		
		System.out.println(" printing compares....");
		for(String isHTMLStr : compares){
			System.out.println("compares : " + isHTMLStr);
		}
		
		System.out.println(" printing feedbacks....");
		for(String isHTMLStr : feedbacks){
			System.out.println("feedbacks : " + isHTMLStr);
		}
		
		System.out.println(" printing scores....");
		for(String isHTMLStr : scores){
			System.out.println("scores : " + isHTMLStr);
		}
		
		System.out.println(" printing whatValue....");
		for(String isHTMLStr : whatValue){
			System.out.println("whatValue : " + isHTMLStr);
		}
		
		System.out.println(" printing stopComparing....");
		for(String isHTMLStr : stopComparing){
			System.out.println("stopComparing : " + isHTMLStr);
		}
		
		System.out.println("############ printing tp_feedback_actionUtil ends ################");
	}

	public ArrayList getIsHTML() {
		return isHTML;
	}

	public void setIsHTML(ArrayList isHTML) {
		this.isHTML = isHTML;
	}

	public ArrayList getCompares() {
		return compares;
	}

	public void setCompares(ArrayList compares) {
		this.compares = compares;
	}

	public ArrayList getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(ArrayList feedbacks) {
		this.feedbacks = feedbacks;
	}

	public ArrayList getScores() {
		return scores;
	}

	public void setScores(ArrayList scores) {
		this.scores = scores;
	}

	public ArrayList getWhatValue() {
		return whatValue;
	}

	public void setWhatValue(ArrayList whatValue) {
		this.whatValue = whatValue;
	}

	public ArrayList getStopComparing() {
		return stopComparing;
	}

	public void setStopComparing(ArrayList stopComparing) {
		this.stopComparing = stopComparing;
	}


}
