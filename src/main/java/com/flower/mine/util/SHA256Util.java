package com.flower.mine.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {
    public static String hash(String str) {
        String encodeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException var4) {

        } catch (UnsupportedEncodingException var5) {

        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            String h = Integer.toHexString(b & 255);
            if (h.length() == 1) {
                sb.append("0");
            }
            sb.append(h);
        }
        return sb.toString();
    }
}
