package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.ContractInformation;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:合同信息（接口）
 * @Date:Created in  16:48 2018\4\9 0009
 * @Modify By:
 */
public interface IContractInformationService {
    PageInfo<ContractInformation> queryByConditions(Map<String,Object> paramMap);

    ContractInformation queryById(Integer id);

    ContractInformation queryByContractcode(String contractcode);

    List<ContractInformation> queryByUserid(Integer userid);

    List<ContractInformation> queryByEntity(ContractInformation contractInformation);

    List<ContractInformation> queryAll(ContractInformation contractInformation);

    Integer addOne(ContractInformation contractInformation);

    void removeOne(Integer id);

    Object modifyOne(ContractInformation contractInformation);

    Object queryContractInformationByUseridAndCurTime(Integer userid);
}
