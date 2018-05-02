package com.elex.oa.service.service_shiyun;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.entity_shiyun.DimissionInformation;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

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
    public Integer addOne(DimissionInformation dimissionInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息
     *@Date: 18:41 2018\4\16 0016
     */
    public PageInfo<DimissionInformation> queryByCondition(HashMap<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid查询离职信息
     *@Date: 13:39 2018\4\17 0017
     */
    public DimissionInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid修改离职信息
     *@Date: 13:48 2018\4\17 0017
     */
    public void modifyOne(DimissionInformation dimissionInformation);
}
