package utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mcgrawhill.ezto.utilities.tp_utils;

public class TestJson {
	
	/*public static void main(String[] args) {
		String b = null;
		StringBuilder a = new StringBuilder("Q_").append(b);
		System.out.println(" a : "+a);
	}*/
	
	/*public static void main(String[] args) throws Exception{
		String jsonString = "{\"activityInfo\": {'instructorInfo': {'instructorImageURL': 'http: //xxx','instructorName': 'ProfessorSchwarz','instructorInstructions': 'Complete this assignment without any cheating. IfÆÿ u have any problem, please contact with the instructor.'},{'actualAttemptNo': '1',"+
            "'displayAttemptNo': '1','scoredAttemptMax': '3','status': 'pastdue','latePenalty': '20','gradebookPolicy': 'bestattemptgraded','buildOnPrevious': 'true','feedbackBetweenQ': 'true',"+
		    "'fileAttachments': 'true/false','studyMode': 'false','autoSubmit': 'true/false','dueDate': '05/23/2014 at 11:59PM'},"+
		    "'marathonInfo': [{'marathontitle': 'Marathon1','marathonminimum': '20'},"+
            "{'marathontitle': 'Marathon2','marathonminimum': '10'}],"+
            "'deductions': {'lateSubmission': '10','lateSubmissionInterval': 'hour/day','hintDeduction': '11','ebookDeduction': '12',"+
            "'checkWorkDeduction': '13','checkWorkLimit': '3'}}}";
		
		System.out.println(" first JSON : "+tp_utils.convertFromUTF8(jsonString));
		
		String jsonStr = "{     &quot;activityInfo&quot;: {         &quot;instructorInfo&quot;: {             &quot;instructorImageURL&quot;: &quot;http: //xxx&quot;,             &quot;instructorName&quot;: &quot;ProfessorSchwarz&quot;,             &quot;instructorInstructions&quot;: &quot;Complete this assignment without any cheating. IfÆÿ u have any problem, please contact with the instructor.&quot;         },         &quot;attemptInfo&quot;: {             &quot;actualAttemptNo&quot;: &quot;1&quot;,             &quot;displayAttemptNo&quot;: &quot;1&quot;,             &quot;scoredAttemptMax&quot;: &quot;3&quot;,             &quot;status&quot;: &quot;pastdue&quot;,             &quot;latePenalty&quot;: &quot;20&quot;,             &quot;gradebookPolicy&quot;: &quot;bestattemptgraded&quot;,             &quot;buildOnPrevious&quot;: &quot;true&quot;,             &quot;feedbackBetweenQ&quot;: &quot;true&quot;,             &quot;fileAttachments&quot;: &quot;true/false&quot;,             &quot;studyMode&quot;: &quot;false&quot;,             &quot;autoSubmit&quot;: &quot;true/false&quot;,             &quot;dueDate&quot;: &quot;05/23/2014 at 11:59PM&quot;         },         &quot;marathonInfo&quot;: [             {                 &quot;marathontitle&quot;: &quot;Marathon1&quot;,                 &quot;marathonminimum&quot;: &quot;20&quot;             },             {                 &quot;marathontitle&quot;: &quot;Marathon2&quot;,                 &quot;marathonminimum&quot;: &quot;10&quot;             }         ],         &quot;deductions&quot;: {             &quot;lateSubmission&quot;: &quot;10&quot;,             &quot;lateSubmissionInterval&quot;: &quot;hour/day&quot;,             &quot;hintDeduction&quot;: &quot;11&quot;,             &quot;ebookDeduction&quot;: &quot;12&quot;,             &quot;checkWorkDeduction&quot;: &quot;13&quot;,             &quot;checkWorkLimit&quot;: &quot;3&quot;         }     } }";
		
		String output = tp_utils.entitiesToCharacters(jsonStr);
		
		System.out.println(" output : "+output);
		System.out.println(" output JSON : "+ new JSONObject(output));
		output = tp_utils.toCharEntities(output);
		System.out.println(" output2 JSON : "+ output);
	}*/
	
