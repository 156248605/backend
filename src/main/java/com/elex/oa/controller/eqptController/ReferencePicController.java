package com.elex.oa.controller.eqptController;

import com.elex.oa.service.eqptService.ReferencePicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/reference")
public class ReferencePicController {

    @Resource
    private ReferencePicService referencePicService;

    @RequestMapping("/savePic")
    @ResponseBody
    public List saveReferencePic(HttpServletRequest request) {
        return referencePicService.saveReferencePic(request);
    }

    @RequestMapping("/showPic")
    @ResponseBody
    public List showReferencePic(HttpServletRequest request) {
        return referencePicService.showReferencePic(request);
    }


    @RequestMapping("/deletePic")
    @ResponseBody
    public String deleteReferencePic(HttpServletRequest request) {
        return referencePicService.deleteReferencePic(request);
    }
}
