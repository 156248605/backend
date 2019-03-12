package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IUserService;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:用户（业务层）
 * @Date:Created in  13:33 2018\3\16 0016
 * @Modify By:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Resource
    IUserDao iUserDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer saveOne(User user) {
        iUserDao.insertOne(user);
        return user.getId();
    }

    @Override
    public void removeOne(Integer id) {
        iUserDao.deleteById(id);
    }

    @Override
    public User queryByTruename(String truename) {
        return iUserDao.selectByTruename(truename);
    }

    @Override
    public User queryByUsername(String username) {
        return iUserDao.selectByUsername(username);
    }

    @Override
    public List<Map> queryAllServings() {
        return iUserDao.queryAllServings();
    }

    @Override
    public User queryServingUserByUserid(Integer userid) {
        return iUserDao.selectServingUserByUserid(userid);
    }

    @Override
    public void removeAllAdmin() {
        iUserDao.deleteAllAdmin();
    }

    @Override
    public Boolean modifyUser(User user) {
        if(user==null || user.getId()==null)return false;
        try {
            iUserDao.updateUser(user);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return false;
        }
        return true;
    }

    @Override
    public List<User> selectAllOrderByDictionary() {
        return iUserDao.selectAllEmployeeON();
    }

    @Override
    public List<User> getUserListByPostname(String postname) {
        return iUserDao.selectUserListByPostname(postname);
    }

    @Override
    public Object queryEmployeenumberByUsernameON(String username) {
        if(StringUtils.isBlank(username))return RespUtil.response("500","登录ID不能为空",null);
        User user = iUserDao.selectUserByUsernameON(username);
        if(null==user)return RespUtil.response("500","员工不存在或离职",user);
        return RespUtil.response("200","请求成功",user.getEmployeenumber());
    }


}
