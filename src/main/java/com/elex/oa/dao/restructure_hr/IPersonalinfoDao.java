package com.elex.oa.dao.restructure_hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IPersonalinfoDao extends BaseDao<Personalinfo> {
    void deleteByEmployeenumber(String employeenumber);

    void updateByPersonalinfo(Personalinfo personalinfo);

    Personalinfo selectPersonalinfoByEmployeenumber(String employeenumber);

    List<Personalinfo> selectByEntity(Personalinfo personalinfo);

    List<Personalinfo> selectUsersByEMPLOYEE_ON();

    Map<String,String> selectDepcodeByEmployeenumber(String employeenumber);

    Map<String,String> selectUserByEmployeenumber(String employeenumber);
}
