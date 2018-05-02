package com.elex.oa.dao;
import com.elex.oa.entity.BpmDef;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
/**
 *@author hugo.zhao
 *@since 2018/3/17 13:18
*/
@Mapper
public interface IBpmDefDao extends BaseDao<BpmDef> {
    //查询流程定义数据
    List<BpmDef> query(Map<String,Object> paramMap);

    BpmDef getByActDefId(String actDefId);

    BpmDef getLatestBpmByKey(Map<String,String> map);

    BpmDef getByDefId(String DefId);

    void updateIsMainByMailDefId(Map<String,String> map);

    Integer getMaxVersions(Map<String,String> map);

    Integer getCountByKey(BpmDef bpmDef);

    Integer getCountByKeyAndId(BpmDef bpmDef);
    /**
     * params String key, String tenantId
     */
    List<BpmDef> getByKey(Map<String,String> map);

}
