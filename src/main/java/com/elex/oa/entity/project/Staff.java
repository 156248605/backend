package com.elex.oa.entity.project;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Staff {
    @Id
    private int id;
    private String phoneticize; //英文名
    private String employeeName; // 员工姓名
    private String deptId; //部门id
    private String deptName; //部门名称
    private List<Map<String,Object>> post; //部门

    public Staff() {
    }

    public Staff(int id, String phoneticize, String employeeName, String deptId, String deptName, List<Map<String, Object>> post) {
        this.id = id;
        this.phoneticize = phoneticize;
        this.employeeName = employeeName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneticize() {
        return phoneticize;
    }

    public void setPhoneticize(String phoneticize) {
        this.phoneticize = phoneticize;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        if (StringUtils.isBlank(deptId) || "null".equals(deptId)) {
            this.deptId = "";
        } else {
            this.deptId = deptId;
        }
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        if (StringUtils.isBlank(deptName)) {
            this.deptName = "";
        } else {
            this.deptName = deptName;
        }
    }

    public List<Map<String, Object>> getPost() {
        return post;
    }

    public void setPost(List<Map<String, Object>> post) {
        if(null==post || post.size()==0 || (post.size()==1&& null==post.get(0))){
            this.post = new ArrayList<>();
        }else {
            this.post = post;
        }
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", phoneticize='" + phoneticize + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", post=" + post +
                '}';
    }
}
