package	utility;

/* Test Pilot sortedList class
*/

import java.util.Enumeration;
import java.util.Vector;


/**
* class to use to dynamically build sorted lists for various uses.
*/
public class tp_sortedListUtil
{
				/** the ordered list of keys */
	public VectorAdapter		keys = null;
	
				/** the ordered list of values assoicated with the keys */
	public VectorAdapter		values = null;
				
				/** true if case should be ignored in comparisons */
	boolean		ignoreCase= false;


	/**
	* main constructor
	*/
	public tp_sortedListUtil() {
	
		keys= new VectorAdapter();
		values = new VectorAdapter();

	}
	
	
	/**
	* alternate constructor with parameter whose value indicates whether case should be ignored
	* on string comparisons.
	*/
	public tp_sortedListUtil( boolean ignore ) 
	{
		keys= new VectorAdapter();
		values = new VectorAdapter();
		
		ignoreCase= ignore;
	}
	
	
	/**
	* add another key/value pair to the list.
	*
	* @param newKey
	* key string
	* 
	* @param newValue
	* associated object
	*/
	public void add( String newKey, Object newValue ) {
	
		if (ignoreCase) newKey= newKey.toLowerCase();
	
		for (int i=0 ; i<keys.size() ; i++) {
			String oldKey= (String)keys.elementAt(i);
			if (oldKey.compareTo(newKey) > 0) {
				keys.insertElementAt( newKey, i );
				values.insertElementAt( newValue, i );
				return;
			}
		}
		
		keys.addElement( newKey );
		values.addElement( newValue );
	
	}
	
	
	/**
	* add another key/value pair to the list.
	*
	* @param newKey
	* String to use as both key and value.
	*/
	public void add( String newKey ) {
	
		if (ignoreCase) newKey= newKey.toLowerCase();
	
		for (int i=0 ; i<keys.size() ; i++) {
			String oldKey= (String)keys.elementAt(i);
			if (oldKey.compareTo(newKey) > 0) {
				keys.insertElementAt( newKey, i );
				values.insertElementAt( newKey, i );
				return;
			}
		}
		
		keys.addElement( newKey );
		values.addElement( newKey );
	
	}
	
	
	/**
	* add another key/value pair to the list.
	*
	* @param newKey
	* Integer key
	* 
	* @param newValue
	* New Object to be associated with the given key.
	*/
	public void add( int newKey, Object newValue ) {
	
		for (int i=0 ; i<keys.size() ; i++) {
			int oldKey= ((Integer)keys.elementAt(i)).intValue();
			if (newKey < oldKey) {
				keys.insertElementAt( new Integer(newKey), i );
				values.insertElementAt( newValue, i );
				return;
			}
		}
		
		keys.addElement( new Integer(newKey) );
		values.addElement( newValue );
	
	}
	
	
	/**
	* add another key/value pair to the list.
	*
	* @param newKeys
	* Vector of keys for sorting on multiple string keys
	* 
	* @param newValue
	* New Object to be associated with the given keys
	*/
	public void add( Vector newKeys, Object newValue ) {
	
		for (int i=0 ; i<keys.size() ; i++) {
			Vector oldKeys= (Vector)keys.elementAt(i);
			if (compare(oldKeys, newKeys, 0) > 0) {
				keys.insertElementAt( newKeys, i );
				values.insertElementAt( newValue, i );
				return;
			}
		}
		
		keys.addElement( newKeys );
		values.addElement( newValue );
	
	}
	
	
	/**
	* get value by key from the list.
	*
	* @param key
	* key string
	* 
	* @return Object associated with the given key.
	*/
	public Object get( String key ) {

		String compareKey= key;
		if (ignoreCase) compareKey= key.toLowerCase();
	
		for (int i=0 ; i<keys.size() ; i++) {
			String oldKey= (String)keys.elementAt(i);
			if (oldKey.compareTo(compareKey) == 0)
				return( values.elementAt(i) );
		}
		
		return(null);
		
	}
	
	
	/**
	* remove value by key from the list.
	* 
	* @param key
	* key string
	*/
	public void remove( String key ) 
	{
		String compareKey= key;
		if (ignoreCase) compareKey= key.toLowerCase();
	
		for (int i=0 ; i<keys.size() ; i++) 
		{
			String oldKey= (String)keys.elementAt(i);
			if (oldKey.compareTo(compareKey) == 0)
			{
				keys.removeElementAt(i);
				values.removeElementAt(i);
			}
		}
	}
	
	
	/**
	* compare two entries having Vectors of keys for sorting on multiple keys.
	* Return -1, 0, or 1 based on whether oldKeys are less than, equal or greater
	* than newKeys respectively.  This method is recursive.
	* @param oldKeys
	* Vector of string keys
	* 
	* @param newKeys
	* Vector of string keys
	* 
	* @param start
	* Integer index of the element at which to begin comparisons
	*
	* @return Integer value -1, 0, or 1 indicating less than, equality, or greater than, respectively.
	*/
	private int compare( Vector oldKeys, Vector newKeys, int start )
	{
		if (start >= oldKeys.size()) return(-1);
		if (start >= newKeys.size()) return(-1);
		
		String oldString= (String)oldKeys.elementAt(start);
		String newString= (String)newKeys.elementAt(start);
		
		int result= oldString.compareTo(newString);
		if (result == 0) {
			if ((start+1) == oldKeys.size())
				return(0);
			else
				return(compare(oldKeys, newKeys, ++start));
		}
		
		return(result);
	}
	
	
	/**
	* return a a count of stored values.
	*/
	public int size() {
	
		return( values.size() );
		
	}
	
	/**
	* return a sorted Enumeration of the stored values.
	*/
	public Enumeration sortedValues() {
	
		return( values.elements() );
		
	}

	
	/**
	* return a sorted Vector of the stored values.
	*/
	public VectorAdapter sortedVector() {
	
		return( values );
		
	}

	
	/**
	* return a sorted Enumeration of the stored keys.
	*/
	public Enumeration keys() {
	
		return( keys.elements() );
		
	}
	
	/**
	* return a sorted Vector of the stored keys.
	*/
	public VectorAdapter keyVector() {
	
		return( keys );
		
	}
	
}