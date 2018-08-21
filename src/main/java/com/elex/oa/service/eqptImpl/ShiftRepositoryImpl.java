package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.ShiftRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.regexp.RE;
import org.hibernate.validator.constraints.SafeHtml;
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
public class ShiftRepositoryImpl implements ShiftRepositoryService {

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private MaterialMtMapper materialMtMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private ShiftRepositoryMapper shiftRepositoryMapper;

    @Resource
    private OutRepositoryMapper outRepositoryMapper;

    @Resource
    private RepositoryMtMapper repositoryMtMapper;

    // 显示全部数据
    @Override
    public PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list = shiftRepositoryMapper.showRepository();
        return new PageInfo<>(list);
    }

    // 查询数据
    @Override
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        String shiftId = request.getParameter("shiftId");
        String shiftIdC = request.getParameter("shiftIdC");
        String shiftTime = request.getParameter("shiftTime");
        String shiftTimeC = request.getParameter("shiftTimeC");
        String shiftNum = request.getParameter("shiftNum");
        String shiftNumC = request.getParameter("shiftNumC");
        String outReptId = request.getParameter("outReptId");
        String outReptIdC = request.getParameter("outReptIdC");
        String outPostId = request.getParameter("outPostId");
        String outPostIdC = request.getParameter("outPostIdC");
        String inReptId = request.getParameter("inReptId");
        String inReptIdC = request.getParameter("inReptIdC");
        String inPostId = request.getParameter("inPostId");
        String inPostIdC = request.getParameter("inPostIdC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String sn = request.getParameter("sn");
        String snC = request.getParameter("sn");
        String bn = request.getParameter("bn");
        String bnC = request.getParameter("bn");
        String projId = request.getParameter("projId");
        String projIdC = request.getParameter("projIdC");
        String projName = request.getParameter("projName");
        String projNameC = request.getParameter("projNameC");
        if (shiftId.equals("") && shiftTime.equals("") && shiftNum.equals("") && outReptId.equals("") && outPostId.equals("") && inReptId.equals("") && inPostId.equals("") && materialId.equals("") && sn.equals("") && bn.equals("") && projId.equals("") && projName.equals("") ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = shiftRepositoryMapper.showRepository();
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setShiftId(shiftId);
            repository.setShiftIdC(shiftIdC);
            repository.setShiftTime(shiftTime);
            repository.setShiftTimeC(shiftTimeC);
            repository.setShiftNum(shiftNum);
            repository.setShiftNumC(shiftNumC);
            repository.setOutRept(outReptId);
            repository.setOutReptC(outReptIdC);
            repository.setOutPost(outPostId);
            repository.setOutPostC(outPostIdC);
            repository.setInRept(inReptId);
            repository.setInReptC(inReptIdC);
            repository.setInPost(inPostId);
            repository.setInPostC(inPostIdC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);
            repository.setSn(sn);
            repository.setSnC(snC);
            repository.setBn(bn);
            repository.setBnC(bnC);
            repository.setProjName(projName);
            repository.setProjNameC(projNameC);
            repository.setProjId(projId);
            repository.setProjIdC(projIdC);
            List<Repository> listR = shiftRepositoryMapper.searchShift(repository);
            return new PageInfo<>(listR);
        }
    }

    // 记录数据
    @Override
    public void NewRepository(HttpServletRequest request) throws ParseException {
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++) {
            String shiftId = request.getParameter("shiftId");
            String shiftNumGet = listSHIFT.get(i).get("theMatNum").toString();
            String shiftNum = "";
            if (shiftNumGet.contains(".")) {
                shiftNum = shiftNumGet.substring(0,shiftNumGet.indexOf("."));
            }else {
                shiftNum = shiftNumGet;
            }
            if (listSHIFT.get(0).containsKey("number")){
                shiftNum = listSHIFT.get(i).get("number").toString();
            }
            String shiftReptC = request.getParameter("shiftReptC");
            String shiftInfo = "";
            if (shiftReptC.equals("归还") || shiftReptC.equals("生产退料")){
                shiftInfo = "无";
            } else {
                shiftInfo = request.getParameter("shiftInfo");
            }
            String date = request.getParameter("shiftTime");
            date = date.replace("Z", " UTC");//注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            String shiftTime = sDate;
            String outRept = request.getParameter("outRept");
            String outPost = request.getParameter("outPost");
            String inRept = request.getParameter("inRept");
            String inPost = request.getParameter("inPost");
            String materialId = listSHIFT.get(i).get("theMatId").toString();
            String materialName = listSHIFT.get(i).get("theMatName").toString();
            String unit = listSHIFT.get(i).get("theMatUnit").toString();
            String spec = listSHIFT.get(i).get("theMatSpec").toString();
            String remark = listSHIFT.get(i).get("theMatRemark").toString();
            String PROJID = request.getParameter("projId");
            String PROJNAME = request.getParameter("projName");
            Material material = new Material();
            material.setId(materialId);
            String bn = null;
            String sn = null;
            String number = materialMtMapper.manageBS(material);
            if (number.equals("否")) {
                sn = "无";
                bn = "无";
            } else if (number.equals("序列号")) {
                sn = listSHIFT.get(i).get("theMatBnSn").toString();
                bn = "无";
            } else if (number.equals("批次号")) {
                bn = listSHIFT.get(i).get("theMatBnSn").toString();
                sn = "无";
            }
            shiftRepositoryMapper.insertNew(shiftId,shiftTime,shiftReptC,shiftNum,shiftInfo,outRept,outPost,inRept,inPost,materialId,materialName,spec,unit,sn,bn,remark,PROJID,PROJNAME);
        }
    }

    /*同步仓库*/
    @Override
    public void changeRepository(HttpServletRequest request){
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++){
            String materialName = listSHIFT.get(i).get("theMatName").toString();
            String shiftNumGet = listSHIFT.get(i).get("theMatNum").toString();
            String shiftNum = "";
            if (shiftNumGet.contains(".")) {
                shiftNum = shiftNumGet.substring(0,shiftNumGet.indexOf("."));
            }else {
                shiftNum = shiftNumGet;
            }
            if (listSHIFT.get(0).containsKey("number")){
                shiftNum = listSHIFT.get(i).get("number").toString();
            }
            String materialId = listSHIFT.get(i).get("theMatId").toString();
            String outRept = "";
            if (listSHIFT.get(0).containsKey("outRept")){
                outRept = listSHIFT.get(i).get("outRept").toString();
            }
            String outPost = "";
            if (listSHIFT.get(i).containsKey("outPost")){
                outPost = listSHIFT.get(i).get("outPost").toString();
            }
            String inRept = "";
            if (listSHIFT.get(i).containsKey("inRept")){
                inRept = listSHIFT.get(i).get("inRept").toString();
            }
            String inPost = "";
            if (listSHIFT.get(i).containsKey("inPost")){
                inPost = listSHIFT.get(i).get("inPost").toString();
            }
            Repository repository = new Repository();
            repository.setReptId(outRept);
            repository.setPostId(outPost);
            String outNum = shiftRepositoryMapper.theNumberOut(repository);
            Material material = new Material();
            material.setReptId(outRept);
            material.setPostId(outPost);
            material.setId(materialId);
            material.setNum(shiftNum);
            if (parseInt(outNum) == parseInt(shiftNum)){
                materialMapper.deleteDetail(material);
            }else {
                materialMapper.updDetailM(material);
            }
            Material material1 = new Material();
            material1.setReptId(inRept);
            material1.setPostId(inPost);
            material1.setId(materialId);
            material1.setNum(shiftNum);
            material1.setName(materialName);
            material1.setSpec(listSHIFT.get(i).get("theMatSpec").toString());
            Material material2 = materialMapper.MaterialId(material);
            material1.setCategory(material2.getCategory());
            material1.setBrand(material2.getBrand());
            material1.setPrice(material2.getPrice());
            String result = materialMapper.matInDetail(material1);
            if(result == null){
                materialMapper.insertDetail(material1);
            }else {
                materialMapper.updDetail(material1);
            }
            /*String shiftNum = listSHIFT.get(i).get("theMatNum").toString();
            String materialId = listSHIFT.get(i).get("theMatId").toString();
            Material material = new Material();
            material.setId(materialId);
            Material material2 = shiftRepositoryMapper.lockMat(material);
            Repository repositoryOne = new Repository();
            repositoryOne.setPostId(request.getParameter("outPost"));
            repositoryOne.setReptId(request.getParameter("outRept"));
            String theNumber = repositoryMapper.getNumber(repositoryOne);
            if (material2.getNum().equals(shiftNum) || theNumber.equals(shiftNum)) {
                Repository repository = new Repository();
                repository.setPostId(request.getParameter("outPost"));
                repository.setReptId(request.getParameter("outRept"));
                repository.setPrice("0");
                repository.setNum("0");
                repository.setMaterialId("无");
                repository.setCategory("无");
                repository.setMaterialName("无");
                repository.setSpec("无");
                int onlyIdR = repositoryMapper.lockOnlyIdR(repository);
                repository.setOnlyIdR(onlyIdR);
                repositoryMapper.updRepository(repository);
            } else {
                Repository repository = new Repository();
                repository.setPosition(request.getParameter("outPost"));
                repository.setReptId(request.getParameter("outRept"));
                String number = repositoryMapper.getNumber(repository);
                String numAfterOut = String.valueOf(parseInt(number) - parseInt(listSHIFT.get(i).get("theMatNum").toString()));
                repository.setNum(numAfterOut);
                int onlyIdR = repositoryMapper.lockOnlyIdR(repository);
                repository.setOnlyIdR(onlyIdR);
                repositoryMapper.updRepository(repository);
            }
            Repository repository1 = new Repository();
            repository1.setReptId(request.getParameter("inRept"));
            repository1.setPosition(request.getParameter("inPost"));
            repository1.setMaterialId(materialId);
            String number = repositoryMapper.getNumber(repository1);
            if (parseInt(number) == 0) {
                Material material3 = repositoryMapper.getOtherInfo(material);
                repository1.setSpec(material3.getSpec());
                repository1.setCategory(material3.getCategory());
                repository1.setMaterialName(material3.getName());
                repository1.setPrice(material3.getPrice());
                String numAfterIn = String.valueOf(parseInt(number) + parseInt(shiftNum));
                repository1.setNum(numAfterIn);
                int onlyIdR = repositoryMapper.lockOnlyIdR(repository1);
                repository1.setOnlyIdR(onlyIdR);
                repositoryMapper.changeRepository(repository1);
            } else {
                String numAfterIn = String.valueOf(parseInt(number) + parseInt(shiftNum));
                repository1.setNum(numAfterIn);
                int onlyIdR = repositoryMapper.lockOnlyIdR(repository1);
                repository1.setOnlyIdR(onlyIdR);
                repositoryMapper.changeRepository(repository1);
            }*/
        }
    }

    /*同步物料数量*/
    /*public void changeMat(HttpServletRequest request) {
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++){
            Material material = new Material();
            material.setId(listSHIFT.get(i).get("theMatId").toString());
            material.setSn(listSHIFT.get(i).get("theMatBnSn").toString());
            material.setBn(listSHIFT.get(i).get("theMatBnSn").toString());
            Material material1 = materialMapper.getNum(material);
            // 生产入库改变数量
            String num = material1.getNum();
            String result = String.valueOf( Integer.parseInt(num) - Integer.parseInt(listSHIFT.get(i).get("theMatNum").toString()) );
            material.setNum(result);
            materialMapper.updMat(material);
        }
    }*/

    /*确定最新移库单号*/
    @Override
    public List showShiftId(HttpServletRequest request) {
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setShiftId(dateToId);
        List showShiftId = shiftRepositoryMapper.showSHIFTID(repository);
        return showShiftId;
    }

    @Override
    public String postCap(HttpServletRequest request) {
        String result = "";
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++) {
            String reptId = "";
            if (listSHIFT.get(i).containsKey("inRept")){
                reptId = listSHIFT.get(i).get("inRept").toString();
            }
            String postId = "";
            if (listSHIFT.get(i).containsKey("inPost")){
                postId = listSHIFT.get(i).get("inPost").toString();
            }
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setPostId(postId);
            repository.setPosition(postId);
            Material material = new Material();
            material.setId(listSHIFT.get(i).get("theMatId").toString());
            material.setReptId(reptId);
            material.setPostId(postId);
            String INNUM = listSHIFT.get(i).get("theMatNum").toString();
            if (listSHIFT.get(0).containsKey("number")){
                INNUM = listSHIFT.get(i).get("number").toString();
            }
            /*String NUM = repositoryMapper.getNumber(repository);*/
            String NUM = "";
            System.out.println(postId);
            if (!postId.equals("")){
                NUM = repositoryMapper.numInPost(material);
                if (NUM == null){
                    NUM = "0";
                }
            }else {
                NUM = "0";
            }
            System.out.println(NUM);
            int a = parseInt(NUM);
            int b = parseInt(INNUM);
            String postCap = "";
            Repository repository1 = repositoryMtMapper.searchPostCap(repository);
            if (repository1.getPostCap().equals("无限制")){
                postCap = String.valueOf(parseInt(NUM) + parseInt(INNUM) + 1);
            }
            if (parseInt(NUM) + parseInt(INNUM) > parseInt(postCap) ){
                result = "1";
                break;
            }else {
                result = "0";
            }
        }
        return result;
    }

    @Override
    public String canIn(HttpServletRequest request) {
        String result = "";
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++) {
            Material material = new Material();
            material.setId(listSHIFT.get(i).get("theMatId").toString());
            String INNUM = listSHIFT.get(i).get("theMatNum").toString();
            if (listSHIFT.get(0).containsKey("number")){
                INNUM = listSHIFT.get(i).get("number").toString();
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
    public String canOut(HttpServletRequest request) {
        String result = "";
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++) {
            Material material = new Material();
            material.setId(listSHIFT.get(i).get("theMatId").toString());
            String OUTNUM = listSHIFT.get(i).get("theMatNum").toString();
            if (listSHIFT.get(0).containsKey("number")){
                OUTNUM = listSHIFT.get(i).get("number").toString();
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
    public List<Repository> wdbhJ() {
        List<Repository> list = shiftRepositoryMapper.wdbhJ();
        return list;
    }

    @Override
    public List<Repository> wdbhL() {
        List<Repository> list = shiftRepositoryMapper.wdbhL();
        return list;
    }

    @Override
    public List<Repository> wdbhG() {
        List<Repository> list = shiftRepositoryMapper.wdbhG();
        return list;
    }

    @Override
    public List<Repository> wdbhT() {
        List<Repository> list = shiftRepositoryMapper.wdbhT();
        return list;
    }

    @Override
    public List<Repository> showmatJ(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showmatJ(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showmatL(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showmatL(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showmatG(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showmatG(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showmatT(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showmatT(wdbh);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).getMaterialId());
            String materialId = outRepositoryMapper.showmatSN(repository);
            list.get(i).setSn(materialId);
        }
        return list;
    }

    @Override
    public List<Repository> showprojL(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showprojL(wdbh);
        return list;
    }

    @Override
    public List<Repository> showprojJ(HttpServletRequest request) {
        String wdbh = request.getParameter("wdbh");
        List<Repository> list = shiftRepositoryMapper.showprojJ(wdbh);
        return list;
    }
}
