package com.elex.oa.core.constants;

/**
 *@author hugo.zhao
 *@since 2018/4/27 10:49
*/
public enum WidgetType {
    HIDDEN("hidden", "隐藏控件"),
    TEXT("textfield", "文本控件"),
    LOCAL_COMBO("localcombofield", "本地下拉选项"),
    CHECKBOX("checkbox", "复选控件"),
    CHECKBOX_GROUP("checkboxgroup", "复选控件组"),
    RADIO("radio", "单选控件"),
    RADIO_GROUP("radiogroup", "单选控件组"),
    BOOL_RADIO("boolradiofield", "是否类型单选控件"),
    PASSWORD("password", "密码"),
    TEXTAREA("textarea", "多行文本控件"),
    DATE("datefield", "日期控件"),
    DATETIME("datetimefield", "日期时间控件"),
    NUMBER("numberfield", "数据控件"),
    LISTMODULE("listmodulefield", "列表子模块"),
    MODULE("modulefield", "实体模块"),
    FIELDSET("fieldset", "字段组"),
    NULL("null", "空");

    private String xtype;
    private String label;

    private WidgetType(String xtype, String label) {
        this.xtype = xtype;
        this.label = label;
    }

    public String getXtype() {
        return this.xtype;
    }

    public String getLabel() {
        return this.label;
    }
}
