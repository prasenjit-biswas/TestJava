package utility;

import java.util.HashMap;

public class CustomHashMap extends HashMap{
 
	public void putElement(String key,String Value,CustomHashMap h)
	{
		if(key != null && key.equalsIgnoreCase("Tapas")){
			String value = (String)h.get(key);
		    h.put("Tapas",Value);
		}else{
			h.put(key,Value);
		}
		
	}
}
