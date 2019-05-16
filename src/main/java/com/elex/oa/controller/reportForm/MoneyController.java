package com.elex.oa.controller.reportForm;

import com.elex.oa.service.reportForm.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/money")
public class MoneyController {

    @Resource
    private MoneyService moneyService;

    @ResponseBody
    @RequestMapping("/moneyForm")
    public List moneyForm (HttpServletRequest request) {
        return moneyService.moneyForm(request);
    }
}
