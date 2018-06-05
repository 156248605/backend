package com.elex.oa.dao;

import com.elex.oa.entity.OsGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/25 16:35
*/
@Mapper
public interface IOsGroupDao extends BaseDao<OsGroup> {

    List<OsGroup> getByUserIdRelTypeId(Map<String,String> map);

    List<OsGroup> getByDimIdUserIdRelTypeId(Map<String,String> map);

    List<OsGroup> getByParty2RelType(Map<String,String> map);

    List<OsGroup> getByDimIdUserIdRelTypeIdIsMain(Map<String,String> map);

    List<OsGroup> getByDimIdUserId(Map<String,String> map);

    OsGroup getByParentIdGroupName(Map<String,String> map);

    List<OsGroup> getByUserId(Map<String,String> map);

    OsGroup getMainGroupByUserId(Map<String,String> map);

    List<OsGroup> getByReportUserIdByGroup(Map<String,String> map);

    List<OsGroup> getByMenuId(Map<String,String> map);






}
