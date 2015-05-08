package utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dataretrieval.util.OracleConnection;

import com.mcgrawhill.ezto.sql.tp_sql;
import com.mcgrawhill.ezto.test.questions.question;
import com.mcgrawhill.ezto.test.questions.questionBank;
import com.mcgrawhill.ezto.utilities.QuestionXrefTO;

public class InsertQuestion {
  
	public static void main(String args[]){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		long uniqueQuestionID = 0l;
		String newQuestionId = String.valueOf(uniqueQuestionID);
		QuestionXrefTO resultTO = null;
		int format = 0;
		long start = 0l;
		InputStream dataInput = null; 
		Map<String, String> xrefMap = null;
		try {
			con = OracleConnection.getConnection();
			start = (new java.util.Date()).getTime();
			uniqueQuestionID = InsertSelect.getEztoSequence(con);
			pstmt= con.prepareStatement( "select q.format, q.data, tqx.points, tqx.maxpoints, tqx.reftag " +
										 "from questions q ,test_question_xref tqx " +
										 "where tqx.testid = ? and tqx.questionid = q.questionid  and tqx.questionid = ? " );
			pstmt.setString(1, "13252698005812180");
			pstmt.setString(2, "13252698006404254");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				format = rs.getInt("format");
				InputStream dbin= rs.getBinaryStream("data");
				ByteArrayOutputStream bout= new ByteArrayOutputStream();
	 	  		byte[] buffer = new byte[32768];
	 			for(;;) {	
	  				int size = dbin.read(buffer);
	  				if (size == -1) break;
	  				bout.write(buffer,0,size);
	  			}
	  			dbin.close();
	  			bout.flush();
	  			byte barray[]= bout.toByteArray();
	  			bout.close();
				InputStream bin= new ByteArrayInputStream(barray);
				dataInput= new DataInputStream(bin);
				xrefMap = new HashMap<String, String>(); 
				xrefMap.put("points", rs.getString("points"));
				xrefMap.put("maxpoints", rs.getString("maxpoints"));
				xrefMap.put("reftag", rs.getString("reftag"));
			}
			tp_sql.releaseResources(pstmt, rs);
			question theQ = questionBank.questionFromStreamXref( dataInput, format, xrefMap);
			theQ.sqlID = newQuestionId;
			List<question> questionList = new ArrayList<question>();
			questionList.add(theQ);
			tp_sql.putQuestions(questionList, uniqueQuestionID, con);
			System.out.println("  Time Taken time " + Long.toString((new java.util.Date()).getTime() - start) +" ms");
			//Time Taken time 3826 ms
			}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			
		}
		
	}
}
