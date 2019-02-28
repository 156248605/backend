package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.personalinformation.OtherInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:ShiYun;
 * @Description:人事信息的其他信息
 * @Date:Created in  13:38 2018\4\9 0009
 * @Modify By:
 */
@Mapper
public interface IOtherInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息的其他信息
     *@Date: 13:38 2018\4\9 0009
     */
    public OtherInformation selectById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:添加其它信息并返回主键
     *@Date: 18:48 2018\4\11 0011
     */
    public Integer insertOne(OtherInformation otherInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:49 2018\4\12 0012
     */
    public void updateOne(OtherInformation otherInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除其它信息
     *@Date: 15:30 2018\8\20 0020
     */
    public void deleteById(Integer id);

    void deleteAll();
}
