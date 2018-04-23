package com.elex.oa.controller.bo;
import com.elex.oa.export.PoiTableBuilder;
import com.elex.oa.json.IJson;
import com.elex.oa.json.JsonPageResult;
import com.elex.oa.json.JsonResult;
import com.elex.oa.query.QueryFilter;
import com.elex.oa.service.ISysBoDefService;
import org.apache.commons.lang.StringUtils;
import com.elex.oa.util.RequestUtil;
import com.elex.oa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *@author hugo.zhao
 *@since 2018/4/19 16:12
*/
@Controller
@CrossOrigin
@RequestMapping({"/sys/bo/sysBoDef/"})
public class SysBoDefController {
    @Autowired
    private ISysBoDefService sysBoDefService;
    @Autowired
    private IJson iJson;
    @Autowired
    private PoiTableBuilder poiTableBuilder;
    public SysBoDefController() {
    }

    @RequestMapping({"del"})
    @ResponseBody
    public JsonResult del(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uId = RequestUtil.getString(request, "ids");
        if(StringUtil.isNotEmpty(uId)) {
            String[] ids = uId.split(",");
            String[] var5 = ids;
            int var6 = ids.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String id = var5[var7];
                //this.sysBoDefManager.delete(id);
                this.sysBoDefService.deleteById(id);
            }
        }

        return new JsonResult(true, "成功删除!");
    }

    @RequestMapping({"dialog"})
    public String  dialog(HttpServletRequest request){
        return "/sysBoDef/sysBoDefDialog";
    }


