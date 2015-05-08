package Datafix;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

public class Question {

    /** My SQL Dev */
	
	static String mysqlurl = "jdbc:mysql://nvldb06.eppg.com:3306/";
	static String mysqldb = "ezto_dev";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	static String mysqluser = "ezto_dev";
	static String mysqlpass = "pixie";
	

	
	/** EZTO Dev*/
	/*
	static String oraurl = "jdbc:oracle:thin:@nj09mhe0234-vip.eppg.com:1521:eztodev1";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztodev";
	*/
	
	/** EZTO QALive*/
	
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "ezto123";
	
	
	/** EZTO PQA*/
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztoload";
	*/
	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB;
	
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	static String LICENSE_TYPE_AMAZON_KEYS	= "amazonKeys";
	
	private boolean usePopup;
	private boolean questionIsHTML;
	private boolean mathColumns;
	private boolean indent;
	private boolean larger;
	private boolean smaller;
	private boolean autoWrap;
	private boolean questionIsMathML;
	private boolean questionIsTeX;
	private boolean questionIsLaTeX;
	private boolean longQuestion;
	private int id;
	private String sqlID;
	private int     storage;
	private int     maxPoints;
	private int     boxWidth;
	private int     boxHeight;
	private String  qtext;
	private String  selectionTitle;
	private String  source;
	private String  sourceInfo;
	private String  referenceTag;
	private String  pageTag;
	private boolean  hasMedia;
	private int  choiceCount;
	private String  choices[];
	private int     pointsCount;
	private String  points[];
	private int     feedbackCount;
	private String  feedback[];
	private int     localCount;
	private int     answerFeedbackCount;
	private int     theCount;
	private String  hints[];
	private String  questionProperties;
	private String  content_link_title;
	private String  content_link_url;
	private String  content_link_type;
	private String  content_link_policy;
	private String  content_link_html;
	private String  questionType;
	private String  defaultAnswer;
	
	public String getDefaultAnswer() {
		return defaultAnswer;
	}

	public void setDefaultAnswer(String defaultAnswer) {
		this.defaultAnswer = defaultAnswer;
	}

	public boolean isUsePopup() {
		return usePopup;
	}

	public void setUsePopup(boolean usePopup) {
		this.usePopup = usePopup;
	}
	
	public boolean isQuestionIsHTML() {
		return questionIsHTML;
	}

	public void setQuestionIsHTML(boolean questionIsHTML) {
		this.questionIsHTML = questionIsHTML;
	}

	public boolean isMathColumns() {
		return mathColumns;
	}

	public void setMathColumns(boolean mathColumns) {
		this.mathColumns = mathColumns;
	}

	public boolean isIndent() {
		return indent;
	}

	public void setIndent(boolean indent) {
		this.indent = indent;
	}

	public boolean isLarger() {
		return larger;
	}

	public void setLarger(boolean larger) {
		this.larger = larger;
	}

	public boolean isSmaller() {
		return smaller;
	}

	public void setSmaller(boolean smaller) {
		this.smaller = smaller;
	}

	public boolean isAutoWrap() {
		return autoWrap;
	}

	public void setAutoWrap(boolean autoWrap) {
		this.autoWrap = autoWrap;
	}

	public boolean isQuestionIsMathML() {
		return questionIsMathML;
	}

	public void setQuestionIsMathML(boolean questionIsMathML) {
		this.questionIsMathML = questionIsMathML;
	}

	public boolean isQuestionIsTeX() {
		return questionIsTeX;
	}

	public void setQuestionIsTeX(boolean questionIsTeX) {
		this.questionIsTeX = questionIsTeX;
	}

	public boolean isQuestionIsLaTeX() {
		return questionIsLaTeX;
	}
	
	public void setQuestionIsLaTeX(boolean questionIsLaTeX) {
		this.questionIsLaTeX = questionIsLaTeX;
	}

	public boolean isLongQuestion() {
		return longQuestion;
	}

	public void setLongQuestion(boolean longQuestion) {
		this.longQuestion = longQuestion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSqlID() {
		return sqlID;
	}

	public void setSqlID(String sqlID) {
		this.sqlID = sqlID;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}

	public String getQtext() {
		return qtext;
	}

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}

