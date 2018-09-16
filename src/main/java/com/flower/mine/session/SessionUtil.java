package com.flower.mine.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.mine.util.ConstUtil;
import com.flower.mine.util.JsonUtil;
import com.flower.mine.util.JwtUtil;
import com.flower.mine.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * A session tool class.
 * @author wanglei, wangleilc@inspur.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class SessionUtil {

    private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);
    private static final String Request_Attribute_User = "_user";

    /**
     * Get the currently logged-in user's SessionInfo
     * @return
     * @throws IOException
     */
    public static SessionInfo current(){
        SessionInfo sessionInfo = (SessionInfo) ServletUtil.currentRequest().getAttribute(Request_Attribute_User);
        if (sessionInfo == null) {
            String header = ServletUtil.currentRequest().getHeader("Authorization");
            if (StringUtils.isEmpty(header)) {
                return null;
            }
            if (header.startsWith("Bearer")) {
                String token = header.trim().substring(7).trim();
                String userInfoJson = JwtUtil.verifyAndGetClaim(token, ConstUtil.Request_Header_Jwt_Session_Key);
                sessionInfo = JsonUtil.deserialize(userInfoJson, SessionInfo.class);
                ServletUtil.currentRequest().setAttribute(Request_Attribute_User, sessionInfo);
            }
        }
        return sessionInfo;
    }

    /**
     * Get the currently logged-in user's id
     * @return
     */
    public static String currentUserId() {
        try {
            SessionInfo sessionInfo = current();
            if (sessionInfo != null) {
                return current().getUsername();
            }
        } catch (Exception e) {
            log.error("SessionUtil currentUserId", e);
        }
        return null;
    }

    /**
     * Generate jwt-token
     * @param sessionInfo
     * @return
     * @throws JsonProcessingException
     */
    public static String token(@NotNull SessionInfo sessionInfo){
        return JwtUtil.generateToken(ConstUtil.Request_Header_Jwt_Session_Key, JsonUtil.serialize(sessionInfo));
    }
}
