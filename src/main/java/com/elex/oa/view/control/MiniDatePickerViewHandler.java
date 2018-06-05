package com.elex.oa.view.control;

import org.springframework.stereotype.Component;

@Component
public class MiniDatePickerViewHandler extends MiniDateViewHandler {
    public MiniDatePickerViewHandler() {
    }

    public String getPluginName() {
        return "mini-datepicker";
    }
}
