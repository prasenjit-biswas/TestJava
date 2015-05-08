package utility;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Dataretrieval.util.OracleConnection;

import com.mcgrawhill.ezto.tp_requestHandler;
//import com.mcgrawhill.ezto.integration.hm_grading_r6b;
import com.mcgrawhill.ezto.sql.tp_sql;
import com.mcgrawhill.ezto.utilities.PartialTO;
import com.mcgrawhill.ezto.utilities.QuestionParameters;

public class JsonParsing {
    final static int studentCountPerpage = 2;
	public final static String QUESTIONS = "questions";
	public final static String Q_ID = "qid";
	public final static String EMPTY_STRING = "";
    
	public static void main(String[] args) throws Exception{
	
		String jsonString = "{'workflow' : 'by question','ezid' : '123456'," +
				           //" 'startwith' : '222556'," +
				           "'questions' : [{'qid' : '556677','students' : [{'userid' : '111111','name' : 'Monica Gellar','attempts' : ['123456','123412']},{ 'userid' : '222222','name' : 'Ross Gellar','attempts' : ['222222',]},{'userid' : '333333','name' : 'Matt LeBlanc','attempts' : ['111111','321321','234234']},]}," +
				           "               {'qid' : '222555','students' : [{'userid' : '444444','name' : 'Monica Gellar','attempts' : ['999888']},{'userid' : '555555','name' : 'Matt LeBlanc','attempts' : ['404040','808080','606060']},]}," +
				           "               {'qid' : '222556','students' : [{'userid' : '777777','name' : 'Prasenjit Biswas1','attempts' : ['999888']},{'userid' : '888888','name' : 'Prasenjit Biswas2','attempts' : ['404040','808080','606060']},]} ]}";
		
		
		jsonString = "{'ezid' : '13252698009612566',"+
						"'startwith' : '13252698009363001',"+
						"'questions' : [{'qid' : '13252698009363001','students':[{'userid' : '23377','name' : 'S Four','attempts' : ['13252698011862689']},{'userid' : '23378','name' : 'S Three','attempts' : ['13252698011862648',]},{'userid' : '23384','name' : 'S Nine','attempts' : ['13252698011664597']},]},]}]," +
						"'role' : 'instructorPrimary',"+
						"'instructorId' : '13634',"+
						"'workflow' : 'by question',"+
						"'instructor_email' : 'sujoy.b@tcs.com',"+
						"'instructor_name' : 'Sujoy Bhattacharya',"+
						"'p_palette' : 'no'"+
						"};";
		
		jsonString = "{'workflow' : 'by question','ezid' : '13252698011862622','startwith' : '13252698012013118','questions' : [{'qid' : '13252698012013118','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+      
                                                                                                                           "{'qid' : '13252698012013120','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+   
															   "{'qid' : '13252698012013121','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+    
															   "{'qid' : '13252698012013123','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+    
															   "{'qid' : '13252698012013124','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013125','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013126','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013127','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013128','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013129','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013130','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013131','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013132','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013133','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013134','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}"+
															   "{'qid' : '13252698012013135','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},]}],"+
															   "'role' : 'instructorPrimary','instructorId' : '13634','instructor_email' : 'sujoy.b@tcs.com','instructor_name' : 'Sujoy Bhattacharya','p_palette' : 'no'};";
		
jsonString = 
"{'workflow' : 'by question','ezid' : '13252698011862622','questions' :" +
" [{'qid' : '13252698012013118','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+      
"{'qid' : '13252698012013120','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+   
"{'qid' : '13252698012013121','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+    
"{'qid' : '13252698012013123','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+    
"{'qid' : '13252698012013124','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013125','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013126','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013127','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013128','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013129','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013130','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013131','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013132','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013133','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013134','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},"+
"{'qid' : '13252698012013135','students' : [{'userid':'23377','name' : 'S Four','attempts' : ['13252698012013305']},{'userid':'23378','name' : 'S Three','attempts' : ['13252698012013306']},{'userid':'23379','name' : 'Two, S','attempts' : ['13252698012013502']},{'userid':'23382','name' : 'Five, S','attempts' : ['13252698012013523']},{'userid':'13637','name' : 'Chakraborty, Only Tapas','attempts' : ['13252698012013433']},{'userid':'23384','name' : 'S Nine','attempts' : ['13252698012013411']},]},],"+
"'role' : 'instructorPrimary','instructorId' : '13634','startwith' : '13252698012013132','instructor_email' : 'sujoy.b@tcs.com','instructor_name' : 'Sujoy Bhattacharya','p_palette' : 'no'};";

		
		/*StringBuilder questionList = new StringBuilder();
		System.out.println(jsonString);
		
		String studentForThisPage = "23381@!S Seven@@!13252698013013243";
		
		if(studentForThisPage != null && !(EMPTY_STRING).equals(studentForThisPage)){
	    	String[] studentArr = studentForThisPage.split("~");
	    	for(int i=0;i<studentArr.length;i++){
	    		List<String> attemptList = new ArrayList<String>();
	    		String studentDt = studentArr[i];
	    		String[] students = studentDt.split("@!");
	    		String userId = students[0];
	    		String name = students[1];
	    		//userIdList.add(userId);
	    		//submissionIdListforDiffuser.add(students[2]);
	    		for(int j=2; j<students.length;j++){
	    			attemptList.add(students[j]);
	    		}
	    		//studentNameMap.put(userId, name);
	    		//studentAttemptMap.put(userId, attemptList);
	    	}
	    }*/
		

        JSONObject hello = new JSONObject();
        hello.put("500", "bjhbsdbsjd");
        System.out.println(hello);
        System.out.println("{\"500\":\"bjhbsdbsjd\"}");
		
		//StringBuilder showHtml = new StringBuilder();
		
		//getJsonDetail(jsonString);
		
		  /*showHtml.append("<select onChange=\"javascript: postData()\">");
	      showHtml.append("<option value=''>"+"-"+"</option>");
	      //for(int j=0;j<specificUserAttempts.size();j++){
	    	  //String submissionID = specificUserAttempts.get(j);
	    	  //if(!submissionID.equals(submissionId) ){
	    		  showHtml.append("<option value="+"1111111"+"#"+"s TWO"+">"+"</option>");
	    	  //}
	      //}
	      showHtml.append("</select>");
		
		  System.out.println("showHtml : "+showHtml);*/
		
		
		//getKeysFromJson(jsonString);
		// String qList = getQuestionList(jsonString,questionList);
		// System.out.println(" qlist --> "+qList);
		 
		 //System.out.println(getQposition(qList,"13252698012013124")); 
		//System.out.println("questionList "+questionList.toString());
		//StringBuilder processedQid = new StringBuilder("");
		//String questionID = "13252698009363001"; 
		//StringBuilder hello = new StringBuilder();
		//System.out.println("Hello : "+hello);
		//getReference(processedQid, questionID);
		//System.out.println(" processedQid : "+processedQid);
		//getJSONValue(jsonString);
			
		
		//String questionID = getCurrentQuestionID(jsonString);
		//StringBuilder paginationStr = new StringBuilder();
		//StringBuilder questionList = new StringBuilder();
		//System.out.println(" questionID : "+questionID);
		//constructMap(jsonString, questionID, paginationStr,questionList);
		//System.out.println(" paginationStr "+paginationStr);
		
		
		
		//printMap(questionMap);
		//System.out.println("questionList : "+questionList);
		//String[] questions = questionList.toString().split(",");
		//String previousQid = "";
		//String nextQid = "";
		/*for(int i =0; i<questions.length;i++){
			if(questions[i].equals(questionID)){
				if(i != 0){
					previousQid = questions[i-1];
				}
				if(i != questions.length-1){
					nextQid = questions[i+1];
				}
			}
		}
		System.out.println(" previousQid : "+previousQid);
		System.out.println(" currentQid : "+questionID);
		System.out.println(" nextQid : "+nextQid);*/
		
		/*List<String> submissionIdListforDiffuser = getStudentDtAndSubmissionIds(paginationStr.toString(), 1);
		String testid = "13252698009612566";
		getSubmissionR6bNew(testid, submissionIdListforDiffuser,questionID);*/
		
    }
	
	
	public static void getJsonDetail(String jsonString) throws Exception{
		JSONObject jsonObj = new JSONObject(jsonString);
		Iterator<String> jsonKeys = jsonObj.keys();
		while(jsonKeys.hasNext()){
			String paletteKey =jsonKeys.next(); 
			if("p_palette".equals(paletteKey)){
				System.out.println("Palette Value : "+jsonObj.get(paletteKey));
			}
		}
		if(jsonObj.has("role")){
			System.out.println(" Palette Value ->: "+jsonObj.getString("role"));
		}
	}
	
	
	public static String getQposition(String qList, String questionid){
		String statement = "";
		StringTokenizer questionToken = new StringTokenizer(qList, ",");
		int count = 0;
		int qPosition = 0;
		while(questionToken.hasMoreTokens()){
			String questionID = questionToken.nextToken();
			if(questionID != null && !("").equals(questionID) && (questionid).equals(questionID)){
				qPosition = count+1;   	
			}
			count++;
		}
		statement = qPosition+" of "+ count +" questions " ;
	  return statement;
	}
	
