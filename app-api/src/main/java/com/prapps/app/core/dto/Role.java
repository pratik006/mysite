package com.prapps.app.core.dto;

import java.util.Set;


public class Role {

    private Integer id;  
    private String name;
    private String appCode;
	private Set<Permission> permissions; 

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

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", appCode=" + appCode + ", permissions=" + permissions + "]";
	}
}
