 package utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mcgrawhill.ezto.security.authentication.authentication_RMS_Service;
import com.sun.org.apache.xml.internal.security.utils.Base64;


public class GetXML {
	
	public static final String rmsURL = "http://createqa.mcgraw-hill.com/"; //"http://createqa.mcgraw-hill.com/";"http://nvldev01j.eppg.com:8080/";
	public static final String basicAuthUid = "rmspeint";
	public static final String basicAuthPwd = "12345678";
	
  public static void main(String[] args)throws Exception {
	  //isAuthorized();
	  //postREST();
	  //TestIsAuthorized();
	  //creteUserAccountJSON();
	  //testJsonArray();
	  //testMap();
	  testString();
  }
  
  
  public static void testString ( )
  {
	String a = "Science, Engineering & Math --> Algebra-->Algebra2--> Algebra3 ";  
	String[] arr = a.split("-->");
	for(int k=0;k<arr.length;k++){
		System.out.println(arr[k]);
	}
	System.out.println("REVRSE");
	for(int k=arr.length-1;k>=0;k--){
		System.out.println(arr[k]+" "+k);
	}
  }
  

  public static void TestIsAuthorized(){
		String theURL = "";
		String encodeString = "";
		String httpMethod = "POST";
		String relativeURL = "rms-rest/user/authenticate";
		String result = "";
		try{
			
			System.getProperties().put("proxySet", "true");
			System.getProperties().put("proxyHost", "204.8.135.174");
			System.getProperties().put("proxyPort", "80");
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("password", "123456");
			jsonObj.put("userName", "sujoy.b@tcs.com");
			String params = jsonObj.toString();
			theURL = rmsURL+relativeURL;
			encodeString = basicAuthUid+":"+basicAuthPwd;
			result = authentication_RMS_Service.connectRmsURL(theURL, encodeString,  httpMethod,  params);
		    System.out.println(" result "+result); 
		}catch(Exception ex){
			
		}
	}
  
  
  public static void testMap(){
	  Map<String, Boolean> getMap = new HashMap<String, Boolean>();
	  getMap.put("hello", false);
	  System.out.println(getMap.get("hi"));
	  if(getMap.containsKey("hello")){
		  getMap.put("hello", true);
	  }
	  System.out.println(getMap);
  }
  
  public static void testJsonArray() throws Exception {
	  JSONArray ja = new JSONArray();
	  ja.put("EZTO_PERMISSION_View Courses");
	  ja.put("EZTO_PERMISSION_Enable/Disable Courses");
	  ja.put("EZTO_PERMISSION_Create User Accounts");
	  ja.put("EZTO_ENVIRONMENT_QA Staging");
	  ja.put("EZTO_ENVIRONMENT_Demo/Prep");
	  ja.put("EZTO_ENVIRONMENT_EZTO Production-cloud");
	  ja.put("EZTO_LIBASSOCIATION_B&E Classware Testing");
	  ja.put("EZTO_LIBASSOCIATION_Career & Professional");
	  ja.put("EZTO_LIBASSOCIATION_Ryerson Testing");
	  System.out.println(ja.toString());
      
	  for(int i =0; i<  ja.length(); i++){
		  System.out.println(ja.get(i));
	  }
  
  }
  
  
  public static void creteUserAccountJSON() throws Exception {
		JSONArray ja = new JSONArray();
		JSONObject jaObj1 = new JSONObject();
		jaObj1.put("schoolNumber", -130929);
		jaObj1.put("schoolName", "My school is not listed");
		jaObj1.put("schoolCountry", "US");
		jaObj1.put("school_State_Code", "NY");
		jaObj1.put("schoolPartyId", "-130929");
		ja.put(jaObj1);
		
		
		
	    JSONObject userProfileInfo = new JSONObject();
		userProfileInfo.put("userId", 0);
		userProfileInfo.put("verified", true);
		userProfileInfo.put("affiliations", ja);
		userProfileInfo.put("securityQuestionId", 3);
		userProfileInfo.put("securityAnswer", "ny");
		userProfileInfo.put("phoneNumber", "1234");
		userProfileInfo.put("userName", "prasenjit.biswas@tcs.com");
		userProfileInfo.put("firstName", "prasenjit");
		userProfileInfo.put("lastName", "biswas");
		userProfileInfo.put("sendMarketingMaterials", false);
		userProfileInfo.put("userType", "I");
		userProfileInfo.put("password", "password");
		
		
		
		
		System.out.println(" userProfileInfo "+userProfileInfo.toString());
	}
  
  
  
