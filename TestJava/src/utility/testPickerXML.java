package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import Dataretrieval.util.MySqlConnection;

public class testPickerXML {
	
	
  public static void main(String[] args) {
	  
	  Connection con  = null;
	  PreparedStatement pstmt =null;
	  ResultSet rs = null;
	  
	  
	  
	  File file = new File("C:/Picker.xml");
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
	    String a = strContent.toString(); 
	    System.out.println(a +" length : "+a.length());
	    
	    //String query = "INSERT INTO test_question_xref (questionid, testid, questiontype, typeidentifier, "
		 //   + "maxpoints, points, reftag, pickerxml, creationtime, pickerxmltimestamp ) " +
		  //  		"VALUES( '1', '2', 1, '1', 1, '1', 'rf', ?, SYSDATE() , SYSDATE())";
	    
	    String query = "UPDATE test_question_xref SET  pickerxml = ?" +
        "WHERE questionid = ? AND testid = ? ";
	  
	 try{
		 con = MySqlConnection.getConnection();
		 pstmt = con.prepareStatement(query);
		 pstmt.setString(1, a);
		 //pstmt.setClob(1, new StringReader(a));
		 pstmt.setString(2, "1");
		 pstmt.setString(3, "2");
		 
		 pstmt.executeUpdate();
		 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
     
     
     
     
     
     
  }
  
  public void getPickerXML(String inputXML)
	{/*
	  ListIterator iter = null;
	  java.util.List theData = null;
	  try{
		  System.out.println("Input XML String ---> "+inputXML);
		  Document theXML = (new SAXBuilder()).build(new StringReader(inputXML));
		  Element picker = theXML.getRootElement();  
		  if(picker != null){
			 if( picker.getName() != null && "question".equals( picker.getName() )  )
			 {
				 if( picker.getChild("selectionTitle") != null){
					System.out.println("selectionTitle --> "+picker.getChild("selectionTitle").getText());  
					 
				 }
				 if( picker.getChild("displayType") != null){
					 System.out.println("displayType --> "+picker.getChild("displayType").getText());
				 }
				 if( picker.getChild("actualType") != null){
					 System.out.println("actualType --> "+picker.getChild("actualType").getText());
					  
				 }
				 if( picker.getChild("hasFormula") != null){
					 System.out.println("hasFormula --> "+picker.getChild("hasFormula").getText());
				 }
				 if( picker.getChild("lines") != null){
					 System.out.println("lines --> "+picker.getChild("lines").getText());
				 }
				 if( picker.getChild("shortanswer") != null){
					 System.out.println("shortanswer --> "+picker.getChild("shortanswer").getText());
				 }
				 Element qProperties = picker.getChild("questionProperties");
				 if(qProperties != null){
					 System.out.println("palette --> "+qProperties.getChild("palette").getText());
				 }
			 }else{
				 
			 }
		  }
	  }catch(Exception e){
		  
	  }
	 	
	*/
	  
     System.out.println("ipxml --> "+inputXML);
	  try{
		  Document theXML = (new SAXBuilder()).build(new StringReader(inputXML));
		  VectorAdapter answersList = new VectorAdapter();
		  Element answers = theXML.getRootElement();
		  Iterator ansListItr = null;
		  if( answers != null && ("answers").equals(answers.getName()))
		  {
			 List ansList =  answers.getChildren("answer");
			 if(ansList != null && !ansList.isEmpty())
			 {
				 ansListItr = ansList.iterator();
			 }
			 if(ansListItr != null)
			 {
				while( ansListItr.hasNext() )
				{
					HashMap<String, String> ansMap = new HashMap<String, String>(); 
				    Element answer = (Element)ansListItr.next();
				    if(answer.getChild("type")!= null)
				    {
				    	ansMap.put("type", answer.getChild("type").getText() );
				    }
				    if( answer.getChild("flashType")!= null )
				    {
				    	ansMap.put("flashType", answer.getChild("flashType").getText() );
				    }
				    Element ansProperties = answer.getChild("answerProperties");
				    if(ansProperties != null){
				    	if(ansProperties.getChild("palette")!= null)
				    	{
				    		ansMap.put("palette", ansProperties.getChild("palette").getText() );	
				    	}
				    }
				    answersList.add(ansMap);       
				} 
			 }
		  }
		  
		  if(answersList != null && !answersList.isEmpty()){
			  Iterator itr = answersList.iterator();
			  while(itr.hasNext()){
				HashMap h = (HashMap) itr.next();
				System.out.println("type --> "+h.get("type"));
				System.out.println("flashType --> "+h.get("flashType"));
				System.out.println("palette --> "+h.get("palette"));
			  }
		  }
		  
		  
	  }catch(Exception e){
		System.out.println(" OraclePickerXmlDAO,getContentLinks Error in parsing ContentLinks XML. ");  
	    e.printStackTrace(); 
	  }
  
	
	}
  
}
