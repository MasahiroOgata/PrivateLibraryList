package com.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.domain.book.model.MBook;

@Mapper
public interface BookMapper {
	
	/** 蔵書リスト取得 */
	public List<MBook> findManyBooks(@Param("search") String search, int userId);
	
	/** 蔵書リスト取得（シリーズ単位） */
	public List<MBook> findSeriesBooks(int id);	
	
	/** 蔵書リスト取得（出版社単位） */
	public List<MBook> findPublisherBooks(int id);
	
	/** 蔵書1冊取得 */
	public MBook findOneBook(int id, int userId);
	
	/** 蔵書1冊取得(当該シリーズ最新書籍) */
	public MBook findOneBookOfSeries(int seriesId);
	
	/** 蔵書1冊登録 */
	public int insertOneBook(MBook book);
	
	/** 蔵書1冊更新 */
	public int updateOneBook(MBook book);
	
	/** 蔵書1冊処分（または取消） */
	public int setOneBookDisposed(MBook book);

}
