package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface InventoryService {
    // 展示所有盘点信息
    PageInfo<Repository> showAllInfo(Page page);

    // 查询盘点信息
    PageInfo<Repository> searchInfo(Page page, HttpServletRequest request);

    // 插入盘点信息
    void insertInfo(HttpServletRequest request) throws ParseException;

    // 删除盘点信息
    void deleteInfo(HttpServletRequest request);

    // 更改盘点信息
    void changeInfo(HttpServletRequest request);

    // 确定盘点单号
    List showInvId(HttpServletRequest request);

    // 查询盘点的仓库内物料情况(选物料)
    List<Material> chooseMat(HttpServletRequest request);

    // 查询盘点的仓库内物料情况(选物料)
    List<Material> matinrept(HttpServletRequest request);

    // 获取仓库ID
    List<Repository> ReptList();

}
