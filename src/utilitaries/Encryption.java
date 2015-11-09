package utilitaries;
import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {
    public static String hash(String str) {
    	return DigestUtils.sha512Hex(str);
    }
    public static String hash(char[] value){
    	return hash(String.valueOf(value));
    }
}