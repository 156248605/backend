package com.elex.oa.service.service_shiyun;


import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.BaseService;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:用户（接口）
 * @Date:Created in  13:29 2018\3\16 0016
 * @Modify By:
 */
public interface IUserService extends BaseService<User> {
    /**
     *@Author:ShiYun;
     *@Description:添加用户
     *@Date: 9:03 2018\4\11 0011
     */
    public Integer saveOne(User user);

    /**
     *@Author:ShiYun;
     *@Description:删除用户
     *@Date: 14:52 2018\5\10 0010
     */
    public void removeOne(Integer id);//离职信息的添加
    /*public void removeOne2(Integer id);//人事信息的删除*/

    /**
     *@Author:ShiYun;
     *@Description:根据用户姓名查询用户
     *@Date: 11:16 2018\5\25 0025
     */
    public User queryByTruename(String truename);

    /**
     *@Author:ShiYun;
     *@Description:根据登录ID查询用户信息
     *@Date: 10:15 2018\8\9 0009
     */
    public User queryByUsername(String username);
}
