package com.elex.oa.schedule;

import com.elex.oa.service.project.ProjectInforService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProjectSchedule {
    @Resource
    private ProjectInforService projectInforService;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void addInfor() {
        //projectInforService.addInfor();
    }
}
