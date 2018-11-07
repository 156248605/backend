package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.HRsetByyx;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:毕业院校
 * @Date:Created in  18:38 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetByyxService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:38 2018\5\11 0011
     */
    Integer addOne(HRsetByyx hRsetByyx);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:39 2018\5\11 0011
     */
    List<HRsetByyx> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    List<HRsetByyx> queryByConditions(HRsetByyx hRsetByyx);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    HRsetByyx queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    HRsetByyx queryByByyx(String byyx);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    PageInfo<HRsetByyx> queryByParam(HashMap<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除HR信息
     *@Date: 16:44 2018\5\19 0019
     */
    void removeOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:34 2018\5\19 0019
     */
    void modifyOne(HRsetByyx hRsetByyx);
}
