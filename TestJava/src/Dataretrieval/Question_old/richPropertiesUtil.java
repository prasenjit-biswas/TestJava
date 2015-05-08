package	Dataretrieval.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class richPropertiesUtil
{
	static String		PROPERTY_TYPE_string		= "string";
	static String		PROPERTY_TYPE_integer		= "integer";
	static String		PROPERTY_TYPE_long			= "long";
	static String		PROPERTY_TYPE_double		= "double";
	static String		PROPERTY_TYPE_boolean		= "boolean";
	static String		PROPERTY_TYPE_html			= "html";
	static String		PROPERTY_TYPE_url			= "url";


	private String		theTitle = "untitled";
	public String getTitle() {
		return theTitle;
	}


	public void setTitle(String theTitle) {
		this.theTitle = theTitle;
	}


	private HashMap	theList = null;
	
	
	public HashMap getList() {
		return theList;
	}


	public void setList(HashMap theList) {
		this.theList = theList;
	}


	private richPropertiesUtil()
	{
		theList= new HashMap();
	}


	public richPropertiesUtil( String theXML )
	{
		theList= new HashMap();
		
		//System.out.println("");
		//System.out.println("richProperties");
		//System.out.println(">>> " + theXML);
		
		try
		{
			//System.out.println("richProperties XML : " + theXML);
			SAXBuilder builder = new SAXBuilder();
			Document theDoc = builder.build(new ByteArrayInputStream(theXML.getBytes()));
			Element theProps= theDoc.getRootElement();
			
			theTitle= theProps.getName();
			
			java.util.List listOprops= theProps.getChildren();
			ListIterator iter= listOprops.listIterator();
			while (iter.hasNext()) 
			{
				Element thisChild= (Element)iter.next();
				String thisName= thisChild.getName();
				
				if (thisName.equals("property"))
				{
					String theName= thisChild.getAttributeValue("name");
					String theType= thisChild.getAttributeValue("type");
					String theValue= thisChild.getAttributeValue("value");
					
					//System.out.println("name: " + theName + ", type: " + theType + ", value: " + theValue);
					
					if (theName.length() > 0)
					{
						try
						{
							prop theProp= new prop( theType, theValue );
							theList.put( theName, theProp );
						}
						catch (Exception ignore)
						{
							System.out.println("Bad richProperties type: " + theType);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception reading properties XML");
			e.printStackTrace();
		}
	}
	
	
	public richPropertiesUtil( Element theProps, String title )
	{
		theList= new HashMap();
		theTitle= title;
		
		try
		{
			java.util.List listOprops= theProps.getChildren();
			ListIterator iter= listOprops.listIterator();
			while (iter.hasNext()) 
			{
				Element thisChild= (Element)iter.next();
				String thisName= thisChild.getName();
				
				if (thisName.equals("property"))
				{
					String theName= thisChild.getAttributeValue("name");
					String theType= thisChild.getAttributeValue("type");
					String theValue= thisChild.getAttributeValue("value");
					
					//System.out.println("name: " + theName + ", type: " + theType + ", value: " + theValue);
					
					if (theName.length() > 0)
					{
						try
						{
							prop theProp= new prop( theType, theValue );
							theList.put( theName, theProp );
						}
						catch (Exception ignore)
						{
							System.out.println("Bad richProperties type: " + theType);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception reading properties XML");
			e.printStackTrace();
		}
	}
	
	
	public String toXML()
	{
		Element theProps= new Element(theTitle);
		Document theDoc= new Document(theProps);
		
		//Enumeration props= theList.keys();
		//while (props.hasMoreElements())
		Iterator iter= theList.keySet().iterator();
		while (iter.hasNext())
		{
			//String theName= (String)props.nextElement();
			String theName= (String)iter.next();
			prop theProp= (prop)theList.get(theName);
			
			Element thisProp= new Element("property");
			theProps.addContent( thisProp );
			
			thisProp.setAttribute("name", theName);
			thisProp.setAttribute("type", theProp.getType());
			thisProp.setAttribute("value", theProp.getData());
		}

		ByteArrayOutputStream out= new ByteArrayOutputStream();
		try
		{
			XMLOutputter outp = new XMLOutputter();
			outp.setTextTrim(true);
			outp.setIndent("  ");
			outp.setNewlines(true);
			outp.output(theDoc, out);
		}
		catch (IOException ignore) {}
		
		String result= new String(out.toByteArray());
		//System.out.println("");
		//System.out.println("richProperties.toXML:");
		//System.out.println(result);
		
		return(result);
	}
	
	
	public boolean compare( richPropertiesUtil otherProps )
	{	
		if (theList.size() != otherProps.theList.size()) { System.out.println("richProperties size mismatch"); return false; }
		
		/*
		Enumeration props= theList.keySet();
		while (props.hasMoreElements())
		{
			String theName= (String)props.nextElement();
		*/
		Iterator iter= theList.keySet().iterator();
		while (iter.hasNext())
		{
			String theName= (String)iter.next();
			prop theProp= (prop)theList.get(theName);
			
			prop oProp= (prop)otherProps.theList.get(theName);
			if (oProp == null) { System.out.println("richProperties missing property"); return false; }
			
			if (!theProp.getType().equals(oProp.getType())) { System.out.println("richProperties type mismatch"); return false; }
			if (!theProp.getData().equals(oProp.getData())) { System.out.println("richProperties data mismatch"); return false; }
		}

		return true;
	}
	
	
	public void buildExportXML( Element theProps )
	{
		/*
		Enumeration props= theList.keys();
		while (props.hasMoreElements())
		{
			String theName= (String)props.nextElement();
		*/
		Iterator iter= theList.keySet().iterator();
		while (iter.hasNext())
		{
			String theName= (String)iter.next();
			prop theProp= (prop)theList.get(theName);
			
			Element thisProp= new Element("property");
			theProps.addContent( thisProp );
			
			thisProp.setAttribute("name", theName);
			thisProp.setAttribute("type", theProp.getType());
			thisProp.setAttribute("value", theProp.getData());
		}
	}
	
	
	public static richPropertiesUtil duplicate( richPropertiesUtil theOriginal )
	{
		return( new richPropertiesUtil( theOriginal.toXML() ) );
	}
	
	
	public static richPropertiesUtil newInstance( String title )
	{
		richPropertiesUtil result= new richPropertiesUtil();
		result.theTitle= title;
		return( result );
	}
	
	
	public void clear()
	{
		theList= new HashMap();
	}
	
	
	public boolean hasProperty( String name )
	{
		return( theList.get(name) != null );
	}
	
	
	public String propertyType( String name )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null) return(theProp.getType());
		return(null);
	}
	
	
	public void dropProperty( String name )
	{
		theList.remove(name);
	}
	
	
	public void setInt( String name, int data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_integer, Integer.toString(data)) );
	}
	
	public int getInt( String name, int theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null)
		{
			if (!theProp.getType().equals(PROPERTY_TYPE_integer)) return(theDefault);
			try
			{
				return( Integer.parseInt(theProp.getData()) );
			}
			catch (NumberFormatException n) {}
		}
		return(theDefault);
	}
	
	
	
	public void setDouble( String name, double data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_double, Double.toString(data)) );
	}
	
	public double getDouble( String name, double theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null)
		{
			if (!theProp.getType().equals(PROPERTY_TYPE_double)) return(theDefault);
			try
			{
				return( Double.parseDouble(theProp.getData()) );
			}
			catch (NumberFormatException n) {}
		}
		return(theDefault);
	}
	

	
	public void setLong( String name, long data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_long, Long.toString(data)) );
	}
	
	public long getLong( String name, long theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null)
		{
			if (!theProp.getType().equals(PROPERTY_TYPE_long)) return(theDefault);
			try
			{
				return( Long.parseLong(theProp.getData()) );
			}
			catch (NumberFormatException n) {}
		}
		return(theDefault);
	}
	
	
	public void setString( String name, String data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_string, data) );
	}
	
	public String getString( String name, String theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null) return( theProp.getData() );
		return(theDefault);
	}
	
	
	public void setHTML( String name, String data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_html, data) );
	}
	
	public String getHTML( String name, String theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null) return( theProp.getData() );
		return(theDefault);
	}
	
	
	public void setURL( String name, String data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_url, data) );
	}
	
	public String getURL( String name, String theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null) return( theProp.getData() );
		return(theDefault);
	}
	
	
	public void setBoolean( String name, boolean data )
	{
		theList.remove( name );
		theList.put( name, new prop(PROPERTY_TYPE_boolean, (data ? "true" : "false")) );
	}
	
	public boolean getBoolean( String name, boolean theDefault )
	{
		prop theProp= (prop)theList.get( name );
		if (theProp != null)
		{
			if (!theProp.getType().equals(PROPERTY_TYPE_boolean)) return(theDefault);
			return( theProp.getData().equals("true") );
		}
		return(theDefault);
	}
	
	
	private class prop
	{
		private String theType= PROPERTY_TYPE_string;
		private String theData= "";
		
		public prop( String type, String data )
		{
			boolean validType= false;
			
			if (type.equals(PROPERTY_TYPE_string)) validType= true;
			if (type.equals(PROPERTY_TYPE_double)) validType= true;
			if (type.equals(PROPERTY_TYPE_integer)) validType= true;
			if (type.equals(PROPERTY_TYPE_long)) validType= true;
			if (type.equals(PROPERTY_TYPE_boolean)) validType= true;
			if (type.equals(PROPERTY_TYPE_html)) validType= true;
			if (type.equals(PROPERTY_TYPE_url)) validType= true;
			
			if (validType) theType= type;
			theData= data;
		}
		
		public String getType()
		{
			return( theType );
		}
		
		public String getData()
		{
			return( theData );
		}
	}
}