package com.elex.oa.sys.bo.service.parse.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.entity.core.SysSeqId;
import com.elex.oa.script.GroovyEngine;
import com.elex.oa.service.ISysSeqIdService;
import com.elex.oa.sys.bo.service.parse.AbstractBoAttrParse;
import java.util.HashMap;
import javax.annotation.Resource;

import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class TextBoxAttrParse extends AbstractBoAttrParse {
    @Resource
    ISysSeqIdService sysSeqIdManager;
    @Resource
    GroovyEngine groovyEngine;

    public TextBoxAttrParse() {
    }

    protected void parseExt(SysBoAttr field, Element el) {
        String datatype = el.attr("datatype");
        if(StringUtil.isEmpty(datatype)) {
            field.setDataType("varchar");
            field.setLength(Integer.valueOf(100));
        } else {
            field.setDataType(datatype);
            int decimal;
            if("varchar".equals(datatype)) {
                String length = el.attr("length").trim();
                decimal = this.getLength(length, 50);
                field.setLength(Integer.valueOf(decimal));
            } else if("number".equals(datatype)) {
                int length1 = this.getLength(el.attr("length").trim(), 14);
                decimal = this.getLength(el.attr("decimal").trim(), 0);
                field.setLength(Integer.valueOf(length1));
                field.setDecimalLength(Integer.valueOf(decimal));
            }
        }

        this.parseExtJson(field, el);
    }

    private void parseExtJson(SysBoAttr field, Element el) {
        JSONObject json = new JSONObject();
        String from = el.attr("from");
        String vtype;
        if(StringUtil.isNotEmpty(from)) {
            json.put("from", from);
            if("sequence".equals(from)) {
                vtype = el.attr("sequence");
                json.put("sequence", vtype);
            } else if("scripts".equals(from)) {
                vtype = el.attr("scripts");
                json.put("scripts", vtype);
            }
        }

        vtype = el.attr("vtype");
        if(StringUtil.isNotEmpty(vtype)) {
            json.put("vtype", vtype);
        }

        String required = el.attr("required");
        if(StringUtil.isNotEmpty(required) && "true".equals(required)) {
            json.put("required", required);
        }

        String dataOptions = el.attr("data-options");
        if(StringUtil.isNotEmpty(dataOptions)) {
            json.put("conf", dataOptions);
        }

        if(json.size() > 1) {
            String jsonStr = json.toJSONString();
            field.setExtJson(jsonStr);
        }

    }

    public JSONObject getInitData(SysBoAttr attr) {
        String from = attr.getPropByName("from");
        if(StringUtil.isEmpty(from)) {
            return null;
        } else {
            JSONObject jsonObject = new JSONObject();
            String scripts;
            if("sequence".equals(from)) {
                scripts = attr.getPropByName("sequence");
                if(StringUtil.isNotEmpty(scripts)) {
                    SysSeqId rtn = (SysSeqId)this.sysSeqIdManager.getById(scripts);
                    if(rtn == null) {
                        return null;
                    }

                    String alias = rtn.getAlias();
                    String no = this.sysSeqIdManager.genSequenceNo(alias, "1");
                    AttrParseUtil.addKey(jsonObject, no);
                }
            } else if("scripts".equals(from)) {
                scripts = attr.getPropByName("scripts");
                if(StringUtil.isNotEmpty(scripts)) {
                    Object rtn1 = this.groovyEngine.executeScripts(scripts, new HashMap());
                    if(rtn1 != null) {
                        AttrParseUtil.addKey(jsonObject, rtn1.toString());
                    }
                }
            }

            return jsonObject;
        }
    }

    public String getPluginName() {
        return "mini-textbox";
    }

    public String getDescription() {
        return "文本框";
    }

    public boolean isSingleAttr() {
        return true;
    }
}

