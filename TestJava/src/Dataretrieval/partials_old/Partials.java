package Dataretrieval.partials;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Dataretrieval.util.OracleConnection;
import Dataretrieval.util.tp_utility;

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
			getPartials(con);
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

	private static void getPartials(Connection con) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ByteArrayOutputStream theBytes = new ByteArrayOutputStream();
		pst = con.prepareStatement(PARTIAL_QUERY);
		pst.setString(1, "13252698001262218");
		pst.setString(2, "13637_58891562_1_32299070");

		// execute Partials SELECT query
		rs = pst.executeQuery();

		InputStream theData = null;

		// iterate result-set and fetch BLOB data
		ArrayList partialList = new ArrayList();
		HashMap partialMap = new HashMap(); 
		while (rs.next()) {
			long dataSize = rs.getLong("datasize");
			theData = rs.getBinaryStream("params");
			//System.out.println("######## dataSize >>   "+dataSize);
			partialMap.put("datasize", Long.valueOf(dataSize));
			partialMap.put("params", theData);
			
			partialList.add(partialMap);
		}
		
		Iterator itr = partialList.iterator();
		while(itr.hasNext()){
			partialMap = (HashMap)itr.next();
			long dataSize = ((Long)partialMap.get("datasize")).longValue();
			theData = (InputStream)partialMap.get("params");
			byte[] barray = new byte[64000];
			int readCount = (int) dataSize;
			if (readCount > 64000) {
				readCount = 64000;
			}
			int charsread = 0;
			try {
				charsread = theData.read(barray, 0, 64000);
			} catch (IOException ignore) {
			}
			if (charsread > 0)
				theBytes.write(barray, 0, charsread);
			if (theData == null) {
				return;
			}
			DataInputStream theInput = new DataInputStream(
					new ByteArrayInputStream(theBytes.toByteArray()));
			List<String> nameList = new ArrayList<String>();
			Map<String, String> nameValueMap = new HashMap<String, String>();
	
			// read the names
			int nameCount = theInput.readInt();
			for (int i = 0; i < nameCount; i++) {
				nameList.add(theInput.readUTF());
			}
	
			// read the values and put them in HashMap as key-value pairs
			int valueCount = theInput.readInt();
			Iterator<String> iterator = nameList.iterator();
	
			for (int i = 0; i < valueCount; i++) {
				nameValueMap.put(iterator.hasNext() ? iterator.next() : null,
						tp_utility.readString(theInput));
			}
	
			// prints the name-value(s) from HashMap
			//PrintWriter pw = new PrintWriter(new File("C:\\get_partials.txt"));
			Set<Map.Entry<String, String>> entrySet = nameValueMap.entrySet();
			Iterator<Entry<String, String>> entryIterator = entrySet.iterator();
			while (entryIterator.hasNext()) {
				 System.out.println("entry set:: " + entryIterator.next());
				//pw.println(entryIterator.next());
			}
			//pw.flush();
			//pw.close();
		}
	}
}
