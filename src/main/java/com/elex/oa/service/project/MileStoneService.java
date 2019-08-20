package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.MileStone;
import com.elex.oa.entity.project.MileStonePlan;
import com.elex.oa.entity.project.OperationQuery;
import com.github.pagehelper.PageInfo;
import com.sun.tools.doclets.formats.html.markup.HtmlAttr;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MileStoneService {
    //列表查询里程碑计划
    PageInfo queryList(AListQuery aListQuery, Page page, HttpServletRequest request);
    //查询可新建里程碑计划的项目信息
    PageInfo queryProjectList(OperationQuery operationQuery, int currentPage);
    //添加里程碑计划
    String addPlans(String dataS, MileStone mileStone);
    //查询里程碑计划详情
    List<MileStonePlan> queryPlansCode(String projectCode);
    //修改里程碑计划详情
    String modifyPlansCode(String dataS, MileStone mileStone);
}
