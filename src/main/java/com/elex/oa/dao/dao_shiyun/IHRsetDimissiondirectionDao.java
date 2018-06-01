package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetDimissiondirection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职去向
 * @Date:Created in  18:35 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetDimissiondirectionDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:35 2018\5\11 0011
     */
    public Integer insertOne(HRsetDimissiondirection hRsetDimissiondirection);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:36 2018\5\11 0011
     */
    public List<HRsetDimissiondirection> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetDimissiondirection> selectByConditions(HRsetDimissiondirection hRsetDimissiondirection);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:42 2018\6\1 0001
     */
    public HRsetDimissiondirection selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR查询信息
     *@Date: 10:43 2018\6\1 0001
     */
    public HRsetDimissiondirection slectByHR(String dimissiondirection);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除HR信息
     *@Date: 16:46 2018\5\19 0019
     */
    public void deleteOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID修改HR信息
     *@Date: 17:31 2018\5\19 0019
     */
    public void updateOne(HRsetDimissiondirection hRsetDimissiondirection);
}
