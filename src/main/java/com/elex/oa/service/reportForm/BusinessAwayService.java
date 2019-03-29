package com.elex.oa.service.reportForm;

import com.elex.oa.entity.Page;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface BusinessAwayService {

    // 显示公出报表信息
    PageInfo businessAwayForm(HttpServletRequest request, Page page);
}
