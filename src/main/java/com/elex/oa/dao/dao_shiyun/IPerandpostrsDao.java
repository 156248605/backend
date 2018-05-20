package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
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
    public List<PerAndPostRs> selectPostidsByPerid(PerAndPostRs perAndPostRs);

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
}
