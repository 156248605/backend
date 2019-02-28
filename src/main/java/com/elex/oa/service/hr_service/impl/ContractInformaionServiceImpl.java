package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IContractInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.hr_service.IContractInformationService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public PageInfo<ContractInformation> queryByConditions(Map<String, Object> paramMap) {

        ContractInformation contractInformation = (ContractInformation) paramMap.get("entity");
        List<User> users = iUserDao.selectByConditions(contractInformation);
        if(users.size()!=0){
            ArrayList<Integer> userids = new ArrayList<>();
            for(User user:users){
                userids.add(user.getId());
            }
            if (userids.size()!=0) {
                contractInformation.setUserids(userids);
            }
        }
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ContractInformation> contractInformationList = iContractInformationDao.selectByConditions(contractInformation);

        return new PageInfo<ContractInformation>(contractInformationList);
    }

    @Override
    public ContractInformation queryById(Integer id) {
        //获得合同的粗略信息
        ContractInformation contractInformation = iContractInformationDao.selectById(id);
        if(null == contractInformation)return null;
        /*获得合同的详细信息*/
        contractInformation = getDetailContractByContract(contractInformation);
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
            //获得姓名
            if (contractInformation!=null && contractInformation.getUserid()!=null && iUserDao.selectById(contractInformation.getUserid())!=null) {
                contractInformation.setTruename(iUserDao.selectById(contractInformation.getUserid()).getTruename());
            }
            //获得工号
            if (iPersonalInformationDao.selectByUserid(contractInformation.getUserid())!=null) {
                contractInformation.setEmployeenumber(iPersonalInformationDao.selectByUserid(contractInformation.getUserid()).getEmployeenumber());
            }
            //获得合同类型
            List<HRset> contractList = ihRsetDao.selectByConditions(new HRset(contractInformation.getContracttypeid()));
            if (contractList!=null && contractInformationList.size()==1) {
                contractInformation.setContracttype(contractList.get(0).getDatavalue());
            }
            //获得办理人姓名
            if (null!=contractInformation.getTransactoruserid()) {
                contractInformation.setTransactortruename(iUserDao.selectById(contractInformation.getTransactoruserid()).getTruename());
            }
            //获得合同期限
            try {
                contractInformation.setContractage(hrUtils.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
            } catch (Exception e) {
                System.out.println("获得合同期限失败！");
            }
        }
        return contractInformationList;
    }

    @Override
    public List<ContractInformation> queryByEntity(ContractInformation contractInformation) {
        List<ContractInformation> contractInformations = iContractInformationDao.selectByConditions(contractInformation);
        return contractInformations;
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
            e.printStackTrace();
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
        //获得合同新对象
        ContractInformation newContract = getDetailContractByContract(contractInformation);
        //获得合同旧对象
        ContractInformation oldContract = iContractInformationDao.selectById(contractInformation.getId());
        oldContract = getDetailContractByContract(oldContract);
        //校验合同编号是否可用
        if(StringUtils.isBlank(contractInformation.getContractcode()))return RespUtil.response("500","合同编号不能为空！",null);
        ContractInformation contractInformationTemp = iContractInformationDao.selectOneByContractcode(contractInformation.getContractcode());
        if(null!=contractInformationTemp && !oldContract.getContractcode().equals(newContract.getContractcode())){
            return RespUtil.response("500","合同编号已存在！",null);
        }
        //判断是否需要修改合同信息
        Boolean isUpdate = isUpdateForContractInformation(oldContract,newContract);
        if (isUpdate) {
            iContractInformationDao.updateOne(contractInformation);
            return RespUtil.response("200","提交成功！",null);
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
            e.printStackTrace();
            return RespUtil.response("500","查询失败",e.getStackTrace());
        }
        if(null==contractInformation)return RespUtil.response("500","合同信息不存在",userid);
        return RespUtil.response("200","查询成功",contractInformation);
    }


    //判断是否需要修改合同信息
    private Boolean isUpdateForContractInformation(ContractInformation oldContract, ContractInformation newContract) {
        if(null==oldContract || null==newContract)return false;
        if(!newContract.getContractcode().equals(oldContract.getContractcode()))return true;
        if(!newContract.getStartdate().equals(oldContract.getStartdate()))return true;
        if(!newContract.getEnddate().equals(oldContract.getEnddate()))return true;
        if(newContract.getContracttypeid().intValue()!=oldContract.getContracttypeid().intValue())return true;
        if(StringUtils.isNotBlank(newContract.getAttachment()))return true;
        if(!newContract.getRemark().equals(oldContract.getRemark()))return true;
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
        if(null==userid)return null;
        List<ContractInformation> contractInformationList = iContractInformationDao.selectByUserid(userid);
        if(null==contractInformationList)return null;
        for(ContractInformation contractInformation:contractInformationList){
            contractInformation = getDetailContractByContract(contractInformation);
        }
        return contractInformationList;
    }
}
