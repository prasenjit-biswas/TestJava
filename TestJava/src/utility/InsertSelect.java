package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mcgrawhill.ezto.sql.tp_sql;

import Dataretrieval.util.OracleConnection;

public class InsertSelect {
	public static final String query = "INSERT INTO questions(bankid,questionid,format,data,previewhtml)" +
      "SELECT ? , ? , format,data,previewhtml FROM  questions q WHERE q.questionid = ?";

	public static void main(String args[]){
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  long bankid  = 0l;
		  long start = 0l;
		  String questionId = "";
		  try{
			  con = OracleConnection.getConnection(); 
			  start= (new java.util.Date()).getTime();
			  bankid = getEztoSequence(con);
			  questionId = String.valueOf(bankid); 
			  System.out.println(" EZTO sequence : "+bankid); 
			  pstmt = con.prepareStatement(query);
			  pstmt.setLong(1,bankid );
			  pstmt.setString(2, questionId);
			  pstmt.setString(3, "13252698006404254");
			  pstmt.executeUpdate();
			  System.out.println("  Time Taken time " + Long.toString((new java.util.Date()).getTime() - start) +" ms");
			  //Time Taken time 1963 ms
		  }catch(SQLException sql){
			  sql.printStackTrace();  
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }finally{
			  OracleConnection.releaseResources(con);
			  OracleConnection.releaseResources(pstmt);
		  }
	}

	
	
	public static long getEztoSequence(Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs= null;
		long eztoSequence =0l;
		try {
			stmt= con.prepareStatement("SELECT EZTO_SEQUENCE.nextval FROM dual");
			rs= stmt.executeQuery();
			if (rs.next()) {
				eztoSequence = rs.getLong(1);				
			}
			tp_sql.releaseResources(stmt, rs);
		} catch (SQLException s) {
			System.out.println("unable to get EZTO_SEQUENCE.nextval FROM dual");
			s.printStackTrace();
			throw s;
		}finally{
			tp_sql.releaseResources(stmt, rs);
		}
		return eztoSequence;
	}

}
