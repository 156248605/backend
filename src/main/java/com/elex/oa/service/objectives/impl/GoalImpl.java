package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.GoalDao;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.GoalService;
import com.elex.oa.service.hr_service.IDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            if(StringUtils.isBlank(revenue.getTheTotal())) {

            } else {
                total1 += Double.parseDouble(revenue.getTheTotal());
            }
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
            if(StringUtils.isBlank(net.getTheTotal())) {

            } else {
                total2 += Double.parseDouble(net.getTheTotal());
            }
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
            if(StringUtils.isBlank(patent.getTheTotal())) {

            } else {
                total3 += Double.parseDouble(patent.getTheTotal());
            }
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
        List<String> list3 = new ArrayList<>();
        list3.add(patent.getAnnualTarget()+"");
        list3.add(patent.getTheTotal());
        list3.add(patent.getCompletion());
        Goal2 net = goalDao.obtainNetCon(content); //查询子公司的销售净利情况
        List<String> list2 = new ArrayList<>();
        list2.add(new BigDecimal(net.getAnnualTarget() / 10000).setScale(2,RoundingMode.UP) + "");
        list2.add(net.getTheTotal());
        list2.add(net.getCompletion());
        content.put("list",list);
        List<Goal2> grossList = goalDao.obtainGrossCon(content); //查询子公司的销售毛利情况
        List<Goal2> revenueList = goalDao.obtainRevenueCon(content); //查询子公司的销售收入情况


        List<String> list1 =  new ArrayList<>();
        double annual1 = 0, total1 = 0;
        for(Goal2 revenue: revenueList) {
            annual1 += revenue.getAnnualTarget();
            if(StringUtils.isBlank(revenue.getTheTotal())) {

            } else {
                total1 += Double.parseDouble(revenue.getTheTotal());
            }
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
        result.put("net",list2);
        result.put("patent",list3);
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

    //获取某单位的发明专利详情
    @Override
    public Goal obtainPatentUnit(String unit) {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        Map<String,String> content =  new HashMap<>();
        content.put("annual",annual);
        content.put("unit",unit);
        return goalDao.obtainPatentUnit(content);
    }

    //获取公司相应数据(手机端)
    @Override
    public List<Map<String, String>> obtainCompany(String unit) {

        if(unit.equals("全部") || unit.equals("南京总部") || unit.equals("上海臻相") || unit.equals("贵州中科")) {

        } else {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        List<Goal2> revenueList;

        List<Goal2> netList;
        Goal2 netS;

        List<Goal> patentList;
        Goal patentS;


        if(unit.equals("全部")) {
            netS = new Goal2();
            patentS = null;
            revenueList = goalDao.obtainRevenue(annual); //获取所有的销售收入
            netList = goalDao.obtainNet(annual); //获取所有的销售净利
            patentList = goalDao.obtainPatent(annual); //获取所有的发明专利
        } else {
            netList = null;
            patentList = null;
            List<Dept> depts = iDeptService.queryAllCompany1and2();
            String company = "";
            for(Dept dept:depts) {
                if(dept.getDepname().contains(unit)) {
                    company = dept.getDepname();
                }
            }
            List<Dept> depts1 = iDeptService.queryByCompanyname(company);
            List<String> deptList = new ArrayList<>();
            deptList.add(company);
            for(Dept dept1: depts1) {
                deptList.add(dept1.getDepname());
            }
            Map<String,Object> content = new HashMap<>();
            content.put("unit",company);
            content.put("annual",annual);

            content.put("list",deptList);
            revenueList = goalDao.obtainRevenueCon(content); //查询子公司的销售收入情况
            netS = goalDao.obtainNetCon(content); //查询子公司的销售净利情况
            patentS = goalDao.obtainPatentCon(content); //查询某子公司的发明专利情况
        }


        List<Map<String,String>> result = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>(), map2 = new HashMap<>(), map3 = new HashMap<>();
        double annual1 = 0, annual2 = 0,  total1 = 0, total2 = 0;
        int annual3 = 0,total3 = 0;
        for(Goal2 revenue: revenueList) {
            annual1 += revenue.getAnnualTarget();
            if(StringUtils.isBlank(revenue.getTheTotal())) {

            } else {
                total1 += Double.parseDouble(revenue.getTheTotal());
            }
        }
        map1.put("name","销售收入");
        map1.put("goal",new BigDecimal(annual1 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        map1.put("cumu",new BigDecimal(total1 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
        if(total1 == 0) {
            map1.put("ratio","");
        } else {
            String ratio1 = new BigDecimal(total1 / annual1 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
            map1.put("ratio",ratio1);
        }
        result.add(map1);

        map2.put("name","销售净利");
        if(unit.equals("全部")) {
            for(Goal2 net: netList) {
                annual2 += net.getAnnualTarget();
                if(StringUtils.isBlank(net.getTheTotal())) {

                } else {
                    total2 += Double.parseDouble(net.getTheTotal());
                }
            }
            map2.put("goal",new BigDecimal(annual2 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
            map2.put("cumu",new BigDecimal(total2 / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
            if(total2 == 0) {
                map2.put("ratio","");
            } else {
                String ratio2 = new BigDecimal(total2 / annual2 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
                map2.put("ratio",ratio2);
            }
        } else {
            if(netS == null) {
                map2.put("goal","");
                map2.put("cumu","");
                map2.put("ratio","");
            } else {
                map2.put("goal",new BigDecimal(netS.getAnnualTarget() / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
                if(StringUtils.isBlank(netS.getTheTotal())) {
                    map2.put("cumu","");
                } else {
                    map2.put("cumu",new BigDecimal(Double.parseDouble(netS.getTheTotal()) / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
                }
                map2.put("ratio",netS.getCompletion());
            }
        }
        result.add(map2);

        map3.put("name","发明专利");
        if(unit.equals("全部")) {
            for(Goal patent: patentList) {
                annual3 += patent.getAnnualTarget();
                if(StringUtils.isBlank(patent.getTheTotal())) {

                } else {
                    total3 += Double.parseDouble(patent.getTheTotal());
                }
            }
            map3.put("goal",annual3+"");
            map3.put("cumu",total3+"");
            if(total3 == 0) {
                map3.put("ratio","");
            } else {
                String ratio3 = new BigDecimal(total3 / (double)annual3 * 100).setScale(2,BigDecimal.ROUND_UP) + "%";
                map3.put("ratio",ratio3);
            }
        } else {
            if(patentS == null) {
                map3.put("goal","");
                map3.put("cumu","");
                map3.put("ratio","");
            } else {
                map3.put("goal",patentS.getAnnualTarget() +"");
                map3.put("cumu",patentS.getTheTotal());
                map3.put("ratio",patentS.getCompletion());
            }

        }

        result.add(map3);
        return result;
    }

    //获取公司销售收入或税后净利相应数据（手机端）
    @Override
    public List<Map<String, String>> obtainVarious(String unit, String various) {
        if(unit.equals("全部") || unit.equals("南京总部") || unit.equals("上海臻相") || unit.equals("贵州中科")) {

        } else {
            return null;
        }
        if(various.equals("收入") || various.equals("毛利")) {

        } else {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR)); //获取当前年份

        List<Goal2> goalList;

        if(unit.equals("全部")) {
            if(various.equals("收入")) {
                goalList = goalDao.obtainRevenue(annual); //获取所有的销售收入
            } else {
                goalList = goalDao.obtainGross(annual); //获取所有的销售毛利
            }
        } else {
            Map<String,Object> content = new HashMap<>();
            content.put("annual",annual);
            List<Dept> depts = iDeptService.queryAllCompany1and2();
            String company = "";
            for(Dept dept:depts) {
                if(dept.getDepname().contains(unit)) {
                    company = dept.getDepname();
                }
            }
            content.put("unit",company);
            List<Dept> deptList = iDeptService.queryByCompanyname(company);
            List<String> list = new ArrayList<>();
            list.add(company);
            for(Dept dept:deptList) {
                list.add(dept.getDepname());
            }
            content.put("list",list);
            if(various.equals("收入")) {
                goalList = goalDao.obtainRevenueCon(content); //查询子公司的销售收入情况
            } else {
                goalList = goalDao.obtainGrossCon(content); //查询子公司的销售毛利情况
            }
        }
        List<Map<String,String>> result = new ArrayList<>();
        for(Goal2 goal2: goalList) {
            Map<String,String> map = new HashMap<>();
            map.put("department",goal2.getUnit());
            map.put("goal",new BigDecimal(goal2.getAnnualTarget() / 10000).setScale(2,BigDecimal.ROUND_UP) + "");
            if(StringUtils.isBlank(goal2.getTheTotal())) {
                map.put("cumulative","");
            } else {
                map.put("cumulative",new BigDecimal(Double.parseDouble(goal2.getTheTotal()) / 10000).setScale(2,BigDecimal.ROUND_UP)+"");
            }
            map.put("ratio",goal2.getCompletion());
            result.add(map);
        }
        return result;
    }

    //获取单位对应的销售收入或税后净利相应数据详情（手机端）
    @Override
    public Map<String,String> obtainDetails(String unit, String various) {
        Calendar calendar = Calendar.getInstance();
        String annual = String.valueOf(calendar.get(Calendar.YEAR));
        Goal2 goal2;
        Map<String,String> content =  new HashMap<>();
        content.put("annual",annual);
        content.put("unit",unit);
        if(various.equals("收入")) {
            goal2 = goalDao.obtainRevenueUnit(content);
        } else {
            goal2 = goalDao.obtainGrossUnit(content);
        }
        Map<String,String> result = new HashMap<>();
        result.put("unit",goal2.getUnit());
        result.put("annualTarget",new BigDecimal(goal2.getAnnualTarget()).setScale(2,BigDecimal.ROUND_UP) + "");
        if(StringUtils.isBlank(goal2.getTheTotal())) {
            result.put("theTotal","");
        } else {
            result.put("theTotal",new BigDecimal(Double.parseDouble(goal2.getTheTotal())).setScale(2,BigDecimal.ROUND_UP) + "");
        }
        result.put("completion",goal2.getCompletion());
        result.put("january",new BigDecimal(goal2.getJanuary()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("february", new BigDecimal(goal2.getFebruary()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("march", new BigDecimal(goal2.getMarch()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("april", new BigDecimal(goal2.getApril()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("may", new BigDecimal(goal2.getMay()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("june", new BigDecimal(goal2.getJune()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("july", new BigDecimal(goal2.getJuly()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("august", new BigDecimal(goal2.getAugust()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("september", new BigDecimal(goal2.getSeptember()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("october", new BigDecimal(goal2.getOctober()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("november", new BigDecimal(goal2.getNovember()).setScale(2,BigDecimal.ROUND_UP) + "");
        result.put("december", new BigDecimal(goal2.getDecember()).setScale(2,BigDecimal.ROUND_UP) + "");
        return result;
    }
}
