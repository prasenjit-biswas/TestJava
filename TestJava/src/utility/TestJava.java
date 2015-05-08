package utility;

import java.io.FileReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.mcgrawhill.ezto.TestPilot4;

import Dataretrieval.Test.TestsTO;

public class TestJava{
	public static void main(String[] v) {
	  try{
		  List<String> mediaList = new ArrayList<String>();
		  mediaList.add("%media:1.jpg%");
		  
		  System.out.println("<script>cloudFrontUrl = \"" + "sujoy" + "\";</script>");
		  
		  //callEzTest();
		  /*Map<String, Integer> newMap = new HashMap<String, Integer>();
		  
		  newMap.put("1", 11);
		  newMap.put("11", 11);
		  newMap.put("2", 22);
		  newMap.put("22", 22);
		  newMap.put("3", 33);
		  newMap.put("33", 33);*/
		  
		  /*
		  Set<Entry<String, Integer>> newMapSet = newMap.entrySet();
		  
		  Iterator<Entry<String, Integer>> itr = newMapSet.iterator();
		  while(itr.hasNext()){
			  Entry<String, Integer> entry = itr.next();
			  System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());
		  }
		  */
		  
		  /*Set<String> keySet = newMap.keySet();
		  
		  System.out.println("" + new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		  System.out.println("" + new BigDecimal(10).setScale(2).toString());*/
		  /*
		  String sourceXrefTestID ="01";
		  String sourceXrefQuestionID = "02";
		  String newQuestionID = "03";
		  String sourceTestID = "04";
		  String sourceQuestionID = "05";
		  String originalQuestionID = "06";
		  String points = null;
		  String trackbackReqd = "Y";
		  String sourceTid = "07";
		  String destinationTid = "08";
		  String newTestBankId = "09";
		  
		  StringBuilder insertGlobaTable = new StringBuilder();
		  insertGlobaTable.append("SELECT '").append(sourceXrefTestID).append("' sourceXrefTID, '")
			.append( sourceXrefQuestionID ).append("' sourceXrefQID , '")
			.append(newQuestionID).append("' targetqid , '")									
			.append(sourceTestID).append("' libraryTID , '")
			.append(sourceQuestionID).append("' libraryQID , '")									
			.append(originalQuestionID).append("' originalQID , ")
			.append(points ).append(" points , '")
			.append(trackbackReqd ).append("' trackbackReqd '")
			.append(sourceTid ).append("' sourceTestID '")
			.append(destinationTid ).append("' targetTestID '")
			.append(newTestBankId ).append("' testBankId ");
           insertGlobaTable.append(" FROM dual ");
           
           System.out.println("Insert Global Table : "+insertGlobaTable);
		  */
		}catch(Exception ex){
		  ex.printStackTrace();
	  }
	}
	
	private static void callMe(ArrayList newList){
		Iterator itr = newList.iterator();
		TestsTO testsTO = null;
		while(itr.hasNext()){
			testsTO = (TestsTO)itr.next();
			testsTO.setTestID("111");
		}
	}
	
	private static void callEzTest() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileReader("C:/classware_workspace/TestJava/showtest.properties"));
		Enumeration keys = prop.keys();
		String theData = null;
        if (keys.hasMoreElements()) {
          String key = (String) keys.nextElement();
          theData = prop.getProperty(key);
          System.out.println(" data : " + theData);
        }
        
        URL thisURL = new URL("http://localhost/hm.tpx");
		HttpURLConnection theConnection = (HttpURLConnection) thisURL.openConnection();
		theConnection.setRequestMethod("POST");
		theConnection.setDoOutput(true);
		theConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		theConnection.connect();

		OutputStream postBody = theConnection.getOutputStream();
		postBody.write(theData.getBytes());
		postBody.close();

		int returnCode = theConnection.getResponseCode();
		if (returnCode != 200) {
			System.out.println("Exception processing backgroundForceSubmission");
			System.out.println("  EZTO returned: " + returnCode);
		} else
			System.out.println("code: " + returnCode);

		theConnection.disconnect();
        
	}
}