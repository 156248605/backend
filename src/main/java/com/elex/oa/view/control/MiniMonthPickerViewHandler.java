package com.elex.oa.view.control;

import org.springframework.stereotype.Component;

@Component
public class MiniMonthPickerViewHandler extends MiniDateViewHandler {
    public MiniMonthPickerViewHandler() {
    }

    public String getPluginName() {
        return "mini-month";
    }
}
