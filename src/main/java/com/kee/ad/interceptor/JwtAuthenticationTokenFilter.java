package com.kee.ad.interceptor;

import com.kee.ad.model.Supervisor;
import com.kee.ad.service.SupervisorService;
import com.kee.ad.util.JsonUtils;
import com.kee.ad.util.JwtTokenManagement;
import com.kee.ad.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Kee on 2017/7/19.
 */
public class JwtAuthenticationTokenFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    protected static final String LOGIN_URI = "/supervisor/logining";
    protected static final String ADDORDER_URI = "/front/order/append";
    protected static final String SWAGGER_URI = "/swagger-ui.html";

    public static final String CK_TOKEN = "token";

    private final static String tokenHeader = "Authorization";

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private JwtTokenManagement jwtTokenUtil;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = JwtUtils.getTokenFromRequest(httpRequest, this.tokenHeader);
        String uri = httpRequest.getRequestURI();
        if ((!LOGIN_URI.equals(uri) && !ADDORDER_URI.equals(uri) && !SWAGGER_URI.equals(uri)) && StringUtils.isNotBlank(token)) {
            //取出userName,查找数据库是否存在，然后重新生成token，看是否一致,如果一致则可以认为已经登陆
            String userName = jwtTokenUtil.getUsernameFromToken(token);
            if (null == userName) {
                servletResponse.getWriter().write(JsonUtils.toJson("请先登陆"));
                return;
            }
            Supervisor user = supervisorService.selectUserByUserName(userName);
            if (null == user) {
                servletResponse.getWriter().write(JsonUtils.toJson("非法操作"));
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
