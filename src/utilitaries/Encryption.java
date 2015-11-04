package utilitaries;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {
    public static String hash(String str) {
    	String result;
    	result = DigestUtils.sha1Hex(str);
    	return result;
    }
    public static String hash(char[] value){
    	return hash(String.valueOf(value));
    }
}
