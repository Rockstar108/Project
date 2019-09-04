package com.spring.healthcare.admin.crypto;

import java.security.PrivateKey;
import javax.crypto.Cipher;


public class RSADecrypter {

    protected RSACipher cipher;

    public RSADecrypter(PrivateKey key) {
        this.cipher = new RSACipher(Cipher.DECRYPT_MODE, key);
    }

    public String decrypt(byte[] message) {
        try {
            return new String(
                cipher.update(message).doFinal()
                , "UTF-8"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}