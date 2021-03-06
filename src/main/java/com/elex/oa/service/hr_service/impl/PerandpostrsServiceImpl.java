package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IPerandpostrsDao;
import com.elex.oa.entity.hr_entity.post.PerAndPostRs;
import com.elex.oa.service.hr_service.IPerandpostrsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事与岗位关系表
 * @Date:Created in  20:33 2018\5\12 0012
 * @Modify By:
 */
@Service
public class PerandpostrsServiceImpl implements IPerandpostrsService {
    @Resource
    IPerandpostrsDao iPerandpostrsDao;

    private static Logger logger = LoggerFactory.getLogger(PerandpostrsServiceImpl.class);

    /**
     *@Author:ShiYun;
     *@Description:添加一条关系信息
     *@Date: 20:34 2018\5\12 0012
     */
    @Override
    public void addOne(PerAndPostRs perAndPostRs) {
        iPerandpostrsDao.insertOne(perAndPostRs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据人事ID查询岗位信息
     *@Date: 20:34 2018\5\12 0012
     */
    @Override
    public List<PerAndPostRs> queryPerAndPostRsByPerid(Integer perid) {
        return iPerandpostrsDao.selectPostidsByPerid(perid);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据postid查询人员集合
     *@Date: 16:00 2018\8\21 0021
     */
    @Override
    public List<PerAndPostRs> queryPerAndPostRsByPostid(Integer postid) {
        return iPerandpostrsDao.selectPeridsByPostid(postid);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:11 2018\5\18 0018
     */
    @Override
    public void removeByPerid(Integer perid) {
        iPerandpostrsDao.deleteByPerid(perid);
    }

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid删除一条信息
     * @Date  2018\11\1 0001 15:19
     * @Param [perid, postid]
     * @return void
     **/
    @Override
    public Boolean removeOneByPeridAndPostid(Integer perid, Integer postid) {
        PerAndPostRs perAndPostRs = iPerandpostrsDao.selectOneByPeridAndPostid(perid, postid);
        if(perAndPostRs==null){
            return false;
        }
        try {
            iPerandpostrsDao.deleteOneByPeridAndPostid(perid,postid);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return false;
        }
        return true;
    }

    /**
     * @Author: shiyun
     * @Description: 根据perid和postid查询一条信息
     * @Date  2018\11\1 0001 15:19
     * @Param [perid, postid]
     * @return com.elex.oa.entity.entity_shiyun.PerAndPostRs
     **/
    @Override
    public PerAndPostRs queryOneByPeridAndPostid(Integer perid, Integer postid) {
        return iPerandpostrsDao.selectOneByPeridAndPostid(perid, postid);
    }

    @Override
    public void removeAll() {
        iPerandpostrsDao.deleteAll();
    }
}
