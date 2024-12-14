package com.library.domain.user.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserWithNameAndId extends User{
	
	private Integer id;
	private String nickname;
	
	public UserWithNameAndId(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id, String nickname) {
		super(username, password, authorities);
		this.id = id;
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

}
