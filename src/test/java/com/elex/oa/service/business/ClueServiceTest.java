package com.elex.oa.service.business;

import com.elex.oa.entity.business.Clue;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:51
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClueServiceTest {
    @Autowired
    IClueService iClueService;

    @Test
    public void getPageInfoByCondition_test1(){
        /*PageInfo<Clue> cluePageInfo = iClueService.getPageInfoByCondition(1, 10, null);
        System.out.println(cluePageInfo.toString());
        System.out.println(cluePageInfo.getList().get(0).toString());*/
    }
}    