	public static void main(String[] args) throws Exception {
		//String jsonStr = "{     &quot;activityInfo&quot;: {         &quot;instructorInfo&quot;: {             &quot;instructorImageURL&quot;: &quot;http: //xxx&quot;,             &quot;instructorName&quot;: &quot;ProfessorSchwarz&quot;,             &quot;instructorInstructions&quot;: &quot;Complete this assignment without any cheating. IfÆÿ u have any problem, please contact with the instructor.&quot;         },         &quot;attemptInfo&quot;: {             &quot;actualAttemptNo&quot;: &quot;1&quot;,             &quot;displayAttemptNo&quot;: &quot;1&quot;,             &quot;scoredAttemptMax&quot;: &quot;3&quot;,             &quot;status&quot;: &quot;pastdue&quot;,             &quot;latePenalty&quot;: &quot;20&quot;,             &quot;gradebookPolicy&quot;: &quot;bestattemptgraded&quot;,             &quot;buildOnPrevious&quot;: &quot;true&quot;,             &quot;feedbackBetweenQ&quot;: &quot;true&quot;,             &quot;fileAttachments&quot;: &quot;true/false&quot;,             &quot;studyMode&quot;: &quot;false&quot;,             &quot;autoSubmit&quot;: &quot;true/false&quot;,             &quot;dueDate&quot;: &quot;05/23/2014 at 11:59PM&quot;         },         &quot;marathonInfo&quot;: [             {                 &quot;marathontitle&quot;: &quot;Marathon1&quot;,                 &quot;marathonminimum&quot;: &quot;20&quot;             },             {                 &quot;marathontitle&quot;: &quot;Marathon2&quot;,                 &quot;marathonminimum&quot;: &quot;10&quot;             }         ],         &quot;deductions&quot;: {             &quot;lateSubmission&quot;: &quot;10&quot;,             &quot;lateSubmissionInterval&quot;: &quot;hour/day&quot;,             &quot;hintDeduction&quot;: &quot;11&quot;,             &quot;ebookDeduction&quot;: &quot;12&quot;,             &quot;checkWorkDeduction&quot;: &quot;13&quot;,             &quot;checkWorkLimit&quot;: &quot;3&quot;         }     } }";
		String jsonStr = " &quot;Complete this assignment without any cheating. If&Atilde;&Atilde;u have ";
		String output = tp_utils.toCharEntities(jsonStr);
		//JSONObject json1 = new JSONObject(output);
		System.out.println(" json1 : "+output);
		//System.out.println("<input name=\"pInstructionsDialog\" type=\"hidden\" value=\'" + json1.toString() + "\'/>");
	}
	
	/*public static void main(String[] args) throws Exception {
		
		JSONObject masterJSON = new JSONObject();
		
		JSONObject compJSON = new JSONObject();
		masterJSON.put("compstatus", compJSON);
		
		JSONObject value = new JSONObject();
		value.put("completion", "0");
		value.put("visited", "false");
		value.put("answered", "false");
		
		JSONObject value1 = new JSONObject();
		value1.put("completion", "0");
		value1.put("visited", "false");
		value1.put("answered", "false");
		JSONObject value = null;
		JSONObject value1 = null;
		compJSON.put("erid1", value);
		compJSON.put("erid2", value1);
		
		
		System.out.println(" compJSON : "+compJSON);
		System.out.println(" masterJSON : "+masterJSON);
	}*/
	/*public static void main(String[] args) throws Exception {
		String json1 = "{'name':'prasenjit'}";
		JSONObject jsonObj1 = new JSONObject(json1);
		
		JSONObject json2 = new JSONObject();
		json2.put("json1", jsonObj1);
	
		System.out.println(" json2 : "+json2.toString());
		
		JSONArray correctnessArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", 1);
		jsonObj.put("correct", "true");
		correctnessArray.put(jsonObj);
		jsonObj = new JSONObject();
		jsonObj.put("id", 2);
		jsonObj.put("correct", "true");
		correctnessArray.put(jsonObj);
		
		JSONObject json1 = new JSONObject();
		json1.put("correctness", correctnessArray.toString());
		System.out.println(" json1 : "+json1);
		JSONArray correctJson = null;
		try{
			 System.out.println(" correctnessArray : "+correctnessArray);
			 correctJson = new JSONArray(json1.getString("correctness"));
		}catch(JSONException je){
			je.printStackTrace();
			correctJson = null;
		}
		System.out.println(" correctJson : "+correctJson);
		json1.put("correctness", correctJson);
		System.out.println(json1);
		
	}*/

}
