package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.HRsetParentcompany;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:上家公司
 * @Date:Created in  14:42 2018\5\12 0012
 * @Modify By:
 */
public interface IHRsetParentcompanyService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 14:43 2018\5\12 0012
     */
    public Integer addOne(HRsetParentcompany hRsetParentcompany);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 14:43 2018\5\12 0012
     */
    public List<HRsetParentcompany> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetParentcompany> queryByConditions(HRsetParentcompany hRsetParentcompany);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetParentcompany queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetParentcompany queryByParentcompanyname(String parentcompanyname);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetParentcompany> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetParentcompany hRsetParentcompany);
}
