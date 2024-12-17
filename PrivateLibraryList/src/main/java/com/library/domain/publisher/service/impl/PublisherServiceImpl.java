package com.library.domain.publisher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.aspect.NotFoundException;
import com.library.domain.publisher.model.MPublisher;
import com.library.domain.publisher.service.PublisherService;
import com.library.domain.user.service.impl.UserWithNameAndId;
import com.library.repository.PublisherMapper;

@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherMapper mapper;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** 出版社リスト取得 */
	@Override
	public List<MPublisher> getPublisherList() {
		return mapper.findManyPublishers(getLoginUserId());
	}
	
	/** 出版社1件取得 */
	@Override
	public MPublisher getOnePublisher(int id) {
		//リクエストされたIDの出版社が存在しない、またはログインユーザーのものでないとエラー
		MPublisher publisher = mapper.findOnePublisher(id, getLoginUserId());
		if (publisher == null) {
			throw new NotFoundException();
		}
		return publisher;
	}
	
	/** 出版社名からIDを取得 */
	@Override
	public Integer fetchPublisherIdByName(String publisherName) {
		return mapper.findPublisherIdByName(publisherName, getLoginUserId());
	}
	
	/** 出版社名の重複を確認 */
	@Override
	public boolean isRegisteredName(String publisherName) {
		return mapper.findPublisherIdByName(publisherName, getLoginUserId()) != null;
	}
	
	/** 出版社1件登録 */
	@Override
	public void addOnePublisher(MPublisher publisher) {
		publisher.setUserId(getLoginUserId());
		mapper.insertOnePublisher(publisher);
	}
	
	/** 出版社1件更新 */
	@Override
	public void editOnePublisher(MPublisher publisher) {
		mapper.updateOnePublisher(publisher);
	}
	
	/** 出版社1件削除 */
	@Override
	public void deleteOnePublisher(int id) {
		mapper.deleteOnePublisherData(id);
	}

}
