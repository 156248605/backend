package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MoneyDao {
    // 获取申请单及费用
    List<HashMap<String, Object>> getFormByProject(@Param("projectId") String projectId, @Param("formName") String formName);
}
