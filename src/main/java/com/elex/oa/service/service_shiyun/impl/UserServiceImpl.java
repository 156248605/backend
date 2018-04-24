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
        return userid;
    }
}
