package com.library.domain.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.domain.user.model.MUser;
import com.library.domain.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MUser loginUser = userService.getLoginUser(username);
		
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority("GENERAL");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		UserDetails userDetails = (UserDetails) new UserWithNameAndId(loginUser.getUser(),
				loginUser.getPass(),
				authorities,
				loginUser.getId(),
				loginUser.getNickname());
		
		return userDetails;
	}
	
}