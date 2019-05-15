package com.elex.oa.controller.reportForm;

import com.elex.oa.service.reportForm.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/cost")
public class CostController {

    @Resource
    private CostService costService;

    @ResponseBody
    @RequestMapping("/costForm")
    public List serveForm (HttpServletRequest request) {
        return costService.serveForm(request);
    }
}
