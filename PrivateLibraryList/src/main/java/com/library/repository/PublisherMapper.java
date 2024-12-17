package com.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.domain.publisher.model.MPublisher;

@Mapper
public interface PublisherMapper {
	
	/** 出版社リスト取得 */
	public List<MPublisher> findManyPublishers(int userId);
	
	/** 出版社1件取得 */
	public MPublisher findOnePublisher(int id, int userId);
	
	/** 出版社名からIDを取得(重複確認にも使用) */
	public Integer findPublisherIdByName(String publisherName, int userId);
	
	/** 出版社1件登録 */
	public int insertOnePublisher(MPublisher publisher);
	
	/** 出版社1件更新 */
	public int updateOnePublisher(MPublisher publisher);
	
	/** 出版社1件削除 */
	public int deleteOnePublisherData(int id);
 
}
