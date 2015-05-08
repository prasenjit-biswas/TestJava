package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

import Dataretrieval.Question.VectorAdapter;

public class worksheet_listCompletion_answer extends worksheet_answer {

	private VectorAdapter correctAnswers;

	public worksheet_listCompletion_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_listCompletion;
		correctAnswers = new VectorAdapter();
		int count = theInput.readInt();
		for (int i = 0; i < count; i++) {
			correctAnswers.addElement(theInput.readUTF());
		}
		printListCompletionAnswer();

	}

	private void printListCompletionAnswer() {
		System.out.println("<<<Correct Answer>>>");
		for (Object obj : correctAnswers) {
			System.out.println((String) obj);
		}
	}

	public VectorAdapter getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(VectorAdapter correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

}
