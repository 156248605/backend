package com.elex.oa.dao;

import com.elex.oa.entity.MiDbId;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Mapper
public interface IMiDbIdDao extends BaseDao<MiDbId> {
    //根据MAC_NAME_查询系统主键增长表中信息
    public MiDbId getMiDbIdByMacName(Map<String,String> paramMap);
    //根据机器ID更新开始值和增长值
    public int updateMiDbIdById(Map<String,Object> paramMap);
    //查询最大的机器ID值
    public Map<String,Object> getMaxId();
    //新增数据
    public int insertMiDbId(Map<String,Object> paramMap);
}
