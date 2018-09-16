package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.entity.entity_shiyun.DeptLog;
import com.elex.oa.service.service_shiyun.IDeptLogService;
import com.elex.oa.service.service_shiyun.IDeptLogServiceTest;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  16:57 2018\6\22 0022
 * @Modify By:
 */
public class DeptLogServiceImplTest implements IDeptLogServiceTest {

    @Override
    public Integer addOne(DeptLog deptLog) {
        return null;
    }

    @Override
    public List<DeptLog> queryAllDeptLogs() {
        return null;
    }

    @Override
    public void removeOne(Integer id) {

    }

    @Override
    public PageInfo<DeptLog> queryByConditions(HashMap paramMap) {
        return null;
    }

}
