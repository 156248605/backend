package com.elex.oa.service.service_shiyun;

import java.util.Date;

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
    public Object queryGzrzByTime(Date date);

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志审查的统计信息
     *@Date: 15:45 2018\8\2 0002
     */
    public Object queryGzrzByTime2(Date date);
}
