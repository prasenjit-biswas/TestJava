package utility;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import Dataretrieval.util.OracleConnection;

import com.mcgrawhill.ezto.test.questions.question;
import com.mcgrawhill.ezto.test.questions.question_types.lsi;

public class GetPickerForATest {

	public static final String SECTIONTITLE  		= "selectionTitle";
	  public static final String DISPLAYTYPE   		= "displayType";
	  public static final String ACTUALTYPE    		= "actualType";
	  public static final String HASFORMULA    		= "hasFormula" ;
	  public static final String LINES         		= "lines";
	  public static final String SHORTANSWER   		= "shortanswer"; 
	  public static final String QPALETTE      		= "Qpalette"; 
	  public static final String FLASHTYPE          = "flashType";
	  public static final String INSTRUCTOR_INFO     = "instructor_info"; 
	  public static final String LOCALRANDOMS 	    = "localRandoms"; 
	  public static final String ANSWERS  		    = "answers"; 
	  public static final String SOURCEINFO		    = "sourceInfo"; 
	  public static final String ITERATIONLEVEL	    = "iterationlevel"; 
	  public static final String ISCHAT     	    = "chat"; 
	  public static final String ISCHEMDRAW    	    = "chemdraw"; 
	  public static final String CONTENTLINKS  	    = "contentLinks"; 
	  public static final String CONTENTLINK  	    = "contentLink"; 
	  public static final String LINKTYPE   	    = "linkType"; 
	  public static final String LINKPOLICY   	    = "linkPOLICY"; 
	  public static final String ANSWER     	    = "answer";
	  public static final String TYPE       	    = "type"; 
	  public static final String ANSWERPROPERTIES   = "answerProperties"; 
	  public static final String PALETTE            = "palette";
	  public static final String ANSWER_TYPE        = "answertype";
	
	  
	 /*public static final String SELECT_QUESTION_XREF_FOR_TESTID = "SELECT xref.questionid,xref.questiontype," +
	  		"xref.typeidentifier,xref.points,xref.maxpoints,xref.reftag," +
	  		"xref.pickerxml.extract('/').getStringVal() pickerxml"
			+ " FROM test_question_xref xref WHERE testid = ? AND questionid = ?";*/
	  
	 /*public static final String SELECT_QUESTION_XREF_FOR_TESTID = "SELECT xref.questionid,xref.questiontype," +
		"xref.typeidentifier,xref.points,xref.maxpoints,xref.reftag," +
		"xref.pickerxml.getClobval() pickerxml"
		+ " FROM test_question_xref xref WHERE testid = ? AND questionid = ?";
*/
	  
	 public static final String SELECT_QUESTION_XREF_FOR_TESTID = 
	 "SELECT xref.pickerxml.getClobval() pickerxml FROM abc xref WHERE xref.name = '10'";
	 
	
	  public static void main(String args[]){
		  
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  String testID ="13252698006404539";//"13252698003162963";
		  String questionID = "13252698006404254";//"13252698003162984";
		  try{
			  List<QuestionXrefTO> questionList = new ArrayList<QuestionXrefTO>();
			  con = OracleConnection.getConnection();
			  long start= (new java.util.Date()).getTime();
			  pstmt = con.prepareStatement(SELECT_QUESTION_XREF_FOR_TESTID);
			  //pstmt.setString(1, testID);
			  //pstmt.setString(2, questionID);
			  rs = pstmt.executeQuery();
			  while(rs.next()){
				    QuestionXrefTO questioXrefTO = new QuestionXrefTO();
					/*
				    questioXrefTO.setQuestionID(rs.getString("questionid"));
					questioXrefTO.setTestID(testID);
					// String quesType = rs.getString("questiontype");
					
					questioXrefTO.setQuestionType(rs.getInt("questiontype"));
					questioXrefTO.setQuestionTypeIdentifier(rs.getString("typeidentifier"));
					String points = rs.getString("points");
					if (points != null) {
						questioXrefTO.setPoints(rs.getString("points"));
					}
					String maxpoints = rs.getString("maxpoints");
					if (maxpoints != null) {
						questioXrefTO.setMaxpoint(maxpoints);
					}
					if (rs.getString("reftag") != null) {
						questioXrefTO.setRefTag(rs.getString("reftag"));
					} else {
						questioXrefTO.setRefTag("");
					}*/
					
					String pickerXml = rs.getString("pickerxml");///rs.getClob("pickerxml").toString();
					//byte[] pickerXml = rs.getBytes("pickerxml");
					//Reader pickerXml = rs.getCharacterStream("pickerxml");
					//java.sql.Clob pickerXml = rs.getClob("pickerxml");
					System.out.println("pickerXml - " + pickerXml);
					questioXrefTO.setPickerXML(pickerXml);
					questionList.add(questioXrefTO);
			  }
			  
			  
			  if(!questionList.isEmpty()){
				  Iterator itr =  questionList.iterator();
				  while(itr.hasNext()){
					  QuestionXrefTO questioXrefTO=  (QuestionXrefTO)itr.next();
					  questioXrefTO = getPickerXmlTO(questioXrefTO.getPickerXML(),questioXrefTO);
					  questioXrefTO.setPickerXML(null);
					  System.out.println("Now printing QuestionXrefTO for questionID "+questioXrefTO.getQuestionID());
					  System.out.println(questioXrefTO);
					  
				  }
				  
			  }
			  System.out.println("  Time Taken time " + Long.toString((new java.util.Date()).getTime() - start) +" ms");
			  
			  
			 
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			  OracleConnection.releaseResources(rs);
			  OracleConnection.releaseResources(pstmt);
			  OracleConnection.releaseResources(con);
			  
		  }
		  
	  }
	  
	  
	  