	public String getSelectionTitle() {
		return selectionTitle;
	}

	public void setSelectionTitle(String selectionTitle) {
		this.selectionTitle = selectionTitle;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceInfo() {
		return sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}

	public String getReferenceTag() {
		return referenceTag;
	}

	public void setReferenceTag(String referenceTag) {
		this.referenceTag = referenceTag;
	}

	public String getPageTag() {
		return pageTag;
	}

	public void setPageTag(String pageTag) {
		this.pageTag = pageTag;
	}

	public boolean isHasMedia() {
		return hasMedia;
	}

	public void setHasMedia(boolean hasMedia) {
		this.hasMedia = hasMedia;
	}

	public int getChoiceCount() {
		return choiceCount;
	}

	public void setChoiceCount(int choiceCount) {
		this.choiceCount = choiceCount;
	}

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public int getPointsCount() {
		return pointsCount;
	}

	public void setPointsCount(int pointsCount) {
		this.pointsCount = pointsCount;
	}

	public String[] getPoints() {
		return points;
	}

	public void setPoints(String[] points) {
		this.points = points;
	}

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public String[] getFeedback() {
		return feedback;
	}

	public void setFeedback(String[] feedback) {
		this.feedback = feedback;
	}

	public int getLocalCount() {
		return localCount;
	}

	public void setLocalCount(int localCount) {
		this.localCount = localCount;
	}

	public int getAnswerFeedbackCount() {
		return answerFeedbackCount;
	}

	public void setAnswerFeedbackCount(int answerFeedbackCount) {
		this.answerFeedbackCount = answerFeedbackCount;
	}

	public int getTheCount() {
		return theCount;
	}

	public void setTheCount(int theCount) {
		this.theCount = theCount;
	}

	public String[] getHints() {
		return hints;
	}

	public void setHints(String[] hints) {
		this.hints = hints;
	}

	public String getQuestionProperties() {
		return questionProperties;
	}

	public void setQuestionProperties(String questionProperties) {
		this.questionProperties = questionProperties;
	}

	public String getContent_link_title() {
		return content_link_title;
	}

	public void setContent_link_title(String content_link_title) {
		this.content_link_title = content_link_title;
	}

	public String getContent_link_url() {
		return content_link_url;
	}

	public void setContent_link_url(String content_link_url) {
		this.content_link_url = content_link_url;
	}

	public String getContent_link_type() {
		return content_link_type;
	}

	public void setContent_link_type(String content_link_type) {
		this.content_link_type = content_link_type;
	}

	public String getContent_link_policy() {
		return content_link_policy;
	}

	public void setContent_link_policy(String content_link_policy) {
		this.content_link_policy = content_link_policy;
	}

	public String getContent_link_html() {
		return content_link_html;
	}

	public void setContent_link_html(String content_link_html) {
		this.content_link_html = content_link_html;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public static void main(String argv[]) throws Exception{
    
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;       
    
    try {
    	con = getConnection();
    	
    	//getFlash(con);
    	Question q = getQuestion(con,"13110853341021000");
    	System.out.println(" Title " + q.getSelectionTitle() + "(" + q.getQuestionType() +")");
    	//getTest1(con);
    	//getTest(con);
        /*
        DataInputStream theInput= new DataInputStream(new ByteArrayInputStream(theBytes.toByteArray()));
        
        int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++){
			System.out.println("names (" + (i+1) + ") : "+ theInput.readUTF());
		}
			
		// read the values
		int valueCount= theInput.readInt();
		for (int i=0 ; i<valueCount ; i++){
			System.out.println("values (" + (i+1) + ") : "+ readString(theInput));
		}
		*/
        
    } catch(SQLException exSQLException){
    	System.out.print(exSQLException);
	    exSQLException.printStackTrace();
    	if(con != null){
    	  //con.rollback();
	    }
	} catch(Exception exException){
		System.out.print(exException);
	    exException.printStackTrace();
		if(con != null){
    	  //con.rollback();
	    }
    }finally{
    	if(rs != null){
    	  rs.close();
    	}
		if(pst != null){
    	  pst.close();
		}
		if(con != null){
		  con.close();
		}
    }
  }
	
	public static Connection getConnection() throws SQLException, Exception{
	  Connection con = null;
	  System.out.println("database : " + database);
	  if(database.equals(ORACLE_DB)){
    	Class.forName(oradriver);
        con = DriverManager.getConnection(oraurl, orauser, orapass);
      } else if(database.equals(MYSQL_DB)){
    	Class.forName(mysqldriver);
        con = DriverManager.getConnection(mysqlurl + mysqldb, mysqluser, mysqlpass);
      }
	  return con;
	}
	
	private static HashMap<String, String> hashFromStream( InputStream bStream ){
		HashMap<String, String>  result= new HashMap<String, String> ();
		
		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				
				int count= input.readInt();
				for (int i= 0 ; i< count ; i++) 
				{
					String key= input.readUTF();
					String value= readString(input);
					result.put( key, value );
				}
				bStream.close();
			} 
			catch (IOException i) 
			{
				System.out.println("IOException in tp_sql.hashFromStream()");
				i.printStackTrace();
			}
		}
		
		return( result );
	}
	
