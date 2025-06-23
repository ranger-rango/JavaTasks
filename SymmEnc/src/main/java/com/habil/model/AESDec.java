package main.java.com.habil.model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AESDec
{
    private static final int GCM_TAG_LENGTH = 128;

    public static byte[] decrypt(byte[] cipherText, SecretKey key, byte[] iv) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        return cipher.doFinal(cipherText);
        
    }
}