	public static QuestionXrefTO getPickerXmlTO(String inputXML , QuestionXrefTO questionXrefTO) throws Exception
	  {
		 ListIterator iter = null;
		 java.util.List theData = null;
		 try{
			 if(inputXML == null || ("").equals(inputXML))
			 {
				 System.out.println("MySQLPickerXmlDAO,getPickerXmlTO... Input XML coming as NULL or Empty.");
			 }
			 Document theXML = (new SAXBuilder()).build(new StringReader(inputXML));
			 //Document theXML = (new richProperties().getBuilder()).build(new StringReader(inputXML));
			 Element picker = theXML.getRootElement();  
			 if(picker != null)
			 {
			    if( picker.getName() != null && "question".equals( picker.getName() )  )
			    {
			    	if( picker.getChild( SECTIONTITLE ) != null)
			    	{
			    		questionXrefTO.setSelectionTitle( picker.getChild( SECTIONTITLE ).getText() );
			    	}
			    	if( picker.getChild( DISPLAYTYPE ) != null)
			    	{
			    		questionXrefTO.setDisplayType( picker.getChild( DISPLAYTYPE ).getText() );
			    	}
			    	if( picker.getChild( ACTUALTYPE ) != null)
			    	{
			    		questionXrefTO.setActualType( picker.getChild( ACTUALTYPE ).getText() );
			    	}
			    	if( picker.getChild( HASFORMULA ) != null)
			    	{
			    		questionXrefTO.setHasFormula(picker.getChild( HASFORMULA ).getText());
			    	}
			    	if( picker.getChild( LINES ) != null)
			    	{
			    		questionXrefTO.setLines( Integer.parseInt( picker.getChild( LINES ).getText() ) );
			    	}
			    	if( picker.getChild( SHORTANSWER ) != null)
			    	{
			    		questionXrefTO.setShortanswer( picker.getChild( SHORTANSWER ).getText() );
			    	}
			    	Element qProperties  = picker.getChild("questionProperties"); 
			    	Map<String, String> quesProperties = questionXrefTO.getQuestionProperties();
			    	if(qProperties != null)
			    	{
			    		if(qProperties.getChild( PALETTE )!= null)
						{
			    			quesProperties.put(PALETTE, qProperties.getChild( PALETTE ).getText());
						}
						if(qProperties.getChild( ANSWER_TYPE )!= null)
						{
							quesProperties.put(ANSWER_TYPE, qProperties.getChild( ANSWER_TYPE ).getText());
						}
						if(qProperties.getChild( INSTRUCTOR_INFO )!= null)
						{
							quesProperties.put(INSTRUCTOR_INFO, qProperties.getChild( INSTRUCTOR_INFO ).getText());
						}
						if(qProperties.getChild( ITERATIONLEVEL )!= null)
						{
							quesProperties.put(ITERATIONLEVEL, qProperties.getChild( ITERATIONLEVEL ).getText());
						}
						if(qProperties.getChild( question.COMPLETE_INCOMPLETE_GRADING )!= null)
						{
							quesProperties.put(question.COMPLETE_INCOMPLETE_GRADING, qProperties.getChild( question.COMPLETE_INCOMPLETE_GRADING ).getText());
						}
						if(qProperties.getChild( question.CONNECT_FORCED_SCORING )!= null)
						{
							quesProperties.put(question.CONNECT_FORCED_SCORING, qProperties.getChild( question.CONNECT_FORCED_SCORING ).getText());
						}
						if(qProperties.getChild( lsi.CUSTOM_QUESTION_ID )!= null)
						{
							//questionProperties.put(lsi.CUSTOM_QUESTION_ID, qProperties.getChild( lsi.CUSTOM_QUESTION_ID ).getText());
							questionXrefTO.setCustomQuestionID(qProperties.getChild( lsi.CUSTOM_QUESTION_ID ).getText());
						}
						
						if(qProperties.getChild( lsi.CUSTOM_QUESTION_REBUILD )!= null)
						{
							//questionProperties.put(lsi.CUSTOM_QUESTION_REBUILD, qProperties.getChild( lsi.CUSTOM_QUESTION_REBUILD ).getText());
							questionXrefTO.setCustomQuestionRebuild(qProperties.getChild( lsi.CUSTOM_QUESTION_REBUILD ).getText());
						}
			    	}
			    	
			    	if( picker.getChild( LOCALRANDOMS ) != null)
			    	{
			    		questionXrefTO.setLocalRandoms( picker.getChild( LOCALRANDOMS ).getText() );
			    	}
			    	if( picker.getChild( ISCHAT ) != null)
			    	{
			    		questionXrefTO.setIsChat( picker.getChild( ISCHAT ).getText() );
			    	}
			    	if( picker.getChild( ISCHEMDRAW ) != null)
			    	{
			    		questionXrefTO.setIsChemDraw( picker.getChild( ISCHEMDRAW ).getText() );
			    	}
			    	Element contentLinks = picker.getChild( CONTENTLINKS );
			    	if(contentLinks != null && ("contentLinks").equals(contentLinks.getName()))
			    	{
			    		List<Map<String, String>> contentList = questionXrefTO.getContentLinks();
			    		Iterator contentLinkItr = null;
			    		List contentLink =  contentLinks.getChildren( CONTENTLINK );
				    	if(contentLink != null && !contentLink.isEmpty() )
				    	{
				    	  contentLinkItr = contentLink.iterator();
				    	}
				    	if(contentLinkItr != null)
				    	{
				    		while(contentLinkItr.hasNext() )
				    		{
				    			HashMap<String,String> linkMap = new HashMap<String,String>();
				    			Element contentLinkEle =(Element)contentLinkItr.next();
				    			if(contentLinkEle.getChild( LINKTYPE )!= null)
				    			{
				    				linkMap.put(LINKTYPE, contentLinkEle.getChild( LINKTYPE ).getText());
				    			}
				    			if(contentLinkEle.getChild( LINKPOLICY )!= null)
				    			{
				    				linkMap.put(LINKPOLICY, contentLinkEle.getChild( LINKPOLICY ).getText());
				    			}
				    			contentList.add(linkMap);
				    		}
				    	}
			    	}
			    	Element answers = picker.getChild( ANSWERS );
			    	if( answers != null && ( ANSWERS ).equals(answers.getName()) )
			    	{
			    		List<Map<String, String>> answersList = questionXrefTO.getAnswers();
			    		Iterator ansListItr = null;
			    		List ansList =  answers.getChildren( ANSWER );
			    		if(ansList != null && !ansList.isEmpty())
			    		{
			    			ansListItr = ansList.iterator(); 
			    		}
			    		if(ansListItr != null)
			    		{
			    			while(ansListItr.hasNext())
			    			{
			    				HashMap<String, String> ansMap = new HashMap<String, String>(); 
							    Element answer = (Element)ansListItr.next();
							    if(answer.getChild( TYPE )!= null)
							    {
							    	ansMap.put(TYPE, answer.getChild( TYPE ).getText() );
							    }
							    if(answer.getChild( FLASHTYPE )!= null)
							    {
							    	ansMap.put(FLASHTYPE, answer.getChild( FLASHTYPE ).getText() );
							    }
							    Element ansProperties = answer.getChild( ANSWERPROPERTIES );
							    if(ansProperties != null){
							    	if(ansProperties.getChild( PALETTE )!= null)
							    	{
							    		ansMap.put(PALETTE, ansProperties.getChild( PALETTE ).getText() );	
							    	}
							    }
							    answersList.add(ansMap); 
			    			}	
			    		}
			    	}
			    	if( picker.getChild( SOURCEINFO ) != null)
			    	{
			    		questionXrefTO.setSourceInfo( picker.getChild( SOURCEINFO ).getText() );
			    	}
					 
				 }else{
				    System.out.println("MySQLPickerXmlDAO,getPickerXmlTO... picker Question XML Name mismatch.");	 
				 }
			  }else{
				  System.out.println("MySQLPickerXmlDAO,getPickerXmlTO... ROOT Element NULL.");
			  }
		  }catch(Exception e){
			  System.out.println("MySQLPickerXmlDAO,getPickerXmlTO... Exception ");
			  e.printStackTrace();
			  throw e;
		  }
		  return questionXrefTO; 	
	  }
	
}



