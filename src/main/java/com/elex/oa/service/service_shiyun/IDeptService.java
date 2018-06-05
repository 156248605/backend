package com.elex.oa.service.service_shiyun;


import com.elex.oa.entity.entity_shiyun.Dept;

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

    //更具部门ID查询部门对象
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
}
