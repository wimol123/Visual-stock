package th.co.gosoft.audit.cpram.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import th.co.gosoft.audit.cpram.conts.ConstSystemProperties;
import th.co.gosoft.audit.cpram.cryptography.AsymmetricCryptography;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.PropertiesUtils;

public class CryptographyController {
	
	private final static Logger logger = Logger.getLogger(CryptographyController.class);
	
	public String encryptRSA(String stringEncoder) {
		AsymmetricCryptography asymmetricCryptography = null;
		Gson gson = null;
		try {
			
			asymmetricCryptography = new AsymmetricCryptography("RSA");
			gson = new Gson();
			Map<String, String> mapResultDecode = new HashMap<>();
			JsonElement jsonElement = new JsonParser().parse(stringEncoder);
			JsonObject  jobject = jsonElement.getAsJsonObject();
			String resultEncode = asymmetricCryptography.encryptText(jobject.get("dataEncrypt").getAsString().trim(), asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA)));
			mapResultDecode.put("encodeResult", resultEncode);
			return gson.toJson(mapResultDecode);
			
		}catch (Exception e) {
			logger.error("CryptographyController.encryptRSA() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	public String decryptRSA(String stringEncoder) {
		AsymmetricCryptography asymmetricCryptography = null;
		Gson gson = null;
		try {
			
			asymmetricCryptography = new AsymmetricCryptography("RSA");
			gson = new Gson();
			Map<String, String> mapResultDecode = new HashMap<>();
			JsonElement jsonElement = new JsonParser().parse(stringEncoder);
			JsonObject  jobject = jsonElement.getAsJsonObject();			
			
			String resultDecode = asymmetricCryptography.decryptText(jobject.get("dataEncrypt").getAsString(), asymmetricCryptography.getPublicKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PUBLIC_KEY_RSA)));			
			mapResultDecode.put("decodeResult", resultDecode);
			return gson.toJson(mapResultDecode);
		}catch(Exception e) {
			logger.error("CryptographyController.decryptRSA() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
}
