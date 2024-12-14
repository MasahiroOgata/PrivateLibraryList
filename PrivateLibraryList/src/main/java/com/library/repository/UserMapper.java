package com.library.repository;

import org.apache.ibatis.annotations.Mapper;

import com.library.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/** ログインユーザー情報取得 */
	public MUser findLoginUser(String user);

}
