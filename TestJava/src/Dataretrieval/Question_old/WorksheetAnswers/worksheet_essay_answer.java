package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

public class worksheet_essay_answer extends worksheet_answer {

	private int field_height = 5;
	private int field_width = 60;
	private String sampleAnswer = "";

	public worksheet_essay_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_essay;
		points = 0;
		field_height = theInput.readInt();
		field_width = theInput.readInt();
		sampleAnswer = theInput.readUTF();
		printEssayAnswer();
	}

	private void printEssayAnswer() {

		System.out.println("<<<Points>>>" + points);
		System.out.println("<<<Field Height>>>" + field_height);
		System.out.println("<<<Field Hight>>>" + field_width);
		System.out.println("<<<Sample Answer>>>" + sampleAnswer);
	}

	public int getField_height() {
		return field_height;
	}

	public void setField_height(int fieldHeight) {
		field_height = fieldHeight;
	}

	public int getField_width() {
		return field_width;
	}

	public void setField_width(int fieldWidth) {
		field_width = fieldWidth;
	}

	public String getSampleAnswer() {
		return sampleAnswer;
	}

	public void setSampleAnswer(String sampleAnswer) {
		this.sampleAnswer = sampleAnswer;
	}

}
