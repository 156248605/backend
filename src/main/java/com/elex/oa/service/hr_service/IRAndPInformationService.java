package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.RAndPInformation;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:奖惩信息（接口）
 * @Date:Created in  9:09 2018\4\18 0018
 * @Modify By:
 */
public interface IRAndPInformationService {
    /**
     *@Author:ShiYun;
     *@Description:添加奖惩信息并返回主键
     *@Date: 9:10 2018\4\18 0018
     */
    public Integer addOne(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的奖惩信息（不分页）
     *@Date: 20:53 2018\4\18 0018
     */
    public List<RAndPInformation> queryAll();

    /**
     *@Author:ShiYun;
     *@Description:查询所有的奖惩名目
     *@Date: 9:29 2018\4\19 0019
     */
    public List<RAndPInformation> queryAllRAndPProject();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询奖惩信息（分页）
     *@Date: 11:26 2018\4\19 0019
     */
    public PageInfo<RAndPInformation> queryByCondition(HashMap<String,Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询奖惩信息（不分页）
     *@Date: 9:49 2018\4\20 0020
     */
    public List<RAndPInformation> queryByCondition(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询奖惩信息
     *@Date: 16:25 2018\4\19 0019
     */
    public RAndPInformation queryOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID修改奖惩信息
     *@Date: 19:06 2018\4\19 0019
     */
    public void modifyOne(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除奖惩信息
     *@Date: 19:46 2018\4\19 0019
     */
    public void removeOne(Integer id);
}
