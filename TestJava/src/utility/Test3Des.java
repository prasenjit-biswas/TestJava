package utility;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringEscapeUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test3Des {
	
	private static final String MESSAGE_DIGEST_INSTANCE = "md5";
	private static final String PASSWORD = "HG58YZ3CR9";
	private static final String ENCRYPTION_ALGORITHM_KEY_FACTORY = "DESede";
	private static final String ENCRYPTION_ALGORITHM_CIPHER = "DESede/CBC/PKCS5Padding";
	private static final String ENCODING_CHARSET = "ISO-8859-1";
	
	public static String  encrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance(MESSAGE_DIGEST_INSTANCE);
		final byte[] digestOfPassword = md.digest(PASSWORD.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}
		final SecretKey key = new SecretKeySpec(keyBytes,ENCRYPTION_ALGORITHM_KEY_FACTORY);
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_CIPHER);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		final byte[] plainTextBytes = message.getBytes(ENCODING_CHARSET);
		final byte[] cipherText = cipher.doFinal(plainTextBytes);
		String encrypted64String = new BASE64Encoder().encode(cipherText);
		//String urlEncodedString = URLEncoder.encode(encrypted64String, ENCODING_CHARSET);
		//System.out.println("escapeJava : "+StringEscapeUtils.escapeJava(urlEncodedString));
		//System.out.println("escapeHtml : "+StringEscapeUtils.escapeHtml(urlEncodedString));
		return encrypted64String;
	}

	public static String decrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance(MESSAGE_DIGEST_INSTANCE);
		final byte[] digestOfPassword = md.digest(PASSWORD.getBytes(ENCODING_CHARSET));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}
		//String encryptedBase64 = URLDecoder.decode(message, ENCODING_CHARSET);
		//System.out.println(" encryptedBase64 : "+encryptedBase64);
		byte[] encryptedBytes = new BASE64Decoder().decodeBuffer(message);
		final SecretKey key = new SecretKeySpec(keyBytes, ENCRYPTION_ALGORITHM_KEY_FACTORY);
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher decipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_CIPHER);
		decipher.init(Cipher.DECRYPT_MODE, key, iv);
		final byte[] plainText = decipher.doFinal(encryptedBytes);
		return new String(plainText, ENCODING_CHARSET);
	}
	
	public static void main(String[] args) throws Exception {
		String testid = "1234567890123123";
		String attempt = "1234567890123123";
		long aat = 1234567890123123l;
		String mode = null;
		String marker = "===";
		/*String text = new StringBuilder(testid).append(marker).append(attempt).
					  append(marker).append(aat).append(marker).append(mode).toString();//"assigment===attempt===aat===mode";
    	String codedtext = encrypt(text);
    	String decodedtext = decrypt(codedtext);*/
		
		String text = encrypt(testid)+"$"+encrypt(attempt)+"$"+encrypt(String.valueOf(aat));
		
    	System.out.println("codedtext : "+text);
    	 String newText = new BASE64Encoder().encode(text.getBytes());
    	
    	 System.out.println(" newText : "+newText);
    	 //System.out.println(" decodedtext : "+decodedtext);
	}
}
