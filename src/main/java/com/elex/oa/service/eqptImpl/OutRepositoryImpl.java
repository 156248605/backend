package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.OutRepositoryService;
import com.elex.oa.service.project.OperationService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class OutRepositoryImpl implements OutRepositoryService {

    @Resource
    private OutRepositoryMapper outRepositoryMapper;

    @Resource
    private MaterialMtMapper materialMtMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private OperationService operationService;

    @Resource
    InventoryMapper inventoryMapper;

    @Resource
    InRepositoryMapper inRepositoryMapper;

    /*所有单号*/
    @Override
    public PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        Repository repository = new Repository();
        repository.setAuthor(request.getParameter("author"));
        List<Repository> list =  outRepositoryMapper.findAll(repository);
        return new PageInfo<>(list);
    }

    /*查询*/
    @Override
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        String reptId = request.getParameter("reptId");
        String reptIdC = request.getParameter("reptIdC");
        String reptCategory = request.getParameter("reptCategory");
        String reptCategoryC = request.getParameter("reptCategoryC");
        String postId = request.getParameter("postId");
        String postIdC = request.getParameter("postIdC");
        String outId = request.getParameter("outId");
        String outIdC = request.getParameter("outIdC");
        String sn = request.getParameter("sn");
        String snC = request.getParameter("snC");
        String bn = request.getParameter("bn");
        String bnC = request.getParameter("bnC");
        String outTime = request.getParameter("outTime");
        String outTimeC = request.getParameter("outTimeC");
        String projId = request.getParameter("projId");
        String projIdC = request.getParameter("projIdC");
        String projName = request.getParameter("projName");
        String projNameC = request.getParameter("projNameC");
        if (projId == null && projName == null && reptId == null && reptCategory == null && postId == null && outId == null && sn == null && bn == null && outTime == null){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setAuthor(request.getParameter("author"));
            List<Repository> listR = outRepositoryMapper.findAll(repository);
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setReptIdC(reptIdC);
            repository.setReptCategory(reptCategory);
            repository.setReptCateC(reptCategoryC);
            repository.setPostId(postId);
            repository.setPostIdC(postIdC);
            repository.setOutId(outId);
            repository.setOutIdC(outIdC);
            repository.setBn(bn);
            repository.setBnC(bnC);
            repository.setSn(sn);
            repository.setSnC(snC);
            repository.setOutTime(outTime);
            repository.setOutTimeC(outTimeC);
            repository.setProjName(projName);
            repository.setProjNameC(projNameC);
            repository.setProjId(projId);
            repository.setProjIdC(projIdC);
            List<Repository> listR = repositoryMapper.searchOut(repository);
            return new PageInfo<>(listR);
        }
    }

    /*新建出库单*/
    @Override
    public Object InsertRepository (HttpServletRequest request){
        Repository repository = new Repository();
        repository.setOutId(request.getParameter("outId"));
        repository.setOutTime(request.getParameter("outTime"));
        repository.setOutInfo(request.getParameter("outInfo"));
        repository.setOutNum(request.getParameter("outNum"));
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setPostId(request.getParameter("postId"));
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setMaterialName(request.getParameter("materialName"));
        repository.setSpec(request.getParameter("spec"));
        repository.setUnit(request.getParameter("unit"));
        repository.setSn(request.getParameter("sn"));
        repository.setOutReptC(request.getParameter("outReptC"));
        repository.setRemark(request.getParameter("remark"));
        repository.setProjId(request.getParameter("projId"));
        repository.setProjName(request.getParameter("projName"));
        outRepositoryMapper.insertNew(repository);
        return RespUtil.response("200","请求成功",null);
    }

    /*更新仓库*/
    @Override
    public Object OutRepository(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setOutNum(request.getParameter("outNum"));
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setPostId(request.getParameter("postId"));
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setReptId(request.getParameter("reptId"));
        material.setPostId(request.getParameter("postId"));
        material.setNum(request.getParameter("outNum"));
        material.setReptName(repositoryMapper.searchReptName(material));
        material.setCategory(materialMapper.MaterialId(material).getCategory());
        material.setName(materialMapper.MaterialId(material).getName());
        material.setSpec(materialMapper.MaterialId(material).getSpec());
        material.setBrand(materialMapper.MaterialId(material).getBrand());
        material.setPrice(materialMapper.MaterialId(material).getPrice());
        // 减少物料数量
        inventoryMapper.changeNumMatOut(repository);
        List<HashMap<String, Object>> materialDetail = inRepositoryMapper.materialLocation(repository.getMaterialId());
        for (int i = 0; i < materialDetail.size(); i++) {
            if ( Integer.parseInt(request.getParameter("outNum")) == Integer.parseInt(materialMapper.getNumD(material)) ) {
                materialMapper.deleteDetail(material);
            }else{
                materialMapper.updDetailM(material);
            }
        }
        return RespUtil.response("200","请求成功",null);
    }

    /*更新物料*/
    @Override
    public void OutMaterial(HttpServletRequest request) throws ParseException {
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            Material material = new Material();
            material.setId(listOUT.get(i).get("theMatId").toString());
            String postId = "";
            if (listOUT.get(i).containsKey("postId")){
                postId = listOUT.get(i).get("postId").toString();
            }
            material.setPostId(postId);
            material.setReptId(listOUT.get(i).get("reptId").toString());
            String number = repositoryMapper.numInPost(material);
            String outNum = "";
            String outNumA = listOUT.get(i).get("number").toString();
            if (outNumA.contains(".")){
                outNum = outNumA.substring(0,outNumA.indexOf("."));
            }else{
                outNum = outNumA;
            }
            material.setNum(outNum);
            if (parseInt(number) == parseInt(outNum)) {
                materialMapper.deleteDetail(material);
            } else {
                materialMapper.updDetailM(material);
            }
            materialMapper.updMatM(material);
        }
    }

    /*新建出库单*/
    @Override
    public List showOutId(HttpServletRequest request) {
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setOutId(dateToId);
        List showOutId = outRepositoryMapper.showOUTID(repository);
        return showOutId;
    }

    /*删除出库*/
    @Override
    public void DeleteOutRept(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setOnlyIdOut( parseInt(request.getParameter("onlyIdOut")) );
        outRepositoryMapper.delete(repository);
    }

    @Override
    public String canOut(HttpServletRequest request){
        String result = "";
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            Material material = new Material();
            material.setId(listOUT.get(i).get("theMatId").toString());
            String OUTNUMGET = listOUT.get(i).get("number").toString();
            String OUTNUM = "";
            if (OUTNUMGET.contains(".")) {
                OUTNUM = OUTNUMGET.substring(0,OUTNUMGET.indexOf("."));
            }else {
                OUTNUM = OUTNUMGET;
            }
            String MIN = materialMapper.MinLimit(material);
            String NUM = materialMapper.getNum(material);
            if (parseInt(NUM) - parseInt(OUTNUM) < parseInt(MIN)) {
                result = "1";
                break;
            } else{
                result = "0";
            }
        }
        return result;
    }

    @Override
    public String negative(HttpServletRequest request) {
        String result = "";
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            Material material = new Material();
            material.setId(listOUT.get(i).get("theMatId").toString());
            String OUTNUMGET = listOUT.get(i).get("number").toString();
            String OUTNUM = "";
            if (OUTNUMGET.contains(".")) {
                OUTNUM = OUTNUMGET.substring(0,OUTNUMGET.indexOf("."));
            }else {
                OUTNUM = OUTNUMGET;
            }
            String postId = "";
            if (listOUT.get(i).containsKey("postId")){
                postId = listOUT.get(i).get("postId").toString();
            }
            material.setPostId(postId);
            material.setReptId(listOUT.get(i).get("reptId").toString());
            String NUM = materialMapper.getNumD(material);
            if (parseInt(NUM) - parseInt(OUTNUM) < 0) {
                result = "1";
                break;
            } else{
                result = "0";
            }
        }
        return result;
    }

    @Override
    public List<Repository> wdbhR() {
        List<Repository> list = outRepositoryMapper.wdbhR();
        return list;
    }

    @Override
    public List<Repository> wdbhX() {
        List<Repository> list = outRepositoryMapper.wdbhX();
        return list;
    }

    @Override
    public List<Repository> wdbhC() {
        List<Repository> list = outRepositoryMapper.wdbhC();
        return list;
    }


    public List<Repository> showmatR(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = outRepositoryMapper.showmatR(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    public List<Repository> showmatX(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = outRepositoryMapper.showmatX(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showmatC(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = outRepositoryMapper.showmatC(wdbh);
        return list;
    }

    @Override
    public List<Repository> showprojX(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = outRepositoryMapper.showprojX(wdbh);
        return list;
    }

    @Override
    public List<Repository> showprojR(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = outRepositoryMapper.showprojR(wdbh);
        return list;
    }

    @Override
    public String getInstId(String instid) {
        if (instid != null && !instid.equals("") ){
            outRepositoryMapper.updateInstId(instid);
        }
        return instid;
    }

    @Override
    public void updateApprove(String instId) {
        String secondOne = second;
        String thirdOne = third;
        String fourthOne = fourth;
        if (instId != null && !instId.equals("") ){
            outRepositoryMapper.updateApprove(instId,secondOne,thirdOne,fourthOne);
            // 最后一人审批通过的情况
            if (!fourthOne.equals("")){
                List<Repository> listOUT = outRepositoryMapper.getInId(instId);
                for (int i = 0;i < listOUT.size();i++) {
                    //更新物料
                    Material material = new Material();
                    material.setId(listOUT.get(i).getMaterialId());
                    String POSTID = postId;
                    if (listOUT.get(i).getPostId() != null) {
                        POSTID = listOUT.get(i).getPostId();
                    }
                    material.setPostId(POSTID);
                    material.setReptId(listOUT.get(i).getReptId());
                    String number = repositoryMapper.numInPost(material);
                    String outNum = "";
                    String outNumA = listOUT.get(i).getOutNum();
                    if (outNumA.contains(".")){
                        outNum = outNumA.substring(0,outNumA.indexOf("."));
                    }else{
                        outNum = outNumA;
                    }
                    material.setNum(outNum);
                    /*if (parseInt(number) == parseInt(outNum)) {
                        materialMapper.deleteDetail(material);
                    } else {
                        materialMapper.updDetailM(material);
                    }*/
                    materialMapper.updDetailM(material);
                    materialMapper.updMatM(material);
                }
                // 物品消耗添加项目
                String theOutId = listOUT.get(0).getOutId();
                operationService.addMaterialInfor(theOutId);
            }
        }
    }

    @Override
    public String node(HttpServletRequest request) {
        String taskid = request.getParameter("taskid");
        String node = outRepositoryMapper.node(taskid);
        return node;
    }

    @Override
    public List<Repository> postInfo(HttpServletRequest request) {
        String instId = request.getParameter("instid");
        List<Repository> list = outRepositoryMapper.getInId(instId);
        return list;
    }

    @Override
    public List<Repository> approveName(HttpServletRequest request) {
        String instid = request.getParameter("instid");
        List<Repository> list = outRepositoryMapper.approveName(instid);
        return list;
    }

    // 获取审批
    @Override
    public void getApprove(HttpServletRequest request) {
        String secondOne = request.getParameter("secondOne");
        String thirdOne = request.getParameter("thirdOne");
        String fourthOne = request.getParameter("fourthOne");
        if (secondOne != null){
            second = secondOne;
        }
        if (thirdOne != null){
            third = thirdOne;
        }
        if (fourthOne != null){
            fourth = fourthOne;
        }
    }

    static String second = " ";
    static String third = " ";
    static String fourth = " ";
    static String postId = " ";

    // 查询草稿
    @Override
    public PageInfo<Repository> showDraft(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = outRepositoryMapper.findDraft();
        return new PageInfo<>(list);
    }

    // 保存草稿
    @Override
    public void insertDraft(HttpServletRequest request) throws ParseException {
        String outId = request.getParameter("outId");
        Repository repository1 = new Repository();
        repository1.setOutId(outId);
        outRepositoryMapper.deleteDraft(repository1);
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            String OUTREPTC = request.getParameter("outReptC");
            String OUTID = request.getParameter("outId");
            String OUTNUMGET = listOUT.get(i).containsKey("number") ? listOUT.get(i).get("number").toString() : "";
            String OUTNUM = "";
            if (OUTNUMGET.contains(".")) {
                OUTNUM = OUTNUMGET.substring(0,OUTNUMGET.indexOf("."));
            }else {
                OUTNUM = OUTNUMGET;
            }
            String date = request.getParameter("outTime");
            String OUTTIME = "";
            if(!date.equals("") && !date.equals(null)) {
                date = date.replace("Z", " UTC");// 注意是空格+UTC
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
                Date d = format.parse(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sDate = sdf.format(d);
                OUTTIME = sDate;
            }
            String OUTINFO = request.getParameter("outInfo");
            Material material = new Material();
            material.setId(listOUT.get(i).get("theMatId").toString());
            String POSTID = "";
            String REPTID = "";
            if (listOUT.get(i).get("postId") != null){
                POSTID = listOUT.get(i).get("postId").toString();
            }
            if (listOUT.get(i).get("reptId") != null){
                REPTID = listOUT.get(i).get("reptId").toString();
            }
            String MATERIALID = listOUT.get(i).get("theMatId").toString();
            String MATERIALNAME = listOUT.get(i).get("theMatName").toString();
            String UNIT = listOUT.get(i).get("theMatUnit").toString();
            String SPEC = listOUT.get(i).get("theMatSpec").toString();
            String REMARK = listOUT.get(i).get("theMatRemark").toString();
            String PROJID = request.getParameter("projId");
            String PROJNAME = request.getParameter("projName");
            String firstOne = request.getParameter("firstOne");
            String secondOne = "";
            String thirdOne = "";
            String fourthOne = "";
            String bn = null;
            String sn = null;
            String number = "";
            if (!MATERIALID.equals("") && !MATERIALID.equals(null)) {
                number = materialMtMapper.manageBS(material);
            }
            if (number.equals("序列号")) {
                sn = listOUT.get(i).get("theMatBnSn").toString();
                bn = "无";
            } else if (number.equals("批次号")) {
                bn = listOUT.get(i).get("theMatBnSn").toString();
                sn = "无";
            } /*else if (number.equals("否")) {
                sn = "无";
                bn = "无";
            }*/ else {
                sn = " ";
                bn = " ";
            }
            String AUTHOR = request.getParameter("author");
            Repository repository = new Repository();
            repository.setMaterialId(MATERIALID);
            repository.setPostId(POSTID);
            repository.setReptId(REPTID);
            repository.setOutId(OUTID);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setProjId(PROJID);
            repository.setProjName(PROJNAME);
            repository.setAuthor(AUTHOR);
            String REPTcategory = "";
            if (!REPTID.equals("")) {
                REPTcategory = repositoryMapper.searchCategory(repository);
            }
            String C = "";
            outRepositoryMapper.insertDraft(REPTcategory,OUTID,OUTTIME,OUTNUM,OUTINFO,REPTID,POSTID,MATERIALID,MATERIALNAME,SPEC,UNIT,sn,bn,OUTREPTC,REMARK,PROJID,PROJNAME,C,firstOne,secondOne,thirdOne,fourthOne,AUTHOR);
        }
    }

    // 删除草稿
    @Override
    public void deleteDraft(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setOutId(request.getParameter("outId"));
        outRepositoryMapper.deleteDraft(repository);
    }


    // 确认是否是草稿
    @Override
    public String checkDraft(HttpServletRequest request) {
        String draftButton = "";
        String outId = request.getParameter("outId");
        String materialId = request.getParameter("materialId");
        String author = request.getParameter("author");
        Repository repository = new Repository();
        repository.setOutId(outId);
        repository.setMaterialId(materialId);
        String result = outRepositoryMapper.checkDraft(repository);
        if (result == null) {
            result = "";
        }
        if (result.equals(author)) {
            draftButton = "1";
        }else {
            draftButton = "0";
        }
        return draftButton;
    }

    // 返回草稿信息
    @Override
    public List<Repository> postDraft(HttpServletRequest request) {
        String outId = request.getParameter("outId");
        List<Repository> list = outRepositoryMapper.getDraft(outId);
        return list;
    }

    // 入库通知弹框
    @Override
    public List<HashMap<String,Object>> notice(HttpServletRequest request) {
        String category = request.getParameter("category");
        List<HashMap<String,Object>> list = null;
        if (category.equals("日常领用")){
            list = outRepositoryMapper.getNoticeR();
        }
        if (category.equals("销售发货")){
            list = outRepositoryMapper.getNoticeX();
        }
        return list;
    }

    // 弹框子表
    @Override
    public List<HashMap<String,Object>> noticeChild(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<HashMap<String,Object>> list = outRepositoryMapper.noticeChild(wdbh);
        return list;
    }

    /*所有出库通知*/
    @Override
    public PageInfo<Repository> showNotice(Page page, HttpServletRequest request){
        String category = request.getParameter("category");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = null;
        if (category.equals("日常领用")) {
            list = outRepositoryMapper.allNoticeR();
        }
        if (category.equals("销售发货")) {
            list = outRepositoryMapper.allNoticeX();
        }
        return new PageInfo<>(list);
    }

    // 根据ID查物料价格
    @Override
    public String priceOfId(String id){
        String price = outRepositoryMapper.priceOfId(id);
        return price;
    }
}

