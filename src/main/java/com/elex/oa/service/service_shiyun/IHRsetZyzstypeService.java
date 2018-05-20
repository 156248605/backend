package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetZyzstype;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:职业证书类型
 * @Date:Created in  11:33 2018\5\12 0012
 * @Modify By:
 */
public interface IHRsetZyzstypeService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:33 2018\5\12 0012
     */
    public Integer addOne(HRsetZyzstype hRsetZyzstype);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息部
     *@Date: 16:59 2018\5\14 0014
     */
    public List<HRsetZyzstype> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetZyzstype> queryByConditions(HRsetZyzstype hRsetByyx);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZyzstype queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetZyzstype queryByZyzstype(String zyzstype);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetZyzstype> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetZyzstype hRsetZyzstype);
}
