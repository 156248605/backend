package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface LinkmanService {
    // 显示联系人
    PageInfo<Linkman> showLinkman (Page page);

    // 查询联系人
    PageInfo<Linkman> searchLinkman (Page page,HttpServletRequest request);

    // 添加联系人
    String newLinkman (HttpServletRequest request);

    // 删除联系人
    void deleteLinkman(HttpServletRequest request);

    // 修改联系人
    void changeLinkman(HttpServletRequest request);
}
