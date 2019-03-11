package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IContractInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.hr_service.IContractInformationService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:合同信息（业务层）
 * @Date:Created in  16:53 2018\4\9 0009
 * @Modify By:
 */
@Service
public class ContractInformaionServiceImpl implements IContractInformationService {

    @Resource
    IContractInformationDao iContractInformationDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    HrUtils hrUtils;

    private static Logger logger = LoggerFactory.getLogger(ContractInformaionServiceImpl.class);

    @Override
    public PageInfo<ContractInformation> queryByConditions(Map<String, Object> paramMap) {

        ContractInformation contractInformation = (ContractInformation) paramMap.get("entity");
        List<User> users = iUserDao.selectByConditions(contractInformation);
        if(!users.isEmpty()){
            ArrayList<Integer> userids = new ArrayList<>();
            for(User user:users){
                userids.add(user.getId());
            }
            if (!userids.isEmpty()) {
                contractInformation.setUserids(userids);
            }
        }
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ContractInformation> contractInformationList = iContractInformationDao.selectByConditions(contractInformation);

        return new PageInfo<>(contractInformationList);
    }

    @Override
    public ContractInformation queryById(Integer id) {
        //获得合同的粗略信息
        ContractInformation contractInformation = iContractInformationDao.selectById(id);
        if(null == contractInformation)return null;
        /*获得合同的详细信息*/
        getDetailContractByContract(contractInformation);
        //获得续签合同集合
        contractInformation.setHistoryContract(getChildrenContractInformationsByUserid(contractInformation.getUserid()));
        return contractInformation;
    }

    @Override
    public ContractInformation queryByContractcode(String contractcode) {
        return iContractInformationDao.selectOneByContractcode(contractcode);
    }

