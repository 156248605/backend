package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.PostLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:岗位信息变更日志（持久层）
 * @Date:Created in  15:51 2018\5\2 0002
 * @Modify By:
 */
@Mapper
public interface IPostLogDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条日志并返回主键
     *@Date: 15:52 2018\5\2 0002
     */
    public Integer insertOne(PostLog postLog);

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门日志信息
     *@Date: 16:05 2018\5\2 0002
     */
    public List<PostLog> selectAllPostLog();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志信息
     *@Date: 17:22 2018\5\2 0002
     */
    public List<PostLog> selectByConditions(PostLog postLog);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除信息
     *@Date: 10:55 2018\5\24 0024
     */
    public void deleteOne(Integer id);
}
