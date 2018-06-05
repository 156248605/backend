package com.elex.oa.form.impl.formhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import com.elex.oa.core.engine.FreemarkEngine;
import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.entity.activiti.BpmTask;
import com.elex.oa.entity.core.KeyValEnt;
import com.elex.oa.form.entity.FormViewRight;
import com.elex.oa.form.entity.RightModel;
import com.elex.oa.form.manager.PermissionUtil;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.org.context.ProfileUtil;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.org.service.UserService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.script.GroovyEngine;
import com.elex.oa.service.*;
import com.elex.oa.util.*;
import com.elex.oa.view.control.MiniControlParseConfig;
import com.elex.oa.view.control.MiniViewHanlder;
import com.elex.oa.view.util.FormViewUtil;
import freemarker.template.TemplateException;
import com.elex.oa.core.context.HttpServletContext;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.form.entity.FormModel;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
public class FormUtil {
    public FormUtil() {
    }

    public static void setFormModelByFormView(FormModel model, BpmFormView formView, BpmInst bpmInst) {
        model.setType(formView.getType());
        if(formView.getType().equals("ONLINE-DESIGN")) {
            model.setType("ONLINE-DESIGN");
            model.setContent(formView.getTemplate());
        } else if(formView.getType().equals("SEL-DEV")) {
            model.setType("SEL-DEV");
            HttpServletRequest request = HttpServletContext.getRequest();
            Map params = FormViewUtil.contructParams(request);
            if(BeanUtil.isNotEmpty(bpmInst)) {
                params.put("pk", bpmInst.getBusKey());
            }

            if(BeanUtil.isNotEmpty(model.getParams())) {
                params.putAll(model.getParams());
            }

            String url = formView.getRenderUrl();
            if(!url.startsWith("http")) {
                url = params.get("ctxPath") + url;
            }

            try {
                url = StringUtil.replaceVariableMap(url, params);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            model.setContent(url);
        }

    }

    private static String calcRights(FormViewRight fvr, Map<String, Set<String>> profileMap, boolean readOnly) {
        if(fvr == null) {
            return readOnly?"r":"w";
        } else {
            String editDf = fvr.getEditDf();
            String readDf = fvr.getReadDf();
            String hideDf = fvr.getHideDf();
            String right = "";
            boolean hasHidden;
            if(isNotEmpty(editDf)) {
                hasHidden = hasRight(editDf, profileMap);
                if(hasHidden) {
                    right = readOnly?"r":"w";
                }
            }

            if(StringUtil.isEmpty(right) && isNotEmpty(readDf)) {
                hasHidden = hasRight(readDf, profileMap);
                if(hasHidden) {
                    right = "r";
                }
            }

            if(StringUtil.isEmpty(right) && isNotEmpty(hideDf)) {
                hasHidden = hasRight(hideDf, profileMap);
                if(hasHidden) {
                    right = "none";
                }
            }

            if(StringUtil.isEmpty(right)) {
                right = readOnly?"r":"w";
            }

            return right;
        }
    }

    private static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str) && !"null".equals(str);
    }

    private static boolean hasRight(String rightJson, Map<String, Set<String>> profileMap) {
        JSONObject obj = JSONObject.parseObject(rightJson);
        boolean all = obj.getBoolean("all").booleanValue();
        if(all) {
            return true;
        } else {
            String userIds = obj.getString("userIds");
            String groupIds = obj.getString("groupIds");
            boolean hasUserRight = hasRight(userIds, "user", profileMap);
            if(hasUserRight) {
                return true;
            } else {
                boolean hasGroupRight = hasRight(groupIds, "group", profileMap);
                return hasGroupRight;
            }
        }
    }

    private static boolean hasRight(String ids, String type, Map<String, Set<String>> profileMap) {
        if(StringUtil.isEmpty(ids)) {
            return false;
        } else {
            RightModel right = new RightModel();
            right.setPermissionType(type);
            right.setIds(ids);
            boolean rtn = PermissionUtil.hasRight(right, profileMap);
            return rtn;
        }
    }

    public static void parseHtml(BpmFormView bpmFormView, Map<String, Object> params, JSONObject dataJson, Map<String, String> rightMap, boolean readOnly) throws IOException, TemplateException {
        MiniControlParseConfig miniControlParseConfig = (MiniControlParseConfig)AppBeanUtil.getBean(MiniControlParseConfig.class);
        FreemarkEngine freemarkEngine = (FreemarkEngine) AppBeanUtil.getBean(FreemarkEngine.class);
        String html = freemarkEngine.parseByStringTemplate(params, bpmFormView.getTemplate());
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("[permission]");
        Iterator permissionIt = els.iterator();

        String pkHtml;
        while(permissionIt.hasNext()) {
            Element ctls = (Element)permissionIt.next();
            String it = ctls.attr("permission");
            pkHtml = (String)rightMap.get(it);
            if("none".equals(pkHtml)) {
                ctls.remove();
            }
        }

        Elements ctls1 = doc.select(".rxc:not(.rx-grid .rxc),[plugins=\"mini-nodeopinion\"],[plugins=\"rx-grid\"]");
        Iterator it1 = ctls1.iterator();

        String boDefHtml;
        while(it1.hasNext()) {
            Element pkHtml1 = (Element)it1.next();
            boDefHtml = pkHtml1.attr("name");
            String plugins = pkHtml1.attr("plugins");
            if(!StringUtils.isEmpty(plugins)) {
                if(StringUtils.isEmpty(boDefHtml)) {
                    boDefHtml = "undefine_" + UUID.randomUUID();
                }

                if("mini-nodeopinion".equals(plugins)) {
                    boDefHtml = boDefHtml.replace("FORM_OPINION_", "") + "_" + "opinion";
                } else if("mini-button".equals(plugins)) {
                    boDefHtml = boDefHtml + "_" + "button";
                } else if("rx-grid".equals(plugins)) {
                    boDefHtml = boDefHtml + "_" + "table";
                } else {
                    boDefHtml = boDefHtml + "_" + "form";
                }

                String right = "";
                if(rightMap.containsKey(boDefHtml)) {
                    right = (String)rightMap.get(boDefHtml);
                } else {
                    right = readOnly?"r":"w";
                }

                if("none".equals(right)) {
                    pkHtml1.remove();
                } else {
                    if("r".equals(right)) {
                        pkHtml1.attr("readonly", "true");
                        pkHtml1.addClass("asLabel");
                    }

                    MiniViewHanlder handler = miniControlParseConfig.getElementViewHandler(plugins);
                    params.put("right", right);
                    handler.parse(pkHtml1, params, dataJson);
                    if("r".equals(right)) {
                        handler.convertToReadOnly(pkHtml1, params, dataJson);
                    }
                }
            }
        }

        pkHtml = "";
        if(dataJson.containsKey("ID_")) {
            pkHtml = "<input class=\'mini-hidden\' name=\'ID_\' value=\'" + dataJson.getString("ID_") + "\'>";
        }

        boDefHtml = "<input class=\'mini-hidden\' name=\'bo_Def_Id_\' value=\'" + bpmFormView.getBoDefId() + "\'>";
        bpmFormView.setTemplate(doc.head().html() + boDefHtml + pkHtml + doc.body().html());
    }

    public static void handTab(BpmFormView bpmFormView, Map<String, Object> params, Map<String, Set<String>> profileMap, boolean isPrint) throws IOException, TemplateException {
        String templateHtml = bpmFormView.getTemplate();
        FreemarkEngine freemarkEngine = (FreemarkEngine)AppBeanUtil.getBean(FreemarkEngine.class);
        templateHtml = freemarkEngine.parseByStringTemplate(params, templateHtml);
        bpmFormView.setTemplate(templateHtml);
        if(templateHtml.indexOf("#page#") != -1) {
            String tabRight = "";
            if(BeanUtil.isNotEmpty(bpmFormView.getBpmSolFv())) {
                tabRight = bpmFormView.getBpmSolFv().getTabRights();
            }

            JSONObject tabRights = handTabRight(tabRight, profileMap);
            String html = getTabHtml(bpmFormView.getTitle(), bpmFormView.getTemplate(), tabRights, isPrint, bpmFormView);
            bpmFormView.setTemplate(html);
        }
    }

    private static String getTabHtml(String title, String html, JSONObject tablPermission, boolean isPrint, BpmFormView bpmFormView) throws IOException, TemplateException {
        String[] templateAry = html.split("#page#");
        String[] tabsAry = title.split("#page#");
        String displayType = bpmFormView.getDisplayType();
        ArrayList list = new ArrayList();

        for(int model = 0; model < tabsAry.length; ++model) {
            String freemarkEngine = tabsAry[model];
            if(BeanUtil.isNotEmpty(tablPermission)) {
                boolean template = tablPermission.get(freemarkEngine) == null?true:tablPermission.getBoolean(freemarkEngine).booleanValue();
                if(!template) {
                    continue;
                }
            }

            KeyValEnt var14 = new KeyValEnt(freemarkEngine, templateAry[model]);
            list.add(var14);
        }

        HashMap var16 = new HashMap();
        var16.put("list", list);
        var16.put("displayType", displayType == null?"first":displayType);
        var16.put("bpmFormView", bpmFormView);
        FreemarkEngine var13 = (FreemarkEngine)AppBeanUtil.getBean(FreemarkEngine.class);
        String var15 = isPrint?"form/render/formTabPrint.ftl":"form/render/formTabslet.ftl";
        String rtnHtml = var13.mergeTemplateIntoString(var15, var16);
        return rtnHtml;
    }

    private static JSONObject handTabRight(String json, Map<String, Set<String>> profileMap) {
        if(StringUtil.isEmpty(json)) {
            return null;
        } else {
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject rtnJson = new JSONObject();
            Set titleSet = jsonObject.keySet();
            Iterator var5 = titleSet.iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                JSONObject tabJson = jsonObject.getJSONObject(key);
                JSONObject obj = handOneTab(key, tabJson, profileMap);
                rtnJson.put(obj.getString("tab"), obj.getBoolean("val"));
            }

            return rtnJson;
        }
    }

    private static JSONObject handOneTab(String tab, JSONObject tabJson, Map<String, Set<String>> profileMap) {
        JSONObject obj = new JSONObject();
        JSONObject whiteList = (JSONObject)tabJson.get("whiteList");
        JSONObject blackList = (JSONObject)tabJson.get("blackList");
        boolean whiteRight = handleList(whiteList, profileMap);
        boolean blackRight = handleList(blackList, profileMap);
        boolean rtn = grantTabRightOrNot(whiteRight, blackRight, whiteList, blackList);
        obj.put("tab", tab);
        obj.put("val", Boolean.valueOf(rtn));
        return obj;
    }

    private static boolean handleList(JSONObject listJson, Map<String, Set<String>> profileMap) {
        if(BeanUtil.isEmpty(listJson)) {
            return false;
        } else {
            Set typeKeySet = listJson.keySet();
            Iterator var3 = typeKeySet.iterator();

            while(var3.hasNext()) {
                String type = (String)var3.next();
                Set profileSet = (Set)profileMap.get(type);
                JSONObject rightJson = listJson.getJSONObject(type);
                String ids = rightJson.getString("id");
                String[] aryId = ids.split(",");

                for(int i = 0; i < aryId.length; ++i) {
                    String tmpId = aryId[i];
                    if(profileSet.contains(tmpId)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private static boolean grantTabRightOrNot(boolean whiteRight, boolean blackRight, JSONObject whiteList, JSONObject blackList) {
        return whiteList != null && !whiteList.isEmpty() && !whiteRight?false:(!whiteRight || !blackRight) && (whiteRight || !blackRight);
    }

    private static Map<String, FormViewRight> convertRightMap(List<FormViewRight> rights) {
        HashMap rightMap = new HashMap();
        if(BeanUtil.isEmpty(rights)) {
            return rightMap;
        } else {
            Iterator var2 = rights.iterator();

            while(var2.hasNext()) {
                FormViewRight v = (FormViewRight)var2.next();
                rightMap.put(v.getFieldName() + "_" + v.getType(), v);
            }

            return rightMap;
        }
    }

    public static Map<String, FormViewRight> getByStart(String formAlias, String actDefId, String solId) {
        //FormViewRightManager formViewRightManager = (FormViewRightManager)AppBeanUtil.getBean(FormViewRightManager.class);
        IFormViewRightService formViewRightService = (IFormViewRightService)AppBeanUtil.getBean(IFormViewRightService.class);
        String nodeId = "_START";
       // List rights = formViewRightManager.getByFormAliasNodeIdActDefIdSolId(formAlias, nodeId, actDefId, solId);
        List rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, nodeId, actDefId, solId);
        if(BeanUtil.isEmpty(rights)) {
            rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, "_PROCESS", actDefId, solId);
        }

        Map rightMap = convertRightMap(rights);
        return rightMap;
    }

    public static Map<String, FormViewRight> getByDetail(String formAlias, String actDefId, String solId) {
        //FormViewRightManager formViewRightManager = (FormViewRightManager)AppBeanUtil.getBean(FormViewRightManager.class);
        IFormViewRightService formViewRightService = (IFormViewRightService)AppBeanUtil.getBean(IFormViewRightService.class);
        String nodeId = "_DETAIL";
        List rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, nodeId, actDefId, solId);
        if(BeanUtil.isEmpty(rights)) {
            rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, "_PROCESS", actDefId, solId);
        }

        Map rightMap = convertRightMap(rights);
        return rightMap;
    }

    public static Map<String, FormViewRight> getByNodeId(String formAlias, String nodeId, String actDefId, String solId) {
        //FormViewRightManager formViewRightManager = (FormViewRightManager)AppBeanUtil.getBean(FormViewRightManager.class);
        IFormViewRightService formViewRightService = (IFormViewRightService)AppBeanUtil.getBean(IFormViewRightService.class);
       // List rights = formViewRightManager.getByFormAliasNodeIdActDefIdSolId(formAlias, nodeId, actDefId, solId);
        List rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, nodeId, actDefId, solId);
        if(BeanUtil.isEmpty(rights)) {
            rights = formViewRightService.getByFormAliasNodeIdActDefIdSolId(formAlias, "_PROCESS", actDefId, solId);
        }

        Map rightMap = convertRightMap(rights);
        return rightMap;
    }

    public static Map<String, FormViewRight> getRightByForm(String formKey) {
        //FormViewRightManager formViewRightManager = (FormViewRightManager)AppBeanUtil.getBean(FormViewRightManager.class);
        IFormViewRightService formViewRightService = (IFormViewRightService)AppBeanUtil.getBean(IFormViewRightService.class);
        String nodeId = "_FORM";
       // List rights = formViewRightManager.getByFormKeyNodeId(formKey, nodeId);
        List rights = formViewRightService.getByFormKeyNodeId(formKey, nodeId);
        Map rightMap = convertRightMap(rights);
        return rightMap;
    }

    public static Map<String, Object> getParams(BpmInst bpmInst, JSONObject jsonData) {
        HttpServletRequest request = HttpServletContext.getRequest();
        Map params = FormViewUtil.contructParams(request);
        String solId = bpmInst.getSolId();
        String actDefId = bpmInst.getActDefId();
        String actInstId = bpmInst.getActInstId();
        params.put("solId", solId);
        if(StringUtil.isNotEmpty(bpmInst.getActInstId())) {
            params.put("actInstId", actInstId);
        }

        if(StringUtil.isNotEmpty(bpmInst.getInstId())) {
            params.put("instId", bpmInst.getInstId());
        }

        return params;
    }

    public static JSONObject getData(BpmSolution solution, BpmFormView formView) {
        HashMap vars = new HashMap();
        String boDefId = formView.getBoDefId();
        IFormDataHandler handler = BoDataUtil.getDataHandler(solution.getDataSaveMode());
        JSONObject jsonObj = handler.getInitData(boDefId);
        String dataConfs = formView.getBpmSolFv().getDataConfs();
        if(StringUtils.isEmpty(dataConfs)) {
            return jsonObj;
        } else {
            IDataSettingHandler settingHanler = (IDataSettingHandler)AppBeanUtil.getBean(IDataSettingHandler.class);
            if(settingHanler != null && StringUtil.isNotEmpty(dataConfs)) {
                JSONObject settingJson = JSONObject.parseObject(dataConfs);
                settingHanler.handSetting(jsonObj, boDefId, settingJson, false, vars);
            }

            return jsonObj;
        }
    }

    public static JSONObject getData(BpmInst bpmInst, BpmFormView bpmFormView, Map<String, Object> vars) {
        JSONObject jsonObj = getData(bpmInst, bpmFormView.getBoDefId());
        IDataSettingHandler settingHanler = (IDataSettingHandler)AppBeanUtil.getBean(IDataSettingHandler.class);
        if(settingHanler == null) {
            return jsonObj;
        } else if(BeanUtil.isEmpty(bpmFormView.getBpmSolFv())) {
            return jsonObj;
        } else {
            BpmSolFv bpmSolFv = bpmFormView.getBpmSolFv();
            String initSetting = bpmSolFv.getDataConfs();
            if(StringUtil.isEmpty(initSetting)) {
              //  BpmSolFvManager settingJson = (BpmSolFvManager)AppBeanUtil.getBean(BpmSolFvManager.class);
                IBpmSolFvService settingJson =AppBeanUtil.getBean(IBpmSolFvService.class);
                bpmSolFv = settingJson.getBySolIdActDefIdNodeId(bpmSolFv.getSolId(), bpmSolFv.getActDefId(), "_PROCESS");
                initSetting = bpmSolFv.getDataConfs();
                if(StringUtil.isEmpty(initSetting)) {
                    return jsonObj;
                }
            }

            JSONObject settingJson1 = JSONObject.parseObject(initSetting);
            settingHanler.handSetting(jsonObj, bpmFormView.getBoDefId(), settingJson1, false, vars);
            return jsonObj;
        }
    }

    public static JSONObject getData(BpmInst bpmInst, String boDefId) {
        return getData(bpmInst.getDataSaveMode(), boDefId, bpmInst.getInstId());
    }

    public static JSONObject getData(String saveMode, String boDefId, String instId) {
        String pk = "";
        if(StringUtil.isNotEmpty(instId)) {
           //BpmInstDataManager handler = (BpmInstDataManager)AppBeanUtil.getBean(BpmInstDataManager.class);
            IBpmInstDataService handler =(IBpmInstDataService)AppBeanUtil.getBean(IBpmInstDataService.class);
            pk = handler.getPk(instId, boDefId);
            if(StringUtil.isEmpty(pk)) {
                return new JSONObject();
            }
        }

        IFormDataHandler handler1 = BoDataUtil.getDataHandler(saveMode);
        JSONObject jsonObj = handler1.getData(boDefId, pk);
        return jsonObj;
    }

    public static JSONObject getData(String bodefId, String id) {
        IFormDataHandler handler = BoDataUtil.getDataHandler("db");
        JSONObject jsonObj = null;
        if(StringUtil.isNotEmpty(id)) {
            jsonObj = handler.getData(bodefId, id);
        } else {
            jsonObj = handler.getInitData(bodefId);
        }

        return jsonObj;
    }

    public static FormModel getByStart(BpmSolution bpmSolution, BpmFormView bpmFormView, JSONObject jsonData, boolean readOnly, boolean isPrint) throws Exception {
        String solId = bpmSolution.getSolId();
        String actDefId = bpmSolution.getActDefId();
        FormModel model = new FormModel();
        model.setType("ONLINE-DESIGN");
        Map profileMap = ProfileUtil.getCurrentProfile();
        BpmInst bpmInst = new BpmInst();
        bpmInst.setActDefId(actDefId);
        bpmInst.setSolId(solId);
        Map params = getParams(bpmInst, jsonData);
        Map rightMap = getByStart(bpmFormView.getKey(), actDefId, solId);
        Map rightsMap = calcRights(rightMap, profileMap, readOnly);
        params.putAll(rightsMap);
        setParams(jsonData, params);
        handTab(bpmFormView, params, profileMap, isPrint);
        parseHtml(bpmFormView, params, jsonData, rightsMap, readOnly);
        model.setContent(bpmFormView.getTemplate());
        model.setDescription(bpmFormView.getDescp());
        model.setViewId(bpmFormView.getViewId());
        model.setJsonData(jsonData);
        return model;
    }

    public static Map<String, String> calcRights(Map<String, FormViewRight> rightMap, Map<String, Set<String>> profileMap, boolean readOnly) {
        HashMap map = new HashMap();
        Iterator var4 = rightMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry ent = (Entry)var4.next();
            String key = (String)ent.getKey();
            FormViewRight fvr = (FormViewRight)ent.getValue();
            String right = calcRights(fvr, profileMap, readOnly);
            map.put(key, right);
            String suffix = "_table";
            if(key.endsWith(suffix)) {
                String name = key.substring(0, key.lastIndexOf(suffix));
                if(StringUtil.isNotEmpty(fvr.getDealwith())) {
                    map.put(name + "_" + "dealwith", fvr.getDealwith());
                }
            }
        }

        return map;
    }

    public static Map<String, String> calcRights(List<FormViewRight> rights, Map<String, Set<String>> profileMap, boolean readOnly) {
        Map rightMap = convertRightMap(rights);
        return calcRights(rightMap, profileMap, readOnly);
    }

    public static void main(String[] args) {
        String suffix = "_table";
        String key = "grid_table";
        key.substring(0, key.lastIndexOf(suffix));
    }

    public static FormModel getByTask(BpmInst bpmInst, BpmTask bpmTask, BpmFormView bpmFormView, JSONObject jsonData, boolean readOnly, boolean isPrint) throws Exception {
        String solId = bpmInst.getSolId();
        String actDefId = bpmInst.getActDefId();
        FormModel model = new FormModel();
        model.setType("ONLINE-DESIGN");
        Map profileMap = ProfileUtil.getCurrentProfile();
        Map params = getParams(bpmInst, jsonData);
        params.put("taskId", bpmTask.getId());
        params.put("nodeId", bpmTask.getTaskDefKey());
        Map rightMap = getByNodeId(bpmFormView.getKey(), bpmTask.getTaskDefKey(), actDefId, solId);
        Map rightsMap = calcRights(rightMap, profileMap, readOnly);
        params.putAll(rightsMap);
        setParams(jsonData, params);
        handTab(bpmFormView, params, profileMap, isPrint);
        parseHtml(bpmFormView, params, jsonData, rightsMap, readOnly);
        model.setContent(bpmFormView.getTemplate());
        model.setDescription(bpmFormView.getName());
        model.setViewId(bpmFormView.getViewId());
        model.setJsonData(jsonData);
        return model;
    }

    private static void setParams(JSONObject jsonData, Map<String, Object> params) {
        UserService userService = (UserService)AppBeanUtil.getBean(UserService.class);
        GroupService groupService = (GroupService)AppBeanUtil.getBean(GroupService.class);
        Date date = jsonData.getDate("CREATE_TIME_");
        Date updDate = jsonData.getDate("UPDATE_TIME_");
        if(date != null) {
            params.put("CREATE_TIME_", DateUtil.formatDate(date));
            params.put("CREATE_DATE_", DateUtil.formatDate(date, "yyyy-MM-dd"));
        }

        if(updDate != null) {
            params.put("UPDATE_TIME_", DateUtil.formatDate(updDate));
            params.put("UPDATE_DATE_", DateUtil.formatDate(updDate, "yyyy-MM-dd"));
        }

        String userId = jsonData.getString("CREATE_USER_ID_");
        if(StringUtil.isNotEmpty(userId)) {
            IUser groupId = userService.getByUserId(userId);
            params.put("CREATE_USER_ID_", userId);
            params.put("CREATE_USER_NAME_", groupId.getFullname());
        }

        String groupId1 = jsonData.getString("CREATE_GROUP_ID_");
        if(StringUtil.isNotEmpty(groupId1)) {
            IGroup group = groupService.getById(groupId1);
            params.put("CREATE_GROUP_ID_", groupId1);
            params.put("CREATE_GROUP_NAME_", group.getIdentityName());
        }

        params.put("INST_STATUS_", jsonData.getString("INST_STATUS_"));
        params.put("CREATE_GROUP_ID_", jsonData.getString("CREATE_GROUP_ID_"));
        params.put("REF_ID_", jsonData.getString("REF_ID_"));
        params.put("ID_", jsonData.getString("ID_"));
    }

    public static FormModel getByFormView(BpmFormView bpmFormView, JSONObject jsonData, boolean readOnly, boolean isPrint) throws Exception {
        HttpServletRequest request = HttpServletContext.getRequest();
        Map params = FormViewUtil.contructParams(request);
        params.put("ctxPath", request.getContextPath());
        Map profileMap = ProfileUtil.getCurrentProfile();
        Map rightMap = getRightByForm(bpmFormView.getKey());
        Map rightsMap = calcRights(rightMap, profileMap, readOnly);
        params.putAll(rightsMap);
        params.put("readOnly", Boolean.valueOf(readOnly));
        setParams(jsonData, params);
        handTab(bpmFormView, params, profileMap, isPrint);
        parseHtml(bpmFormView, params, jsonData, rightsMap, readOnly);
        FormModel model = new FormModel();
        model.setContent(bpmFormView.getTemplate());
        model.setViewId(bpmFormView.getViewId());
        model.setJsonData(jsonData);
        return model;
    }

    public static FormModel getByInst(BpmFormView bpmFormView, BpmInst bpmInst, JSONObject jsonData, boolean readOnly, boolean isPrint) throws Exception {
        FormModel model = new FormModel();
        model.setType("ONLINE-DESIGN");
        Map profileMap = ProfileUtil.getCurrentProfile();
        Map params = getParams(bpmInst, jsonData);
        Map rightMap = getByDetail(bpmFormView.getKey(), bpmInst.getActDefId(), bpmInst.getSolId());
        Map rightsMap = calcRights(rightMap, profileMap, readOnly);
        params.putAll(rightsMap);
        params.put("readOnly", Boolean.valueOf(readOnly));
        setParams(jsonData, params);
        handTab(bpmFormView, params, profileMap, isPrint);
        parseHtml(bpmFormView, params, jsonData, rightsMap, readOnly);
        model.setContent(bpmFormView.getTemplate());
        model.setDescription(bpmFormView.getName());
        model.setViewId(bpmFormView.getViewId());
        model.setJsonData(jsonData);
        return model;
    }

    private static JSONObject getOpinionByInst(BpmInst bpmInst) {
       // BpmNodeJumpManager jumpManager = (BpmNodeJumpManager)AppBeanUtil.getBean(BpmNodeJumpManager.class);
        IBpmNodeJumpService jumpService =(IBpmNodeJumpService)AppBeanUtil.getBean(IBpmNodeJumpService.class);
        UserService userService = (UserService)AppBeanUtil.getBean(UserService.class);
        List jumps = jumpService.getFormOpinionByActInstId(bpmInst.getActInstId());
        if(BeanUtil.isEmpty(jumps)) {
            return null;
        } else {
            JSONObject jsonObj = new JSONObject();
            Iterator var5 = jumps.iterator();

            while(var5.hasNext()) {
                BpmNodeJump jump = (BpmNodeJump)var5.next();
                String opinionName = jump.getOpinionName();
                if(!StringUtil.isEmpty(opinionName)) {
                    JSONObject obj = new JSONObject();
                    obj.put("userId", jump.getHandlerId());
                    IUser user = userService.getByUserId(jump.getHandlerId());
                    obj.put("userName", user.getFullname());
                    obj.put("opinion", jump.getRemark());
                    obj.put("type", jump.getJumpType());
                    obj.put("createTime", jump.getCreateTime());
                    JSONArray ary;
                    if(jsonObj.containsKey(jump.getOpinionName())) {
                        ary = jsonObj.getJSONArray(opinionName);
                        ary.add(obj);
                    } else {
                        ary = new JSONArray();
                        ary.add(obj);
                        jsonObj.put(opinionName, ary);
                    }
                }
            }

            return jsonObj;
        }
    }

    public static void setOpinionData(BpmInst bpmInst, JSONObject jsonObject) {
        JSONObject jsonObj = getOpinionByInst(bpmInst);
        if(!BeanUtil.isEmpty(jsonObj)) {
            jsonObject.put("form_opinion_", jsonObj);
        }
    }

    public static FormModel getFormByFormAlias(String alias, String pk, boolean readOnly, boolean isPrint, Map<String, Object> params) throws Exception {
        //BpmFormViewManager bpmFormViewManager = (BpmFormViewManager)AppBeanUtil.getBean(BpmFormViewManager.class);
        IBpmFormViewService bpmFormViewManager = (IBpmFormViewService)AppBeanUtil.getBean(IBpmFormViewService.class);
        String tenantId = ContextUtil.getCurrentTenantId();
        BpmFormView bpmFormView = bpmFormViewManager.getLatestByKey(alias, tenantId);
        JSONObject jsonData = getData(bpmFormView.getBoDefId(), pk);
        setContextData(jsonData, params);
        FormModel model = getByFormView(bpmFormView, jsonData, readOnly, isPrint);
        model.setJsonData(jsonData);
        return model;
    }

    public static void setContextData(JSONObject jsonData, Map<String, Object> params) {
        if(!BeanUtil.isEmpty(params)) {
            Iterator var2 = params.entrySet().iterator();

            while(var2.hasNext()) {
                Entry ent = (Entry)var2.next();
                jsonData.put((String)ent.getKey(), ent.getValue());
            }

        }
    }

   /* public static void convertFieldToReadOnly(Element el, Map<String, Object> params, JSONObject jsonObj, String htmlText) {
        String dataOptions = el.attr("data-options");
        if(StringUtils.isEmpty(dataOptions)) {
            el.replaceWith((new Element(Tag.valueOf("span"), "")).html(htmlText));
        } else {
            JSONObject dataOpJSON = JSONObject.parseObject(dataOptions);
            JSONObject linkFieldConfig = dataOpJSON.getJSONObject("linkFieldConfig");
            if(linkFieldConfig == null) {
                el.replaceWith((new Element(Tag.valueOf("span"), "")).html(htmlText));
            } else {
                String url = linkFieldConfig.getString("url");
                String linkType = linkFieldConfig.getString("linkType");
                if(StringUtils.isEmpty(url)) {
                    el.replaceWith((new Element(Tag.valueOf("span"), "")).html(htmlText));
                } else {
                    String cusQuery = linkFieldConfig.getString("cusQuery");
                    String replaceRule = linkFieldConfig.getString("replaceRule");
                    Map dataMap = FastjsonUtil.json2Map(jsonObj);
                    if(StringUtils.isNotEmpty(cusQuery) && StringUtils.isNotEmpty(replaceRule)) {
                        replaceRule = replaceRule.trim();
                        HashMap var29 = new HashMap();
                        JSONArray var30 = linkFieldConfig.getJSONArray("cusInputData");
                        GroovyEngine var31 = (GroovyEngine)AppBeanUtil.getBean(GroovyEngine.class);

                        String results;
                        for(int queryManager = 0; queryManager < var30.size(); ++queryManager) {
                            JSONObject e1 = var30.getJSONObject(queryManager);
                            String isInputData = e1.getString("mode");
                            String keyIt = e1.getString("fieldName");
                            results = e1.getString("bindVal");
                            Object i;
                            if("script".equals(isInputData)) {
                                i = var31.executeScripts(results, params);
                                var29.put(keyIt, i);
                            } else {
                                i = dataMap.get(results);
                                var29.put(keyIt, i);
                            }
                        }

                        SysSqlCustomQueryManager var32 = (SysSqlCustomQueryManager)AppBeanUtil.getBean(SysSqlCustomQueryManager.class);

                        try {
                            Element var33 = new Element(Tag.valueOf("ul"), "");
                            var33.addClass("link-field-ul");
                            boolean var34 = true;
                            Iterator var35 = var29.keySet().iterator();

                            Object var36;
                            while(var35.hasNext()) {
                                var36 = var29.get(var35.next());
                                if(var36 == null) {
                                    var34 = false;
                                    break;
                                }
                            }

                            results = null;
                            if(!var34) {
                                var36 = new ArrayList();
                            } else {
                                var36 = var32.getQueryData(cusQuery, var29);
                            }

                            for(int var37 = 0; var37 < ((List)var36).size(); ++var37) {
                                Map rows = (Map)((List)var36).get(var37);
                                String rule = StringUtil.replaceVariableMap(replaceRule, rows);
                                rows.putAll(params);
                                Element li = new Element(Tag.valueOf("li"), "");
                                Element link1 = new Element(Tag.valueOf("a"), "");

                                try {
                                    dataMap.putAll(rows);
                                    dataMap.putAll(params);
                                    url = StringUtil.replaceVariableMap(url, dataMap);
                                    link1.attr("href", "javascript:void(0);");
                                    link1.attr("onclick", "_ShowLinkFieldInfo(\'" + url + "\',\'" + linkType + "\')");
                                    link1.text(rule);
                                    li.appendChild(link1);
                                    var33.appendChild(li);
                                } catch (Exception var27) {
                                    var27.printStackTrace();
                                }
                            }

                            el.replaceWith(var33);
                        } catch (Exception var28) {
                            var28.printStackTrace();
                        }
                    } else {
                        Element link = new Element(Tag.valueOf("a"), "");

                        try {
                            dataMap.putAll(params);
                            url = StringUtil.replaceVariableMap(url, dataMap);
                            link.attr("href", "javascript:void(0);");
                            link.attr("onclick", "_ShowLinkFieldInfo(\'" + url + "\',\'" + linkType + "\')");
                            String e = linkFieldConfig.getString("cur_control");
                            String labelText = jsonObj.getString(e);
                            link.text(labelText);
                            el.replaceWith(link);
                        } catch (Exception var26) {
                            var26.printStackTrace();
                        }
                    }

                }
            }
        }
    }*/

    public static Map<String, JSONObject> convertJsonToMap(JSONObject jsonData) {
        HashMap map = new HashMap();
        JSONArray jsonAry = jsonData.getJSONArray("bos");
        if(jsonAry == null) {
            return map;
        } else {
            for(int i = 0; i < jsonAry.size(); ++i) {
                JSONObject formJson = jsonAry.getJSONObject(i);
                String boDefId = formJson.getString("boDefId");
                JSONObject data = formJson.getJSONObject("data");
                map.put(boDefId, data);
            }

            return map;
        }
    }
}