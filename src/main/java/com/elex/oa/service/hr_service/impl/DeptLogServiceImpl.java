package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IDeptLogDao;
import com.elex.oa.entity.hr_entity.department.DeptLog;
import com.elex.oa.service.hr_service.IDeptLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:部门日志信息（业务层）
 * @Date:Created in  16:10 2018\5\2 0002
 * @Modify By:
 */
@Service
public class DeptLogServiceImpl implements IDeptLogService {
    @Autowired
    IDeptLogDao iDeptLogDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条日志
     *@Date: 16:12 2018\5\2 0002
     */
    @Override
    public Integer addOne(DeptLog deptLog) {
        iDeptLogDao.insertOne(deptLog);
        return deptLog.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门日志
     *@Date: 16:14 2018\5\2 0002
     */
    @Override
    public List<DeptLog> queryAllDeptLogs() {
        return iDeptLogDao.selectAllDeptLog();
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志信息（分页）
     *@Date: 17:31 2018\5\2 0002
     */
    @Override
    public PageInfo<DeptLog> queryByConditions(Map<String, Object> paramMap) {
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        DeptLog deptLog = (DeptLog)paramMap.get("entity");
        List<DeptLog> deptLogs = iDeptLogDao.selectByConditions(deptLog);

        return new PageInfo<>(deptLogs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:57 2018\5\24 0024
     */
    @Override
    public void removeOne(Integer id) {
        iDeptLogDao.deleteOne(id);
    }
}
