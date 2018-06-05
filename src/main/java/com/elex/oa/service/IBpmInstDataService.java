package com.elex.oa.service;

import com.elex.oa.entity.BpmInstData;

import java.util.List;

public interface IBpmInstDataService {
    String getPk(String instId, String boDefId);

    List<BpmInstData> getByInstId(String instId);
}
