package com.elex.oa.controller;
import com.elex.oa.entity.SysDic;
import com.elex.oa.entity.SysTree;
import com.elex.oa.service.ISysDicService;
import com.elex.oa.service.ISysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/3/14 11:06
*/
@RestController
@CrossOrigin
@RequestMapping("/sys")
public class SysDicController {

    private ISysDicService sysDicService;

    private ISysTreeService sysTreeService;
    @Autowired
    public SysDicController(ISysDicService sysDicService, ISysTreeService sysTreeService) {
        this.sysDicService = sysDicService;
        this.sysTreeService = sysTreeService;
    }

    @GetMapping("/getByDicKey/{dicKey}")
    public List<SysDic> getByDicKey(@PathVariable String dicKey){
        SysTree sysTree = new SysTree();
        sysTree.setKey(dicKey);
        sysTree = sysTreeService.selectOne(sysTree);
        if(sysTree==null){
            return  new ArrayList<>();
        }
        return  this.sysDicService.getByTreeId(sysTree.getTreeId());
    }









}
