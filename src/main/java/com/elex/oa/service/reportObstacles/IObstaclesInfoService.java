package com.elex.oa.service.reportObstacles;

import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IObstaclesInfoService {
    Boolean addObstaclesInfo(ObstaclesInfo obstaclesInfo);

    List<ObstaclesInfo> queryAllObstaclesInfo();

    PageInfo<ObstaclesInfo> queryObstaclesByConditions(Integer pageNum, Integer pageSize, ObstaclesQueryInfo obstaclesQueryInfo);

    Object changeObstaclesState(String id, String flag, String location_description, String process_description);
}
