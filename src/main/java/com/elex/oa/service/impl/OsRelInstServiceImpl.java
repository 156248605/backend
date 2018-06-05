package com.elex.oa.service.impl;


import com.elex.oa.dao.IOsRelInstDao;
import com.elex.oa.dao.IOsRelTypeDao;
import com.elex.oa.entity.OsRelInst;
import com.elex.oa.entity.OsRelType;
import com.elex.oa.service.IOsRelInstService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
@Service
public class OsRelInstServiceImpl implements IOsRelInstService {
    @Resource
    private IOsRelInstDao osRelInstDao;

    @Resource
    private IOsRelTypeDao osRelTypeDao;
    @Override
    public boolean isPartyExistInRelation(String relTypeId, String party) {
        //return this.osRelInstDao.isPartyExistInRelation(relTypeId, party);
        return false;
    }

    @Override
    public void doChangeInst(String instId, String userId, String groupId) {
        OsRelInst osRelInst = osRelInstDao.selectByPrimaryKey(instId);
        String orgParty2 = osRelInst.getParty2();
        String insts;
        Iterator var7;
        OsRelInst inst;
        List insts1;
        if(StringUtils.isNotEmpty(userId)) {
            if(StringUtils.isNotEmpty(osRelInst.getPath())) {
                insts = osRelInst.getPath().replace(osRelInst.getParty2() + ".", userId + ".");
                osRelInst.setPath(insts);
            }

            osRelInst.setParty2(userId);
           // this.update(osRelInst);
            osRelInstDao.updateByPrimaryKeySelective(osRelInst);
            insts1 = this.getByRelTypeIdParty1(osRelInst.getOsRelTypeId(), orgParty2);
            var7 = insts1.iterator();

            while(var7.hasNext()) {
                inst = (OsRelInst)var7.next();
                inst.setParty1(userId);
               // this.update(inst);
                osRelInstDao.updateByPrimaryKeySelective(inst);
            }
        } else {
            if(StringUtils.isNotEmpty(osRelInst.getPath())) {
                insts = osRelInst.getPath().replace(osRelInst.getParty2() + ".", groupId + ".");
                osRelInst.setPath(insts);
            }

           // this.update(osRelInst);
            osRelInstDao.updateByPrimaryKeySelective(osRelInst);
            osRelInst.setParty2(groupId);
           // this.update(osRelInst);
            osRelInstDao.updateByPrimaryKeySelective(osRelInst);
            insts1 = this.getByRelTypeIdParty1(osRelInst.getOsRelTypeId(), orgParty2);
            var7 = insts1.iterator();

            while(var7.hasNext()) {
                inst = (OsRelInst)var7.next();
                inst.setParty1(groupId);
                //this.update(inst);
                osRelInstDao.updateByPrimaryKeySelective(inst);
            }
        }

    }

    @Override
    public void delInstCascade(OsRelInst inst) {
  /*      if(StringUtils.isNotEmpty(inst.getParty2())) {
            this.delete(inst.getInstId());
        }

        List subInsts = this.getByRelTypeIdParty1(inst.getOsRelType().getId(), inst.getParty2());
        if(subInsts.size() == 0) {
            this.delete(inst.getInstId());
        }

        Iterator var3 = subInsts.iterator();

        while(var3.hasNext()) {
            OsRelInst oi = (OsRelInst)var3.next();
            this.delInstCascade(oi);
        }*/

    }

    @Override
    public void deleteByRelTypeId(String relTypeId) {

    }

    @Override
    public OsRelInst getByParty1Party2RelTypeId(String party1, String party2, String relTypeId) {
        return null;
    }

    @Override
    public OsRelInst getByParty1RelTypeId(String party1, String relTypeId) {
        return null;
    }

    @Override
    public OsRelInst getByParty2RelTypeId(String party2, String relTypeId) {
        OsRelInst osRelInst = new OsRelInst();
        osRelInst.setParty2(party2);
        //osRelInst.setRelTypeId(relTypeId);
        osRelInst.setOsRelTypeId(relTypeId);
        OsRelInst osRelInst1 =  this.osRelInstDao.selectOne(osRelInst);
        OsRelType relType = this.osRelTypeDao.selectByPrimaryKey(relTypeId);
        osRelInst1.setOsRelType(relType);
        return osRelInst1;
    }

    @Override
    public List<OsRelInst> getByRelTypeIdTenantId(String relTypeId, String tenantId) {
        return null;
    }

    @Override
    public OsRelInst getByRootInstByTypeId(String typeId) {
        return null;
    }

    @Override
    public List<OsRelInst> getGrantGroupsByUserId(String userId, String tenantId) {
        return null;
    }

    @Override
    public List<OsRelInst> getByRelTypeIdParty1(String relTypeId, String party1) {
        return null;
    }
}
