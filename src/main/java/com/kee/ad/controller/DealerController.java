package com.kee.ad.controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.Dealer;
import com.kee.ad.model.DealerQueryBean;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.pojo.PageBean;
import com.kee.ad.service.DealerService;
import com.kee.ad.util.JAXBUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author KeeYang on 2017/8/8.
 * @Description :
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @PostMapping("/add")
    public BaseResult<Integer> addDealer(@RequestBody Dealer dealer){
        //validate necessary factor
        if (StringUtils.isBlank(dealer.getAgencyName())){
            return ResponseBuilder.error("经销商名称不能为空");
        }

        if (StringUtils.isBlank(dealer.getProvinceName())){
            return ResponseBuilder.error("省分为空");
        }

        if (StringUtils.isBlank(dealer.getCityName())){
            return ResponseBuilder.error("市级为空");
        }

        if (StringUtils.isBlank(dealer.getAgencyAddress())){
            return ResponseBuilder.error("经销商地址为空");
        }

        if (StringUtils.isBlank(dealer.getConnectTel())){
            return ResponseBuilder.error("经销商联系电话为空");
        }

        return ResponseBuilder.success(dealerService.addDealer(dealer));
    }

    @PutMapping("/update")
    public BaseResult<Integer> udpateDealer(@RequestBody Dealer dealer){
        if (null == dealer.getId() ||dealer.getId() < 1){
            return ResponseBuilder.error("请先选择更新的经销商");
        }
        return ResponseBuilder.success(dealerService.updateDealer(dealer));
    }

    @DeleteMapping("/del")
    public BaseResult<Integer> deleteDealer(Integer id){
        if (null == id || id < 1){
            return ResponseBuilder.error("请先选择要删除的经销商");
        }
        return ResponseBuilder.success(dealerService.deleteDealer(id));
    }

    @PostMapping("/list")
    public BaseResult<PageBean<Dealer>> listDealer(DealerQueryBean queryBean){
        return ResponseBuilder.success(dealerService.listDealer(queryBean));
    }

    @RequestMapping("/toxml")
    public BaseResult<String> convertToXml() throws IOException {
        dealerService.convertToXml();
        return ResponseBuilder.success("success");
    }

    @PostMapping("/upload")
    public BaseResult<String> upload(){
        return ResponseBuilder.success("success");
    }

}
