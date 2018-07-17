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
        String reptCategory = request.getParameter("reptCategory");
        String position = request.getParameter("position");
        String outId = request.getParameter("outId");
        String sn = request.getParameter("sn");
        String bn = request.getParameter("bn");
        String outTime = request.getParameter("outTime");
        if (reptId.equals("") && reptCategory.equals("") && position.equals("") && outId.equals("") && sn.equals("") && bn.equals("") && outTime.equals("")){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = outRepositoryMapper.findAll();
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setReptCategory(reptCategory);
            repository.setPosition(position);
            repository.setOutId(outId);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setOutTime(outTime);
            List<Repository> listR = repositoryMapper.searchOut(repository);
            return new PageInfo<>(listR);
        }
    }

    /*新建出库单*/
    @Override
    public void InsertRepository (HttpServletRequest request)throws ParseException{
        String OUTLIST = request.getParameter("outList");
        List<HashMap> listOUT =JSON.parseArray(OUTLIST, HashMap.class);
        for (int i = 0; i < listOUT.size(); i++) {
            String OUTREPTC = request.getParameter("outReptC");
            String OUTID = request.getParameter("outId");
            String OUTNUM = listOUT.get(i).get("theMatNum").toString();
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
            String bn = null;
            String sn = null;
            String number = materialMtMapper.manageBS(material);
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
            String POSITION = listOUT.get(i).get("position").toString();
            String REPTID = listOUT.get(i).get("reptId").toString();
            String MATERIALID = listOUT.get(i).get("theMatId").toString();
            String MATERIALNAME = listOUT.get(i).get("theMatName").toString();
            String UNIT = listOUT.get(i).get("theMatUnit").toString();
            String SPEC = listOUT.get(i).get("theMatSpec").toString();
            String REMARK = listOUT.get(i).get("theMatRemark").toString();
            Repository repository = new Repository();
            repository.setMaterialId(MATERIALID);
            repository.setPosition(POSITION);
            repository.setReptId(REPTID);
            repository.setOutId(OUTID);
            repository.setBn(bn);
            repository.setSn(sn);
            String REPTcategory = repositoryMapper.searchCategory(repository);
            outRepositoryMapper.insertNew(REPTcategory,OUTID,OUTTIME,OUTNUM,OUTINFO,REPTID,POSITION,MATERIALID,MATERIALNAME,SPEC,UNIT,sn,bn,OUTREPTC,REMARK);
        }
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
            repository.setPosition(listOUT.get(i).get("position").toString());
            String REPTcategory = repositoryMapper.searchCategory(repository);
            repository.setReptCategory(REPTcategory);
            /*更新数量*/
            String number = repositoryMapper.getNumber(repository);
            String numAfterOut = String.valueOf(parseInt(number) - parseInt(listOUT.get(i).get("theMatNum").toString()));
            repository.setNum(numAfterOut);
            int onlyIdR = repositoryMapper.lockOnlyIdR(repository);
            repository.setOnlyIdR(onlyIdR);
            repositoryMapper.updRepository(repository);
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
            material.setNum(listOUT.get(i).get("theMatNum").toString());
            material.setUnit(listOUT.get(i).get("theMatUnit").toString());
            material.setPosition(listOUT.get(i).get("position").toString());
            String date = request.getParameter("outTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            material.setDate(sDate);
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
            String OUTNUM = listOUT.get(i).get("theMatNum").toString();
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
}

