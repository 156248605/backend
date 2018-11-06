package com.elex.oa.dao.hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.hr_entity.ChangeInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:变更信息（持久层）
 * @Date:Created in  18:39 2018\4\12 0012
 * @Modify By:
 */
@Mapper
public interface IChangeInformaionDao extends BaseDao<ChangeInformation> {
    /**
     *@Author:ShiYun;
     *@Description:添加变更信息并返回主键
     *@Date: 18:40 2018\4\12 0012
     */
    public Integer insertOne(ChangeInformation changeInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询变更信息
     *@Date: 14:24 2018\4\13 0013
     */
    public List<ChangeInformation> selectByConditions(ChangeInformation changeInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 10:39 2018\5\25 0025
     */
    public List<ChangeInformation> selectAll();

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除信息
     *@Date: 10:40 2018\5\25 0025
     */
    public void deleteOne(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据userid查询变更信息
     *@Date: 15:45 2018\8\20 0020
     */
    public List<ChangeInformation> selectByUserid(Integer changeduserid);
}
