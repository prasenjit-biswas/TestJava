package utility;

import java.io.File;
import java.io.FileInputStream;



public class ChangeAnsProperty {
	public static void main(String args[]){
		int startPoint = 0 ;
		int endPoint = 0;
		String firstHalf = null;
		String endHalf =  null;
		  
			
		String pickerXml = readFromFile("C:/WholePickerXML.xml");
		String answerXml  = readFromFile("C:/answer.xml");
		System.out.println(" pickerXml : "+pickerXml);
	   
		StringBuilder sb = new StringBuilder(pickerXml); 
		
		if(pickerXml.indexOf("<answers>") != -1){
			startPoint =  pickerXml.indexOf("<answers>");
		}
		if(pickerXml.indexOf("</question>") != -1){
			endPoint =  pickerXml.indexOf("</question>");
		}
		//String oldAnswer = pickerXml.substring(startPoint, endPoint);
		/*if(startPoint != 0){
			firstHalf = pickerXml.substring(0,startPoint);
		}
		if(endPoint != 0){
			endHalf = pickerXml.substring(endPoint);
		}*/
		/*if(firstHalf != null && answerXml != null && endHalf != null){
			pickerXml = firstHalf + answerXml + endHalf;
		}*/
		if(startPoint != 0 && endPoint != 0 && answerXml!= null){
			sb.replace(startPoint, endPoint, answerXml);
		}
		/*System.out.println(" startPoint : "+startPoint);
		System.out.println(" endPoint : "+endPoint);
		System.out.println(" firstHalf : "+firstHalf);
		System.out.println(" endHalf : "+endHalf);*/
		System.out.println(" Updated : "+sb.toString());
	}
	
	
	
	
	public static String readFromFile(String FilePath){
		String returnStr = "";
		File file = new File(FilePath);//"C:/file.txt"
	    int ch;
	    StringBuffer strContent = new StringBuffer("");
	    FileInputStream fin = null;
	    try {
	      fin = new FileInputStream(file);
	      while ((ch = fin.read()) != -1)
	        strContent.append((char) ch);
	      fin.close();
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    returnStr = strContent.toString();
	    return returnStr;
	}
}
