package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门信息（持久层）
 * @Date:Created in  13:40 2018\3\16 0016
 * @Modify By:
 */
@Mapper
public interface IDeptDao{

    //更具部门名称查询部门对象
    Dept selectDeptByDeptname(String deptname);

    //更具部门ID查询部门对象
    Dept selectDeptByDepid(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门信息
     *@Date: 11:04 2018\4\11 0011
     */
    List<Dept> selectAllDept();

    /**
     *@Author:ShiYun;
     *@Description:根据上一级部门的ID查询部门信息
     *@Date: 10:47 2018\4\16 0016
     */
    List<Dept> selectByParentId(Integer parentdepid);

    /**
     *@Author:ShiYun;
     *@Description:添加部门信息并返回主键
     *@Date: 10:52 2018\4\23 0023
     */
    public Integer insertOne(Dept dept);
}
