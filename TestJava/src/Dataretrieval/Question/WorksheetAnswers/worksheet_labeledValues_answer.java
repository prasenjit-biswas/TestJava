package Dataretrieval.Question.WorksheetAnswers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class worksheet_labeledValues_answer extends worksheet_answer {

	private int field_width = 7;
	private Vector answerPairs = null;

	public worksheet_labeledValues_answer(DataInputStream theInput, int format)
			throws IOException {
		type = ANSWER_TYPE_labeledValues;
		field_width = theInput.readInt();
		answerPairs = new Vector();
		int pairCount = theInput.readInt();
		for (int i = 0; i < pairCount; i++) {
			ConcurrentHashMap newPair = new ConcurrentHashMap();
			int keyCount = theInput.readInt();
			for (int j = 0; j < keyCount; j++)
				newPair.put(theInput.readUTF(), theInput.readUTF());
			answerPairs.addElement(newPair);
		}
		printLabeledValuedAnswers();
	}

	private void printLabeledValuedAnswers() {
		System.out.println("<<<Field Width>>>" + field_width);
		System.out.println("<<<Answer Pairs>>>");
		for (Object obj : answerPairs) {
			Map chm = (ConcurrentHashMap) obj;
			Iterator iter = chm.entrySet().iterator();
			while (iter.hasNext()) {
				System.out.println(iter.next());
			}
		}
	}

	public int getField_width() {
		return field_width;
	}

	public void setField_width(int fieldWidth) {
		field_width = fieldWidth;
	}

	public Vector getAnswerPairs() {
		return answerPairs;
	}

	public void setAnswerPairs(Vector answerPairs) {
		this.answerPairs = answerPairs;
	}

}
