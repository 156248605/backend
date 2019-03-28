package com.elex.oa.controller.reportForm;

import com.elex.oa.service.reportForm.LeaveService;
import com.elex.oa.entity.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/leave")
public class LeaveController {
    @Resource
    private LeaveService leaveService;

    @RequestMapping("/leaveForm")
    @ResponseBody
    public PageInfo leaveForm (HttpServletRequest request, Page page) {
        return leaveService.leaveForm(request, page);
    }
}
