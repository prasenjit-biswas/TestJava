package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;



public class tp_guiUtil {
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	
	private boolean headerNeedsFormating;
	private boolean instructionsNeedFormating;
	private boolean footerNeedsFormating;
	
	private boolean questionAtATime;
	private boolean perQuestionFeedback;
	private boolean submissionWarning;
	private boolean branchingEnabled;
	private boolean disableBack;
	private boolean resumable;
	private boolean tmp;
	private boolean styleBold;
	private boolean styleItalic;
	private boolean rstyleBold;
	private  boolean rstyleItalic;
	private  boolean astyleBold;
	private  boolean astyleItalic;

	private  boolean pstyleBold;
	private  boolean pstyleItalic;

	private  boolean fstyleBold;
	private  boolean fstyleItalic;


	private  boolean hideEraseButton;
	private  boolean numberQuestions;

	private  boolean defaultIndent;
	private  boolean defaultUsePopup;
	private  boolean defaultMathColumns;
	private boolean groupAtATime;
	private  boolean anonymous;
	private  boolean penalizeBack;
	
	private  boolean suppressNameLine;
	private  boolean showPoints;
	private  boolean mcDistractorScrambling;
	private  int defaultType;
	private  int defaultGroup;
	private  int defaultMediaPosition;
	private  int defaultMediaHeight;
	private  int defaultMediaWidth;
	private  int defaultEssayHeight;
	private  int defaultEssayWidth;
	private  int defaultStorage;
	private  int tickleList;
	private  String title;
	private  String header;
	private  String instructions;
	private  String preparer;
	private  String footer;
	private  String insidehead;
	private  String bodystart;
	private  String bodyend;

	private  String testBackground;
	private  String testBackgroundGraphic;
	private  String questionText;
	private  String questionFont;
	private  String questionSize;
	private  String responseText;
	private  String responseFont;
	private  String responseSize;
	private  String answerText;
	private  String answerFont;
	private  String answerSize;
	private  String pointText;
	private  String pointFont;
	private  String pointSize;
	private  String feedbackText;
	private  String feedbackFont;
	private  String feedbackSize;
	private  String oemName;
	private  String oemHeader;
	private  String oemFooter;
	private  String visitedLinks;
	private  String newProps;
	private String bankTitle;
	private String titleSize;
	private boolean showCustomTypeLabels;
	private boolean terseKey;
	private boolean noKey;
	private boolean showHints;
	private boolean allowPregrade;
	private boolean showGroupSummary;
	private String customReturnURL;
	private boolean external_ebook;
	private boolean show_categories;
	private boolean show_pages;
	private boolean show_titles;
	private String MC_delimiter;
	private boolean MC_delimiterForceCaps;
	private boolean MC_delimiterForceBold;
	private boolean ESSA_suppressLines;
	private String ESSA_lineHeight;
	private boolean  hm_allowPrint;
	private boolean  hm_allowPreview;
	
	public boolean isHeaderNeedsFormating() {
		return headerNeedsFormating;
	}

	public void setHeaderNeedsFormating(boolean headerNeedsFormating) {
		this.headerNeedsFormating = headerNeedsFormating;
	}

	public boolean isInstructionsNeedFormating() {
		return instructionsNeedFormating;
	}

	public void setInstructionsNeedFormating(boolean instructionsNeedFormating) {
		this.instructionsNeedFormating = instructionsNeedFormating;
	}

	public boolean isFooterNeedsFormating() {
		return footerNeedsFormating;
	}

	public void setFooterNeedsFormating(boolean footerNeedsFormating) {
		this.footerNeedsFormating = footerNeedsFormating;
	}

	public boolean isQuestionAtATime() {
		return questionAtATime;
	}

	public void setQuestionAtATime(boolean questionAtATime) {
		this.questionAtATime = questionAtATime;
	}

	public boolean isPerQuestionFeedback() {
		return perQuestionFeedback;
	}

	public void setPerQuestionFeedback(boolean perQuestionFeedback) {
		this.perQuestionFeedback = perQuestionFeedback;
	}

	public boolean isSubmissionWarning() {
		return submissionWarning;
	}

	public void setSubmissionWarning(boolean submissionWarning) {
		this.submissionWarning = submissionWarning;
	}

	public boolean isBranchingEnabled() {
		return branchingEnabled;
	}

	public void setBranchingEnabled(boolean branchingEnabled) {
		this.branchingEnabled = branchingEnabled;
	}

