package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetPostlevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description: HR设置（岗位级别）
 * @Date:Created in  18:03 2018\5\10 0010
 * @Modify By:
 */
@Mapper
public interface IHRsetPostlevelDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:03 2018\5\10 0010
     */
    public Integer insertOne(HRsetPostlevel postlevel);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的岗位级别
     *@Date: 18:04 2018\5\10 0010
     */
    public List<HRsetPostlevel> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetPostlevel> selectByConditions(HRsetPostlevel hRsetPostlevel);

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
    public void updateOne(HRsetPostlevel hRsetPostlevel);
}
