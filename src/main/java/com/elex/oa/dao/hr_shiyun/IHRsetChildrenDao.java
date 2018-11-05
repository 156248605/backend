package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetChildren;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（生育）
 * @Date:Created in  17:06 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetChildrenDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:07 2018\5\11 0011
     */
    public Integer insertOne(HRsetChildren hRsetChildren);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 17:07 2018\5\11 0011
     */
    public List<HRsetChildren> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetChildren> selectByConditions(HRsetChildren hRsetChildren);

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
    public void updateOne(HRsetChildren hRsetChildren);
}
