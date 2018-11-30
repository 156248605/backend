package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Contractinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IContractinfoDao {

    void insert(Contractinfo contractInfo);

    List<Contractinfo> selectByEntity(Contractinfo contractInfo);
}
