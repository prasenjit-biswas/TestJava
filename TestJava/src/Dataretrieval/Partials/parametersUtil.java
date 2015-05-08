package	Dataretrieval.Partials;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;







/**
* class to maintain a list of name/value pairs for retrieval and use in various manners
*/
public class parametersUtil 
	extends Object
{
	static String	COOKIE_RECORD_SEPARATOR = "&",
					COOKIE_ITEM_SEPARATOR = "$";
					
				/** the list of parameter names */
	Vector		names;
	
				/** the list of values assoicated with the names */
	Vector		values;
				
	
	/**
	* construct an empty parameters object
	*/ 
	public parametersUtil() 
	{		
		super();
		
		names= new Vector();
		
		values= new Vector();
	}

	
	/**
	* construct a new parameters object from a comma separated list of name=value pairs
	* 
	* @param encoded
	* the list
	*/ 
	public parametersUtil( String encoded ) 
	{
		super();
		
		names= new Vector();
		values= new Vector();

		StringTokenizer thePairs= new StringTokenizer( encoded, "," );
		while (thePairs.hasMoreTokens()) {
			StringTokenizer theTokens= new StringTokenizer( thePairs.nextToken(), "=" );
			if (theTokens.countTokens()==2)
				replaceParam( theTokens.nextToken(), tp_utility.substitute( tp_utility.substitute( tp_utility.substitute( theTokens.nextToken(), "$C#", "," ), "~Q#", "'" ), "!E#", "=" ) );
			else
				replaceParam( theTokens.nextToken(), "" );
		}
	}
	

	/**
	* duplicate a parameters object
	* 
	* @param original
	* the original object
	*/ 
	public parametersUtil( parametersUtil original ) 
	{
		super();
		
		names= new Vector();
		
		values= new Vector();
		
		for (int i=0 ; i<original.names.size() ; i++) 
		{
			names.addElement( original.names.elementAt(i) );
			values.addElement( original.values.elementAt(i) );
		}
	}
	
	
	/**
	* read a parameters object from an RandomAccessFile input stream
	* 
	* @param theInput
	* the input stream
	*
	* @throws IOException
	*/ 
	public parametersUtil( RandomAccessFile theInput ) 
		throws IOException
	{
		names= new Vector();
		
		values= new Vector();

		// read the names
		int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++)
			names.addElement( theInput.readUTF() );
		
		
		// read the values
		int valueCount= theInput.readInt();
		for (int i=0 ; i<valueCount ; i++)
			values.addElement( theInput.readUTF() );
		
		// read additional subclasses here
	}


	/**
	* read a parameters object from a input stream
	* 
	* @param theInput
	* the input stream
	*
	* @throws IOException
	*/ 
	public parametersUtil( DataInputStream theInput ) 
		throws IOException
	{
		names= new Vector();
		
		values= new Vector();

		// read the names
		int nameCount= theInput.readInt();
		for (int i=0 ; i<nameCount ; i++)
			names.addElement( theInput.readUTF() );
		
		
		// read the values
		int valueCount= theInput.readInt();
		for (int i=0 ; i<valueCount ; i++)
			values.addElement( tp_utility.readString(theInput) );
				
		// read additional subclasses here		
	}


	/**
	* construct a new parameters object from a cookie
	* 
	* @param theCookie
	* the cookie
	*/ 
	public static parametersUtil cookieParams( String theCookie ) 
	{
		parametersUtil result= new parametersUtil();
		
		//System.out.println( "the cookie: " + theCookie );
		
		StringTokenizer theItems= new StringTokenizer( theCookie, COOKIE_RECORD_SEPARATOR );
		while (theItems.hasMoreTokens()) 
		{
			String thePair= theItems.nextToken();
			int index= thePair.indexOf(COOKIE_ITEM_SEPARATOR);
			if (index > 0) 
			{
				result.replaceParam(thePair.substring(0,index), decodeCookieValue(thePair.substring(index+1)));
				//System.out.println( "  " + thePair.substring(0,index) + "=" + decodeCookieValue(thePair.substring(index+1)) );
			}
		}
		
		return(result);
	}


	/**
	* write a parameters object to a RandomAccessFile output stream
	* 
	* @param out
	* the output stream
	*
	* @throws IOException
	*/ 
	public void write( RandomAccessFile out ) 
		throws IOException
	{
		// write the names
		out.writeInt( names.size() );
		for (int i=0 ; i< names.size() ; i++) 
		{
			String val= (String)names.elementAt(i);
			if (val == null) val= "";
			out.writeUTF( val );
		}
		
		
		// write the values
		out.writeInt( values.size() );
		for (int i=0 ; i< values.size() ; i++) 
		{
			String val= (String)values.elementAt(i);
			if (val == null) val= "";
			out.writeUTF( val );
		}
		
		// write additional subclasses here
	}


	/**
	* write a parameters object to an output stream
	* 
	* @param out
	* the output stream
	*
	* @throws IOException
	*/ 
	public void write( DataOutputStream out ) 
		throws IOException
	{
		
		out.writeInt( names.size() );
		for (int i=0 ; i< names.size() ; i++) 
		{
			String val= (String)names.elementAt(i);
			if (val == null) val= "";
			out.writeUTF( val );
		}
		
		// write the values
		out.writeInt( values.size() );
		for (int i=0 ; i< values.size() ; i++) 
		{
			String val= (String)values.elementAt(i);
			if (val == null) val= "";
			tp_utility.writeString( val, out );
		}
				
		// write additional subclasses here
	}


	/**
	* add a name/value pair to this parameters object replacing the value of any existing
	* item with the same name if necessary
	* 
	* @param name
	* the name of the parameter
	*
	* @param value
	* the value of the parameter
	*/ 
	public void addParam( String name, String value ) {
		
		if (name.startsWith("EDITMultiple")) {
			boolean exists= false;
			for (int i=0 ; i<names.size() ; i++) {
				String theName= (String)names.elementAt(i);
				
				if ( theName.toLowerCase().equals( name.toLowerCase() ) ) {
					exists= true;
					values.setElementAt( (String)values.elementAt(i) + " " + value, i );
				}
			}
			
			if (!exists) {
				values.addElement( value );
				names.addElement( name );
			}
		}
		else {
			values.addElement( value );
			names.addElement( name );
		}
			
	}
	
	
	/**
	* return the number of key/value pairs in this object
	*/ 
	public int size() {
		
		return( names.size() );
			
	}
	
	
	/**
	* return the value of the named parameter, empty string if it does not exist.
	* 
	* @param paramName
	* the name of the parameter whose value should be returned
	*
	* @return String containing the value of the named parameter.
	*/ 
	public String getParam( String paramName ) 
	{
		String editParam= "";
		
		for (int i=0 ; i<names.size() ; i++) 
		{
			String name= (String)names.elementAt(i);
			
			if ( name.toLowerCase().equals( paramName.toLowerCase() ) )
				return( (String)values.elementAt(i) );
		}
		
		return( editParam );
	}
	
	public String getParam( String paramName, String defaultParam ) 
	{
		for (int i=0 ; i<names.size() ; i++) 
		{
			String name= (String)names.elementAt(i);
			
			if ( name.toLowerCase().equals( paramName.toLowerCase() ) )
				return( (String)values.elementAt(i) );
		}
		
		return( defaultParam );
	}

	
	public String getUTF8Param( String paramName ) 
	{
		return( tp_utility.convertFromUTF8( getParam(paramName) ) );	
	}

	/**
	* return the value of the named parameter, null if it does not exist.
	* 
	* @param paramName
	* the name of the parameter whose value should be returned
	*/ 
	public String getParamNull( String paramName ) {
		
		for (int i=0 ; i<names.size() ; i++) {
			String name= (String)names.elementAt(i);
			
			if ( name.toLowerCase().equals( paramName.toLowerCase() ) )
				return( (String)values.elementAt(i) );
		}
		
		return( null );
		
	}
	
	
	/**
	* replace the value of the named parameter, are add it if it does not exist.
	* 
	* @param paramName
	* the name of the parameter whose value should be changed
	* 
	* @param newvalue
	* the new alue of the parameter
	*/ 
	public void replaceParam( String paramName, String newvalue ) {
		
		String editParam= "";
		
		for (int i=0 ; i<names.size() ; i++) {
			String name= (String)names.elementAt(i);
			
			if ( name.toLowerCase().equals( paramName.toLowerCase() ) ) {
				values.setElementAt(newvalue, i);
				return;
			}
		}
		
		addParam( paramName, newvalue );
	}
	
	
	/**
	* remove a named parameter from this object.
	* 
	* @param paramName
	* the name of the parameter whose name and value should be removed
	*/ 
	public void removeParam( String paramName ) {
		
		for (int i=0 ; i<names.size() ; i++) {
			String name= (String)names.elementAt(i);
			
			if ( name.toLowerCase().equals( paramName.toLowerCase() ) ) {
				names.removeElementAt(i);
				values.removeElementAt(i);
				return;
			}
		}
		
	}
	
	
	/**
	* set the parameter names REQUEST to a new value.
	* 
	* @param newValue
	* the new value
	*/ 


	/**
	* return the value of a parameter by index.
	* 
	* @param theIndex
	* the index into the values Vector of the parameter
	*/ 
	public String valueAt( int theIndex ) {
	
		if ((theIndex >=0) && (theIndex < values.size()))
			return( (String)values.elementAt( theIndex ) );
			
		return( "" );
	}
	
	
	/**
	* return the name of a parameter by index.
	* 
	* @param theIndex
	* the index into the names Vector of the parameter
	*/ 
	public String idAt( int theIndex ) {
	
		if ((theIndex >=0) && (theIndex < names.size()))
			return( (String)names.elementAt( theIndex ) );
			
		return( "" );
	}
	
	
	/**
	* return a comma separated list of parameter names.
	*/ 
	public String idList() {
	
		String		result= "";
		
		for (int i=0 ; i<names.size() ; i++) {
		
			result += (String)names.elementAt(i);
		
			if ( (i+1) != names.size() ) result += ",";
			
		}
		
		return( result );
	}
	
	
	/**
	* return an array of parameter names.
	*/ 
	public String [] names() 
	{
		String [] result;
		
		result= new String[names.size()];
		
		for (int i=0 ; i<names.size() ; i++)		
			result[i]= (String)names.elementAt(i);
		
		return( result );
	}
	
	
	/**
	* debug routine for viewing the contents of this object.
	*/ 
	public void debugDump() 
	{
		
		// write the fields
		for (int i=0 ; i< names.size() ; i++)
			System.out.println( (String)names.elementAt(i) + " = " + (String)values.elementAt(i) );
		
	}
	

	/**
	* routine for sending the contents of parameters from this object whose names begin with "Q".
	* Send them as HTML HIDDEN fields to an output stream. This is used in "build a test" for
	* question selecion persistance.
	*
	* @param theHandler
	* the request Handler
	*/ 

	

	/*
	public void sendLibraryParams( tp_requestHandler theHandler ) 
	{
		// write the fields
		for (int i=0 ; i< names.size() ; i++) {
			String theName= (String)names.elementAt(i);
			if (theName.startsWith("Q_"))
				theHandler.snd( "\r<INPUT TYPE=\"HIDDEN\" NAME=\"" + theName + "\" VALUE=\"" + (String)values.elementAt(i) + "\">\r" );
			if (theName.startsWith("R_"))
				theHandler.snd( "\r<INPUT TYPE=\"HIDDEN\" NAME=\"" + theName + "\" VALUE=\"" + (String)values.elementAt(i) + "\">\r" );
		}
	}
	*/

	public String getSelectParams() 
	{
		String result= "";
		
		for (int i=0 ; i< names.size() ; i++) {
			String theName= (String)names.elementAt(i);
			if (theName.startsWith("O")) {
			//if (theName.startsWith("O") || theName.equals(test.PICK_DIRECTORY) || theName.equals("fullQ") || theName.equals("arrangement")) {
				if (result.length() > 0) result += "&";
				result += theName + "#" + (String)values.elementAt(i);
				//System.out.println("built " + theName + " with " + (String)values.elementAt(i));
			}
		}
		
		return(result);
	}
	


	public void dropSelectParams() 
	{
		Vector paramsToDrop= new Vector();
		
		for (int i=0 ; i< names.size() ; i++) 
		{
			String theName= (String)names.elementAt(i);
			if (theName.startsWith("O"))
				paramsToDrop.addElement( theName );
		}

		for (int i=0 ; i< paramsToDrop.size() ; i++) 
		{
			String theName= (String)paramsToDrop.elementAt(i);
			removeParam( theName );
		}
	}



	/**
	* return true if there are local random variables defined in this parameter list
	*/ 
	public boolean hasLocalRandoms() 
	{
		// write the fields
		for (int i=0 ; i< names.size() ; i++) {
			String theName= (String)names.elementAt(i);
			if (theName.endsWith("_rnd")) {
				if (getParam(theName).length() > 0) return(true);
			}
		}
		
		return(false);
	}
	

	/**
	* routine for sending the contents of this object as HTML HIDDEN fields to an output stream.
	* This is typically used in assessments offered a question at a time or a group at a time in
	* order to include the responses to previously answered questions in succeeding pages of a
	* multipage assessment.
	* 
	* @param out
	* ServletOutputStream where the hidden form field HTML is written.
	*
	* @throws IOException
	*/ 
	
	
	
	
	
	public String allHiddenFields() 
	{
		String result= "";
		
		for (int i=0 ; i< names.size() ; i++)
			result += "<INPUT TYPE=\"HIDDEN\" NAME=\"" + (String)names.elementAt(i) + "\" VALUE=\"" + (String)values.elementAt(i) + "\">";
		
		return( result );
	}


	public String allHiddenFieldsNoOutput() 
	{
		String result= "";
		
		for (int i=0 ; i< names.size() ; i++)
		{
			if (((String)names.elementAt(i)).endsWith("_output")) continue;
			result += "<INPUT TYPE=\"HIDDEN\" NAME=\"" + (String)names.elementAt(i) + "\" VALUE=\"" + (String)values.elementAt(i) + "\">";
		}
		
		return( result );
	}



	/**
	* Sends the contents of this object as HTML HIDDEN fields to an output stream.
	* This is typically used in assessments offered a question at a time or a group at a time in
	* order to include the responses to previously answered questions in succeeding pages of a
	* multipage assessment.  This routine ONLY outputs local random variable parameters.
	* 
	* @param theOutput
	* the output {@link parametersUtil} object to be populated with local random variables for later streaming.
	*/ 
	public void putLocals( parametersUtil theOutput ) 
	{
		for (int i=0 ; i< names.size() ; i++) {
			String theName= (String)names.elementAt(i);
			if (theName.endsWith("_rnd"))
				theOutput.replaceParam( theName, (String)values.elementAt(i) );
		}
	}
	
	
	
	
	/**
	* generate a String for output as a cookie that represents all of the name/value pairs of this object.
	*/ 
	public String cookieFullEnumeration() 
	{
		String result= "";
		
		for (int i=0 ; i< names.size() ; i++) {
			String theName= (String)names.elementAt(i);
			result += theName + COOKIE_ITEM_SEPARATOR + encodeCookieValue((String)values.elementAt(i)) + COOKIE_RECORD_SEPARATOR;
		}
		
		return( result );
	}
		
	
	/**
	* generate a string that is in legal cookie format from the original string that may contain
	* illegal characters.
	*/
	private static String encodeCookieValue( String original )
	{
		if (original.length() == 0) return( "!^E" );
		
		String result= tp_utility.substitute( original, "\t", "!^t" );
		result= tp_utility.substitute( result, "\r", "!^R");
		result= tp_utility.substitute( result, "\n", "!^N");
		result= tp_utility.substitute( result, ",", "!^c");
		result= tp_utility.substitute( result, ";", "!^s" );
		result= tp_utility.substitute( result, "[", "!^B" );
		result= tp_utility.substitute( result, "]", "!^b" );
		result= tp_utility.substitute( result, "(", "!^P" );
		result= tp_utility.substitute( result, ")", "!^p" );
		result= tp_utility.substitute( result, "=", "!^e" );
		result= tp_utility.substitute( result, "\"", "!^q" );
		result= tp_utility.substitute( result, "/", "!^h" );
		result= tp_utility.substitute( result, "?", "!^Q" );
		result= tp_utility.substitute( result, "@", "!^a" );
		result= tp_utility.substitute( result, ":", "!^n" );
		result= tp_utility.substitute( result, " ", "_" );
		
		return( result );
	}
	

	/**
	* retrieve a string that that may contain illegal characters from a cookie
	* that has been previously encoded with encodeCookieValue().
	*/
	private static String decodeCookieValue( String cookieValue )
	{
		if (cookieValue.equals("!^E")) return("");
		
		String result= tp_utility.substitute( cookieValue, "\t", "!^t" );
		result= tp_utility.substitute( result, "!^R", "\r");
		result= tp_utility.substitute( result, "!^N", "\n");
		result= tp_utility.substitute( result, "!^c", ",");
		result= tp_utility.substitute( result, "!^s", ";");
		result= tp_utility.substitute( result, "!^B", "[");
		result= tp_utility.substitute( result, "!^b", "]");
		result= tp_utility.substitute( result, "!^P", "(");
		result= tp_utility.substitute( result, "!^p", ")");
		result= tp_utility.substitute( result, "!^e", "=");
		result= tp_utility.substitute( result, "!^q", "\"");
		result= tp_utility.substitute( result, "!^h", "/");
		result= tp_utility.substitute( result, "!^Q", "?");
		result= tp_utility.substitute( result, "!^a", "@");
		result= tp_utility.substitute( result, "!^n", ":");
		result= tp_utility.substitute( result, "_", " ");

		return( result );
	}



	/**
	* yet another debug routine for viewing the contents of this object.
	*/ 
	
	
	
	/**
	* yet another debug routine for viewing the contents of this object.
	*/ 
	public void dump() {
		
		for (int i=0 ; i<names.size() ; i++)
			System.out.println( "    " + (String)names.elementAt(i) + " = " + getParam((String)names.elementAt(i)) );
			
		System.out.println( " " );

	}
	
	
	
	/**
	* yet another debug routine for viewing the contents of this object.
	*/ 
	public String pageDump() {
		// dump the contents as HTML
		
		String result= "<PRE>\r";
		for (int i=0 ; i<names.size() ; i++)
			result += "    " + (String)names.elementAt(i) + " = " + getParam((String)names.elementAt(i)) + "\r";
		result += "</PRE><P>";
		
		return(result);
		
	}


	/**
	* return a comma separated list of name=value pairs from this object.
	*/ 
	public String toString() 
	{
		String result= "";
		
		for (int i=0 ; i< names.size() ; i++) {
			result += (String)names.elementAt(i) + "=";
			result += tp_utility.substitute( tp_utility.substitute( tp_utility.substitute( (String)values.elementAt(i), "=", "!E#" ), "'", "~Q#" ), ",", "$C#" );
			if ((i+1) < names.size()) result += ",";
		}
		
		return(result);
	}
	

	private static String shownName( String longName )
	{
		StringTokenizer theTokens= new StringTokenizer(longName, "_");
		if (theTokens.countTokens() >= 2)
		{
			String result= theTokens.nextToken();
			result += "_" + theTokens.nextToken() + "_shown";
			return result;
		}
		return "";
	}

	public void copyInfoParams( parametersUtil source )
	{
		for (int i=0 ; i< source.names.size() ; i++) 
		{
			String theName= (String)source.names.elementAt(i);
			if (theName.startsWith("_"))
				replaceParam( "_START" + theName, source.getParam(theName) );
		}
	}


	public ConcurrentHashMap hash() 
	{
		ConcurrentHashMap result= new ConcurrentHashMap();
		
		for (int i=0 ; i< names.size() ; i++) 
			result.put( (String)names.elementAt(i), (String)values.elementAt(i) );
			
		return( result );
	}
	
	
	/**
	used in classware_hm.duplicateSubmissions to change question ID's whereever they may lie in a submissions variables
	yes, this is dangerous and ugly
	*/
	public void updateIDs( ConcurrentHashMap questionMap )
	{
		Enumeration originalIDs= questionMap.keys();
		while (originalIDs.hasMoreElements())
		{
			String originalID= (String)originalIDs.nextElement();
			String duplicateID= (String)questionMap.get(originalID);
			if (duplicateID == null) continue;
		
			for (int i=0 ; i<names.size() ; i++)
			{
				String theName= (String)names.elementAt(i);
				String theValue= (String)values.elementAt(i);
				
				theName= tp_utility.substitute( theName, originalID, duplicateID );
				theValue= tp_utility.substitute( theValue, originalID, duplicateID );
				
				names.setElementAt( theName, i );
				values.setElementAt( theValue, i );
			}
		}
	}

}	
