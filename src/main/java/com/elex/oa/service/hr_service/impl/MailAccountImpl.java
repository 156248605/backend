package com.elex.oa.service.hr_service.impl;

import com.elex.oa.entity.hr_entity.MailAccount;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IMailAccount;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MailAccountImpl extends BaseServiceImpl<MailAccount> implements IMailAccount {

    /**
     *@Author:ShiYun;
     *@Description;查询邮箱账号
     *@Date:2018/2/25 10:34
     */
    @Override
    public PageInfo<MailAccount> queryAll(Map<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        MailAccount mailAccount = (MailAccount) paramMap.get("entity");
        List<MailAccount> list = selectByCondition(mailAccount);

        return new PageInfo<MailAccount>(list);
    }
}
