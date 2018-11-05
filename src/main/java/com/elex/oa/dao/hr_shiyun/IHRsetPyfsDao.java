package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetPyfs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:培养方式
 * @Date:Created in  10:16 2018\5\12 0012
 * @Modify By:
 */
@Mapper
public interface IHRsetPyfsDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:17 2018\5\12 0012
     */
    public Integer insertOne(HRsetPyfs hRsetPyfs);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:17 2018\5\12 0012
     */
    public List<HRsetPyfs> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetPyfs> selectByConditions(HRsetPyfs hRsetPyfs);

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
    public void updateOne(HRsetPyfs hRsetPyfs);
}
