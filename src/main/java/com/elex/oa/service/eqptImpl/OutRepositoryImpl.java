package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.OutRepositoryService;
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

    /*所有单号*/
    @Override
    public PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list =  outRepositoryMapper.findAll();
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
        if (projId.equals("") && projName.equals("") && reptId.equals("") && reptCategory.equals("") && postId.equals("") && outId.equals("") && sn.equals("") && bn.equals("") && outTime.equals("")){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = outRepositoryMapper.findAll();
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
    public String InsertRepository (HttpServletRequest request)throws ParseException{
        String outId = request.getParameter("outId");
        Repository repository1 = new Repository();
        repository1.setOutId(outId);
        outRepositoryMapper.deleteDraft(repository1);
        String a = "";
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            String OUTREPTC = request.getParameter("outReptC");
            String OUTID = request.getParameter("outId");
            String OUTNUMGET = listOUT.get(i).get("theMatNum").toString();
            String OUTNUM = "";
            if (OUTNUMGET.contains(".")) {
                OUTNUM = OUTNUMGET.substring(0,OUTNUMGET.indexOf("."));
            }else {
                OUTNUM = OUTNUMGET;
            }
            String date = request.getParameter("outTime");
            date = date.replace("Z", " UTC");//注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            String OUTTIME = sDate;
            String OUTINFO = request.getParameter("outInfo");
            Material material = new Material();
            material.setId(listOUT.get(i).get("theMatId").toString());
            String number = materialMtMapper.manageBS(material);
            String POSTID = "无";
            if (listOUT.get(i).get("postId") != null){
                POSTID = listOUT.get(i).get("postId").toString();
            }
            String REPTID = listOUT.get(i).get("reptId").toString();
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
            if ( !materialMtMapper.manageBS(material).equals("否") && (listOUT.get(i).get("theMatBnSn").toString().equals("") || !listOUT.get(i).containsKey("theMatBnSn")) ) {
                a = "2";
                break;
            } else{
                String bn = null;
                String sn = null;
                if (number.equals("否")) {
                    sn = "无";
                    bn = "无";
                } else if (number.equals("序列号")) {
                    sn = listOUT.get(i).get("theMatBnSn").toString();
                    bn = "无";
                } else if (number.equals("批次号")) {
                    bn = listOUT.get(i).get("theMatBnSn").toString();
                    sn = "无";
                }
                Repository repository = new Repository();
                repository.setMaterialId(MATERIALID);
                repository.setPostId(POSTID);
                repository.setReptId(REPTID);
                repository.setOutId(OUTID);
                repository.setBn(bn);
                repository.setSn(sn);
                repository.setProjId(PROJID);
                repository.setProjName(PROJNAME);
                String REPTcategory = repositoryMapper.searchCategory(repository);
                String C = "";
                outRepositoryMapper.insertNew(REPTcategory,OUTID,OUTTIME,OUTNUM,OUTINFO,REPTID,POSTID,MATERIALID,MATERIALNAME,SPEC,UNIT,sn,bn,OUTREPTC,REMARK,PROJID,PROJNAME,C,firstOne,secondOne,thirdOne,fourthOne);
                a = "0";
            }
        }
        return a;
    }

    /*更新仓库*/
    @Override
    public void OutRepository(HttpServletRequest request) {
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(listOUT.get(i).get("theMatId").toString());
            repository.setReptId(listOUT.get(i).get("reptId").toString());
            repository.setPostId(listOUT.get(i).get("postId").toString());
            String REPTcategory = repositoryMapper.searchCategory(repository);
            repository.setReptCategory(REPTcategory);
            /*更新数量*/
            String number = repositoryMapper.getNumber(repository);
            String numAfterOut = String.valueOf(parseInt(number) - parseInt(listOUT.get(i).get("theMatNum").toString()));
            repository.setNum(numAfterOut);
            /*int onlyIdR = repositoryMapper.lockOnlyIdR(repository);
            repository.setOnlyIdR(onlyIdR);
            repositoryMapper.updRepository(repository);*/
            int onlyIdP = repositoryMapper.lockOnlyIdP(repository);
            repository.setOnlyIdP(onlyIdP);
            repositoryMapper.updPosition(repository);
        }
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
            String outNumA = listOUT.get(i).get("theMatNum").toString();
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
            String OUTNUMGET = listOUT.get(i).get("theMatNum").toString();
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
            String OUTNUMGET = listOUT.get(i).get("theMatNum").toString();
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
                    if (parseInt(number) == parseInt(outNum)) {
                        materialMapper.deleteDetail(material);
                    } else {
                        materialMapper.updDetailM(material);
                    }
                    materialMapper.updMatM(material);
                }
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
            String OUTNUMGET = listOUT.get(i).get("theMatNum").toString();
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
            String number = materialMtMapper.manageBS(material);
            String POSTID = "无";
            if (listOUT.get(i).get("postId") != null){
                POSTID = listOUT.get(i).get("postId").toString();
            }
            String REPTID = listOUT.get(i).get("reptId").toString();
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
            if (!MATERIALID.equals("") && !MATERIALID.equals(null)) {
                number = materialMtMapper.manageBS(material);
            }
            if (number.equals("序列号")) {
                sn = listOUT.get(i).get("theMatBnSn").toString();
                bn = "无";
            } else if (number.equals("批次号")) {
                bn = listOUT.get(i).get("theMatBnSn").toString();
                sn = "无";
            } else if (number.equals("否")) {
                sn = "无";
                bn = "无";
            } else {
                sn = " ";
                bn = " ";
            }
            Repository repository = new Repository();
            repository.setMaterialId(MATERIALID);
            repository.setPostId(POSTID);
            repository.setReptId(REPTID);
            repository.setOutId(OUTID);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setProjId(PROJID);
            repository.setProjName(PROJNAME);
            String REPTcategory = repositoryMapper.searchCategory(repository);
            String C = "";
            outRepositoryMapper.insertNew(REPTcategory,OUTID,OUTTIME,OUTNUM,OUTINFO,REPTID,POSTID,MATERIALID,MATERIALNAME,SPEC,UNIT,sn,bn,OUTREPTC,REMARK,PROJID,PROJNAME,C,firstOne,secondOne,thirdOne,fourthOne);
        }
    }


    // 确认是否是草稿
    @Override
    public String checkDraft(HttpServletRequest request) {
        String a = "";
        String outId = request.getParameter("outId");
        String materialId = request.getParameter("materialId");
        Repository repository = new Repository();
        repository.setInId(outId);
        repository.setMaterialId(materialId);
        String result = outRepositoryMapper.checkDraft(repository);
        if (result != null) {
            a = "1";
        }else {
            a = "0";
        }
        return a;
    }

    // 返回草稿信息
    @Override
    public List<Repository> postDraft(HttpServletRequest request) {
        String outId = request.getParameter("outId");
        List<Repository> list = outRepositoryMapper.getDraft(outId);
        return list;
    }
}

