package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

public class worksheet_number_answer extends worksheet_answer {
	
	String correctAnswer = "0";

	String formatString = "#";

	String precisionString = "";

	String units = "";

	int precision_type = 0;

	int field_width = 7;

	boolean engineering_units = false;

	boolean currency = false;

	public worksheet_number_answer(DataInputStream theInput, int format) throws IOException {
		type = ANSWER_TYPE_number;
		correctAnswer = theInput.readUTF();
		formatString = theInput.readUTF();
		precisionString = theInput.readUTF();
		units = theInput.readUTF();
		precision_type = theInput.readInt();
		field_width = theInput.readInt();
		engineering_units = theInput.readBoolean();
		currency = theInput.readBoolean();
		printNumberAnswer();
	}

	private void printNumberAnswer() {

		System.out.println("<<<Correct Answer>>>" + correctAnswer);
		System.out.println("<<<Format String>>>" + formatString);
		System.out.println("<<<Precision String>>>" + precisionString);
		System.out.println("<<<Units>>>" + units);
		System.out.println("<<<Precision Type>>>" + precision_type);
		System.out.println("<<<Field Width>>>" + field_width);
		System.out.println("<<<Engineering Units>>>" + engineering_units);
		System.out.println("<<<Currency>>>" + currency);
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public String getPrecisionString() {
		return precisionString;
	}

	public void setPrecisionString(String precisionString) {
		this.precisionString = precisionString;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public int getPrecision_type() {
		return precision_type;
	}

	public void setPrecision_type(int precisionType) {
		precision_type = precisionType;
	}

	public int getField_width() {
		return field_width;
	}

	public void setField_width(int fieldWidth) {
		field_width = fieldWidth;
	}

	public boolean isEngineering_units() {
		return engineering_units;
	}

	public void setEngineering_units(boolean engineeringUnits) {
		engineering_units = engineeringUnits;
	}

	public boolean isCurrency() {
		return currency;
	}

	public void setCurrency(boolean currency) {
		this.currency = currency;
	}

}
