package dao;

import java.util.List;

import dto.RamenDto;

public interface RamenDao {
	/**
	 * t_shop内の一覧取得
	 * @param RamenDto
	 * @return ラーメン屋情報一覧
	 * @throws Exception
	 */
	public List<RamenDto> searchall() throws Exception;		// ラーメン屋情報を全件取得
	public List<RamenDto> search(RamenDto dto) throws Exception; // 検索条件に一致するラーメン屋情報を取得
	public RamenDto detail(int shopid) throws Exception;	// 特定の店舗コードのラーメン情報を取得
	
	public int insert(RamenDto dto) throws Exception;	// ラーメン屋情報を新規登録
	public int update(RamenDto dto) throws Exception;	// ラーメン屋情報を更新
	public int delete(int shopid) throws Exception;		// ラーメン屋情報を削除
	
	public List<RamenDto> reviewlist(int shopid) throws Exception;	// ラーメン店の口コミ情報一覧
	public RamenDto reviewinfo(int reviewid) throws Exception;	// 特定レビューIDの口コミ情報
	public int reviewcount(RamenDto dto) throws Exception;		// 該当のラーメン店及びユーザーでのの登録済口コミ情報一覧
	public int insertreview(RamenDto dto) throws Exception;	// ラーメン店の口コミ情報登録
	public int updatereview(RamenDto dto) throws Exception;	// ラーメン店の口コミ情報更新
	public int deletereview(int reviewid) throws Exception;	// ラーメン店の口コミ情報削除

	public int deleteallreview(int shopid)  throws Exception;	// 特定の店舗に投稿された口コミ情報を一括削除。店舗情報そのものを削除するの前に使用
	public int insertimage(RamenDto dto)  throws Exception;	// 画像情報を画像テーブルに挿入
	public int deleteimage(int reviewid)  throws Exception;	// 口コミコードに紐づいた画像情報を削除(口コミ削除時に利用)
	public int deleteallimage(int shopid) throws Exception;	// 特定の店舗コードに紐づいた画像情報を削除(店舗情報削除時に利用)
	
	public List<String> imagelist(int reviewid) throws Exception; // 特定の口コミ投稿時にアップロードした画像ファイル名一覧を取得
	public List<String> imagelistofshop(int shopid) throws Exception; // 特定の店舗の全ての口コミ投稿時にアップロードした画像ファイル名一覧を取得
	
	
	// レビューIDを取得。店舗コードとユーザーIDが一致したレコードが対象
	// 1ユーザーにつき各店舗1件までレビュー投稿が可能な仕様とする。
	public void getreviewid(RamenDto dto) throws Exception;
}
