package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.personalinformation.BaseInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询信息
     *@Date: 15:03 2018\5\22 0022
     */
    public List<BaseInformation> selectByConditions(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除基本信息表
     *@Date: 15:02 2018\8\20 0020
     */
    public void deleteById(Integer id);

    void deleteAll();
}
