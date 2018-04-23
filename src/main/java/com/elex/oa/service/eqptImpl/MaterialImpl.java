package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Page;
import com.elex.oa.service.eqptService.MaterialService;
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
public class MaterialImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;


    @Override
    public PageInfo<Material> showMaterial(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Material> listM = materialMapper.MaterialList();
        return new PageInfo<>(listM);
    }

    @Override
    public PageInfo<Material> searchMaterial(Page page, HttpServletRequest request){
        String ID = request.getParameter("id");
        String SN = request.getParameter("sn");
        String BN = request.getParameter("bn");
        String PARTNER = request.getParameter("partner");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        if (ID.equals("") && SN.equals("") && BN.equals("") && PARTNER.equals("")){
            List<Material> listM = materialMapper.MaterialList();
            return new PageInfo<>(listM);
        }else {
            Material material = new Material();
            material.setId(ID);
            material.setSn(SN);
            material.setBn(BN);
            material.setPartner(PARTNER);
            List<Material> listM = materialMapper.SearchMaterial(material);
            return new PageInfo<>(listM);
        }
    }

    @Override
    public Material changeMaterial (HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("id"));
        Material material1 = materialMapper.MaterialId(material);
        return material1;
    }

    @Override
    public void saveMaterial (HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setBn(request.getParameter("bn"));
        material.setSn(request.getParameter("sn"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setPosition(request.getParameter("position"));
        material.setBrand(request.getParameter("brand"));
        material.setDate(request.getParameter("date"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        materialMapper.saveMaterial(material);
    }

    @Override
    public void deleteMaterial (HttpServletRequest request){
        Material material = new Material();
        material.setId(request.getParameter("id"));
        materialMapper.deleteMaterial(material);
    }

    @Override
    public void insertMaterial(Material material, HttpServletRequest request)throws ParseException {
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setBn(request.getParameter("bn"));
        material.setSn(request.getParameter("sn"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setPosition(request.getParameter("position"));
        material.setBrand(request.getParameter("brand"));
        String date = request.getParameter("date");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        materialMapper.newMaterial(material);
    }
}
