package com.spring.healthcare.admin.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Crypto {

	public static final String CIPHER_ALGORITHM = "AES";

	public static final String ALOGORITHM = "AES/CBC/PKCS5Padding";

	public static final int KEY_LENGTH = 16;

	public static final String ENCODING = "UTF-8";

	private static final String KEY = "azdowx+bhgR8ff5EPh8DfQK+zVyta4YOa3FpBJsU2ykGzSOihPaY2dNQFJPnJgDh2CNVuz0M6qc1lPlsshUwTYeMyD0kqrWnah9dXMTNI4O+n2KQ4WIqEpS+gCFjmIlRQLbE84Nqx1JkjJlFtUDR1mTiz5NC8EC8h8OWpEFswYJ7Xa5Jc/v8eeX99tUw60/8LGUC0p03A62uUx0/KCaausybffx9npTFZcCf/O/y29ERaGTaAD8z+Eq1CLWjJUMHAqtOi2M4mXnx/RDgz6+oHAzWlaSYyqHyMXP3+w+jH2eZPabt52J/SXMOJ1WGd5CbiQYwduxc8JO80cfqEFc2FqMbPMqRsoEjsarY6X3GTO9prJIw+Q37DR8LsiLiFY9/M3SlOD8WD6mRr+hJR0UA3tcfMNSFlGgbjAJSdVbxNaEaS+/lI+Q500YMkj8owsWkOzahdw923XGw1MVthLaJ+n8HZMQVJDusxjVsaUiLlQc2m/RfAI4yxhHdxVF6gyFcqGcOggJl7EOKwvWTRlLlYGHqaLj+o0moUqS1qx3+GTAorZP/4Fl5xm4KxVmKQ/4U6C2Qfr1hv+yNL9asLitUCPWmEusZWNgv5WE3bkqCUwdB1TPGBwBFgstTjAfuTBfxhgAFTwnnI/IIYTY0w1WGPh3A8YcySTMI3I9hs6qxkYfrJsxoxtgNo109wgg8lC6NcVnAZIe0v+G6RUFMVIr2n7D9PzEM/gFCcOWtnBXcklzclAUJ1tjhQ8Yjd3G1uVgBTqf0bcWWPTWjW0vmO6jbPbxcn6f8xIm9YfqhY/9H65qNVABcbvJd7A==";

	private static final Log LOG = LogFactory.getLog(Crypto.class);

	/**
	 * Decrypt the given string.
	 * 
	 * @param encryptedValue
	 * @return
	 */
	public static String decrypt(String encryptedValue) {
		String decryptedValue = null;

		try {
			byte[] keyBytes = new byte[Crypto.KEY_LENGTH];

			byte[] b = KEY.getBytes(ENCODING);
			int len = b.length;

			if (len > keyBytes.length) {
				len = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, len);
			Cipher cipherClass = Cipher.getInstance(Crypto.ALOGORITHM);

			SecretKeySpec cipherKey = new SecretKeySpec(keyBytes, Crypto.CIPHER_ALGORITHM);
			IvParameterSpec cipherIvSpec = new IvParameterSpec(keyBytes);

			cipherClass.init(Cipher.DECRYPT_MODE, cipherKey, cipherIvSpec);

		
			BASE64Decoder decoder = new BASE64Decoder();

			byte[] data = cipherClass.doFinal(decoder.decodeBuffer(encryptedValue));
			decryptedValue = new String(data, Crypto.ENCODING);
		}
		catch (NoSuchAlgorithmException e) {
			LOG.error(" NoSuchAlgorithmException: " + e.getMessage(),e);
		}
		catch (NoSuchPaddingException e) {
			LOG.error(" NoSuchPaddingException: " + e.getMessage(),e);
		}
		catch (InvalidKeyException e) {
			LOG.error(" InvalidKeyException: " + e.getMessage(),e);
		}
		catch (InvalidAlgorithmParameterException e) {
			LOG.error(" InvalidAlgorithmParameterException: " + e.getMessage(),e);
		}
		catch (IllegalBlockSizeException e) {
			LOG.error(" IllegalBlockSizeException: " + e.getMessage(),e);
			return null;
		}
		catch (BadPaddingException e) {
			LOG.error(" BadPaddingException: " + e.getMessage(),e);
		}
		catch (IOException e) {
			LOG.error(" IOException: " + e.getMessage(),e);
		}

		return decryptedValue;
	}

	public static String encrypt(String strData) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {

		String strEncryptedData = null;
		IvParameterSpec cipherIvSpec = null;
		Cipher cipherClass = Cipher.getInstance(ALOGORITHM);

		byte[] keyBytes = new byte[KEY_LENGTH];
		byte[] b = KEY.getBytes(ENCODING);
		int len = b.length;

		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec cipherKey = new SecretKeySpec(keyBytes, CIPHER_ALGORITHM);
		cipherIvSpec = new IvParameterSpec(keyBytes);
		if ((cipherClass != null) && (cipherKey != null)) {

			try {
				// init the cipher for encryption
				cipherClass.init(Cipher.ENCRYPT_MODE, cipherKey, cipherIvSpec);

				byte[] encryptedData = cipherClass.doFinal(strData.getBytes(Charset.forName("UTF-8")));
				BASE64Encoder encoder = new BASE64Encoder();

				strEncryptedData = encoder.encode(encryptedData);
			}
			catch (InvalidKeyException e) {
				LOG.error(" InvalidKeyException: " + e.getMessage(),e);
			}
			catch (IllegalStateException e) {
				LOG.error(" IllegalStateException: " + e.getMessage(),e);
			}
			catch (IllegalBlockSizeException e) {
				LOG.error(" IllegalBlockSizeException: " + e.getMessage(),e);
			}
			catch (BadPaddingException e) {
				LOG.error(" BadPaddingException: " + e.getMessage(),e);
			}
			catch (InvalidAlgorithmParameterException e) {
				LOG.error(" InvalidAlgorithmParameterException: " + e.getMessage(),e);
			}
		}

		return strEncryptedData;
	}

}
