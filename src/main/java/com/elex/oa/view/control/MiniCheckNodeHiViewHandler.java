package com.elex.oa.view.control;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.UserService;
import com.elex.oa.service.IBpmNodeJumpService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniCheckNodeHiViewHandler implements MiniViewHanlder {
    @Resource
    private IBpmNodeJumpService bpmNodeJumpManager;
    @Resource
    private UserService userService;

    public MiniCheckNodeHiViewHandler() {
    }

    public String getPluginName() {
        return "mini-checknodehi";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String nodeId = el.attr("nodeid");
        String actInstId = (String)params.get("actInstId");
        if(StringUtils.isNotBlank(nodeId) && StringUtils.isNotEmpty(actInstId)) {
            List jumpList = this.bpmNodeJumpManager.getByActInstIdNodeId(actInstId, nodeId);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Element ulEl = new Element(Tag.valueOf("ul"), "");
            Iterator var9 = jumpList.iterator();

            while(var9.hasNext()) {
                BpmNodeJump jump = (BpmNodeJump)var9.next();
                if(StringUtils.isNotEmpty(jump.getHandlerId()) && jump.getCompleteTime() != null) {
                    IUser osUser = this.userService.getByUserId(jump.getHandlerId());
                    String fullname = osUser != null?osUser.getFullname():"用户(" + jump.getHandlerId() + ")";
                    String datetime = df.format(jump.getCompleteTime());
                    Element liEl = (new Element(Tag.valueOf("li"), "")).text(fullname + "进行了" + jump.getCheckStatusText() + "操作(" + datetime + "),审批意见为：" + jump.getRemark());
                    ulEl.appendChild(liEl);
                }
            }

            el.replaceWith(ulEl);
        } else {
            el.replaceWith((new Element(Tag.valueOf("span"), "")).text("暂无审批意见~"));
        }

    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    }
}
