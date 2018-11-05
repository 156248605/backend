package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.HRsetZgxl;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:最高学历
 * @Date:Created in  18:04 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetZgxlService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:04 2018\5\11 0011
     */
    public Integer addOne(HRsetZgxl hRsetZgxl);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 18:05 2018\5\11 0011
     */
    public List<HRsetZgxl> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetZgxl> queryByConditions(HRsetZgxl hRsetZgxl);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZgxl queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZgxl queryByZgxl(String zgxl);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetZgxl> queryByParam(HashMap<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除HR信息
     *@Date: 16:44 2018\5\19 0019
     */
    public void removeOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:HR信息的修改
     *@Date: 17:34 2018\5\19 0019
     */
    public void modifyOne(HRsetZgxl hRsetZgxl);
}