    @Override
    public List<ContractInformation> queryByUserid(Integer userid) {
        List<ContractInformation> contractInformationList = iContractInformationDao.selectByUserid(userid);
        for(ContractInformation contractInformation:contractInformationList){
            if (null!=contractInformation) {
                //获得姓名
                contractInformation.setTruename(hrUtils.getTruenameByUserid(contractInformation.getUserid()));
                //获得工号
                contractInformation.setEmployeenumber(hrUtils.getEmployeenumberByUserid(contractInformation.getUserid()));
                //获得合同类型
                contractInformation.setContracttype(hrUtils.getDatavalueByHrsetid(contractInformation.getContracttypeid()));
                //获得办理人姓名
                contractInformation.setTransactortruename(hrUtils.getTruenameByUserid(contractInformation.getTransactoruserid()));
                //获得合同期限
                contractInformation.setContractage(hrUtils.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
            }
        }
        return contractInformationList;
    }

    @Override
    public List<ContractInformation> queryByEntity(ContractInformation contractInformation) {
        return iContractInformationDao.selectByConditions(contractInformation);
    }

    public List<ContractInformation> queryAll(ContractInformation contractInformation){
        List<ContractInformation> contractInformationList = iContractInformationDao.selectAll();
        for (ContractInformation con:contractInformationList
             ) {
            User user = iUserDao.selectById(con.getUserid());
            if(user==null){//用户不存在
                iContractInformationDao.deleteOne(con.getId());
                continue;
            }
            Integer state = iUserDao.selectById(con.getUserid()).getState();
            con.setState(state.toString());
        }
        return contractInformationList;
    }

    @Override
    public Integer addOne(ContractInformation contractInformation){
        try {
            Resp resp = (Resp) queryContractInformationByUseridAndCurTime(contractInformation.getUserid());
            String rspCode = resp.getHead().getRspCode();
            if(rspCode.equals("200"))return null;//有则退出
            contractInformation.setContractage(hrUtils.getContractage(contractInformation.getStartdate(), contractInformation.getEnddate()));
            iContractInformationDao.insertOne(contractInformation);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return null;
        }
        return contractInformation.getId();
    }

    @Override
    public void removeOne(Integer id) {
        iContractInformationDao.deleteOne(id);
    }

    @Override
    public Object modifyOne(ContractInformation contractInformation){
        if (null!=contractInformation) {
            //获得合同新对象
            ContractInformation newContract = getDetailContractByContract(contractInformation);
            //获得合同旧对象
            ContractInformation oldContract = iContractInformationDao.selectById(contractInformation.getId());
            oldContract = getDetailContractByContract(oldContract);
            //校验合同编号是否可用
            if(StringUtils.isBlank(contractInformation.getContractcode()))return RespUtil.response("500","合同编号不能为空！",null);
            ContractInformation contractInformationTemp = iContractInformationDao.selectOneByContractcode(contractInformation.getContractcode());
            if(null!=contractInformationTemp && oldContract!=null && !oldContract.getContractcode().equals(newContract.getContractcode())){
                return RespUtil.response("500","合同编号已存在！",null);
            }
            //判断是否需要修改合同信息
            Boolean isUpdate = isUpdateForContractInformation(oldContract,newContract);
            if (isUpdate) {
                iContractInformationDao.updateOne(contractInformation);
                return RespUtil.response("200","提交成功！",null);
            }
        } else {
            RespUtil.response("500","合同不能为空！",null);
        }
        return RespUtil.response("500","没有需要修改的信息！",null);
    }

    @Override
    public Object queryContractInformationByUseridAndCurTime(Integer userid) {
        //目的：根据userid和curtime查出还在有效期中的合同
        if(null==userid)return RespUtil.response("500","合同人userid不能为空",null);
        User user = iUserDao.selectByPrimaryKey(userid);
        if(null==user)return RespUtil.response("500","合同人不存在",userid);
        //获得当前时间（格式：YYYY/MM/dd）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String curtime = sdf.format(new Date());
        //获得合同信息
        ContractInformation contractInformation = null;
        try {
            contractInformation = iContractInformationDao.selectContractInformationByUseridAndCurTime(userid,curtime);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","查询失败",e.getStackTrace());
        }
        if(null==contractInformation)return RespUtil.response("500","合同信息不存在",userid);
        return RespUtil.response("200","查询成功",contractInformation);
    }


    //判断是否需要修改合同信息
    private Boolean isUpdateForContractInformation(ContractInformation oldContract, ContractInformation newContract) {
        if(null==oldContract || null==newContract){
            return false;
        }else if(!newContract.getContractcode().equals(oldContract.getContractcode())
        ||!newContract.getStartdate().equals(oldContract.getStartdate())
        ||!newContract.getEnddate().equals(oldContract.getEnddate())
        ||newContract.getContracttypeid().intValue()!=oldContract.getContracttypeid().intValue()
        ||StringUtils.isNotBlank(newContract.getAttachment())
        ||!newContract.getRemark().equals(oldContract.getRemark()))return true;
        return false;
    }

    //获得详细合同信息
    private ContractInformation getDetailContractByContract(ContractInformation contractInformation) {
        if(null==contractInformation)return null;
        //获得姓名
        contractInformation.setTruename(hrUtils.getTruenameByUserid(contractInformation.getUserid()));
        //获得工号
        contractInformation.setEmployeenumber(hrUtils.getEmployeenumberByUserid(contractInformation.getUserid()));
        //获得合同类型
        contractInformation.setContracttype(hrUtils.getDatavalueByHrsetid(contractInformation.getContracttypeid()));
        //获得办理人姓名
        contractInformation.setTransactortruename(hrUtils.getTruenameByUserid(contractInformation.getTransactoruserid()));
        //获得合同期限
        contractInformation.setContractage(hrUtils.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
        return contractInformation;
    }

    //获得合同续签信息
    private List<ContractInformation> getChildrenContractInformationsByUserid(Integer userid){
        if(null==userid)return Collections.emptyList();
        List<ContractInformation> contractInformationList = iContractInformationDao.selectByUserid(userid);
        if(null==contractInformationList)return Collections.emptyList();
        for(ContractInformation contractInformation:contractInformationList){
            getDetailContractByContract(contractInformation);
        }
        return contractInformationList;
    }
}
