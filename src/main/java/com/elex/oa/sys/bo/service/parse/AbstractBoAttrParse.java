package com.elex.oa.sys.bo.service.parse;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.util.StringUtil;
import org.jsoup.nodes.Element;
public abstract class AbstractBoAttrParse implements IBoAttrParse {
    public AbstractBoAttrParse() {
    }

    public JSONObject getInitData(SysBoAttr attr) {
        return null;
    }

    protected int getLength(String len, int defaultLen) {
        return StringUtil.isNotEmpty(len)?Integer.parseInt(len):defaultLen;
    }

    protected abstract void parseExt(SysBoAttr var1, Element var2);

    public SysBoAttr parse(String pluginName, Element el) {
        SysBoAttr field = new SysBoAttr();
        this.parseBase(field, el);
        this.parseExt(field, el);
        return field;
    }

    private void parseBase(SysBoAttr boAttr, Element el) {
        String label = el.attr("label").trim();
        String name = el.attr("name").trim();
        String plugins = el.attr("plugins");
        boAttr.setIsSingle(this.isSingleAttr()?1:0);
        boAttr.setComment(label);
        boAttr.setName(name);
        boAttr.setControl(plugins);
    }
}
