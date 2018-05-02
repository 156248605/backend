package com.elex.oa.controller;
import com.elex.oa.entity.BpmSolUser;
import com.elex.oa.entity.BpmSolUsergroup;
import com.elex.oa.identity.service.IdentityCalService;
import com.elex.oa.identity.service.IdentityTypeService;
import com.elex.oa.service.IBpmSolUserService;
import com.elex.oa.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/24 17:35
*/
@Controller
@RequestMapping({"/bpm/core/bpmSolUser/"})
public class BpmSolUserController {

    @Autowired
    IdentityTypeService identityTypeService;

    @Autowired
    IBpmSolUserService bpmSolUserService;

    @RequestMapping({"getUserTypes"})
    @ResponseBody
    public Collection<IdentityCalService> getUserTypes(HttpServletRequest request, HttpServletResponse response) throws Exception{
       return this.identityTypeService.getIdentityCalServices();
    }

    @RequestMapping({"getUserBySolIdNodeId"})
    @ResponseBody
    public List<BpmSolUser> getUserBySolIdNodeId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        String nodeId = request.getParameter("nodeId");
        String groupType = RequestUtil.getString(request, "groupType", BpmSolUsergroup.GROUP_TYPE_TASK);
        //List solUsers = this.bpmSolUserManager.getBySolIdActDefIdNodeId(solId, actDefId, nodeId, groupType);
        return this.bpmSolUserService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId, groupType);
    }






}
