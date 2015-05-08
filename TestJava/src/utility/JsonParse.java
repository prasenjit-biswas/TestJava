package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParse {

	//static String  jsonString = "{'eaid': '223232bxvn32cvn43cvnc6n7cv8n8c8n8cbv8nc666cn8','questions':[{'erid': 'g3jh45g6hj34g56hj3g45h6g345hj6gg345jh6345jh6g','format': 1,'type': 'MC','scramble': [ 1,4,3,2 ],'response': 3,'correctness': 100,'completeness': 100,'hints': 0,'timeontask': 4580,'bop': 'incorrect','bopid': '35nm5y624k5j245k6j4k5jk45k6j4k5gg345jh6345jh6g','randoms': [{ 'name': 'a', 'value': '3' },{ 'name': 'b', 'value': '6' },{ 'name': 'c[0]', 'value': '3' },{ 'name': 'c[1]', 'value': 'Mary' }]},{'erid': 'g3jh45g6hj34g56hj3g45h6g345hj6gg345jh6345jh6g','format': 1,'type': 'TF','scramble': [ 1,4,3,2 ],'response': 3,'correctness': 100,'completeness': 100,'hints': 0,'timeontask': 4580,'bop': 'incorrect','bopid': '35nm5y624k5j245k6j4k5jk45k6j4k5gg345jh6345jh6g','randoms': [{ 'name': 'a', 'value': '3' },{ 'name': 'b', 'value': '6' },{ 'name': 'c[0]', 'value': '3' },{ 'name': 'c[1]', 'value': 'Mary' }]}]}";
	static String  jsonString ="{'rdata':{'updatedid': '987dfg987f987df9h7wdfg98h7w9gd8798wg7n987gnsfg98n7','rinfo':{'eaid': '223232bxvn32cvn43cvnc6n7cv8n8c8n8cbv8nc666cn7','erid': 'g3jh45g6hj34g56hj3g45h6g345hj6gg345jh6345jh6g','format': 1,'type': 'MC','scramble':[1,4,3,2],'response': 3,'correctness': 100,'completeness': 100,'hints': 0,'timeontask': 4580,'bop': 'incorrect','bopid': '35nm5y624k5j245k6j4k5jk45k6j4k5gg345jh6345jh6g','randoms':[{'name': 'a','value': '3'},{'name': 'b','value': '6'},{'name': 'c[0]','value': '3'},{'name': 'c[1]','value': 'Mary'}]}}}";
	
	/*public static void main(String[] args) throws Exception {
		JSONObject jsonObj = new JSONObject(jsonString);
		JSONArray jarr = jsonObj.getJSONArray("questions");
		System.out.println("eaid : "+jsonObj.getString("eaid"));
		for(int i =0; i< jarr.length() ; i++){
			JSONObject jo = (JSONObject)jarr.get(i);
			System.out.println("erid : "+jo.get("erid"));
			jo.put("questionid", "123");
		}
		System.out.println(jsonObj);
	}*/
	/*public static void main(String[] args) {
		try{
			JSONObject jsonObj = new JSONObject(jsonString);
			System.out.println("COMING JSON STR : "+jsonObj);
			JSONObject rdataObj = jsonObj.getJSONObject("rdata");
			System.out.println("RDATA STR : "+rdataObj);
			String updatedidString= rdataObj.getString("updatedid");
			System.out.println("updatedidString : "+updatedidString);
			JSONObject rinfo = rdataObj.getJSONObject("rinfo");
			System.out.println("rinfo : "+rinfo);
			String type = rinfo.getString("type");
			System.out.println("type : "+type);
			rinfo.put("questionid", 123);
			System.out.println("Modified RINFO "+rinfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			String a = "";
			JSONObject json = new JSONObject();
			json.put("a", "valueA");
			json.put("b", a);
			System.out.println(" json : "+json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	/*public static void main(String[] args) {
		try{
			String str = "MTMyNTI2OTgwMTM5NjczMzU9PT09MTMyNTI2OTgwMTYyNDEyNDY9PT09MTM5NTY3NzI2Njc2MQ==";
			JSONObject jsonObj = new JSONObject();
			
			System.out.println(jsonObj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	/*public static void main(String[] args) {
		String jsonStr = "{'erids':['987dfg987f987df9h7wdfg98h7w9gd8798wg7n987gnsfg98n7','796fg8hj6wr98j7w8r7hj98r7j987gj987fg89j7f98gj798dfg7jfg7','fgjsfg75j6s87fg6js78r6js786gj7sf6g78j6f78g6js78fg6j786fgh']}";
		List<String> eridList = new ArrayList<String>();
		try{
			JSONObject jsonObj = new JSONObject(jsonStr);
			JSONArray eridArr = jsonObj.getJSONArray("erids");
			
			for(int i =0 ; i< eridArr.length(); i++){
				eridList.add((String)eridArr.get(i));
			}
			System.out.println(eridList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	/*public static void main(String[] args) {
		JSONObject jsonObj = null;
		try{
			System.out.println(jsonObj == null);
			jsonObj = new JSONObject();
			System.out.println(jsonObj.length());
			JSONArray jarr = new JSONArray();
			jsonObj.put("items", jarr);
			
			JSONObject jsonObj1 = new JSONObject();
			jsonObj1.put("name", "1");
			jarr.put(jsonObj1);
			
			JSONObject jsonObj2 = new JSONObject();
			jsonObj2.put("name", "2");
			jarr.put(jsonObj2);
			
			
			System.out.println(jsonObj);
			System.out.println(jsonObj.length());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		Map<String, Object> testMap = new HashMap<String, Object>();
		Set<String> modSet = new HashSet<String>();
		modSet.add(null);
		modSet.add(null);
		modSet.add(null);
		
		System.out.println(modSet.size());
		if(modSet.size() == 1){
			testMap.put("mode", modSet.iterator().next());
		}
		
		System.out.println(testMap);
		if(testMap.get("mode") != null){
			System.out.println("not coming as NULL");
		}else{
			System.out.println("coming as NULL");
		}
	}
}
