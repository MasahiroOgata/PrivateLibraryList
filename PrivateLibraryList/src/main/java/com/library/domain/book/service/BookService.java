package com.library.domain.book.service;

import java.util.List;

import com.library.domain.book.model.MBook;

public interface BookService {
	
	/** 蔵書リスト取得 */
	public List<MBook> getBookList(String search);
	
	/** 蔵書リスト取得（シリーズ単位） */
	public List<MBook> getSeriesBookList(int id);
	
	/** 蔵書リスト取得（出版社単位） */
	public List<MBook> getPublisherBookList(int id);
	
	/** 蔵書1冊取得 */
	public MBook getOneBook(int id);
	
	/** 蔵書1冊取得(当該シリーズ最新書籍) */
	public MBook getOneBookOfSeries(int seriesId);
	
	/** 蔵書1冊登録 */
	public void addOneBook(MBook book);
	
	/** 蔵書1冊更新 */
	public void editOneBook(MBook book);
	
	/** 蔵書1冊処分（または取消） */
	public void disposeOneBook(MBook book);

}
