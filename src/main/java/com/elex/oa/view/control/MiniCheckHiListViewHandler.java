package com.elex.oa.view.control;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MiniCheckHiListViewHandler implements MiniViewHanlder {
    public MiniCheckHiListViewHandler() {
    }

    public String getPluginName() {
        return "mini-checkhilist";
    }

    public void parse(Element el, Map<String, Object> params, JSONObject jsonObj) {
        String actInstId = (String)params.get("actInstId");
        String gridUrl = params.get("ctxPath") + "/bpm/core/bpmNodeJump/listForInstShowName.do?actInstId=" + actInstId;
        String gridDiv = " <div id=\'checkhilist\' class=\'mini-datagrid\' style=\'width:1024px;height:auto;\' allowResize=\'true\'  showPager=\'false\'  url=\'" + gridUrl + "\'  idField=\'jumpId\' >" + "<div property=\'columns\'>" + "<div field=\'createBy\' width=\'80\' headerAlign=\'center\' >提交人</div>" + "<div field=\'createTime\' width=\'120\' headerAlign=\'center\'  dateFormat=\'yyyy-MM-dd HH:mm:ss\'>发送日期</div>" + "<div field=\'handlerId\' width=\'80\' headerAlign=\'center\'>审批人</div>" + "<div field=\'jumpType\' width=\'100\' headerAlign=\'center\'>审批意见</div>" + "<div field=\'completeTime\' width=\'120\' headerAlign=\'center\' dateFormat=\'yyyy-MM-dd HH:mm:ss\'>处理日期</div>" + "<div field=\'remark\' width=\'180\' headerAlign=\'center\' >批语</div>" + "</div>" + "</div>";
        Document doc = Jsoup.parseBodyFragment(gridDiv);
        el.after(doc.body().html());
        el.remove();
    }

    public void convertToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj) {
    }
}