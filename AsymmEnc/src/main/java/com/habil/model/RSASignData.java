package main.java.com.habil.model;

import java.security.PrivateKey;
import java.security.Signature;

public class RSASignData
{
    public static byte[] signData(String data, PrivateKey privateKey) throws Exception
    {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.sign();
    }
}
