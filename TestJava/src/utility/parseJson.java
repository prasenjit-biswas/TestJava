package utility;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class parseJson {
  public static void main(String[] args) {
    try{
	 // String jsonString = "{'userId':1000166962,'verified':true,'userStatus':'VALID','roles':[{'roleName':'INSTRUCTOR','roleID':'INSTRUCTOR','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':2}],'affiliations':[{'userId':0,'schoolNumber':17058,'schoolName':'SUCCESS INSTITUTE','schoolCountry':'US','school_State_Code':'ID','schoolPartyId':'1639564','depId':261,'department':'ECONOMICS','depCountry':'US','specId':0,'id':50245},{'userId':0,'schoolNumber':-14575,'schoolName':'germany','schoolCountry':'DE','school_State_Code':'germany','depId':261,'department':'ECONOMICS','depCountry':'US','specId':0,'id':50246}],'securityQuestionId':3,'securityQuestion':'What is your favorite city?','securityAnswer':'nyc','phoneNumber':'','faxNumber':'','userType':'I','userName':'sujoy.b@tcs.com','firstName':'Sujoy','lastName':'Bhatt','middleInitial':'','userCountry':'US','insValidationRequest':false,'department':'ECONOMICS','schoolName':'SUCCESS INSTITUTE','termsAcceptedDate':'Jul 23, 2012','sendMarketingMaterials':true,'userEmail':'sujoy.b@tcs.com','id':1000166962}";
    	String jsonString = "{'userId':1000166962,'verified':true,'userStatus':'VALID','roles':[{'roleName':'INSTRUCTOR','roleID':'INSTRUCTOR','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':2}],'affiliations':[{'userId':0,'schoolNumber':17058,'schoolName':'SUCCESS INSTITUTE','schoolCountry':'US','school_State_Code':'ID','schoolPartyId':'1639564','depId':261,'department':'ECONOMICS','depCountry':'US','specId':0,'id':50245},{'userId':0,'schoolNumber':-14575,'schoolName':'germany','schoolCountry':'DE','school_State_Code':'germany','depId':261,'department':'ECONOMICS','depCountry':'US','specId':0,'id':50246}],'securityQuestionId':3,'securityQuestion':'What is your favorite city?','securityAnswer':'nyc','phoneNumber':'','faxNumber':'','userType':'I','userName':'sujoy.b@tcs.com','firstName':'Sujoy','lastName':'Bhatt','middleInitial':'','userCountry':'US','insValidationRequest':false,'department':'ECONOMICS','schoolName':'SUCCESS INSTITUTE','termsAcceptedDate':'Jul 23, 2012','sendMarketingMaterials':true,'userEmail':'sujoy.b@tcs.com','id':1000166962}";
    	//jsonString = "{'userId':1000350941,'verified':true,'userStatus':'PENDING','roles':[{'roleName':'INSTRUCTOR','roleID':'INSTRUCTOR','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':2},'EZTO_PERMISSION_support_view_course','EZTO_PERMISSION_support_disable_course','EZTO_PERMISSION_support_create_account','EZTO_ENVIRONMENT_QA Staging','EZTO_ENVIRONMENT_Demo/Prep','EZTO_ENVIRONMENT_EZTO Production-cloud','EZTO_LIBASSOCIATION_B&E Classware Testing','EZTO_LIBASSOCIATION_Career & Professional','EZTO_LIBASSOCIATION_EZ Test Online Sample Banks'],'affiliations':[{'userId':0,'schoolNumber':-13601,'schoolName':'My school is not listed','schoolCountry':'US','school_State_Code':'NY','depId':0,'specId':0,'id':69257}],'securityQuestionId':3,'securityQuestion':'What is your favorite city?','securityAnswer':'ny','phoneNumber':'1234','faxNumber':'','userType':'I','userName':'biswas5.prasenjit@tcs.com','firstName':'Prasenjit','lastName':'Biswas5','middleInitial':'','userCountry':'US','insValidationRequest':false,'department':'0','schoolName':'My school is not listed','sendMarketingMaterials':false,'userEmail':'biswas5.prasenjit@tcs.com','id':1000350941}";
    	//jsonString = "{'userId':39201,'verified':true,'userStatus':'PENDING','roles':[{'roleName':'EZTO_ENVIRONMENT_Demo/Prep','roleID':'EZTO_ENVIRONMENT_Demo/Prep','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':67},{'roleName':'EZTO_ENVIRONMENT_EZTO Production-cloud','roleID':'EZTO_ENVIRONMENT_EZTO Production-cloud','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':68},{'roleName':'EZTO_ENVIRONMENT_QA Staging','roleID':'EZTO_ENVIRONMENT_QA Staging','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':66},{'roleName':'EZTO_ENVIRONMENT_deveztest','roleID':'EZTO_ENVIRONMENT_deveztest','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':72},{'roleName':'EZTO_LIBASSOCIATION_B\u0026E Classware Testing','roleID':'EZTO_LIBASSOCIATION_B\u0026E Classware Testing','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':69},{'roleName':'EZTO_LIBASSOCIATION_Career \u0026 Professional','roleID':'EZTO_LIBASSOCIATION_Career \u0026 Professional','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':70},{'roleName':'EZTO_LIBASSOCIATION_EZ Test Online Sample Banks','roleID':'EZTO_LIBASSOCIATION_EZ Test Online Sample Banks','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':71},{'roleName':'EZTO_PERMISSION_support_create_account','roleID':'EZTO_PERMISSION_support_create_account','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':62},{'roleName':'EZTO_PERMISSION_support_disable_course','roleID':'EZTO_PERMISSION_support_disable_course','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':61},{'roleName':'EZTO_PERMISSION_support_librarian','roleID':'EZTO_PERMISSION_support_librarian','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':63},{'roleName':'EZTO_PERMISSION_support_view_course','roleID':'EZTO_PERMISSION_support_view_course','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':60},{'roleName':'INSTRUCTOR','roleID':'INSTRUCTOR','group':[{'groupId':'ALL','groupName':'ALL','id':1}],'id':2}],'affiliations':[{'userId':0,'schoolNumber':-591,'schoolName':'My school is not listed','schoolCountry':'US','school_State_Code':'NY','depId':0,'specId':0,'id':8105}],'securityQuestionId':3,'securityQuestion':'What is your favorite city?','securityAnswer':'ny','phoneNumber':'1234','faxNumber':'','userType':'I','userName':'biswas5.prasenjit@tcs.com','firstName':'Prasenjit','lastName':'biswas5','middleInitial':'','userCountry':'US','insValidationRequest':false,'department':'0','schoolName':'My school is not listed','sendMarketingMaterials':false,'userEmail':'biswas5.prasenjit@tcs.com','id':39201}";
    	jsonString = "[{'userId':39204,'verified':false,'securityQuestionId':0,'firstName':'Prasenjit','lastName':'Biswas8','insValidationRequest':false,'sendMarketingMaterials':false,'userEmail':'biswas8.prasenjit@tcs.com','id':39204},{'userId':39199,'verified':false,'securityQuestionId':0,'firstName':'Prasenjit','lastName':'Biswas','insValidationRequest':false,'sendMarketingMaterials':false,'userEmail':'biswas.prasenjit@tcs.com','id':39199},{'userId':39203,'verified':false,'securityQuestionId':0,'firstName':'Prasenjit','lastName':'Biswas7','insValidationRequest':false,'sendMarketingMaterials':false,'userEmail':'biswas7.prasenjit@tcs.com','id':39203},{'userId':39202,'verified':false,'securityQuestionId':0,'firstName':'Prasenjit','lastName':'Biswas6','insValidationRequest':false,'sendMarketingMaterials':false,'userEmail':'biswas6.prasenjit@tcs.com','id':39202},{'userId':39201,'verified':false,'securityQuestionId':0,'firstName':'Prasenjit','lastName':'biswas5','insValidationRequest':false,'sendMarketingMaterials':false,'userEmail':'biswas5.prasenjit@tcs.com','id':39201}]";
    	
    	JSONArray ja = new JSONArray(jsonString);
    	for(int k=0;k< ja.length();k++){
    		if(ja.get(k)!=null){
    			if(ja.get(k) instanceof JSONObject){
    				JSONObject jobj = (JSONObject)ja.get(k);
    				System.out.println(" Fristname "+jobj.get("firstName"));
    				System.out.println(" lastName "+jobj.get("lastName"));
    				System.out.println(" userEmail "+jobj.get("userEmail"));
    			}
    		}
    	}
    	
    	
	  
	  
	  /*JSONObject jsonObj = new JSONObject(jsonString) ;
	    Iterator<String> jsonKeys = jsonObj.keys();
	  while(jsonKeys.hasNext()){
		  String key = jsonKeys.next();
		  if("roles".equals(key)){
			  //System.out.println(" I got roles");
			  JSONArray jArr = jsonObj.getJSONArray(key);
			  for(int i =0; i< jArr.length() ; i++){
				  if(jArr.get(i) != null ){
					  //if(jArr.get(i) instanceof JSONObject || jArr.get(i) instanceof JSONArray ) continue;  
					  if(jArr.get(i) instanceof JSONObject){
						  JSONObject eztoJSONRole = (JSONObject)jArr.get(i);
						  String eztoRole = eztoJSONRole.getString("roleName");
						  //System.out.println(eztoRole);
						  if(eztoRole != null && !("").equals(eztoRole) && (eztoRole).startsWith("EZTO_PERMISSION_")){
							  String eztoRoleValue = eztoRole.replace("EZTO_PERMISSION_", "");
							  System.out.println(" ezto permission "+eztoRoleValue);
						  }
						  if(eztoRole != null && !("").equals(eztoRole) && (eztoRole).startsWith("EZTO_ENVIRONMENT_")){
							  String eztoRoleValue = eztoRole.replace("EZTO_ENVIRONMENT_", "");
							  System.out.println(" ezto environment "+eztoRoleValue);
						  }
						  if(eztoRole != null && !("").equals(eztoRole) && (eztoRole).startsWith("EZTO_LIBASSOCIATION_")){
							  String eztoRoleValue = eztoRole.replace("EZTO_LIBASSOCIATION_", "");
							  System.out.println(" ezto LibAssociation "+eztoRoleValue);
						  }
					  }
				  }
			  }
		     
		  }
		  
		  if("userName".equals(key)){
			  String userName = jsonObj.getString(key);
			  System.out.println(" userName "+userName);
		  }
		  if("firstName".equals(key)){
			  String firstName = jsonObj.getString(key);
			  System.out.println(" firstName "+firstName);
		  }
		  if("userEmail".equals(key)){
			  String lastName = jsonObj.getString(key);
			  System.out.println(" userEmail "+lastName);
		  }
	  }*/
	  
	
	  
    }catch(Exception ex){
    	ex.printStackTrace();
    }
  }
  
 
}
