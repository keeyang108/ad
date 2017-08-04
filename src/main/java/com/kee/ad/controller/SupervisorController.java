package com.kee.ad.controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.model.Supervisor;
import com.kee.ad.service.SupervisorService;
import com.kee.ad.util.JwtTokenManagement;
import com.kee.ad.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kee on 2016/10/30.
 */
@Api(value = "Supervisor", description = "Supervisor", tags = {"Supervisor"})
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    private final static Logger logger = LoggerFactory.getLogger(SupervisorController.class);

    @Autowired
    private SupervisorService supervisorService;

    private JwtTokenManagement jwtTokenUtils;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public BaseResult<String> addSupervisor(@RequestBody Supervisor supervisor) throws IOException {

        if (StringUtils.isEmpty(supervisor.getSupervisorName())) {
            return ResponseBuilder.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(supervisor.getSupervisorPhone()) || !supervisor.getSupervisorPhone().matches("\\d{11}")) {
            return ResponseBuilder.error("请输入正确格式的手机号码");
        }
        if (StringUtils.isEmpty(supervisor.getPassword())) {
            return ResponseBuilder.error("请输入密码");
        }
        int row = supervisorService.addSupervisor(supervisor);
        if (row < 1) {
            return ResponseBuilder.error("Internal Error");
        }
        return ResponseBuilder.success("success");
    }

    @ApiOperation("user login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public BaseResult<Object> login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userName)) {
            return new BaseResult<Object>(false, "请输入用户名");
        }
        if (StringUtils.isEmpty(password)) {
            return new BaseResult<Object>(false, "请输入密码");
        }
        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorName(userName);
        supervisor.setPassword(password);
        Supervisor result = supervisorService.checkUser(supervisor);
        if (result == null) {
            return new BaseResult<Object>(false, "用户名或密码错误");
        }
        String token = jwtTokenUtils.generateToken(supervisor);
        JwtUtils.setCookie(request, response, token);
        return new BaseResult<Object>(true, "success");
    }

    @ApiOperation("user logout")
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        cleanCookie(request, response);
        return "/supervisor/login";
    }

    private void cleanCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setDomain(request.getServerName());
                cookies[i].setValue(null);
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
    }
}
