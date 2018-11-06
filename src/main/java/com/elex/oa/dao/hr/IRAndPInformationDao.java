package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.RAndPInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:奖惩信息（持久层）
 * @Date:Created in  20:03 2018\4\17 0017
 * @Modify By:
 */
@Mapper
public interface IRAndPInformationDao {
    /**
     *@Author:ShiYun;
     *@Description:添加奖惩信息并返回主键
     *@Date: 20:04 2018\4\17 0017
     */
    public Integer insertOne(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的奖惩信息
     *@Date: 20:55 2018\4\18 0018
     */
    public List<RAndPInformation> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询奖惩信息
     *@Date: 11:16 2018\4\19 0019
     */
    public List<RAndPInformation> selectByCondition(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询奖惩信息
     *@Date: 16:19 2018\4\19 0019
     */
    public RAndPInformation selectOneById(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID修改奖惩信息
     *@Date: 18:20 2018\4\19 0019
     */
    public void updateOne(RAndPInformation rAndPInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除奖惩信息
     *@Date: 19:41 2018\4\19 0019
     */
    public void deleteOne(Integer id);
}
