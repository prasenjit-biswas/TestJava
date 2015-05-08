package utility;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Dataretrieval.util.OracleConnection;

public class MediaConsumerInsert {
  
	public static void main(String[] args) {
	  try{
		  String fileName = "c:/MediaInput.txt";
		  List<Map<String, List<String>>> processedList = readFromFile(fileName);
          Connection con = OracleConnection.getConnection();
		  Iterator<Map<String, List<String>>> processedListItr =  processedList.iterator();
		  while(processedListItr.hasNext()){
			  Map<String, List<String>> processMap = processedListItr.next();
			  getSourceMediaConsumer(con,processMap);
		  }
	  }catch(Exception ex){
		 ex.printStackTrace();
	  }
	}

  
	
	public static void getSourceMediaConsumer(Connection con,Map<String, List<String>> processMap) throws Exception
	{
	   String testid = "";
	   String mediaName = "";
	   long mediaId = 0l;
	   String consumerId = "";
	   String sourcetId = "";
	   String sourceqId = ""; 
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try{
		   Iterator<String> mapItr = processMap.keySet().iterator();
	  	   while(mapItr.hasNext()){
	  	    	 String value = "";
		  		 String key = mapItr.next();
		  		 //System.out.println(" Key :"+key);
		  		 List<String> valueList = processMap.get(key);
			  		 for(int j =0;j<valueList.size();j++){
			  			 //System.out.println(" Value :"+valueList.get(j));
			  			 if(!("0").equals(valueList.get(j))){
			  				 value = valueList.get(j);
			  			 }
			  		 }
			  	 if("mediaName".equals(key)){
			  		if(value != null && !("").equals(value)){
			  			mediaName = value;
			  		}
			  	 }
			  	 if("testId".equals(key)){
			  		if(value != null && !("").equals(value)){ 
			  			testid = value;
			  		}
			  	 }
			  	 if("mediaId".equals(key)){
			  		if(value != null && !("").equals(value)){ 
			  			mediaId = Long.valueOf(value);
			  		}
			  	 }
			  	if("consumerid".equals(key)){
			  		if(value != null && !("").equals(value)){
			  			consumerId = value;
			  		}
			  	}
	  	    }	
	  	    
	  	  System.out.println(" mediaName :"+mediaName);
	  	  System.out.println(" testid :"+testid);
	  	  System.out.println(" mediaId :"+mediaId);
	  	  System.out.println(" consumerId :"+consumerId);
          
	  	  pstmt = con.prepareStatement("SELECT sourcetid, sourceqid FROM trackback where destinationtid = ? and destinationqid = ? ");   
	  	  pstmt.setString(1, testid);  
	  	  pstmt.setString(2, consumerId);
	  	  rs =  pstmt.executeQuery();
	  	  if(rs.next()){
	  		sourcetId = rs.getString("sourcetid");
	  		sourceqId = rs.getString("sourceqid");
	  	  }
		  System.out.println(" sourcetId : "+sourcetId);
		  System.out.println(" sourceqId : "+sourceqId);
		  TestFullCredit.releaseResources(pstmt);
		  TestFullCredit.releaseResources(rs);
		  /*pstmt = con.prepareStatement("SELECT consumerid,name FROM mediaconsumers WHERE testid = ? AND mediaid = ? AND consumerid IN ('0', ?)  ");
		  pstmt.setString(1, sourcetId);
		  pstmt.setLong(2, mediaId);
		  pstmt.setString(3, sourceqId);
		  rs = pstmt.executeQuery();
		  while(rs.next()){
			 String name = rs.getString("name");
			 String consumerid =  rs.getString("consumerid");
			 System.out.println(" source media Consumer Name : "+name+" for consumerID "+consumerid);
		  }*/
		
	    }catch(Exception ex){
			System.out.println(" Problem in getSourceMediaConsumer : "+ex.getMessage());
			throw ex;
		}
	}
	
	
	
	
    public static List<Map<String, List<String>>> readFromFile(String fileName) throws Exception
    {
      List<Map<String, List<String>>> processList = new ArrayList<Map<String, List<String>>>();	
      try{	
	       FileInputStream fis = new FileInputStream(fileName); 
	       DataInputStream in = new DataInputStream(fis);
	       BufferedReader br = new BufferedReader(new InputStreamReader(in));
	       String str;
	       while ((str = br.readLine()) != null) {
	    	 if( str != null && (str.trim()).startsWith("updated with")){  
	    		 System.out.println(str);
	    		 processList.add(parseInput(str));
	    	 }
	       }
	       in.close();
      }catch(Exception ex){
    	  System.out.println(" Problem in readFromFile : "+ex.getMessage());
    	  throw ex;
      }
      return processList;
    } 

    
    
    public static Map<String, List<String>> parseInput(String inpString) throws Exception
    {
      Map<String, List<String>> map = new HashMap<String, List<String>>();	
      try{
    	  if(inpString != null && !("").equals(inpString)){
    		 String[] ipstrArr = inpString.split(",");
    		 List<String> consumerIdList = new ArrayList<String>();
    		 for(int i=0; i<ipstrArr.length ;i++){
    			//System.out.println(" ipstrArr i = "+i+" value : "+ipstrArr[i]); 
    			String processString = ipstrArr[i];
    			if(processString.indexOf(":") != -1){
    				processString = processString.substring(processString.indexOf(":")+1);
    			}
    		    //System.out.println(" processString -->"+processString.trim());
    			String assignedStr = "";
    			if(processString != null && !("").equals(processString)){
    				assignedStr = processString.trim();
    			}
    			if(i==0){
    		    	List<String> mediaNameList = new ArrayList<String>();
    		    	mediaNameList.add(assignedStr);
    		    	map.put("mediaName", mediaNameList);	
    		    }
    		    if(i==1){
    		    	List<String> testList = new ArrayList<String>();
    		    	testList.add(assignedStr);
    		    	map.put("testId", testList);
    		    }
    		    if(i==2){
    		    	List<String> mediaIdList = new ArrayList<String>();
    		    	mediaIdList.add(assignedStr);
    		    	map.put("mediaId", mediaIdList);
    		    }
    		     
    		    if(i==3){
    		    	consumerIdList.add(assignedStr);
    		    	map.put("consumerid", consumerIdList);
    		    }
    		    if(i==4){
    		    	consumerIdList.add(assignedStr);
    		    	map.put("consumerid", consumerIdList);
    		    }
    		 }
    	  }
      }catch(Exception ex){
    	System.out.println(" Problrm in parseInput : "+ex.getMessage()); 
    	throw ex;
      }	
      return map;
    } 









}
