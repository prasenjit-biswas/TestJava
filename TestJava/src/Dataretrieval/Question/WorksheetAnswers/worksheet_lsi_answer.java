package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;

import Dataretrieval.Question.richPropertiesUtil;

public class worksheet_lsi_answer extends worksheet_answer {

	private richPropertiesUtil lsiProperties = null;

	public worksheet_lsi_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_lsi;
		lsiProperties = new richPropertiesUtil(theInput.readUTF());
		System.out.println("<<<LSI Properties>>>" + lsiProperties);
	}

	public richPropertiesUtil getLsiProperties() {
		return lsiProperties;
	}

	public void setLsiProperties(richPropertiesUtil lsiProperties) {
		this.lsiProperties = lsiProperties;
	}

}
