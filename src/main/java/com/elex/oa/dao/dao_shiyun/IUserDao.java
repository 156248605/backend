package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.entity_shiyun.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:ShiYun;
 * @Description:用户表(持久层)
 * @Date:Created in  13:40 2018\3\16 0016
 * @Modify By:
 */
@Mapper
public interface IUserDao extends BaseDao<User> {
    /**
     *@Author:ShiYun;
     *@Description:添加用户数据返回主键
     *@Date: 20:14 2018\4\10 0010
     */
    public Integer insertOne(User user);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询用户数据
     *@Date: 10:19 2018\4\17 0017
     */
    public User selectById(Integer id);
}
