package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.DeptLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门信息变更日志（持久层）
 * @Date:Created in  15:51 2018\5\2 0002
 * @Modify By:
 */
@Mapper
public interface IDeptLogDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条日志并返回主键
     *@Date: 15:52 2018\5\2 0002
     */
    public Integer insertOne(DeptLog deptLog);

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门日志信息
     *@Date: 16:05 2018\5\2 0002
     */
    public List<DeptLog> selectAllDeptLog();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志信息
     *@Date: 17:22 2018\5\2 0002
     */
    public List<DeptLog> selectByConditions(DeptLog deptLog);
}
