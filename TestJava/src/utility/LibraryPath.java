package utility;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.axis.utils.StringUtils;

import Datafix.Question;
import Dataretrieval.util.OracleConnection;


public class LibraryPath {
	
	public static void main(String argv[]) throws Exception{
    
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;       
    
    try {
    	con = OracleConnection.getConnection();
    	
    	String finalpath = printPath(con,"13040983683403765");
    	System.out.println("\nfinalpath " + finalpath);    	
    	
    } catch(SQLException exSQLException){
	    exSQLException.printStackTrace();
	} catch(Exception exException){
	    exException.printStackTrace();
    }finally{
    	if(rs != null){
    	  rs.close();
    	}
		if(pst != null){
    	  pst.close();
		}
		if(con != null){
		  con.close();
		}
			
    }
  }
	
	
	
	
	static String	LONG_STRING		= "***UTF String Too Long for writeUTF***";
	
	public static String readString( DataInputStream input )
	throws IOException
    {
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
	
	
	public static ConcurrentHashMap hashFromStream( InputStream bStream )
	{
		ConcurrentHashMap result= new ConcurrentHashMap();		
		if (bStream != null) 
		{
			try 
			{
				DataInputStream input= new DataInputStream(bStream);
				
				int count= input.readInt();
				for (int i= 0 ; i< count ; i++) 
				{
					String key= input.readUTF();
					String value= readString(input);
					result.put( key, value );
				}
				bStream.close();
			} 
			catch (IOException i) 
			{
				//System.out.println("IOException in tp_sql.hashFromStream()");
				//i.printStackTrace();
			}
		}		
		return( result );
	}
	
	
	private static String loadParent(Connection con,String entryID) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map mediaMap = null;
		ConcurrentHashMap librarydata = null;
		String finalvalue = "";
		try{
		  ps = con.prepareStatement(" SELECT titleid, parentid, entrytype, data FROM library WHERE entryid = ? ");
		  ps.setString(1, entryID);
		  rs = ps.executeQuery();
		  if(rs.next()){
			String URL = rs.getString("titleid");
			String parentid =  rs.getString("parentid");
			String entryType = rs.getString("entrytype");
			librarydata =  hashFromStream(rs.getBinaryStream("data"));
			if(!StringUtils.isEmpty(parentid)){
			   String path = loadParent(con,parentid);	
			   //System.out.println(" path " + path + " parentid " + parentid);
			   finalvalue = path + "-->" + finalvalue;	
			}			
			Question q = null;
			Iterator i = librarydata.keySet().iterator();
			boolean valueFound = false;
			while(i.hasNext()){
			  String key = (String)i.next();  	
			  String value = (String)librarydata.get(key);
			  //if(!"tableOfContents".equalsIgnoreCase(key))
			  //System.out.println("Key " + key  + "   Value " + value + " entry type " + entryType);
			  if("author".equalsIgnoreCase(key)){
				 //System.out.println(" Value " + value);  
				 finalvalue = finalvalue + value;
				 valueFound = true;
			  }
			  else if("leafTitle".equalsIgnoreCase(key)){
				 if(value.equalsIgnoreCase("ROOT")){
				   value = "TESTBANK";	 
				 }
				 if(!valueFound){
				   finalvalue = finalvalue + value;
				 }
				 else{
				   finalvalue = finalvalue + "--" + value;	 
				 }
				 //System.out.println("Value " + value + " entry type " + entryType);
			  }
			  if("edition".equalsIgnoreCase(key)){
				finalvalue = finalvalue + "--" + value;  
				//System.out.println(" Value " + value + " entry type " + entryType);  	  
			  }
			}

		  }
		}
		catch(Exception ex){
		  con.rollback();
		  ex.printStackTrace(); 	
		}
		finally{
		   	if(rs != null){
		      rs.close();
		    }
			if(ps != null){
		      ps.close();
			}			
		}
		return finalvalue;
	}	
	
	private static String printPath(Connection con,String testID) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map mediaMap = null;
		ConcurrentHashMap librarydata = null;
		String finalPath = "";
		String entryType = "";
		try{
		  ps = con.prepareStatement(" SELECT entryType, titleid, parentid, data FROM library WHERE testID = ? ");
		  ps.setString(1, testID);
		  //ps = con.prepareStatement(" SELECT entryType, titleid, parentid, data FROM library WHERE entrytype = 1 and titleid = ?");
		  //ps.setString(1, isbn);
		  rs = ps.executeQuery();
		  if(rs.next()){
			String URL = rs.getString("titleid");
			String parentid =  rs.getString("parentid");
			librarydata =  hashFromStream(rs.getBinaryStream("data"));
			entryType = rs.getString("entryType");
			if(!"0".equalsIgnoreCase(entryType)){
			   finalPath = loadParent(con,parentid);	
			}			
			Question q = null;
			Iterator i = librarydata.keySet().iterator(); 
			while(i.hasNext()){
			  String key = (String)i.next();  	
			  String value = (String)librarydata.get(key);
			  if("leafTitle".equalsIgnoreCase(key)){
				finalPath = finalPath + "-->" + value; 
			    //System.out.println("Value " + value );
			  }
			}
		  }
		}
		catch(Exception ex){
		  con.rollback();
		  ex.printStackTrace(); 	
		}
		finally{
		   	if(rs != null){
		      rs.close();
		    }
			if(ps != null){
		      ps.close();
			}			
		}
		return finalPath;
	}
}

