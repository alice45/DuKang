package cn.stylefeng.guns.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class DESPlus {


    private Cipher encryptCipher = null;

    private Cipher decryptCipher = null;




    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;

        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp += 256;
            }

            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;


        byte[] arrOut = new byte[iLen / 2]; int i;
        for (i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }



    public byte[] encrypt(byte[] arrB){
        try {
            return this.encryptCipher.doFinal(arrB);
        } catch (Exception e) {

        }
        return null;
    }








    public String encrypt(String strIn){
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }








    public byte[] decrypt(byte[] arrB) {
        try {
            return this.decryptCipher.doFinal(arrB);
        }catch (Exception e) {

        }
        return null;
    }









    public String decrypt(String strIn){ return new String(decrypt(hexStr2ByteArr(strIn))); }










    private Key getKey(byte[] arrBTmp) {
        byte[] arrB = new byte[8];

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        return new SecretKeySpec(arrB, "DES");
    }

}
