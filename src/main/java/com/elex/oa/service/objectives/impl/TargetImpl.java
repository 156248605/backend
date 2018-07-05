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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
