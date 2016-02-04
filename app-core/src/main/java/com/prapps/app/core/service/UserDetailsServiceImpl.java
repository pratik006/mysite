package com.prapps.app.core.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prapps.app.core.api.UserService;
import com.prapps.app.core.dataaccess.UserRepository;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.dto.UserDetailsImpl;
import com.prapps.app.core.mapper.UserMapper;
import com.prapps.app.core.persistence.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

	private final UserRepository userRepository;
	@Inject UserMapper userMapper;
	
	@Inject
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserEntity userEntity = userRepository.findOneByUserName(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found");
		}
		User user = userMapper.mapUserEntity(userEntity);
		return new UserDetailsImpl(user);
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
