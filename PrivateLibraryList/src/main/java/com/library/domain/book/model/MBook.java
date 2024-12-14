package com.library.domain.book.model;

import java.util.Date;

import com.library.domain.publisher.model.MPublisher;
import com.library.domain.series.model.MSeries;

import lombok.Data;

@Data
public class MBook {
	Integer id;				//書籍ID
	Integer userId;			//ユーザーID
	String bookTitle;		//書籍タイトル
	String author;			//著者名
	Date purchasedDate;		//購入日
	Date finishedDate;		//読了日
	Integer seriesId;		//シリーズID
	Integer volNum;			//巻数
	Integer publisherId;	//出版社ID
	Integer media;			//媒体
	Integer isDisposed;		//処分済みフラグ
	MSeries series;			//シリーズデータ
	MPublisher publisher; 	//出版社データ
}
