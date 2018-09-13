package com.flower.mine.util;


import javax.validation.constraints.NotNull;

/**
 * Password tool class.
 * @author wanglei, wangleilc@inspur.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class PasswordUtil {

    private static final String Password_Format = "i %s love %s china";

    /**
     * Get hash string using password and salt
     * @param password user's password
     * @param salt a random string
     * @return sha-256 string{@link SHA256Util}
     */
    public static String hashPassword(@NotNull String password, @NotNull String salt) {
        return SHA256Util.hash(String.format(Password_Format, password, salt));
    }

    /**
     * Check whether the password is correct
     * @param password user's password
     * @param salt a random string
     * @param hash correct sha-256 string
     * @return true if the password is correct, false if incorrect
     */
    public static boolean checkPassword(@NotNull String password, @NotNull String salt, @NotNull String hash) {
        return SHA256Util.hash(String.format(Password_Format, password, salt)).equals(hash);
    }
}
