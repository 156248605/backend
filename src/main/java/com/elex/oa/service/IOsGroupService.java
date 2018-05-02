package com.elex.oa.service;

import com.elex.oa.entity.OsGroup;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/26 10:04
*/
public interface IOsGroupService {
    List<OsGroup> getByUserIdRelTypeId(String userId, String relTypeId);
}
