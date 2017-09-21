package com.kee.ad.util;

import com.kee.ad.interceptor.JwtAuthenticationTokenFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kee on 2017/7/19.
 */
public class JwtUtils {

    /**
     * 从用户请求里取出token
     *
     * @param request
     * @param tokenHeader
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request, String tokenHeader) {
        String authToken = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(authToken)) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (null != cookies) {
                for (int i = 0; i < cookies.length; i++) {
                    if (JwtAuthenticationTokenFilter.CK_TOKEN.equals(cookies[i].getName())) {
                        authToken = cookies[i].getValue();
                        break;
                    }
                }
            }
        }
        return authToken;
    }

    /**
     * 设置cookie
     *
     * @param req
     * @param resp
     * @param device
     * @param token
     */
    public static void setCookie(HttpServletRequest req, HttpServletResponse resp, String token) {

        Cookie cookie = new Cookie(JwtAuthenticationTokenFilter.CK_TOKEN, token);
        cookie.setHttpOnly(true);
        cookie.setDomain(req.getServerName());
        cookie.setPath("/");
        resp.setHeader("token",token);
        resp.addCookie(cookie);

    }
}
