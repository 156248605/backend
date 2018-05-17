package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.InRepositoryMapper;
import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InRepositoryService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@Service
public class InRepositoryImpl implements InRepositoryService {

    @Resource
    private InRepositoryMapper inRepositoryMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    /*查询所有单号*/
    @Override
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> list =  inRepositoryMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        String reptId = request.getParameter("reptId");
        String reptCategory = request.getParameter("reptCategory");
        String position = request.getParameter("position");
        String inId = request.getParameter("inId");
        String sn = request.getParameter("sn");
        String bn = request.getParameter("bn");
        String inTime = request.getParameter("inTime");
        // 查询判断
        if (reptId.equals("") && reptCategory.equals("") && position.equals("") && inId.equals("") && sn.equals("") && bn.equals("") && inTime.equals("")){
            List<Repository> listR = inRepositoryMapper.findAll();
            return new PageInfo<>(listR);
        } else {
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setReptCategory(reptCategory);
            repository.setPosition(position);
            repository.setInId(inId);
            repository.setBn(bn);
            repository.setSn(sn);
            repository.setInTime(inTime);
            List<Repository> listR = repositoryMapper.searchIn(repository);
            return new PageInfo<>(listR);
        }
    }

    /*新建入库单号*/
    @Override
    public String NewRepository(HttpServletRequest request) throws ParseException {
        String INID = request.getParameter("inId");
        String INNUM = request.getParameter("inNum");
        String REPTCATEGORY = request.getParameter("reptCategory");
        // 格林尼治时间转格式
        String date = request.getParameter("inTime");
        date = date.replace("Z", " UTC");// 注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        String INTIME = sDate;
        String SN = request.getParameter("sn");
        String BN = request.getParameter("bn");
        String POSITION = request.getParameter("position");
        String REPTID = request.getParameter("reptId");
        String MATERIALID = request.getParameter("materialId");
        Material material = new Material();
        material.setSn(SN);
        material.setBn(BN);
        material.setId(MATERIALID);
        // 判断序列号规范
        String Sn;
        if ( inRepositoryMapper.searchSn(material).get(0).toString() != SN){
            Sn = SN+"...";
        }else {
            Sn = SN;
        }
        // 确定物料上限
        String MaxLimit;
        if (materialMapper.Id(material) != null) {
            MaxLimit = materialMapper.MaxLimit(material).getMaxlimit().toString();
        } else {
            MaxLimit = request.getParameter("materialMax");
        }

        Repository repository = new Repository();
        repository.setInId(request.getParameter("inId"));
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setPosition(request.getParameter("position"));
        repository.setBn(request.getParameter("bn"));
        repository.setSn(request.getParameter("sn"));
        String inid = inRepositoryMapper.INID(repository);
        String REPTcategory = repositoryMapper.searchCategory(repository);
        // 库存总量
        List<Repository> list = repositoryMapper.getNum(repository);
        int num = 0;
        for (Repository r : list) {
            num += Integer.parseInt(r.getNum());
        }
        // 确定库位正确
        String Position;
        if (repositoryMapper.thePosition(repository).contains(request.getParameter("position"))) {
            Position = "1";
        }else {
            Position = "0";
        }
        // 判断条件允许才可以插入
        if (num + Integer.parseInt(request.getParameter("inNum")) <= Integer.parseInt(MaxLimit) && REPTCATEGORY.equals(REPTcategory) && inid == null && Sn.equals(Sn) && Position.equals("1")) {
            inRepositoryMapper.insertNew(REPTCATEGORY, INID, INTIME, INNUM, REPTID, POSITION, SN, BN);
            return "1";
        } else if (!SN.equals(Sn)){
            return "2";
        } else if (num + Integer.parseInt(request.getParameter("inNum")) > Integer.parseInt(MaxLimit)){
            return "3";
        } else if (!REPTCATEGORY.equals(REPTcategory)){
            return "4";
        } else if (inid != null){
            return "5";
        } else if (Position.equals("0")){
            return "6";
        } else {
            return "7";
        }
    }

    @Override
    public void InsertMaterial(HttpServletRequest request) throws ParseException{
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setName(request.getParameter("materialName"));
        String date = request.getParameter("materialDate");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        material.setDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);
        material.setCategory(request.getParameter("materialCategory"));
        material.setBrand(request.getParameter("materialBrand"));
        material.setMaterial(request.getParameter("materialMat"));
        material.setSpec(request.getParameter("materialSpec"));
        material.setMaxlimit(request.getParameter("materialMax"));
        material.setMinlimit(request.getParameter("materialMin"));
        material.setPosition(request.getParameter("position"));
        material.setPrice(request.getParameter("materialPrice"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
        material.setRemark(request.getParameter("materialRemark"));
        material.setPartner(request.getParameter("materialPartner"));
        if (materialMapper.Id(material) != null){
            materialMapper.updMaterial(material);
        }else {
            materialMapper.newMaterial(material);
        }
    }

    @Override
    public String InsertRepository(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("inNum"));
        repository.setSn(request.getParameter("sn"));
        repository.setBn(request.getParameter("bn"));
        List<Repository> list = repositoryMapper.getNum(repository);
        int num = 0;
        for(Repository r: list) {
            num += Integer.parseInt(r.getNum());
        }
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
        String MaxLimit;
        if (materialMapper.Id(material) != null) {
            MaxLimit = materialMapper.MaxLimit(material).getMaxlimit().toString();
        } else {
            MaxLimit = request.getParameter("materialMax");
        }
        if (num + Integer.parseInt(request.getParameter("inNum")) <= Integer.parseInt(MaxLimit)) {
            if (request.getParameter("position").equals(repositoryMapper.Position(repository).getPosition())  ) {
                repositoryMapper.updRepository(repository);
            } else {
                repositoryMapper.newRepository(repository);
            }
            return "1";
        }  else {
            return "2";
        }
    }


}
