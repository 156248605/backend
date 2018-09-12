package com.elex.oa.schedule;

import com.elex.oa.service.project.ProjectBoardService;
import com.elex.oa.service.project.ProjectInforService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProjectSchedule {
    @Resource
    private ProjectInforService projectInforService;

    @Resource
    private ProjectBoardService projectBoardService;

    @Scheduled(cron = "0 0/4 * * * ?")
    public void addInfor() {
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void updateStaff() {
        //projectBoardService.informationUpdate();
    }
}
