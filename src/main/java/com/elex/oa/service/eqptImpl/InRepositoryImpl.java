package com.elex.oa.service.eqptImpl;

import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.regexp.RE;
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
public class InRepositoryImpl implements InRepositoryService {

    @Resource
    private InRepositoryMapper inRepositoryMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private OutRepositoryMapper outRepositoryMapper;

    @Resource
    private RepositoryMtMapper repositoryMtMapper;

    @Resource
    private MaterialMtMapper materialMtMapper;

    @Resource
    private InRepositoryImpl inRepositoryImpl;


    /*所有单号*/
    @Override
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = inRepositoryMapper.findAll();
        return new PageInfo<>(list);
    }

    /*查询*/
    @Override
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        String reptId = request.getParameter("reptId");
        String reptIdC = request.getParameter("reptIdC");
        String reptCategory = request.getParameter("reptCate");
        String reptCategoryC = request.getParameter("reptCateC");
        String postId = request.getParameter("postId");
        String postIdC = request.getParameter("postIdC");
        String inId = request.getParameter("inId");
        String inIdC = request.getParameter("inIdC");
        String sn = request.getParameter("sn");
        String snC = request.getParameter("snC");
        String bn = request.getParameter("bn");
        String bnC = request.getParameter("bnC");
        String inTime = request.getParameter("inTime");
        String inTimeC = request.getParameter("inTimeC");
        String inNum = request.getParameter("inNum");
        String inNumC = request.getParameter("inNumC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String projId = request.getParameter("projId");
        String projIdC = request.getParameter("projIdC");
        String projName = request.getParameter("projName");
        String projNameC = request.getParameter("projNameC");
        // 查询判断
        if (projId.equals("") && projName.equals("") && reptId.equals("") && reptCategory.equals("") && postId.equals("") && inId.equals("") && sn.equals("") && bn.equals("") && inTime.equals("") && inNum.equals("") && materialId.equals("")){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = inRepositoryMapper.findAll();
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
            repository.setInId(inId);
            repository.setInIdC(inIdC);
            repository.setInNum(inNum);
            repository.setInNumC(inNumC);
            repository.setBn(bn);
            repository.setBnC(bnC);
            repository.setSn(sn);
            repository.setSnC(snC);
            repository.setInTime(inTime);
            repository.setInTimeC(inTimeC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);
            repository.setProjName(projName);
            repository.setProjNameC(projNameC);
            repository.setProjId(projId);
            repository.setProjIdC(projIdC);
            List<Repository> listR = repositoryMapper.searchIn(repository);
            return new PageInfo<>(listR);
        }
    }

    /*确定最新入库单号*/
    @Override
    public List showInId(HttpServletRequest request) {
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setInId(dateToId);
        List showInId = inRepositoryMapper.showINID(repository);
        return showInId;
    }

    /*入库申请*/
    @Override
    public String checkInRepository (HttpServletRequest request){
        String theMaterial = "";
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++){
            String MATERIALID = listIN.get(i).get("theMatId").toString();
            Material material = new Material();
            material.setId(MATERIALID);
            if ( inRepositoryMapper.ID(material) == null) {
                theMaterial = listIN.get(i).get("theMatId").toString();
            } else {
                theMaterial = "1";
                break;
            }
        }
        return theMaterial;
    }

    /*新建入库单*/
    @Override
    public String NewRepository(HttpServletRequest request) throws ParseException {
        String inId = request.getParameter("inId");
        Repository repository1 = new Repository();
        repository1.setInId(inId);
        inRepositoryMapper.deleteDraft(repository1);
        String a = "";
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++){
            String INREPTC = request.getParameter("inReptC");
            String INID = request.getParameter("inId");
            String INNUMGET = listIN.get(i).get("number").toString();
            String INNUM = "";
            if (INNUMGET.contains(".")) {
                INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
            }else {
                INNUM = INNUMGET;
            }
            // 格林尼治时间转格式
            String date = request.getParameter("inTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            String INTIME = sDate;
            String ININFO = request.getParameter("inInfo");
            String POSTID = postId;
            if (listIN.get(i).get("postId") != null){
                POSTID = listIN.get(i).get("postId").toString();
            }
            String REPTID = listIN.get(i).get("reptId").toString();
            String MATERIALID = listIN.get(i).get("theMatId").toString();
            String MATERIALNAME = listIN.get(i).get("theMatName").toString();
            String UNIT = listIN.get(i).get("theMatUnit").toString();
            String SPEC = listIN.get(i).get("theMatSpec").toString();
            String CHECK = listIN.get(i).get("theMatCheck").toString();
            String REMARK = listIN.get(i).get("theMatRemark").toString();
            String PROJID = request.getParameter("projId");
            String PROJNAME = request.getParameter("projName");
            Material material = new Material();
            material.setId(MATERIALID);
            String firstOne = request.getParameter("firstOne");
            String secondOne = "";
            String thirdOne = "";
            String fourthOne = "";
            if ( materialMtMapper.needCheck(material).equals("是") && CHECK == null){
                a = "1";
                break;
            } else if ( !materialMtMapper.manageBS(material).equals("否") && (listIN.get(i).get("theMatBnSn").toString().equals("") || !listIN.get(i).containsKey("theMatBnSn")) ) {
                a = "2";
                break;
            } else {
                String bn = null;
                String sn = null;
                String number = materialMtMapper.manageBS(material);
                if (number.equals("否")) {
                    sn = "无";
                    bn = "无";
                } else if (number.equals("序列号")) {
                    sn = listIN.get(i).get("theMatBnSn").toString();
                    bn = "无";
                } else if (number.equals("批次号")) {
                    bn = listIN.get(i).get("theMatBnSn").toString();
                    sn = "无";
                }
                Repository repository = new Repository();
                repository.setInId(INID);
                repository.setMaterialId(MATERIALID);
                repository.setMaterialName(MATERIALNAME);
                repository.setUnit(UNIT);
                repository.setSpec(SPEC);
                repository.setReptId(REPTID);
                repository.setPostId(POSTID);
                repository.setBn(bn);
                repository.setSn(sn);
                repository.setCheck(CHECK);
                repository.setRemark(REMARK);
                repository.setInInfo(ININFO);
                repository.setProjId(PROJID);
                repository.setProjName(PROJNAME);
                String REPTcategory = repositoryMapper.searchCategory(repository);
                String C = "";
                inRepositoryMapper.insertNew(REPTcategory, INID, INTIME, INNUM, ININFO, REPTID, POSTID, MATERIALID, MATERIALNAME, SPEC, UNIT, sn, bn,INREPTC, CHECK, REMARK,PROJID,PROJNAME,C,firstOne,secondOne,thirdOne,fourthOne);
                a = "0";
            }
        }
        return a;
    }

    /*更新物料*/
    @Override
    public void InsertMaterial(HttpServletRequest request) throws ParseException {
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            String INNUMGET = listIN.get(i).get("number").toString();
            String INNUM = "";
            if (INNUMGET.contains(".")) {
                INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
            }else {
                INNUM = INNUMGET;
            }
            Material material = new Material();
            material.setId(listIN.get(i).get("theMatId").toString());
            material.setNum(INNUM);
            String postId = "";
            if (listIN.get(i).get("postId") != null) {
                postId = listIN.get(i).get("postId").toString();
            }
            material.setPostId(postId);
            material.setReptId(listIN.get(i).get("reptId").toString());
            material.setName(listIN.get(i).get("theMatName").toString());
            material.setSpec(listIN.get(i).get("theMatSpec").toString());
            Material material1 = materialMapper.MaterialId(material);
            material.setCategory(material1.getCategory());
            material.setBrand(material1.getBrand());
            material.setPrice(material1.getPrice());
            materialMapper.updMat(material);
            // 查询是否库存记录
            String result = materialMapper.matInDetail(material);
            if(result == null){
                materialMapper.insertDetail(material);
                materialMapper.deleteNull(material);
            }else {
                materialMapper.updDetail(material);
            }
        }
    }

    /*更新仓库*/
    @Override
    public void InsertRepository(HttpServletRequest request) {
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            Repository repository = new Repository();
            repository.setReptId(listIN.get(i).get("reptId").toString());
            repository.setPostId(listIN.get(i).get("postId").toString());
            repository.setMaterialId(listIN.get(i).get("theMatId").toString());
            repository.setSpec(listIN.get(i).get("theMatSpec").toString());
            /*更新数量*/
            String emptyRept = repositoryMapper.getNumber(repository);
            if (emptyRept.equals("0")) {
                repository.setNum(listIN.get(i).get("number").toString());
            } else {
                String number = repositoryMapper.getNumber(repository);
                String numAfterIn = String.valueOf(parseInt(number) + parseInt(listIN.get(i).get("number").toString()));
                repository.setNum(numAfterIn);
            }
            int onlyIdP = repositoryMapper.lockOnlyIdP(repository);
            repository.setOnlyIdP(onlyIdP);
            repositoryMapper.updPosition(repository);
        }
    }

    /*删除入库*/
    @Override
    public void DeleteInRept(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setOnlyIdIn( parseInt(request.getParameter("onlyIdIn")) );
        inRepositoryMapper.delete(repository);
    }

    /*核对数量*/
    @Override
    public String canIn(HttpServletRequest request){
        String result = "";
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            Material material = new Material();
            material.setId(listIN.get(i).get("theMatId").toString());
            String INNUMGET = listIN.get(i).get("number").toString();
            String INNUM = "";
            if (INNUMGET.contains(".")) {
                INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
            }else {
                INNUM = INNUMGET;
            }
            String MAX = materialMapper.MaxLimit(material);
            String NUM = materialMapper.getNum(material);
            if (parseInt(NUM) + parseInt(INNUM) > parseInt(MAX)) {
                result = "1";
                break;
            } else{
                result = "0";
            }
        }
        return result;
    }

    @Override
    public String postCap(HttpServletRequest request) {
        String result = "";
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            String reptId = listIN.get(i).get("reptId").toString();
            String postId = "";
            String NUM = "0";
            String INNUMGET = listIN.get(i).get("number").toString();
            String INNUM = "";
            if (INNUMGET.contains(".")) {
                INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
            }else {
                INNUM = INNUMGET;
            }
            String postCap = String.valueOf(parseInt(NUM) + parseInt(INNUM) + 1);
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setPostId(postId);
            repository.setPosition(postId);
            if (listIN.get(i).containsKey("postId") && !listIN.get(i).get("postId").equals("")){
                postId = listIN.get(i).get("postId").toString();
                Material material = new Material();
                material.setId(listIN.get(i).get("theMatId").toString());
                INNUM = listIN.get(i).get("number").toString();
                repository.setPostId(postId);
                if (repositoryMapper.getNumber(repository) != null){
                    NUM = repositoryMapper.getNumber(repository);
                }else{
                    NUM = "0";
                }
                Repository repository1 = repositoryMtMapper.searchPostCap(repository);
                if (repository1.getPostCap().equals("无限制")){
                    postCap = String.valueOf(999999999);
                }else {
                    postCap = repository1.getPostCap();
                }
            }
            Repository repository2 = repositoryMtMapper.noPost(repository);
            if (parseInt(NUM) + parseInt(INNUM) <= parseInt(postCap) || repository2.getPostManage().equals("否")){
                result = "0";
            }else {
                result = "1";
                break;
            }
        }
        return result;
    }

    @Override
    public List<Repository> wdbh() {
        List<Repository> list = inRepositoryMapper.wdbh();
        return list;
    }

    @Override
    public List<Repository> showmat(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = inRepositoryMapper.showmat(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showproj(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = inRepositoryMapper.showproj(wdbh);
        return list;
    }

    // 流程id传给明细
    @Override
    public String getInstId(String instid, HttpServletRequest request){
        if (instid != null && !instid.equals("") ){
            inRepositoryMapper.updateInstId(instid);
        }
        return instid;
    }

    // 更新审批意见
    @Override
    public void updateApprove(String instId){
        String secondOne = second;
        String thirdOne = third;
        String fourthOne = fourth;
        if (instId != null && !instId.equals("") ){
            inRepositoryMapper.updateApprove(instId,secondOne,thirdOne,fourthOne);
            // 最后一人审批通过的情况
            if (!fourthOne.equals("")){
                List<Repository> listIN = inRepositoryMapper.getInId(instId);
                for (int i = 0;i < listIN.size();i++) {
                    //更新物料
                    String INNUMGET = listIN.get(i).getInNum();
                    String INNUM = "";
                    if (INNUMGET.contains(".")) {
                        INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
                    }else {
                        INNUM = INNUMGET;
                    }
                    Material material = new Material();
                    material.setId(listIN.get(i).getMaterialId());
                    material.setNum(INNUM);
                    String POSTID = postId;
                    if (listIN.get(i).getPostId() != null) {
                        POSTID = listIN.get(i).getPostId();
                    }
                    material.setPostId(POSTID);
                    material.setReptId(listIN.get(i).getReptId());
                    material.setName(listIN.get(i).getMaterialName());
                    material.setSpec(listIN.get(i).getSpec());
                    Material material1 = materialMapper.MaterialId(material);
                    material.setCategory(material1.getCategory());
                    material.setBrand(material1.getBrand());
                    material.setPrice(material1.getPrice());
                    materialMapper.updMat(material);
                    // 查询是否库存记录
                    String result = materialMapper.matInDetail(material);
                    if(result == null){
                        materialMapper.insertDetail(material);
                        materialMapper.deleteNull(material);
                    }else {
                        materialMapper.updDetail(material);
                    }
                }
            }
        }
    }

    @Override
    public List<Repository> postInfo(HttpServletRequest request){
        String instId = request.getParameter("instid");
        List<Repository> list = inRepositoryMapper.getInId(instId);
        return list;
    }

    @Override
    public String node(HttpServletRequest request) {
        String taskid = request.getParameter("taskid");
        String node = inRepositoryMapper.node(taskid);
        return node;
    }

    @Override
    public List<Repository> approveName(HttpServletRequest request){
        String instid = request.getParameter("instid");
        List<Repository> list = inRepositoryMapper.approveName(instid);
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

    static String second = "";
    static String third = "";
    static String fourth = "";
    static String postId = "";

    // 查询草稿
    @Override
    public PageInfo<Repository> showDraft(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = inRepositoryMapper.findDraft();
        return new PageInfo<>(list);
    }

    // 保存草稿
    @Override
    public void insertDraft(HttpServletRequest request) throws ParseException {
        String inId = request.getParameter("inId");
        Repository repository1 = new Repository();
        repository1.setInId(inId);
        inRepositoryMapper.deleteDraft(repository1);
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++){
            String INREPTC = request.getParameter("inReptC");
            String INID = request.getParameter("inId");
            String INNUMGET = listIN.get(i).get("number").toString();
            String INNUM = "";
            if (INNUMGET.contains(".")) {
                INNUM = INNUMGET.substring(0,INNUMGET.indexOf("."));
            }else {
                INNUM = INNUMGET;
            }
            // 格林尼治时间转格式
            String date = request.getParameter("inTime");
            String INTIME = "";
            if(!date.equals("") && !date.equals(null)) {
                date = date.replace("Z", " UTC");// 注意是空格+UTC
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
                Date d = format.parse(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sDate = sdf.format(d);
                INTIME = sDate;
            }
            String ININFO = request.getParameter("inInfo");
            String POSTID = postId;
            String REPTID = "";
            if (listIN.get(i).get("postId") != null){
                POSTID = listIN.get(i).get("postId").toString();
            }
            if (listIN.get(i).get("reptId") != null){
                REPTID = listIN.get(i).get("reptId").toString();
            }
            String MATERIALID = listIN.get(i).get("theMatId").toString();
            String MATERIALNAME = listIN.get(i).get("theMatName").toString();
            String UNIT = listIN.get(i).get("theMatUnit").toString();
            String SPEC = listIN.get(i).get("theMatSpec").toString();
            String CHECK = listIN.get(i).get("theMatCheck").toString();
            String REMARK = listIN.get(i).get("theMatRemark").toString();
            String PROJID = request.getParameter("projId");
            String PROJNAME = request.getParameter("projName");
            Material material = new Material();
            material.setId(MATERIALID);
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
                sn = listIN.get(i).get("theMatBnSn").toString();
                bn = "无";
            } else if (number.equals("批次号")) {
                bn = listIN.get(i).get("theMatBnSn").toString();
                sn = "无";
            } else if (number.equals("否")) {
                sn = "无";
                bn = "无";
            } else {
                sn = " ";
                bn = " ";
            }
            Repository repository = new Repository();
            repository.setInId(INID);
            repository.setMaterialId(MATERIALID);
            repository.setMaterialName(MATERIALNAME);
            repository.setUnit(UNIT);
            repository.setSpec(SPEC);
            repository.setReptId(REPTID);
            repository.setPostId(POSTID);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setCheck(CHECK);
            repository.setRemark(REMARK);
            repository.setInInfo(ININFO);
            repository.setProjId(PROJID);
            repository.setProjName(PROJNAME);
            String REPTcategory = "";
            if (!REPTID.equals("")) {
                REPTcategory = repositoryMapper.searchCategory(repository);
            }
            String C = "";
            inRepositoryMapper.insertDraft(REPTcategory, INID, INTIME, INNUM, ININFO, REPTID, POSTID, MATERIALID, MATERIALNAME, SPEC, UNIT, sn, bn,INREPTC, CHECK, REMARK,PROJID,PROJNAME,C,firstOne,secondOne,thirdOne,fourthOne);
        }
    }


    // 确认是否是草稿
    @Override
    public String checkDraft(HttpServletRequest request) {
        String a = "";
        String inId = request.getParameter("inId");
        String materialId = request.getParameter("materialId");
        Repository repository = new Repository();
        repository.setInId(inId);
        repository.setMaterialId(materialId);
        String result = inRepositoryMapper.checkDraft(repository);
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
        String inId = request.getParameter("inId");
        List<Repository> list = inRepositoryMapper.getDraft(inId);
        return list;
    }

    /*所有入库通知*/
    @Override
    public PageInfo<Repository> showNotice(Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = inRepositoryMapper.allNotice();
        return new PageInfo<>(list);
    }

    // 入库通知弹框
    @Override
    public List<HashMap<String,Object>> notice(HttpServletRequest request) {
        String category = request.getParameter("category");
        List<HashMap<String,Object>> list = null;
        if (category.equals("采购收货")){
            list = inRepositoryMapper.getNotice();
        }
        return list;
    }

    // 弹框子表
    @Override
    public List<HashMap<String,Object>> noticeChild(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<HashMap<String,Object>> list = inRepositoryMapper.noticeChild(wdbh);
        return list;
    }
}
