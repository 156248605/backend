package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetSxzy;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:所学专业
 * @Date:Created in  9:57 2018\5\12 0012
 * @Modify By:
 */
public interface IHRsetSxzyService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 9:57 2018\5\12 0012
     */
    public Integer addOne(HRsetSxzy hRsetSxzy);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 9:58 2018\5\12 0012
     */
    public List<HRsetSxzy> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetSxzy> queryByConditions(HRsetSxzy hRsetSxzy);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetSxzy queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetSxzy queryBySxzy(String sxzy);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetSxzy> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetSxzy hRsetSxzy);
}
