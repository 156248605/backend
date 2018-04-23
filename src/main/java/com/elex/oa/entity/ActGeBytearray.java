package com.elex.oa.entity;
import javax.persistence.Column;
import javax.persistence.Id;
/**
 *@author hugo.zhao
 *@since 2018/4/8 15:48
*/
public class ActGeBytearray {
    @Id
    @Column(
            name = "ID_"
    )
    private String ID;

    @Column(
            name = "REV_"
    )
    private String REV;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getREV() {
        return REV;
    }

    public void setREV(String REV) {
        this.REV = REV;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDEPLOYMENTID() {
        return DEPLOYMENTID;
    }

    public void setDEPLOYMENTID(String DEPLOYMENTID) {
        this.DEPLOYMENTID = DEPLOYMENTID;
    }

    public byte[] getBYTES() {
        return BYTES;
    }

    public void setBYTES(byte[] BYTES) {
        this.BYTES = BYTES;
    }

    public Integer getGENERATED() {
        return GENERATED;
    }

    public void setGENERATED(Integer GENERATED) {
        this.GENERATED = GENERATED;
    }

    @Column(

            name = "NAME_"
    )
    private String NAME;

    @Column(
            name = "DEPLOYMENT_ID_"
    )
    private String DEPLOYMENTID;

    @Column(
            name = "BYTES_"
    )
    private byte[] BYTES;
    @Column(
            name = "GENERATED_"
    )
    private Integer GENERATED;


}
