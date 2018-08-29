package com.elex.oa.controller.objectives;

import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.service.objectives.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    private PatentService patentService;

    //根据年度查询发明专利信息
    @RequestMapping("/query_data")
    @ResponseBody
    public Goal queryData(Goal goal) {
        return patentService.queryData(goal);
    }

    //保存发明专利信息
    @RequestMapping("/save_data")
    @ResponseBody
    public String saveData(Goal goal) {
        return patentService.saveData(goal);
    }
}
