package com.library.domain.series.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.aspect.NotFoundException;
import com.library.domain.series.model.MSeries;
import com.library.domain.series.service.SeriesService;
import com.library.domain.user.service.impl.UserWithNameAndId;
import com.library.repository.SeriesMapper;

@Service
public class SeriesServiceImpl implements SeriesService{
	
	@Autowired
	private SeriesMapper mapper;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** シリーズリスト取得 */
	@Override
	public List<MSeries> getSeriesList() {
		return mapper.findManySeries(getLoginUserId());
	}
	
	/** シリーズリスト取得(最新購入順) */
	@Override
	public List<MSeries> getSeriesListPurchasedOrder() {
		return mapper.findManySeriesPurchasedOrder(getLoginUserId());
	}
	
	/** シリーズ1件取得 */
	@Override
	public MSeries getOneSeries(int id) {
		//リクエストされたIDのシリーズが存在しない、またはログインユーザーのものでないとエラー
		MSeries series = mapper.findOneSeries(id, getLoginUserId());
		if (series == null) {
			throw new NotFoundException();
		}
		return series;
	}
	
	/** シリーズ名検索（重複確認） */
	@Override
	public String checkSeriesName(String seriesName) {
		return mapper.findBySeriesName(seriesName, getLoginUserId());
	}
	
	/** シリーズ1件登録 */
	@Override
	public void addOneSeries(MSeries series) {
		series.setUserId(getLoginUserId());
		mapper.insertOneSeries(series);
	}
	
	/** シリーズ1件更新 */
	@Override
	public void editOneSeries(MSeries series) {
		mapper.updateOneSeries(series);
	}
	
	/** シリーズ1件削除 */
	@Override
	public void deleteOneSeries(int id) {
		mapper.deleteOneSeriesData(id);
	}

}
