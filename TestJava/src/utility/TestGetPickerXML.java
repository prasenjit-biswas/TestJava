package utility;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.mcgrawhill.ezto.integration.classware_hm;
import com.mcgrawhill.ezto.utilities.tp_utils;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import Dataretrieval.util.OracleConnection;

public class TestGetPickerXML {

	public static void main(String[] args) {
		String query = "SELECT name, a.pickerxml.getClobval() pickerxml FROM abc a where name = '13'"; 
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;
        
        try{
           con = OracleConnection.getConnection();
           updateXml(con);	
           pstmt = con.prepareStatement( query );	
           rs = pstmt.executeQuery();
           if(rs.next()){
              String name = rs.getString("name");	
              String pickerxml = rs.getString("pickerxml");
              //System.out.println("name _ pickerXml : "+name + " _ "+pickerxml);
              String selectionTitle =  parseXml(pickerxml);
              System.out.println(" selectionTitle : "+selectionTitle);
              
           }
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}

    public static String parseXml(String pickerXml) throws Exception{
    	String selectionTitle = null;
    	try{
    		Document theXML = (new SAXBuilder()).build(new StringReader(pickerXml));
    		Element picker = theXML.getRootElement();  
   		 	if(picker != null)
   		 	{
	   		 	if( picker.getName() != null && "question".equals( picker.getName() )  )
			    {
			    	if( picker.getChild( "selectionTitle" ) != null)
			    	{
			    		selectionTitle = picker.getChild( "selectionTitle" ).getText() ;
			    	}
			    	
	   		 	}
   		 	}
    	}catch(Exception ex){
    	  throw ex;	
    	}
    	return selectionTitle;
    }


    public static void updateXml(Connection con) throws Exception
    {
    	PreparedStatement pstmt = null;
    	Element results = new Element("question");
    	Element selectionTitleTag = null;
    	Document xmlSnippet = new Document(results);
    	//String  selectionTitle = "Study Question 3-14 ";
    	String  selectionTitle = "Study Question 3-14 < ";
		if(selectionTitle != null && selectionTitle.trim().length() > 0){
			selectionTitleTag = new Element("selectionTitle");
			results.addContent(selectionTitleTag);
			
			//selectionTitleTag.addContent(new CDATA(selectionTitle));
			selectionTitleTag.addContent(selectionTitle);
			//tp_utils.safeCDATA( selectionTitle )
		}
    	
		String pickerXml = classware_hm.getXMLString(xmlSnippet);
		System.out.println(" pickerXml : "+pickerXml);
    	String sqlQuery = "Update abc SET pickerxml = '"+pickerXml+"' WHERE NAME = 13";
		try{
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
		}catch(Exception e){
		   e.printStackTrace();	
		}
		
		
		//System.out.println(selectionTitleTag.getText());
		
    	
    }
}
