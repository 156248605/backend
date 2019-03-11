package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.department.Dept;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:部门信息（持久层）
 * @Date:Created in  13:40 2018\3\16 0016
 * @Modify By:
 */
@Mapper
public interface IDeptDao{

    //更具部门名称查询部门对象
    List<Dept> selectDeptByDeptname(String deptname);
    List<Dept> selectDeptByLikeDeptname(String deptname);

    //查询所有的部门名称
    List<String> selectAllDepnames();

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

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息
     *@Date: 9:59 2018\5\2 0002
     */
    public void updateOne(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:删除部门信息
     *@Date: 14:03 2018\5\2 0002
     */
    public void deleteOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:删除用户信息的修改部门信息
     *@Date: 15:05 2018\5\10 0010
     */
    public void updateByDeleteUser(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门信息
     *@Date: 15:30 2018\5\10 0010
     */
    public List<Dept> selectDeptsByDept(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:根据部门编号查询部门信息
     *@Date: 17:04 2018\8\14 0014
     */
    Dept selectDeptByDeptcode(String depcode);
    Dept selectDeptByDeptcodeIgnoreState(String depcode);

    /**
     *@Author:ShiYun;
     *@Description:根据部门编号模糊查询部门信息
     *@Date: 17:05 2018\8\14 0014
     */
    public List<Dept> selectDeptByDeptcode2(String depcode);

    /**
     *@Author:ShiYun;
     *@Description:根据部门类型查询部门信息
     *@Date: 10:54 2018\8\21 0021
     */
    List<Dept> selectDeptByDeptypeid(Integer deptypeid);

    /**
     *@Author:ShiYun;
     *@Description:根据公司名称查询公司的部门
     *@Date: 11:20 2018\8\21 0021
     */
    List<Dept> selectDeptByCompanyname(String companyname);

    List<Map<String, Object>> getAllDepidAndDepnameByDepON();
    List<Map<String, Object>> getAllDepidAndDepnameByRemoveCompany();

    String getPrincipalTruenameByDepid(Integer depid);

    List<String> getAllCompanyNamesByDepON();

    Dept selectCompanynameAndDepnameByTruename(String truename);

    Dept selectCompanynameAndDepnameByUserid(Integer userid);

    Dept selectDeptByUsername(String username);

    User selectPrincipalUserByUsername(String username);

    String selectDepnameByEmployeenumber(String employeenumber);

    String selectDepnamesByTreuname(String truename);

    List<Dept> selectDeptByDeptypeValueLike(String depttypeValue);
}
