package main.java.com.habil.app;

import java.security.KeyPair;
import java.util.Base64;

import main.java.com.habil.model.RSADec;
import main.java.com.habil.model.RSAEnc;
import main.java.com.habil.model.RSASignData;
import main.java.com.habil.model.RSAVerifySignature;
import main.java.com.habil.model.RSAkeyPairGen;

public class RSAAsymm
{
    public static void main(String[] args) throws Exception
    {
        KeyPair keyPair = RSAkeyPairGen.keyGen();

        String secret = "Hello, secure world!";
        String modified_secret = "Hello, secure world1";

        byte[] cipherText = RSAEnc.encrypt(secret, keyPair.getPublic());

        String decrypted = RSADec.decrypt(cipherText, keyPair.getPrivate());

        byte[] signature = RSASignData.signData(secret, keyPair.getPrivate());

        boolean isValid = RSAVerifySignature.verifySignature(secret, signature, keyPair.getPublic());
        boolean isTampered = RSAVerifySignature.verifySignature(modified_secret, signature, keyPair.getPublic());

        System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(cipherText));
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signature));
        System.out.println("Signature Valid: " + isValid);
        System.out.println("(Integrity) Tampered With: " + !isTampered);
    }
}
