package com.library.domain.user.service;

import com.library.domain.user.model.MUser;

public interface UserService {
	
	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String user);

}
