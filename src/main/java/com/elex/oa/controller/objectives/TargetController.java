package com.elex.oa.controller.objectives;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.objectives.Target;
import com.elex.oa.entity.objectives.TargetQuery;
import com.elex.oa.service.objectives.TargetService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/target")
public class TargetController {

    @Autowired
    private TargetService targetService;

    //查询年度信息
    @RequestMapping("/query_annual")
    @ResponseBody
    public Map<String,Object> queryAnnual() {
        return targetService.queryAnnual();
    }

    //查询销售收入列表信息
    @RequestMapping("/query_sales_list")
    @ResponseBody
    public PageInfo<Target> querySalesList(TargetQuery targetQuery, Page page) {
        return targetService.querySalesList(targetQuery, page);
    }

    //查询税后净利列表信息
    @RequestMapping("/query_net_list")
    @ResponseBody
    public PageInfo<Target> queryNetList(TargetQuery targetQuery, Page page) {
        return targetService.queryNetList(targetQuery, page);
    }

    //查询发明专利列表信息
    @RequestMapping("/query_invention_list")
    @ResponseBody
    public PageInfo<Target> queryInventionList(TargetQuery targetQuery, Page page) {
        return targetService.queryInventionList(targetQuery, page);
    }

    //添加销售收入
    @RequestMapping("/add_sales")
    @ResponseBody
    public String addSales(Target target) {
        return targetService.addSales(target);
    }

    //添加税后净利
    @RequestMapping("/add_net")
    @ResponseBody
    public String addNet(Target target) {
        return targetService.addNet(target);
    }

    //添加发明专利
    @RequestMapping("/add_invention")
    @ResponseBody
    public String addInvention(Target target) {
        return targetService.addInvention(target);
    }

    //修改销售收入
    @RequestMapping("/amend_sales")
    @ResponseBody
    public String amendSales(Target target) {
        return targetService.amendSales(target);
    }

    //修改税后净利
    @RequestMapping("/amend_net")
    @ResponseBody
    public String amendNet(Target target) {
        return targetService.amendNet(target);
    }

    //修改发明专利
    @RequestMapping("/amend_invention")
    @ResponseBody
    public String amendInvention(Target target) {
        return targetService.amendInvention(target);
    }

    //管理总板
    @RequestMapping("/board_total")
    @ResponseBody
    public Map<String,Object> boardTotal(String annual) {
        return targetService.boardTotal(annual);
    }
}
