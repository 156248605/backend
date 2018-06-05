package com.elex.oa.core.engine;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public class FreemakerUtil {
    public FreemakerUtil() {
    }

    public static TemplateHashModel getTemplateModel(Class cls) throws TemplateModelException {
        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel fileStatics = (TemplateHashModel)staticModels.get(cls.getName());
        return fileStatics;
    }
}
