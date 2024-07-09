package th.co.gosoft.audit.cpram.cryptography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import th.co.gosoft.audit.cpram.conts.ConstSystemProperties;
import th.co.gosoft.audit.cpram.utils.PropertiesUtils;

public class AsymmetricCryptography {
	private Cipher cipher;
	private String transformation;
	
	public AsymmetricCryptography(String transformation) throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.transformation = transformation;
		this.cipher = Cipher.getInstance(this.transformation);
	}
	
	public PrivateKey getPrivateKey(String fileName) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(fileName).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(this.transformation);
		return kf.generatePrivate(spec);
	}
	
	public PublicKey getPublicKey(String fileName) throws Exception{
		byte[] keyBytes = Files.readAllBytes(new File(fileName).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(this.transformation);
		return kf.generatePublic(spec);
	}
	
	public String encryptText(String msg, PrivateKey key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes("UTF-8")));
	}
	
	public String decryptText(String msg, PublicKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(this.cipher.doFinal(Base64.getDecoder().decode(msg.getBytes())) ,"UTF-8");		
	}
	
	public void generateKeyPair(int keySize) throws NoSuchAlgorithmException, IOException {
		String pathOutputPrivateKey = PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA);
		String pathOutputPublicKey = PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PUBLIC_KEY_RSA);
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(this.transformation);
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		FileOutputStream fileOutputStream = new FileOutputStream(pathOutputPrivateKey);
		fileOutputStream.write(keyPair.getPrivate().getEncoded());
		fileOutputStream.close();
		
		fileOutputStream = new FileOutputStream(pathOutputPublicKey);
		fileOutputStream.write(keyPair.getPublic().getEncoded());
		fileOutputStream.close();
	}
	
	
}
