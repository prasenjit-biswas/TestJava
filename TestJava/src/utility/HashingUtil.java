package utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;


/**
 * Some utility methods for digesting info using MD5.
 */
public class HashingUtil {
    public static final String charSet = "UTF-8";
    public static final String hashAlgo = "MD5";

    private static final char[] hex = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
    };

    /**
     * Turns array of bytes into string representing each byte as
     * unsigned hex number.
     *
     * @param hash array of bytes to convert to hex-string
     * @return	generated hex string
     */
    public static final String toHex(byte hash[]) {
        StringBuffer buf = new StringBuffer(hash.length * 2);

        for (int idx = 0; idx < hash.length; idx++)
            buf.append(hex[(hash[idx] >> 4) & 0x0f]).append(hex[hash[idx] & 0x0f]);

        return buf.toString();
    }

    /**
     * Digest the input.
     *
     * @param input the data to be digested.
     * @return the md5-digested input
     */
    public static final byte[] digest(byte[] input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(input);
        } catch (NoSuchAlgorithmException nsae) {
            throw new Error(nsae.toString());
        }
    }

    /**
     * Digest the input.
     *
     * @param input1 the first part of the data to be digested.
     * @param input2 the second part of the data to be digested.
     * @return the md5-digested input
     */
    public static final byte[] digest(byte[] input1, byte[] input2) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(hashAlgo);
            md5.update(input1);
            return md5.digest(input2);
        } catch (NoSuchAlgorithmException nsae) {
            throw new Error(nsae.toString());
        }
    }

    /**
     * Digest the input.
     *
     * @param input the data to be digested.
     * @return the md5-digested input as a hex string
     */
    public static final String hexDigest(byte[] input) {
        return toHex(digest(input));
    }

    /**
     * Digest the input.
     *
     * @param input1 the first part of the data to be digested.
     * @param input2 the second part of the data to be digested.
     * @return the md5-digested input as a hex string
     */
    public static final String hexDigest(byte[] input1, byte[] input2) {
        return toHex(digest(input1, input2));
    }

    /**
     * Digest the input.
     *
     * @param input the data to be digested.
     * @return the md5-digested input as a hex string
     */
    public static final byte[] digest(String input) {
        try {
            return digest(input.getBytes(charSet));
        } catch (UnsupportedEncodingException uee) {
            throw new Error(uee.toString());
        }
    }

    /**
     * Digest the input.
     *
     * @param input the data to be digested.
     * @return the md5-digested input as a hex string
     */
    public static final String hexDigest(String input) {
        try {
            return toHex(digest(input.getBytes(charSet)));
        } catch (UnsupportedEncodingException uee) {
            throw new Error(uee.toString());
        }
    }

    public static boolean verifyData(String hash, String data, String pp) {
        String msgId = getHexDigest(data, pp);

        if (msgId.equals(hash))
            return true;
        else
            return false;
    }

    public static String getParamValue(String paramName, String queryStr) {
        StringTokenizer strTok = new StringTokenizer(queryStr, "&");
        while (strTok.hasMoreTokens()) {
            String data[] = strTok.nextToken().split("=");
            if (data[0].equalsIgnoreCase(paramName)) {
                try {
                    return data[1];
                } catch (ArrayIndexOutOfBoundsException aex) {
                    return "";
                }
            }
        }
        return null;
    }

    /**
     * Notice that this method actually digest twice.
     *
     * @param qs
     * @param ps
     * @return
     */
    public static String getHexDigest(String qs, String ps) {
        String msgId = "default";
        String convertString = ps + "" + qs;
        byte res[] = HashingUtil.digest(convertString.getBytes());      //digest once
        msgId = HashingUtil.hexDigest(res);                             //digest twice

        return msgId;
    }


    /**
     * Generate MD 5 string ( digest once )
     *
      *@param qs
     * @param ps
     * @return
     */
    public static String getDigest(String qs, String ps) {
        String msgId = "default";
        String convertString = ps + "" + qs;
        byte res[] = HashingUtil.digest(convertString.getBytes());
        msgId = toHex(res);
        return msgId;
    }


    /**
     *  A keyed digest is one in which a secret key is used to create a digest for a buffer of bytes.
     *  You can use different keys to create different digests for the same buffer of bytes.
    */
    public static byte[] getKeyedDigest(String passphrase,String sk) {
        byte[] buffer = passphrase.getBytes();
        byte[] key = sk.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
        String nativeId ="";// "13113273062721000";
    	String MD5String = HashingUtil.getDigest("todo=pullSubmissions&ezid="+nativeId, "Madison Square Gardens");
    	System.out.println("#### MD5String : " + MD5String);
    }
}