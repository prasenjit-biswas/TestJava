package Dataretrieval.Question;

import java.io.DataInputStream;

import Dataretrieval.Question.Question;

public class sectionBreakQ extends Question{

	public sectionBreakQ(){}
	public static sectionBreakQ getSection_BreakQ (Question result, DataInputStream theInput, int format) throws Exception{
		sectionBreakQ sectionBreakq = null;

		try {
			sectionBreakq = new sectionBreakQ();
			sectionBreakq = (sectionBreakQ)sectionBreakq.getQuestion(result, theInput, format);
			//print_sectionBreakQ(sectionBreakq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionBreakq;

	}
	private static void print_sectionBreakQ(sectionBreakQ sectionBreakq) {
		System.out.println("########### Printing sectionBreakQ Start ################");
		System.out.println(" Title " + sectionBreakq.getSelectionTitle() + "(" + sectionBreakq.getQuestionType() +")");
		System.out.println("questionType : " + sectionBreakq.getQuestionType());
		System.out.println("usePopup : " + sectionBreakq.isUsePopup());
		System.out.println("questionIsHTML : " + sectionBreakq.isQuestionIsHTML());
		System.out.println("mathColumns : " + sectionBreakq.isMathColumns());
		System.out.println("indent : " + sectionBreakq.isIndent());
		System.out.println("Larger : " + sectionBreakq.isLarger());
		System.out.println("smaller : " + sectionBreakq.isSmaller());
		System.out.println("autoWrap : " + sectionBreakq.isAutoWrap());
		System.out.println("questionIsHTML : " + sectionBreakq.isQuestionIsHTML());
		System.out.println("QuestionIsMathML : " + sectionBreakq.isQuestionIsMathML());
		System.out.println("questionIsTeX : " + sectionBreakq.isQuestionIsTeX());
		System.out.println("questionIsLaTeX : " + sectionBreakq.isQuestionIsLaTeX());
		System.out.println("LongQuestion : " + sectionBreakq.isLongQuestion());
		System.out.println("Larger : " + sectionBreakq.isLarger());
		System.out.println("id : " + sectionBreakq.getId());
		System.out.println("sqlID : " + sectionBreakq.getSqlID());
		System.out.println("Storage : " + sectionBreakq.getStorage());
		System.out.println("boxWidth : " + sectionBreakq.getBoxWidth());
		System.out.println("boxHeight : " + sectionBreakq.getBoxWidth());
		System.out.println("MaxPoints : " + sectionBreakq.getMaxPoints());
		System.out.println("qtext : " + sectionBreakq.getQtext());
		System.out.println("DefaultAnswer : " + sectionBreakq.getDefaultAnswer());
		System.out.println("selectionTitle : " + sectionBreakq.getSelectionTitle());
		System.out.println("source : " + sectionBreakq.getSource());
		System.out.println("sourceInfo : " + sectionBreakq.getSourceInfo());
		System.out.println("referenceTag : " + sectionBreakq.getReferenceTag());
		System.out.println("pageTag : " + sectionBreakq.getPageTag());
		System.out.println("hasMedia : " + sectionBreakq.isHasMedia());
		System.out.println("choiceCount : " + sectionBreakq.getChoiceCount());
		System.out.println("pointsCount : " + sectionBreakq.getPointsCount());
		System.out.println("feedbackCount : " + sectionBreakq.getFeedbackCount());
		System.out.println("localCount : " + sectionBreakq.getLocalCount());
		System.out.println("answerFeedbackCount : " + sectionBreakq.getAnswerFeedbackCount());
		System.out.println("theCount 1: " + sectionBreakq.getTheCount());
		System.out.println("theCount 2 : " + sectionBreakq.getTheCount());
		System.out.println("theCount 3 : " + sectionBreakq.getTheCount());
		System.out.println("..... printing hints ....");
		for(String ishintsStr : sectionBreakq.getHints())
		{
			System.out.println(" hints : " + ishintsStr);
		}
		System.out.println("CONTENT_LINK_TITLE : " + sectionBreakq.getContent_link_title());
		System.out.println("CONTENT_LINK_URL : " + sectionBreakq.getContent_link_url());
		System.out.println("CONTENT_LINK_TYPE : " + sectionBreakq.getContent_link_type());
		System.out.println("CONTENT_LINK_POLICY : " + sectionBreakq.getContent_link_policy());
		System.out.println("CONTENT_LINK_HTML : " + sectionBreakq.getContent_link_html());
		System.out.println("########### Printing sectionBreakQ End ################");

	}

}
