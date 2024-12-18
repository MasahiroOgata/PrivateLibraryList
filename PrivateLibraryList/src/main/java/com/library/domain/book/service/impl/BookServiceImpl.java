package com.library.domain.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.aspect.NotFoundException;
import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;
import com.library.domain.publisher.model.MPublisher;
import com.library.domain.publisher.service.PublisherService;
import com.library.domain.user.service.impl.UserWithNameAndId;
import com.library.repository.BookMapper;

@Service
public class BookServiceImpl implements BookService {	
	
	@Autowired
	private BookMapper mapper;
	
	@Autowired
	private PublisherService publisherService;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** 蔵書リスト取得 */
	@Override
	public List<MBook> getBookList(String search) {
		return mapper.findManyBooks(search, getLoginUserId());
	}
	
	/** 蔵書リスト取得（シリーズ単位） */
	@Override
	public List<MBook> getSeriesBookList(int id) {
		return mapper.findSeriesBooks(id);
	}
	
	/** 蔵書リスト取得（出版社単位） */
	@Override
	public List<MBook> getPublisherBookList(int id) {
		return mapper.findPublisherBooks(id);
	}
	
	/** 蔵書1冊取得 */
	@Override
	public MBook getOneBook(int id) {
		//リクエストされたIDの書籍が存在しない、またはログインユーザーのものでないとエラー
		MBook book = mapper.findOneBook(id, getLoginUserId());
		if (book == null) {
			throw new NotFoundException();
		}
		return book;
	}
	
	/** 蔵書1冊取得(当該シリーズ最新書籍) */
	@Override
	public MBook getOneBookOfSeries(int seriesId) {
		return mapper.findOneBookOfSeries(seriesId);
	}
	
	/** 蔵書1冊登録 */
	@Override
	public void addOneBook(MBook book) {
		book.setUserId(getLoginUserId());
		mapper.insertOneBook(book);
	}
	
	/** 蔵書1冊登録および出版社1件登録 */
	@Transactional
	@Override
	public void addOneBookAndOnePublisher(MBook book, MPublisher publisher) {
		publisherService.addOnePublisher(publisher);
		book.setPublisherId(publisher.getId());
		addOneBook(book);		
	}
	
	/** 蔵書1冊更新 */
	@Override
	public void editOneBook(MBook book) {
		mapper.updateOneBook(book);
	}
	
	/** 蔵書1冊更新および出版社1件登録 */
	@Transactional
	@Override
	public void editOneBookAndAddOnePublisher(MBook book, MPublisher publisher) {
		publisherService.addOnePublisher(publisher);
		book.setPublisherId(publisher.getId());
		editOneBook(book);
	}
	
	/** 蔵書1冊処分（または取消） */
	@Override
	public void disposeOneBook(MBook book) {
		mapper.setOneBookDisposed(book);
	}

}
