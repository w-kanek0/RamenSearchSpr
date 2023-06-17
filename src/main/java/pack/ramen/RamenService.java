package pack.ramen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RamenDao;
import dto.RamenDto;

@Service
public class RamenService {

	@Autowired
	private RamenDao dao;
	
	/**
	 * ラーメン屋情報の全件一覧取得を行う
	 * @param なし
	 * @return 取得データ
	 * @throws Exception
	 */
	public List<RamenDto> getAllRamenList() throws Exception {
		return dao.searchall();
	}
	
	/**
	 * ラーメン屋情報の検索条件に一致するデータの一覧取得を行う
	 * @param RamenDto
	 * @return 取得データ
	 * @throws Exception
	 */
	public List<RamenDto> getRamenListForSearch(RamenDto dto) throws Exception {
		return dao.search(dto);
	}

	/**
	 * 指定した店舗コードに一致するラーメン屋情報を取得
	 * @param 店舗コード
	 * @return 取得データ
	 * @throws Exception
	 */
	public RamenDto getRamenInfoForUpdate(int shopid) throws Exception {
		return dao.detail(shopid);
	}
	
	/**
	 * ラーメン屋情報を新規登録。
	 * @param RamenDto
	 * @return RamenDaoクラスのinsertメソッドの戻り値
	 * @throws Exception
	 */
	public int insertRamenInfo(RamenDto dto) throws Exception {
		return dao.insert(dto);
	}
	
	/**
	 * ラーメン屋情報を更新。
	 * @param RamenDto
	 * @return RamenDaoクラスのupdateメソッドの戻り値
	 * @throws Exception
	 */
	public int updateRamenInfo(RamenDto dto) throws Exception {
		return dao.update(dto);
	}
	
	/**
	 * 指定した店舗コードのラーメン屋情報を削除。
	 * @param 店舗コード
	 * @return RamenDaoクラスのupdateメソッドの戻り値
	 * @throws Exception
	 */
	public int deleteRamenInfo(int shopid) throws Exception {
		return dao.delete(shopid);
	}
	
	/**
	 * 指定した店舗コードに登録された口コミ情報を取得。
	 * @param 店舗コード
	 * @return RamenDaoクラスのupdateメソッドの戻り値
	 * @throws Exception
	 */
	public List<RamenDto> getRamenReviewList(int shopid) throws Exception {
		return dao.reviewlist(shopid);
	}
	
	/**
	 * 該当の店舗及びユーザーで口コミが登録されている件数を取得(二重登録防止のため)
	 * @param RamenDto
	 * @return RamenDaoクラスのreviewcountの戻り値
	 * @throws Exception
	 */	
	public int getReviewCount(RamenDto dto) throws Exception {
		return dao.reviewcount(dto);
	}
	
	/**
	 * 登録した口コミのレビューIDを取得
	 * @param RamenDto
	 * @return なし
	 * @throws Exception
	 */	
	public void getReviewid(RamenDto dto) throws Exception {
		dao.getreviewid(dto);
	}
	
	/**
	 * ラーメン屋口コミ情報を新規登録。
	 * @param RamenDto
	 * @return RamenDaoクラスのinsertメソッドの戻り値
	 * @throws Exception
	 */
	public int insertRamenReviewInfo(RamenDto dto) throws Exception {
		return dao.insertreview(dto);
	}
	
	/**
	 * 該当のレビューIDのラーメン屋口コミ情報を取得。
	 * @param reviewid
	 * @return RamenDaoクラスのreviewinfoメソッドの戻り値
	 * @throws Exception
	 */
	public RamenDto getRamenReviewInfoForUpdate(int reviewid) throws Exception {
		return dao.reviewinfo(reviewid);
	}
	
	/**
	 * ラーメン屋口コミ情報を更新。
	 * @param RamenDto
	 * @return RamenDaoクラスのinsertメソッドの戻り値
	 * @throws Exception
	 */
	public int updateRamenReviewInfo(RamenDto dto) throws Exception {
		return dao.updatereview(dto);
	}
	
	/**
	 * ラーメン屋口コミ情報を削除。
	 * @param reviewid
	 * @return RamenDaoクラスのdeletereviewメソッドの戻り値
	 * @throws Exception
	 */
	int deleteRamenReviewInfo(int reviewid) throws Exception {
		return dao.deletereview(reviewid);
	}

	/**
	 * ラーメン屋口コミ情報を削除。
	 * @param shopid
	 * @return RamenDaoクラスのdeleteallreviewメソッドの戻り値
	 * @throws Exception
	 */
	int deleteReviewByShopid(int shopid) throws Exception {
		return dao.deleteallreview(shopid);
	}
	
	/**
	 * 該当のレビューIDの画像ファイル情報を取得。
	 * @param reviewid
	 * @return RamenDaoクラスのimagelistメソッドの戻り値(画像ファイル名一覧)
	 * @throws Exception
	 */
	List<String> getImageList(int reviewid) throws Exception {
		return dao.imagelist(reviewid);
	}
	
	/**
	 * 該当の店舗コードの画像ファイル情報を取得。
	 * @param reviewid
	 * @return RamenDaoクラスのimagelistメソッドの戻り値(画像ファイル名一覧)
	 * @throws Exception
	 */
	List<String> getImageListFromShopid(int shopid) throws Exception {
		return dao.imagelistofshop(shopid);
	}
	
	/**
	 * ラーメン屋口コミ情報に登録する画像ファイル名を登録。
	 * @param RamenDto
	 * @return RamenDaoクラスのinsertimageメソッドの戻り値
	 * @throws Exception
	 */
	int insertImage(RamenDto dto) throws Exception {
		return dao.insertimage(dto);
	}
	
	/**
	 * ラーメン屋口コミ情報に登録済みの画像ファイル情報を削除。
	 * @param reviewid
	 * @return RamenDaoクラスのdeleteimageメソッドの戻り値
	 * @throws Exception
	 */
	int deleteImage(int reviewid) throws Exception {
		return dao.deleteimage(reviewid);
	}
	
	/**
	 * 指定した店舗コードラーメン屋口コミ情報に登録済みの画像ファイル情報を全て削除。
	 * @param reviewid
	 * @return RamenDaoクラスのdeleteallimageメソッドの戻り値
	 * @throws Exception
	 */
	int deleteImageByShopid(int shopid) throws Exception {
		return dao.deleteallimage(shopid);
	}
}
