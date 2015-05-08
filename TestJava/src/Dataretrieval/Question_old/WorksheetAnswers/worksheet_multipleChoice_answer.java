package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

import Dataretrieval.Question.VectorAdapter;

public class worksheet_multipleChoice_answer extends worksheet_answer {
	private int correctChoice = 0;
	private VectorAdapter choices;

	public worksheet_multipleChoice_answer(DataInputStream theInput, int format)
			throws IOException {

		type = ANSWER_TYPE_multipleChoice;
		correctChoice = theInput.readInt();
		choices = new VectorAdapter();
		int count = theInput.readInt();
		for (int i = 0; i < count; i++) {
			choices.addElement(theInput.readUTF());
		}
		printMultipleChoiceAnswer();
	}

	private void printMultipleChoiceAnswer() {
		System.out.println("<<<Mutiple Choice Options>>>");
		for(Object obj : choices){
			System.out.println((String) obj);
		}
	}

	public int getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(int correctChoice) {
		this.correctChoice = correctChoice;
	}

	public VectorAdapter getChoices() {
		return choices;
	}

	public void setChoices(VectorAdapter choices) {
		this.choices = choices;
	}

}
