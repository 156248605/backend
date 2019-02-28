package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IPostLogDao;
import com.elex.oa.entity.hr_entity.post.PostLog;
import com.elex.oa.service.hr_service.IPostLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:岗位日志信息（业务层）
 * @Date:Created in  9:28 2018\5\3 0003
 * @Modify By:
 */
@Service
public class PostLogServiceImpl implements IPostLogService {
    @Autowired
    private IPostLogDao iPostLogDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条日志并返回主键
     *@Date: 9:29 2018\5\3 0003
     */
    @Override
    public Integer addOne(PostLog postLog) {
        Integer integer = iPostLogDao.insertOne(postLog);
        return postLog.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有日志
     *@Date: 9:29 2018\5\3 0003
     */
    @Override
    public List<PostLog> queryAllPostLogs() {
        List<PostLog> postLogs = iPostLogDao.selectAllPostLog();
        return postLogs;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询日志（分页）
     *@Date: 9:30 2018\5\3 0003
     */
    @Override
    public PageInfo<PostLog> queryByConditions(HashMap<String, Object> paramMap) {
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        PostLog postLog = (PostLog)paramMap.get("entity");
        List<PostLog> postLogs = iPostLogDao.selectByConditions(postLog);

        PageInfo<PostLog> postLogPageInfo = new PageInfo<PostLog>(postLogs);
        return postLogPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除信息
     *@Date: 16:31 2018\5\24 0024
     */
    @Override
    public void removeOne(Integer id) {
        iPostLogDao.deleteOne(id);
    }
}
