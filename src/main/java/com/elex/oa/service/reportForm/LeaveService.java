package com.elex.oa.service.reportForm;

import com.github.pagehelper.PageInfo;
import com.elex.oa.entity.Page;

import javax.servlet.http.HttpServletRequest;

public interface LeaveService {
    // 显示请假报表信息
    PageInfo leaveForm(HttpServletRequest request, Page page);
}
