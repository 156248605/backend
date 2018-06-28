package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.DeptLog;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  17:03 2018\6\22 0022
 * @Modify By:
 */
public interface IDeptLogServiceTest<T> {
    /**
     *@Author:ShiYun;
     *@Description:添加部门信息
     *@Date: 16:06 2018\5\2 0002
     */
    public Integer addOne(DeptLog deptLog);

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门日志信息
     *@Date: 16:08 2018\5\2 0002
     */
    public List<DeptLog> queryAllDeptLogs();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志（分页）
     *@Date: 17:29 2018\5\2 0002
     */
    public PageInfo<DeptLog> queryByConditions(HashMap<String, Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:56 2018\5\24 0024
     */
    public void removeOne(Integer id);
}
