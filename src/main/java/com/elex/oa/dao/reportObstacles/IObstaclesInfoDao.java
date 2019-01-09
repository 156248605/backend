package com.elex.oa.dao.reportObstacles;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Connection;

import java.util.List;

@Mapper
public interface IObstaclesInfoDao extends BaseDao<ObstaclesInfo> {
    List<ObstaclesInfo> selectByConditions(ObstaclesQueryInfo obstaclesQueryInfo);
}
