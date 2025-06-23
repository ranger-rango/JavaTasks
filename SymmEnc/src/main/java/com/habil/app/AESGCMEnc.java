package main.java.com.habil.app;

import javax.crypto.SecretKey;

import main.java.com.habil.model.AESDec;
import main.java.com.habil.model.AESEnc;
import main.java.com.habil.model.AESKeyGen;
import main.java.com.habil.model.AESNonceGen;
import main.java.com.habil.model.Base64Helpers;

public class AESGCMEnc
{
    public static void main(String[] args) throws Exception
    {
        // generateKey
        SecretKey key = AESKeyGen.keyGen();
        // generate nonce 
        byte[] iv = AESNonceGen.generateIV();

        String secret = "This is a secret message";
        byte[] plainText = secret.getBytes();

        // encrypt <to b64>
        byte[] cipherText = AESEnc.encrypt(plainText, key, iv);

        // decrypt <from b64>
        byte[] decrypted = AESDec.decrypt(cipherText, key, iv);

        System.out.println("Original: " + secret);
        System.out.println("Encrypted: " + Base64Helpers.toBase64(cipherText));
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
