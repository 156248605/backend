package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;


public interface MaterialService {
    PageInfo<Material> showMaterial(Page page,HttpServletRequest request);

    // 查找物料
    PageInfo<Material> searchMaterial(Page page, HttpServletRequest request);

    // 是否有记录
    String record(HttpServletRequest request);

    Material changeMaterial(HttpServletRequest request);

    void saveMaterial(HttpServletRequest request);

    void deleteMaterial (HttpServletRequest request);

    void insertMaterial(Material material, HttpServletRequest request);

    String checkId(HttpServletRequest request);

    // 获取物料种类
    List<HashMap<String,Object>> getCategory(HttpServletRequest request);

}
