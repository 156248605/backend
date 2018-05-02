package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.Email;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description;d电子邮件接口
 * @Date:Create in 2018/3/1 16:45
 * @Modify By:
 */
public interface IEmail extends BaseService<Email> {

    //保存邮件
    Integer insertOne(Email email);

    //根据条件查询所有的邮件
    PageInfo<Email> queryAllByCondition(Map<String, Object> paramMap);
}
