package org.hbsp.cl.report.config;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rui.chen on 4/11/16.
 */
public class CryptoUtilTest {
    @Test
    public void testUtil() {
        byte[] encrypted1 = CryptoUtil.encrypt("test");
        String decrypted1 = CryptoUtil.decrypt(encrypted1);

        System.out.println(decrypted1);
    }

    @Test
    public void testJasypt() {
        String toEncryptText = "test789";
        int times = 1000; // run 1000 times

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("57592664c22539bf8cfd0818dc9245e943742856");
        // sha-1('cl-secret') = 57592664c22539bf8cfd0818dc9245e943742856
        // md5("cl-secret")   = 62f0ebad6c1c564687caecef62109b26
        // password123 -> ARYPZp/tVBLB1znya2Notg3Le9ZIgC+v

        for (int i = 0; i < times; i++) {
            String encrypted = textEncryptor.encrypt(toEncryptText);
            System.out.println(encrypted);
            assertEquals(textEncryptor.decrypt(encrypted), toEncryptText);
        }
    }

}
