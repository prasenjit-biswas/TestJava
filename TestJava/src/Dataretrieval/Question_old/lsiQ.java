package Dataretrieval.Question;

import java.io.DataInputStream;

public class lsiQ extends Question {
	public lsiQ(){}
	public static lsiQ getLsiQ(Question result, DataInputStream theInput, int format) throws Exception{
		lsiQ lsiq = null;
		try {
			lsiq = new lsiQ();
			lsiq = (lsiQ)lsiq.getQuestion(result, theInput, format);
			//print_docQ(docq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsiq;
	}
}
