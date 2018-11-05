package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetZzmm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（政治面貌）
 * @Date:Created in  17:39 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetZzmmDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:39 2018\5\11 0011
     */
    public Integer insertOne(HRsetZzmm hRsetZzmm);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 17:40 2018\5\11 0011
     */
    public List<HRsetZzmm> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetZzmm> selectByConditions(HRsetZzmm hRsetZzmm);

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
    public void updateOne(HRsetZzmm hRsetZzmm);
}
