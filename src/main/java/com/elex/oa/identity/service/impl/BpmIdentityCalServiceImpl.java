package com.elex.oa.identity.service.impl;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

import com.elex.oa.activiti.util.ProcessHandleHelper;
import com.elex.oa.core.entity.IExecutionCmd;
import com.elex.oa.entity.BpmSolUser;
import com.elex.oa.identity.service.BpmIdentityCalService;
import com.elex.oa.identity.service.IdentityCalConfig;
import com.elex.oa.identity.service.IdentityCalService;
import com.elex.oa.identity.service.IdentityTypeService;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.model.IdentityInfo;
import com.elex.oa.org.service.UserService;
import com.elex.oa.service.IBpmSolUserService;
import com.elex.oa.util.ProcessVarType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/5/5 14:42
*/
@Service
public class BpmIdentityCalServiceImpl implements BpmIdentityCalService {
    @Resource
    //BpmSolUserManager bpmSolUserManager;
    IBpmSolUserService bpmSolUserService;
    @Resource
    IdentityTypeService identityTypeService;
    @Resource
    UserService userService;

    public BpmIdentityCalServiceImpl() {
    }

    public Collection<IdentityInfo> calNodeUsersOrGroups(String actDefId, String nodeId, Map<String, Object> vars) {
        String solId = (String)vars.get("solId");
        Set idInfos = this.getFromStartVars(vars, nodeId);
        if(idInfos != null && idInfos.size() > 0) {
            return idInfos;
        } else {
            List bpmSolUsers = this.bpmSolUserService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId,"task");
            Set idInfoList = this.calNodeUsersOrGroups(actDefId, nodeId, bpmSolUsers, vars);
            return idInfoList;
        }
    }

    private Set<IdentityInfo> getFromStartVars(Map<String, Object> vars, String nodeId) {
        LinkedHashSet infos = new LinkedHashSet();
        String nodeUserIds = (String)vars.get(ProcessVarType.NODE_USER_IDS.getKey());
        if(StringUtils.isEmpty(nodeUserIds)) {
            return infos;
        } else {
            try {
                JSONArray e = JSONArray.fromObject(nodeUserIds);

                for(int i = 0; i < e.size(); ++i) {
                    JSONObject obj = e.getJSONObject(i);
                    String tNodeId = obj.getString("nodeId");
                    if(nodeId.equals(tNodeId)) {
                        String userIds = obj.getString("userIds");
                        if(!StringUtils.isEmpty(userIds)) {
                            String[] uIds = userIds.split("[,]");
                            String[] var11 = uIds;
                            int var12 = uIds.length;

                            for(int var13 = 0; var13 < var12; ++var13) {
                                String uId = var11[var13];
                                IUser osUser = this.userService.getByUserId(uId);
                                infos.add(osUser);
                            }
                        }
                        break;
                    }
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            return infos;
        }
    }

    public Set<IdentityInfo> calNodeUsersOrGroups(String actDefId, String nodeId, List<BpmSolUser> bpmSolUsers, Map<String, Object> vars) {
        LinkedHashSet idInfoList = new LinkedHashSet();
        String instId = (String)vars.get("instId");
        IExecutionCmd cmd = ProcessHandleHelper.getProcessCmd();
        String logic = null;
        int i = 0;

        BpmSolUser bsu;
        for(Iterator var10 = bpmSolUsers.iterator(); var10.hasNext(); logic = bsu.getCalLogic()) {
            bsu = (BpmSolUser)var10.next();
            IdentityCalService service = (IdentityCalService)this.identityTypeService.getIdentityCalServicesMap().get(bsu.getUserType());
            IdentityCalConfig idCalConfig = new IdentityCalConfig();
            idCalConfig.setIsCalUser(bsu.getIsCal());
            idCalConfig.setNodeId(nodeId);
            idCalConfig.setProcessInstId(instId);
            idCalConfig.setProcessDefId(actDefId);
            idCalConfig.setJsonConfig(bsu.getConfig());
            idCalConfig.setVars(vars);
            if(cmd != null) {
                idCalConfig.setToken(cmd.getToken());
            }

            Collection tmpList = service.calIdentities(idCalConfig);
            if(i++ == 0) {
                idInfoList.addAll(tmpList);
            } else if("NOT".equals(logic)) {
                idInfoList.removeAll(tmpList);
            } else if("AND".equals(logic)) {
                idInfoList.retainAll(tmpList);
            } else {
                idInfoList.addAll(tmpList);
            }
        }

        return idInfoList;
    }
}