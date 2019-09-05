package com.elex.oa.controller.reportForm;

import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.OvertimeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@CrossOrigin
@Controller
@RequestMapping("/overtime")
public class OvertimeController {

    @Resource
    private OvertimeService overtimeService;

    @RequestMapping("/overtimeForm")
    @ResponseBody
    public PageInfo overtimeForm (HttpServletRequest request, Page page) throws ParseException {
        return overtimeService.overtimeForm(request, page);
    }
}
