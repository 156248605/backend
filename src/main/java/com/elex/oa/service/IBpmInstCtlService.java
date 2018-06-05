package com.elex.oa.service;


import com.elex.oa.entity.BpmInstCtl;

import java.util.List;

public interface IBpmInstCtlService {

     List<BpmInstCtl> getBySolAndTypeAndRight(String instId, String type, String right);

     boolean sysFileCtl(String userId, String instId, String right);
}
