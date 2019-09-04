package com.spring.healthcare.admin.crypto;

import java.security.PublicKey;
import javax.crypto.Cipher;

public class RSAEncrypter {

    protected RSACipher cipher;

    public RSAEncrypter(PublicKey key) {
        this.cipher = new RSACipher(Cipher.ENCRYPT_MODE, key);
    }

    public byte[] encrypt(String message) {
        try {
            return cipher
                    .update(message.getBytes("UTF-8"))
                    .doFinal()
            ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}