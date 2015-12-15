package com.prapps.app.core.secutiry;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prapps.app.core.api.UserService;
import com.prapps.app.core.dataaccess.UserRepository;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.persistence.RoleEntity;
import com.prapps.app.core.persistence.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

	private final UserRepository userRepository;
	
	@Inject
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserEntity user = userRepository.findOneByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new UserDetails() {
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return user.isEnabled();
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return !user.isCredentialExpired();
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return !user.isLocked();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return !user.isExpired();
			}
			
			@Override
			public String getUsername() {
				return user.getUserName();
			}
			
			@Override
			public String getPassword() {
				return user.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				Collection<SimpleGrantedAuthority> list = new ArrayList<>();
				for(RoleEntity role : user.getRoles()) {
					list.add(new SimpleGrantedAuthority(role.getName()));
				}
				return list;
			}
		};
	}
	
	@Override
	public User findUser(String username) {
		UserEntity userEntity = userRepository.findOneByUserName(username);
		userEntity.setPassword(null);
		User user = new User();
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}
}
