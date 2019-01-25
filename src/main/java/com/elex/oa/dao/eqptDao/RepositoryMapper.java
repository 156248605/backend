package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RepositoryMapper {
    // 显示所有
    List<Repository> RepositoryList();

    // 搜索
    List<Repository> searchRepository(Repository repository);

    // 插入新的数据
    void insertRepository(Repository repository);

    // 更改仓库
    void changeRepository(Repository repository);

    //删除仓库
    void deleteRepository(Repository repository);

    // 避免出现同一个库位
    Repository Position(Repository repository);

    // 更新仓库
    void updRepository(Repository repository);

    List<Repository> searchIn(Repository repository);

    List<Repository> searchOut(Repository repository);

    // 获取仓库的类型
    String searchCategory(Repository repository);

    // 确定不会输入错误仓库类型
    List theCategory(Repository repository);

    // 补全信息
    List<Material> otherInfo(Material material);

    // 将盘点中需要的数据放入仓库表中
    List<Material> invInfo(Material material);

    // 导入Excel
    int importRepository(Repository repository);

    List<Repository> reptlist();

    List<Repository> postlist();

    // 更新数量
    String getNumber(Repository repository);
    String numInPost(Material material);

    // 更新其他数据
    Material getOtherInfo(Material material);

    // 确定仓库
    int lockOnlyIdR(Repository repository);

    // 确定库位
    int lockOnlyIdP(Repository repository);

    // 确定某个物料所在仓库
    List<Repository> matInRept(Material material);
    List<Repository> matInPost(Material material);
    List<Repository> matInPostReplace();
    List<Repository> matOutRept(Material material);
    List<Repository> matOutPost(Material material);

    List<Material> matlist();

    void updPosition(Repository repository);

    void updPostState(Repository repository);

    List<Repository> getPost(Repository repository);

    void changeDetail(Repository repository);

    // 确定是否为空仓库
    List<Repository> emptyReptD(Material material);
    List<Repository> emptyReptP();

    String recordin(Repository repository);

    String recordout(Repository repository);

    String recordshift(Repository repository);

    String checkBS(Material material);

    List<HashMap<String, Object>> getCate();

    List<HashMap<String, Object>> checkCate(String cateName);

    void insertCate(String cateName);

    void deleteCate(String onlyId);

    String fixPostMatInfo(Material material);

    List<Repository> fixRept(Material material);

    // 根据仓库Id查询name
    String searchReptName(Material material);

    // 查询仓库是否有物料
    List reptCanChangeState(Repository repository);
}
