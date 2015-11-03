package bl.other;

import org.apache.commons.codec.digest.DigestUtils;

public class Utilitary {
	
    public static String hash(String str) {
    	String result;
    	result = DigestUtils.sha1Hex(str);
    	return result;
    }
    public static String hash(char[] value){
    	return hash(String.valueOf(value));
    }
}