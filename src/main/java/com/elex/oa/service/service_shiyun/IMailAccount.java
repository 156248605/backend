package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.MailAccount;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMailAccount extends BaseService<MailAccount> {

/*根据条件查询所有邮箱账号*/
public PageInfo<MailAccount> queryAll(Map<String,Object> paramMap);
}
