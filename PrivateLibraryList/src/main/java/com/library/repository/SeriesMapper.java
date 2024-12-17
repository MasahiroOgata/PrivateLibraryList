package com.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.domain.series.model.MSeries;

@Mapper
public interface SeriesMapper {
	
	/** シリーズリスト取得 */
	public List<MSeries> findManySeries(int userId);
	
	/** シリーズリスト取得(最新購入順) */
	public List<MSeries> findManySeriesPurchasedOrder(int userId);
	
	/** シリーズ1件取得 */
	public MSeries findOneSeries(int id, int userId);
	
	/** シリーズ名からIDを取得(重複確認に使用) */
	public Integer findSeriesIdByName(String seriesName, int userId);
	
	/** シリーズ1件登録 */
	public int insertOneSeries(MSeries series);
	
	/** シリーズ1件更新 */
	public int updateOneSeries(MSeries series);
	
	/** シリーズ1件削除 */
	public int deleteOneSeriesData(int id);

}
