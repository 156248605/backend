package com.elex.oa.controller.project;

import com.elex.oa.entity.project.ProjectRecord;
import com.elex.oa.service.project.ProjectRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/record")
public class ProjectRecordController {

    @Resource
    private ProjectRecordService projectRecordService;

    //查询修改记录
    @RequestMapping("/query_list")
    @ResponseBody
    public List<ProjectRecord> queryList(String projectCode) {
        return projectRecordService.queryList(projectCode);
    }
}
