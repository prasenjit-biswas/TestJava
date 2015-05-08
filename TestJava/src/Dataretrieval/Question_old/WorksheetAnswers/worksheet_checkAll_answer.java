package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Dataretrieval.Question.VectorAdapter;

public class worksheet_checkAll_answer extends worksheet_answer {

	private ArrayList choices;
	private ArrayList checked;

	public worksheet_checkAll_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_checkAll;
		choices = new ArrayList();
		int count = theInput.readInt();
		for (int i = 0; i < count; i++)
			choices.add(theInput.readUTF());
		checked = new VectorAdapter();
		count = theInput.readInt();
		for (int i = 0; i < count; i++) {
			checked.add(theInput.readUTF());
		}

		printChekAllAnswer();
	}

	private void printChekAllAnswer() {
		System.out.println("<<<Choices>>>");
		for (Object obj : choices) {
			System.out.println((String) obj);
		}
		System.out.println("<<<Checked>>>");
		for (Object obj : checked) {
			System.out.println((String) obj);
		}
	}

	public ArrayList getChoices() {
		return choices;
	}

	public void setChoices(ArrayList choices) {
		this.choices = choices;
	}

	public ArrayList getChecked() {
		return checked;
	}

	public void setChecked(ArrayList checked) {
		this.checked = checked;
	}

}
