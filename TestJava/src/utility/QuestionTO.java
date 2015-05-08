package com.mcgrawhill.ezto.utilities;

import java.util.HashMap;
import java.util.Map;

public class QuestionTO {
	
	private Map<String,QuestionParameters> questionMap;
	
	
	public Map<String, QuestionParameters> getQuestionMap() {
		if(questionMap == null){
			questionMap = new HashMap<String, QuestionParameters>();
		}
		return questionMap;
	}

	public void setQuestionMap(Map<String, QuestionParameters> questionMap) {
		this.questionMap = questionMap;
	}


}
