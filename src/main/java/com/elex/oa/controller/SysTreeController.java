package com.elex.oa.controller;

import com.elex.oa.entity.SysTree;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:33
*/
@RestController
@CrossOrigin
@RequestMapping("/sysTree")
public class SysTreeController {
/*    @RequestMapping({"/listAllByCatKey"})
    public List<SysTree> listAllByCatKey(HttpServletRequest request){
        String catKey = request.getParameter("catKey");
        Map<String,String> map = new HashMap<>();
        map.put("catKey",catKey);
        map.


    }*/


}
