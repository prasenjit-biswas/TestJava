package Dataretrieval.Question;

import java.io.DataInputStream;

public class docQ extends Question{
	public docQ(){}
	public static docQ getDocQ(Question result, DataInputStream theInput, int format) throws Exception{
		docQ docq = null;
		try {
			docq = new docQ();
			docq = (docQ)docq.getQuestion(result, theInput, format);
			print_docQ(docq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docq;
	}
	private static void print_docQ(docQ docq) {
		System.out.println("########### Printing docQ Start ################");
		System.out.println(" Title " + docq.getSelectionTitle() + "(" + docq.getQuestionType() +")");
		System.out.println("questionType : " + docq.getQuestionType());
		System.out.println("usePopup : " + docq.isUsePopup());
		System.out.println("questionIsHTML : " + docq.isQuestionIsHTML());
		System.out.println("mathColumns : " + docq.isMathColumns());
		System.out.println("indent : " + docq.isIndent());
		System.out.println("Larger : " + docq.isLarger());
		System.out.println("smaller : " + docq.isSmaller());
		System.out.println("autoWrap : " + docq.isAutoWrap());
		System.out.println("questionIsHTML : " + docq.isQuestionIsHTML());
		System.out.println("QuestionIsMathML : " + docq.isQuestionIsMathML());
		System.out.println("questionIsTeX : " + docq.isQuestionIsTeX());
		System.out.println("questionIsLaTeX : " + docq.isQuestionIsLaTeX());
		System.out.println("LongQuestion : " + docq.isLongQuestion());
		System.out.println("Larger : " + docq.isLarger());
		System.out.println("id : " + docq.getId());
		System.out.println("sqlID : " + docq.getSqlID());
		System.out.println("Storage : " + docq.getStorage());
		System.out.println("boxWidth : " + docq.getBoxWidth());
		System.out.println("boxHeight : " + docq.getBoxWidth());
		System.out.println("MaxPoints : " + docq.getMaxPoints());
		System.out.println("qtext : " + docq.getQtext());
		System.out.println("DefaultAnswer : " + docq.getDefaultAnswer());
		System.out.println("selectionTitle : " + docq.getSelectionTitle());
		System.out.println("source : " + docq.getSource());
		System.out.println("sourceInfo : " + docq.getSourceInfo());
		System.out.println("referenceTag : " + docq.getReferenceTag());
		System.out.println("pageTag : " + docq.getPageTag());
		System.out.println("hasMedia : " + docq.isHasMedia());
		System.out.println("choiceCount : " + docq.getChoiceCount());
		System.out.println("pointsCount : " + docq.getPointsCount());
		System.out.println("feedbackCount : " + docq.getFeedbackCount());
		System.out.println("localCount : " + docq.getLocalCount());
		System.out.println("answerFeedbackCount : " + docq.getAnswerFeedbackCount());
		System.out.println("theCount 1: " + docq.getTheCount());
		System.out.println("theCount 2 : " + docq.getTheCount());
		System.out.println("theCount 3 : " + docq.getTheCount());
		System.out.println("..... printing hints ....");
		for(String ishintsStr : docq.getHints())
		{
			System.out.println(" hints : " + ishintsStr);
		}
		System.out.println("CONTENT_LINK_TITLE : " + docq.getContent_link_title());
		System.out.println("CONTENT_LINK_URL : " + docq.getContent_link_url());
		System.out.println("CONTENT_LINK_TYPE : " + docq.getContent_link_type());
		System.out.println("CONTENT_LINK_POLICY : " + docq.getContent_link_policy());
		System.out.println("CONTENT_LINK_HTML : " + docq.getContent_link_html());
		System.out.println("########### Printing docQ End ################");

	}

}
