package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.post.PostLog;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:岗位日志信息接口
 * @Date:Created in  16:06 2018\5\2 0002
 * @Modify By:
 */
public interface IPostLogService {
    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息
     *@Date: 16:06 2018\5\2 0002
     */
    public Integer addOne(PostLog postLog);

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位日志信息
     *@Date: 16:08 2018\5\2 0002
     */
    public List<PostLog> queryAllPostLogs();

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询岗位日志（分页）
     *@Date: 17:29 2018\5\2 0002
     */
    public PageInfo<PostLog> queryByConditions(Map<String, Object> paramMap);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询信息
     *@Date: 10:56 2018\5\24 0024
     */
    public void removeOne(Integer id);
}
