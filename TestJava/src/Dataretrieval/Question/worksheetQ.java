package Dataretrieval.Question;

import java.io.DataInputStream;
import java.util.Iterator;

import Dataretrieval.Question.WorksheetAnswers.worksheet_answer;
import Dataretrieval.util.tp_utility;

public class worksheetQ extends Question {
	public static int QUESTION_TYPE_undefined = 0;
	public static int QUESTION_TYPE_worksheet = 101;
	public int type = QUESTION_TYPE_worksheet;
	private VectorAdapter answers = new VectorAdapter();

	private String explanation;
	static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	public static int ANSWER_TYPE_trueFalse = 1;
	public static int ANSWER_TYPE_multipleChoice = 2;
	public static int ANSWER_TYPE_number = 3;
	public static int ANSWER_TYPE_phrase = 4;
	public static int ANSWER_TYPE_labeledValues = 5; // label and value pairs
	public static int ANSWER_TYPE_flash = 6;
	public static int ANSWER_TYPE_essay = 7;
	public static int ANSWER_TYPE_lsi = 8;
	public static int ANSWER_TYPE_checkAll = 9;
	public static int ANSWER_TYPE_external = 10;
	public static int ANSWER_TYPE_listCompletion = 11;
	public static int ANSWER_TYPE_avRecording = 12;
	public static int ANSWER_TYPE_videoVoiceover = 13;

	public static String REFRESH_PARAM = "refreshParam";

	public worksheetQ() {
	}

	public static worksheetQ getWorksheetQ(Question result,
			DataInputStream theInput, int format) throws Exception {

		// super( theInput, format );
		worksheetQ worksheetQ = (worksheetQ) Question.getQuestion(result,
				theInput, format);
		// explanation= theInput.readUTF();
		worksheetQ.setExplanation(tp_utility.readString(theInput));

		int count = theInput.readInt();
		VectorAdapter answerVector = new VectorAdapter();
		for (int i = 0; i < count; i++) {
			worksheet_answer theAnswer = worksheet_answer.read(theInput, format);
			if (theAnswer != null) {
				answerVector.addElement(theAnswer);
			}
		}
		worksheetQ.setAnswers(answerVector);
		if (count != worksheetQ.getAnswers().size())
			System.out.println("count should be " + count + "; it actually is "+ worksheetQ.getAnswers().size());

		// read additional subclasses here
		printWorksheetQ(worksheetQ);
		return worksheetQ;
	}

	private static void printWorksheetQ(worksheetQ worksheetQ) {
		System.out.println("<<<Worksheet explanation>>>\n "
				+ worksheetQ.getExplanation());
		richPropertiesUtil questionProperties = worksheetQ
				.getQuestionProperties();
		if (questionProperties != null) {
			System.out.println("<<<Worksheet Question Properties>>>");
			Iterator questionPropItr = questionProperties.getList().keySet()
					.iterator();
			String questionPropName = "";
			String questionPropVal = "";
			System.out.println("Title : " + questionProperties.getTitle());
			while (questionPropItr.hasNext()) {
				questionPropName = (String) questionPropItr.next();
				questionPropVal = questionProperties.getString(
						questionPropName, "NA");
				System.out.println("Name : " + questionPropName + ", Value : "
						+ questionPropVal);
			}
		}
	}

	public VectorAdapter getAnswers() {
		return answers;
	}

	public void setAnswers(VectorAdapter answers) {
		this.answers = answers;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
