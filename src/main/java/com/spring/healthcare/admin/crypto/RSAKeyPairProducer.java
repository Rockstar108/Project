package com.spring.healthcare.admin.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class RSAKeyPairProducer {

    /**
     * Generates a new RSA-4096 bit {@code KeyPair}.
     *
     * @return {@code KeyPair}, never null
     * @throws RuntimeException All exceptions are caught
     * and re-thrown as {@code RuntimeException}
     */
    public KeyPair produce() {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            //keyGen.initialize(1024);
            keyGen.initialize(2048);
            //keyGen.initialize(3072);
            //keyGen.initialize(4096);
            return keyGen.generateKeyPair();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}