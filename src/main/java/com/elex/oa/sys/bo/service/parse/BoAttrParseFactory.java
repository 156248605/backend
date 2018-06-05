package com.elex.oa.sys.bo.service.parse;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.sys.bo.service.parse.impl.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class BoAttrParseFactory {

    @Resource
    private AttachMentAttrParse attachMentAttrParse;

    @Resource
    private ButtonEditAttrParse buttonEditAttrParse;

    @Resource
    private CheckBoxAttrParse checkBoxAttrParse;

    @Resource
    private CheckBoxListAttrParse checkBoxListAttrParse;

    @Resource
    private ComboboxAttrParse comboboxAttrParse;

    @Resource
    private  DatepickerAttrParse datepickerAttrParse;

    @Resource
    private  DeptAttrParse deptAttrParse;

    @Resource
    private GroupAttrParse groupAttrParse;

    @Resource
    private HiddenAttrParse hiddenAttrParse;

    @Resource
    private ImgAttrParse imgAttrParse;

    @Resource
    private MonthAttrParse monthAttrParse;

    @Resource
    private NumberAttrParse numberAttrParse;

    @Resource
    private RadioListAttrParse radioListAttrParse;

    @Resource
    private  TextAreaAttrParse textAreaAttrParse;

    @Resource
    private TextBoxAttrParse textBoxAttrParse;

    @Resource
    private TextBoxListAttrParse textBoxListAttrParse;

    @Resource
    private TimeAttrParse timeAttrParse;

    @Resource
    private TreeSelectAttrParse treeSelectAttrParse;

    @Resource
    private  UeditorAttrParse ueditorAttrParse;

    @Resource
    private UserAttrParse userAttrParse;

    @Resource
    private RelatedSolutionAttrParse relatedSolutionAttrParse;

    @Resource
    private  OfficeAttrParse officeAttrParse;

    private Map<String, IBoAttrParse> parseMap = new HashMap();
    private JSONObject pluginDescJson = new JSONObject();
    private Set<String> excludePlugins = new HashSet();

    public BoAttrParseFactory() {
        System.out.println("123");
    }

    @PostConstruct
    public void SynDictionary() {
        List<IBoAttrParse> list = new ArrayList<>();
        list.add(this.attachMentAttrParse);
        list.add(this.buttonEditAttrParse);
        list.add(this.checkBoxAttrParse);
        list.add(this.checkBoxListAttrParse);
        list.add(this.comboboxAttrParse);
        list.add(this.datepickerAttrParse);
        list.add(this.deptAttrParse);
        list.add(this.groupAttrParse);
        list.add(this.hiddenAttrParse);
        list.add(this.imgAttrParse);
        list.add(this.monthAttrParse);
        list.add(this.numberAttrParse);
        list.add(this.radioListAttrParse);
        list.add(this.textAreaAttrParse);
        list.add(this.textBoxAttrParse);
        list.add(this.textBoxListAttrParse);
        list.add(this.timeAttrParse);
        list.add(this.treeSelectAttrParse);
        list.add(this.ueditorAttrParse);
        list.add(this.userAttrParse);
        list.add(this.relatedSolutionAttrParse);
        list.add(this.officeAttrParse);
        this.setAttrParseList(list);
    }

    public void setAttrParseList(List<IBoAttrParse> list) {
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            IBoAttrParse parse = (IBoAttrParse)var2.next();
            this.parseMap.put(parse.getPluginName(), parse);
            this.pluginDescJson.put(parse.getPluginName(), parse.getDescription());
        }

    }

    public void setExculdePlugins(Set<String> set) {
        this.excludePlugins = set;
    }

    public JSONObject getPluginDesc() {
        return this.pluginDescJson;
    }

    public IBoAttrParse getByPluginName(String pluginName) {
        return this.excludePlugins.contains(pluginName)?null:(this.parseMap.containsKey(pluginName)?(IBoAttrParse)this.parseMap.get(pluginName):(IBoAttrParse)this.parseMap.get("baseBo"));
    }
}

