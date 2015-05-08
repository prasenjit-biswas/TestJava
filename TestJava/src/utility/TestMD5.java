package utility;

import java.io.ByteArrayInputStream;

import com.mcgrawhill.ezto.utilities.MD5;

public class TestMD5 {

	public static void main(String[] args) {
		String parameter = "cid=1&domain=1&probid=0000000000-chap1-sect1-prob1&qapreview=true&uid=demo_user&viewer_style=2";
		String mpCode = "who is mh b3rn0u11i?";
		String ipCode = "";
		try{
			getMD5(parameter, mpCode, ipCode);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static String getMD5(String parameter, String mpCode, String ipCode) throws Exception {
		String convertString = mpCode+parameter/*+ipCode*/;
		String contentMD5 = MD5.md5stream(new ByteArrayInputStream(convertString.getBytes()));
		System.out.println(" contentMD5 : "+contentMD5);
		return contentMD5;
	}
}
