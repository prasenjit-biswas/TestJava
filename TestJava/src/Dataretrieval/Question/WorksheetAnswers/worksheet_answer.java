package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

import Dataretrieval.Question.richPropertiesUtil;
import Dataretrieval.util.tp_utility;

/**
 * worksheet_answer superclass
 */
public class worksheet_answer {
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

	protected int type = -1;
	protected int points = 1;
	protected String name = "";
	protected richPropertiesUtil answerProperties = null;
	protected String answerTypeText = "";

	public richPropertiesUtil getAnswerProperties() {
		return answerProperties;
	}

	public void setAnswerProperties(richPropertiesUtil answerProperties) {
		this.answerProperties = answerProperties;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static worksheet_answer read(DataInputStream theInput, int format)
			throws IOException {
		try {
			// read common variables
			int thisType = theInput.readInt();
			int thePoints = theInput.readInt();

			String theName = theInput.readUTF();
			richPropertiesUtil theProps = richPropertiesUtil.newInstance("answerProperties");
			
			System.out.println("inside worksheet_answer read richPropertiesUtil");
			
			if (format >= 449) {
			  theProps = new richPropertiesUtil(tp_utility.readString(theInput));
			}

			// read answer_type object
			worksheet_answer result = null;
			String worksheet_anwer_type_text = null;

			if (thisType == ANSWER_TYPE_trueFalse) {
				worksheet_anwer_type_text = "trueFalse";
				result = new worksheet_trueFalse_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_multipleChoice) {
				worksheet_anwer_type_text = "multipleChoice";
				result = new worksheet_multipleChoice_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_number) {
				worksheet_anwer_type_text = "number";
				result = new worksheet_number_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_phrase) {
				worksheet_anwer_type_text = "phrase";
				result = new worksheet_phrase_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_labeledValues) {
				worksheet_anwer_type_text = "labeledValues";
				result = new worksheet_labeledValues_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_flash) {
				worksheet_anwer_type_text = "flash";
				result = new worksheet_flash_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_essay) {
				worksheet_anwer_type_text = "essay";
				result = new worksheet_essay_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_lsi) {
				worksheet_anwer_type_text = "lsi";
				result = new worksheet_lsi_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_checkAll) {
				worksheet_anwer_type_text = "checkAll";
				result = new worksheet_checkAll_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_external) {
				worksheet_anwer_type_text = "external";
				result = new worksheet_external_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_listCompletion) {
				worksheet_anwer_type_text = "listCompletion";
				result = new worksheet_listCompletion_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_avRecording) {
				worksheet_anwer_type_text = "avRecording";
				result = new worksheet_avRecording_answer(theInput, format);
			}

			else if (thisType == ANSWER_TYPE_videoVoiceover) {
				worksheet_anwer_type_text = "videoVoiceover";
				result = new worksheet_videoVoiceover_answer(theInput, format);
			}

			else {
				System.out.println("worksheet answer type error");
				return (null);
			}

			// assign common variable values
			result.setPoints(thePoints);
			result.setName(theName);
			result.setAnswerProperties(theProps);
			result.setAnswerTypeText(worksheet_anwer_type_text);
			printWorksheetAnswer(result);
			return (result);
		} catch (IOException catchIt) {
			System.out
					.println("worksheet_answer.read(): Exception reading answer");
		}

		return (null);
	}

	public static void printWorksheetAnswer(worksheet_answer theAnswer) {
		System.out.println("<<<WorksheetAnswer Type>>>"
				+ theAnswer.getAnswerTypeText());
		System.out.println("<<<WorksheetAnswer Name>>>" + theAnswer.getName());
		System.out.println("<<<WorksheetAnswer Points>>>"
				+ theAnswer.getPoints());
		richPropertiesUtil answerProperties = theAnswer.getAnswerProperties();
		if (answerProperties != null) {
			System.out.println("<<<WorksheetAnswer Properties>>>");
			Iterator questionPropItr = answerProperties.getList().keySet()
					.iterator();
			String questionPropName = "";
			String questionPropVal = "";
			System.out.println("Title : " + answerProperties.getTitle());
			while (questionPropItr.hasNext()) {
				questionPropName = (String) questionPropItr.next();
				questionPropVal = answerProperties.getString(questionPropName,
						"NA");
				System.out.println("Name : " + questionPropName + ", Value : "
						+ questionPropVal);
			}
		}
	}

	public String getAnswerTypeText() {
		return answerTypeText;
	}

	public void setAnswerTypeText(String answerTypeText) {
		this.answerTypeText = answerTypeText;
	}

}