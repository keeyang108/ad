package com.kee.ad
        .controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.model.Subject;
import com.kee.ad.model.SubjectQueryBean;
import com.kee.ad.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author KeeYang on 2017/7/27.
 * @Description :
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResult<Boolean> addSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.addSubject(subject));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResult<Boolean> updateSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.updateSubjectByPKSelective(subject));
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public BaseResult<Subject> viewSubjectById(Integer id) {
        return ResponseBuilder.success(subjectService.selectByPk(id));
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public BaseResult<Boolean> deleteByPK(Integer id) {
        return ResponseBuilder.success(subjectService.deleteByPK(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public BaseResult<List<Subject>> listSubject(@RequestBody SubjectQueryBean queryBean) {
        return ResponseBuilder.success(subjectService.listSubject(queryBean));
    }

}
