package com.elex.oa.controller.business;

import com.elex.oa.entity.business.Clue;
import com.elex.oa.service.business.IClueService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:23
 * @Version 1.0
 **/
@RequestMapping("/clue")
@Controller
@CrossOrigin
public class ClueController {
    @Autowired
    IClueService iClueService;

    @RequestMapping("/getPageInfo")
    @ResponseBody
    public PageInfo<Clue> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Clue clue
    ){
        return iClueService.getPageInfoByCondition(page,rows,clue);
    }
}    