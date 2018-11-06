package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.HRsetPosttitle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职称
 * @Date:Created in  11:22 2018\5\12 0012
 * @Modify By:
 */
@Mapper
public interface IHRsetPosttitleDao  {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:23 2018\5\12 0012
     */
    public Integer insertOne(HRsetPosttitle hRsetPosttitle);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 11:23 2018\5\12 0012
     */
    public List<HRsetPosttitle> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetPosttitle> selectByConditions(HRsetPosttitle hRsetPosttitle);

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
    public void updateOne(HRsetPosttitle hRsetPosttitle);
}
