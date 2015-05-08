package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;

public class worksheet_external_answer extends worksheet_answer {

	public worksheet_external_answer(DataInputStream theInput, int format) {
		type = ANSWER_TYPE_external;
	}

}
