package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

import Dataretrieval.util.tp_utility;

public class worksheet_flash_answer extends worksheet_answer {

	private String flashType = "";
	private String inputState = "";
	private int width = 320;
	private int height = 240;

	public worksheet_flash_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_flash;
		flashType = theInput.readUTF();
		inputState = tp_utility.readString(theInput);
		width = theInput.readInt();
		height = theInput.readInt();
		printFlasAnswer();
	}

	private void printFlasAnswer() {

		System.out.println("<<<Flash Type>>>" + flashType);
		System.out.println("<<<Input State>>>" + inputState);
		System.out.println("<<<Width>>>" + width);
		System.out.println("<<<Height>>>" + height);
	}

	public String getFlashType() {
		return flashType;
	}

	public void setFlashType(String flashType) {
		this.flashType = flashType;
	}

	public String getInputState() {
		return inputState;
	}

	public void setInputState(String inputState) {
		this.inputState = inputState;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
