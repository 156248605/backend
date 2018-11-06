package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.HRsetSalarystandard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:薪资标准
 * @Date:Created in  18:35 2018\5\11 0011
 * @Modify By:
 */
@Mapper
public interface IHRsetSalarystandardDao {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:35 2018\5\11 0011
     */
    public Integer insertOne(HRsetSalarystandard hRsetSalarystandard);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:36 2018\5\11 0011
     */
    public List<HRsetSalarystandard> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 10:10 2018\5\14 0014
     */
    public List<HRsetSalarystandard> selectByConditions(HRsetSalarystandard hRsetSalarystandard);

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
    public void updateOne(HRsetSalarystandard hRsetSalarystandard);
}
