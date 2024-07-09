package th.co.gosoft.audit.cpram.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;

public class EncryptUtils {
	private static final Logger logger = Logger.getLogger(EncryptUtils.class);
	private static final String SALTSTRING = ConfigurationSystemManager.getInstance().getSecureSaltedString().trim();
	
	public static String EncryptSHA256() throws NoSuchAlgorithmException {
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hashInBytes = messageDigest.digest(SALTSTRING.getBytes(StandardCharsets.UTF_8));
		StringBuilder stringEncrypt = new StringBuilder();
		
		for (byte b : hashInBytes) {
			stringEncrypt.append(String.format("%02x", b));			
		}
		
		return stringEncrypt.toString();
	}
	
	public static String encoderURL(String s) throws UnsupportedEncodingException {
		String resultEncoder = URLEncoder.encode(s, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
		return resultEncoder;
	}
}
