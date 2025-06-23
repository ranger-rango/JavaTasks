package main.java.com.habil.model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class RSAkeyPairGen
{
    public static KeyPair keyGen() throws Exception
    {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        return keyGenerator.generateKeyPair();
    }
}
