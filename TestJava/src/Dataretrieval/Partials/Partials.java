package Dataretrieval.Partials;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dataretrieval.Test.OracleConnection;
import EZTO_Migration.src.com.mcgrawhill.ezto.utilities.parameters;




/**
 * <h3>This class gets the BLOB records from Partials table, parses them and
 * prints/displays them</h3>
 * <ul>
 * <li>get DB connection
 * <li>execute Partials SELECT query
 * <li>iterate result-set and fetch BLOB data
 * <li>read the values and put them in HashMap as key-value pairs
 * <li>prints the name-value(s) from HashMap
 * </ul>
 * 
 * @author 497647
 * 
 */
public class Partials {

	final static String PARTIAL_QUERY = "SELECT * FROM partials WHERE testID = ? and userID = ? ORDER BY block";

	public static void main(String[] args) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			// get DB connection
			con = OracleConnection.getConnection();
			getPartials(con,"9435000_237955984_1_5995548","13252698164652290");
		} catch (SQLException exSQLException) {
			System.out.print(exSQLException);
			exSQLException.printStackTrace();
			if (con != null) {
				// con.rollback();
			}
		} catch (Exception exException) {
			System.out.print(exException);
			exException.printStackTrace();
			if (con != null) {
				// con.rollback();
			}
		} finally {
			OracleConnection.releaseResources(con, pst, rs);
		}
	}

	private static void getPartials(Connection con,String useID,String testId) throws Exception {

		parameters savedParams = null;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		ResultSet qs= null;
		PreparedStatement pstmt = null;
		try {
			String userID = useID;		
		
			stmt= con.prepareStatement("SELECT * FROM partials WHERE testID=? AND userID=? ORDER BY block");
			stmt.setString(1, testId);
			stmt.setString(2, userID);
			
			boolean tryAgain= true;
			int tryCount= 0;
			while (tryAgain)
			{
				tryCount++;
				tryAgain= false;
				rs= stmt.executeQuery();
	
				ByteArrayOutputStream theBytes = new ByteArrayOutputStream();
	
				//int lastBlock = -1;
				//boolean multiBlock= false;
				boolean triedToReadSomething= false;
				int partialCount = 0;
				while (rs.next()) 
				{
					triedToReadSomething= true;
					
					int block = rs.getInt("block");
					long dataSize = rs.getLong("datasize");
					InputStream theData = null;
					//int charsread = 0;
					byte[] buffer = null;
					
					//if(tp_sql.getDatabase().equalsIgnoreCase(tp_sql.DATABASE_ORACLE)){
					theData = rs.getBinaryStream("params");
					buffer = new byte[64000];
					
					try{
					  for(;;) {	
					    int size = theData.read(buffer);
						if (size == -1) break;
						theBytes.write(buffer,0,size);
					  }
						
					  theBytes.flush();
					}
					catch (IOException ignore) {
						ignore.printStackTrace();
					}
					partialCount ++;
				}
				//Partials Table BLOB Change END
				/*if (theBytes.size() == 0) 
				{
					if (debugging) System.out.println("classware_hm.getPartial #1a: no partial found in (" + theTest.sqlID + " for " + userID + ")" );
					tp_sql.releaseResources(rs);
					tp_sql.releaseResources(stmt);
					tp_sql.releaseResources(con);
					return null;
				}*/
	
				savedParams = tp_sql.parametersFromStream(new ByteArrayInputStream(theBytes.toByteArray()));
	
				if (savedParams.size() == 0) 
				{
					// do something that'll take a while - potentially permitting any update in progress to complete
					pstmt= con.prepareStatement("SELECT COUNT(*) FROM userlists");
					qs= pstmt.executeQuery();
					boolean check= qs.next();
					tp_sql.releaseResources(pstmt);
					tp_sql.releaseResources(qs);
					
					if (++tryCount > 20)
					{
						tp_sql.releaseResources(con);
						System.out.println("Failed");
					}
					else
						tryAgain= true;
					
					continue;
					
					/*
					System.out.println("classware_hm.getPartial #1b (testID, userID) (" + theTest.sqlID + ", " + userID + " Exception: failed in loading partial");
					tp_sql.releaseResources(rs);
					tp_sql.releaseResources(stmt);
					tp_sql.releaseResources(con);
					return null;
					*/
				}
					
				/* NEVER wipe out the attempt
				{
					pstmt= con.prepareStatement("UPDATE partials SET userID=? WHERE testID=? AND userID=?");
					pstmt.setString(1, new StringBuffer("b_").append(userID).toString());
					pstmt.setString(2, theTest.sqlID);
					pstmt.setString(3, userID);
					pstmt.executeUpdate();
					System.out.println("classware_hm.getPartial #1 (testID, userID) (" + theTest.sqlID + ", " + userID + " Exception: failed in loading partial, starting attempt over");
					return (null);
				}
				*/
				/*if (partialCount > 0){
					savedParams.replaceParam(MULTI_BLOCK_PARAMS, "true");
					if (debugging) System.out.println(MULTI_BLOCK_PARAMS + " : true");
				}
				else{
					savedParams.removeParam(MULTI_BLOCK_PARAMS);
				}*/
			}

		} 
		catch (SQLException s) 
		{
			s.printStackTrace();
		}
		finally
		{
			tp_sql.releaseResources(pstmt);
			tp_sql.releaseResources(con, stmt, rs);
		}

		// rebuild missing qlist - QCS#640
		if (savedParams.getParam("qlist").length() == 0) 
		{
			System.out.println("qlist missing from partial");
			//repairPartialQlist(theHandler, theTest, savedParams);
		}

//		if (debugging) 
//		{
//			if (savedParams != null)
//				savedParams.dump();
//			else
//				System.out.println("  null");
//			System.out.println("END getPartial:");
//			System.out.println("");
//		}
//
//		return (savedParams);
	
	}

}
