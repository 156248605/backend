package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RepositoryService {

    // 显示所有
    PageInfo<Repository> showRepository(Page page);

    // 查询
    PageInfo<Repository> searchRepository(Page page,HttpServletRequest request);

    // 插入新的仓库
    String insertRepository(Repository repository,HttpServletRequest request);

    // 更新仓库
    void changeRepository(Repository repository,HttpServletRequest request);

    // 删除仓库
    void deleteRepository(Repository repository,HttpServletRequest request);

    // 获取仓库ID
    List<Repository> ReptList();
    /*List<Repository> matInRept(HttpServletRequest request);
    List<Repository> matOutRept(HttpServletRequest request);*/

    // 获取库位
    List<Repository> getPost(HttpServletRequest request);

    // 获取库位
    List<Repository> PostList();

    List<Repository> matOutRept(HttpServletRequest request);
    List<Repository> matOutPost(HttpServletRequest request);
    List<Repository> matInRept(HttpServletRequest request);
    List<Repository> matInPost(HttpServletRequest request);

    // 是否有记录
    String record(HttpServletRequest request);

    // 是否批次号序列号管理
    String manageResult(HttpServletRequest request);
}
