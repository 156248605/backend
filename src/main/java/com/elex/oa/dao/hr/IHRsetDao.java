package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.hr_set.HRset;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: shiyun
 * @Description: HR设置
 * @Date  2018\11\6 0006 10:43
 **/
@Mapper
public interface IHRsetDao {

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:35 2018\5\11 0011
     */
    Integer insertOne(HRset hRset);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:36 2018\5\11 0011
     */
    List<HRset> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    List<HRset> selectByConditions(HRset hRset);

    HRset selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除HR信息
     *@Date: 16:46 2018\5\19 0019
     */
    void deleteOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID修改HR信息
     *@Date: 17:31 2018\5\19 0019
     */
    void updateOne(HRset hRset);

    List<HRset> selectByDatacodeIsNull();
}
