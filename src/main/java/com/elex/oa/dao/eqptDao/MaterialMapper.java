package com.elex.oa.dao.eqptDao;



import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface MaterialMapper {

    // 遍历所有启用物料
    List<Material> MaterialList(String userName);
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

    //获取最大最小值和物料数量
    String MaxLimit(Material material);
    String MinLimit(Material material);
    String getNum(Material material);
    String getId(Material material);
    String getNumD(Material material);

    // 导入Excel
    int importMaterial(Material material);

    // 入库和出库
    void updMat(Material material);

    void updMatM(Material material);

    List<Material> searchList(List<String> listId, List<String> listName, String SDATE, String EDATE);

    // 确定批次号还是序列号
    Material lockBn(Material material);
    Material lockSn(Material material);

    // 确定价格
    Material lockPrice(Material material);

    // 更新库存
    void updDetail(Material material);

    void updDetailM(Material material);

    void newDetail(Material material);

    void insertDetail(Material material);

    void deleteDetail(Material material);

    void saveDetail(Material material);

    //查询是否有库存记录
    String matInDetail(Material material);

    // 修改库位时修改库存查询
    void updateDetail(String reptId, String postId, String position);

    void deleteNull(Material material);

    // 获取物料类别
    List<HashMap<String,Object>> getCategory();
}
