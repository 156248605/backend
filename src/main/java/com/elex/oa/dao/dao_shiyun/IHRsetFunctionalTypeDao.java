package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetFunctionalType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（职能类型）
 * @Date:Created in  16:52 2018\5\10 0010
 * @Modify By:
 */
@Mapper
public interface IHRsetFunctionalTypeDao {
    /**
     *@Author:ShiYun;
     *@Description:查询所有的职能类型
     *@Date: 16:53 2018\5\10 0010
     */
    public List<HRsetFunctionalType> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:添加一条职能类型信息并返回主键
     *@Date: 16:57 2018\5\10 0010
     */
    public Integer insertOne(HRsetFunctionalType depFunctionalType);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询职能类型
     *@Date: 9:26 2018\5\14 0014
     */
    public List<HRsetFunctionalType> selectByConditions(HRsetFunctionalType hRsetFunctionalType);

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
    public void updateOne(HRsetFunctionalType hRsetFunctionalType);
}
