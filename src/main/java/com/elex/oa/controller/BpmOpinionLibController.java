package com.elex.oa.controller;

import com.elex.oa.entity.BpmOpinionLib;
import com.elex.oa.json.JsonResult;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.IBpmOpinionLibService;
import com.elex.oa.util.IdGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping({"/bpm/core/bpmOpinionLib/"})
public class BpmOpinionLibController {

    @Resource
    private IBpmOpinionLibService bpmOpinionLibManager;

    @Resource
    protected IdGenerator idGenerator;

    @RequestMapping({"getUserText"})
    @ResponseBody
    public List<BpmOpinionLib> getUserText(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = ContextUtil.getCurrentUserId();
        return this.bpmOpinionLibManager.getByUserId(userId);
    }

    @RequestMapping({"saveOpinion"})
    @ResponseBody
/*    @LogEnt(
            action = "saveOpinion",
            module = "流程",
            submodule = "用户审批意见表"
    )*/
    public JsonResult saveOpinion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String opText = request.getParameter("opText");
        String userId = ContextUtil.getCurrentUserId();
        String userName = ContextUtil.getCurrentUser().getFullname();
        Cookie[] cookies = request.getCookies();
        System.out.println(ContextUtil.getCurrentUserId());
        System.out.println(userId);
        if(this.bpmOpinionLibManager.isOpinionSaved(userId, opText)) {
            return new JsonResult(true, "已经收藏过了！");
        } else {
            BpmOpinionLib lib = new BpmOpinionLib();
            lib.setOpId(this.idGenerator.getSID());
            lib.setUserId(userId);
            lib.setOpText(opText);
            this.bpmOpinionLibManager.save(lib);
            return new JsonResult(true, "成功收藏！");
        }
    }

}
