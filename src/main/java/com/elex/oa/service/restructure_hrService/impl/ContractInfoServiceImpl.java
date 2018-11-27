package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IContractInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.restructure_hr.IContractinfoDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.ContractInfo;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.restructure_hrService.IContractInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 13:04
 * @Version 1.0
 **/
@Service
public class ContractInfoServiceImpl implements IContractInfoService {
    @Resource
    IContractInformationDao iContractInformationDao;
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;
    @Resource
    IContractinfoDao iContractinfoDao;

    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<ContractInformation> contractInformationList = iContractInformationDao.selectAll();
        List<ContractInfo> contractInfoList = new ArrayList<>();
        for (ContractInformation con:contractInformationList
             ) {
            ContractInfo temContractInfo = getContractInfoByContractcode(con.getContractcode());
            if(null!=temContractInfo){
                valBoolean = false;
                continue;
            }
            getContractcodeByAddContractInfo(getNewContractinfo(con));
        }
        return valBoolean;
    }

    private String getContractcodeByAddContractInfo(ContractInfo contractInfo){
        ContractInfo temContractInfo = getContractInfoByContractcode(contractInfo.getContractcode());
        if(null!=temContractInfo)return null;
        iContractinfoDao.insert(contractInfo);
        return contractInfo.getContractcode();
    }

    private ContractInfo getNewContractinfo(ContractInformation con) {
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setContractcode(con.getContractcode());
        //员工号要转换
        contractInfo.setEmployeenumber(getEmployeenumberByUserid(con.getUserid()));
        contractInfo.setStartdate(con.getStartdate());
        contractInfo.setEnddate(con.getEnddate());
        //合同类型的要转换
        contractInfo.setContracttypeid(getContracttypeidContractinformation(con));
        contractInfo.setAttachment(con.getAttachment());
        contractInfo.setRemark(con.getRemark());
        //办理人工号要转换
        contractInfo.setTransactoruserid(getTransactoruserid(con));
        contractInfo.setTransdate(con.getTransdate());
        contractInfo.setContractage(con.getContractage());
        return contractInfo;
    }

    private String getContracttypeidContractinformation(ContractInformation con) {
        HRset hRset_contracttype = ihRsetDao.selectById(con.getContracttypeid());
        if(null!=hRset_contracttype){
            List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(hRset_contracttype.getDatatype(), hRset_contracttype.getDatavalue()));
            if(null!=hrdatadictionaryList && hrdatadictionaryList.size()!=0){
                return hrdatadictionaryList.get(0).getDatacode();
            }
        }
        return null;
    }

    private String getEmployeenumberByUserid(Integer userid) {
        return iPersonalInformationDao.selectByUserid(userid).getEmployeenumber();
    }

    private String getTransactoruserid(ContractInformation con){
        if(null == con.getTransactoruserid()){
            return "admin";
        }else {
            return getEmployeenumberByUserid(con.getTransactoruserid());
        }
    }

    private ContractInfo getContractInfoByContractcode(String contractcode){
        if(StringUtils.isEmpty(contractcode))return null;
        List<ContractInfo> contractInfoList = iContractinfoDao.selectByEntity(new ContractInfo(contractcode));
        if(null == contractInfoList || contractInfoList.size()==0){
            return null;
        }else if(contractInfoList.size()==1){
            return contractInfoList.get(0);
        }
        return null;
    }
}