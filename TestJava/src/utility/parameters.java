package utility;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

/**
* class to maintain a list of name/value pairs for retrieval and use in various manners
*/
public class parameters extends Object
{
	final static String ENCRYPTION_PREFIX = "_Cr_";
	
	static String	COOKIE_RECORD_SEPARATOR = "&",
					COOKIE_ITEM_SEPARATOR = "$";
					
				/** the list of parameter names */
	Vector		names;
	
				/** the list of values assoicated with the names */
	Vector		values;
				
	
	/**
	* construct an empty parameters object
	*/ 
	public parameters() 
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
	
	

	/**
	* duplicate a parameters object
	* 
	* @param original
	* the original object
	*/ 
	public parameters( parameters original ) 
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
	public parameters( RandomAccessFile theInput ) 
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
	* return the number of key/value pairs in this object
	*/ 
	public int size() {
		
		return( names.size() );
			
	}

	/**
	* read a parameters object from a input stream
	* 
	* @param theInput
	* the input stream
	*
	* @throws IOException
	*/ 
	public parameters( DataInputStream theInput ) 
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
			values.addElement( readString(theInput) );
				
		// read additional subclasses here		
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
			writeString( val, out );
		}
				
		// write additional subclasses here
	}
	
	public static void writeString( String theString, DataOutputStream out )
		throws IOException
	{
		if (theString.length() > 65535) 
		{
			//System.out.println("outputting " + LONG_STRING);
			out.writeUTF( LONG_STRING );
			byte[] barray= theString.getBytes("UTF-8");
			out.writeInt( barray.length );
			out.write( barray, 0, barray.length);
		}
		else
			out.writeUTF( theString );
	}


	

}	
