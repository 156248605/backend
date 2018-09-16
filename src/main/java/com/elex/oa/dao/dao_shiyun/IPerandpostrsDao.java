package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;

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
     *@Description:根据人事ID查询其占用哪些岗位IDs
     *@Date: 20:21 2018\5\12 0012
     */
    public List<PerAndPostRs> selectPostidsByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:添加一条关系信息
     *@Date: 20:27 2018\5\12 0012
     */
    public void insertOne(PerAndPostRs perAndPostRs);

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:05 2018\5\18 0018
     */
    public void deleteByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:根据postid删除信息
     *@Date: 9:56 2018\6\21 0021
     */
    public void deleteByPostid(Integer postid);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 15:04 2018\5\22 0022
     */
    public List<PerAndPostRs> selectByConditions(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 15:04 2018\5\22 0022
     */
    public List<PerAndPostRs> selectByConditions2(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据postid查询perids
     *@Date: 15:56 2018\8\21 0021
     */
    public List<PerAndPostRs> selectPeridsByPostid(Integer postid);
}
