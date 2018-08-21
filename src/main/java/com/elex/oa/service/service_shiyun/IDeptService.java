package com.elex.oa.service.service_shiyun;


import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.entity_shiyun.HRManageCard;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门信息（接口）
 * @Date:Created in  13:29 2018\3\16 0016
 * @Modify By:
 */
public interface IDeptService {

    //根据部门名称查询部门对象
    Dept queryOneDepByDepname(String depname);

    /**
     *@Author:ShiYun;
     *@Description:根据部门code查询部门信息
     *@Date: 10:19 2018\7\16 0016
     */
    Dept queryOneByDepcode(String depcode);

    /**
     *@Author:ShiYun;
     *@Description:根据部门code模糊查询部门信息
     *@Date: 17:06 2018\8\14 0014
     */
    List<Dept> queryDeptsByDepcode(String depcode);

    //根据部门ID查询部门对象
    Dept queryOneDepByDepid(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门信息
     *@Date: 11:06 2018\4\11 0011
     */
    public List<Dept> queryAllDepts();

    /**
     *@Author:ShiYun;
     *@Description:根据上级部门ID查询部门的信息
     *@Date: 10:50 2018\4\16 0016
     */
    public List<Dept> queryByParentId(Integer parentid);

    /**
     *@Author:ShiYun;
     *@Description:添加部门信息并返回主键
     *@Date: 10:59 2018\4\23 0023
     */
    public Integer addOne(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息
     *@Date: 9:56 2018\5\2 0002
     */
    public void modifyOne(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:根据部门id删除部门信息
     *@Date: 14:06 2018\5\2 0002
     */
    public void removeOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:删除用户时修改部门信息
     *@Date: 15:24 2018\5\10 0010
     */
    public void modifyOne(Integer userid);

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息时，将在其他部门信息的相应职位修改掉
     *@Date: 10:34 2018\6\20 0020
     */
    public void modifyByDeptidAndOtherinformation(Dept dept);

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID获得部门名称、部门人数、总人数
     *@Date: 15:25 2018\6\1 0001
     */
    public HRManageCard getParamMap1(Integer depid);

    /**
     *@Author:ShiYun;
     *@Description:人事看板信息
     *@Date: 13:39 2018\6\28 0028
     */
    public Object getHRManageCard(String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得总人数(edate时间点的在职总人数)
     *@Date: 9:13 2018\8\15 0015
     */
    public Object getHRManageCard2(Integer rows,Integer page,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得入职总人数(edate时间点的入职总人数)
     *@Date: 9:15 2018\8\15 0015
     */
    public Object getHRManageCard3(Integer rows,Integer page,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得离职总人数(edate时间点的离职总人数)
     *@Date: 9:17 2018\8\15 0015
     */
    public Object getHRManageCard4(Integer rows,Integer page,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 9:17 2018\8\15 0015
     */
    public Object getHRManageCard5(Integer rows,Integer page,Integer depid,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 9:17 2018\8\15 0015
     */
    public Object getHRManageCard6(Integer rows,Integer page,Integer depid,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 9:17 2018\8\15 0015
     */
    public Object getHRManageCard7(Integer rows,Integer page,Integer depid,String sdate,String edate);

    /**
     *@Author:ShiYun;
     *@Description:forGXF（根据姓名查询相应的公司名称）
     *@Date: 17:23 2018\8\14 0014
     */
    public String queryByTruename(String truename);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的一级公司和二级公司
     *@Date: 10:57 2018\8\21 0021
     */
    public List<Dept> queryAllCompany1and2();

    /**
     *@Author:ShiYun;
     *@Description:根据公司名称查询公司的部门(不包括公司)
     *@Date: 11:21 2018\8\21 0021
     */
    public List<Dept> queryByCompanyname(String companyname);
}
