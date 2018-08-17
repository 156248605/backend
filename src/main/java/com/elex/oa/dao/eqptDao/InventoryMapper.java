package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface InventoryMapper {

    // 遍历所有盘点信息
    List<Repository> find();

    // 查询部分盘点信息
    List<Repository> search(Repository repository);

    // 删除盘点信息
    void delete(Repository repository);

    // 插入盘点信息
    void insert(Repository repository);

    // 确定盘点单号
    List showINVID(Repository repository);

    // 仓库里需要盘点的物料
    List<Material> matinrept(Material material);

    // 打开草稿内容
    List<Repository> openDraft(Repository repository);

    List<Material> chooseMat(Material material);

    List<Repository> reptlist();

    // 暂存草稿
    void insertDraft(Repository repository);

    // 判断是否为草稿
    List<Repository> checkDraft(Repository repository);

    // 删除草稿
    void deleteDraft(Repository repository);

    // 更新
    void changeInvD(Repository repository);

    // 获取流程信息
    List<HashMap<String, Object>> invInfo(String INST_STATUS_);

    List<HashMap<String, Object>> test();

    // 定时修改
    void changeNum (Repository repository);

    void changeNumMat (Repository repository);
}
