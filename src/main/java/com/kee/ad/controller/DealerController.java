package com.kee.ad.controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.Dealer;
import com.kee.ad.model.DealerQueryBean;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.pojo.PageBean;
import com.kee.ad.service.DealerService;
import com.kee.ad.util.ExcelUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.MULTIANEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    public BaseResult<String> upload(MultipartHttpServletRequest multiRequest, HttpServletRequest httpServletRequest) throws Exception {

        Iterator<String> iter = multiRequest.getFileNames();
        while (iter.hasNext()){
            String fileName = iter.next();
            List<MultipartFile> files =  multiRequest.getFiles(fileName);
            boolean isExcel2003 = false;
            for (MultipartFile file : files){
                if (null != file && file.getSize() > 0){
                    String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
                    if (!fileExt.equals("xls") && !fileExt.equals("xlsx")){
                        throw new Exception("请上传Excel文档");
                    }
                    if (fileExt.equals("xls")){
                        isExcel2003 = true;
                    }
                    List<List<String>> result = ExcelUtil.readExcel(file.getInputStream(),isExcel2003);
                    if (null != result && result.size()>0){
                        dealerService.addDealerFromExcel(result);
                    }
                }
            }
        }
        return ResponseBuilder.success("success");
    }

}
