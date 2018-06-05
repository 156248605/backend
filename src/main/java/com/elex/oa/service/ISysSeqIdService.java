package com.elex.oa.service;


import com.elex.oa.entity.core.SysSeqId;

public interface ISysSeqIdService {

    String genSequenceNo(String alias, String tenantId);

    SysSeqId getByAlias(String alias, String tenantId);

    SysSeqId getById(String Id);

}
