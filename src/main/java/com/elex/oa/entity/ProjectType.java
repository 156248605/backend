package com.elex.oa.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="project_type")
public class ProjectType {
    @Id
    private Integer id;
	//项目类型
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}




