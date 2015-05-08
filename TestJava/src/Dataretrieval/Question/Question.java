package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import Dataretrieval.util.MySqlConnection;
import Dataretrieval.util.OracleConnection;


public class Question  {

	private richPropertiesUtil	questionProperties = null;
	public static String	CONTENT_LINK_TITLE						= "linkTitle";
	public static String	CONTENT_LINK_URL						= "linkURL";
	public static String	CONTENT_LINK_HTML						= "linkHTML";
	public static String	CONTENT_LINK_TYPE						= "linkType";
	public static String		CONTENT_LINK_TYPE_ebook				= "ebook";
	public static String		CONTENT_LINK_TYPE_textflow_reading	= "textflow";
	public static String		CONTENT_LINK_TYPE_textflow_video	= "textflow_vid";
	public static String		CONTENT_LINK_TYPE_textflow_image	= "textflow_img";
	public static String		CONTENT_LINK_TYPE_qsi_tutor			= "qsi_tutor";
	public static String		CONTENT_LINK_TYPE_lecture			= "lecture";
	public static String		CONTENT_LINK_TYPE_document			= "document";
	public static String		CONTENT_LINK_TYPE_tutorial			= "tutorial";
	public static String		CONTENT_LINK_TYPE_policyControlled	= "policy";
	public static String	CONTENT_LINK_POLICY		= "linkPOLICY";
	public static String		CUSTOM_TYPE					= "customType";
	
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	static String LICENSE_TYPE_AMAZON_KEYS	= "amazonKeys";
	public static int		QUESTION_TYPE_undefined = 0;
	public int		type = QUESTION_TYPE_undefined;
	private richMediaUtil questionMedia = null;

	static char		filemakerReturn[] 		= { (char)0x0b };
	protected static String	fmReturn = new String( filemakerReturn );
	public static int		QUESTION_TYPE_copy = 0;
	/** Constant integer {@link #type} value indicating a {@link yesNo} question. */
	public static int		QUESTION_TYPE_yesNo = 1;
	/** Constant integer {@link #type} value indicating a {@link trueFalse} question. */
	public static int		QUESTION_TYPE_trueFalse = 2;
	/** Constant integer {@link #type} value indicating a {@link multipleChoice} question. */
	public static int		QUESTION_TYPE_multipleChoice = 3;
	/** Constant integer {@link #type} value indicating a {@link fillInTheBlank} question. */
	public static int		QUESTION_TYPE_fillBlank = 4;
	/** Constant integer {@link #type} value indicating a {@link checkAll} question type. */
	public static int		QUESTION_TYPE_checkAll = 5;
	/** Constant integer {@link #type} value indicating a {@link survey} question type. */
	public static int		QUESTION_TYPE_survey = 6;
	/** Constant integer {@link #type} value indicating a {@link sectionBreak} question type. */
	public static int		QUESTION_TYPE_sectionbreak = 7;
	/** Constant integer {@link #type} value indicating a {@link matching} question type. */
	public static int		QUESTION_TYPE_matching = 8;
	/** Constant integer {@link #type} value indicating a {@link ranking} question type. */
	public static int		QUESTION_TYPE_ranking = 9;
	/** Constant integer {@link #type} value indicating a {@link worksheet} question type. */
	public static int		QUESTION_TYPE_worksheet = 101;
	/** Constant integer {@link #type} value indicating an {@link lsi} question type. */
	public static int		QUESTION_TYPE_lsi = 201;
	/** Constant integer {@link #type} value indicating an {@link document} question type. */
	public static int		QUESTION_TYPE_document = 301;

	public static String	SUBTAG3 = "<!--Zach-->";
	


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
	private int     pointsCount;
	private int     feedbackCount;
	private int     localCount;
	private int     answerFeedbackCount;
	
	private ArrayList<String>  hints;
	private ArrayList<String>  feedback;
	private ArrayList<String>  points;
	private ArrayList<String>  choices;
	private ArrayList<ConcurrentHashMap<String, String>> contentLinks;
	private ArrayList<String> correctAnswerFeedback;
	private ArrayList<randomVariableUtil> localRandoms;
	private ArrayList<randomVariableUtil>	calculatedVariables;

	private int     theCount;
	
	private String  content_link_title;
	private String  content_link_url;
	private String  content_link_type;
	private String  content_link_policy;
	private String  content_link_html;
	private int  questionType;
	private String  defaultAnswer;


	public ArrayList<ConcurrentHashMap<String, String>> getContentLinks() {
		return contentLinks;
	}

	public void setContentLinks(ArrayList<ConcurrentHashMap<String, String>> contentLinks) {
		this.contentLinks = contentLinks;
	}

