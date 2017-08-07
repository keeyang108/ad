package com.kee.ad
        .controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.model.Subject;
import com.kee.ad.model.SubjectQueryBean;
import com.kee.ad.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public BaseResult<Boolean> addSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.addSubject(subject));
    }

    @PutMapping("/update")
    public BaseResult<Boolean> updateSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.updateSubjectByPKSelective(subject));
    }

    @GetMapping("/view")
    public BaseResult<Subject> viewSubjectById(Integer id) {
        return ResponseBuilder.success(subjectService.selectByPk(id));
    }

    @DeleteMapping("/del")
    public BaseResult<Boolean> deleteByPK(Integer id) {
        return ResponseBuilder.success(subjectService.deleteByPK(id));
    }


    @GetMapping("/list")
    public BaseResult<List<Subject>> listSubject(SubjectQueryBean queryBean) {
        return ResponseBuilder.success(subjectService.listSubject(queryBean));
    }

}
