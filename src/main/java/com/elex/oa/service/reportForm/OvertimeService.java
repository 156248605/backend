package com.elex.oa.service.reportForm;

import com.elex.oa.entity.Page;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface OvertimeService {

    // 显示加班报表信息
    PageInfo overtimeForm(HttpServletRequest request, Page page) throws ParseException;
}
