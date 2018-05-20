package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetPostlevel;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（岗位信息）
 * @Date:Created in  18:09 2018\5\10 0010
 * @Modify By:
 */
public interface IHRsetPostlevelService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条信息并返回主键
     *@Date: 18:10 2018\5\10 0010
     */
    public Integer addOne(HRsetPostlevel postlevel);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的岗位级别信息
     *@Date: 18:11 2018\5\10 0010
     */
    public List<HRsetPostlevel> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询对象
     *@Date: 10:19 2018\5\14 0014
     */
    public List<HRsetPostlevel> queryByConditions(HRsetPostlevel hRsetPostlevel);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetPostlevel queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据级别查询对象
     *@Date: 10:21 2018\5\14 0014
     */
    public HRsetPostlevel queryByPostlevel(String postlevel);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetPostlevel> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetPostlevel hRsetPostlevel);
}
