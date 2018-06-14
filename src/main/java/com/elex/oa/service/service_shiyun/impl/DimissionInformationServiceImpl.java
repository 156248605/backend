package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.common.common_shiyun.Commons;
import com.elex.oa.dao.dao_shiyun.*;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.service_shiyun.IDimissionInformationService;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    IPerandpostrsDao iPerandpostrsDao;
    @Autowired
    IHRsetDimissiontypeDao ihRsetDimissiontypeDao;
    @Autowired
    IHRsetDimissiondirectionDao ihRsetDimissiondirectionDao;
    @Autowired
    IHRsetDimissionreasonDao ihRsetDimissionreasonDao;

    /**
     *@Author:ShiYun;
     *@Description:添加离职信息并返回主键
     *@Date: 16:55 2018\4\16 0016
     */
    @Override
    public Integer addOne(DimissionInformation dimissionInformation) {
        //获得办理日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String transactiondate = simpleDateFormat.format(new Date());
        if (dimissionInformation.getTransactiondate()!=null && !"".equals(dimissionInformation.getTransactiondate() )) {
            dimissionInformation.setTransactiondate(transactiondate);//办理日期
        }
        //获得8个参数
        dimissionInformation.setApprovalnumbers(1);//待审批单数量默认为1，实际中用userID到ACT_RU_TASK表中查询
        dimissionInformation.setApprovalstatue(Commons.未处理);
        dimissionInformation.setWorkingnumbers(2);//代办任务
        dimissionInformation.setWorkingstatue(Commons.未处理);
        dimissionInformation.setFilenumbers(3);//占用文件数
        dimissionInformation.setFilestatue(Commons.未处理);
        dimissionInformation.setOfficesupplynumbers(4);//办公用品领用
        dimissionInformation.setOfficesupplystatue(Commons.未处理);
        //添加离职信息
        iDimissionInformationDao.insertOne(dimissionInformation);
        Integer dimissionInformationId = dimissionInformation.getId();
        //将离职的人员从user表中移除(其实是修改状态)
        iUserDao.deleteById(dimissionInformation.getDimissionuserid());
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
        DimissionInformation dimissionInformation = (DimissionInformation) paramMap.get("entity");

        //先查询关系表，获得perids
        if(dimissionInformation.getPostname()!=null && !"".equals(dimissionInformation.getPostname()) || dimissionInformation.getPostnameList()!=null){
            PersonalInformation personalInformation = new PersonalInformation();

            if (dimissionInformation.getPostname()!=null && !"".equals(dimissionInformation.getPostname())) {
                personalInformation.setPostname(dimissionInformation.getPostname());
                personalInformation.setPostnamevalue(dimissionInformation.getPostnamevalue());

                //如何是不包含就多走一步
                if (personalInformation.getPostnamevalue().equals("不包含")) {
                    List<Integer> perids = new ArrayList<>();
                    List<PerAndPostRs> perAndPostRs2 = iPerandpostrsDao.selectByConditions2(personalInformation);
                    for(PerAndPostRs perAndPostRs1:perAndPostRs2){
                        perids.add(perAndPostRs1.getPerid());
                    }
                    personalInformation.setPerids(perids);
                }
            }

            if (dimissionInformation.getPostnameList()!=null) {
                personalInformation.setPostnameList(dimissionInformation.getPostnameList());
                personalInformation.setPostnamevalue(dimissionInformation.getPostnamevalue());
            }
            //这里获得perids是最终的
            List<Integer> perids = new ArrayList<>();
            List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectByConditions(personalInformation);
            for(PerAndPostRs perAndPostRs1:perAndPostRs){
                perids.add(perAndPostRs1.getPerid());
            }
            dimissionInformation.setPerids(perids);
        }

        //再查询per表，获得userids
        List<Integer> userids = new ArrayList<>();
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByConditions2(dimissionInformation);
        for(PersonalInformation personalInformation:personalInformationList){
            userids.add(personalInformation.getUserid());
        }
        dimissionInformation.setUserids(userids);
        //最后查询出离职信息
        PageHelper.startPage(pageNum,pageSize);
        List<DimissionInformation> dimissionInformations = iDimissionInformationDao.selectByCondition(dimissionInformation);
        for (Integer i=0;i<dimissionInformations.size();i++){
            //获得岗位
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectPostidsByPerid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getId());
                List<String> postnames = new ArrayList<>();
                for(PerAndPostRs perAndPostRs1:perAndPostRs){
                    postnames.add(iPostDao.selectPostByPostid(perAndPostRs1.getPostid()).getPostname());
                }
                dimissionInformations.get(i).setPostnames(IDcodeUtil.getArrayToString(postnames,";"));
            }
            //获得办理人
            if (iUserDao.selectById(dimissionInformations.get(i).getTransactoruserid())!=null) {
                dimissionInformations.get(i).setTransactortruename(iUserDao.selectById(dimissionInformations.get(i).getTransactoruserid()).getTruename());
            }
            //获得员工号
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                dimissionInformations.get(i).setEmployeenumber(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getEmployeenumber());
            }
            //获得姓名
            if (iUserDao.selectById(dimissionInformations.get(i).getDimissionuserid())!=null) {
                dimissionInformations.get(i).setDimissiontruename(iUserDao.selectById(dimissionInformations.get(i).getDimissionuserid()).getTruename());
            }
            //获得部门
            if (iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid())!=null) {
                dimissionInformations.get(i).setDepname(iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid()).getDepname());
            }
            //获得离职类型
            if (ihRsetDimissiontypeDao.selectById(dimissionInformations.get(i).getDimissiontypeid())!=null) {
                dimissionInformations.get(i).setDimissiontype(ihRsetDimissiontypeDao.selectById(dimissionInformations.get(i).getDimissiontypeid()).getDimissiontype());
            }
            //获得离职去向
            if (ihRsetDimissiondirectionDao.selectById(dimissionInformations.get(i).getDimissiondirectionid())!=null) {
                dimissionInformations.get(i).setDimissiondirection(ihRsetDimissiondirectionDao.selectById(dimissionInformations.get(i).getDimissiondirectionid()).getDimissiondirection());
            }
            //获得离职原因
            if (ihRsetDimissionreasonDao.selectById(dimissionInformations.get(i).getDimissionreasonid())!=null) {
                dimissionInformations.get(i).setDimissionreason(ihRsetDimissionreasonDao.selectById(dimissionInformations.get(i).getDimissionreasonid()).getDimissionreason());
            }
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
        //获得姓名
        if (iUserDao.selectById(dimissionInformation.getDimissionuserid())!=null) {
            dimissionInformation.setDimissiontruename(iUserDao.selectById(dimissionInformation.getDimissionuserid()).getTruename());
        }
        //获得办理人
        if (iUserDao.selectById(dimissionInformation.getTransactoruserid())!=null) {
            dimissionInformation.setTransactortruename(iUserDao.selectById(dimissionInformation.getTransactoruserid()).getTruename());
        }
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

    /**
     *@Author:ShiYun;
     *@Description:查询所有离职信息（不分页）
     *@Date: 18:29 2018\5\29 0029
     */
    @Override
    public List<DimissionInformation> queryAll() {
        List<DimissionInformation> dimissionInformations = iDimissionInformationDao.selectAll();
        for (Integer i=0;i<dimissionInformations.size();i++){
            //获得岗位
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectPostidsByPerid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getId());
                List<String> postnames = new ArrayList<>();
                for(PerAndPostRs perAndPostRs1:perAndPostRs){
                    postnames.add(iPostDao.selectPostByPostid(perAndPostRs1.getPostid()).getPostname());
                }
                dimissionInformations.get(i).setPostnames(IDcodeUtil.getArrayToString(postnames,";"));
            }
            //获得办理人
            if (iUserDao.selectById(dimissionInformations.get(i).getTransactoruserid())!=null) {
                dimissionInformations.get(i).setTransactortruename(iUserDao.selectById(dimissionInformations.get(i).getTransactoruserid()).getTruename());
            }
            //获得员工号
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                dimissionInformations.get(i).setEmployeenumber(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getEmployeenumber());
            }
            //获得姓名
            if (iUserDao.selectById(dimissionInformations.get(i).getDimissionuserid())!=null) {
                dimissionInformations.get(i).setDimissiontruename(iUserDao.selectById(dimissionInformations.get(i).getDimissionuserid()).getTruename());
            }
            //获得部门
            if (iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid())!=null) {
                dimissionInformations.get(i).setDepname(iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid()).getDepname());
            }
            //获得离职类型
            if (ihRsetDimissiontypeDao.selectById(dimissionInformations.get(i).getDimissiontypeid())!=null) {
                dimissionInformations.get(i).setDimissiontype(ihRsetDimissiontypeDao.selectById(dimissionInformations.get(i).getDimissiontypeid()).getDimissiontype());
            }
            //获得离职去向
            if (ihRsetDimissiondirectionDao.selectById(dimissionInformations.get(i).getDimissiondirectionid())!=null) {
                dimissionInformations.get(i).setDimissiondirection(ihRsetDimissiondirectionDao.selectById(dimissionInformations.get(i).getDimissiondirectionid()).getDimissiondirection());
            }
            //获得离职原因
            if (ihRsetDimissionreasonDao.selectById(dimissionInformations.get(i).getDimissionreasonid())!=null) {
                dimissionInformations.get(i).setDimissionreason(ihRsetDimissionreasonDao.selectById(dimissionInformations.get(i).getDimissionreasonid()).getDimissionreason());
            }
        }
        return dimissionInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除离职信息
     *@Date: 13:47 2018\5\30 0030
     */
    @Override
    public void removeOne(Integer id) {
        //先将误删的员工删除
        DimissionInformation dimissionInformation = iDimissionInformationDao.selectOneById(id);
        iUserDao.deleteById2(dimissionInformation.getDimissionuserid());
        //再讲离职信息删除
        iDimissionInformationDao.deleteOne(id);
    }
}
