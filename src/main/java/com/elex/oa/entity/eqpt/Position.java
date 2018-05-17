package com.elex.oa.entity.eqpt;

public class Position {
    // 仓库编号
    private String reptId;
    // 仓库名称
    private String reptName;
    // 库位编号
    private String postId;
    // 库位名称
    private String postName;
    // 库位类别
    private String postCategory;
    // 固定库位物品
    private String postGoods;
    // 库位容量
    private String postCapacity;
    // 备注
    private String remark;

    public Position() {
    }

    public Position(String reptId, String reptName, String postId, String postName, String postCategory, String postGoods, String postCapacity, String remark) {
        this.reptId = reptId;
        this.reptName = reptName;
        this.postId = postId;
        this.postName = postName;
        this.postCategory = postCategory;
        this.postGoods = postGoods;
        this.postCapacity = postCapacity;
        this.remark = remark;
    }

    public String getReptId() {
        return reptId;
    }

    public void setReptId(String reptId) {
        this.reptId = reptId;
    }

    public String getReptName() {
        return reptName;
    }

    public void setReptName(String reptName) {
        this.reptName = reptName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostGoods() {
        return postGoods;
    }

    public void setPostGoods(String postGoods) {
        this.postGoods = postGoods;
    }

    public String getPostCapacity() {
        return postCapacity;
    }

    public void setPostCapacity(String postCapacity) {
        this.postCapacity = postCapacity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Position{" +
                "reptId='" + reptId + '\'' +
                ", reptName='" + reptName + '\'' +
                ", postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postGoods='" + postGoods + '\'' +
                ", postCapacity='" + postCapacity + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
