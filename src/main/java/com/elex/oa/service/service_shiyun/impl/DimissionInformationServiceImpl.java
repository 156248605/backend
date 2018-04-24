package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.common.Commons;
import com.elex.oa.dao.dao_shiyun.*;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.service_shiyun.IDimissionInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职信息（业务逻辑层）
 * @Date:Created in  16:49 2018\4\16 0016
 * @Modify By:
 */
@Service
public class DimissionInformationServiceImpl extends BaseServiceImpl<DimissionInformation> implements IDimissionInformationService {
    @Autowired
    IDimissionInformationDao iDimissionInformationDao;
    @Autowired
    IPersonalInformationDao iPersonalInformationDao;
    @Autowired
    IUserDao iUserDao;
    @Autowired
    IDeptDao iDeptDao;
    @Autowired
    IPostDao iPostDao;

    /**
     *@Author:ShiYun;
     *@Description:添加离职信息并返回主键
     *@Date: 16:55 2018\4\16 0016
     */
    @Override
    public Integer addOne(DimissionInformation dimissionInformation) {
        dimissionInformation.setTransactoruserid(1);//办理人默认为管理员，实际中用session中userID代替
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String transactiondate = simpleDateFormat.format(new Date());
        dimissionInformation.setTransactiondate(transactiondate);//办理日期
        dimissionInformation.setApprovalnumbers(1);//待审批单数量默认为1，实际中用userID到ACT_RU_TASK表中查询
        dimissionInformation.setApprovalstatue(Commons.未处理);
        dimissionInformation.setWorkingnumbers(2);//代办任务
        dimissionInformation.setWorkingstatue(Commons.未处理);
        dimissionInformation.setFilenumbers(3);//占用文件数
        dimissionInformation.setFilestatue(Commons.未处理);
        dimissionInformation.setOfficesupplynumbers(4);//办公用品领用
        dimissionInformation.setOfficesupplystatue(Commons.未处理);
        iDimissionInformationDao.insertOne(dimissionInformation);
        Integer dimissionInformationId = dimissionInformation.getId();
        return dimissionInformationId;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息
     *@Date: 18:43 2018\4\16 0016
     */
    @Override
    public PageInfo<DimissionInformation> queryByCondition(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        DimissionInformation dimissionInformation = (DimissionInformation) paramMap.get("entity");
        List<DimissionInformation> dimissionInformations = iDimissionInformationDao.selectByCondition(dimissionInformation);
        for (Integer i=0;i<dimissionInformations.size();i++){
            Integer dimissionuserid = dimissionInformations.get(i).getDimissionuserid();
            PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(dimissionuserid);
            User user = iUserDao.selectById(dimissionuserid);
            User transactoruser = iUserDao.selectById(dimissionInformations.get(i).getTransactoruserid());
            Dept dept = iDeptDao.selectDeptByDepid(personalInformation.getDepid());
            Post post = iPostDao.selectPostByPostid(personalInformation.getPostid());
            dimissionInformations.get(i).setEmployeenumber(personalInformation.getEmployeenumber());
            dimissionInformations.get(i).setDimissiontruename(user.getTruename());
            if (dept!=null) {
                dimissionInformations.get(i).setDepname(dept.getDepname());
            } else {
                dimissionInformations.get(i).setDepname("还没有分配部门");
            }
            if (post!=null) {
                dimissionInformations.get(i).setPostname(post.getPostname());
            } else {
                dimissionInformations.get(i).setPostname("还没有分配岗位");
            }
            dimissionInformations.get(i).setTransactortruename(transactoruser.getTruename());
        }
        PageInfo<DimissionInformation> dimissionInformationPageInfo = new PageInfo<DimissionInformation>(dimissionInformations);
        return dimissionInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid查询离职信息
     *@Date: 13:41 2018\4\17 0017
     */
    @Override
    public DimissionInformation queryOneById(Integer id) {
        DimissionInformation dimissionInformation = iDimissionInformationDao.selectOneById(id);
        return dimissionInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid修改离职信息
     *@Date: 13:49 2018\4\17 0017
     */
    @Override
    public void modifyOne(DimissionInformation dimissionInformation) {
        iDimissionInformationDao.updateOne(dimissionInformation);
    }
}
