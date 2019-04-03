package com.elex.oa.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.Project;
import com.elex.oa.entity.project.ProjectAmend;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.service.project.ProjectInforService;
import com.elex.oa.service.project.impl.ProjectAmendImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 *@author hugo.zhao
 *@since 2019/4/2 13:17
*/
@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectInforControllerTest {

    @Autowired
    private ProjectInforService projectInforService;
    @Autowired
    private ProjectAmendImpl projectAmendService;

    @Test
    void amendProJson() {
        String userId = "1";
        ProjectAmend  projectAmend =  projectAmendService.getById("63");
        ProjectInfor projectInfor = (ProjectInfor)JSON.parse(projectAmend.getProject_json());
        projectInforService.amendPro(projectInfor,userId);
    }
}