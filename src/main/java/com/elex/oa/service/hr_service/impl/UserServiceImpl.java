package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IUserService;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public Integer saveOne(User user) {
        /*
        * 人事添加 先传，后改，拿取工号、姓名、激活状态
        * */

        Integer userid = iUserDao.insertOne(user);
        return user.getId();
    }

    @Override
    public void removeOne(Integer id) {
        iUserDao.deleteById(id);
    }

    @Override
    public User queryByTruename(String truename) {
        User user = iUserDao.selectByTruename(truename);
        return user;
    }

    @Override
    public User queryByUsername(String username) {
        User user = iUserDao.selectByUsername(username);
        return user;
    }

    @Override
    public List<Map> queryAllServings() {
        List<Map> users = iUserDao.queryAllServings();
        return users;
    }

    @Override
    public User queryServingUserByUserid(Integer userid) {
        User user = iUserDao.selectServingUserByUserid(userid);
        return user;
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
            e.printStackTrace();
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
