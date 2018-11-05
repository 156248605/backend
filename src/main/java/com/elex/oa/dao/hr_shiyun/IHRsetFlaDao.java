package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetFla;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:外语
 * @Date:Created in  10:38 2018\5\12 0012
 * @Modify By:
 */
@Mapper
public interface IHRsetFlaDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:38 2018\5\12 0012
     */
    public Integer insertOne(HRsetFla hRsetFla);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:39 2018\5\12 0012
     */
    public List<HRsetFla> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetFla> selectByConditions(HRsetFla hRsetFla);

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
    public void updateOne(HRsetFla hRsetFla);
}
