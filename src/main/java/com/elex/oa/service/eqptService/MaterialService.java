package com.example.oa_file.service;

import com.example.oa_file.entity.Material;
import com.example.oa_file.entity.Page;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.server.ServerRtException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface MaterialService {
    PageInfo<Material> showMaterial(Page page);

    PageInfo<Material> searchMaterial(Page page, HttpServletRequest request);

    Material changeMaterial(HttpServletRequest request);

    void saveMaterial(HttpServletRequest request);

    void deleteMaterial (HttpServletRequest request);

    String insertMaterial(Material material, HttpServletRequest request)throws ParseException;
}
