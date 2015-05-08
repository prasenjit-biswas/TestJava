package utility;

import com.mcgrawhill.ezto.utilities.Crypt;
import com.mcgrawhill.ezto.utilities.tp_utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestBasicEncoding {
	public static void main(String[] args) throws Exception {
		//String authHeader = "Basic eWVzOmtoYXRydTcy";
		String admin = "badPenny";//"lfo2AugErMe0ivEipOtlvA==";
		String adminid = "wmd";//"/cqtJEsYIGY=";
		
		System.out.println("admin : "+Crypt.decrypt(admin));
		System.out.println("adminid : "+Crypt.decrypt(adminid));		
		/*String usernpass= tp_utils.b64decode(authHeader.substring(6));
		
		int colonIndex= usernpass.indexOf(":");
		if (colonIndex >= 0) {
		
			String uname=usernpass.substring(0,colonIndex);
			String password=usernpass.substring(colonIndex+1);
			
			System.out.println(" uname : "+uname);
			System.out.println(" password : "+password);
		}*/
	}
}

