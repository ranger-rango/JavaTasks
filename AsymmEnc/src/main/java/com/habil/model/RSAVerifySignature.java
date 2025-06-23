package main.java.com.habil.model;

import java.security.PublicKey;
import java.security.Signature;

public class RSAVerifySignature
{
    public static boolean verifySignature(String data, byte[] signatureBytes, PublicKey publicKey) throws Exception
    {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(signatureBytes);
        
    }
}
