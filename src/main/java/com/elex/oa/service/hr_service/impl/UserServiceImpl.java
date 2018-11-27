package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     *@Author:ShiYun;
     *@Description:添加用户
     *@Date: 9:05 2018\4\11 0011
     */
    @Override
    public Integer saveOne(User user) {
        /*
        * 人事添加 先传，后改，拿取工号、姓名、激活状态
        * */

        Integer userid = iUserDao.insertOne(user);
        return user.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:删除用户
     *@Date: 14:53 2018\5\10 0010
     */
    @Override
    public void removeOne(Integer id) {
        iUserDao.deleteById(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据姓名查询用户
     *@Date: 11:17 2018\5\25 0025
     */
    @Override
    public User queryByTruename(String truename) {
        User user = iUserDao.selectByTruename(truename);
        return user;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据登录ID查询用户信息
     *@Date: 10:16 2018\8\9 0009
     */
    @Override
    public User queryByUsername(String username) {
        User user = iUserDao.selectByUsername(username);
        return user;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的在职人员
     *@Date: 10:01 2018\8\21 0021
     */
    @Override
    public List<User> queryAllServings() {
        List<User> users = iUserDao.selectAllServings();
        return users;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据userid查询在职用户
     *@Date: 19:17 2018\8\21 0021
     */
    @Override
    public User queryServingUserByUserid(Integer userid) {
        User user = iUserDao.selectServingUserByUserid(userid);
        return user;
    }

    @Override
    public void removeAll_admin() {
        iUserDao.deleteAll_admin();
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
}
