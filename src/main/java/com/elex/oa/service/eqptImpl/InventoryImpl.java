package com.elex.oa.service.eqptImpl;

import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.InventoryMapper;
import com.elex.oa.entity.Page;

import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InventoryService;
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

@Service
public class InventoryImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

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
            repository.setRemark(listINV.get(i).get("theMatRemark").toString());
            inventoryMapper.insert(repository);
        }
    }

    // 删除数据
    @Override
    public void deleteInfo(HttpServletRequest request) {
        String onlyIdInv = request.getParameter("onlyIdInv");
        Repository repository = new Repository();
        repository.setOnlyIdInv( Integer.parseInt(onlyIdInv) );
        inventoryMapper.delete(repository);
    }

    @Override
    public void changeInfo(HttpServletRequest request) {
    }

    @Override
    public List showInvId(HttpServletRequest request) {
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setInvId(dateToId);
        List showInvId = inventoryMapper.showINVID(repository);
        return showInvId;
    }

    @Override
    public List<Repository> matinrept(HttpServletRequest request) {
        String reptId = request.getParameter("reptId");
        Repository repository = new Repository();
        repository.setReptId(reptId);
        List<Repository> listMIR = inventoryMapper.matinrept(repository);
        return listMIR;
    }

    @Override
    public List<Repository> ReptList() {
        List<Repository> reptlist = inventoryMapper.reptlist();
        return reptlist;
    }
}
