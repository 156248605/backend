package com.elex.oa.util.user;

import com.elex.oa.dao.user.UserDao;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Component
public class UserUtil {

    @Resource
    UserDao userDao;

    // 根据账号ID获得员工号
    public String queryEmployeenumberByUserId(String userId) {
        return userDao.queryEmployeenumberByUserId(userId);
    }

    // 根据员工号获得账号ID
    public String queryUserIdByEmployeeNumber(String employeeNumber) {
        return userDao.queryUserIdByEmployeeNumber(employeeNumber);
    }

    // 根据员工号查询员工姓名
    public String queryUserNameByEmployeeNumber(String employeenumber){
        return userDao.queryUserNameByEmployeeNumber(employeenumber);
    }
}
