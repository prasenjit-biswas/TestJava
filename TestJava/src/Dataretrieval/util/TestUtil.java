package Dataretrieval.util;

public class TestUtil {
	public static void main(String[] args) {
		String qtext = "abcdefg%media:1formula1.jpg, formula2.png%ratnankur kushary";
		String firstName = qtext.substring(qtext.indexOf("%media:") );
		 firstName = firstName.substring(firstName.indexOf(":")+1);
		 String seconName = firstName.substring(0,firstName.indexOf("%"));
		 String totalMediaName = seconName;
		 System.out.println(totalMediaName);
		 String[] mediaArray = totalMediaName.split(","); 
		 for(int i=0; i< mediaArray.length;i++){
			 System.out.println(mediaArray[i]);
		 }
	}
}
