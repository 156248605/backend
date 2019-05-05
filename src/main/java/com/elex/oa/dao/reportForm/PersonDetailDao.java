package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PersonDetailDao {
    // 根据NAME_获取USER_ID_
    List<HashMap<String, Object>> getUserIdByName(@Param("name") String name);

    // 根据USER_ID_查询流程角色
    List<HashMap<String, Object>> getGroupRoleByUserId(@Param("userId") String userId);

    // 根据USER_ID_查询职能岗位
    List<HashMap<String, Object>> getGroupPositionByUserId(@Param("userId") String userId);

    // 获取所有菜单权限
    List<HashMap<String, Object>> getGrantSubsByGroupId (@Param("groupId") String groupId);
    List<HashMap<String, Object>> getGrantMenusByGroupId (@Param("groupId") String groupId);
}
