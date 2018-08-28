package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.GoalDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.GoalService;
import com.elex.oa.service.service_shiyun.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoalImpl implements GoalService {
    @Resource
    private GoalDao goalDao;

    @Autowired
    private IDeptService iDeptService;

    //获取总公司的数据
    @Override
    public Map<String, Object> obtainDataCentral() {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        List<Goal2> revenueList = goalDao.obtainRevenue(annual); //获取所有的销售收入
        List<Goal2> netList = goalDao.obtainNet(annual); //获取所有的销售净利
        List<Goal2> grossList = goalDao.obtainGross(annual); //获取所有的销售毛利
        List<Goal> patentList = goalDao.obtainPatent(annual); //获取所有的发明专利
        List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>();
        double annual1 = 0, annual2 = 0,  total1 = 0, total2 = 0;
        int annual3 = 0,total3 = 0;
        for(Goal2 revenue: revenueList) {
            annual1 += revenue.getAnnualTarget();
            total1 += Double.parseDouble(revenue.getTheTotal());
        }
        list1.add(new BigDecimal( annual1 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        list1.add(new BigDecimal(total1 / 10000).setScale(2,BigDecimal.ROUND_UP) + "");
        if(total1 == 0) {
            list1.add("");
        } else {
            String ratio1 = new BigDecimal(total1 / annual1 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
            list1.add(ratio1);
        }
        for(Goal2 net: netList) {
            annual2 += net.getAnnualTarget();
            total2 += Double.parseDouble(net.getTheTotal());
        }
        list2.add(new BigDecimal(annual2 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        list2.add(new BigDecimal(total2 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        if(total2 == 0) {
            list2.add("");
        } else {
            String ratio2 = new BigDecimal(total2 / annual2 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
            list2.add(ratio2);
        }
        for(Goal patent: patentList) {
            annual3 += patent.getAnnualTarget();
            total3 += Double.parseDouble(patent.getTheTotal());
        }
        list3.add(annual3+"");
        list3.add(total3+"");
        if(total3 == 0) {
            list3.add("");
        } else {
            String ratio3 = new BigDecimal(total3 / (double)annual3 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
            list3.add(ratio3);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("revenue",list1);
        result.put("net",list2);
        result.put("patent",list3);
        result.put("revenues",revenueList);
        result.put("gross",grossList);
        result.put("patents",patentList);
        return result;
    }

    //获取子公司的数据
    @Override
    public Map<String, Object> obtainDataBranch(String unit) {
        Map<String,Object> content = new HashMap<>();
        content.put("unit",unit);
        Calendar calendar =  Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        content.put("annual",annual);
        List<String> list = new ArrayList<>();
        list.add(unit);
        List<Dept> depts = iDeptService.queryByCompanyname(unit);
        for(Dept dept:depts) {
            list.add(dept.getDepname());
        }
        Goal patent = goalDao.obtainPatentCon(content); //查询某子公司的发明专利情况
        Goal2 net = goalDao.obtainNetCon(content); //查询子公司的销售净利情况
        List<Goal2> grossList = goalDao.obtainGrossCon(content); //查询子公司的销售毛利情况
        content.put("list",list);
        List<Goal2> revenueList = goalDao.obtainRevenueCon(content); //查询子公司的销售收入情况
        System.out.println(revenueList);

        List<String> list1 =  new ArrayList<>();
        double annual1 = 0, total1 = 0;
        for(Goal2 revenue: revenueList) {
            annual1 += revenue.getAnnualTarget();
            total1 += Double.parseDouble(revenue.getTheTotal());
        }
        list1.add(new BigDecimal(annual1 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        list1.add(new BigDecimal(total1 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        if(annual1 == 0){
            list1.add("");
        } else {
            String ratio1 = new BigDecimal( total1 / annual1 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
            list1.add(ratio1);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("revenue",list1);
        result.put("net",net);
        result.put("patent",patent);
        result.put("revenueList",revenueList);
        result.put("grossList",grossList);
        return result;
    }

    //获取某单位的销售收入详情
    @Override
    public Goal2 obtainRevenueUnit(String unit) {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        Map<String,String> content =  new HashMap<>();
        content.put("annual",annual);
        content.put("unit",unit);
        return goalDao.obtainRevenueUnit(content);
    }

    //获取某单位的销售毛利详情
    @Override
    public Goal2 obtainGrossUnit(String unit) {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        Map<String,String> content =  new HashMap<>();
        content.put("annual",annual);
        content.put("unit",unit);
        return goalDao.obtainGrossUnit(content);
    }

    //获取某单位的销售毛利详情
    @Override
    public Goal obtainPatentUnit(String unit) {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        Map<String,String> content =  new HashMap<>();
        content.put("annual",annual);
        content.put("unit",unit);
        return goalDao.obtainPatentUnit(content);
    }
}
