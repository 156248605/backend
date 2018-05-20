package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetZzmm;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（政治面貌）
 * @Date:Created in  17:44 2018\5\11 0011
 * @Modify By:
 */
public interface IHRsetZzmmService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:44 2018\5\11 0011
     */
    public Integer addOne(HRsetZzmm hRsetZzmm);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 17:45 2018\5\11 0011
     */
    public List<HRsetZzmm> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetZzmm> queryByConditions(HRsetZzmm hRsetByyx);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZzmm queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZzmm queryByZzmm(String zzmm);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetZzmm> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetZzmm hRsetZzmm);
}