  public static String isAuthorized( ) throws Exception{
		String theURL= "";
		String theResults = "";
		JSONObject jobj = new JSONObject();
		jobj.put("password", "1234");
		jobj.put("userName", "sujoy.b@tcs.com");
		System.out.println(jobj.toString());
		try{
			String encodeString = Base64.encode("rmspeint:12345678".getBytes());
			System.out.println(" encodeString "+encodeString);
			theURL = "http://nvldev01j.eppg.com:8080/rms-rest/user/authenticate";
			URL url = new URL(theURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Basic "+encodeString.getBytes());
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", new String(Integer.toString(jobj.toString().length())));
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			
			OutputStream classOut = connection.getOutputStream();
			classOut.write(jobj.toString().getBytes());
			classOut.close();
			
			// read in the result
			BufferedInputStream classIn = new BufferedInputStream(connection.getInputStream());
			long length = connection.getContentLength();
			if (length < 0)
				length = classIn.available(); // sometimes the wrong number is
												// returned, try this method

			if (length == -1)
			{
				classIn.close();
				System.out.println("restServices.postREST: Exception - no response from Connect for " + theURL);
				//return theResults;
			} 
			else
			{
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    int result = classIn.read();
			    while(result != -1) {
			      byte b = (byte)result;
			      bos.write(b);
			      result = classIn.read();
			    }        
			    theResults = bos.toString();
			    classIn.close();
			    bos.close();
			}
			System.out.println(" ######## isAuthorized theResults : "+theResults);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
		}
	  return theResults;
	}
  
  
  public static String postREST()
	{
		String theResults = "";
		try 
		{
			String theURL = "http://nvldev01j.eppg.com:8080/rms-rest/user/profile/email/sujoy.b@tcs.com";
			URL url = new URL(theURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setReadTimeout(5*60*1000);
			connection.connect();

			// read in the result
			BufferedInputStream classIn = new BufferedInputStream(connection.getInputStream());
			long length = connection.getContentLength();
			if (length < 0)
				length = classIn.available(); // sometimes the wrong number is
												// returned, try this method

			if (length == -1)
			{
				classIn.close();
				System.out.println("restServices.postREST: Exception - no response from Connect for " + theURL);
				
			} 
			else
			{
				
			    ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    int result = classIn.read();
			    while(result != -1) {
			      byte b = (byte)result;
			      bos.write(b);
			      result = classIn.read();
			    }        
			    theResults = bos.toString();
			    classIn.close();
			    bos.close();
			}
		}
		catch (Exception x) 
		{
			x.printStackTrace();
			
		}

		return theResults;
	}
  
  
  public static String b64encode(String plain)
	{
	    if(plain.length() > 76) return null;
	    
	    int maxturns;
	    StringBuffer sb=new StringBuffer();
	    //the encode buffer
	    byte[] enc=new byte[3];
	    boolean end=false;
	    for(int i=0,j=0; !end; i++)
	    {
          char _ch=plain.charAt(i);
          if(i==plain.length()-1)
          end=true;
          enc[j++]=(byte)plain.charAt(i);
          if(j==3 || end)
          {
              int res;
              //this is a bit inefficient at the end point
              //worth it for the small decrease in code size?
              res=(enc[0] << 16)+(enc[1] << 8)+enc[2];
              int b;
              int lowestbit=18-(j*6);
              for(int toshift=18; toshift>=lowestbit; toshift-=6)
              {
                  b=res >>> toshift;
                  b&=63;
                  if(b>=0 && b<26)
                  sb.append((char)(b+65));
                  if(b>=26 && b<52)
                  sb.append((char)(b+71));
                  if(b>=52 && b<62)
                  sb.append((char)(b-4));
                  if(b==62)
                  sb.append('+');
                  if(b==63)
                  sb.append('/');
                  if(sb.length()%76==0)
                  sb.append('\n');
              }
              //now set the end chars to be pad character if there 
              //was less than integral input (ie: less than 24 bits)
              if(end)
              {
                  if(j==1)
                  sb.append("==");
                  if(j==2)
                  sb.append('=');
              }
              enc[0]=0; enc[1]=0; enc[2]=0;
              j=0;
          }
	    }
	    return sb.toString();
	}
}
