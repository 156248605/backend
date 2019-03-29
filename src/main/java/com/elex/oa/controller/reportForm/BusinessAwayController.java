package com.elex.oa.controller.reportForm;

import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.BusinessAwayService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/businessAway")
public class BusinessAwayController {
    @Resource
    private BusinessAwayService businessAwayService;

    @RequestMapping("/businessAwayForm")
    @ResponseBody
    public PageInfo businessAwayForm (HttpServletRequest request, Page page) {
        return businessAwayService.businessAwayForm(request, page);
    }
}
