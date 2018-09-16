package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.service_shiyun.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:ShiYun;
 * @Description:用户（业务层）
 * @Date:Created in  13:33 2018\3\16 0016
 * @Modify By:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
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
}