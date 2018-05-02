package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.BaseInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:ShiYun;
 * @Description:人事信息的基本信息
 * @Date:Created in  18:20 2018\4\8 0008
 * @Modify By:
 */
@Mapper
public interface IBaseInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的基本信息
     *@Date: 18:20 2018\4\8 0008
     */
    public BaseInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的基本信息并返回主键
     *@Date: 18:34 2018\4\10 0010
     */
    public Integer insertOne(BaseInformation baseInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的基本信息
     *@Date: 14:09 2018\4\12 0012
     */
    public void updateOne(BaseInformation baseInformation);
}
