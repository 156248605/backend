package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IClueDao extends BaseDao<Clue> {
    List<Clue> selectByCondition(Clue clue);

    List<Clue> selectByClueAndPrincipalUsername(Clue clue);

    // 查询最新更新时间
    String getUpdateTime(String dependenceCode);

    // 查询关联商机
    List getRelativeEvent(Opportunity opportunity);
}
