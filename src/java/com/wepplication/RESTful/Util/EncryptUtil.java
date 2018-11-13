package com.wepplication.RESTful.Util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class EncryptUtil {
    /**
     * 문자열을 MD-5 방식으로 암호화
     */
    public static String encryptByMd5(String txt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e){
            return "";
        }
    }

    public static String encryptBySha256(String txt){
        try{
            StringBuffer sbuf = new StringBuffer();

            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            mDigest.update(txt.getBytes());

            byte[] msgStr = mDigest.digest() ;

            for(int i=0; i < msgStr.length; i++){
                byte tmpStrByte = msgStr[i];
                String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

                sbuf.append(tmpEncTxt) ;
            }

            return sbuf.toString().toUpperCase();
        }catch (Exception e){
            return "";
        }
    }
}
