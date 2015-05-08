package utility;

public class TestSubstr {
	static String  str ="[INFO] 2013-10-08 03:09:43,445 com.mcgrawhill.ezto.integration.classware_hm processRequest - 2013/10/08 03:09:43 HM (econ) request: showT (13252698014257718) HM=http://dev3.mhhe.com/, IP=10.160.123.119"; 
	
	static String  str1 ="2013-10-13T00:01:39-04:00 eztest-app-78-110 nodelogs [INFO] 2013-10-13 00:01:37,317 com.mcgrawhill.ezto.integration.classware_hm showTest_3aNew -  ######### Entering showTest_3aNew for wid:13252699469223531 sectionId:16318687 activityId:561731411 userId:11112535 attemptNo:2";
	
	public static void main(String[] args) {
		String searchString = "request: showT ("; 
		if(str.contains(searchString)){
		
		   String[] strArr =  str.split(" ");
		   String date = strArr[1];
		   String time = strArr[2];
		   if(time.indexOf(",") != -1){
			   time = time.substring(0, time.indexOf(","));
		   }
		   System.out.println(date+" "+time);
			
		   strArr = str1.split(" ");  
		   String str2 = strArr[0];
		   System.out.println(str2);
		   if(str2.indexOf("T") != -1){
			   str2 = str2.replace("T"," ");
		   }
		   System.out.println(str2);
		   
		   /*strArr = str2.split(" ");
		   String[] strArr1 = strArr[1].split(":");*/
		   
		   
		   
 		   int startIndex = str.lastIndexOf("(");
 		   str = str.substring(startIndex+1);
 		   System.out.println(" str1 : "+str);
 		   int endIndex = str.indexOf(")");
 		   str = str.substring(0,endIndex);
 		   System.out.println(" str2 : "+str);
 	   }
		
		/*String searchString = "######### Entering showTest_3aNew for";
		if(str1.contains(searchString)){
			int startindex = str1.indexOf(searchString);
			str1 = str1.substring(startindex);
			str1 = str1.replace(searchString, "");
			str1 = str1.trim();
			System.out.println(str1);
			
			String infoStr = "";
			String testid = "";
			String[] strArr = str1.split(" ");
			for(int i =0; i < strArr.length; i++){
				String str = strArr[i];
				System.out.println(" str : "+str);
				if(str.contains("wid")){
					String[] widArr = str.split(":");
					testid = widArr[1].trim();
				}else{
					infoStr +=" "+str;
				}
			}
			
			System.out.println(testid);
			System.out.println(infoStr);
		}*/
	}
}
