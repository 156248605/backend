package com.elex.oa.controller.objectives;

import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.NetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/net")
public class NetController {
    @Autowired
    private NetService netService;

    //根据年度查询税后净利信息
    @RequestMapping("/query_data")
    @ResponseBody
    public Goal2 queryData(Goal2 goal2) {
        return netService.queryData(goal2);
    }

    //保存税后净利信息
    @RequestMapping("/save_data")
    @ResponseBody
    public String saveData(Goal2 goal2) {
        return netService.saveData(goal2);
    }
}
