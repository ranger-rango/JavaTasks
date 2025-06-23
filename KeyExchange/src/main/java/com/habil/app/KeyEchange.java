package main.java.com.habil.app;

import java.security.KeyPair;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import main.java.com.habil.model.AESDec;
import main.java.com.habil.model.AESEnc;
import main.java.com.habil.model.AESKeyGen;
import main.java.com.habil.model.AESNonceGen;
import main.java.com.habil.model.RSADec;
import main.java.com.habil.model.RSAEnc;
import main.java.com.habil.model.RSASignData;
import main.java.com.habil.model.RSAVerifySignature;
import main.java.com.habil.model.RSAkeyPairGen;

public class KeyEchange
{
    public static void main(String[] args) throws Exception
    {
        KeyPair senderKeyPair = RSAkeyPairGen.keyGen();
        KeyPair receiverKeyPair = RSAkeyPairGen.keyGen();

        SecretKey key = AESKeyGen.keyGen();
        byte[] iv = AESNonceGen.generateIV();

        byte[] keyBytes = key.getEncoded();
        String aesKeyBase64 = Base64.getEncoder().encodeToString(keyBytes);

        String randomXml = """
        <?xml version=\"1.0\"?>
        <person>
            <name>John Doe</name>
            <age>30</age>
        </person>
        """;

        byte[] randomXmlPlainText = randomXml.getBytes();
        byte[] aesCiphertext = RSAEnc.encrypt(aesKeyBase64, receiverKeyPair.getPublic());
        byte[] randomXmlCipherText = AESEnc.encrypt(randomXmlPlainText, key, iv);
        byte[] randomXmlSignature = RSASignData.signData(randomXml, senderKeyPair.getPrivate());

        System.out.println("XML: \n" + randomXml);
        // second class 
        String exchangedKey = RSADec.decrypt(aesCiphertext, receiverKeyPair.getPrivate());
        byte[] decodedKey = Base64.getDecoder().decode(exchangedKey);
        SecretKey exchangedAesKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        byte[] decXmlBytes = AESDec.decrypt(randomXmlCipherText, exchangedAesKey, iv);
        String decXml = new String(decXmlBytes);
        boolean signatureValid = RSAVerifySignature.verifySignature(decXml, randomXmlSignature, senderKeyPair.getPublic());

        System.out.println("Signature Valid? " + signatureValid);
        System.out.println("Decrypted XML: \n" + decXml);

    }
}
