package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformationExchange;
import com.elex.oa.entity.hr_entity.post.PerAndPostRs;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.hr_service.IDimissionInformationService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    IDimissionInformationDao iDimissionInformationDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IDeptDao iDeptDao;
    @Resource
    IPostDao iPostDao;
    @Resource
    IPerandpostrsDao iPerandpostrsDao;
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    HrUtils hrUtils;

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
        dimissionInformation.setTransactiondate(transactiondate);//办理日期
        //获得8个参数
        /*dimissionInformation.setApprovalnumbers(1);//待审批单数量默认为1，实际中用userID到ACT_RU_TASK表中查询
        dimissionInformation.setApprovalstatue(Commons.未处理);
        dimissionInformation.setWorkingnumbers(2);//代办任务
        dimissionInformation.setWorkingstatue(Commons.未处理);
        dimissionInformation.setFilenumbers(3);//占用文件数
        dimissionInformation.setFilestatue(Commons.未处理);
        dimissionInformation.setOfficesupplynumbers(4);//办公用品领用
        dimissionInformation.setOfficesupplystatue(Commons.未处理);*/
        //添加离职信息
        iDimissionInformationDao.insertOne(dimissionInformation);
        Integer dimissionInformationId = dimissionInformation.getId();
        //将离职的人员从user表中移除(其实是修改状态)
        iUserDao.addDimissionById(dimissionInformation.getDimissionuserid());
        return dimissionInformationId;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息
     *@Date: 18:43 2018\4\16 0016
     */
    @Override
    public PageInfo<DimissionInformation> queryByCondition(HashMap<String, Object> paramMap) {
        System.out.println(123);
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        DimissionInformation dimissionInformation = (DimissionInformation) paramMap.get("entity");

        //先查询关系表，获得perids

        if(dimissionInformation.getPostname()!=null && !"".equals(dimissionInformation.getPostname()) || (dimissionInformation.getPostnameList()==null?0:dimissionInformation.getPostnameList().size())!=0){
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
                dimissionInformations.get(i).setPostnames(hrUtils.getArrayToString(postnames,";"));
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
            List<HRset> hRsetDimissiontypeList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissiontypeid()));
            if (hRsetDimissiontypeList!=null && hRsetDimissiontypeList.size()==1) {
                dimissionInformations.get(i).setDimissiontype(hRsetDimissiontypeList.get(0).getDatavalue());
            }
            //获得离职去向
            List<HRset> hRsetDimissiondirectionList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissiondirectionid()));
            if (hRsetDimissiondirectionList!=null && hRsetDimissiondirectionList.size()==1) {
                dimissionInformations.get(i).setDimissiondirection(hRsetDimissiondirectionList.get(0).getDatavalue());
            }
            //获得离职原因
            List<HRset> hRsetDimissionreasonList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissionreasonid()));
            if (hRsetDimissionreasonList!=null && hRsetDimissionreasonList.size()==1) {
                dimissionInformations.get(i).setDimissionreason(hRsetDimissionreasonList.get(0).getDatavalue());
            }
        }
        PageInfo<DimissionInformation> dimissionInformationPageInfo = new PageInfo<DimissionInformation>(dimissionInformations);
        return dimissionInformationPageInfo;
    }

    @Override
    public List<DimissionInformation> queryByDimission(DimissionInformation dimissionInformation) {
        Boolean isNotNull = false;
        if(dimissionInformation.getDimissionuserid()!=null)isNotNull=true;
        if (isNotNull) {
            return iDimissionInformationDao.selectByDimission(dimissionInformation);
        }
        return null;
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
        //获得离职类型
        List<HRset> hRsetDimissiontypeList = ihRsetDao.selectByConditions(new HRset(dimissionInformation.getDimissiontypeid()));
        if(hRsetDimissiontypeList!=null && hRsetDimissiontypeList.size()==1){
           dimissionInformation.setDimissiontype(hRsetDimissiontypeList.get(0).getDatavalue());
        }
        //获得离职去向
        List<HRset> hRsetDimissiondirectionList = ihRsetDao.selectByConditions(new HRset(dimissionInformation.getDimissiondirectionid()));
        if (hRsetDimissiondirectionList!=null && hRsetDimissiondirectionList.size()==1) {
            dimissionInformation.setDimissiondirection(hRsetDimissiondirectionList.get(0).getDatavalue());
        }
        //获得离职原因
        List<HRset> hRsetDimissionreasonList = ihRsetDao.selectByConditions(new HRset(dimissionInformation.getDimissionreasonid()));
        if (hRsetDimissionreasonList!=null && hRsetDimissionreasonList.size()==1) {
            dimissionInformation.setDimissionreason(hRsetDimissionreasonList.get(0).getDatavalue());
        }
        return dimissionInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid修改离职信息
     *@Date: 13:49 2018\4\17 0017
     */
    @Override
    public Object modifyOne(DimissionInformation dimissionInformation) {
        if(dimissionInformation==null){
            return RespUtil.response("500","参数不能为空！",null);
        }
        Boolean b = false;//判断是否需要进数据库修改数据
        dimissionInformation.setTransactoruserid(hrUtils.getUseridByUsername(dimissionInformation.getTransactorusername()));
        //先根据ID获取原数据
        DimissionInformation selectOneById = iDimissionInformationDao.selectOneById(dimissionInformation.getId());
        if(dimissionInformation.getId()==null || selectOneById==null){
            return RespUtil.response("500","离职ID为null或找不到！",null);
        }
        if(dimissionInformation.getTransactoruserid()==null ){
            return RespUtil.response("500","获取登录ID失败！",null);
        }
        //判断处理人是否有变
        if(dimissionInformation.getTransactoruserid().compareTo(selectOneById.getTransactoruserid())!=0){
            b = true;
        }
        //判断最后工作日期是否有变
        if(StringUtils.isNotEmpty(dimissionInformation.getLastworkingdate()) && !dimissionInformation.getLastworkingdate().equals(selectOneById.getLastworkingdate()))b=true;
        //判断离职类型ID是否有变
        if(null!=dimissionInformation.getDimissiontypeid() && dimissionInformation.getDimissiontypeid().intValue()!=selectOneById.getDimissiontypeid().intValue())b=true;
        //判断离职原因ID是否有变
        if(null!=dimissionInformation.getDimissionreasonid() && dimissionInformation.getDimissionreasonid().intValue()!=selectOneById.getDimissionreasonid().intValue())b=true;
        //判断离职方向ID是否有变
        if(null!=dimissionInformation.getDimissiondirectionid() && dimissionInformation.getDimissiondirectionid().intValue()!=selectOneById.getDimissiondirectionid().intValue())b=true;
        //判断办理日期是否有变
        if(StringUtils.isNotEmpty(dimissionInformation.getTransactiondate()) && !dimissionInformation.getTransactiondate().equals(selectOneById.getTransactiondate()))b=true;
        if (b) {
            iDimissionInformationDao.updateOne(dimissionInformation);
            return RespUtil.response("200","请求成功！",null);
        }
        return RespUtil.response("500","没有需要修改的数据！",null);
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
                dimissionInformations.get(i).setPostnames(hrUtils.getArrayToString(postnames,";"));
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
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                if (iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid())!=null) {
                    dimissionInformations.get(i).setDepname(iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid()).getDepname());
                }
            }
            //获得离职类型
            List<HRset> hRsetDimissiontypeList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissiontypeid()));
            if (hRsetDimissiontypeList!=null && hRsetDimissiontypeList.size()==1) {
                dimissionInformations.get(i).setDimissiontype(hRsetDimissiontypeList.get(0).getDatavalue());
            }
            //获得离职去向
            List<HRset> hRsetDimissiondirectionList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissiondirectionid()));
            if (hRsetDimissiondirectionList!=null && hRsetDimissiondirectionList.size()==1) {
                dimissionInformations.get(i).setDimissiondirection(hRsetDimissiondirectionList.get(0).getDatavalue());
            }
            //获得离职原因
            List<HRset> hRsetDimissionreasonList = ihRsetDao.selectByConditions(new HRset(dimissionInformations.get(i).getDimissionreasonid()));
            if (hRsetDimissionreasonList!=null && hRsetDimissionreasonList.size()==1) {
                dimissionInformations.get(i).setDimissionreason(hRsetDimissionreasonList.get(0).getDatavalue());
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
        iUserDao.deleteDimissionById(dimissionInformation.getDimissionuserid());
        //再讲离职信息删除
        iDimissionInformationDao.deleteOne(id);
    }

    @Override
    public List<PersonalInformationExchange> queryAllDimissionInformations() {
        return iDimissionInformationDao.queryAllDimissionInformations();
    }


}
