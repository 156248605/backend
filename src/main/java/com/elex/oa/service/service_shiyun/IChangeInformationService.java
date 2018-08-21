package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.ChangeInformation;
import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

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
     *@Description:变更信息的列表(分页)
     *@Date: 11:49 2018\4\13 0013
     */
    public PageInfo<ChangeInformation> queryAll(HashMap<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息(不分页)
     *@Date: 10:41 2018\5\25 0025
     */
    public List<ChangeInformation> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:42 2018\5\25 0025
     */
    public void removeOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据userid查询变更信息
     *@Date: 15:47 2018\8\20 0020
     */
    public List<ChangeInformation> queryByUserid(Integer changeduserid);
}
