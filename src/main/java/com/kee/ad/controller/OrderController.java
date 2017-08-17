package com.kee.ad.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.OrderDetail;
import com.kee.ad.model.OrderDetailBean;
import com.kee.ad.model.OrderDetailQueryBean;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.pojo.PageBean;
import com.kee.ad.service.OrderDetailService;
import com.kee.ad.util.ExcelUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kee on 2016/10/23.
 */
@Api(value = "预约信息管理",description = "预约信息管理",tags = {"预约信息管理"})
@RestController
@RequestMapping("/front/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderDetailService orderDetailService;

    @ApiOperation("跨域新增预订信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "callback",value ="回调函数名称" ,dataType = "int",paramType = "query")
    })
    @GetMapping("/append")
    public JSONPObject addOrderByJSONP(String callback, HttpServletRequest request) throws UnsupportedEncodingException {
        JSONPObject json = null;
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if (name == null || StringUtils.isEmpty(name.toString())) {
            BaseResult<Object> result = new BaseResult<Object>(false, "用户名不能为空");
            json = new JSONPObject(callback, result);
            return json;
        }
        logger.info("**************name={}******************", name);
        String sex = request.getParameter("sex");
        logger.info("**************sex={}******************", sex);
        if (sex == null || StringUtils.isEmpty(sex.toString())) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String mobile = request.getParameter("mobile");
        logger.info("**************mobile={}******************", mobile);
        if (mobile == null || StringUtils.isEmpty(mobile) || !mobile.matches("\\d{11}")) {
            BaseResult<Object> result = new BaseResult<Object>(false, "请输入正确的手机号码");
            json = new JSONPObject(callback, result);
            return json;
        }
        String province = request.getParameter("province");
        logger.info("**************province={}******************", province);
        if (province == null || StringUtils.isEmpty(province)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String city = request.getParameter("city");
        logger.info("**************city={}******************", city);
        if (city == null || StringUtils.isEmpty(city)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String agentName = request.getParameter("agentName");
        logger.info("**************agentName={}******************", agentName);
        if (agentName == null || StringUtils.isEmpty(agentName)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String agentCode = request.getParameter("agentCode");
        logger.info("**************agentCode={}******************", agentCode);
        if (agentCode == null || StringUtils.isEmpty(agentCode) || Integer.parseInt(agentCode) < 0 || !agentCode.matches("\\d")) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String carType = request.getParameter("carType");
        logger.info("**************carType={}******************", carType);
        if (carType == null || StringUtils.isEmpty(carType)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String carTypeCode = request.getParameter("carTypeCode");
        logger.info("**************carTypeCode={}******************", carTypeCode);
        if (carTypeCode == null || StringUtils.isEmpty(carTypeCode) || Integer.parseInt(carTypeCode) < 0 || !carTypeCode.matches("\\d")) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String mediaName = URLDecoder.decode(request.getParameter("mediaName"), "utf-8");
        logger.info("**************mediaName={}******************", mediaName);
        if (mediaName == null || StringUtils.isEmpty(mediaName)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String mediaUrl = URLDecoder.decode(request.getParameter("mediaUrl"), "utf-8");
        logger.info("**************mediaUrl={}******************", mediaUrl);
        if (mediaUrl == null || StringUtils.isEmpty(mediaUrl)) {
            BaseResult<Object> result = new BaseResult<Object>(false, "非法参数");
            json = new JSONPObject(callback, result);
            return json;
        }
        String subject = request.getParameter("subject");
        logger.info("*************subject={}*******************", subject);
        String terminal = request.getParameter("terminal");
        logger.info("**************terminal={}******************", terminal);
        Integer isActivity = request.getParameter("isActivity") == null ? 0 : 1;
        OrderDetail detail = new OrderDetail();
        detail.setName(name);
        detail.setSex(sex);
        detail.setMobile(mobile);
        detail.setProvince(province);
        detail.setCity(city);
        detail.setAgentname(agentName);
        detail.setAgentcode(Integer.parseInt(agentCode));
        detail.setCartype(carType);
        detail.setCartypecode(Integer.parseInt(carTypeCode));
        detail.setTerminal(terminal);
        detail.setSubject(subject);
        detail.setIsActivity(isActivity.byteValue());
        detail.setMediaurl(mediaUrl);

        int row = orderDetailService.addOrder(detail);
        if (row < 1) {
            logger.warn("****** Add order failed *************");
            return new JSONPObject(callback, new BaseResult<Object>(false, "Add failed"));
        }
        logger.info("*********name={},mobile={},province={},city={},agentName={},agentCode={},carType={},carTypeCode={},mediaName={},terminal={},subject={}", name, mobile, province, city, agentName, agentCode, carType, carTypeCode, mediaName, terminal, subject);
        return new JSONPObject(callback, new BaseResult<Object>(true, "success"));
    }

    @ApiOperation("预订信息列表")
    @PostMapping("/list")
    public BaseResult<PageBean<OrderDetailBean>> listOrder(OrderDetailQueryBean queryBean) {
        List<OrderDetailBean> details = null;
        int totalCount;
        PageBean<OrderDetailBean> pageBean = new PageBean<>(queryBean);
        try {
            totalCount = orderDetailService.countOrderDetails(queryBean);
            if (totalCount > 0){
                details = orderDetailService.listOrderDetails(queryBean);
            }
        } catch (DataAccessException e) {
            logger.error("***************获取预约订单失败，错误信息{}", e.getCause().getMessage());
            return ResponseBuilder.error( "内部错误，请联系管理员");
        }
        if (details == null || details.size() < 1) {
            return ResponseBuilder.error( "内部错误，请联系管理员");
        }
        pageBean.setData(details);
        pageBean.setTotalCount(totalCount);
        Map<String, Object> conditions = new HashMap<String, Object>();
        if (queryBean.getStartDate() != null) {
            conditions.put("startDate", queryBean.getStartDate());
        }
        if (queryBean.getEndDate() != null) {
            conditions.put("endDate", queryBean.getEndDate());
        }
        if (!StringUtils.isEmpty(queryBean.getSubject())) {
            conditions.put("subject", queryBean.getSubject());
        }
        pageBean.setConditions(conditions);
        return ResponseBuilder.success(pageBean);
    }

    @ApiOperation("预约信息下载")
    @PostMapping(value = "/download")
    public String download(OrderDetailQueryBean detail, HttpServletResponse response) {
        List<OrderDetailBean> result = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (detail != null && StringUtils.isEmpty(detail.getSubject())) {
            return null;
        }
        if (detail == null) {
            detail = new OrderDetailQueryBean();
        }
        detail.setPageNo(0);
        detail.setPageSize(10000);
        detail.setIsActivity(false);
        logger.info(detail.toString());
        result = orderDetailService.listOrderDetails(detail);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OrderDetailBean orderDetail : result) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", orderDetail.getName());
            map.put("sex", orderDetail.getSex());
            map.put("mobile", orderDetail.getMobile());
            map.put("province", orderDetail.getProvince());
            map.put("city", orderDetail.getCity());
            map.put("agentName", orderDetail.getAgentname());
            map.put("connectTel",orderDetail.getConnectTel());
            map.put("carType", orderDetail.getCartype());
            map.put("mediaName", orderDetail.getMedianame());
            map.put("createTime", dateFormat.format(orderDetail.getCreateTime()));
            map.put("mediaUrl", orderDetail.getMediaurl());
            list.add(map);
        }
        String[] titles = {"用户名", "性别", "电话", "省份", "城市", "经销商","经销商电话", "车型", "渠道", "渠道链接", "预约时间"};
        String[] keys = {"name", "sex", "mobile", "province", "city", "agentName", "connectTel","carType", "mediaName", "mediaUrl", "createTime"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ExcelUtil.createExcel("预约表", titles, keys, list).write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(("预约表.xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int byteRead;
            while (-1 != (byteRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @ApiOperation("活动预约信息下载")
    @PostMapping(value = "/activity/download")
    public String activityDownload(OrderDetailQueryBean detail, HttpServletResponse response) {
        List<OrderDetailBean> result = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (detail == null) {
            detail = new OrderDetailQueryBean();
        }
        detail.setPageNo(0);
        detail.setPageSize(10000);
        detail.setIsActivity(true);
        result = orderDetailService.listOrderDetails(detail);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OrderDetail orderDetail : result) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", orderDetail.getName());
            map.put("mobile", orderDetail.getMobile());
            map.put("city", orderDetail.getCity());
            map.put("subject", orderDetail.getSubject());
            map.put("createTime", dateFormat.format(orderDetail.getCreateTime()));
            list.add(map);
        }
        String[] titles = {"用户名", "电话", "城市", "活动专题", "预约时间"};
        String[] keys = {"name", "mobile", "city", "subject", "createTime"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ExcelUtil.createExcel("预约表", titles, keys, list).write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(("活动参与表.xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int byteRead;
            while (-1 != (byteRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
