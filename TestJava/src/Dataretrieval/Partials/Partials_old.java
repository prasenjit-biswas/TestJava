package Dataretrieval.Partials;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

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
public class Partials_old {

	static String mysqlurl = "jdbc:mysql://nvldb06.eppg.com:3306/";
	static String mysqldb = "ezto_qa2";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	static String mysqluser = "ezto_qa2";
	static String mysqlpass = "pixie";

	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztodev)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztodev";

	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB;
	static Properties prop = new Properties();
	static final String FILE_NAME = "FILE_NAME";
	static final String FILE_LOCATION = "FILE_LOCATION";

	final static String LONG_STRING = "***UTF String Too Long for writeUTF***";
	final static String PARTIAL_QUERY = "SELECT * FROM partials WHERE testID = ? and userID = ? ORDER BY block";

	public static void main(String[] args) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			// get DB connection
			con = getConnection();
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
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	private static Connection getConnection() throws SQLException, Exception {
		Connection con = null;
		System.out.println("database : " + database);
		if (database.equals(ORACLE_DB)) {
			Class.forName(oradriver);
			con = DriverManager.getConnection(oraurl, orauser, orapass);
		} else if (database.equals(MYSQL_DB)) {
			Class.forName(mysqldriver);
			con = DriverManager.getConnection(mysqlurl + mysqldb, mysqluser,
					mysqlpass);
		}
		return con;
	}

	private static void getPartials(Connection con) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ByteArrayOutputStream theBytes = new ByteArrayOutputStream();
		pst = con.prepareStatement(PARTIAL_QUERY);
		pst.setString(1, "13252698008264512");
		pst.setString(2, "instructorPreviewID");

		// execute Partials SELECT query
		rs = pst.executeQuery();

		InputStream theData = null;

		// iterate result-set and fetch BLOB data
		if (rs.next()) {
			long dataSize = rs.getLong("datasize");
			theData = rs.getBinaryStream("params");
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
		}

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
			System.out.println(entryIterator.next());
			//pw.println(entryIterator.next());
		}
		//pw.flush();
		//pw.close();
	}

}
