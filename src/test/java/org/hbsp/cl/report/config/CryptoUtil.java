package org.hbsp.cl.report.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

@Component
public class CryptoUtil {
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtil.class);

    private static final byte[] SALT = {
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final String SECRET = "secret1";

    // move this step to CLI tool
    public static byte[] encrypt(String textToEncrypt) {
        byte[] encryptedText = null;
        Cipher cipher = null;

        try {
            PBEParameterSpec paramSpec = new PBEParameterSpec(SALT, 20);
            PBEKeySpec keySpec = new PBEKeySpec(SECRET.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(keySpec);
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            int blockSize = cipher.getBlockSize();
            byte b[] = textToEncrypt.getBytes();
            byte padded[] = new byte[b.length + blockSize - (b.length % blockSize)];
            System.arraycopy(b, 0, padded, 0, b.length);
            for (int i = 0; i < blockSize - (b.length % blockSize); i++) {
                padded[b.length + i] = 0;
            }

            byte[] encrypted = cipher.doFinal(padded);
            /*StringBuilder builder = new StringBuilder();
            for(int i=0; i<encrypted.length; i++) {
				builder.append((char)encrypted[i]);
			}*/
            encryptedText = encrypted;
        } catch (Exception e) {
            logger.error("Failed to initialize cipher.", e);
        }


        return encryptedText;
    }


    public static String decrypt(byte[] encryptedText) {
        String clearText = null;
        Cipher cipher = null;

        try {
            PBEParameterSpec paramSpec = new PBEParameterSpec(SALT, 20);
            PBEKeySpec keySpec = new PBEKeySpec(SECRET.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(keySpec);
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            byte[] decryptedText = cipher.doFinal(encryptedText);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < decryptedText.length; i++) {
                builder.append((char) decryptedText[i]);
            }

            clearText = builder.toString().trim();

        } catch (Exception e) {
            logger.error("Failed to initialize cipher.", e);
        }

        return clearText;
    }


}
