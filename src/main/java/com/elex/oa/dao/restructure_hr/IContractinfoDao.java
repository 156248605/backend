package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.ContractInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IContractinfoDao {

    void insert(ContractInfo contractInfo);

    List<ContractInfo> selectByEntity(ContractInfo contractInfo);
}