	public ArrayList<randomVariableUtil> getCalculatedVariables() {
		return calculatedVariables;
	}

	public void setCalculatedVariables(ArrayList<randomVariableUtil> calculatedVariables) {
		this.calculatedVariables = calculatedVariables;
	}
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

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}
	public ArrayList<String> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<String> points) {
		this.points = points;
	}
	public int getPointsCount() {
		return pointsCount;
	}

	public void setPointsCount(int pointsCount) {
		this.pointsCount = pointsCount;
	}
	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public ArrayList<String> getCorrectAnswerFeedback() {
		return correctAnswerFeedback;
	}

	public void setCorrectAnswerFeedback(ArrayList<String> correctAnswerFeedback) {
		this.correctAnswerFeedback = correctAnswerFeedback;
	}
	public ArrayList<String> getFeedback() {
		return feedback;
	}

	public void setFeedback(ArrayList<String> feedback) {
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
	public ArrayList<String> getHints() {
		return hints;
	}

	public void setHints(ArrayList<String> hints) {
		this.hints = hints;
	}


	public richPropertiesUtil getQuestionProperties() {
		return questionProperties;
	}

	public void setQuestionProperties(richPropertiesUtil questionProperties) {
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

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public richMediaUtil getQuestionMedia() {
		return questionMedia;
	}

	public void setQuestionMedia(richMediaUtil questionMedia) {
		this.questionMedia = questionMedia;
	}
	
	public ArrayList<randomVariableUtil> getLocalRandoms() {
		return localRandoms;
	}

	public void setLocalRandoms(ArrayList<randomVariableUtil> localRandoms) {
		this.localRandoms = localRandoms;
	}

	public static void main(String argv[]) throws Exception{

		Connection con = null;
		try {
			con = OracleConnection.getConnection();
			//con = MySqlConnection.getConnection();
			
			getQuestion(con,"13252698046458471");// 13252698045039344
			//getQuestionsFromTest(con, "13252698010863581");
		} catch(SQLException exSQLException){
			System.out.print(exSQLException);
			exSQLException.printStackTrace();

		} catch(Exception exException){
			System.out.print(exException);
			exException.printStackTrace();

		}finally{
			if(con != null){
				con.close();
			}
		}
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

	private static Question getQuestionInstance(int questionType)throws Exception{
		Question question = null;
		String textQuestionType = "";
		switch(questionType){
		case 1: //yesNO
			textQuestionType = "yesNO";
			question = new yesNoQ();
			break;
		case 2: //trueFalse
			textQuestionType = "trueFalse";
			question = new trueFalseQ();
			break;
		case 3: //multipleChoice
			textQuestionType = "multipleChoice";
			question = new multipleChoiceQ();
			break;
		case 4: //fillBlank
			textQuestionType = "fillBlank";
			question = new fillInTheBlankQ();
			break;
		case 5: //checkAll
			textQuestionType = "checkAll";
			question = new checkAllQ();
			break;
		case 6: //survey
			textQuestionType = "survey";
			question = new surveyQ();
			break;
		case 7: //sectionbreak
			textQuestionType = "sectionbreak";
			question = new sectionBreakQ();
			break;
		case 8: //matching
			textQuestionType = "matching";
			question = new matchingQ();
			break;
		case 9: //ranking
			textQuestionType = "ranking";
			question = new rankingQ();
			break;
			//Implemetation in progress
			 case 101: //worksheet
			  textQuestionType = "worksheet";
			  question = new worksheetQ();
		        break;
		  case 201: //lsi
			  textQuestionType = "lsi";
			  question = new lsiQ();
		      break;
		case 301: //document
			textQuestionType = "document";
			question = new docQ();
			break;
		default: //undefined question type
			throw new Exception("undefined question type");
		}
		System.out.println("questionType_textQuestionType : " + questionType + "_" + textQuestionType);
		return question;		
	}

	public static Question getQuestion(Connection con,String questionID) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		int format = -1;
		InputStream theData = null;
		Question question = null;
		DataInputStream input= null;
		try{
			pst = con.prepareStatement("SELECT questionID, format, data FROM questions WHERE questionID = ?");
			pst.setString(1, questionID);    	
			rs = pst.executeQuery();

			while (rs.next()) {
				format = rs.getInt("format");
				theData = rs.getBinaryStream("data");
				
				//theData = new ByteArrayInputStream(rs.getBytes("data"));
				
				input= new DataInputStream(theData);
				int questionType= input.readInt();

				question = getQuestionInstance(questionType);
				question.setQuestionType(questionType);
				question.setSqlID(questionID);
				System.out.println("format : " + format);
				System.out.println("Question ID : " + questionID);
				question = questionFromStream(question,input, format);
				
				System.out.println("##################################################");
			}
			printData(question);
		}
		catch(Exception ex){
			ex.printStackTrace();
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
	
	public static ArrayList<Question> getQuestionsFromTest(Connection con,String testId) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Question> questionList = new ArrayList<Question>();
		try{
			//pst = con.prepareStatement("SELECT q.questionid FROM tests t,questions q WHERE t.testid = ? AND q.bankid = t.bankid");
			pst = con.prepareStatement("SELECT t.questionid FROM test_question_xref t WHERE t.testid = ? ");
			pst.setString(1, testId);    	
			rs = pst.executeQuery();

			while (rs.next()) {
				questionList.add(getQuestion(con, rs.getString("questionid")));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(rs != null){
				rs.close();
			}
			if(pst != null){
				pst.close();
			}
		}
		return questionList;
	}
	
	public static Question questionFromStream(Question result,
			DataInputStream input, int format) throws Exception {

		try {

			int questionType= result.getQuestionType();
			if ((questionType == 0) && (format <= 402)) {
				result= checkAllQ.getQuestioncheckAll(result, input, format);
			}
			else {

				if (questionType == Question.QUESTION_TYPE_yesNo)
					result= yesNoQ.getQuestionYesNoQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_trueFalse)
					result= trueFalseQ.getQuestionTFQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_multipleChoice){
					result= multipleChoiceQ.getQuestionMCQ(result, input, format);
				}
				else if (questionType == Question.QUESTION_TYPE_fillBlank)
					result= fillInTheBlankQ.getQuestionFillBlkQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_checkAll)
					result= checkAllQ.getQuestioncheckAll(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_survey)
					result= surveyQ.getSurveyQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_matching)
					result= matchingQ.getMatchingQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_ranking)
					result= rankingQ.getRankingQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_sectionbreak)
					result= sectionBreakQ.getSection_BreakQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_worksheet)
					result= worksheetQ.getWorksheetQ(result, input, format);

				else if (questionType == Question.QUESTION_TYPE_lsi)
					result= lsiQ.getLsiQ(result, input, format);
				
				else if (questionType == Question.QUESTION_TYPE_document)
					result= docQ.getDocQ(result, input, format);
				else
					throw (new Exception( "undefined question type" ) );

				result.setQuestionType(questionType);
			}

		} catch (IOException i) {
			i.printStackTrace();
			throw (new Exception( "IOException in questionBanks.questonFromStream()" ) );			
		}

		return( result );

	}

	/*Method to print the output for Question*/
	private static void printData(Question question)throws Exception {
		System.out.println("############### Printing data in Question start ####################");
		//System.out.println(" Title " + question.getSelectionTitle() + "(" + question.getQuestionType() +")");
		System.out.println("questionType : " + question.getQuestionType());
		System.out.println("usePopup : " + question.isUsePopup());
		System.out.println("questionIsHTML : " + question.isQuestionIsHTML());
		System.out.println("mathColumns : " + question.isMathColumns());
		System.out.println("indent : " + question.isIndent());
		System.out.println("Larger : " + question.isLarger());
		System.out.println("smaller : " + question.isSmaller());
		System.out.println("autoWrap : " + question.isAutoWrap());
		System.out.println("questionIsHTML : " + question.isQuestionIsHTML());
		System.out.println("QuestionIsMathML : " + question.isQuestionIsMathML());
		System.out.println("questionIsTeX : " + question.isQuestionIsTeX());
		System.out.println("questionIsLaTeX : " + question.isQuestionIsLaTeX());
		System.out.println("LongQuestion : " + question.isLongQuestion());
		System.out.println("Larger : " + question.isLarger());
		System.out.println("id : " + question.getId());
		System.out.println("sqlID : " + question.getSqlID());
		System.out.println("Storage : " + question.getStorage());
		System.out.println("boxWidth : " + question.getBoxWidth());
		System.out.println("boxHeight : " + question.getBoxWidth());
		System.out.println("MaxPoints : " + question.getMaxPoints());
		System.out.println("qtext : " + question.getQtext());
		System.out.println("DefaultAnswer : " + question.getDefaultAnswer());
		System.out.println("selectionTitle : " + question.getSelectionTitle());
		System.out.println("source : " + question.getSource());
		System.out.println("sourceInfo : " + question.getSourceInfo());
		System.out.println("referenceTag : " + question.getReferenceTag());
		System.out.println("pageTag : " + question.getPageTag());
		System.out.println("hasMedia : " + question.hasMedia);
		System.out.println("choiceCount : " + question.getChoiceCount());
		System.out.println("pointsCount : " + question.getPointsCount());
		System.out.println("feedbackCount : " + question.getFeedbackCount());
		System.out.println("localCount : " + question.getLocalCount());
		System.out.println("answerFeedbackCount : " + question.getAnswerFeedbackCount());
		System.out.println("theCount : " + question.getTheCount());
		
		System.out.println("..... printing hints ....");
		for(String ishintsStr : question.getHints())
		{
			System.out.println(" hints : " + ishintsStr);
		}
		
		System.out.println("..... printing correctAnswerFeedback ....");
		for(String correctAnswerFeedback : question.getCorrectAnswerFeedback())
		{
			System.out.println(" correctAnswerFeedback : " + correctAnswerFeedback);
		}
		
		System.out.println("..... printing points ....");
		for(String points : question.getPoints())
		{
			System.out.println(" points : " + points);
		}
		
		System.out.println("..... printing choices ....");
		for(String choice : question.getChoices())
		{
			System.out.println(" choice : " + choice);
		}
		
		System.out.println("..... printing localRandoms ....");
		for(randomVariableUtil localRandoms : question.getLocalRandoms())
		{
			localRandoms.print_randomVariableUtil();
		}
		
		System.out.println("..... printing feedback ....");
		for(String feedback : question.getFeedback())
		{
			System.out.println(" feedback : " + feedback);
		}
		
		System.out.println("..... printing contentLinks ....");
		for(ConcurrentHashMap<String, String> contentLinks : question.getContentLinks())
		{
			for(String contentLinksKey : contentLinks.keySet()){
				System.out.println(" Key : " + contentLinksKey + ", Val : " + contentLinks.get(contentLinksKey));
			}
		}
		
		System.out.println("..... printing calculatedVariables ....");
		for(randomVariableUtil calculatedVariables : question.getCalculatedVariables())
		{
			calculatedVariables.print_randomVariableUtil();
		}
		
		System.out.println("CONTENT_LINK_TITLE : " + question.getContent_link_title());
		System.out.println("CONTENT_LINK_URL : " + question.getContent_link_url());
		System.out.println("CONTENT_LINK_TYPE : " + question.getContent_link_type());
		System.out.println("CONTENT_LINK_POLICY : " + question.getContent_link_policy());
		System.out.println("CONTENT_LINK_HTML : " + question.getContent_link_html());
		System.out.println("############### Printing data in Question end ####################");
	}

	public static Question getQuestion(Question question,DataInputStream theInput, int format )
	throws Exception {

		try {

			question.setUsePopup(theInput.readBoolean());

			if (format >= 8) {

				question.setQuestionIsHTML(theInput.readBoolean());
				question.setMathColumns(theInput.readBoolean());
				question.setIndent(theInput.readBoolean());
				question.setLarger(theInput.readBoolean());
				question.setSmaller(theInput.readBoolean());
			}

			if (format >= 24){

				question.setAutoWrap(theInput.readBoolean());
			}

			if (format >= 43) {

				question.setQuestionIsMathML(theInput.readBoolean());
				question.setQuestionIsTeX(theInput.readBoolean());
				question.setQuestionIsLaTeX(theInput.readBoolean());
			}

			// additional booleans go here
			if (format >= 434){

				question.setLongQuestion(theInput.readBoolean()) ;
			}

			// read the ints

			question.setId(theInput.readInt());

			if (format >= 400){

				question.setSqlID(theInput.readUTF());
			}


			question.setStorage(theInput.readInt());

			question.setMaxPoints(theInput.readInt());
			if (format >= 43) {
				question.setBoxWidth(theInput.readInt());
				question.setBoxHeight(theInput.readInt());
			}

			// additional ints here


			// read the strings
			String qtext= readString( theInput );

			if (format < 8) 
			{	// strip off old font tags
				qtext= tp_utility.substitute( qtext, fmReturn, "\r" );
				int index= qtext.indexOf(SUBTAG3);
				if (index > 0) 
				{
					String tag= qtext.substring(0, index);

					qtext= qtext.substring(index+SUBTAG3.length());
					if (qtext.length() > 7) 
					{
						index= qtext.lastIndexOf("</FONT>");
						if (index > 0)
							qtext= qtext.substring(0,index);
					}
					else qtext= "";

					if (tag.indexOf("<B>") > 0) 
					{
						index= qtext.lastIndexOf( "</B>" );
						if (index > 0)
							qtext= qtext.substring(0,index);
					}

					if (tag.indexOf("<I>") > 0) 
					{
						index= qtext.lastIndexOf( "</I>" );
						if (index > 0)
							qtext= qtext.substring(0,index);
					}

				}
			}
			question.setQtext(qtext);

			question.setDefaultAnswer(theInput.readUTF());

			question.setSelectionTitle(theInput.readUTF());

			// additional strings here
			if (format >= 414){

				question.setSource(theInput.readUTF());
			}
			if (format >= 416){

				question.setSourceInfo(theInput.readUTF());
			}
			if (format >= 423){

				question.setReferenceTag(theInput.readUTF());
			}
			if (format >= 424){

				question.setPageTag(theInput.readUTF());
			}


			// read the questionMedia if any
			boolean hasMedia= theInput.readBoolean();
			question.setHasMedia(hasMedia);
			if (hasMedia){
				richMediaUtil questionMedia= new richMediaUtil( theInput, format );				
			}

			// read choices
			int choiceCount= theInput.readInt();
			question.setChoiceCount(choiceCount);
			ArrayList choiceList = new ArrayList();
			for (int i=0 ; i<choiceCount ; i++){
				choiceList.add( theInput.readUTF() );
			}
			question.setChoices(choiceList);


			// read points
			int pointsCount= theInput.readInt();
			question.setPointsCount(pointsCount);
			ArrayList pointList = new ArrayList();
			for (int i=0 ; i<pointsCount ; i++){
				pointList.add(theInput.readUTF());
			}
			question.setPoints(pointList);


			// read feedback

			// read feedbackcount
			int feedbackCount= theInput.readInt();

			question.setFeedbackCount(feedbackCount);
			ArrayList feedbackList = new ArrayList();
			for (int i=0 ; i<feedbackCount ; i++){
				feedbackList.add(readString( theInput ));
			}
			question.setFeedback(feedbackList);

			// read the local randoms
			int localCount= theInput.readInt();

			question.setLocalCount(localCount);
			ArrayList localRandoms = new ArrayList();
			for (int i=0 ; i<localCount ; i++){
				localRandoms.add( new randomVariableUtil( theInput, format ) );
				//new randomVariableUtil(theInput, format );
			}		
			question.setLocalRandoms(localRandoms);
			
			// read the correctAnswerFeedback Vector
			int answerFeedbackCount= theInput.readInt();

			question.setAnswerFeedbackCount(answerFeedbackCount);
			ArrayList correctAnswerFeedbackList = new ArrayList();
			for (int i=0 ; i<answerFeedbackCount ; i++){
				correctAnswerFeedbackList.add(readString( theInput ));
			}
			question.setCorrectAnswerFeedback(correctAnswerFeedbackList);


			// read additional subclasses here
			ArrayList calculatedVariablesList= new ArrayList();
			if (format >= 430)
			{
				int theCount= theInput.readInt();
				for (int i=0 ; i<theCount ; i++)
					calculatedVariablesList.add( new randomVariableUtil( theInput, format ) );
			}
			question.setCalculatedVariables(calculatedVariablesList);

			ArrayList hintsList= new ArrayList();
			if (format >= 437)
			{
				int theCount= theInput.readInt();
				for (int i=0 ; i<theCount ; i++)
					hintsList.add( readString( theInput ) );
			}
			question.setHints(hintsList);

			ArrayList contentLinks= new ArrayList();
			if (format >= 442)
			{
				int theCount= theInput.readInt();
				for (int i=0 ; i<theCount ; i++)
				{
					ConcurrentHashMap theLink= new ConcurrentHashMap();
					theLink.put(CONTENT_LINK_TITLE, readString( theInput ));
					theLink.put(CONTENT_LINK_URL, readString( theInput ));
					theLink.put(CONTENT_LINK_TYPE, readString( theInput ));

					if (((String)theLink.get(CONTENT_LINK_TYPE)).equals(CONTENT_LINK_TYPE_policyControlled))
					{
						theLink.put(CONTENT_LINK_POLICY, readString( theInput ));
						theLink.put(CONTENT_LINK_HTML, "<!--none-->");
					}
					else
						theLink.put(CONTENT_LINK_HTML, readString( theInput ));

					contentLinks.add( theLink );
				}
			}
			question.setContentLinks(contentLinks);

			richPropertiesUtil questionProp= richPropertiesUtil.newInstance("questionProperties");
			System.out.println("inside Question getQuestion richPropertiesUtil");
			
			if (format >= 447) questionProp= new richPropertiesUtil( readString(theInput) );
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		}
		return question;
	}
	

}
