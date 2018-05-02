package com.elex.oa.identity.service;

import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/4/24 19:09
*/
@Service
public abstract class AbstractIdentityCalService implements IdentityCalService {
    protected String typeKey;
    protected String typeName;
    protected String description;
    protected String handlerClass;

    public AbstractIdentityCalService() {
    }

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescp() {
        return this.description;
    }
}
