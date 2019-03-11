package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.department.DeptLog;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:部门日志信息接口
 * @Date:Created in  16:06 2018\5\2 0002
 * @Modify By:
 */
public interface IDeptLogService {
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
    public PageInfo<DeptLog> queryByConditions(Map<String, Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:56 2018\5\24 0024
     */
    public void removeOne(Integer id);
}
