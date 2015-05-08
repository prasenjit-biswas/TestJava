package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Dataretrieval.util.OracleConnection;

//import Dataretrieval.util.OracleConnection;

public class Test {

	public static int ACTION_NONE = 0;
	public static int ACTION_FEEDBACK = 1;

	private  securityInfoUtil	securityInfo = null;
	private ArrayList			globalRandoms;
	//private questionBankUtil	quest;
	//private tp_reportListUtil	theReportList;
	private tp_guiUtil	theGUI = null;
	private ArrayList			submissionActions;
	private tp_Select_Util	theSelection = null;
	private HashMap idMap = null;
	
	public HashMap getIdMap() {
		return idMap;
	}

	public void setIdMap(HashMap idMap) {
		this.idMap = idMap;
	}

	public securityInfoUtil getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(securityInfoUtil securityInfo) {
		this.securityInfo = securityInfo;
	}

	public ArrayList getGlobalRandoms() {
		return globalRandoms;
	}

	public void setGlobalRandoms(ArrayList globalRandoms) {
		this.globalRandoms = globalRandoms;
	}
	/*
	public questionBankUtil getQuest() {
		return quest;
	}

	public void setQuest(questionBankUtil quest) {
		this.quest = quest;
	}
	*/
	public tp_guiUtil getGUI() {
		return theGUI;
	}

	public void setGUI(tp_guiUtil theGUI) {
		this.theGUI = theGUI;
	}

	public ArrayList getSubmissionActions() {
		return submissionActions;
	}

	public void setSubmissionActions(ArrayList submissionActions) {
		this.submissionActions = submissionActions;
	}

	public tp_Select_Util getSelection() {
		return theSelection;
	}

	public void setSelection(tp_Select_Util theSelection) {
		this.theSelection = theSelection;
	}
	
	static int nextID = 10; 
	
	public boolean showStatistics = false, correctOnRecall = true, scoreOnRecall = true;

	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
	static String LICENSE_TYPE_AMAZON_KEYS	= "amazonKeys";


	public static void main(String argv[]) throws Exception{

		Connection con = null;
		String testID = "13252698015510011";//"13252698162919398";//"13252698006812614";//"13252698002412383";//"13252698000310662";
		try {
			con = OracleConnection.getConnection();
			//con = OracleConnection.getProdDRConnection();
			//con = MySqlConnection.getConnection();
			TestsTO testsTO = getTestsTO(con, testID);
			//StandAloneConnectionProvider.releaseResources(con);
			Test test = new Test();
			if(testsTO != null){
				test = getTest(con, test, testsTO);
			}

		} catch(SQLException exSQLException){
			System.out.print(exSQLException);
			exSQLException.printStackTrace();

		} catch(Exception exException){
			System.out.print(exException);
			exException.printStackTrace();

		}finally{
			//StandAloneConnectionProvider.releaseResources(con);
			OracleConnection.releaseResources(con);
		}
	}

	public static TestsTO getTestsTO(Connection con, String testID) throws Exception {
		TestsTO testsTO = null;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		try 
		{
			stmt= con.prepareStatement("SELECT * FROM tests WHERE testID=?");
			stmt.setString(1, testID);
			rs= stmt.executeQuery();
			if (rs.next()){
				testsTO = new TestsTO();
				testsTO.setTestID(rs.getString("testID"));
				/*
				testsTO.setTitle(rs.getString("title"));
				testsTO.setEnabled(rs.getInt("enabled"));
				testsTO.setModified(rs.getLong("modified"));
				testsTO.setTestID(rs.getString("testID"));
				 */
				testsTO.setData(rs.getBinaryStream("data"));
				//theInput = new DataInputStream(testsTO.getData());
				//System.out.println("format : " + new DataInputStream(testsTO.getData()).readInt());
				//InputStream theInput = rs.getBinaryStream("data");
				/*
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
				InputStream theInput= new DataInputStream(bin);
				testsTO.setData(theInput);
				*/
				/*
				testsTO.setBeginDate(rs.getLong("beginDate"));					
				testsTO.setEndDate(rs.getLong("endDate"));
				testsTO.setRecallDate(rs.getLong("recallDate"));
				testsTO.setElapsed(rs.getLong("elapsed"));
				testsTO.setIndexID(rs.getLong("indexID"));
				testsTO.setOwner(rs.getString("owner"));
				testsTO.setOemID(rs.getLong("oemID"));
				testsTO.setUrl(rs.getString("url"));
				testsTO.setBankID(rs.getLong("bankID"));
				testsTO.setCreation(rs.getLong("creation"));
				testsTO.setManagers(rs.getLong("managers"));
				testsTO.setParticipants(rs.getLong("participants"));
				testsTO.setComputers(rs.getLong("computers"));
				 */
			} else {
				System.out.println("Bad Test id");
			}
		}
		catch (SQLException s) 
		{
			System.out.println("SQLException in getTestTO()");
			s.printStackTrace();
		}finally{
			OracleConnection.releaseResources(stmt, rs);
			//StandAloneConnectionProvider.releaseResources(stmt, rs);
		}
		return( testsTO );	
	}
	
