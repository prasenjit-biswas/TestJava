package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

public class worksheet_trueFalse_answer extends worksheet_answer {
	private boolean correctAnswer = true;

	public worksheet_trueFalse_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_trueFalse;

		correctAnswer = theInput.readBoolean();
		System.out.println("<<<Correct Answer>>>" + correctAnswer);
	}

	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
