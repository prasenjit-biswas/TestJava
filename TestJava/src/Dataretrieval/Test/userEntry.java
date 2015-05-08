package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;


public class userEntry {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String			id = "undefined ID";
	
	private String			name = "";
	
	private String			email = "";
	
	public userEntry( DataInputStream theInput, int format )
	throws IOException
{

	try {
		
		id= theInput.readUTF();
		name= theInput.readUTF();
		email= theInput.readUTF();

	} catch (IOException e) {
		e.printStackTrace();
		throw (new IOException( "IOException reading userEntry" ) );
	}
	
}
	public void printUserEntry(){
	  	System.out.println("######## printing userEntry ");
	  	System.out.println("id : " + id);
	  	System.out.println("name : " + name);
	  	System.out.println("email : " + email);
	}

}
