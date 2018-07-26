package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PositionService {
    // 显示所有库位
    PageInfo<Repository> showPosition(Page page);

    // 查寻库位
    PageInfo<Repository> searchPosition(Page page, HttpServletRequest request);

    // 插入新的库位
    String insertPosition(Repository repository, HttpServletRequest request);

    // 更新库位信息
    void changePosition(Repository repository, HttpServletRequest request);

    // 删除库位
    void deleteRepository(Repository repository, HttpServletRequest request);

    List<Repository> ReptList();

    List<Repository> ReptName(HttpServletRequest request);
}
