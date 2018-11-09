package com.flower.mine.util;

public class SmsUtil {

    private static final int[] key = new int[]{-1, 0, 2, 1, -2};

    public static String encript(String value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i ++) {
            char c = value.charAt(i);
            sb.append( (char)(c + key[i % 5]) );
        }
        return sb.toString();
    }

    public static String decript(String value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i ++) {
            char c = value.charAt(i);
            sb.append( (char) (c - key[i % 5]) );
        }
        return sb.toString();
    }
}
