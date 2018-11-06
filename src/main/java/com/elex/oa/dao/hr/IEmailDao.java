package com.elex.oa.dao.hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.hr_entity.Email;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description;电子邮件数据查询接口
 * @Date:Create in 2018/3/1 16:50
 * @Modify By:
 */
@Mapper
public interface IEmailDao extends BaseDao<Email> {
    Integer insertOne(Email email);
    //收件箱
    List<Email> selectEmailsByCondition(Email email);
}
