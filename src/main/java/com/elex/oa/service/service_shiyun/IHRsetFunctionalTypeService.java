package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetFunctionalType;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:HR设置（职能类型）接口
 * @Date:Created in  16:59 2018\5\10 0010
 * @Modify By:
 */
public interface IHRsetFunctionalTypeService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条职能并返回主键
     *@Date: 17:00 2018\5\10 0010
     */
    ;public Integer addOne(HRsetFunctionalType depFunctionalType);

    /**
     *@Author:ShiYun;
     *@Description:查询所有职能信息
     *@Date: 17:01 2018\5\10 0010
     */
    public List<HRsetFunctionalType> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询职能信息
     *@Date: 9:31 2018\5\14 0014
     */
    public List<HRsetFunctionalType> queryByCondtions(HRsetFunctionalType hRsetFunctionalType);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询对象
     *@Date: 10:17 2018\5\14 0014
     */
    public HRsetFunctionalType queryById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据职能类型查询对象
     *@Date: 10:18 2018\5\14 0014
     */
    public HRsetFunctionalType queryByFuctionaltype(String functionaltype);

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:35 2018\5\19 0019
     */
    public PageInfo<HRsetFunctionalType> queryByParam(HashMap<String,Object> paramMap);

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
    public void modifyOne(HRsetFunctionalType hRsetFunctionalType);
}
