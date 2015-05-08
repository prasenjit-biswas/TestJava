package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Dataretrieval.Question.Question;
import Dataretrieval.util.OracleConnection;

public class MediaConsumerEntry {

	public static void main(String[] args) {
	   try{
		String testid = args[0];
		Connection con = OracleConnection.getConnection();
		System.out.println("Working for Testid : "+testid);
		List<String> questionIdList = getQuestionIDs(con, testid);
		TestFullCredit.setAutoCommit(con, false);
		getMediaNameFromQblob(con, questionIdList,testid);
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	}
	
	public static List<String> getQuestionIDs(Connection con, String testid) throws Exception{
		ResultSet rs = null;
		List<String> questionIdList = new ArrayList<String>();
	    PreparedStatement pstmt = con.prepareStatement("SELECT questionid from test_question_xref where testid = ?");
	    pstmt.setString(1, testid);
	    rs = pstmt.executeQuery();
	    while(rs.next()){
	    	String questionid = rs.getString("questionid");
	    	//System.out.println(" question Id s : "+questionid);
	    	questionIdList.add(questionid);
	    }
		return questionIdList;
	}

	
   public static Map<String, String> getMediaNameFromQblob(Connection con, 
		   												   List<String> questionIdList
		   												   ,String testid ) throws Exception{
	   Iterator<String> itr = questionIdList.iterator();
	   Map<String,List> questionMediaMap = new HashMap<String,List>(); 
	   PreparedStatement pstmt = null;
	   PreparedStatement updatePstmt = null;
	   ResultSet rs = null;
	   try{
		   String mediaName = "";
		   while(itr.hasNext()){
			  String questionid = itr.next();
			  Question qBlob =  Question.getQuestion(con, questionid);
		      String qtext = qBlob.getQtext();
		      
		      if(qtext!= null && !("").equals(qtext) && qtext.indexOf("%media:")!= -1 ){
		    	  String firstName = qtext.substring(qtext.indexOf("%media:") );
		    	  firstName = firstName.substring(firstName.indexOf(":")+1);
		    	  //System.out.println(" firstName "+firstName);
		    	  String seconName = firstName.substring(0,firstName.indexOf("%"));
		    	  String totalMediaName = seconName;//.substring(firstName.indexOf(":")+1);
			      //System.out.println(" mediaName : "+totalMediaName);
			      String[] mediaArray = totalMediaName.split(",");
			      
			      for(int i=0;i<mediaArray.length;i++){
			    	  mediaName = mediaArray[i];
			    	  pstmt = con.prepareStatement("SELECT mediaID, name FROM mediaconsumers WHERE  testID= ? AND consumerID= ? AND name like ? ");
			    	  pstmt.setString(1, testid);
			    	  pstmt.setString(2, questionid);
			    	  pstmt.setString(3, "%"+mediaName+"%");
			    	  rs = pstmt.executeQuery(); 
			          if(rs.next()){
			            //it means it matches the media name, we are doing anything here.	 
			          }else{
			        	  
			        	 System.out.println(" Select * from mediaConsumers WHERE testid = '"+testid+"' AND consumerid = '"+questionid+"' AND name like '%"+mediaName+"%'");
			        	 String updateQuery = new String (" update mediaconsumers mc set name = '"+mediaName+"' WHERE testid = '"+testid+"' " +
			        	 		                              "AND mediaid = (SELECT " +
			        	 		                                                "mc.mediaid " +
			        	 		                                                "FROM mediaconsumers mc " +
			        	 		                                                ",(SELECT tr.testid from test_question_xref tr where tr.questionid = '"+questionid+"' AND tr.testid <> '"+testid+"') tr " +
			        	 		                                                "WHERE " +
			        	 		                                                "mc.testid = tr.testid " +
			        	 		                                                "AND mc.name = '"+mediaName+"' " +
			        	 		                                                "AND mc.consumerid = '"+questionid+"' )");
			            System.out.println(" updateQuery : "+updateQuery);
			          }
			      }
		      }else{
		    	  
		      }
		   }
	   }catch(Exception ex){
		   ex.printStackTrace();
		   throw ex;
	   }finally{
		   if(pstmt!= null){
			   pstmt.close();
		   }
		   if(rs != null){
			   rs.close();
		   }
	   }
	   return null;
   }














}
