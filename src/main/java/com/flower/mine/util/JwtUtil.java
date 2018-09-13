package com.flower.mine.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JWT token tool class.
 * @author wanglei, wangleilc@inspur.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class JwtUtil {

    private static final Algorithm algorithm = Algorithm.HMAC256("28a12b67e68441588d0ee60539d4f289");
    private static final JWTVerifier verifier = JWT.require(algorithm).build();

    public static String verifyAndGetClaim(String token, String key) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(key).asString();
    }

    public static String generateToken(String key, String value) {
        return JWT.create().withClaim(key, value).sign(algorithm);
    }
}