	static String readString( DataInputStream input )
	  throws IOException{
	  String result= input.readUTF();
	  if (result.equals(LONG_STRING))
	  {
		//System.out.println("inputting " + LONG_STRING);
		int arrayLength= input.readInt();
		byte[] barray= new byte[ arrayLength ];
		input.read( barray, 0, arrayLength );
		result= new String( barray, "UTF-8" );
	  }
	  return(result);
    }
	
	private static String getQuestionType(int questionType){
		String textQuestionType = "";
		switch(questionType){
		  case 1: 
			    textQuestionType = "yesNO";
			    break;
		  case 2: 
		        textQuestionType = "trueFalse";
		        break;
		  case 3: 
		        textQuestionType = "multipleChoice";
		        break;
		  case 4: 
		        textQuestionType = "fillBlank";
		        break;
		  case 5: 
		        textQuestionType = "checkAll";
		        break;
		  case 6: 
		        textQuestionType = "survey";
		        break;
		  case 7: 
		        textQuestionType = "sectionbreak";
		        break;
		  case 8: 
		        textQuestionType = "matching";
		        break;
		  case 9: 
		        textQuestionType = "ranking";
		        break;
		  case 101: 
		        textQuestionType = "worksheet";
		        break;
		  case 201: 
		        textQuestionType = "lsi";
		        break;
		  case 301: 
		        textQuestionType = "document";
		        break;
		  default: 
			    textQuestionType = "undefined question type";
			    break;
		}
		return textQuestionType;		
	}
	
	private static void printQuestion(Question question){
		System.out.println("usePopup : " + question.isUsePopup());
		
	}
	
	public static Question getQuestion(Connection con,String questionID) throws Exception{
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    int format = -1;
	    InputStream theData = null;
	    Question question = new Question();
	    try{
		pst = con.prepareStatement("SELECT questionID, format, data FROM questions WHERE questionID = ?");
    	pst.setString(1, questionID);    	
    	rs = pst.executeQuery();
    	
        if (rs.next()) {
        	format = rs.getInt("format");
        	theData = rs.getBinaryStream("data");
        	DataInputStream input= new DataInputStream(theData);
            int questionType= input.readInt();
            
            question.setQuestionType(getQuestionType(questionType));
            //System.out.println("format : " + format);
            //System.out.println("questionType : " + questionType);
            question = getQuestion(question,input, format);
            
            printQuestion(question);
        }
	    }
	    catch(Exception ex){
	    }
	    finally{
          if(rs != null){
        	rs.close();
          }
    	  if(pst != null){
        	pst.close();
    	  }
  	    }
	    return question;
    }
	
