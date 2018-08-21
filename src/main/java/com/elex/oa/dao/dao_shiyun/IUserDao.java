package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.entity_shiyun.ContractInformation;
import com.elex.oa.entity.entity_shiyun.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     *@Author:ShiYun;
     *@Description:根据username查询用户数据
     *@Date: 11:47 2018\6\1 0001
     */
    public User selectByUsername(String username);

    /**
     *@Author:ShiYun;
     *@Description:删除信息
     *@Date: 14:47 2018\5\10 0010
     */
    public void deleteById(Integer id);//直接删除信息
    public void deleteDimissionById(Integer id);//删除离职信息(state=1)
    public void addDimissionById(Integer id);//删除离职信息(state=0)

    /**
     *@Author:ShiYun;
     *@Description:根据姓名查询用户
     *@Date: 16:48 2018\5\25 0025
     */
    public User selectByTruename(String truename);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询用户
     *@Date: 16:49 2018\5\25 0025
     */
    public List<User> selectByConditions(ContractInformation contractInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的在职人员
     *@Date: 9:59 2018\8\21 0021
     */
    public List<User> selectAllServings();
}
