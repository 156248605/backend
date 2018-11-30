package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.ManageInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事信息的管理信息
 * @Date:Created in  10:18 2018\4\9 0009
 * @Modify By:
 */
@Mapper
public interface IManageInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息管理信息
     *@Date: 10:18 2018\4\9 0009
     */
    ManageInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:保存人事信息的管理信息并返回主键
     *@Date: 16:01 2018\4\11 0011
     */
    Integer insertOne(ManageInformation manageInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改管理信息
     *@Date: 16:05 2018\4\12 0012
     */
    void updateOne(ManageInformation manageInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除管理信息
     *@Date: 15:10 2018\8\20 0020
     */
    void deleteById(Integer id);

    void deleteAll();
}
