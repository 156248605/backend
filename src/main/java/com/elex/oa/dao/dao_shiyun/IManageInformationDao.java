package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.ManageInformation;
import org.apache.ibatis.annotations.Mapper;

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
    public ManageInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:保存人事信息的管理信息并返回主键
     *@Date: 16:01 2018\4\11 0011
     */
    public Integer insertOne(ManageInformation manageInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改管理信息
     *@Date: 16:05 2018\4\12 0012
     */
    public void updateOne(ManageInformation manageInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除管理信息
     *@Date: 15:10 2018\8\20 0020
     */
    public void deleteById(Integer id);
}
