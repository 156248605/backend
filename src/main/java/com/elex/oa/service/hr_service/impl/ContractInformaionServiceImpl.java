package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IContractInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.service.hr_service.IContractInformationService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
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

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
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
        contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getContractage()));
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ContractInformation> contractInformationList = iContractInformationDao.selectByConditions(contractInformation);

        return new PageInfo<ContractInformation>(contractInformationList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
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

    /**
     *@Author:ShiYun;
     *@Description:根据userid查询合同信息
     *@Date: 16:12 2018\5\30 0030
     */
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
                contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
            } catch (Exception e) {
                System.out.println("获得合同期限失败！");
            }
        }
        return contractInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件（不分页）查询合同信息
     *@Date: 17:37 2018\4\12 0012
     */
    @Override
    public List<ContractInformation> queryByEntity(ContractInformation contractInformation) {
        List<ContractInformation> contractInformations = iContractInformationDao.selectByConditions(contractInformation);
        return contractInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:51 2018\5\25 0025
     */
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

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息并返回主键
     *@Date: 14:28 2018\4\20 0020
     */
    @Override
    public Integer addOne(ContractInformation contractInformation){
        try {
            contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getStartdate(), contractInformation.getEnddate()));
            iContractInformationDao.insertOne(contractInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return contractInformation.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除合同信息
     *@Date: 17:50 2018\5\25 0025
     */
    @Override
    public void removeOne(Integer id) {
        iContractInformationDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:修改合同信息
     *@Date: 10:14 2018\5\29 0029
     */
    @Override
    public Object modifyOne(ContractInformation contractInformation){
        //获得合同新对象
        ContractInformation newContract = getDetailContractByContract(contractInformation);
        //获得合同旧对象
        ContractInformation oldContract = iContractInformationDao.selectById(contractInformation.getId());
        oldContract = getDetailContractByContract(oldContract);
        //校验合同编号是否可用
        if(StringUtils.isBlank(contractInformation.getContractcode()))return RespUtil.successResp("500","合同编号不能为空！",null);
        ContractInformation contractInformationTemp = iContractInformationDao.selectOneByContractcode(contractInformation.getContractcode());
        if(null!=contractInformationTemp && !oldContract.getContractcode().equals(newContract.getContractcode())){
            return RespUtil.successResp("500","合同编号已存在！",null);
        }
        //判断是否需要修改合同信息
        Boolean isUpdate = isUpdateForContractInformation(oldContract,newContract);
        if (isUpdate) {
            iContractInformationDao.updateOne(contractInformation);
            return RespUtil.successResp("200","提交成功！",null);
        }
        return RespUtil.successResp("500","没有需要修改的信息！",null);
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
        contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
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
