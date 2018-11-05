package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.MailAccount;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface IMailAccount extends BaseService<MailAccount> {

/*根据条件查询所有邮箱账号*/
public PageInfo<MailAccount> queryAll(Map<String,Object> paramMap);
}
