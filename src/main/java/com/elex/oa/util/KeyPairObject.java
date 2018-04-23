package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:31
*/
public class KeyPairObject {
    private String privateKey = "";
    private String publicKey = "";

    public KeyPairObject() {
    }

    public KeyPairObject(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
