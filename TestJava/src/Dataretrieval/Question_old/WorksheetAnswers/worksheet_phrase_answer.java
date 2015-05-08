package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

import Dataretrieval.Question.VectorAdapter;

public class worksheet_phrase_answer extends worksheet_answer {
	private boolean caseSensitive = false;
	private int field_width = 15;
	VectorAdapter correctAnswers;

	public worksheet_phrase_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_phrase;
		caseSensitive = theInput.readBoolean();
		field_width = theInput.readInt();
		correctAnswers = new VectorAdapter();
		int count = theInput.readInt();
		for (int i = 0; i < count; i++) {
			correctAnswers.addElement(theInput.readUTF());
		}
		printPhraseAnswer();
	}

	private void printPhraseAnswer() {
		System.out.println("<<<Case Sensitive>>>" + caseSensitive);
		System.out.println("<<<Field Width>>>" + field_width);
		System.out.println("<<<Correct Answers>>>");
		for (Object obj : correctAnswers) {
			System.out.println((String) obj);
		}
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public int getField_width() {
		return field_width;
	}

	public void setField_width(int fieldWidth) {
		field_width = fieldWidth;
	}

	public VectorAdapter getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(VectorAdapter correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

}
