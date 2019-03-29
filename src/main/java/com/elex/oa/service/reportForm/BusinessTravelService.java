package com.elex.oa.service.reportForm;

import com.elex.oa.entity.Page;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface BusinessTravelService {

    // 显示出差报表信息
    PageInfo businessTravelForm(HttpServletRequest request, Page page);
}
