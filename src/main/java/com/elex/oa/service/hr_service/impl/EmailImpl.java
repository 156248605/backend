package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr_shiyun.IEmailDao;
import com.elex.oa.entity.hr_entity.Email;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IEmail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description;电子邮件实现类
 * @Date:Create in 2018/3/1 16:48
 * @Modify By:
 */
@Service
public class EmailImpl extends BaseServiceImpl<Email> implements IEmail {
    @Autowired
    private IEmailDao iEmailDao;

    @Override
    public Integer insertOne(Email email) {
        return iEmailDao.insertOne(email);
    }

    @Override
    public PageInfo<Email> queryAllByCondition(Map<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        Email email = (Email)paramMap.get("entity");
        // 收件箱
        List<Email> list = iEmailDao.selectEmailsByCondition(email);
        return new PageInfo<Email>(list);
    }
}
