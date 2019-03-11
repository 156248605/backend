package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IChangeInformaionDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.ChangeInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IChangeInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:变更信息（业务层）
 * @Date:Created in  18:52 2018\4\12 0012
 * @Modify By:
 */
@Service
public class ChangeInformationServiceImpl extends BaseServiceImpl<ChangeInformation> implements IChangeInformationService {
    @Resource
   private IChangeInformaionDao iChangeInformaionDao;
    @Resource
    private IUserDao iUserDao;

    /**
     *@Author:ShiYun;
     *@Description:添加变更信息并返回主键
     *@Date: 18:53 2018\4\12 0012
     */
    @Override
    public Integer addOne(ChangeInformation changeInformation) {
        return iChangeInformaionDao.insertOne(changeInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:变更信息列表
     *@Date: 11:52 2018\4\13 0013
     */
    @Override
    public PageInfo<ChangeInformation> queryAll(Map<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ChangeInformation> changeInformations = iChangeInformaionDao.selectByConditions((ChangeInformation) paramMap.get("entity"));
        for(Integer i=0;i<changeInformations.size();i++){
            // 设置姓名
            User changeduser = iUserDao.selectById(changeInformations.get(i).getChangeduserid());
            try {
                changeInformations.get(i).setChangedtruename(changeduser.getTruename());
            } catch (Exception e) {
                changeInformations.get(i).setChangedtruename("该员工可能已经删档!");
            }
            // 设置办理人姓名
            User transactoruser = iUserDao.selectById(changeInformations.get(i).getTransactoruserid());
            changeInformations.get(i).setTransactortruename(transactoruser.getTruename());
        }
        return new PageInfo<>(changeInformations);
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息（不分页）
     *@Date: 10:43 2018\5\25 0025
     */
    @Override
    public List<ChangeInformation> queryAll() {
        List<ChangeInformation> changeInformations = iChangeInformaionDao.selectAll();
        for (ChangeInformation changeinformation:changeInformations
             ) {
            String truename = null;
            try {
                truename = iUserDao.selectById(changeinformation.getChangeduserid()).getTruename();
            } catch (Exception e) {
                truename = "该员工可能已经删档！";
            }
            changeinformation.setChangedtruename(truename);
            User transactoruser = iUserDao.selectById(changeinformation.getTransactoruserid());
            String transactortruename;
            if (transactoruser!=null) {
                transactortruename = transactoruser.getTruename();
            } else {
                transactortruename = "该办理人可能已经删档！";
            }
            changeinformation.setTransactortruename(transactortruename);
        }
        return changeInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除信息
     *@Date: 10:44 2018\5\25 0025
     */
    @Override
    public void removeOne(Integer id) {
        iChangeInformaionDao.deleteOne(id);
    }

    @Override
    public List<ChangeInformation> queryByUserid(Integer changeduserid) {
        return iChangeInformaionDao.selectByUserid(changeduserid);
    }
}
