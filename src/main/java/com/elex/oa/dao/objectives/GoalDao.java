package com.elex.oa.dao.objectives;

import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.entity.objectives.Goal2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoalDao {
    //获取所有的销售收入
    List<Goal2> obtainRevenue(String annual);
    //获取所有的销售净利
    List<Goal2> obtainNet(String annual);
    //获取所有的销售毛利
    List<Goal2> obtainGross(String annual);
    //获取所有的发明专利
    List<Goal> obtainPatent(String annual);
    //查询某子公司的发明专利情况
    Goal obtainPatentCon(Map<String,Object> content);
    //查询子公司的销售净利情况
    Goal2 obtainNetCon(Map<String,Object> content);
    //查询子公司的销售毛利情况
    List<Goal2> obtainGrossCon(Map<String,Object> content);
    //查询子公司的销售收入情况
    List<Goal2> obtainRevenueCon(Map<String,Object> content);
    //获取某单位的销售收入详情
    Goal2 obtainRevenueUnit(Map<String,String> content);
    //获取某单位的销售毛利详情
    Goal2 obtainGrossUnit(Map<String,String> content);
    //获取某单位的销售毛利详情
    Goal obtainPatentUnit(Map<String,String> content);

}
