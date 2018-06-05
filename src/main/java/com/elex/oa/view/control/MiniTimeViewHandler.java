package com.elex.oa.view.control;

import org.springframework.stereotype.Component;

@Component
public class MiniTimeViewHandler extends MiniDateViewHandler {
    public MiniTimeViewHandler() {
    }

    public String getPluginName() {
        return "mini-time";
    }
}
