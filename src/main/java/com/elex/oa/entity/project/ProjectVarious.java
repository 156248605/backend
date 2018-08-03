package com.elex.oa.entity.project;

public class ProjectVarious {
    private int code;
    private String name;

    public ProjectVarious() {
    }

    public ProjectVarious(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProjectVarious{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
