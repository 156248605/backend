package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ProjectLabor;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface ProjectLaborDao {
    void updateLaborHourInfo(ProjectLabor projectLabor);
}
