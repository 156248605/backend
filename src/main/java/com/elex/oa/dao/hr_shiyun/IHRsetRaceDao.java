package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetRace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（民族）
 * @Date:Created in  16:42 2018\5\9 0009
 * @Modify By:
 */
@Mapper
public interface IHRsetRaceDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条HR信息
     *@Date: 16:43 2018\5\9 0009
     */
    public Integer insertOne(HRsetRace race);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 16:46 2018\5\9 0009
     */
    public List<HRsetRace> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetRace> selectByConditions(HRsetRace hRsetRace);

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
    public void updateOne(HRsetRace hRsetRace);
}
