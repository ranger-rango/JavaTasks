package main.java.com.habil.model;

import java.security.SecureRandom;

public class AESNonceGen
{
    private static final int GCM_IV_LENGTH = 12;

    public static byte[] generateIV()
    {
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}