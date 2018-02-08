package com.elex.oa.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="department")
public class Department {
    @Id
    private Integer id;
	//部门名称
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




