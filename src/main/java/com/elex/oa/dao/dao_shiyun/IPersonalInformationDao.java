package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.DimissionInformation;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事信息查询
 * @Date:Created in  10:26 2018\4\8 0008
 * @Modify By:
 */
@Mapper
public interface IPersonalInformationDao{
    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息
     *@Date: 18:07 2018\4\8 0008
     */
    public List<PersonalInformation> selectByConditions(PersonalInformation personalInformation);
    public List<PersonalInformation> selectByConditions2(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息
     *@Date: 18:08 2018\4\8 0008
     */
    public PersonalInformation selectById(Integer id);//不包括离职的
    public PersonalInformation selectById2(Integer id);//包括离职的

    /**
     *@Author:ShiYun;
     *@Description:根据员工ID查询人事信息
     *@Date: 11:03 2018\4\9 0009
     */
    public PersonalInformation selectByUserid(Integer userid);

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询人事信息
     *@Date: 16:18 2018\5\28 0028
     */
    public List<PersonalInformation> selectByDepid(Integer depid);//查当前时间节点的部门在职人数
    public List<PersonalInformation> selectByDepid2(@Param("depid")Integer depid, @Param("sdate")String sdate, @Param("edate")String edate);//查在时间节点edate前入职的部门人数(在职+离职)
    public List<PersonalInformation> selectByDepid3(@Param("depid")Integer depid,@Param("sdate")String sdate,@Param("edate")String edate);//查在时间节点edate前离职的部门人数(离职)

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的主要信息并返回主键
     *@Date: 18:45 2018\4\10 0010
     */
    public Integer insertOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的主要信息
     *@Date: 16:14 2018\4\11 0011
     */
    public void updateOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有人事信息（不分页）
     *@Date: 17:24 2018\4\18 0018
     */
    public List<PersonalInformation> selectAll();//查在当前时间节点的所有人(在职+离职)
    public List<PersonalInformation> selectAll2(@Param("sdate")String sdate,@Param("edate")String edate);//查在时间点edate前入职的人员(在职+离职)
    public List<PersonalInformation> selectAll3(@Param("sdate")String sdate,@Param("edate")String edate);//查在时间点edate前离职的人员(离职)

    /**
     *@Author:ShiYun;
     *@Description:删除部门前先将人员的部门信息清空为NULL
     *@Date: 17:03 2018\6\20 0020
     */
    public void updateDeptinformation(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据员工号查询员工
     *@Date: 9:59 2018\8\9 0009
     */
    public List<PersonalInformation> selectByEmployeenumber(String employeenumber);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除人事主体信息
     *@Date: 16:08 2018\8\20 0020
     */
    public void deleteById(Integer id);

    void deleteAll();
}
