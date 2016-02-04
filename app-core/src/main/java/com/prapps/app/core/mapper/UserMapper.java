package com.prapps.app.core.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.prapps.app.core.dto.Role;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.persistence.RoleEntity;
import com.prapps.app.core.persistence.UserEntity;

@Component
public class UserMapper {

	public User mapUserEntity(UserEntity userEntity) {
		User user = new User();
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setUserName(userEntity.getUserName());
		user.setEnabled(userEntity.isEnabled());
		user.setExpired(userEntity.isExpired());
		user.setCredentialExpired(userEntity.isCredentialExpired());
		user.setLocked(userEntity.isLocked());
		user.setAppCode(userEntity.getAppCode());
		user.setPassword(userEntity.getPassword());
		
		Set<Role> roles = new HashSet<Role>(userEntity.getRoles().size());
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			Role role = new Role();
			role.setAppCode(roleEntity.getAppCode());
			role.setId(roleEntity.getId());
			role.setName(roleEntity.getName());
			roles.add(role);
		}
		user.setRoles(roles);
		return user;
	}
}
