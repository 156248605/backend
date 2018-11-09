package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.HRsetZgxl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:最高学历
 * @Date:Created in  18:00 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetZgxlDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:00 2018\5\11 0011
     */
    public Integer insertOne(HRsetZgxl hRsetZgxl);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 18:01 2018\5\11 0011
     */
    public List<HRsetZgxl> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetZgxl> selectByConditions(HRsetZgxl hRsetZgxl);

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
    public void updateOne(HRsetZgxl hRsetZgxl);
}