package com.spring.healthcare.admin.crypto;

import java.security.Key;
import javax.crypto.Cipher;

public class RSACipher {

    public static String ALGORITHM_NAME = "RSA";
    public static String MODE_OF_OPERATION = "ECB";
    public static String PADDING_SCHEME = "OAEPWithSHA-512AndMGF1Padding";

    protected Cipher cipher;

    public RSACipher(int mode, Key key) {
        try {
            cipher = Cipher.getInstance(
                String.format("%s/%s/%s",ALGORITHM_NAME,MODE_OF_OPERATION,PADDING_SCHEME));
            cipher.init(mode, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RSACipher update(byte[] bytes) {
        try {
            cipher.update(bytes);
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] doFinal() {
        try {
            return cipher.doFinal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
