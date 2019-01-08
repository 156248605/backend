package com.elex.oa.service.reportObstacles;

import com.elex.oa.entity.reportObstacles.ObstaclesInfo;

import java.util.List;

public interface IObstaclesInfoService {
    Boolean addObstaclesInfo(ObstaclesInfo obstaclesInfo);

    List<ObstaclesInfo> queryAllObstaclesInfo();
}
