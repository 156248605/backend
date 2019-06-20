package com.elex.oa.dao.user;

import afu.org.checkerframework.checker.oigj.qual.O;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserDao {

    // 根据登录ID获得员工号
    String queryEmployeenumberByUserId(String userId);

    // 根据员工号获得账号ID
    String queryUserIdByEmployeeNumber(String employeeNumber);

    // 根据员工号查询员工姓名
    String queryUserNameByEmployeeNumber(String employeenumber);

}
