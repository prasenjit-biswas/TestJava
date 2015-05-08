package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;


public class tp_manager {

	String		id = "";
	
	String		password = "";
	
	
	boolean		isEnabled = false;

	boolean		editTestPermission = false;
	
	boolean		deleteRecordsPermission = false;
	
	boolean		scorePermission = false;

	boolean		exceptionPermission = false;

	boolean		deleted= false;
	
	public tp_manager( DataInputStream theInput, int format )
	throws IOException
{

	try {
	
		id= theInput.readUTF();
		password= theInput.readUTF();
		
		isEnabled= theInput.readBoolean();
		editTestPermission= theInput.readBoolean();
		deleteRecordsPermission= theInput.readBoolean();
		scorePermission= theInput.readBoolean();
		
		if (format >= 25) exceptionPermission= theInput.readBoolean();
		
	} catch (IOException e) {
		
		throw (new IOException( "IOException reading tp_manager" ) );
		
	}
	
}

}
