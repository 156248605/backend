package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.Lysqd;

import java.util.Date;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:工作日志
 * @Date:Created in  15:42 2018\8\2 0002
 * @Modify By:
 */
public interface IGzrzService {
    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志填写的统计信息
     *@Date: 15:43 2018\8\2 0002
     */
    Object queryGzrzByTime(Date date);

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志审查的统计信息
     *@Date: 15:45 2018\8\2 0002
     */
    Object queryGzrzByTime2(Date date);

    /**
     *@Author:ShiYun;
     *@Description:查询录用审批单通过的信息
     *@Date: 14:58 2018\9\28 0028
     */
    List<Lysqd> queryLyspd();

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询一条录用申请单
     *@Date: 17:09 2018\9\28 0028
     */
    Lysqd queryLysqdById(String id);

    /**
     *@Author:ShiYun;
     *@Description:修改录用申请单
     *@Date: 17:31 2018\9\28 0028
     */
    void modifyLysqdById(String id);
}
