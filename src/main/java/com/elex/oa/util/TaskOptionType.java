package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/5/5 16:29
*/
public enum TaskOptionType {
    AGREE("通过", "icon-agree", "true"),
    SKIP("跳过", "icon-skip", "false"),
    RECOVER("撤回", "icon-recorver", "false"),
    TIMEOUT_SKIP("超时跳过", "timeout-skip", "false"),
    REFUSE("反对", "icon-refuse", "true"),
    COMMUNICATE("沟通", "icon-communicate", "true"),
    REPLY_COMMUNICATE("回复沟通", "icon-replaycommunicate", "false"),
    CANCEL_COMMUNICATE("取消沟通", "icon-cancelcommunicate", "false"),
    BACK("驳回", "icon-back", "true"),
    BACK_TO_STARTOR("驳回至发起人", "icon-startor", "true"),
    ABSTAIN("弃权", "icon-abstain", "true"),
    INTERPOSE("干预", "icon-interpose", "false");

    private String text;
    private String iconCls;
    private String isShow;

    private TaskOptionType(String text, String iconCls, String isShow) {
        this.text = text;
        this.iconCls = iconCls;
        this.isShow = isShow;
    }

    public String getText() {
        return this.text;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public String getIsShow() {
        return this.isShow;
    }
}
