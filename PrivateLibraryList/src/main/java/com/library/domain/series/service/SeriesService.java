package com.library.domain.series.service;

import java.util.List;

import com.library.domain.series.model.MSeries;

public interface SeriesService {
	
	/** シリーズリスト取得 */
	public List<MSeries> getSeriesList();
	
	/** シリーズ1件取得 */
	public MSeries getOneSeries(int id);
	
	/** シリーズ名検索（重複確認） */
	public String checkSeriesName(String seriesName);
	
	/** シリーズ1件登録 */
	public void addOneSeries(MSeries series);
	
	/** シリーズ1件更新 */
	public void editOneSeries(MSeries series);
	
	/** シリーズ1件削除 */
	public void deleteOneSeries(int id);

}
