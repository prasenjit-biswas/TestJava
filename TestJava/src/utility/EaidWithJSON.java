package utility;

import com.mcgrawhill.ezto.utilities.Crypt;

public class EaidWithJSON {

	/*public static String getEAIDJson(String eaid) throws Exception{
		String encryptedEaid = Crypt.encrypt(eaid);
		System.out.println(" encryptedEaid : "+encryptedEaid);
		String urlEncryptedEaid = URLEncoder.encode(encryptedEaid,"utf-8");
		return urlEncryptedEaid;
	} 
	
	public static String decryptEAID(String urlEncryptedEaid) throws Exception{
		String encyptedEaid = URLDecoder.decode(urlEncryptedEaid,"utf-8");
		System.out.println(" decodeEAID : "+encyptedEaid);
		return Crypt.decrypt(encyptedEaid);
	}*/
	
	public static String getEAIDJson(String eaid) throws Exception{
		return Crypt.encrypt(eaid);
	} 
	
	public static String decryptEAID(String urlEncryptedEaid) throws Exception{
		return Crypt.decrypt(urlEncryptedEaid);
	}
	
	public static void main(String[] args) {
		/*List<String> eaidList = new ArrayList<String>();
		eaidList.add("1234567890123");
		eaidList.add("1234567890123");
		eaidList.add("1234567890123");*/
		//eaidList.add("review");
		String eaid = "1234567890123===1234567890123===1234567890123===review";
		try{
			System.out.println(" Given EAID : "+eaid);
			String ecryptedStr = getEAIDJson(eaid);
			System.out.println(" Encoded eaid : "+ecryptedStr);
			System.out.println(" Decrypted eaid : "+decryptEAID(ecryptedStr));
			//decryptEAID(eaid);
			//System.out.println("decrypted eaidJson : "+ decryptEAID(eaid));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