/*
    @RequestMapping({"get"})
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pkId = RequestUtil.getString(request, "pkId");
        SysBoDef sysBoDef = null;
        if(StringUtil.isNotEmpty(pkId)) {
            //sysBoDef = (SysBoDef)this.sysBoDefManager.get(pkId);
            sysBoDef = this.sysBoDefService.getById(pkId);
        } else {
            sysBoDef = new SysBoDef();
        }

        return this.getPathView(request).addObject("sysBoDef", sysBoDef);
    }

    @RequestMapping({"edit"})
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pkId = RequestUtil.getString(request, "pkId");
        String forCopy = request.getParameter("forCopy");
        SysBoDef sysBoDef = null;
        if(StringUtil.isNotEmpty(pkId)) {
            sysBoDef = (SysBoDef)this.sysBoDefManager.get(pkId);
            if("true".equals(forCopy)) {
                sysBoDef.setId((String)null);
            }
        } else {
            sysBoDef = new SysBoDef();
        }

        return this.getPathView(request).addObject("sysBoDef", sysBoDef);
    }

    @RequestMapping({"getJson"})
    @ResponseBody
    public JSONObject getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pkId = RequestUtil.getString(request, "pkId");
        IFormDataHandler handler = BoDataUtil.getDataHandler("json");
        return handler != null?handler.getInitData(pkId):null;
    }

    @RequestMapping({"getRelForm"})
    @ResponseBody
    public JSONObject getRelForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pkId = RequestUtil.getString(request, "pkId");
        QueryFilter filter = new QueryFilter();
        filter.addFieldParam("boDefId", pkId);
        List formViews = this.bpmFormViewManager.getByFilter(filter);
        List mobileforms = this.bpmMobileFormManager.getByBoDefId(pkId);
        JSONArray formAry = new JSONArray();
        Iterator mobileAry = formViews.iterator();

        while(mobileAry.hasNext()) {
            BpmFormView rtnObj = (BpmFormView)mobileAry.next();
            JSONObject view = new JSONObject();
            view.put("id", rtnObj.getViewId());
            view.put("name", rtnObj.getName());
            view.put("alias", rtnObj.getKey());
            formAry.add(view);
        }

        JSONArray mobileAry1 = new JSONArray();
        Iterator rtnObj1 = mobileforms.iterator();

        while(rtnObj1.hasNext()) {
            BpmMobileForm view1 = (BpmMobileForm)rtnObj1.next();
            JSONObject obj = new JSONObject();
            obj.put("id", view1.getId());
            obj.put("name", view1.getName());
            obj.put("alias", view1.getAlias());
            mobileAry1.add(obj);
        }

        JSONObject rtnObj2 = new JSONObject();
        rtnObj2.put("pc", formAry);
        rtnObj2.put("mobile", mobileAry1);
        return rtnObj2;
    }

    @RequestMapping({"getBoJson"})
    @ResponseBody
    public JSONObject getBoJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String boDefId = RequestUtil.getString(request, "boDefId");
        List list = this.sysBoEntManager.getListByBoDefId(boDefId, true);
        SysBoDef boDef = (SysBoDef)this.sysBoDefManager.get(boDefId);
        JSONObject json = new JSONObject();
        json.put("list", list);
        json.put("hasGen", boDef.getSupportDb());
        json.put("boDefId", boDefId);
        return json;
    }

    @RequestMapping({"manage"})
    @ResponseBody
    public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String boDefId = RequestUtil.getString(request, "boDefId");
        JSONObject json = this.boAttrParseFactory.getPluginDesc();
        ModelAndView mv = this.getPathView(request);
        mv.addObject("json", json);
        mv.addObject("boDefId", boDefId);
        return mv;
    }

    @RequestMapping({"removeAttr"})
    @ResponseBody
    public JsonResult<Void> removeAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String attrId = RequestUtil.getString(request, "attrId");
        JsonResult rtn = new JsonResult(true, "删除列成功!");

        try {
            this.sysBoEntManager.removeAttr(attrId);
        } catch (Exception var6) {
            rtn = new JsonResult(false, "删除列失败!");
        }

        return rtn;
    }

    @RequestMapping({"removeEnt"})
    @ResponseBody
    public JsonResult<Void> removeEnt(HttpServletRequest request, HttpServletResponse response) {
        String boDefId = RequestUtil.getString(request, "boDefId");
        String entId = RequestUtil.getString(request, "entId");
        JsonResult json = new JsonResult(true, "删除BO实体成功!");
        SysBoDef boDef = (SysBoDef)this.sysBoDefManager.get(boDefId);
        SysBoEnt boEnt = (SysBoEnt)this.sysBoEntManager.get(entId);
        boolean genTable = "yes".equals(boDef.getSupportDb());
        SysBoRelation boRelation = this.sysBoRelationQueryDao.getByDefEntId(boDefId, entId);

        try {
            if(genTable && boRelation.getIsRef() != 1) {
                this.tableOperator.dropTable(boEnt.getTableName());
            }

            if(boRelation.getIsRef() != 1) {
                this.sysBoEntManager.delete(entId);
                this.sysBoAttrQueryDao.delByMainId(entId);
            }

            this.sysBoRelationQueryDao.delete(boRelation.getId());
        } catch (Exception var11) {
            json = new JsonResult(false, ExceptionUtil.getExceptionMessage(var11));
        }

        return json;
    }

    public ExtBaseManager getBaseManager() {
        return this.sysBoDefManager;
    }*/
/*    @RequestMapping({"listData"})
    public void listData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String export = request.getParameter("_export");
        if(StringUtils.isNotEmpty(export)) {
            String queryFilter = request.getParameter("_all");
            if(StringUtils.isNotEmpty(queryFilter)) {
                this.exportAllPages(request, response);
            } else {
                this.exportCurPage(request, response);
            }
        }else {
            response.setContentType("application/json");
            QueryFilter queryFilter1 = this.getQueryFilter(request);
            List list = this.getPage(queryFilter1);
            JsonPageResult result = new JsonPageResult(list, queryFilter1.getPage().getTotalItems());
            String jsonResult = this.iJson.toJson(result);
            PrintWriter pw = response.getWriter();
            pw.println(jsonResult);
            pw.close();

        }
    }*/

/*    public void exportCurPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String columns = URLDecoder.decode(request.getParameter("columns"), "UTF-8");
        QueryFilter queryFilter = this.getQueryFilter(request);
        List list = this.getPage(queryFilter);
        response.setContentType("application/ms-excel");
        Date curDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
        response.setHeader("Content-Disposition", "attachment;filename=" + sdf.format(curDate) + ".xls");
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        List gridColumns = this.poiTableBuilder.constructColumns(columns);
        Workbook wb = this.poiTableBuilder.writeTable(gridColumns, list);
        wb.write(outByteStream);
        ServletOutputStream out = response.getOutputStream();
        out.write(outByteStream.toByteArray());
        out.flush();
    }*/

}
