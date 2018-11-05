package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetZyzsname;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职业证书名称
 * @Date:Created in  13:52 2018\5\12 0012
 * @Modify By:
 */
@Mapper
public interface IHRsetZyzsnameDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 13:52 2018\5\12 0012
     */
    public Integer insertOne(HRsetZyzsname hRsetZyzsname);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 13:53 2018\5\12 0012
     */
    public List<HRsetZyzsname> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetZyzsname> selectByConditions(HRsetZyzsname hRsetZyzsname);

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
    public void updateOne(HRsetZyzsname hRsetZyzsname);
}
