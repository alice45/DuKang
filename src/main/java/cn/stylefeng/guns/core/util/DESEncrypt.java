package cn.stylefeng.guns.core.util;

import com.google.common.base.Strings;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESEncrypt {


    private static final String keyString = "vpRZ1kmU";


    private static final String ivString = "EbpU4WtY";




    public static String encrypt(String content) {
        try {
            if (Strings.isNullOrEmpty(content)) {
                return null;
            }
            IvParameterSpec iv = new IvParameterSpec("EbpU4WtY".getBytes());
            DESKeySpec dks = new DESKeySpec("vpRZ1kmU".getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, key, iv);
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));
            return DESPlus.byteArr2HexStr(result);
        } catch (Exception e) {

            return null;
        }
    }
    public static String decrypt(String content) {
        try {
            if (Strings.isNullOrEmpty(content)) {
                return null;
            }
            IvParameterSpec iv = new IvParameterSpec("EbpU4WtY".getBytes());
            DESKeySpec dks = new DESKeySpec("vpRZ1kmU".getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, key, iv);
            byte[] result = cipher.doFinal(DESPlus.hexStr2ByteArr(content));
            return new String(result, "utf-8");
        } catch (Exception e) {

            return null;
        }
    }

}