	public static Test getTest(Connection con, Test test,TestsTO testsTO) throws Exception{
		DataInputStream theInput = null;
		int format = -1;

		try{
			theInput = new DataInputStream(testsTO.getData());
			
			format= theInput.readInt();
			System.out.println("testformat : " + format);
			test.setSecurityInfo(new securityInfoUtil( theInput, format ));
			test.setGUI(new tp_guiUtil(theInput, format));
			
			ArrayList submissionActions = new ArrayList();
			int actionCount= theInput.readInt();
			System.out.println("actionCount : " + actionCount);
			for (int i=0 ; i<actionCount ; i++) {
				int actionType= theInput.readInt();
				tp_actionUtil newAction= null;

				if (actionType == ACTION_NONE){
					newAction = new tp_actionUtil(theInput, format);
				}else if (actionType == ACTION_FEEDBACK){
					newAction = new tp_feedback_actionUtil(theInput, format);
				}
				else
					throw (new Exception( "undefined action type" ) );
				submissionActions.add( newAction );
			}
			test.setSubmissionActions(submissionActions);			
			//test.setSelection(new tp_Select_Util(con, format, testsTO.getTestID()));
			/*if (format <= 402) {
				test.setSelection(new tp_Select_Util( theInput, format));

				//theSelection.writeSQL(con, this, format);

			}else{
				test.setSelection(new tp_Select_Util(con, format, testsTO.getTestID()));
			}*/
			// read the global randoms
			ArrayList<randomVariableUtil> globalRandoms= new ArrayList<randomVariableUtil>();
			int globalCount= theInput.readInt();
			for (int i=0 ; i<globalCount ; i++) {
				randomVariableUtil theVar= new randomVariableUtil(theInput, format);
				globalRandoms.add( theVar );
			}
			test.setGlobalRandoms(globalRandoms);
			
			nextID= theInput.readInt();
			System.out.println(" NextID : "+nextID);
			HashMap<String, String> idMap= new HashMap<String, String>();
			int count= theInput.readInt();
			for (int i=0 ; i< count ; i++) {
				String localID= theInput.readUTF();
				String sqlID= theInput.readUTF();
				idMap.put( localID, sqlID );
			}
			printIdMap(idMap);
			test.setIdMap(idMap);
			// read questions
			//quest= new questionBankUtil( con,theInput, format, testsTO);

			//if (theServlet.debug) System.out.println("ckpt#7");

			// read the report list
			//theReportList= new tp_reportListUtil( theInput, format);

		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return test;
	}


	private static void printIdMap(HashMap<String, String> idMap){
		System.out.println("#### printing idMap in Test....");
		for(String localID : idMap.keySet()){
		  System.out.println("localID in test : " + localID + ", sqlID [questionid] : " + idMap.get(localID));
		  //System.out.println("'" + localID + "',");
		}
	}
	
	static String readString( DataInputStream input )
	throws IOException{
		String result= input.readUTF();
		if (result.equals(LONG_STRING))
		{
			//System.out.println("inputting " + LONG_STRING);
			int arrayLength= input.readInt();
			byte[] barray= new byte[ arrayLength ];
			input.read( barray, 0, arrayLength );
			result= new String( barray, "UTF-8" );
		}
		return(result);
	}
}
