package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.*;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.ShiftRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String shiftTime = request.getParameter("shiftTime");
        String outReptId = request.getParameter("outReptId");
        String inReptId = request.getParameter("inReptId");
        String materialId = request.getParameter("materialId");
        String sn = request.getParameter("sn");
        String bn = request.getParameter("bn");
        if (shiftId.equals("") && shiftTime.equals("") && outReptId.equals("") && inReptId.equals("") && materialId.equals("") && sn.equals("") && bn.equals("") ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = shiftRepositoryMapper.showRepository();
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setShiftId(shiftId);
            repository.setShiftTime(shiftTime);
            repository.setOutRept(outReptId);
            repository.setInRept(inReptId);
            repository.setMaterialId(materialId);
            repository.setSn(sn);
            repository.setBn(bn);
            List<Repository> listR = shiftRepositoryMapper.searchShift();
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
            String shiftNum = listSHIFT.get(i).get("theMatNum").toString();
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
            shiftRepositoryMapper.insertNew(shiftId,shiftTime,shiftReptC,shiftNum,shiftInfo,outRept,outPost,inRept,inPost,materialId,materialName,spec,unit,sn,bn,remark);
        }
    }

    /*同步仓库*/
    @Override
    public void changeRepository(HttpServletRequest request){
        String SHIFTLIST = request.getParameter("shiftList");
        List<HashMap> listSHIFT =JSON.parseArray(SHIFTLIST, HashMap.class);
        for (int i = 0; i < listSHIFT.size(); i++){
            String shiftNum = listSHIFT.get(i).get("theMatNum").toString();
            String materialId = listSHIFT.get(i).get("theMatId").toString();
            Material material = new Material();
            material.setId(materialId);
            Material material2 = shiftRepositoryMapper.lockMat(material);
            Repository repositoryOne = new Repository();
            repositoryOne.setPosition(request.getParameter("outPost"));
            repositoryOne.setReptId(request.getParameter("outRept"));
            String theNumber = repositoryMapper.getNumber(repositoryOne);
            if (material2.getNum().equals(shiftNum) || theNumber.equals(shiftNum)) {
                Repository repository = new Repository();
                repository.setPosition(request.getParameter("outPost"));
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
            }
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
            String reptId = request.getParameter("inRept");
            String postId = request.getParameter("inPost");
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setPostId(postId);
            repository.setPosition(postId);
            Material material = new Material();
            material.setId(listSHIFT.get(i).get("theMatId").toString());
            String INNUM = listSHIFT.get(i).get("theMatNum").toString();
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
