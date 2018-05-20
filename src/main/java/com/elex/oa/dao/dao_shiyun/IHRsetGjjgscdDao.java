package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetGjjgscd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:公积金公司缴费比例
 * @Date:Created in  18:35 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetGjjgscdDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:35 2018\5\11 0011
     */
    public Integer insertOne(HRsetGjjgscd hRsetGjjgscd);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:36 2018\5\11 0011
     */
    public List<HRsetGjjgscd> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetGjjgscd> selectByConditions(HRsetGjjgscd hRsetGjjgscd);

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
    public void updateOne(HRsetGjjgscd hRsetGjjgscd);
}
