package com.library.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.domain.user.model.MUser;
import com.library.domain.user.service.UserService;
import com.library.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String user) {
		return mapper.findLoginUser(user);
	}

}
