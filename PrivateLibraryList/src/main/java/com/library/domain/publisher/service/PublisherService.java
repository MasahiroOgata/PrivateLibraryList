package com.library.domain.publisher.service;

import java.util.List;

import com.library.domain.publisher.model.MPublisher;

public interface PublisherService {
	
	/** 出版社リスト取得 */
	public List<MPublisher> getPublisherList();
	
	/** 出版社1件取得 */
	public MPublisher getOnePublisher(int id);
	
	/** 出版社名検索（重複確認） */
	public Integer checkPublisherName(String publisherName);
	
	/** 出版社1件登録 */
	public void addOnePublisher(MPublisher publisher);
	
	/** 出版社1件更新 */
	public void editOnePublisher(MPublisher publisher);
	
	/** 出版社1件削除 */
	public void deleteOnePublisher(int id);
	

}
