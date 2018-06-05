package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetChildren;
import com.elex.oa.entity.entity_shiyun.HRsetFunctionalType;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（生育）
 * @Date:Created in  17:12 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetChildrenService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:12 2018\5\11 0011
     */
    public Integer addOne(HRsetChildren hRsetChildren);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 17:13 2018\5\11 0011
     */
    public List<HRsetChildren> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetChildren> queryByConditions(HRsetChildren hRsetChildren);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetChildren queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetChildren queryByChildren(String children);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetChildren> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetChildren hRsetChildren);
}
