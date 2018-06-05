package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.dao.IOsGroupDao;
import com.elex.oa.entity.OsGroup;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.elex.oa.sys.bo.service.parse.ParseUtil;
import javax.annotation.Resource;

import com.elex.oa.util.StringUtil;
import com.elex.oa.util.WebAppUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class DeptAttrParse extends AbstractBoAttrParse {
    @Resource
    GroupService groupService;
    public DeptAttrParse() {
    }

    public String getPluginName() {
        return "mini-dep";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        this.parseExtJson(field, el);
    }

    private void parseExtJson(SysBoAttr field, Element el) {
        String initlogindep = el.attr("initlogindep");
        String single = el.attr("single");
        JSONObject json = new JSONObject();
        String required;
        if("true".equals(initlogindep)) {
            json.put("init", "true");
            required = el.attr("level");
            if(StringUtil.isNotEmpty(required)) {
                json.put("level", required);
            }
        }

        json.put("single", single);
        required = el.attr("required");
        if(StringUtil.isEmpty(required)) {
            required = "false";
        }

        json.put("required", required);
        field.setExtJson(json.toJSONString());
    }

    public JSONObject getInitData(SysBoAttr attr) {
        JSONObject jsonObj = getInitDep(attr);
        return jsonObj;
    }

    private static JSONObject getInitDep(SysBoAttr attr) {
        String initlogindep = attr.getPropByName("init");
        Integer level = attr.getIntPropByName("level");
        if(!"true".equals(initlogindep)) {
            return null;
        } else {
            GroupService groupService = WebAppUtil.getBean(GroupService.class);
            String groupId = ContextUtil.getCurrentUser().getMainGroupId();
            if(StringUtil.isEmpty(groupId)) {
                return null;
            } else {
                OsGroup group = (OsGroup)groupService.getById(groupId);
                JSONObject obj = new JSONObject();
                if(!level.equals(Integer.valueOf(-1)) && !group.getRankLevel().equals(level)) {
                    group = groupService.getByLevel(group, level);
                    if(group != null) {
                        AttrParseUtil.addKey(obj, group.getGroupId());
                        AttrParseUtil.addName(obj, group.getName());
                    }
                } else {
                    AttrParseUtil.addKey(obj, groupId);
                    AttrParseUtil.addName(obj, group.getName());
                }

                return obj.keySet().size() == 0?null:obj;
            }
        }
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "部门选择";
    }

}
