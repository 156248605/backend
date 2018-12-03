package com.elex.oa.dao.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPersonalinfoDao {
    void insert(Personalinfo personalinfo);

    void deleteByEmployeenumber(String employeenumber);

    void updateByPersonalinfo(Personalinfo personalinfo);

    Personalinfo selectPersonalinfoByEmployeenumber(String employeenumber);

    List<Personalinfo> selectByEntity(Personalinfo personalinfo);
}
