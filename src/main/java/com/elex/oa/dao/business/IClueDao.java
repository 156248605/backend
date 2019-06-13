package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface IClueDao extends BaseDao<Clue> {
    List<Clue> selectByCondition(Clue clue);

    List<Clue> selectByUsername(Map<String,Object> map);

    List<Clue> selectByClueAndPrincipalUsername(Map<String,Object> map);

    // 查询最新更新时间
    String getUpdateTime(String dependenceCode);

    // 查询关联商机
    List getRelativeEvent(Opportunity opportunity);

    // 根据工号查询userId
    List<HashMap<String,Object>> queryUserIdByEmployeeNumber(String employeeNumber);
}
