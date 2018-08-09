package com.elex.oa.schedule;

import com.elex.oa.service.eqptService.InventoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InventorySchedule {
    @Resource
    private InventoryService inventoryService;

    /*@Scheduled(cron = "0 0/5 * * * ?")
    public void insertInventory(){
        inventoryService.insertInv();
    }*/
}
