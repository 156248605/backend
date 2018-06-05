package com.elex.oa.service;


import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.entity.Page;

import java.util.List;
import java.util.Set;

public interface IBpmNodeJumpService {

    Set<String> getNodeHandleUserIds(String actInstId);

    List<BpmNodeJump> getByActInstId(String actInstId);

    List<BpmNodeJump> getByActInstId(String actInstId, String status);

    List<BpmNodeJump> getByActInstId(String actInstId, Page page);

    List<BpmNodeJump> getFormOpinionByActInstId(String actInstId);

    List<BpmNodeJump> getByActInstIdNodeId(String actInstId, String nodeId);
}
