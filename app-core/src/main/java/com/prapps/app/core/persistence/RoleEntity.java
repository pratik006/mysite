package com.prapps.app.core.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID")
    private Integer id;  
	@Column(name="NAME")  
    private String name;
	@Column(name="APP_CODE")  
    private String appCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ROLE_PERMISSION",
		joinColumns = {@JoinColumn(name="ROLE_ID")},
		inverseJoinColumns = {@JoinColumn(name="PERMISSION_ID")}
	)
	private Set<PermissionEntity> permissions; 

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
