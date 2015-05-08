package Dataretrieval.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class tp_Select_Util {
	private ArrayList<QuestionGroupUtil>	questionGroups;
		
	
	public ArrayList<QuestionGroupUtil> getQuestionGroups() {
		return questionGroups;
	}

	public void setQuestionGroups(ArrayList<QuestionGroupUtil> questionGroups) {
		this.questionGroups = questionGroups;
	}

	private void printTpSelect(){
		for(QuestionGroupUtil questionGroupUtil : questionGroups){
			questionGroupUtil.printQuestionGroup();
		}
	}
	
	public tp_Select_Util (Connection con, int format, String testId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet gs = null;
		questionGroups= new ArrayList<QuestionGroupUtil>();
		
		try {
			pstmt= con.prepareStatement("SELECT groupID, name, enabled, randomize, randomCount, summarize from groups WHERE testID = ? ORDER BY orderID");
			pstmt.setString(1, testId);
			gs= pstmt.executeQuery();
			if (gs.next()) {
				do {
					questionGroups.add( new QuestionGroupUtil(con, gs, format) );
				} while (gs.next());
			}
			else {
				System.out.println("No Question Group found");
			}
			printTpSelect();
			
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	 
	    finally{
	      if(gs != null){
	    	  gs.close();
	      }
		  if(pstmt != null){
			  pstmt.close();
		  }
		 }
	}	
	
}
	
	
	