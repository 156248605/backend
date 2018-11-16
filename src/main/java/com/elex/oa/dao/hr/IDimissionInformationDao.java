package com.elex.oa.dao.hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.hr_entity.DimissionInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职信息（持久层）
 * @Date:Created in  16:28 2018\4\16 0016
 * @Modify By:
 */
@Mapper
public interface IDimissionInformationDao extends BaseDao<DimissionInformation> {
    /**
     *@Author:ShiYun;
     *@Description:添加离职信息并返回主键
     *@Date: 16:29 2018\4\16 0016
     */
     Integer insertOne(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息
     *@Date: 18:25 2018\4\16 0016
     */
     List<DimissionInformation> selectByCondition(DimissionInformation dimissionInformation);

     List<DimissionInformation> selectByDimission(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid查询离职信息
     *@Date: 13:37 2018\4\17 0017
     */
     DimissionInformation selectOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid修改离职信息
     *@Date: 13:42 2018\4\17 0017
     */
     void updateOne(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:删除离职信息
     *@Date: 13:53 2018\5\30 0030
     */
     void deleteOne(Integer id);
}
