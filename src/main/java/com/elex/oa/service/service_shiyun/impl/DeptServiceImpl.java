package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.service.service_shiyun.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  13:33 2018\3\16 0016
 * @Modify By:
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    IDeptDao iDeptDao;

    @Override
    public Dept queryOneDepByDepname(String depname) {
        return iDeptDao.selectDeptByDeptname(depname);
    }

    @Override
    public Dept queryOneDepByDepid(Integer id) {
        return iDeptDao.selectDeptByDepid(id);
    }

    @Override
    public List<Dept> queryAllDepts() {
        List<Dept> depts = iDeptDao.selectAllDept();
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据上级部门ID查询部门信息
     *@Date: 10:52 2018\4\16 0016
     */
    @Override
    public List<Dept> queryByParentId(Integer parentid) {
        List<Dept> depts = iDeptDao.selectByParentId(parentid);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加部门信息并返回主键
     *@Date: 11:00 2018\4\23 0023
     */
    @Override
    public Integer addOne(Dept dept) {
        Integer integer = iDeptDao.insertOne(dept);
        return dept.getId();
    }
}
