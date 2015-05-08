package utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StringReplace {
   /*public static void main(String[] args) {
	   String jsonString = "{\"workflow\" : \"by question\", \"ezid\" : \"13252698009612566\"";
	   jsonString = jsonString.replace("\"", "'");  
	   System.out.println(jsonString);
    }*/
	
	public static void main(String[] args) {
		/*List<String> userResponse = new ArrayList<String>();
		userResponse.add("incorrect");
		userResponse.add("incorrect");
		userResponse.add("incorrect");
		//userResponse.add("incorrect");
		//userResponse.add("incorrect");
		//System.out.println(" userResponse1 : "+userResponse);
		userResponse.set(4, "correct");
		System.out.println(" userResponse2 : "+userResponse);
		userResponse.set(5, "correct");
		System.out.println(" userResponse3 : "+userResponse);*/
		
		List<String> userResponse = new ArrayList<String>();
		userResponse.add("incorrect");
		userResponse.add("incorrect");
		userResponse.add("incorrect");
		System.out.println(" userResponse1 : "+userResponse+" size : "+userResponse.size());
		int userResponseSize = userResponse.size();
		int diff = 5;
		for(int i=userResponseSize; i<diff; i++){
			userResponse.add("correct");
		}
		System.out.println(" userResponse2 : "+userResponse+" size : "+userResponse.size());
		
	}
	
}
