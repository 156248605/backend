package com.elex.oa.controller.reportForm;

import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.BusinessTravelService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/businessTravel")
public class BusinessTravelController {

    @Resource
    private BusinessTravelService businessTravelService;

    @RequestMapping("/businessTravelForm")
    @ResponseBody
    public PageInfo businessTravelForm (HttpServletRequest request, Page page) {
        return businessTravelService.businessTravelForm(request,page);
    }
}
