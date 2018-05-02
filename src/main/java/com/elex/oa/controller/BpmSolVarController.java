package com.elex.oa.controller;

import com.elex.oa.entity.activiti.BpmSolVar;
import com.elex.oa.service.activiti.BpmSolVarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/24 16:29
*/
@Controller
@RequestMapping({"/bpm/core/bpmSolVar/"})
public class BpmSolVarController {

    @Autowired
    private BpmSolVarService bpmSolVarService;

    @RequestMapping({"getBySolIdActDefIdNodeId"})
    @ResponseBody
    public List<BpmSolVar> getBySolIdActDefIdNodeId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nodeId = request.getParameter("nodeId");
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
       // List vars = this.bpmSolVarManager.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        return this.bpmSolVarService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
    }






}
