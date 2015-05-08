package utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dataretrieval.Question.richPropertiesUtil;
import Dataretrieval.util.OracleConnection;

public class TestMangrade {
	public static String INSERT_FULLCREDIT = "INSERT INTO fullcredit (testID, questionID, data) VALUES (?,?,?)";
	public static String SELECT_FULLCREDIT = "select 1 from fullcredit where testid = ?";
	public static void main(String[] args) {
		String testid = "13252698046405843";
		String qid = "13252698046405847";
		String adjustmentProperty = "manualScore";//TestFullCredit.ADJUSTMENT_FULLCREDIT;
		insertFullCredit(testid, qid, adjustmentProperty);
		getFullCreditData(testid);
	}
	
	public static void insertFullCredit(String testid, String qid, String adjustmentProperty){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = OracleConnection.getConnection();
			pstmt = con.prepareStatement(INSERT_FULLCREDIT);
			pstmt.setString(1, testid);
			pstmt.setString(2, qid);
			richPropertiesUtil flaggedItems = richPropertiesUtil.newInstance(TestFullCredit.FLAGGED_ITEMS);
			flaggedItems.setString(TestFullCredit.ADJUSTMENT_PROPERTY, adjustmentProperty);
			ByteArrayOutputStream theOut = new ByteArrayOutputStream();
			TestFullCredit.writeString(flaggedItems.toXML(), new DataOutputStream(theOut));
			byte[] barray = theOut.toByteArray();
			ByteArrayInputStream bStream = new ByteArrayInputStream(barray);
			pstmt.setBinaryStream(3, bStream, barray.length);
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("Problem for updating testid : "+testid+", qid : "+qid);
			ex.printStackTrace();
		}finally{
			TestFullCredit.releaseResources(pstmt);
			TestFullCredit.releaseResources(con);
		}
	}
	
	public static String getFullCreditData(String testid){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String gotFalg = "false";
		try{
			con = OracleConnection.getConnection();
			pstmt = con.prepareStatement(SELECT_FULLCREDIT);
			pstmt.setString(1, testid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				gotFalg = "true";
			}
		}catch(Exception ex){
			System.out.println("Problem for updating testid : "+testid);
			ex.printStackTrace();
		}finally{
			TestFullCredit.releaseResources(pstmt);
			TestFullCredit.releaseResources(con);
		}
		System.out.println(" gotFalg : "+gotFalg);
		return gotFalg;
	}
}
