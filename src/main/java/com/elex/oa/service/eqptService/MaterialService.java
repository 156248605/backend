package com.elex.oa.service.eqptService;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Page;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface MaterialService {
    PageInfo<Material> showMaterial(Page page);

    PageInfo<Material> searchMaterial(Page page, HttpServletRequest request);

    Material changeMaterial(HttpServletRequest request);

    void saveMaterial(HttpServletRequest request);

    void deleteMaterial (HttpServletRequest request);

    void insertMaterial(Material material, HttpServletRequest request)throws ParseException;
}
