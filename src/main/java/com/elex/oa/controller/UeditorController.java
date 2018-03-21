package com.elex.oa.controller;
import com.elex.oa.common.ueditor.ActionEnter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
/**
 * 用于处理关于ueditor插件相关的请求
 *@author hugo.zhao
 *@since 2018/3/8 15:44
*/
@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {
    @RequestMapping(value = "/exec")
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException{
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        return new ActionEnter(request,rootPath).exec();
    }


}
