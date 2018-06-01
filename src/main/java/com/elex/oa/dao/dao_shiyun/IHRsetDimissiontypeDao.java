package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetDimissiontype;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职类型
 * @Date:Created in  18:35 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetDimissiontypeDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:35 2018\5\11 0011
     */
    public Integer insertOne(HRsetDimissiontype hRsetDimissiontype);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:36 2018\5\11 0011
     */
    public List<HRsetDimissiontype> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:39 2018\6\1 0001
     */
    public HRsetDimissiontype selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR信息查询信息
     *@Date: 10:39 2018\6\1 0001
     */
    public HRsetDimissiontype selectByHR(String dimissiontype);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetDimissiontype> selectByConditions(HRsetDimissiontype hRsetDimissiontype);

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
    public void updateOne(HRsetDimissiontype hRsetDimissiontype);
}
