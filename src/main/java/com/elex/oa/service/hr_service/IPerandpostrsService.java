package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.PerAndPostRs;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事与岗位关系表
 * @Date:Created in  20:30 2018\5\12 0012
 * @Modify By:
 */
public interface IPerandpostrsService {
    /**
     *@Author:ShiYun;
     *@Description:添加一条关系信息
     *@Date: 20:30 2018\5\12 0012
     */
    void addOne(PerAndPostRs perAndPostRs);

    /**
     *@Author:ShiYun;
     *@Description:根据人事ID查询岗位集合
     *@Date: 20:31 2018\5\12 0012
     */
    List<PerAndPostRs> queryPerAndPostRsByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:根据postid查询人员集合
     *@Date: 15:58 2018\8\21 0021
     */
    List<PerAndPostRs> queryPerAndPostRsByPostid(Integer postid);

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:10 2018\5\18 0018
     */
    void removeByPerid(Integer perid);

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid删除一条信息
     * @Date  2018\11\1 0001 15:14
     * @Param perid,postid
     * @return void
     **/
    Boolean removeOneByPeridAndPostid(Integer perid,Integer postid);

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid查询一条信息
     * @Date  2018\11\1 0001 15:16
     * @Param perid,postid
     * @return PerAndPostRs
     **/
    PerAndPostRs queryOneByPeridAndPostid(Integer perid,Integer postid);

    void removeAll();
}
