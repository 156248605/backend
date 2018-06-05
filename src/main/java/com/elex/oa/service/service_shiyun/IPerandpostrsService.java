package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
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
    public void addOne(PerAndPostRs perAndPostRs);

    /**
     *@Author:ShiYun;
     *@Description:根据人事ID查询岗位集合
     *@Date: 20:31 2018\5\12 0012
     */
    public List<PerAndPostRs> queryPerAndPostRsByPerid(Integer perid);

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:10 2018\5\18 0018
     */
    public void removeByPerid(Integer perid);
}
