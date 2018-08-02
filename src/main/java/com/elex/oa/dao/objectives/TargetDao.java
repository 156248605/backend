package com.elex.oa.dao.objectives;

import com.elex.oa.entity.objectives.Target;
import com.elex.oa.entity.objectives.TargetQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TargetDao {
    //查询销售收入的本年年度信息
    List<Target> querySalesAnnual(String year);
    //查询税后净利的本年年度信息
    List<Target> queryNetAnnual(String year);
    //查询发明专利的本年年度信息
    Target queryInventionAnnual(String year);
    //查询销售收入列表信息
    List<Target> querySalesList(TargetQuery targetQuery);
    //查询税后净利列表信息
    List<Target> queryNetList(TargetQuery targetQuery);
    //查询发明专利列表信息
    List<Target> queryInventionList(TargetQuery targetQuery);
    //添加销售收入
    void addSales(Target target);
    //添加税后净利
    void addNet(Target target);
    //添加发明专利
    void addInvention(Target target);
    //修改销售收入
    void amendSales(Target target);
    //修改税后净利
    void amendNet(Target target);
    //修改发明专利
    void amendInvention(Target target);
    //查询某部门的销售收入
    Target querySalesDepartment(Map<String,String> condition);
    //查询某部门的收入净利
    Target queryNetDepartment(Map<String,String> condition);
}
