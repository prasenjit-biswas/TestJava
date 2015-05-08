package utility;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomMap<K, V> extends HashMap<K, V> {
	private HashMap<String, String> index = new HashMap<String,String>();
	
	public static final String AP_INSTRUCTOR_NAME = "instructor_name";
	public static final String AP_INSTRUCTOR_EMAIL = "instructor_email";
	public static String HEADER_STUDENT_NAME = "studentName";
	public static final String POLICY_LSI_instructoremail	= "p_instructor_email";
	public static final String POLICY_LSI_studentemail 		= "p_student_email";
	
	private static List<String> listOfParametersToBeEncrypted = new ArrayList<String>(Arrays.asList(
																				  AP_INSTRUCTOR_NAME
																				, AP_INSTRUCTOR_EMAIL
																				, HEADER_STUDENT_NAME
																				, POLICY_LSI_instructoremail
																				, POLICY_LSI_studentemail));

	public CustomMap() {
		super();
	}
	@Override
	public V get(Object key) {
		if (super.get(key) != null) {
			return super.get(key);
		}else{
			key = parameters.ENCRYPTION_PREFIX + key;
			if (super.get(key) != null) {
				return super.get(key);
			}
		}
		return (V) new String("");
	}
	@Override
	public V put(K key, V value) {
		return replaceParam(key, value);
	}
	@Override
	public V remove(Object key) {
		String k = (String)key;
        String keyInLowerCase = k.toLowerCase();
        String propKey = index.get(keyInLowerCase);
        return super.remove(propKey);
	}
	
	public V getParam(Object key) {
		boolean needsDecryption = false;
		String result = "";
		String k = (String)key;
        String keyInLowerCase = k.toLowerCase();
        String propKey = index.get(keyInLowerCase);
        if(propKey != null && propKey.contains(parameters.ENCRYPTION_PREFIX)){
			needsDecryption = true;
		}
        
        if(super.get(propKey) != null){
        	result = (String)super.get(propKey);
        }
        if(!result.isEmpty() && needsDecryption){
        	try {
        		result = Crypt.decrypt(result);
			} catch (GeneralSecurityException e) {
				System.out.println(" Decryption of key: " + key + " and value : " + result + " failed ");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(" Decryption of key: " + key + " and value : " + result + " failed ");
				e.printStackTrace();
			}
        }
        return (V)result;
	}
	
	public V replaceParam(Object key, Object value) {
		String inputValue = (String)value;
		String originalKey = (String)key;
		String str = originalKey.replace(parameters.ENCRYPTION_PREFIX, "");
		try{
			if(listOfParametersToBeEncrypted.contains(originalKey)){
				inputValue = Crypt.encrypt(inputValue);
				key = parameters.ENCRYPTION_PREFIX + key;
			}
			if(originalKey.startsWith(parameters.ENCRYPTION_PREFIX) && listOfParametersToBeEncrypted.contains(str)){
				originalKey = str;
			}
		}catch (GeneralSecurityException e) {
			System.out.println(" Encryption of key " + key + " failed ");
			e.printStackTrace();
		}
		
		String indexValue = index.get(originalKey.toLowerCase());
		if(indexValue != null){
			super.put((K)indexValue, (V)inputValue);
		}else{
			super.put((K)key, (V)inputValue);
			index.put(originalKey.toLowerCase(), (String)key);
		}
        
        return (V)inputValue;
	}

}
