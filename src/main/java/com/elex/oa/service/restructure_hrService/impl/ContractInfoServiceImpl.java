package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IContractInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.ContractInfo;
import com.elex.oa.service.restructure_hrService.IContractInfoService;
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

    @Override
    public Boolean changeTable() {
        List<ContractInformation> contractInformationList = iContractInformationDao.selectAll();
        List<ContractInfo> contractInfoList = new ArrayList<>();
        for (ContractInformation con:contractInformationList
             ) {
            ContractInfo contractInfo = new ContractInfo();
            contractInfo.setContractcode(con.getContractcode());
            contractInfo.setEmployeenumber(getEmployeenumberByUserid(con.getUserid()));
            contractInfo.setStartdate(con.getStartdate());
            contractInfo.setEnddate(con.getEnddate());
            HRset hRset_contracttype = ihRsetDao.selectById(con.getContracttypeid());

        }
        return null;
    }

    private String getEmployeenumberByUserid(Integer userid) {
        return iPersonalInformationDao.selectByUserid(userid).getEmployeenumber();
    }
}