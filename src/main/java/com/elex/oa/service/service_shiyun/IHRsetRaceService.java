package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetRace;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（民族）
 * @Date:Created in  16:47 2018\5\9 0009
 * @Modify By:
 */
public interface IHRsetRaceService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 16:48 2018\5\9 0009
     */
    public Integer addOne(HRsetRace race);

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 16:48 2018\5\9 0009
     */
    public List<HRsetRace> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetRace> queryByConditions(HRsetRace hRsetRace);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetRace queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据HR的值查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetRace queryByRace(String race);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetRace> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetRace hRsetRace);
}
