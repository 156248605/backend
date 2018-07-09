package com.elex.oa.service.objectives.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.objectives.TargetDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.objectives.Target;
import com.elex.oa.entity.objectives.TargetQuery;
import com.elex.oa.service.objectives.TargetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TargetImpl implements TargetService {

    @Resource
    private TargetDao targetDao;

    //查询年度信息
    @Override
    public Map<String, Object> queryAnnual() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(new Date());
        List<Target> salesList = targetDao.querySalesAnnual(year);
        List<Target> netList = targetDao.queryNetAnnual(year);
        Target invention = targetDao.queryInventionAnnual(year);

        Map<String,Object> map = new HashMap<>();
        map.put("sales",salesList);
        map.put("net",netList);
        if(invention == null) {
            map.put("invention","y");
        } else {
            map.put("invention","n");
        }
        return map;
    }

    //查询销售收入列表信息
    @Override
    public PageInfo<Target> querySalesList(TargetQuery targetQuery, Page page) {
        List<String> list2 = JSONArray.parseArray(targetQuery.getSelect2(),String.class);
        if(list2.size() > 0) {
            targetQuery.setList2(list2);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Target> list = targetDao.querySalesList(targetQuery);
        return new PageInfo<Target>(list);
    }

    //查询税后净利列表信息
    @Override
    public PageInfo<Target> queryNetList(TargetQuery targetQuery, Page page) {
        List<String> list2 = JSONArray.parseArray(targetQuery.getSelect2(),String.class);
        if(list2.size() > 0) {
            targetQuery.setList2(list2);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Target> list = targetDao.queryNetList(targetQuery);
        return new PageInfo<Target>(list);
    }

    //查询发明专利列表信息
    @Override
    public PageInfo<Target> queryInventionList(TargetQuery targetQuery, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Target> list = targetDao.queryInventionList(targetQuery);
        return new PageInfo<Target>(list);
    }

    //添加销售收入
    @Override
    public String addSales(Target target) {
        targetDao.addSales(target);
        return "1";
    }

    //添加税后净利
    @Override
    public String addNet(Target target) {
        targetDao.addNet(target);
        return "1";
    }

    //添加发明专利
    @Override
    public String addInvention(Target target) {
        targetDao.addInvention(target);
        return "1";
    }


    //修改销售收入
    @Override
    public String amendSales(Target target) {
        targetDao.amendSales(target);
        return "1";
    }

    //修改税后净利
    @Override
    public String amendNet(Target target) {
        targetDao.amendNet(target);
        return "1";
    }

    //修改发明专利
    @Override
    public String amendInvention(Target target) {
        targetDao.amendInvention(target);
        return "1";
    }


    //管理总板
    @Override
    public Map<String, Object> boardTotal(String annual) {
        List<Target> salesList = targetDao.querySalesAnnual(annual);
        List<Target> netList = targetDao.queryNetAnnual(annual);
        Target invention = targetDao.queryInventionAnnual(annual);
        Map<String,Object> map = new HashMap<>();
        map.put("sales",salesList);
        map.put("net",netList);
        map.put("invention", invention);
        return map;
    }

    //管理看板（手机端）
    @Override
    public List<Map<String, String>> boardPhone() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String annual = simpleDateFormat.format(new Date());
        List<Target> salesList = targetDao.querySalesAnnual(annual);
        List<Target> netList = targetDao.queryNetAnnual(annual);
        Target invention = targetDao.queryInventionAnnual(annual);

        double goal1 = 0, goal2 = 0, cumulative1 = 0, cumulative2 = 0, ratio1 = 0, ratio2 = 0, ratio3 = 0;
        for(Target target: salesList) {
            goal1 += Double.parseDouble(target.getGoal());
            if(target.getCumulative().equals("")) {

            } else {
                cumulative1 += Double.parseDouble(target.getCumulative());
            }
        }
        Map<String,String> map1 = new HashMap<>();
        goal1 = new BigDecimal(goal1 / 10000).setScale(2,RoundingMode.UP).doubleValue();
        map1.put("name","销售收入");
        map1.put("goal",goal1+"");
        cumulative1 = new BigDecimal(cumulative1 / 10000).setScale(2,RoundingMode.UP).doubleValue();
        ratio1 = new BigDecimal((cumulative1 / goal1) * 100).setScale(2,RoundingMode.UP).doubleValue();
        map1.put("cumu",cumulative1+"");
        map1.put("ratio",ratio1+"");

        for(Target target: netList) {
            goal2 += Double.parseDouble(target.getGoal());
            if(target.getCumulative().equals("")) {

            } else {
                cumulative2 += Double.parseDouble(target.getCumulative());
            }
        }
        Map<String,String> map2 = new HashMap<>();
        goal2 = new BigDecimal( goal2 / 10000).setScale(2,RoundingMode.UP).doubleValue();
        cumulative2 = new BigDecimal(cumulative2 / 10000).setScale(2,RoundingMode.UP).doubleValue();
        ratio2 = new BigDecimal( (cumulative2 / goal2) * 100).setScale(2,RoundingMode.UP).doubleValue();
        map2.put("name","税后净利");
        map2.put("goal",goal2+"");
        map2.put("cumu", cumulative2+"");
        map2.put("ratio",ratio2+"");

        Map<String,String> map3 = new HashMap<>();
        map3.put("name","发明专利");
        map3.put("goal",invention.getGoal());
        if(invention.getCumulative().equals("")) {
            map3.put("cumu","0");
        } else {
            map3.put("cumu",invention.getCumulative());
        }
        if(invention.getGoal().equals("") || invention.getCumulative().equals("")) {
            map3.put("ratio","0.00");
        } else {
            ratio3 = new BigDecimal( ( Double.parseDouble(invention.getCumulative()) / Double.parseDouble(invention.getGoal())) * 100).setScale(2,RoundingMode.UP).doubleValue();
            map3.put("ratio",ratio3+"");
        }
        List<Map<String,String>> list =  new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }
}