	public boolean isDisableBack() {
		return disableBack;
	}

	public void setDisableBack(boolean disableBack) {
		this.disableBack = disableBack;
	}

	public boolean isResumable() {
		return resumable;
	}

	public void setResumable(boolean resumable) {
		this.resumable = resumable;
	}

	public boolean isTmp() {
		return tmp;
	}

	public void setTmp(boolean tmp) {
		this.tmp = tmp;
	}

	public boolean isStyleBold() {
		return styleBold;
	}

	public void setStyleBold(boolean styleBold) {
		this.styleBold = styleBold;
	}

	public boolean isStyleItalic() {
		return styleItalic;
	}

	public void setStyleItalic(boolean styleItalic) {
		this.styleItalic = styleItalic;
	}

	public boolean isRstyleBold() {
		return rstyleBold;
	}

	public void setRstyleBold(boolean rstyleBold) {
		this.rstyleBold = rstyleBold;
	}

	public boolean isRstyleItalic() {
		return rstyleItalic;
	}

	public void setRstyleItalic(boolean rstyleItalic) {
		this.rstyleItalic = rstyleItalic;
	}

	public boolean isAstyleBold() {
		return astyleBold;
	}

	public void setAstyleBold(boolean astyleBold) {
		this.astyleBold = astyleBold;
	}

	public boolean isAstyleItalic() {
		return astyleItalic;
	}

	public void setAstyleItalic(boolean astyleItalic) {
		this.astyleItalic = astyleItalic;
	}

	public boolean isPstyleBold() {
		return pstyleBold;
	}

	public void setPstyleBold(boolean pstyleBold) {
		this.pstyleBold = pstyleBold;
	}

	public boolean isPstyleItalic() {
		return pstyleItalic;
	}

	public void setPstyleItalic(boolean pstyleItalic) {
		this.pstyleItalic = pstyleItalic;
	}

	public boolean isFstyleBold() {
		return fstyleBold;
	}

	public void setFstyleBold(boolean fstyleBold) {
		this.fstyleBold = fstyleBold;
	}

	public boolean isFstyleItalic() {
		return fstyleItalic;
	}

	public void setFstyleItalic(boolean fstyleItalic) {
		this.fstyleItalic = fstyleItalic;
	}

	public boolean isHideEraseButton() {
		return hideEraseButton;
	}

	public void setHideEraseButton(boolean hideEraseButton) {
		this.hideEraseButton = hideEraseButton;
	}

	public boolean isNumberQuestions() {
		return numberQuestions;
	}

	public void setNumberQuestions(boolean numberQuestions) {
		this.numberQuestions = numberQuestions;
	}

	public boolean isDefaultIndent() {
		return defaultIndent;
	}

	public void setDefaultIndent(boolean defaultIndent) {
		this.defaultIndent = defaultIndent;
	}

	public boolean isDefaultUsePopup() {
		return defaultUsePopup;
	}

	public void setDefaultUsePopup(boolean defaultUsePopup) {
		this.defaultUsePopup = defaultUsePopup;
	}

	public boolean isDefaultMathColumns() {
		return defaultMathColumns;
	}

	public void setDefaultMathColumns(boolean defaultMathColumns) {
		this.defaultMathColumns = defaultMathColumns;
	}

	public boolean isGroupAtATime() {
		return groupAtATime;
	}

