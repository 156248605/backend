package com.elex.oa.service;


import com.elex.oa.entity.BpmOpinionLib;

import java.util.List;

public interface IBpmOpinionLibService {

    List<BpmOpinionLib> getByUserId(String userId);

    boolean isOpinionSaved(String userId, String opText);

    int save(BpmOpinionLib bpmOpinionLib);


}