	private static Question getQuestion(Question question,DataInputStream theInput, int format )
	throws Exception {
		
	try {
		
		// read the booleans
		//usePopup= theInput.readBoolean();
		question.setUsePopup(theInput.readBoolean());
		System.out.println("usePopup : " + question.isUsePopup());
		
		if (format >= 8) {
			//questionIsHTML= theInput.readBoolean();			
			question.setQuestionIsHTML(theInput.readBoolean());
			//print("question.isQuestionIsHTML", question.isQuestionIsHTML());
			
			//mathColumns= theInput.readBoolean();
			question.setMathColumns(theInput.readBoolean());
			//System.out.println("mathColumns : " + theInput.readBoolean());
			//indent= theInput.readBoolean();
			//System.out.println("indent : " + theInput.readBoolean());
			question.setIndent(theInput.readBoolean());
			//larger= theInput.readBoolean();
			//System.out.println("larger : " + theInput.readBoolean());
			question.setLarger(theInput.readBoolean());
			//smaller= theInput.readBoolean();
			//System.out.println("smaller : " + theInput.readBoolean());
			question.setSmaller(theInput.readBoolean());
		}
		
		if (format >= 24){
			//autoWrap= theInput.readBoolean();
			//System.out.println("autoWrap : " + theInput.readBoolean());
			question.setAutoWrap(theInput.readBoolean());
		}
		
		if (format >= 43) {
			//questionIsMathML= theInput.readBoolean();
			//System.out.println("questionIsMathML : " + theInput.readBoolean());
			question.setQuestionIsHTML(theInput.readBoolean());
			//questionIsTeX= theInput.readBoolean();
			//System.out.println("questionIsTeX : " + theInput.readBoolean());
			question.setQuestionIsLaTeX(theInput.readBoolean());
			//questionIsLaTeX= theInput.readBoolean();
			//System.out.println("questionIsLaTeX : " + theInput.readBoolean());
			question.setQuestionIsLaTeX(theInput.readBoolean());
		}
		
		// additional booleans go here
		if (format >= 434){
			//longQuestion= theInput.readBoolean();
			//System.out.println("longQuestion : " + theInput.readBoolean());
			question.setLarger(theInput.readBoolean());
		}
		
		
		// read the ints
		//int id= theInput.readInt();
		
		//System.out.println("id : " + theInput.readInt());
		question.setId(theInput.readInt());
		
		if (format >= 400){
			String sqlID= theInput.readUTF();
			question.setSqlID(sqlID);
			//System.out.println("sqlID : " + sqlID);
		}
		
		question.setStorage(theInput.readInt());
		//int storage= theInput.readInt();

		int maxPoints= theInput.readInt();
		if (format >= 43) {
			int boxWidth= theInput.readInt();
			int boxHeight= theInput.readInt();
			question.setBoxWidth(boxWidth);
			question.setBoxHeight(boxHeight);
		}

		// additional ints here
		
		
		// read the strings
		//System.out.println("qtext : " + readString(theInput));
		question.setQtext(readString(theInput));
		//defaultAnswer= theInput.readUTF();
		//System.out.println("defaultAnswer : " + theInput.readUTF());
		question.setDefaultAnswer(theInput.readUTF());
		// formerly html was stored - now we use the string for v7select/classware titles
		//selectionTitle= theInput.readUTF();
		question.setSelectionTitle(theInput.readUTF());
		//System.out.println("selectionTitle : " + theInput.readUTF());

		// additional strings here
		//if (format >= 414) source= theInput.readUTF();
		if (format >= 414){
		  question.setSource(theInput.readUTF());
		  //System.out.println("source : " + theInput.readUTF());
		}
		//if (format >= 416) sourceInfo= theInput.readUTF();
		if (format >= 416){
		  //System.out.println("sourceInfo : " + theInput.readUTF());
		  question.setSourceInfo(theInput.readUTF());
		}
		//if (format >= 423) referenceTag= theInput.readUTF();
		if (format >= 423){
		  //System.out.println("referenceTag : " + theInput.readUTF());
		  question.setReferenceTag(theInput.readUTF());
		  System.out.println("referenceTag : " + question.getReferenceTag());
		}
		
		//if (format >= 424) pageTag= theInput.readUTF();
		if (format >= 424){
		  //System.out.println("pageTag : " + theInput.readUTF());
		  question.setPageTag(theInput.readUTF());
		}
		
		// read the questionMedia if any
		boolean hasMedia= theInput.readBoolean();
		question.setHasMedia(hasMedia);
		
		//System.out.println("hasMedia : " + hasMedia);
		//if (hasMedia)
			//questionMedia= new richMedia( theInput, format );
		
		
		// read choices
		int choiceCount= theInput.readInt();
		question.setChoiceCount(choiceCount);
		//System.out.println("choiceCount : " + choiceCount);
		for (int i=0 ; i<choiceCount ; i++){
			//choices.addElement( theInput.readUTF() );
			//question.setChoices(theInput.readUTF());
			//System.out.println("choices " + (i+1) +" : " + theInput.readUTF());
			String choices = theInput.readUTF();
		}
			
		
		// read points
		int pointsCount= theInput.readInt();
		//System.out.println("pointsCount : " + pointsCount);
		question.setPointsCount(pointsCount);
		for (int i=0 ; i<pointsCount ; i++){
			//points.addElement( theInput.readUTF() );
			String points = theInput.readUTF();
			//System.out.println("pointsCount " + (i+1) +" : " + theInput.readUTF());
		}
			
		
		// read feedback
		int feedbackCount= theInput.readInt();
		//System.out.println("feedbackCount : " + feedbackCount);
		question.setFeedbackCount(feedbackCount);
		for (int i=0 ; i<feedbackCount ; i++){
			//feedback.addElement( readString( theInput ) );
            String feedback = readString( theInput );
			//System.out.println("feedback " + (i+1) +" : " + readString( theInput ));
		}
			
		
		// read the local randoms
		int localCount= theInput.readInt();
		//System.out.println("localCount : " + localCount);
		question.setLocalCount(localCount);
		for (int i=0 ; i<localCount ; i++){
			//localRandoms.addElement( new randomVariable( theInput, format ) );
			randomVariable(theInput, format );
		}		
		
		// read the correctAnswerFeedback Vector
		int answerFeedbackCount= theInput.readInt();
		//System.out.println("answerFeedbackCount : " + answerFeedbackCount);
		question.setAnswerFeedbackCount(answerFeedbackCount);
		for (int i=0 ; i<answerFeedbackCount ; i++){
			//correctAnswerFeedback.addElement( readString( theInput ) );
			String correctAnswerFeedback;
			correctAnswerFeedback = readString( theInput );
			//System.out.println("correctAnswerFeedback " + (i+1) +" : " + readString( theInput ));
		}		
		
		// read additional subclasses here
		//calculatedVariables= new Vector();
		if (format >= 430)
		{
			int theCount= theInput.readInt();
			//System.out.println("theCount 1 : " + theCount);
			question.setTheCount(theCount);
			for (int i=0 ; i<theCount ; i++){
				//calculatedVariables.addElement( new randomVariable( theInput, format ) );
				randomVariable(theInput, format );
			}
		}
	
		//hints= new Vector();
		if (format >= 437)
		{
			int theCount= theInput.readInt();
			//System.out.println("theCount 2 : " + theCount);
			question.setTheCount(theCount);
			for (int i=0 ; i<theCount ; i++){
				//hints.addElement( tp_sql.readString( theInput ) );
				String hints;
				hints =  readString( theInput );
				//System.out.println("hints " + (i+1) +" : " + readString( theInput ));
			}
		}
	
		//contentLinks= new Vector();
		if (format >= 442)
		{
			int theCount= theInput.readInt();
			//System.out.println("theCount 3 : " + theCount);
			question.setTheCount(theCount);
			for (int i=0 ; i<theCount ; i++)
			{
				Hashtable theLink= new Hashtable();
				//theLink.put(CONTENT_LINK_TITLE, tp_sql.readString( theInput ));
				//System.out.println("CONTENT_LINK_TITLE : " + readString( theInput ));
				question.setContent_link_title(readString( theInput ));
				//theLink.put(CONTENT_LINK_URL, tp_sql.readString( theInput ));
				//System.out.println("CONTENT_LINK_URL : " + readString( theInput ));
				question.setContent_link_url(readString( theInput ));
				//theLink.put(CONTENT_LINK_TYPE, tp_sql.readString( theInput ));
				String CONTENT_LINK_TYPE = readString( theInput );
				question.setContent_link_type(CONTENT_LINK_TYPE);
				//System.out.println("CONTENT_LINK_TYPE : " + CONTENT_LINK_TYPE);
				
				if (CONTENT_LINK_TYPE.equals("policy"))
				{
					//theLink.put(CONTENT_LINK_POLICY, tp_sql.readString( theInput ));
					//System.out.println("CONTENT_LINK_POLICY : " + readString( theInput ));
					question.setContent_link_policy(readString( theInput ));
					//theLink.put(CONTENT_LINK_HTML, "<!--none-->");
					
				}
				else{
					//theLink.put(CONTENT_LINK_HTML, tp_sql.readString( theInput ));
					//System.out.println("CONTENT_LINK_HTML : " + readString( theInput ));
					question.setContent_link_html(readString( theInput ));
				}

				//contentLinks.addElement( theLink );
			}
		}
		
		//questionProperties= richProperties.newInstance("questionProperties");
		if (format >= 447){
			//questionProperties= new richProperties( tp_sql.readString(theInput) );
			//System.out.println("questionProperties : " + readString( theInput ));
			question.setQuestionProperties(readString( theInput ));
		}
		return question;
	} 
	catch (IOException e)
	{
		throw (new Exception( "IOException reading question" ) );
	}
	
}

	

	

