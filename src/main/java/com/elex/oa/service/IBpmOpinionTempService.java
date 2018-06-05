package com.elex.oa.service;

import com.elex.oa.entity.BpmOpinionTemp;

/**
 *@author hugo.zhao
 *@since 2018/5/4 15:24
*/
public interface IBpmOpinionTempService {

    BpmOpinionTemp getByType(String type, String typeId);
}
