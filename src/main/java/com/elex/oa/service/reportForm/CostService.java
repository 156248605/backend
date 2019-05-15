package com.elex.oa.service.reportForm;

import com.elex.oa.entity.Page;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CostService {
    // 获取申请单报表(费用)
    List serveForm(HttpServletRequest request);
}
