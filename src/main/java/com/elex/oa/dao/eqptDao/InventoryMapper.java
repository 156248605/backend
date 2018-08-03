package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

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

    List<Material> chooseMat(Material material);

    List<Repository> reptlist();

}