	public static void randomVariable( DataInputStream theInput, int format )
	 {

	try {
		
		// read the doubles
		//startValue= theInput.readDouble();
		double startValue = theInput.readDouble();
		//System.out.println("startValue : " + theInput.readDouble());
		//endValue= theInput.readDouble();
		double endValue = theInput.readDouble();
		//System.out.println("endValue : " + theInput.readDouble());
		//incrementValue= theInput.readDouble();
		double incrementValue = theInput.readDouble();
		//System.out.println("incrementValue : " + theInput.readDouble());
		//incrementAmount= theInput.readDouble();
		double incrementAmount = theInput.readDouble();
		//System.out.println("incrementAmount : " + theInput.readDouble());

		// additional doubles here
		
		
		// read the strings
		//name= theInput.readUTF();
		String name = theInput.readUTF();
		//System.out.println("name : " + theInput.readUTF());

		// additional strings here
		
		
		if (format >= 26) {
			boolean usePool= theInput.readBoolean();
			//System.out.println("usePool : " + usePool);
			if (usePool) {
				int count= theInput.readInt();
				//pool= new Vector();
				for (int i=0 ; i<count ; i++)
				{
					//String inValue= theInput.readUTF();
					String inValue = theInput.readUTF();
					//System.out.println("inValue : " + theInput.readUTF());
					/*
					if (inValue.indexOf(",") >= 0)
					{
						StringTokenizer theTokens= new StringTokenizer(inValue, ",");
						Vector thisArray= new Vector();
						while (theTokens.hasMoreTokens())
							thisArray.addElement( rv_recall(theTokens.nextToken().trim()) );
						pool.addElement(thisArray);
					}
					else
						pool.addElement( rv_recall(inValue) );
					*/
				}
			}
		}
		
		if (format >= 426){
			//arrayed= theInput.readBoolean();
			boolean arrayed = theInput.readBoolean();
			//System.out.println("arrayed : " + theInput.readBoolean());
		}
		String dependencies = "";
		//dependencies= new Vector();
		if (format >= 429)
		{
			int count= theInput.readInt();
			for (int i=0 ; i<count ; i++)
				//dependencies.addElement(theInput.readUTF());
				dependencies = theInput.readUTF();
				//System.out.println("dependencies : " + theInput.readUTF());
		}
		
		// read additional subclasses here
		String calculation = "";
		if (format >= 430)
		{
			boolean calculated= theInput.readBoolean();
			//System.out.println("calculated : " + calculated);
			if (calculated){
				//calculation= theInput.readUTF();
				calculation = theInput.readUTF();
				//System.out.println("calculation : " + theInput.readUTF());
			}				
		}
	
	} catch (IOException e) {
		
		e.printStackTrace();
		
	}
	
}
	
}
