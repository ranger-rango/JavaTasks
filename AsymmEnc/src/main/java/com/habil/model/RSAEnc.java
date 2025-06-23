package main.java.com.habil.model;

import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSAEnc
{
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }
}
