package com.elex.oa.service.eqptImpl;

import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class InRepositoryImpl implements InRepositoryService {

    @Resource
    private InRepositoryMapper inRepositoryMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private RepositoryMtMapper repositoryMtMapper;

    @Resource
    private MaterialMtMapper materialMtMapper;

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
        String position = request.getParameter("position");
        String positionC = request.getParameter("positionC");
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
        // 查询判断
        if (reptId.equals("") && reptCategory.equals("") && position.equals("") && inId.equals("") && sn.equals("") && bn.equals("") && inTime.equals("") && inNum.equals("") && materialId.equals("")){
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
            repository.setPosition(position);
            repository.setPositionC(positionC);
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
    public void NewRepository(HttpServletRequest request) throws ParseException {
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++){
            String INREPTC = request.getParameter("inReptC");
            String INID = request.getParameter("inId");
            String INNUM = listIN.get(i).get("theMatNum").toString();
            // 格林尼治时间转格式
            String date = request.getParameter("inTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            String INTIME = sDate;
            String ININFO = request.getParameter("inInfo");
            String POSITION = listIN.get(i).get("position").toString();
            String REPTID = listIN.get(i).get("reptId").toString();
            String MATERIALID = listIN.get(i).get("theMatId").toString();
            String MATERIALNAME = listIN.get(i).get("theMatName").toString();
            String UNIT = listIN.get(i).get("theMatUnit").toString();
            String SPEC = listIN.get(i).get("theMatSpec").toString();
            String CHECK = listIN.get(i).get("theMatCheck").toString();
            String REMARK = listIN.get(i).get("theMatRemark").toString();
            Material material = new Material();
            material.setId(MATERIALID);
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
            repository.setPosition(POSITION);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setCheck(CHECK);
            repository.setRemark(REMARK);
            repository.setInInfo(ININFO);
            String REPTcategory = repositoryMapper.searchCategory(repository);
            inRepositoryMapper.insertNew(REPTcategory, INID, INTIME, INNUM, ININFO, REPTID, POSITION, MATERIALID, MATERIALNAME, SPEC, UNIT, sn, bn,INREPTC, CHECK, REMARK);
        }
    }

    /*更新物料*/
    @Override
    public void InsertMaterial(HttpServletRequest request) throws ParseException {
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            Material material = new Material();
            material.setId(listIN.get(i).get("theMatId").toString());
            material.setNum(listIN.get(i).get("theMatNum").toString());
            material.setUnit(listIN.get(i).get("theMatUnit").toString());
            material.setPosition(listIN.get(i).get("position").toString());
            String date = request.getParameter("inTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            material.setDate(sDate);
            materialMapper.updMat(material);
        }
    }

    /*更新仓库*/
    @Override
    public void InsertRepository(HttpServletRequest request) {
        String INLIST = request.getParameter("inList");
        List<HashMap> listIN =JSON.parseArray(INLIST, HashMap.class);
        for (int i = 0; i < listIN.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(listIN.get(i).get("theMatId").toString());
            repository.setReptId(listIN.get(i).get("reptId").toString());
            repository.setPosition(listIN.get(i).get("position").toString());
            String REPTcategory = repositoryMapper.searchCategory(repository);
            repository.setReptCategory(REPTcategory);
            Material material = new Material();
            material.setId(listIN.get(i).get("theMatId").toString());
            Material material2 = materialMapper.lockPrice(material);
            Material material3 = materialMapper.MaterialId(material);
            repository.setPrice(material2.getPrice());
            repository.setMaterialName(material3.getName());
            repository.setSpec(material3.getSpec());
            repository.setCategory(material3.getCategory());
            /*更新数量*/
            String emptyRept = repositoryMapper.getNumber(repository);
            if (emptyRept.equals("0")) {
                repository.setNum(listIN.get(i).get("theMatNum").toString());
            } else {
                String number = repositoryMapper.getNumber(repository);
                String numAfterIn = String.valueOf(parseInt(number) + parseInt(listIN.get(i).get("theMatNum").toString()));
                repository.setNum(numAfterIn);
            }
            int onlyIdR = repositoryMapper.lockOnlyIdR(repository);
            repository.setOnlyIdR(onlyIdR);
            repositoryMapper.updRepository(repository);
        }
    }

    /*删除入库*/
    @Override
    public void DeleteInRept(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setOnlyIdIn( parseInt(request.getParameter("inId")) );
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
            String INNUM = listIN.get(i).get("theMatNum").toString();
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
            String postId = listIN.get(i).get("position").toString();
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setPostId(postId);
            repository.setPosition(postId);
            Material material = new Material();
            material.setId(listIN.get(i).get("theMatId").toString());
            /*material.setBn(listIN.get(i).get("theMatBnSn").toString());
            material.setSn(listIN.get(i).get("theMatBnSn").toString());
            Material material1 = materialMapper.lockBn(material);
            Material material2 = materialMapper.lockSn(material);
            String bn = null;
            String sn = null;
            if (material1 != null){
                bn = listIN.get(i).get("theMatBnSn").toString();
                sn = "无";
            }else if (material2 != null){
                sn = listIN.get(i).get("theMatBnSn").toString();
                bn = "无";
            }else {
                sn = "无";
                bn = "无";
            }
            Material material3 = new Material();
            material3.setId(listIN.get(i).get("theMatId").toString());
            material3.setSn(sn);
            material3.setBn(bn);*/
            String INNUM = listIN.get(i).get("theMatNum").toString();
            String NUM = repositoryMapper.getNumber(repository);
            String postCap = "";
            if (repositoryMtMapper.searchPostCap(repository).equals("无限制")){
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


}
