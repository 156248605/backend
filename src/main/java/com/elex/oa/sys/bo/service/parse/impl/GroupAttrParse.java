package com.elex.oa.sys.bo.service.parse.impl;


import com.elex.oa.entity.OsGroup;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import com.alibaba.fastjson.JSONObject;
import java.util.List;

import com.elex.oa.sys.bo.service.parse.ParseUtil;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.WebAppUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class GroupAttrParse extends AbstractBoAttrParse {

    public GroupAttrParse() {
    }

    public String getPluginName() {
        return "mini-group";
    }

    protected void parseExt(SysBoAttr field, Element el) {
        ParseUtil.setStringLen(field, el);
        this.parseExtJson(field, el);
    }

    private void parseExtJson(SysBoAttr field, Element el) {
        String initlogindep = el.attr("initlogingroup");
        String single = el.attr("single");
        String dimId = el.attr("dimId");
        String showDim = el.attr("showdim");
        JSONObject json = new JSONObject();
        json.put("single", single);
        String required;
        if("true".equals(initlogindep)) {
            json.put("init", "true");
            json.put("showdim", showDim);
            json.put("dimId", dimId);
            required = el.attr("level");
            if(StringUtil.isNotEmpty(required)) {
                json.put("level", required);
            }
        }

        required = el.attr("required");
        if(StringUtil.isEmpty(required)) {
            required = "false";
        }

        json.put("required", required);
        field.setExtJson(json.toJSONString());
    }

    public boolean isSingleAttr() {
        return false;
    }

    public String getDescription() {
        return "用户组选择";
    }

    public JSONObject getInitData(SysBoAttr attr) {
        JSONObject jsonObj = this.getInitDep(attr);
        return jsonObj;
    }

    private JSONObject getInitDep(SysBoAttr attr) {
        GroupService osGroupManager = WebAppUtil.getBean(GroupService.class);
        String single = attr.getPropByName("single");
        String showDim = attr.getPropByName("showdim");
        String dimId = attr.getPropByName("dimId");
        Integer level = attr.getIntPropByName("level");
        IUser user = ContextUtil.getCurrentUser();
        JSONObject obj = new JSONObject();
        OsGroup group;
        if("true".equals(showDim)) {
            String list = user.getMainGroupId();
            if(StringUtil.isEmpty(list)) {
                return null;
            }

            group = (OsGroup)osGroupManager.getById(list);
            if(group != null) {
                if(level.equals(Integer.valueOf(-1))) {
                    AttrParseUtil.addKey(obj, list);
                    AttrParseUtil.addName(obj, group.getName());
                } else {
                    group = osGroupManager.getByLevel(group, level);
                    if(group != null) {
                        AttrParseUtil.addKey(obj, group.getGroupId());
                        AttrParseUtil.addName(obj, group.getName());
                    }
                }
            }
        } else {
            List list1 = osGroupManager.getByDimIdAndUserId(dimId, user.getUserId());
            if(BeanUtil.isEmpty(list1)) {
                return null;
            }

            if(level.equals(Integer.valueOf(-1))) {
                if("true".equals(single)) {
                    group = (OsGroup)list1.get(0);
                    AttrParseUtil.addKey(obj, group.getGroupId());
                    AttrParseUtil.addName(obj, group.getName());
                } else {
                    this.setJson(obj, list1);
                }
            } else {
                group = osGroupManager.getByLevel((OsGroup)list1.get(0), level);
                if(group != null) {
                    AttrParseUtil.addKey(obj, group.getGroupId());
                    AttrParseUtil.addName(obj, group.getName());
                }
            }
        }

        return obj.keySet().size() == 0?null:obj;
    }

    private void setJson(JSONObject json, List<OsGroup> groups) {
        StringBuffer gIds = new StringBuffer();
        StringBuffer gNames = new StringBuffer();

        for(int i = 0; i < groups.size(); ++i) {
            OsGroup group = (OsGroup)groups.get(i);
            if(i == 0) {
                gIds.append(group.getGroupId());
                gNames.append(group.getName());
            } else {
                gIds.append("," + group.getGroupId());
                gNames.append("," + group.getName());
            }
        }

        AttrParseUtil.addKey(json, gIds.toString());
        AttrParseUtil.addName(json, gNames.toString());
    }



}
