package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.common.common_shiyun.Commons;
import com.elex.oa.dao.dao_shiyun.*;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.impl.BaseServiceImpl;
import com.elex.oa.service.service_shiyun.IDimissionInformationService;
import com.elex.oa.util.resp.RespUtil;
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
        dimissionInformation.setTransactiondate(transactiondate);//办理日期
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
        //获得离职类型
        if(ihRsetDimissiontypeDao.selectById(dimissionInformation.getDimissiontypeid())!=null){
           dimissionInformation.setDimissiontype(ihRsetDimissiontypeDao.selectById(dimissionInformation.getDimissiontypeid()).getDimissiontype());
        }
        //获得离职去向
        if (ihRsetDimissiondirectionDao.selectById(dimissionInformation.getDimissiondirectionid())!=null) {
            dimissionInformation.setDimissiondirection(ihRsetDimissiondirectionDao.selectById(dimissionInformation.getDimissiondirectionid()).getDimissiondirection());
        }
        //获得离职原因
        if (ihRsetDimissionreasonDao.selectById(dimissionInformation.getDimissionreasonid())!=null) {
            dimissionInformation.setDimissionreason(ihRsetDimissionreasonDao.selectById(dimissionInformation.getDimissionreasonid()).getDimissionreason());
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
            return RespUtil.successResp("501","请求的参数类型错误，参数不能为空！",null);
        }
        Boolean b = false;//判断是否需要进数据库修改数据
        //先根据ID获取原数据
        if(dimissionInformation.getId()==null || iDimissionInformationDao.selectOneById(dimissionInformation.getId())==null){
            return RespUtil.successResp("502","数据请求有误！",null);
        }
        DimissionInformation selectOneById = iDimissionInformationDao.selectOneById(dimissionInformation.getId());
        //判断处理人是否有变
            //先根据username获得处理人的userid
            if(dimissionInformation.getTransactorusername()==null || dimissionInformation.getTransactorusername().equals("")){
                return RespUtil.successResp("503","数据请求有误！",null);
            }
            User user = iUserDao.selectByUsername(dimissionInformation.getTransactorusername());
            if(user==null){
                return RespUtil.successResp("504","数据请求有误！",null);
            }
            //处理办理人的信息
            if(user.getId()!=selectOneById.getTransactoruserid()){
                /*//更新办理日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String transactiondate = sdf.format(new Date());
                dimissionInformation.setTransactiondate(transactiondate);*/
                b = true;
            }else {
                dimissionInformation.setTransactoruserid(null);
            }
        //判断最后工作日期是否有变
        if(dimissionInformation.getLastworkingdate()==null || dimissionInformation.getLastworkingdate().equals("") || dimissionInformation.getLastworkingdate().equals(selectOneById.getLastworkingdate())){
              dimissionInformation.setLastworkingdate(null);
        }else {
                b = true;
        }
        //判断离职类型ID是否有变
        Integer dimissiontypeid = dimissionInformation.getDimissiontypeid();
        if(dimissiontypeid==null || dimissiontypeid.equals("") || dimissiontypeid==selectOneById.getDimissiontypeid()){
            dimissionInformation.setDimissiontypeid(null);
        }else if(ihRsetDimissiontypeDao.selectById(dimissiontypeid)==null) {
            return RespUtil.successResp("505","数据请求有误！",null);
        }else {
            b = true;
        }
        //判断离职原因ID是否有变
        Integer dimissionreasonid = dimissionInformation.getDimissionreasonid();
        if(dimissionreasonid==null || dimissionreasonid.equals("") || dimissionreasonid==selectOneById.getDimissionreasonid()){
            dimissionInformation.setDimissionreasonid(null);
        }else if(ihRsetDimissionreasonDao.selectById(dimissionreasonid)==null){
            return RespUtil.successResp("506","数据请求有误！",null);
        }else {
            b = true;
        }
        //判断离职方向ID是否有变
        Integer dimissiondirectionid = dimissionInformation.getDimissiondirectionid();
        if(dimissiondirectionid==null || dimissiondirectionid.equals("") || dimissiondirectionid==selectOneById.getDimissiondirectionid()){
            dimissionInformation.setDimissiondirectionid(null);
        }else if(ihRsetDimissiondirectionDao.selectById(dimissiondirectionid)==null){
            return RespUtil.successResp("507","数据请求有误！",null);
        }else {
            b = true;
        }
        if (b) {
            //更新办理日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String transactiondate = sdf.format(new Date());
            dimissionInformation.setTransactiondate(transactiondate);
            iDimissionInformationDao.updateOne(dimissionInformation);
            return RespUtil.successResp("205","请求成功！",null);
        }
        return RespUtil.successResp("508","没有需要修改的数据！",null);
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
            if (iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid())!=null) {
                if (iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid())!=null) {
                    dimissionInformations.get(i).setDepname(iDeptDao.selectDeptByDepid(iPersonalInformationDao.selectByUserid(dimissionInformations.get(i).getDimissionuserid()).getDepid()).getDepname());
                }
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
        iUserDao.deleteDimissionById(dimissionInformation.getDimissionuserid());
        //再讲离职信息删除
        iDimissionInformationDao.deleteOne(id);
    }
}
