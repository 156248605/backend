package com.elex.oa.entity.project;

import org.springframework.data.annotation.Id;

public class ProjectCode {
    @Id
    private String id;
    private String code; //编号

    public ProjectCode() {
    }

    public ProjectCode(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ProjectCode{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
