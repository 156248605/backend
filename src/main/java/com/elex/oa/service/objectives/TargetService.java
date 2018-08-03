package com.elex.oa.service.objectives;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.objectives.Target;
import com.elex.oa.entity.objectives.TargetQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TargetService {
    //查询年度信息
    Map<String,Object> queryAnnual();
    //查询销售收入列表信息
    PageInfo<Target> querySalesList(TargetQuery targetQuery, Page page);
    //查询税后净利列表信息
    PageInfo<Target> queryNetList(TargetQuery targetQuery, Page page);
    //查询发明专利列表信息
    PageInfo<Target> queryInventionList(TargetQuery targetQuery, Page page);
    //添加销售收入
    String addSales(Target target);
    //添加税后净利
    String addNet(Target target);
    //添加发明专利
    String addInvention(Target target);
    //修改销售收入
    String amendSales(Target target);
    //修改税后净利
    String amendNet(Target target);
    //修改发明专利
    String amendInvention(Target target);
    //管理总板
    Map<String,Object> boardTotal(String annual);
    //管理看板（手机端）
    List<Map<String, String>> boardPhone();
    //管理看板项目部门相关
    List<Target> boardDepartment(String department, String annual);
    //查看销售收入或税后净利（手机）
    List<Target> boardVarious(String various);
}
