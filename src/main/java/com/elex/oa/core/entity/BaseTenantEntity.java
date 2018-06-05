package com.elex.oa.core.entity;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
@MappedSuperclass
public abstract class BaseTenantEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Column(
            name = "TENANT_ID_"
    )
    @Size(
            max = 64
    )
    protected String tenantId = null;

    public BaseTenantEntity() {
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}