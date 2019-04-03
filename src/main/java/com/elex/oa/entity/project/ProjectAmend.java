package com.elex.oa.entity.project;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *@author hugo.zhao
 *@since 2019/3/30 13:04
*/
@Table(name = "pro_amend")
public class ProjectAmend {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String project_json;

    private String record_json;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_json() {
        return project_json;
    }

    public void setProject_json(String project_json) {
        this.project_json = project_json;
    }

    public String getRecord_json() {
        return record_json;
    }

    public void setRecord_json(String record_json) {
        this.record_json = record_json;
    }
}
