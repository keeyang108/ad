package com.kee.ad.service.impl;

import com.kee.ad.dao.SubjectDao;
import com.kee.ad.model.Subject;
import com.kee.ad.model.SubjectExample;
import com.kee.ad.model.SubjectQueryBean;
import com.kee.ad.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KeeYang on 2017/7/28.
 * @Description :
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectMapper;

    public boolean addSubject(Subject subject) {
        return subjectMapper.insertSelective(subject) > 0;
    }

    public boolean updateSubjectByPKSelective(Subject subject) {
        return subjectMapper.updateByPrimaryKeySelective(subject) > 0;
    }

    public List<Subject> listSubject(SubjectQueryBean queryBean) {
        SubjectExample example = new SubjectExample();
        SubjectExample.Criteria criteria = example.createCriteria();
        if (null != queryBean) {
            if (null != queryBean.getBeginDate()) {
                criteria.andCreateTimeGreaterThanOrEqualTo(queryBean.getBeginDate());
            }
            if (null != queryBean.getEndDate()) {
                criteria.andCreateTimeLessThan(queryBean.getEndDate());
            }
            if (null != queryBean.getStatus()) {
                criteria.andStatusEqualTo(queryBean.getStatus().byteValue());
            }
            if (null != queryBean.getSubjectName()) {
                criteria.andSubjectNameLike(queryBean.getSubjectName());
            }
        }
        return subjectMapper.selectByExample(example);
    }

    public boolean deleteByPK(Integer subjectId) {
        return subjectMapper.deleteByPrimaryKey(subjectId) > 0;
    }

    public Subject selectByPk(Integer subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }
}
