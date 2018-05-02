package com.elex.oa.service;

import com.elex.oa.entity.BpmSolCtl;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/25 11:11
*/
public interface IBpmSolCtlService {
    List<BpmSolCtl> getBySolIdAndType(String solId, String type);

    List<BpmSolCtl> getBySolAndTypeAndRight(String solId, String type, String right);
}
