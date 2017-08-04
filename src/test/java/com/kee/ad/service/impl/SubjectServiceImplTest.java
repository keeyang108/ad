package com.kee.ad.service.impl;

import com.kee.ad.model.Subject;
import com.kee.ad.pojo.BaseSpringTest;
import com.kee.ad.service.SubjectService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author KeeYang on 2017/8/4.
 * @Description :
 */
public class SubjectServiceImplTest extends BaseSpringTest{


    @Autowired
    private SubjectService subjectService;

    private Subject subject;

    @Before
    public void setup(){
        subject = new Subject();
        subject.setSubjectName("test");
        subject.setDescription("this is a test");
        subject.setUrl("https://baidu.com");
    }

    @Test
    public void addSubject() throws Exception {
        subjectService.addSubject(subject);
    }

    @Test
    public void updateSubjectByPKSelective() throws Exception {
        subject.setId(1);
        subject.setSubjectName("update test");
        subjectService.updateSubjectByPKSelective(subject);
    }

    @Test
    public void listSubject() throws Exception {
        subjectService.listSubject(null);
    }

    @Test
    public void deleteByPK() throws Exception {
        subjectService.deleteByPK(2);
    }

    @Test
    public void selectByPk() throws Exception {
        subjectService.selectByPk(1);
    }

}
