package cc.myandroid.likelive.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenzd on 2017/2/23.
 * 加密工具类
 */

public class EncryptionUtil {

    /**
     * 字符串加密 MD5
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String EncodeMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <bytes.length ; i++) {
            int value = bytes[i]&0xff;
            if (value<16){
                sb.append("0");
            }else {
                sb.append(value);
            }
        }
        return sb.toString();
    }
}
