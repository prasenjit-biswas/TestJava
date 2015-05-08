package Dataretrieval.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionGroupUtil {
	private boolean	randomSelection= false;
	
	/** 
	 * Boolean flag indicating that this group is enabled, meaning its questions are available for generating assessments. 
	 * Default is false; new groups are not enabled. 
	 */
	private boolean	enabled = false;
	
	/** 
	 * Integer number of questions to select from the group when {@link #randomSelection} is enabled. 
	 * If set to zero (the default value), then no questions are selected.
	 */
	private int		randomCount= 0;
	
	/** Long integer sqlID (key) value for the group in the database. */
	private long		groupID = -1;
	
	/** String containing the name of this group. */
	private String	name = "";
	
	private ArrayList<String> idlist;
	
	private HashMap<String, String> orderIdMap = null;
	
	private boolean	summarize= false;
	
	public boolean isRandomSelection() {
		return randomSelection;
	}

	public void setRandomSelection(boolean randomSelection) {
		this.randomSelection = randomSelection;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getRandomCount() {
		return randomCount;
	}

	public void setRandomCount(int randomCount) {
		this.randomCount = randomCount;
	}

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getIdlist() {
		return idlist;
	}

	public void setIdlist(ArrayList<String> idlist) {
		this.idlist = idlist;
	}

	public HashMap<String, String> getOrderIdMap() {
		return orderIdMap;
	}

	public void setOrderIdMap(HashMap<String, String> orderIdMap) {
		this.orderIdMap = orderIdMap;
	}

	public boolean isSummarize() {
		return summarize;
	}

	public void setSummarize(boolean summarize) {
		this.summarize = summarize;
	}

	public void printQuestionGroup(){
		System.out.println("##### Printing QuestionGroup start #####");
		System.out.println("randomSelection : " + randomSelection);
		System.out.println("enabled : " + enabled);
		System.out.println("randomCount : " + randomCount);
		System.out.println("groupID : " + groupID);
		System.out.println("name : " + name);
		System.out.println("summarize : " + summarize);
		System.out.println("printing idlist .. ");
		for(String questionID : idlist){
			System.out.println("questionID : " + questionID + " , orderID : " + orderIdMap.get(questionID));
		}
		System.out.println("##### Printing QuestionGroup end ###########");
	}
	
	public QuestionGroupUtil( Connection con, ResultSet gs, int format )
	throws SQLException {
	PreparedStatement pstmt= null;
	ResultSet qs= null;
	
	
	try {
		idlist= new ArrayList<String>();
		orderIdMap = new HashMap<String, String>();
		
		groupID= gs.getLong("groupID");
		name= gs.getString("name");
		
		if (gs.getInt("enabled") == 1) enabled= true;
		if (gs.getInt("randomize") == 1) 
		{
			randomSelection= true;
			randomCount= gs.getInt("randomCount");
		}
		if (gs.getInt("summarize") == 1) summarize= true;
		
		pstmt= con.prepareStatement("SELECT orderID, questionID from groupmembers WHERE groupID = ? ORDER BY orderID");
		pstmt.setLong(1, groupID);
		
		qs= pstmt.executeQuery();
		String questionID = "";
		while (qs.next()){
			questionID = qs.getString("questionID");
			idlist.add( questionID );
			orderIdMap.put(questionID, qs.getString("orderID"));
		}
		
	} catch(Exception ex)
	{
		ex.printStackTrace();
	}
  finally{
      if(qs != null){
    	  qs.close();
      }
	  if(pstmt != null){
		  pstmt.close();
	  }
	 }

}


}