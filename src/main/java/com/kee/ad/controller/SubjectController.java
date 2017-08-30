package com.kee.ad
        .controller;

import com.kee.ad.dto.BaseResult;
import com.kee.ad.model.ResponseBuilder;
import com.kee.ad.model.Subject;
import com.kee.ad.model.SubjectQueryBean;
import com.kee.ad.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author KeeYang on 2017/7/27.
 * @Description :
 */
@Api(value = "专题管理",description = "专题管理",tags = {"专题管理"})
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("专题添加")
    @PostMapping("/add")
    public BaseResult<Boolean> addSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.addSubject(subject));
    }

    @ApiOperation("专题修改")
    @PutMapping("/update")
    public BaseResult<Boolean> updateSubject(@RequestBody Subject subject) {
        return ResponseBuilder.success(subjectService.updateSubjectByPKSelective(subject));
    }

    @ApiOperation("查看专题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "专题id",dataType = "int",paramType = "query")
    })
    @GetMapping("/view")
    public BaseResult<Subject> viewSubjectById(Integer id) {
        return ResponseBuilder.success(subjectService.selectByPk(id));
    }

    @ApiOperation("删除专题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "专题id",dataType = "int",paramType = "query")
    })
    @DeleteMapping("/del")
    public BaseResult<Boolean> deleteByPK(Integer id) {
        return ResponseBuilder.success(subjectService.deleteByPK(id));
    }

    @ApiOperation("专题列表")
    @PostMapping("/list")
    public BaseResult<List<Subject>> listSubject(@RequestBody SubjectQueryBean queryBean) {
        return ResponseBuilder.success(subjectService.listSubject(queryBean));
    }

}
