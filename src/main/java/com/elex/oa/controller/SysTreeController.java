package com.elex.oa.controller;

import com.elex.oa.entity.SysTree;
import com.elex.oa.service.ISysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    private ISysTreeService sysTreeService;
    @Autowired
    public SysTreeController(ISysTreeService sysTreeService) {
        this.sysTreeService = sysTreeService;
    }
    @RequestMapping({"/listAllByCatKey"})
    public List<SysTree> listAllByCatKey(HttpServletRequest request){
        String catKey = request.getParameter("catKey");
        Map<String,String> map = new HashMap<>();
        map.put("catKey",catKey);
        map.put("tenantId","1");
        return sysTreeService.selectByCatKey(map);
    }
    @RequestMapping("/listDicTree")
    public List<SysTree> listDicTree(){
        Map<String,String> map = new HashMap<>();
        map.put("catKey","CAT_DIM");
        map.put("tenantId","1");
        return this.sysTreeService.selectByCatKey(map);



    }



}
