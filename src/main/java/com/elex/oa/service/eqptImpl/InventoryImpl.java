package com.elex.oa.service.eqptImpl;

import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.InventoryMapper;
import com.elex.oa.entity.Page;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InventoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/*import com.sun.xml.internal.bind.v2.model.core.ID;*/
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class InventoryImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public List<HashMap<String, Object>> testInv(){
        List<HashMap<String, Object>> list = inventoryMapper.test();
        return list;
    }

    // 显示所有信息
    @Override
    public PageInfo<Repository> showAllInfo(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> listInv = inventoryMapper.find();
        return new PageInfo<>(listInv);
    }

    // 查询部分信息
    @Override
    public PageInfo<Repository> searchInfo(Page page, HttpServletRequest request) {
        String invId = request.getParameter("invId");
        String invIdC = request.getParameter("invIdC");
        String invTime = request.getParameter("invTime");
        String invTimeC = request.getParameter("invTimeC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String materialName = request.getParameter("materialName");
        String materialNameC = request.getParameter("materialNameC");
        String position = request.getParameter("position");
        String positionC = request.getParameter("positionC");
        String reptId = request.getParameter("rept");
        String reptIdC = request.getParameter("reptC");
        String price = request.getParameter("price");
        String priceC = request.getParameter("priceC");
        String numInv = request.getParameter("numInv");
        String numInvC = request.getParameter("numInvC");
        String pal = request.getParameter("pal");
        String palC = request.getParameter("palC");
        String remark = request.getParameter("remark");
        String num = request.getParameter("num");
        String numC = request.getParameter("numC");
        if ( !invTimeC.equals("") || !invIdC.equals("") || !positionC.equals("") || !materialIdC.equals("") || !numC.equals("") || materialNameC.equals("") || priceC.equals("") || numInvC.equals("") || palC.equals("") || remark.equals("")){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setInvId(invId);
            repository.setInvIdC(invIdC);
            repository.setInvTime(invTime);
            repository.setInvTimeC(invTimeC);
            repository.setNum(num);
            repository.setNumC(numC);
            repository.setNumInv(numInv);
            repository.setNumInvC(numInvC);
            repository.setReptId(reptId);
            repository.setReptIdC(reptIdC);
            repository.setMaterialName(materialName);
            repository.setMatNameC(materialNameC);
            repository.setPosition(position);
            repository.setPositionC(positionC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);
            repository.setPrice(price);
            repository.setPriceC(priceC);
            repository.setPal(pal);
            repository.setPalC(palC);
            List<Repository> listR = inventoryMapper.search(repository);
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = inventoryMapper.find();
            return new PageInfo<>(listR);
        }

    }

    // 定时流程
    @Override
    public List<HashMap<String, Object>> insertInv(){
        String INST_STATUS_ = "SUCCESS_END";
        List<HashMap<String, Object>> list = inventoryMapper.invInfo(INST_STATUS_);
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).get("F_TBRQ").toString().substring(0,list.get(i).get("F_TBRQ").toString().length()-2));
            Calendar cal= Calendar.getInstance();
            String y,m;
            y = String.valueOf(cal.get(Calendar.YEAR));
            m = String.valueOf((cal.get(Calendar.MONTH)+1 > 10)?cal.get(Calendar.MONTH)+1:"0"+String.valueOf(cal.get(Calendar.MONTH)+1) );
            Repository repository = new Repository();
            repository.setInvId(y+m);
            List showInvId = inventoryMapper.showINVID(repository);
            String result = "";// 1.确定盘点单号
            if (showInvId.size() > 0){
                result = String.valueOf(parseInt(showInvId.get(showInvId.size()-1).toString())+1);
            }else {
                result = y+m+"0001";
            }
            String invTime = list.get(i).get("F_TBRQ").toString();// 2.获取盘点时间(需要修改)
            String materialId = list.get(i).get("F_TBRQ").toString();// 3.获取物料Id(需要修改)
            String reptId = list.get(i).get("F_TBRQ").toString();// 4.获取盘点仓库(需要修改)
            String postId = list.get(i).get("F_TBRQ").toString();// 5.获取盘点库位(需要修改)
            String num = list.get(i).get("F_TBRQ").toString();// 6.获取账上数量(需要修改)
            String numInv = list.get(i).get("F_TBRQ").toString();// 7.获取盘点(实际)数量(需要修改)
            repository.setInvTime(invTime);
            repository.setMaterialId(materialId);
            repository.setReptId(reptId);
            repository.setPostId(postId);
            repository.setNum(String.valueOf(parseInt(numInv) - parseInt(num)));
            repository.setNumInv(numInv);
            inventoryMapper.changeNum(repository);
            inventoryMapper.changeNumMat(repository);
            /*Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setRemark("");
            repository.setReptState("1");
            inventoryMapper.insert(repository);*/
        }
        return null;
    }


    // 插入数据
    @Override
    public void insertInfo(HttpServletRequest request) throws ParseException {
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setRemark("");
            repository.setReptState("1");
            inventoryMapper.insert(repository);
        }
    }

    // 暂存数据
    @Override
    public void saveInfo(HttpServletRequest request) throws ParseException{
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setRemark("");
            inventoryMapper.insertDraft(repository);
        }
    }

    // 删除数据
    @Override
    public void deleteInfo(HttpServletRequest request){
        String onlyIdInv = request.getParameter("onlyIdInv");
        String invId = request.getParameter("invId");
        Repository repository = new Repository();
        repository.setOnlyIdInv( parseInt(onlyIdInv) );
        repository.setInvId(invId);
        inventoryMapper.delete(repository);
        inventoryMapper.deleteD(repository);
    }

    @Override
    public void changeInfo(HttpServletRequest request) throws ParseException{
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setRemark(listINV.get(i).get("theMatRemark").toString());
            inventoryMapper.changeInvD(repository);
        }
    }

    @Override
    public List showInvId(HttpServletRequest request){
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setInvId(dateToId);
        List showInvId = inventoryMapper.showINVID(repository);
        List showInvIdD = inventoryMapper.showINVIDd(repository);
        String result1 = "0";
        String result2 = "0";
        if (showInvId.size() > 0){
            result1 = showInvId.get(showInvId.size()-1).toString();
        }
        if (showInvIdD.size() > 0) {
            result2 = showInvIdD.get(showInvIdD.size()-1).toString();
        }
        if (parseInt(result2) > parseInt(result1)) {
            return showInvIdD;
        } else {
            return showInvId;
        }
    }

    @Override
    public List<Material> chooseMat(HttpServletRequest request) {
        String reptId = request.getParameter("reptId");
        Material material = new Material();
        material.setReptId(reptId);
        List<Material> listMIR = inventoryMapper.chooseMat(material);
        return listMIR;
    }

    @Override
    public List<Material> matinrept(HttpServletRequest request) {
        String reptId = request.getParameter("reptId");
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        String matId = "";
        for (int i = 0; i < ID.size(); i++){
            matId += ID.get(i).get("id").toString() + ",";
        }
        Material material = new Material();
        material.setReptId(reptId);
        material.setId(matId.substring(0,matId.length() - 1));
        List<Material> listMIR = inventoryMapper.matinrept(material);
        return listMIR;
    }

    @Override
    public List<Repository> ReptList() {
        List<Repository> reptlist = inventoryMapper.reptlist();
        return reptlist;
    }

    @Override
    public String checkDraft(HttpServletRequest request){
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        Repository repository = new Repository();
        repository.setInvId(ID.get(0).get("invId").toString());
        List<Repository> result = inventoryMapper.checkDraft(repository);
        if (result.size() > 0){
            return "1";
        }else {
            return "0";
        }
    }

    @Override
    public void deleteDraft(HttpServletRequest request){
        String id = request.getParameter("id");
        /*List<HashMap> ID =JSON.parseArray(id, HashMap.class);*/
        Repository repository = new Repository();
        repository.setInvId(id);
        inventoryMapper.deleteDraft(repository);
    }

    @Override
    public List<Repository> openDraft(HttpServletRequest request) {
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        Repository repository = new Repository();
        repository.setInvId(ID.get(0).get("invId").toString());
        List<Repository> list = inventoryMapper.openDraft(repository);
        return list;
    }


}
