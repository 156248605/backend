package com.elex.oa.view.control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class MiniControlParseConfig implements InitializingBean {

    @Resource
    private MiniOfficeViewHandler miniOfficeViewHandler;

    @Resource
    private MiniHiddenViewHandler miniHiddenViewHandler;

    @Resource
    private MiniCheckHiListViewHandler miniCheckHiListViewHandler;

    @Resource
    private MiniCheckboxListViewHandler miniCheckboxListViewHandler;

    @Resource
    private MiniRadioButtonListViewHandler miniRadioButtonListViewHandler;

    @Resource
    private MiniCheckboxViewHandler miniCheckboxViewHandler;

    @Resource
    private MiniComboBoxViewHandler miniComboBoxViewHandler;

    @Resource
    private MiniDatePickerViewHandler miniDatePickerViewHandler;

    @Resource
    private MiniMonthPickerViewHandler miniMonthPickerViewHandler;

    @Resource
    private MiniUserViewHandler miniUserViewHandler;

    @Resource
    private MiniGroupViewHandler miniGroupViewHandler;

    @Resource
    private MiniDepViewHandler miniDepViewHandler;

    @Resource
    private UploadPanelViewHandler uploadPanelViewHandler;

    @Resource
    private MiniButtonEditViewHandler miniButtonEditViewHandler;

    @Resource
    private  MiniGridViewHandler miniGridViewHandler;

    @Resource
    private  MiniTreeSelectViewHandler miniTreeSelectViewHandler;

    @Resource
    private  MiniButtonViewHandler miniButtonViewHandler;

    @Resource
    private  MiniTextareaViewHandler miniTextareaViewHandler;

    @Resource
    private  MiniRichTextViewHandler miniRichTextViewHandler;

    @Resource
    private  MiniTextBoxListViewHandler  miniTextBoxListViewHandler;

    @Resource
    private MiniCheckNodeHiViewHandler miniCheckNodeHiViewHandler;

    @Resource
    private MiniTextBoxViewHandler miniTextBoxViewHandler;

    @Resource
    private  MiniNodeOpinionViewHandler miniNodeOpinionViewHandler;

    @Resource
    private  MiniImgViewHandler miniImgViewHandler;

    @Resource
    private  MiniTimeViewHandler miniTimeViewHandler;

    @Resource
    private MiniRelatedSolutionHandler miniRelatedSolutionHandler;

    @Resource
    private MiniConditionDivViewHandler miniConditionDivViewHandler;

    @Resource
    private MiniNumberViewHandler miniNumberViewHandler;

    private Map<String, MiniViewHanlder> viewHandlersMap = new HashMap();
    private List<MiniViewHanlder> viewHandlers = new ArrayList();
    private DefaultViewHandler defaulViewHandler = new DefaultViewHandler();

    public MiniControlParseConfig() {
    }

    @PostConstruct
    public void SynDictionary() {
        List<MiniViewHanlder> viewHandlers = new ArrayList<>();
        viewHandlers.add(this.miniOfficeViewHandler);
        viewHandlers.add(this.miniHiddenViewHandler);
        viewHandlers.add(this.miniCheckHiListViewHandler);
        viewHandlers.add(this.miniCheckboxListViewHandler);
        viewHandlers.add(this.miniRadioButtonListViewHandler);
        viewHandlers.add(this.miniCheckboxViewHandler);
        viewHandlers.add(this.miniComboBoxViewHandler);
        viewHandlers.add(this.miniDatePickerViewHandler);
        viewHandlers.add(this.miniMonthPickerViewHandler);
        viewHandlers.add(this.miniUserViewHandler);
        viewHandlers.add(this.miniGroupViewHandler);
        viewHandlers.add(this.miniDepViewHandler);
        viewHandlers.add(this.uploadPanelViewHandler);
        viewHandlers.add(this.miniButtonEditViewHandler);
        viewHandlers.add(this.miniGridViewHandler);
        viewHandlers.add(this.miniTreeSelectViewHandler);
        viewHandlers.add(this.miniButtonViewHandler);
        viewHandlers.add(this.miniTextareaViewHandler);
        viewHandlers.add(this.miniRichTextViewHandler);
        viewHandlers.add(this.miniTextBoxListViewHandler);
        viewHandlers.add(this.miniCheckNodeHiViewHandler);
        viewHandlers.add(this.miniTextBoxViewHandler);
        viewHandlers.add(this.miniNodeOpinionViewHandler);
        viewHandlers.add(this.miniImgViewHandler);
        viewHandlers.add(this.miniTimeViewHandler);
        viewHandlers.add(this.miniRelatedSolutionHandler);
        viewHandlers.add(this.miniConditionDivViewHandler);
        viewHandlers.add(this.miniNumberViewHandler);
    }



    public MiniViewHanlder getElementViewHandler(String pluginName) {
        MiniViewHanlder viewHandler = (MiniViewHanlder)this.viewHandlersMap.get(pluginName);
        return (MiniViewHanlder)(viewHandler != null?viewHandler:this.defaulViewHandler);
    }

    public DefaultViewHandler getDefaulViewHandler() {
        return this.defaulViewHandler;
    }

    public void setDefaulViewHandler(DefaultViewHandler defaulViewHandler) {
        this.defaulViewHandler = defaulViewHandler;
    }

    public Map<String, MiniViewHanlder> getViewHandlersMap() {
        return this.viewHandlersMap;
    }

    public void setViewHandlersMap(Map<String, MiniViewHanlder> viewHandlersMap) {
        this.viewHandlersMap = viewHandlersMap;
    }

    public List<MiniViewHanlder> getViewHandlers() {
        return this.viewHandlers;
    }

    public void setViewHandlers(List<MiniViewHanlder> viewHandlers) {
        this.viewHandlers = viewHandlers;
    }

    public void afterPropertiesSet() throws Exception {
        Iterator var1 = this.viewHandlers.iterator();

        while(var1.hasNext()) {
            MiniViewHanlder handler = (MiniViewHanlder)var1.next();
            this.viewHandlersMap.put(handler.getPluginName(), handler);
        }

    }
}
