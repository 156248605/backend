package com.elex.oa.controller.reportForm;

import com.elex.oa.service.reportForm.PersonDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/personDetail")
@Controller
@CrossOrigin
public class PersonDetailController {

    @Resource
    private PersonDetailService personDetailService;

    @RequestMapping("/userForm")
    @ResponseBody
    public List userForm (HttpServletRequest request) {
        return personDetailService.userForm(request);
    }

    @RequestMapping("/menuAndRoleForm")
    @ResponseBody
    public List menuAndRoleForm (HttpServletRequest request) {
        return personDetailService.menuAndRole(request);
    }

    @RequestMapping("/clueAndOpporitunityAndProjectInfo")
    @ResponseBody
    public Map allInfoByCOP (HttpServletRequest request) {
        return personDetailService.allInfoByCOP(request);
    }
}
