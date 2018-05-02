package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.ChangeInformation;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * @Author:ShiYun;
 * @Description:变更信息接口
 * @Date:Created in  18:50 2018\4\12 0012
 * @Modify By:
 */
public interface IChangeInformationService extends BaseService<ChangeInformation> {
    /**
     *@Author:ShiYun;
     *@Description:添加变更信息并返回主键
     *@Date: 18:50 2018\4\12 0012
     */
    public Integer addOne(ChangeInformation changeInformation);

    /**
     *@Author:ShiYun;
     *@Description:变更信息的列表
     *@Date: 11:49 2018\4\13 0013
     */
    public PageInfo<ChangeInformation> queryAll(HashMap<String,Object> paramMap);
}