	public void setGroupAtATime(boolean groupAtATime) {
		this.groupAtATime = groupAtATime;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public boolean isPenalizeBack() {
		return penalizeBack;
	}

	public void setPenalizeBack(boolean penalizeBack) {
		this.penalizeBack = penalizeBack;
	}

	public boolean isSuppressNameLine() {
		return suppressNameLine;
	}

	public void setSuppressNameLine(boolean suppressNameLine) {
		this.suppressNameLine = suppressNameLine;
	}

	public boolean isShowPoints() {
		return showPoints;
	}

	public void setShowPoints(boolean showPoints) {
		this.showPoints = showPoints;
	}

	public boolean isMcDistractorScrambling() {
		return mcDistractorScrambling;
	}

	public void setMcDistractorScrambling(boolean mcDistractorScrambling) {
		this.mcDistractorScrambling = mcDistractorScrambling;
	}

	public int getDefaultType() {
		return defaultType;
	}

	public void setDefaultType(int defaultType) {
		this.defaultType = defaultType;
	}

	public int getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(int defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public int getDefaultMediaPosition() {
		return defaultMediaPosition;
	}

	public void setDefaultMediaPosition(int defaultMediaPosition) {
		this.defaultMediaPosition = defaultMediaPosition;
	}

	public int getDefaultMediaHeight() {
		return defaultMediaHeight;
	}

	public void setDefaultMediaHeight(int defaultMediaHeight) {
		this.defaultMediaHeight = defaultMediaHeight;
	}

	public int getDefaultMediaWidth() {
		return defaultMediaWidth;
	}

	public void setDefaultMediaWidth(int defaultMediaWidth) {
		this.defaultMediaWidth = defaultMediaWidth;
	}

	public int getDefaultEssayHeight() {
		return defaultEssayHeight;
	}

	public void setDefaultEssayHeight(int defaultEssayHeight) {
		this.defaultEssayHeight = defaultEssayHeight;
	}

	public int getDefaultEssayWidth() {
		return defaultEssayWidth;
	}

	public void setDefaultEssayWidth(int defaultEssayWidth) {
		this.defaultEssayWidth = defaultEssayWidth;
	}

	public int getDefaultStorage() {
		return defaultStorage;
	}

	public void setDefaultStorage(int defaultStorage) {
		this.defaultStorage = defaultStorage;
	}

	public int getTickleList() {
		return tickleList;
	}

	public void setTickleList(int tickleList) {
		this.tickleList = tickleList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPreparer() {
		return preparer;
	}

	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getInsidehead() {
		return insidehead;
	}

	public void setInsidehead(String insidehead) {
		this.insidehead = insidehead;
	}

	public String getBodystart() {
		return bodystart;
	}

	public void setBodystart(String bodystart) {
		this.bodystart = bodystart;
	}

	public String getBodyend() {
		return bodyend;
	}

	public void setBodyend(String bodyend) {
		this.bodyend = bodyend;
	}

	public String getTestBackground() {
		return testBackground;
	}

	public void setTestBackground(String testBackground) {
		this.testBackground = testBackground;
	}

	public String getTestBackgroundGraphic() {
		return testBackgroundGraphic;
	}

	public void setTestBackgroundGraphic(String testBackgroundGraphic) {
		this.testBackgroundGraphic = testBackgroundGraphic;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionFont() {
		return questionFont;
	}

	public void setQuestionFont(String questionFont) {
		this.questionFont = questionFont;
	}

	public String getQuestionSize() {
		return questionSize;
	}

	public void setQuestionSize(String questionSize) {
		this.questionSize = questionSize;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String getResponseFont() {
		return responseFont;
	}

	public void setResponseFont(String responseFont) {
		this.responseFont = responseFont;
	}

	public String getResponseSize() {
		return responseSize;
	}

	public void setResponseSize(String responseSize) {
		this.responseSize = responseSize;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getAnswerFont() {
		return answerFont;
	}

	public void setAnswerFont(String answerFont) {
		this.answerFont = answerFont;
	}

	public String getAnswerSize() {
		return answerSize;
	}

	public void setAnswerSize(String answerSize) {
		this.answerSize = answerSize;
	}

	public String getPointText() {
		return pointText;
	}

	public void setPointText(String pointText) {
		this.pointText = pointText;
	}

	public String getPointFont() {
		return pointFont;
	}

	public void setPointFont(String pointFont) {
		this.pointFont = pointFont;
	}

	public String getPointSize() {
		return pointSize;
	}

	public void setPointSize(String pointSize) {
		this.pointSize = pointSize;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public String getFeedbackFont() {
		return feedbackFont;
	}

	public void setFeedbackFont(String feedbackFont) {
		this.feedbackFont = feedbackFont;
	}

	public String getFeedbackSize() {
		return feedbackSize;
	}

	public void setFeedbackSize(String feedbackSize) {
		this.feedbackSize = feedbackSize;
	}

	public String getOemName() {
		return oemName;
	}

	public void setOemName(String oemName) {
		this.oemName = oemName;
	}

	public String getOemHeader() {
		return oemHeader;
	}

	public void setOemHeader(String oemHeader) {
		this.oemHeader = oemHeader;
	}

	public String getOemFooter() {
		return oemFooter;
	}

	public void setOemFooter(String oemFooter) {
		this.oemFooter = oemFooter;
	}

	public String getVisitedLinks() {
		return visitedLinks;
	}

	public void setVisitedLinks(String visitedLinks) {
		this.visitedLinks = visitedLinks;
	}

	public String getNewProps() {
		return newProps;
	}

	public void setNewProps(String newProps) {
		this.newProps = newProps;
	}

	public String getBankTitle() {
		return bankTitle;
	}

	public void setBankTitle(String bankTitle) {
		this.bankTitle = bankTitle;
	}

	public String getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(String titleSize) {
		this.titleSize = titleSize;
	}

	public boolean isShowCustomTypeLabels() {
		return showCustomTypeLabels;
	}

	public void setShowCustomTypeLabels(boolean showCustomTypeLabels) {
		this.showCustomTypeLabels = showCustomTypeLabels;
	}

	public boolean isTerseKey() {
		return terseKey;
	}

	public void setTerseKey(boolean terseKey) {
		this.terseKey = terseKey;
	}

	public boolean isNoKey() {
		return noKey;
	}

	public void setNoKey(boolean noKey) {
		this.noKey = noKey;
	}

	public boolean isShowHints() {
		return showHints;
	}

	public void setShowHints(boolean showHints) {
		this.showHints = showHints;
	}

	public boolean isAllowPregrade() {
		return allowPregrade;
	}

	public void setAllowPregrade(boolean allowPregrade) {
		this.allowPregrade = allowPregrade;
	}

	public boolean isShowGroupSummary() {
		return showGroupSummary;
	}

	public void setShowGroupSummary(boolean showGroupSummary) {
		this.showGroupSummary = showGroupSummary;
	}

	public String getCustomReturnURL() {
		return customReturnURL;
	}

	public void setCustomReturnURL(String customReturnURL) {
		this.customReturnURL = customReturnURL;
	}

	public boolean isExternal_ebook() {
		return external_ebook;
	}

	public void setExternal_ebook(boolean externalEbook) {
		external_ebook = externalEbook;
	}

	public boolean isShow_categories() {
		return show_categories;
	}

	public void setShow_categories(boolean showCategories) {
		show_categories = showCategories;
	}

	public boolean isShow_pages() {
		return show_pages;
	}

	public void setShow_pages(boolean showPages) {
		show_pages = showPages;
	}

	public boolean isShow_titles() {
		return show_titles;
	}

	public void setShow_titles(boolean showTitles) {
		show_titles = showTitles;
	}

	public String getMC_delimiter() {
		return MC_delimiter;
	}

	public void setMC_delimiter(String mCDelimiter) {
		MC_delimiter = mCDelimiter;
	}

	public boolean isMC_delimiterForceCaps() {
		return MC_delimiterForceCaps;
	}

	public void setMC_delimiterForceCaps(boolean mCDelimiterForceCaps) {
		MC_delimiterForceCaps = mCDelimiterForceCaps;
	}

	public boolean isMC_delimiterForceBold() {
		return MC_delimiterForceBold;
	}

	public void setMC_delimiterForceBold(boolean mCDelimiterForceBold) {
		MC_delimiterForceBold = mCDelimiterForceBold;
	}

	public boolean isESSA_suppressLines() {
		return ESSA_suppressLines;
	}

	public void setESSA_suppressLines(boolean eSSASuppressLines) {
		ESSA_suppressLines = eSSASuppressLines;
	}

	public String getESSA_lineHeight() {
		return ESSA_lineHeight;
	}

	public void setESSA_lineHeight(String eSSALineHeight) {
		ESSA_lineHeight = eSSALineHeight;
	}

	public boolean isHm_allowPrint() {
		return hm_allowPrint;
	}

	public void setHm_allowPrint(boolean hmAllowPrint) {
		hm_allowPrint = hmAllowPrint;
	}

	public boolean isHm_allowPreview() {
		return hm_allowPreview;
	}

	public void setHm_allowPreview(boolean hmAllowPreview) {
		hm_allowPreview = hmAllowPreview;
	}
	
	
	
	public tp_guiUtil(DataInputStream theInput, int format) throws IOException {
		
		headerNeedsFormating= theInput.readBoolean();
		instructionsNeedFormating= theInput.readBoolean();
		footerNeedsFormating= theInput.readBoolean();
		
		questionAtATime= theInput.readBoolean();
		perQuestionFeedback= theInput.readBoolean();
		submissionWarning= theInput.readBoolean();
		branchingEnabled= theInput.readBoolean();
		disableBack= theInput.readBoolean();
		
		if (format >= 11) resumable= theInput.readBoolean();
		if (format > 16) {
			if (format < 19) {
				boolean tmp= theInput.readBoolean();
				tickleList= 0;
			}
		}
		
		styleBold= theInput.readBoolean();
		styleItalic= theInput.readBoolean();
		
		if (format > 29) {
			rstyleBold= theInput.readBoolean();
			rstyleItalic= theInput.readBoolean();
		}
		else {
			rstyleBold= false;
			rstyleItalic= false;
		}
		
		if (format >= 34) {
			astyleBold= theInput.readBoolean();
			astyleItalic= theInput.readBoolean();

			pstyleBold= theInput.readBoolean();
			pstyleItalic= theInput.readBoolean();

			fstyleBold= theInput.readBoolean();
			fstyleItalic= theInput.readBoolean();
		}
		
		hideEraseButton= theInput.readBoolean();
		numberQuestions= theInput.readBoolean();
		
		defaultIndent= theInput.readBoolean();
		defaultUsePopup= theInput.readBoolean();
		defaultMathColumns= theInput.readBoolean();
		
		if (format >= 14) groupAtATime= theInput.readBoolean();
		
		if (format >= 21) anonymous= theInput.readBoolean();
		
		if (format >= 22) penalizeBack= theInput.readBoolean();
		
		if (format >= 425) suppressNameLine= theInput.readBoolean();
		
		if (format >= 435) showPoints= theInput.readBoolean();

		if (format >= 436) mcDistractorScrambling= theInput.readBoolean();

		// additional booleans go here
		
		
		// read the ints
		defaultType= theInput.readInt();
		defaultGroup= theInput.readInt();
		defaultMediaPosition= theInput.readInt();
		defaultMediaHeight= theInput.readInt();
		defaultMediaWidth= theInput.readInt();
		defaultEssayHeight= theInput.readInt();
		defaultEssayWidth= theInput.readInt();
		
		if (format > 30) defaultStorage= theInput.readInt();
		
		if (format > 18) tickleList= theInput.readInt();

		// additional ints here
		
		
		// read the strings
		title= theInput.readUTF();
		header= theInput.readUTF();
		instructions= theInput.readUTF();
		preparer= theInput.readUTF();
		footer= theInput.readUTF();
		
		if (format >= 33) {
			insidehead= theInput.readUTF();
			bodystart= theInput.readUTF();
			bodyend= theInput.readUTF();
		}

		testBackground= theInput.readUTF();
		testBackgroundGraphic= theInput.readUTF();
		questionText= theInput.readUTF();
		
		questionFont= theInput.readUTF();
		if (!questionFont.endsWith(",Arial Unicode MS")) questionFont += ",Arial Unicode MS";
		
		questionSize= theInput.readUTF();
		
		if (format < 30) {
			questionText= theInput.readUTF();
			
			responseText= questionText;
			responseFont= "Times,Times New Roman,Arial Unicode MS";
			responseSize= "3";
		}
		else {
			responseText= theInput.readUTF();
			
			responseFont= theInput.readUTF();
			if (!responseFont.endsWith(",Arial Unicode MS")) responseFont += ",Arial Unicode MS";
		
			responseSize= theInput.readUTF();
		}
		
		if (format >= 34) {
			answerText= theInput.readUTF();
			answerFont= theInput.readUTF();
			answerSize= theInput.readUTF();

			pointText= theInput.readUTF();
			pointFont= theInput.readUTF();
			pointSize= theInput.readUTF();

			feedbackText= theInput.readUTF();
			feedbackFont= theInput.readUTF();
			feedbackSize= theInput.readUTF();
		}

		if (format >= 35) {
			oemName= theInput.readUTF();
			oemHeader= theInput.readUTF();
			oemFooter= theInput.readUTF();
		}
		
		if (format >= 36) {
			visitedLinks= theInput.readUTF();
			
			//unvisitedLinks= theInput.readUTF();
			
			// replaced unvisitedLinks with properties xml
			//testProperties= richProperties.newInstance("testProperties");
			String newProps= readString(theInput);
			//System.out.println("newProps: " + newProps);
			//if (newProps.indexOf("<?xml") >= 0) testProperties= new richProperties( newProps );					
		}
		
		// additional strings here
		if (format >= 415) bankTitle= theInput.readUTF();

		if (format >= 417) titleSize= theInput.readUTF();

		// read additional subclasses here
		
		ConcurrentHashMap customTypeLabels= defaultCustomLabels();
		if (format >= 418) {
			showCustomTypeLabels= theInput.readBoolean();
			readStringConcurrentHashMap( theInput, customTypeLabels );
		}
		
		if (format >= 420) terseKey= theInput.readBoolean();
		if (format >= 421) noKey= theInput.readBoolean();
			
		if (format >= 437) showHints= theInput.readBoolean();
		if (format >= 438) allowPregrade= theInput.readBoolean();
		if (format >= 439) showGroupSummary= theInput.readBoolean();
		
		if (format >= 441) customReturnURL= theInput.readUTF();
		
		if (format >= 443)
		{
			external_ebook= theInput.readBoolean();
			show_categories= theInput.readBoolean();
			show_pages= theInput.readBoolean();
			show_titles= theInput.readBoolean();
		}
		
		if (format >= 444)
		{
			MC_delimiter= theInput.readUTF();
			MC_delimiterForceCaps= theInput.readBoolean();
			MC_delimiterForceBold= theInput.readBoolean();
		}

		if (format >= 445)
			ESSA_suppressLines= theInput.readBoolean();

		if (format >= 446)
			ESSA_lineHeight= theInput.readUTF();
		
		if (format >= 448)
		{
			hm_allowPrint= theInput.readBoolean();
			hm_allowPreview= theInput.readBoolean();
		}
        
        //System.out.println("questionType : " + questionType);
		printData_tp_guiUtil();
	}
	
	private String readString(DataInputStream theInput) throws IOException{
		  String result= theInput.readUTF();
		  if (result.equals(LONG_STRING))
		  {
			//System.out.println("inputting " + LONG_STRING);
			int arrayLength= theInput.readInt();
			byte[] barray= new byte[ arrayLength ];
			theInput.read( barray, 0, arrayLength );
			result= new String( barray, "UTF-8" );
		  }
		  return(result);
	    
	    }

	private void printData_tp_guiUtil() throws IOException
	{
		System.out.println("############### printing tp_guiUtil  starts ########################");
		System.out.println(" headerNeedsFormating : " + headerNeedsFormating);
		System.out.println(" instructionsNeedFormating : "  + instructionsNeedFormating);
		System.out.println(" footerNeedsFormating : " + footerNeedsFormating);
		System.out.println(" questionAtATime : " + questionAtATime);
		System.out.println(" perQuestionFeedback : " + perQuestionFeedback);
		System.out.println(" submissionWarning : " + submissionWarning);
		System.out.println(" branchingEnabled : " + branchingEnabled);
		System.out.println(" disableBack : " + disableBack);
		System.out.println(" resumable : " + resumable);
		System.out.println(" tmp : " + tmp);
		System.out.println(" styleBold : " + styleBold);
		System.out.println(" styleItalic : " + styleItalic);
		System.out.println(" rstyleBold : " + rstyleBold);
		System.out.println(" rstyleItalic : " + rstyleItalic);
		System.out.println(" astyleBold : " + astyleBold);
		System.out.println(" astyleItalic : " + astyleItalic);
		System.out.println(" pstyleBold : " + pstyleBold);
		System.out.println(" pstyleItalic : " + pstyleItalic);
		System.out.println(" fstyleBold : " + fstyleBold);
		System.out.println(" fstyleItalic : " + fstyleItalic);
		System.out.println(" hideEraseButton : " + hideEraseButton);
		System.out.println(" numberQuestions : " + numberQuestions);
		System.out.println(" defaultIndent : " + defaultIndent);
		System.out.println(" defaultUsePopup : " + defaultUsePopup);
		System.out.println(" defaultMathColumns : " + defaultMathColumns);
		System.out.println(" groupAtATime : " + groupAtATime);
		System.out.println(" anonymous : " + anonymous);
		System.out.println(" penalizeBack : " + penalizeBack);
		System.out.println(" suppressNameLine : " + suppressNameLine);
		System.out.println(" showPoints : " + showPoints);
		System.out.println(" mcDistractorScrambling : " + mcDistractorScrambling);
		System.out.println(" defaultType : " + defaultType);
		System.out.println(" defaultGroup : " + defaultGroup);
		System.out.println(" defaultMediaPosition : " + defaultMediaPosition);
		System.out.println(" defaultMediaHeight : " + defaultMediaHeight);
		System.out.println(" defaultMediaWidth : " + defaultMediaWidth);
		System.out.println(" defaultEssayHeight : " + defaultEssayHeight);
		System.out.println(" defaultEssayWidth : " + defaultEssayWidth);
		System.out.println(" defaultStorage : " + defaultStorage);
		System.out.println(" tickleList : " + tickleList);
		System.out.println(" title : " + title);
		System.out.println(" header : " + header);
		System.out.println(" instructions : " + instructions);
		System.out.println(" preparer : " + preparer);
		System.out.println(" footer : " + footer);
		System.out.println(" insidehead : " + insidehead);
		System.out.println(" bodystart : " + bodystart);
		System.out.println(" bodyend : " + bodyend);
		System.out.println("testBackground  : " + testBackground);
		System.out.println("testBackgroundGraphic  : " + testBackgroundGraphic);
		System.out.println("questionText  : " + questionText);
		System.out.println("questionFont  : " + questionFont);
		System.out.println("questionSize  : " + questionSize);
		System.out.println("responseText  : " + responseText);
		System.out.println("responseFont  : " + responseFont);
		System.out.println("responseSize  : " + responseSize);
		System.out.println("answerText  : " + answerText);
		System.out.println("answerFont  : " + answerFont);
		System.out.println("answerSize  : " + answerSize);
		System.out.println("pointText  : " + pointText);
		System.out.println("pointFont  : " + pointFont);
		System.out.println("pointSize  : " + pointSize);
		System.out.println("feedbackText  : " + feedbackText);
		System.out.println("feedbackFont  : " + feedbackFont);
		System.out.println("feedbackSize  : " + feedbackSize);
		System.out.println("oemName  : " + oemName);
		System.out.println("oemHeader  : " + oemHeader);
		System.out.println("oemFooter  : " + oemFooter);
		System.out.println("visitedLinks  : " + visitedLinks);
		System.out.println("newProps  : " + newProps);
		System.out.println("bankTitle  : " + bankTitle);
		System.out.println("titleSize  : " + titleSize);
		System.out.println("showCustomTypeLabels  : " + showCustomTypeLabels);
		System.out.println("terseKey  : " + terseKey);
		System.out.println("noKey  : " + noKey);
		System.out.println("showHints  : " + showHints);
		System.out.println("allowPregrade  : " + allowPregrade);
		System.out.println("showGroupSummary  : " + showGroupSummary);
		System.out.println("customReturnURL  : " + customReturnURL);
		System.out.println("external_ebook  : " + external_ebook);
		System.out.println("show_categories  : " + show_categories);
		System.out.println("show_pages  : " + show_pages);
		System.out.println("show_titles  : " + show_titles);
		System.out.println("MC_delimiter  : " + MC_delimiter);
		System.out.println("MC_delimiterForceCaps  : " + MC_delimiterForceCaps);
		System.out.println("MC_delimiterForceBold  : " + MC_delimiterForceBold);
		System.out.println("ESSA_suppressLines  : " + ESSA_suppressLines);
		System.out.println("ESSA_lineHeight  : " + ESSA_lineHeight);
		System.out.println("hm_allowPrint  : " + hm_allowPrint);
		System.out.println("hm_allowPreview  : " + hm_allowPreview);
		System.out.println("############## printing tp_guiUtil ends ##############");
	}
	
	private static ConcurrentHashMap defaultCustomLabels()
	{
		ConcurrentHashMap result= new ConcurrentHashMap();
		
		result.put("TF", "True / False Questions");
		result.put("YN", "Yes / No Questions");
		result.put("MC", "Multiple Choice Questions");
		result.put("CA", "Check All That Apply Questions");
		result.put("MA", "Matching Questions");
		result.put("RA", "Ranking Questions");
		result.put("FB", "Fill in the Blank Questions");
		result.put("NU", "Numeric Response Questions");
		result.put("ES", "Essay Questions");
		result.put("SV", "Survey Questions");
		result.put("SA", "Short Answer Questions");
		result.put("WK", "Worksheet Questions");
		result.put("SB", "");
		
		return(result);
	}
	
	private static void readStringConcurrentHashMap( DataInputStream theInput, ConcurrentHashMap theTable )
	throws IOException
{
	int pairCount= theInput.readInt();
	
	for (int i=0; i<pairCount ; i++)
	{
		String key= theInput.readUTF();
		String value= theInput.readUTF();
		theTable.put(key, value);
	}
}

}
