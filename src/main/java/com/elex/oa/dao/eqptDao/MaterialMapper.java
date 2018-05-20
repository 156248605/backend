package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialMapper {

    // 遍历所有物料
    List<Material> MaterialList();
    // 条件查询物料
    List<Material> SearchMaterial(Material material);
    // 根据id定位物料
    Material MaterialId(Material material);
    // 更新物料
    void saveMaterial(Material material);
    // 删除物料
    void deleteMaterial(Material material);
    // 新建物料
    void newMaterial(Material material);
    // 判断物料id存在
    Material Id(Material material);
    // 同步更新
    void updMaterial(Material material);
    // 获取数量下限
    Material MinLimit(Material material);
    // xxxx上限
    Material MaxLimit(Material material);

    List searchSN(Material material);

    // 导入Excel
    int importMaterial(Material material);
}
