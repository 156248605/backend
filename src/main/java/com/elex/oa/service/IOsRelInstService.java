package com.elex.oa.service;


import com.elex.oa.entity.OsRelInst;

import java.util.List;

public interface IOsRelInstService {

    boolean isPartyExistInRelation(String relTypeId, String party);

    void doChangeInst(String instId, String userId, String groupId);

    void delInstCascade(OsRelInst inst);

    void deleteByRelTypeId(String relTypeId);

    OsRelInst getByParty1Party2RelTypeId(String party1, String party2, String relTypeId);

    OsRelInst getByParty1RelTypeId(String party1, String relTypeId);

    OsRelInst getByParty2RelTypeId(String party2, String relTypeId);

    List<OsRelInst> getByRelTypeIdTenantId(String relTypeId, String tenantId);

    OsRelInst getByRootInstByTypeId(String typeId);

    List<OsRelInst> getGrantGroupsByUserId(String userId, String tenantId);

    List<OsRelInst> getByRelTypeIdParty1(String relTypeId, String party1);




}