	public static void getKeysFromJson(String jsonString){
		try{
			StringBuilder workFlow = new StringBuilder();
			StringBuilder studentName = new StringBuilder();
			String questionID = "13252698012013121";
			JSONObject jsonObj = new JSONObject(jsonString);
			Iterator<String> jsonKeys = jsonObj.keys();
			String key = EMPTY_STRING;
			while(jsonKeys.hasNext()){
				key = jsonKeys.next();
				if(("workflow").equalsIgnoreCase(key)){
					workFlow.append(jsonObj.getString("workflow"));
				}
			    if((QUESTIONS).equalsIgnoreCase(key)){
			    	JSONArray ja = jsonObj.getJSONArray(QUESTIONS);
					for(int i=0;i<ja.length();i++){
						JSONObject jsonChild =(JSONObject)ja.get(i);
						String questionid = EMPTY_STRING;
						if(jsonChild.has(Q_ID)){
							questionid = jsonChild.getString(Q_ID);
						}
						if(!questionID.equals(questionid)){
						  continue;	
						}
						if(jsonChild.has("students")){
							JSONArray studentArray = jsonChild.getJSONArray("students");
							for(int j =0;j<studentArray.length();j++){
								if(!("").equals(studentName.toString())){
									studentName.append("~");	
								}
								JSONObject studentObj = (JSONObject)studentArray.get(j);
								if(studentObj.has("userid")){
									studentName.append(studentObj.getString("userid")).append("_");
								}
								if(studentObj.has("name")){
									studentName.append(studentObj.getString("name"));
								}
								if(studentObj.has("attempts")){
									JSONArray attemptArray = studentObj.getJSONArray("attempts");
									for(int k=0 ; k < attemptArray.length();k++){
										studentName.append("_").append(attemptArray.getString(k));
									}
								}
								
							}
						}
					}
			    }
			
			}
			
			System.out.println(" workFlow : "+workFlow);
			System.out.println(" studentName : "+studentName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	public static String getQuestionList( String jsonString, StringBuilder questionList )throws Exception{
		try{
		    String startWith = "";
			int totalQLength = 0;
			JSONObject jsonObj = new JSONObject(jsonString);
			if(jsonObj.has("startwith")){
				startWith = jsonObj.getString("startwith");
			}
			int questionPosition = -1;
			if(jsonObj.has(QUESTIONS)){
				JSONArray ja = jsonObj.getJSONArray(QUESTIONS);
				totalQLength = ja.length();
				for(int i=0;i<ja.length();i++){
					JSONObject jsonChild =(JSONObject)ja.get(i);
					String questionid = EMPTY_STRING;
					if(jsonChild.has(Q_ID)){
						questionid = jsonChild.getString(Q_ID);
						if(i!=0 && i<ja.length()){
							questionList.append(",");
						}
						questionList.append(questionid);
					}
				}
			}
			System.out.println("unprocessed qlist : "+questionList);
			List<String> beforeList = new ArrayList<String>();
			List<String> afterList = null;
			if(startWith != null && !("").equals(startWith)){	
				String[] qList = questionList.toString().split(",");
				for(int i =0;i<qList.length; i++ ){
					if(qList[i].equals(startWith)){
						System.out.println(" index position : "+i);
						afterList = new ArrayList<String>();
						afterList.add(qList[i]);
					}
					if(afterList == null || afterList.isEmpty()){
						beforeList.add(qList[i]);
					}else{
						if(!afterList.contains(qList[i]))afterList.add(qList[i]);
					}
				}
				
				System.out.println(" afterList "+afterList);
				System.out.println(" beforeList "+beforeList);
				if(!beforeList.isEmpty()){
				   for(int k=0;k<beforeList.size();k++){
					   afterList.add(beforeList.get(k)); 
				   }
				}
				if(afterList != null && !afterList.isEmpty()){
					questionList = new StringBuilder();
					for(int m=0;m<afterList.size();m++){
						if(m!=0 && m<afterList.size()){
							questionList.append(",");
						}
						questionList.append(afterList.get(m));
					}
				}
			}
			System.out.println(" processed qList : "+questionList);
		}catch(Exception ex){
           System.out.println(" Problem in getQuestionList "+ex.getMessage());			
		   ex.printStackTrace();
		}
		return questionList.toString();
	}
	/*
	  public static int getStudentsPerPageWithSB(tp_requestHandler theHandler,String jsonString,String questionID,List<String> qlist){
		  
		  String testId = "";
		  String questionId = "";
		  String sbQuestionId = "";
		  question theQ = null;
		  boolean isHeavyQuestion = false;
		  VectorAdapter worksheetAnswersList = null;
		  worksheet_answer worksheetAnswer = null;
		  int studentsPerPage = 2;
		  
		  try{
			  JSONObject jsonObj = new JSONObject(jsonString);
			   if(jsonObj.has("ezid")){
				   testId = jsonObj.getString("ezid");
			   }
			   //questionId = getCurrentQuestionID(jsonString);	
			   questionId = questionID ;
			   
		  }catch(Exception ex){
			  ex.printStackTrace();  		  
		  }
		  theQ = theHandler.currentTest.questions.getSQL(questionId);
		  if(theQ != null){
			  	if(theQ.type == question.QUESTION_TYPE_fillBlank 
			  			|| theQ.type == question.QUESTION_TYPE_ranking
			  			|| theQ.type == question.QUESTION_TYPE_matching
			  			|| theQ.type == question.QUESTION_TYPE_document){
			  		studentsPerPage = 5;
			  	}
			  	if(theQ.type == question.QUESTION_TYPE_trueFalse
			  			|| theQ.type == question.QUESTION_TYPE_yesNo
			  			|| theQ.type == question.QUESTION_TYPE_multipleChoice
			  			|| theQ.type == question.QUESTION_TYPE_checkAll){
			  		studentsPerPage = 20;
			  	}
			  
				if(theQ.type == question.QUESTION_TYPE_worksheet){
					worksheetAnswersList = ((worksheet) theQ).answers;
					for (int j=0; j<worksheetAnswersList.size(); j++){
						worksheetAnswer = (worksheet_answer)worksheetAnswersList.elementAt(j);
						if (worksheetAnswer.type == worksheet_answer.ANSWER_TYPE_flash 
								|| worksheetAnswer.type == worksheet_answer.ANSWER_TYPE_external) {
							isHeavyQuestion = true;
							break;
						}
					}
				} else if(theQ.type == question.QUESTION_TYPE_lsi){
					isHeavyQuestion = true;
				} else {
					isHeavyQuestion = false;
				}
				if(theQ.type != question.QUESTION_TYPE_sectionbreak && theQ.referenceTag != null && theQ.referenceTag.length() > 0){
					
					Enumeration theQs= theHandler.currentTest.questions.getEnumeration();
					while (theQs.hasMoreElements()) {
						question thisQ= (question)theQs.nextElement();
						if ((thisQ.type == question.QUESTION_TYPE_sectionbreak) && (theQ.referenceTag.equals(thisQ.referenceTag))){
							qlist.add(thisQ.sqlID);
							qlist.add(theQ.sqlID);
							studentsPerPage = 1;
							break;
						}
					}				
				}
				if(isHeavyQuestion){
					studentsPerPage = 1;
				}		  
		  } 		  
		  return studentsPerPage;
	  }
	
	
	
	
	
	
	public static void getJSONValue(String jsonString) throws Exception{
		JSONObject jObj = new JSONObject(jsonString) ;
		/*if(jObj.has("workflow")){
			System.out.println(" workflow "+jObj.getString("workflow"));
		}   end here
		Iterator itr = jObj.keys();
		while(itr.hasNext()){
			System.out.println(itr.next()); ;
		}
		
	}
	*/
	
	
	public static void getReference(StringBuilder processedQid,String questionID){
		//processedQid = new StringBuilder(); 
		processedQid.append(questionID);
		
	}
	
	
	
	
	public static Map <String,PartialTO> getSubmissionR6bNew(String testid,
														   List<String> submissionIdListforDiffuser,
														   String currentQid ) 
	throws Exception{
		
		Connection con = null;
		Map <String,PartialTO> userIDpartialTOMap= null;
		Map<Long,PartialTO> attempTpkPartialTOMap = null;;
		PartialTO partialTO = null;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		int qCount = 0;
		
		try{
				con = OracleConnection.getConnection();//theHandler.getConnection();
				StringBuilder query = new StringBuilder("SELECT * FROM attempt WHERE testid = ? ");
				if(submissionIdListforDiffuser !=null ){
				for(String submissionId : submissionIdListforDiffuser){
				if(qCount == 0){
				query.append(" AND ( submissionid = ?"); 
				}
				if(qCount > 0){
				query.append(" OR submissionid = ? ");
				}
				qCount++;
				}
				query.append(")");
				}
				System.out.println(" Attempt query : "+query);
				stmt= con.prepareStatement(query.toString());
				stmt.setString(1, testid);
				if(submissionIdListforDiffuser != null){
				for(int i = 0; i < submissionIdListforDiffuser.size(); i++){
				String theSubmission =submissionIdListforDiffuser.get(i);
				if (theSubmission == null) 
				{
				//theHandler.errorMessage("unable to complete transaction");
				return null ;
				}
				stmt.setString((i + 2), theSubmission);
				}
				}
				rs= stmt.executeQuery();
				attempTpkPartialTOMap = new HashMap<Long,PartialTO>();
				while(rs.next()){
				partialTO = new PartialTO();
				long submissionID = 0;
				
				partialTO.setAttemptPK(rs.getLong("attempt_pk"));
				submissionID = rs.getLong("submissionID");
				if(submissionID != 0){
				partialTO.setSubmission(true);
				}
				partialTO.setSubmissionID(rs.getLong("submissionID"));
				
				partialTO.setStudentID(rs.getString("userID"));
				partialTO.setActivityID(rs.getString("activityID"));
				partialTO.setAttemptNo(rs.getString("attemptno"));
				partialTO.setSectionID(rs.getString("sectionID"));
				
				partialTO.setScore(rs.getInt("totalscore"));
				partialTO.setMaxScore(rs.getInt("maxscore"));
				partialTO.setTotalCorrect(rs.getInt("totalcorrect"));
				partialTO.setPercentageScore(rs.getInt("pctscore"));
				partialTO.setSubmissionTime(rs.getLong("submissionTime"));
				partialTO.setElapsedTime(rs.getLong("elapsedTime"));
				
				byte[] theData = rs.getBytes("params");
				Map<String, String> testParam = tp_sql.mapFromStream(new ByteArrayInputStream(theData));
				partialTO.setTestParameter(testParam);
				partialTO.setGroupEvaluations(tp_sql.hashFromBlob(rs, "groupScores"));
				
				if(partialTO == null){
				return null;
				}
				attempTpkPartialTOMap.put(partialTO.getAttemptPK(), partialTO);
				}
				System.out.println("attempTpkPartialTOMap size : "+attempTpkPartialTOMap.size());
				
				userIDpartialTOMap = getManGradeR6bResponsesNew( con, attempTpkPartialTOMap, 
												currentQid, 
												testid);	
				
				return( userIDpartialTOMap );
		}catch(SQLException sqle){
		System.out.println("######### SQLException in getManGradeR6SubmissionNew");
		System.out.println("######### Test id : " + testid);
		throw sqle;
		}
		finally{
		tp_sql.releaseResources(stmt, rs);
		tp_sql.releaseResources(con);
		}
  }
 


  public static Map <String,PartialTO> getManGradeR6bResponsesNew ( Connection con, 
					   Map<Long,PartialTO> attempTpkPartialTOMap, 
                    String currentQid, 
                    String testid) 
  throws Exception{

		Map <String,PartialTO> userIDpartialTOMap= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int qCount = 0;
		try{
			StringBuilder queryBuilder = new StringBuilder("SELECT ad.* FROM attemptdata ad WHERE " +
			                                " ad.testid = ? AND ad.questionid = ?  ");
			Iterator<Long> attemptpkItr= attempTpkPartialTOMap.keySet().iterator();
			while(attemptpkItr.hasNext()){
			attemptpkItr.next();
			if(qCount == 0){
			queryBuilder.append(" AND ( ad.attempt_pk = ? "); 
			}
			if(qCount > 0){
			queryBuilder.append(" OR ad.attempt_pk = ? ");
			}
			qCount++;
			}
			queryBuilder.append(")");
			System.out.println("#### getManGradeR6Responses Query : " + queryBuilder);
			
			pstmt= con.prepareStatement(queryBuilder.toString());
			pstmt.setString(1, testid);
			pstmt.setString(2, currentQid);
			int queryCount =0;
			attemptpkItr = attempTpkPartialTOMap.keySet().iterator();
			while(attemptpkItr.hasNext()){
			long attemptPk = attemptpkItr.next();	
			System.out.println(" attemptPk : "+attemptPk);
			pstmt.setLong((queryCount + 3), attemptPk);
			queryCount ++;
			}
			rs= pstmt.executeQuery();
			Map<String, QuestionParameters> questionMap = new HashMap<String, QuestionParameters>();
			while (rs.next()) {
			long attemptPk = rs.getLong("attempt_pk");  
			QuestionParameters questionParams = new QuestionParameters();
			questionMap.put(rs.getString("questionid"), questionParams);
			questionParams.setPoints(rs.getInt("itemscore"));
			questionParams.setPointsMax(rs.getInt("maxpoints"));
			String correctFormulaAnswer = rs.getString("formulaanswer");
			questionParams.setCorrectFormulaAnswer(correctFormulaAnswer != null? correctFormulaAnswer : "");
			questionParams.setFollowup((questionParams.getFollowupValue().length() > 0));
			questionParams.setFollowupValue(tp_sql.stringFromStream(rs.getBinaryStream("followup")));
			questionParams.setHtml(tp_sql.stringFromStream(rs.getBinaryStream("html")));
			questionParams.setRecordedValue(tp_sql.vectorFromStream(rs.getBinaryStream("recordedvalue")));
			Map<String, String> questionParamMap = tp_sql.mapFromStream(rs.getBinaryStream("params"));
			questionParams.getQuestionParameters().putAll(questionParamMap);
			questionParams.setFeedback(tp_sql.vectorFromStream(rs.getBinaryStream("feedback")));
			questionParams.setComment(tp_sql.stringFromStream(rs.getBinaryStream("instructorcomment")));
			PartialTO partialTO = attempTpkPartialTOMap.get(attemptPk);
			partialTO.getQuestionTO().setQuestionMap(questionMap);
			}
			tp_sql.releaseResources(pstmt, rs);
			
			System.out.println("attempTpkPartialTOMap size "+attempTpkPartialTOMap.size());
			
			Iterator<Long> attemptPkItr = attempTpkPartialTOMap.keySet().iterator();
			userIDpartialTOMap = new HashMap<String,PartialTO>();
			while(attemptPkItr.hasNext()){
			long attemptPk = attemptPkItr.next();
			PartialTO partialTO = attempTpkPartialTOMap.get(attemptPk);
			userIDpartialTOMap.put(partialTO.getStudentID(), partialTO);
			}
			System.out.println(" userIDpartialTOMap size "+userIDpartialTOMap.size());
		}catch(Exception sqle){
		System.out.println("######### SQLException in getManGradeR6bResponsesNew "+sqle.getMessage());
		throw sqle;
		}finally{
		tp_sql.releaseResources(pstmt, rs);
		}
		
		return userIDpartialTOMap;
 }
	
	
	
	
	
	
	
	
	public static String getCurrentQuestionID(String jsonString) throws Exception{
	    String currentQid = "";
		try{
		   JSONObject jsonObj = new JSONObject(jsonString);
		   if(jsonObj.has("startwith")){
			   currentQid = jsonObj.getString("startwith");
			   if(("").equals(currentQid) ){
				   System.out.println(" coming startwith is empty .");
				   currentQid = getFirstQid(jsonString);
			   }
		   }else{
			   currentQid = getFirstQid(jsonString);
		   }
	   }catch(Exception ex){
		   ex.printStackTrace();
		   throw ex;
	   }
		
	   return currentQid;	
	}
	public static String getFirstQid (String jsonString) throws Exception{
		String  currentQid = "";
		JSONObject jsonObj = new JSONObject(jsonString);
		 if(jsonObj.has("questions")){
			   JSONArray ja = jsonObj.getJSONArray("questions");
			   JSONObject jsonChild =(JSONObject)ja.get(0);
			   if(jsonChild.has("qid")){
				   currentQid = jsonChild.getString("qid");
			   }
		   } 
	 return currentQid;
	}
	
	
	
	

   
	public static void constructMap(String jsonString, 
			                        String questionID,
			                        StringBuilder paginationStr,
			                        StringBuilder questionList){
		    StringBuilder studentName = new StringBuilder();
			try{
				JSONObject jsonObj = new JSONObject(jsonString);
				if(jsonObj.has("questions")){
					JSONArray ja = jsonObj.getJSONArray("questions");
					for(int i=0;i<ja.length();i++){
						JSONObject jsonChild =(JSONObject)ja.get(i);
						String questionid = null;
						if(jsonChild.has("qid")){
							questionid = jsonChild.getString("qid");
							questionList.append(questionid).append(",");
						}
						if(!questionID.equals(questionid)){
						  continue;	
						}
						if(jsonChild.has("students")){
							JSONArray studentArray = jsonChild.getJSONArray("students");
							for(int j =0;j<studentArray.length();j++){
								if(!("").equals(studentName.toString())){
									studentName.append(",");	
								}
								JSONObject studentObj = (JSONObject)studentArray.get(j);
								if(studentObj.has("userid")){
									studentName.append(studentObj.getString("userid")).append("_");
								}
								if(studentObj.has("name")){
									studentName.append(studentObj.getString("name"));
								}
								if(studentObj.has("attempts")){
									JSONArray attemptArray = studentObj.getJSONArray("attempts");
									for(int k=0 ; k < attemptArray.length();k++){
										studentName.append("_").append(attemptArray.getString(k));
									}
								}
								
							}
						}
					}
			}
		    //System.out.println(" studentName "+studentName);		
			}catch(JSONException jse){
			jse.printStackTrace();
			}
			//studentCountPerpage = get
			String[] studentList = studentName.toString().split(",");
			int count = 0;
			for(int i=0;i<studentList.length;i++)
			{
				count = count+1;
				if(count%studentCountPerpage != 0){
					if(count != studentList.length){
						paginationStr.append(studentList[i]).append(",");
					}else{
						paginationStr.append(studentList[i]);
					}
				}else{
					if(count != studentList.length){
						paginationStr.append(studentList[i]).append(";");
					}else{
						paginationStr.append(studentList[i]);
					}
				}	
			}
			//System.out.println("paginationStr "+paginationStr);
	}
	
	
	
	public static List<String> getStudentDtAndSubmissionIds( String paginationStr , int currentPageId){
		System.out.println(paginationStr);
		List<String> userIdList = new ArrayList<String>();
		List<String> submissionIdListforDiffuser = new ArrayList<String>();
		Map<String,String> studentNameMap = new HashMap<String,String>();
		Map<String, List> studentAttemptMap = new HashMap<String, List>();
		String[] paginationStrArr = paginationStr.split(";");
	    Map<Integer, String> map = new HashMap<Integer, String>();
	    for(int i=0;i<paginationStrArr.length;i++){
	    	map.put(i+1, paginationStrArr[i]);
	    }
	    String studentForThisPage = map.get(currentPageId); 
	    System.out.println("studentForThisPage "+studentForThisPage);
	    if(studentForThisPage != null && !("").equals(studentForThisPage)){
	    	String[] studentArr = studentForThisPage.split(",");
	    	for(int i=0;i<studentArr.length;i++){
	    		List<String> attemptList = new ArrayList<String>();
	    		String studentDt = studentArr[i];
	    		String[] students = studentDt.split("_");
	    		String userId = students[0];
	    		userIdList.add(userId);
	    		String name = students[1];
	    		submissionIdListforDiffuser.add(students[2]);
	    		for(int j=2; j<students.length;j++){
	    			attemptList.add(students[j]);
	    		}
	    		studentNameMap.put(userId, name);
	    		studentAttemptMap.put(userId, attemptList);
	    	}
	    }   
	  System.out.println("================================================================");
	    Iterator<String> snmItr = userIdList.iterator();//studentNameMap.keySet().iterator();
	    
	    while(snmItr.hasNext()){
	    	String UserId = snmItr.next();
	    	System.out.println("----------------------------------------------------------");
	    	System.out.println(" Map UserId "+UserId);
	    	System.out.println(" Map Name "+studentNameMap.get(UserId));
	    	List<String> attemptList = studentAttemptMap.get(UserId);
	        for(int k =0;k<attemptList.size();k++){
	        	System.out.println("Map attempt "+attemptList.get(k));
	        }
	    }
	    
	    /*System.out.println("================================================================");  
	    System.out.println("=====================SubmissionId List===========================");
	   for(int i =0;i<submissionIdListforDiffuser.size();i++){
		   System.out.println(submissionIdListforDiffuser.get(i));
	   }
	   System.out.println("=====================SubmissionId List===========================");*/
	   return submissionIdListforDiffuser;
	}

}
