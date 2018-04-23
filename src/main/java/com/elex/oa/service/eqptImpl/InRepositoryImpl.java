package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.InRepositoryMapper;
import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InRepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public void NewRepository(HttpServletRequest request) throws ParseException{
        String INID = request.getParameter("inId");
        String INNUM = request.getParameter("inNum");
        String REPTCATEGORY = request.getParameter("reptCategory");
        String date = request.getParameter("inTime");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
        String ID = inRepositoryMapper.searchId(material);
        if (MATERIALID.equals(ID)) {
            inRepositoryMapper.insertNew(REPTCATEGORY, INID, INTIME, INNUM, REPTID, POSITION, SN, BN);
        }
    }

    @Override
    public void InsertMaterial(HttpServletRequest request){
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setName(request.getParameter("materialName"));
        material.setDate(request.getParameter("materialDate"));
        material.setCategory(request.getParameter("materialCategory"));
        material.setBrand(request.getParameter("materialBrand"));
        material.setMaterial(request.getParameter("materialMat"));
        material.setSpec(request.getParameter("materialSpec"));
        material.setMaxlimit(request.getParameter("materialMax"));
        material.setMinlimit(request.getParameter("materialMin"));
        material.setPosition(request.getParameter("position"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
        material.setRemark(request.getParameter("materialRemark"));
        material.setPartner(request.getParameter("materialPartner"));
        String Id = materialMapper.Id(material).getId().toString();
        if (request.getParameter("materialId").equals(Id)){
            materialMapper.updMaterial(material);
        }else {
            materialMapper.newMaterial(material);
        }
    }

    @Override
    public void InsertRepository(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("inNum"));
        repository.setSn(request.getParameter("sn"));
        repository.setBn(request.getParameter("bn"));
        String Position = repositoryMapper.Position(repository).getPosition().toString();
        String Num = repositoryMapper.Num(repository).getNum().toString();
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        String MaxLimit = materialMapper.MaxLimit(material).getMaxlimit().toString();
        if (Integer.parseInt(Num) + Integer.parseInt(request.getParameter("inNum")) <= Integer.parseInt(MaxLimit)) {
            if (request.getParameter("position").equals(Position) ) {
                repositoryMapper.updRepository(repository);
            } else {
                repositoryMapper.newRepository(repository);
            }
        }
    }


}
