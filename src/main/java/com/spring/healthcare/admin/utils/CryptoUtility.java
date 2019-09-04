package com.spring.healthcare.admin.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.log4j.Logger;

import com.spring.healthcare.admin.crypto.RSADecrypter;
import com.spring.healthcare.admin.crypto.RSAEncrypter;
import com.spring.healthcare.admin.crypto.RSAKeyPairProducer;
import com.spring.healthcare.admin.crypto.RSAPrivateKeyProducer;
import com.spring.healthcare.admin.crypto.RSAPublicKeyProducer;
import com.spring.healthcare.admin.crypto.io.ByteArrayReader;
import com.spring.healthcare.admin.crypto.io.ByteArrayWriter;

public class CryptoUtility {

	private static Logger logger = Logger.getLogger(CryptoUtility.class);

	static Path privateKeyFile;
	static Path publicKeyFile;

	public static byte[] encrypt(String toEncrypt) {
		logger.info("Encryption Value :: " + toEncrypt);
		byte[] encryptedBytes = null;
		try {
			publicKeyFile = Paths.get("./target/RSAPublic.key").toAbsolutePath();
			KeyPair keyPair = new RSAKeyPairProducer().produce();

			ByteArrayWriter writer = new ByteArrayWriter(publicKeyFile);
			writer.write(keyPair.getPublic().getEncoded());

			PublicKey publicKey = new RSAPublicKeyProducer().produce(new ByteArrayReader(publicKeyFile).read());
			RSAEncrypter encrypter = new RSAEncrypter(publicKey);

			encryptedBytes = encrypter.encrypt(toEncrypt);
			//logger.info("Encrypted %s%n :: " + new String(encryptedBytes, "UTF-8"));

		} catch (Exception e) {
			logger.error("Exception in encrypt :: " + e.getMessage());
		}
		return encryptedBytes;
	}

	public static String decrypt(byte[] toDecrypt) {
		logger.info("Decryption Value :: " + toDecrypt);
		String decryptedValue = null;
		try {
			privateKeyFile = Paths.get("./target/RSAPrivate.key").toAbsolutePath();
			KeyPair keyPair = new RSAKeyPairProducer().produce();

			ByteArrayWriter writer = new ByteArrayWriter(privateKeyFile);
			writer.write(keyPair.getPrivate().getEncoded());

			PrivateKey privateKey = new RSAPrivateKeyProducer().produce(new ByteArrayReader(privateKeyFile).read());
			RSADecrypter decrypter = new RSADecrypter(privateKey);

			decryptedValue = decrypter.decrypt(toDecrypt);
			logger.info("Decrypted :: " + decryptedValue);

		} catch (Exception e) {
			logger.error("Exception in decrypt :: " + e.getMessage());
		}
		return decryptedValue;
	}
	
	public static byte[] getPublicKey() {
		byte[] keyBytes = null;
		try {
			publicKeyFile = Paths.get("./target/RSAPublic.key").toAbsolutePath();
			KeyPair keyPair = new RSAKeyPairProducer().produce();

			ByteArrayWriter writer = new ByteArrayWriter(publicKeyFile);
			writer.write(keyPair.getPublic().getEncoded());
			
			logger.info("publicKeyFile :: " + publicKeyFile.toString());
			keyBytes = Files.readAllBytes(Paths.get(publicKeyFile.toString()));
		
		} catch(Exception e) {
			logger.error("Exception in getPublicKey :: " + e.getMessage());
		}
		return keyBytes;
	}
	
	public static void main(String args[]) throws Exception {
		byte[] encryptValue = CryptoUtility.encrypt("Test Message 11111111");
		logger.info(encryptValue);
		String decryptValue = CryptoUtility.decrypt(encryptValue);		
		logger.info("Final Output :: " + decryptValue);
	}

}
