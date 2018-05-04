package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.InRepositoryMapper;
import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.OutRepositoryMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.OutRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OutRepositoryImpl implements OutRepositoryService {

    @Resource
    private OutRepositoryMapper outRepositoryMapper;

    @Resource
    private InRepositoryMapper inRepositoryMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public PageInfo<Repository> ShowRepository(Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list =  outRepositoryMapper.findAll();
        return new PageInfo<>(list);
    }

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
            List<Repository> listR = outRepositoryMapper.findAll();
            return new PageInfo<>(listR);
        } else {
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

    @Override
    public String InsertRepository (HttpServletRequest request)throws ParseException{
        String OUTID = request.getParameter("outId");
        String OUTNUM = request.getParameter("outNum");
        String REPTCATEGORY = request.getParameter("reptCategory" );
        String date = request.getParameter("outTime");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        String OUTTIME = sDate;
        String SN = request.getParameter("sn");
        String BN = request.getParameter("bn");
        String POSITION = request.getParameter("position");
        String REPTID = request.getParameter("reptId");
        String MATERIALID = request.getParameter("materialId");
        Material material = new Material();
        material.setSn(SN);
        material.setBn(BN);
        material.setId(MATERIALID);
        String ID;
        if (inRepositoryMapper.searchId(material).contains(MATERIALID)){
            ID = MATERIALID;
        }else {
            ID = MATERIALID+"...";
        }
        String MinLimit;
        if (materialMapper.Id(material) != null) {
            MinLimit = materialMapper.MinLimit(material).getMinlimit().toString();
        } else {
            MinLimit = request.getParameter("materialMax");
        }
        Repository repository = new Repository();
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setPosition(request.getParameter("position"));
        repository.setOutId(request.getParameter("outId"));
        String outid = inRepositoryMapper.OUTID(repository);
        String REPTcategory = repositoryMapper.searchCategory(repository);
        List<Repository> list = repositoryMapper.getNum(repository);
        int num = 0;
        for (Repository r : list) {
            num += Integer.parseInt(r.getNum());
        }
        if (MATERIALID.equals(ID) && num - Integer.parseInt(request.getParameter("outNum")) >= Integer.parseInt(MinLimit) && REPTCATEGORY.equals(REPTcategory) && outid == null ){
            outRepositoryMapper.insertNew(REPTCATEGORY,OUTID,OUTTIME,OUTNUM,REPTID,POSITION,SN,BN);
            return "1";
        }else if ( !MATERIALID.equals(ID) ){
            return "2";
        }else if ( num - Integer.parseInt(request.getParameter("outNum")) < Integer.parseInt(MinLimit)) {
            return "3";
        }else if (!REPTCATEGORY.equals(REPTcategory)){
            return "4";
        } else if (outid != null){
            return "5";
        }else {
            return "6";
        }
    }

    @Override
    public String OutRepository(HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        String Id = materialMapper.Id(material).getId().toString();
        String MinLimit;
        if (materialMapper.Id(material) != null) {
            MinLimit = materialMapper.MinLimit(material).getMinlimit().toString();
        } else {
            MinLimit = request.getParameter("materialMax");
        }
        Repository repository = new Repository();
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("outNum"));
        repository.setSn(request.getParameter("sn"));
        repository.setBn(request.getParameter("bn"));
        String Num = repositoryMapper.Num(repository).getNum().toString();
        if ( request.getParameter("materialId").equals(Id) && Integer.parseInt(Num) - Integer.parseInt(request.getParameter("outNum")) >= Integer.parseInt(MinLimit)){
            repositoryMapper.outRepository(repository);
            return "1";
        } else {
            return "0";
        }
    }
}

