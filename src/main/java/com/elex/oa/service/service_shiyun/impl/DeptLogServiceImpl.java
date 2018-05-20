package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IDeptLogDao;
import com.elex.oa.entity.entity_shiyun.DeptLog;
import com.elex.oa.service.service_shiyun.IDeptLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门日志信息（业务层）
 * @Date:Created in  16:10 2018\5\2 0002
 * @Modify By:
 */
@Service
public class DeptLogServiceImpl implements IDeptLogService {
    @Autowired
    private IDeptLogDao iDeptLogDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条日志
     *@Date: 16:12 2018\5\2 0002
     */
    @Override
    public Integer addOne(DeptLog deptLog) {
        Integer integer = iDeptLogDao.insertOne(deptLog);
        return deptLog.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门日志
     *@Date: 16:14 2018\5\2 0002
     */
    @Override
    public List<DeptLog> queryAllDeptLogs() {
        List<DeptLog> deptLogs = iDeptLogDao.selectAllDeptLog();
        return deptLogs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志信息（分页）
     *@Date: 17:31 2018\5\2 0002
     */
    @Override
    public PageInfo<DeptLog> queryByConditions(HashMap<String, Object> paramMap) {
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        DeptLog deptLog = (DeptLog)paramMap.get("entity");
        List<DeptLog> deptLogs = iDeptLogDao.selectByConditions(deptLog);

        PageInfo<DeptLog> deptLogPageInfo = new PageInfo<>(deptLogs);
        return deptLogPageInfo;
    }
}
