package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.HRset;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HRset
 * @Date:Created in  18:38 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:38 2018\5\11 0011
     */
    Integer addOne(HRset hRset);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:39 2018\5\11 0011
     */
    List<HRset> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    List<HRset> queryByConditions(HRset hRset);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    HRset queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    PageInfo queryByParam(HashMap<String, Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除HR信息
     *@Date: 16:44 2018\5\19 0019
     */
    Boolean removeOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:34 2018\5\19 0019
     */
    HRset modifyOne(HRset hRset);
}
