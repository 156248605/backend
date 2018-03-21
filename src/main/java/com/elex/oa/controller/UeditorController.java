package com.elex.oa.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 用于处理关于ueditor插件相关的请求
 *@author hugo.zhao
 *@since 2018/3/8 15:44
*/
@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {
//    @RequestMapping(value = "/exec")
//    public String exec(HttpServletRequest request) throws UnsupportedEncodingException{
//        request.setCharacterEncoding("utf-8");
//        String rootPath = request.getRealPath("/");
//        return new ActionEnter(request,rootPath).exec();
//    }

    @RequestMapping(
            value = {"/exec"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public void exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if("config".equals(action)) {
            ServletOutputStream os = response.getOutputStream();
            FileInputStream is = new FileInputStream(ResourceUtils.getFile("classpath:ueditor-config.json"));
            IOUtils.copy(is, os);
        }

    }
}
