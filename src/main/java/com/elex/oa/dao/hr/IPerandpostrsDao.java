package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.PerAndPostRs;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事与岗位关系表
 * @Date:Created in  20:21 2018\5\12 0012
 * @Modify By:
 */
@Mapper
public interface IPerandpostrsDao {

    /**
     *@Author:ShiYun;
     *@Description:添加一条关系信息
     *@Date: 20:27 2018\5\12 0012
     */
    void insertOne(PerAndPostRs perAndPostRs);

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:05 2018\5\18 0018
     */
    void deleteByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:根据postid删除信息
     *@Date: 9:56 2018\6\21 0021
     */
    void deleteByPostid(Integer postid);

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid删除一条信息
     * @Date  2018\11\1 0001 15:22
     * @Param perid,postid
     * @return void
     **/
    void deleteOneByPeridAndPostid(@Param("perid")Integer perid,@Param("postid")Integer postid);

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid查询一条信息
     * @Date  2018\11\1 0001 15:25
     * @Param perid,postid
     * @return PerAndPostRs
     **/
    PerAndPostRs selectOneByPeridAndPostid(@Param("perid")Integer perid,@Param("postid")Integer postid);

    /**
     *@Author:ShiYun;
     *@Description:根据人事ID查询其占用哪些岗位IDs
     *@Date: 20:21 2018\5\12 0012
     */
    List<PerAndPostRs> selectPostidsByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:根据postid查询perids
     *@Date: 15:56 2018\8\21 0021
     */
    List<PerAndPostRs> selectPeridsByPostid(Integer postid);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 15:04 2018\5\22 0022
     */
    List<PerAndPostRs> selectByConditions(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 15:04 2018\5\22 0022
     */
    List<PerAndPostRs> selectByConditions2(PersonalInformation personalInformation);

    void deleteAll();
}
