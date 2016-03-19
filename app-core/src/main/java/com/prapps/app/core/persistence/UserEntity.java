package com.prapps.app.core.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 124809048698999960L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private long userId;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="APP_CODE")
	private String appCode;
	@Column(name="ENABLED")
	private Boolean enabled;
	@Column(name="CREDENTIAL_EXPIRED")
	private Boolean credentialExpired;
	@Column(name="LOCKED")
	private Boolean locked;
	@Column(name="EXPIPRED")
	private Boolean expired;
	
	@OneToMany
	@JoinColumn(name="USER_ID")
	private List<UserAttributeEntity> userAttributes;
	//@Column(name="ROLE")
	private transient String roleStr;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
    @JoinTable(name="USER_ROLES",  
        joinColumns = {@JoinColumn(name="USER_ID")},  
        inverseJoinColumns = {@JoinColumn(name="ROLE_ID")}  
    )  
    private Set<RoleEntity> roles;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoleStr() {
		if(roles != null) {
			roleStr = "";
			for(RoleEntity r : roles) {
				if(null != r && r.getName()!=null) {
					roleStr += r.getName()+", ";
				}
			}
			if(roleStr!=null && !roleStr.isEmpty() && roleStr.endsWith(", ")) {
				roleStr.substring(0, roleStr.length()-2);
			}
		}
		return roleStr;
	}
	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	
	public List<UserAttributeEntity> getUserAttributes() {
		return userAttributes;
	}
	public void setUserAttributes(List<UserAttributeEntity> userAttributes) {
		this.userAttributes = userAttributes;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public Boolean isLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean isExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean isCredentialExpired() {
		return credentialExpired;
	}
	public void setCredentialExpired(Boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", roleStr=" + roleStr + ", roles=" + roles + "]";
	}
}
