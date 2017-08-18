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
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * Created by Administrator on 2016/11/15.
 */
@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtTokenManagement jwtTokenUtil;

    @Autowired
    private SupervisorService supervisorService;

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    protected static final String LOGIN_URI = "/supervisor/login";
    protected static final String ADDORDER_URI = "/front/order/append";
    protected static final String SWAGGER_URI = "/swagger-ui.html";

    public static final String CK_TOKEN = "token";

    private final static String tokenHeader = "Authorization";

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());

        logger.info("requestUri:{}", requestUri);
        logger.info("contextPath:{}", contextPath);
        logger.info("url:{}", url);
        String token = JwtUtils.getTokenFromRequest(httpServletRequest, this.tokenHeader);
        String uri = httpServletRequest.getRequestURI();
        if ((!LOGIN_URI.equals(uri) && !ADDORDER_URI.equals(uri) && validateIfSwagger(uri))) {
            if (StringUtils.isBlank(token)){
                httpServletResponse.getWriter().write(JsonUtils.toJson("请先登陆"));
                return false;
            }
            //取出userName,查找数据库是否存在，然后重新生成token，看是否一致,如果一致则可以认为已经登陆
            String userName = jwtTokenUtil.getUsernameFromToken(token);
            if (null == userName) {
                httpServletResponse.getWriter().write(JsonUtils.toJson("请先登陆"));
                return false;
            }
            Supervisor user = supervisorService.selectUserByUserName(userName);
            if (null == user) {
                httpServletResponse.getWriter().write(JsonUtils.toJson("非法操作"));
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean validateIfSwagger(String uri){
        if (uri.equals(SWAGGER_URI)){
            return  false;
        }
        if (uri.startsWith("/swagger-resources")){
            return false;
        }
        if (uri.startsWith("/v2/api-docs")){
            return false;
        }
        if (uri.startsWith("/webjars/springfox-swagger-ui")){
            return false;
        }
        return true;
    }
}
