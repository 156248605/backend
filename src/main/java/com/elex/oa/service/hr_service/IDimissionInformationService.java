package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.DimissionInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformationExport;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职信息（接口）
 * @Date:Created in  16:48 2018\4\16 0016
 * @Modify By:
 */
public interface IDimissionInformationService extends BaseService<DimissionInformation> {
    /**
     *@Author:ShiYun;
     *@Description:添加离职信息并返回主键
     *@Date: 16:48 2018\4\16 0016
     */
    Integer addOne(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息(分页)
     *@Date: 18:41 2018\4\16 0016
     */
    PageInfo<DimissionInformation> queryByCondition(HashMap<String,Object> paramMap);

    List<DimissionInformation> queryByDimission(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid查询离职信息
     *@Date: 13:39 2018\4\17 0017
     */
    DimissionInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid修改离职信息
     *@Date: 13:48 2018\4\17 0017
     */
    Object modifyOne(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的离职信息
     *@Date: 18:30 2018\5\29 0029
     */
    List<DimissionInformation> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除离职信息
     *@Date: 13:48 2018\5\30 0030
     */
    void removeOne(Integer id);

    List<PersonalInformationExport> queryAllDimissionInformations();
}
