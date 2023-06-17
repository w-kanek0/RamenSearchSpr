package pack.ramen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.RamenDao;
import dto.RamenDto;

@Repository
public class RamenDaoImpl implements RamenDao {

	//JdbcTemplateクラスをAutowiredでDI
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private final String SQL_SELECTALL = "SELECT s.shop_id , s.shop_name , g.genre_name , s.area , s.open_day ,"
			+ " s.open_time , s.close_time , v.value , s.date_create , s.date_update,"
			+ "  s.pict  FROM t_shop AS s LEFT OUTER JOIN m_genre AS g ON s.genre_id = g.genre_id"
			+ " LEFT OUTER JOIN m_value AS v ON s.value_id = v.value_id"
			+ " ORDER BY s.shop_id";	// データ全件取得
	private final String SQL_INSERT = "INSERT INTO t_shop (shop_name, genre_id, area,"
			+ " open_day, open_time, close_time, value_id, date_create, date_update, pict)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, now(), now() , ?)";
	private final String SQL_UPDATE = "UPDATE t_shop SET shop_name = ?, genre_id = ?,  area = ?,"
			+ " open_day = ?, open_time = ?, close_time = ?, value_id = ?, date_update = now()"
			+ " WHERE shop_id = ?";
	private final String SQL_UPDATE_WITH_FILE = "UPDATE t_shop SET shop_name = ?, genre_id = ?,  area = ?,"
			+ " open_day = ?, open_time = ?, close_time = ?, value_id = ?, date_update = now(), pict = ?"
			+ " WHERE shop_id = ?";
	private final String SQL_DELETE = "DELETE FROM t_shop WHERE shop_id = ?";
	
	private final String SQL_REVIEWLIST = "SELECT r.review_id, r.user_id, v.value, r.review_title, r.review,"
			+ " r.date_create, r.date_update, r.date_visit"
			+ " FROM t_review AS r LEFT OUTER JOIN m_value AS v ON r.value_id = v.value_id"
			+ " WHERE r.shop_id = ? ORDER BY r.date_update DESC";
	
	private final String SQL_REVIEWCOUNT = "SELECT count(*) as review_count FROM t_review WHERE shop_id = ?"
			+ " AND user_id = ?";
	
	private final String SQL_REVIEWID = "SELECT review_id FROM t_review WHERE shop_id = ? AND user_id = ?";
	
	private final String SQL_GETREVIEW = "SELECT r.review_id, r.user_id, v.value, r.review_title, r.review,"
			+ " r.date_create, r.date_update, r.date_visit"
			+ " FROM t_review AS r LEFT OUTER JOIN m_value AS v ON r.value_id = v.value_id"
			+ " WHERE r.review_id = ?";
	
	private final String SQL_INSERTREVIEW = "INSERT INTO t_review (shop_id, user_id, value_id, review_title,"
			+ " review, date_create, date_update, date_visit)"
			+ " VALUES (?, ?, ?, ?, ?, now(), now(), ?)";
			
	private final String SQL_UPDATEREVIEW = "UPDATE t_review SET value_id = ?,  review_title = ?, review = ?,"
			+ " date_update = now(), date_visit = ? WHERE review_id = ?";
	
	private final String SQL_DELETEREVIEW = "DELETE FROM t_review WHERE review_id = ?";
	
	private final String SQL_DELETEALLREVIEW = "DELETE FROM t_review WHERE shop_id = ?";
	
	private final String SQL_GETIMAGE = "SELECT filename from t_image WHERE review_id = ?";
	
	private final String SQL_GETALLIMAGE = "SELECT i.filename FROM t_image AS i LEFT OUTER JOIN t_review AS r"
											+ " ON i.review_id = r.review_id WHERE r.shop_id = ?";
	
	private final String SQL_INSERTIMAGE = "INSERT INTO t_image (review_id, filename) VALUES (?, ?)";
	
	private final String SQL_DELETEIMAGE = "DELETE FROM t_image WHERE review_id = ?";
	
	private final String SQL_DELETEALLIMAGE = "DELETE FROM t_image WHERE review_id in ( SELECT review_id FROM t_review"
											+ " WHERE shop_id = ? )";
	
	// ラーメン屋情報テーブルから全件のデータを取得
	@Override
	public List<RamenDto> searchall() throws Exception {		
		
		List<RamenDto> list = jdbcTemplate.query(
				SQL_SELECTALL, new RowMapper<RamenDto>() {
				public RamenDto mapRow(ResultSet rs, int rowNum) throws SQLException{
					
					RamenDto dto = new RamenDto();

					dto.setShopid(rs.getInt("shop_id"));
					dto.setShopName(rs.getString("shop_name"));
					dto.setGenreName(rs.getString("genre_name"));
					dto.setArea(rs.getString("area"));
					dto.setOpenday(rs.getDate("open_day"));
					dto.setOpentime(rs.getTime("open_time"));
					dto.setClosetime(rs.getTime("close_time"));
					dto.setValueLabel(rs.getString("value"));
					dto.setRegisterdate(rs.getTimestamp("date_create"));
					dto.setLastupdate(rs.getTimestamp("date_update"));
					dto.setFilename(rs.getString("pict"));
					
					return dto;
				}
			}
		);
		
		return list;
	}

	// ラーメン屋情報テーブルから検索条件に一致したデータのみ取得(検索条件が未指定の場合全件取得)
	@Override
	public List<RamenDto> search(RamenDto dto) throws Exception {
		// SQL文設定(t_shopのデータを全件取得)
		String sql = "SELECT s.shop_id , s.shop_name , g.genre_name , s.area , s.open_day ,"
				+ " s.open_time , s.close_time , v.value , s.date_create , s.date_update,"
				+ "  s.pict  FROM t_shop AS s LEFT OUTER JOIN m_genre AS g ON s.genre_id = g.genre_id"
				+ " LEFT OUTER JOIN m_value AS v ON s.value_id = v.value_id";

		// 検索条件が指定されている場合
		if(dto != null) {
			boolean andFlag = false;
			/* 検索条件に店舗名、エリア、ジャンル、評価、営業時間、オープン日のいずれかが指定されていれば
			 * " WHERE"	をセット
			*/ 
			if(	
				(dto.getShopName() != null && !dto.getShopName().isEmpty() ) ||
				(dto.getArea() != null && !dto.getArea().isEmpty() ) ||
				dto.getGenreid() != 0 || dto.getValue() != 0  ||
				(dto.getInputOpentime() != null && !dto.getInputOpentime().isEmpty() ) ||
				(dto.getInputOpendayStart() != null && !dto.getInputOpendayStart().isEmpty() ) ||
				(dto.getInputOpendayEnd() != null && !dto.getInputOpendayEnd().isEmpty() )
				) {
				sql += " WHERE";
			}
			
			// 店舗名が指定されていればLIKE '%店名%'をセット
			if(dto.getShopName() != null && !dto.getShopName().isEmpty()) {
				sql += " s.shop_name LIKE '%" + dto.getShopName() + "%'";
				andFlag = true;
			}
			
			// エリアが指定されていればLIKE '%エリア%'をセット
			if(dto.getArea() != null && !dto.getArea().isEmpty()) {
				if(andFlag == true) {
					sql += " AND";
				}
				sql += " s.area LIKE '%" + dto.getArea() + "%'";
				andFlag = true;
			}
			
			// ジャンル(ID)が指定されていれば" = ジャンルID"をセット
			if(dto.getGenreid() != 0) {
				if(andFlag == true) {
					sql += " AND";
				}
				sql += " s.genre_id = " + dto.getGenreid();
				andFlag = true;
			}
			
			// 評価(1～5)が指定されていれば" =(<=, >=) 評価"をセット
			if(dto.getValue() != 0) {
				if(andFlag == true) {
					sql += " AND";
				}
				sql += " s.value_id " + dto.getEquality() + " " + dto.getValue();
				andFlag = true;
			}
			
			// 営業時間が指定されていれば営業時間をセット
			// 営業時間が日付をまたぐか否かで検索条件を変更
			if(dto.getInputOpentime() != null && !dto.getInputOpentime().isEmpty()) {
				if(andFlag == true) {
					sql += " AND";
				}
				sql += " CASE WHEN (s.open_time <= s.close_time)"
					+ " THEN (s.open_time <= '" + dto.getInputOpentime() + "' AND '" + dto.getInputOpentime() + "' < s.close_time)"
					+ " ELSE ('" + dto.getInputOpentime() + "' < s.close_time OR s.open_time <= '" + dto.getInputOpentime() + "') END";
				andFlag = true;
			}
			
			// オープン日(検索開始、終了日いずれか)が指定されている場合
			if((dto.getInputOpendayStart() != null && !dto.getInputOpendayStart().isEmpty() ) ||
					(dto.getInputOpendayEnd() != null && !dto.getInputOpendayEnd().isEmpty() )) {
				if(andFlag == true) {
					sql += " AND";
				}
				// オープン日の検索開始日と終了日両方が指定されている場合
				if((dto.getInputOpendayStart() != null && !dto.getInputOpendayStart().isEmpty() ) &&
						(dto.getInputOpendayEnd() != null && !dto.getInputOpendayEnd().isEmpty() )) {
					sql += " s.open_day BETWEEN '" + dto.getInputOpendayStart() + "' AND '" + dto.getInputOpendayEnd() + "'";
				}
				
				// オープン日の検索開始日のみ指定されている場合
				if((dto.getInputOpendayStart() != null && !dto.getInputOpendayStart().isEmpty() ) &&
						(dto.getInputOpendayEnd() == null || dto.getInputOpendayEnd().isEmpty() )) {
					sql += " s.open_day >= '" + dto.getInputOpendayStart() + "'";
				}
				
				// オープン日の検索終了日のみ指定されている場合
				if((dto.getInputOpendayStart() == null || dto.getInputOpendayStart().isEmpty() ) &&
						(dto.getInputOpendayEnd() != null && !dto.getInputOpendayEnd().isEmpty() )) {
					sql += " s.open_day <= '" + dto.getInputOpendayEnd() + "'";
				}
			}
		}

		// データの並び順を設定。(shop_id昇順とする)
		sql += " ORDER BY s.shop_id";

/*		System.out.println("SQL(search):" + sql);
 */
		List<RamenDto> list = jdbcTemplate.query(
			sql, new RowMapper<RamenDto>() {
				public RamenDto mapRow(ResultSet rs, int rowNum) throws SQLException{
							
					RamenDto dto2 = new RamenDto();

					dto2.setShopid(rs.getInt("shop_id"));
					dto2.setShopName(rs.getString("shop_name"));
					dto2.setGenreName(rs.getString("genre_name"));
					dto2.setArea(rs.getString("area"));
					dto2.setOpenday(rs.getDate("open_day"));
					dto2.setOpentime(rs.getTime("open_time"));
					dto2.setClosetime(rs.getTime("close_time"));
					dto2.setValueLabel(rs.getString("value"));
					dto2.setRegisterdate(rs.getTimestamp("date_create"));
					dto2.setLastupdate(rs.getTimestamp("date_update"));
					dto2.setFilename(rs.getString("pict"));
							
					return dto2;
				}
			}
		);
				
		return list;
	}
	
	// 特定の店舗コードのラーメン店情報のみ取得する
	@Override
	public RamenDto detail(int shopid) throws Exception {
		String sql = "SELECT s.shop_id , s.shop_name , g.genre_name , s.area , s.open_day ,"
				+ " s.open_time , s.close_time , v.value , s.date_create , s.date_update,"
				+ "  s.pict  FROM t_shop AS s LEFT OUTER JOIN m_genre AS g ON s.genre_id = g.genre_id"
				+ " LEFT OUTER JOIN m_value AS v ON s.value_id = v.value_id"
				+ " WHERE s.shop_id = ?";
		
		List<RamenDto> list = jdbcTemplate.query(
				sql, new Object[] {shopid}, new RowMapper<RamenDto>() {
					public RamenDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						RamenDto dto = new RamenDto();

						dto.setShopid(rs.getInt("shop_id"));
						dto.setShopName(rs.getString("shop_name"));
						dto.setGenreName(rs.getString("genre_name"));
						dto.setArea(rs.getString("area"));
						dto.setOpenday(rs.getDate("open_day"));
						dto.setOpentime(rs.getTime("open_time"));
						dto.setClosetime(rs.getTime("close_time"));
						dto.setValueLabel(rs.getString("value"));
						dto.setRegisterdate(rs.getTimestamp("date_create"));
						dto.setLastupdate(rs.getTimestamp("date_update"));
						dto.setFilename(rs.getString("pict"));
						
						return dto;
					}
				}
			);
		
		return list.get(0);
	}

	// ラーメン店口コミ情報一覧を取得する
	@Override
	public List<RamenDto> reviewlist(int shopid) throws Exception {

		
		List<RamenDto> list = jdbcTemplate.query(
				SQL_REVIEWLIST, new Object[] { shopid }, new RowMapper<RamenDto>() {
					public RamenDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						RamenDto dto = new RamenDto();

						dto.setReviewid(rs.getInt("review_id"));				// 口コミID
						dto.setUserid(rs.getString("user_id"));				// ユーザーID
						dto.setValueLabel(rs.getString("value"));				// 評価
						dto.setReviewTitle(rs.getString("review_title"));		// 口コミタイトル
						
						dto.setReview(rs.getString("review"));					// 口コミ本文。
						dto.setReviewBr(nl2br(dto.getReview()));					//	口コミ本文。改行コードを<br>タグに変換。
						dto.setRegisterdate(rs.getTimestamp("date_create"));	// 口コミ投稿日
						dto.setLastupdate(rs.getTimestamp("date_update"));		// 口コミ更新日
						dto.setVisitday(rs.getDate("date_visit"));		// 口コミ更新日

						
						return dto;
					}
				}
			);
		
		return list;
	}

	// 特定口コミIDの口コミ情報を取得する
	@Override
	public RamenDto reviewinfo(int reviewid) throws Exception {

		
		List<RamenDto> list = jdbcTemplate.query(
				SQL_GETREVIEW, new Object[] { reviewid }, new RowMapper<RamenDto>() {
					public RamenDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						RamenDto dto = new RamenDto();

						dto.setReviewid(rs.getInt("review_id"));				// 口コミID
						dto.setUserid(rs.getString("user_id"));				// ユーザーID
						dto.setValueLabel(rs.getString("value"));				// 評価
						dto.setReviewTitle(rs.getString("review_title"));		// 口コミタイトル
						
						dto.setReview(rs.getString("review"));					// 口コミ本文。
						dto.setRegisterdate(rs.getTimestamp("date_create"));	// 口コミ投稿日
						dto.setLastupdate(rs.getTimestamp("date_update"));		// 口コミ更新日
						dto.setVisitday(rs.getDate("date_visit"));				// 来訪日
						
						return dto;
					}
				}
			);
		
		return list.get(0);
	}

	@Override
	public int reviewcount(RamenDto dto) throws Exception {
		// queryForMapメソッドでSQLを実行し、結果MapのListで受け取る。
		// SQL文の ? の部分に当てはめる値を一緒に与える。
		Map<String, Object> getCount 
				= jdbcTemplate.queryForMap(SQL_REVIEWCOUNT, new Object[]{ dto.getShopid(), dto.getUserid() });
        
		int count = ((Number) getCount.get("review_count")).intValue();
		
		return count;
	}
	
	// t_review内の、口コミIDを取得
	@Override
	public void getreviewid(RamenDto dto) throws Exception {
		// queryForMapメソッドでSQLを実行し、結果MapのListで受け取る。
		// SQL文の ? の部分に当てはめる値を一緒に与える。
		Map<String, Object> getCount 
				= jdbcTemplate.queryForMap(SQL_REVIEWID, new Object[]{ dto.getShopid(), dto.getUserid() });
        
		dto.setReviewid(((Number) getCount.get("review_id")).intValue());
	}
	
	// t_image内の、該当口コミIDに紐づいた画像ファイル名を一覧取得
	@Override
	public List<String> imagelist(int reviewid) throws Exception {
		List<String> filenames = jdbcTemplate.query(
				SQL_GETIMAGE, new Object[] { reviewid }, new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getString("filename");
					}
				}
			);
		
		return filenames;
	}
	
	// 条件で指定した店舗に投稿された全口コミ内の画像ファイル名を一覧取得
	@Override
	public List<String> imagelistofshop(int shopid) throws Exception {
		List<String> filenames = jdbcTemplate.query(
				SQL_GETALLIMAGE, new Object[] { shopid }, new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException{
						return rs.getString("filename");
					}
				}
			);
		
		return filenames;
	}
	
	// ラーメン店情報を新規登録
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int insert(RamenDto dto) throws Exception {
		return jdbcTemplate.update(SQL_INSERT, 
				new Object[] {dto.getShopName(), dto.getGenreid(), dto.getArea(), dto.getOpenday(),
						dto.getOpentime(), dto.getClosetime(), dto.getValue(), dto.getFilename()});

	}
	
	// ラーメン店の口コミを新規登録
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int insertreview(RamenDto dto) throws Exception {
		return jdbcTemplate.update(SQL_INSERTREVIEW, 
				new Object[] {dto.getShopid(), dto.getUserid(), dto.getValue(), dto.getReviewTitle(),  dto.getReview(), dto.getVisitday()});

	}

	// t_imageテーブルに口コミID、画像ファイル名を挿入
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int insertimage(RamenDto dto)  throws Exception {
		return jdbcTemplate.update(SQL_INSERTIMAGE, new Object[] {dto.getReviewid(), dto.getFilename()});
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int update(RamenDto dto) throws Exception {
		// ファイル名が空の場合
		if(dto.getFilename() == null || dto.getFilename().isEmpty()) { // ファイル名を変更しない場合
			return jdbcTemplate.update(SQL_UPDATE, 
					new Object[] {dto.getShopName(), dto.getGenreid(), dto.getArea(), dto.getOpenday(),
							dto.getOpentime(), dto.getClosetime(), dto.getValue(), dto.getShopid() });
		} else { // ファイル名を変更する場合
			return jdbcTemplate.update(SQL_UPDATE_WITH_FILE, 
				new Object[] {dto.getShopName(), dto.getGenreid(), dto.getArea(), dto.getOpenday(),
						dto.getOpentime(), dto.getClosetime(), dto.getValue(), dto.getFilename(),
						dto.getShopid() });
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int updatereview(RamenDto dto) throws Exception {
		return jdbcTemplate.update(SQL_UPDATEREVIEW, 
				new Object[] {dto.getValue(), dto.getReviewTitle(),  dto.getReview(), dto.getVisitday(), dto.getReviewid()});
	}
	
	// t_shopからラーメン店情報を削除
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int delete(int shopid) throws Exception {
		return jdbcTemplate.update(SQL_DELETE, shopid);
	}

	// t_reviewから口コミ情報を削除
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int deletereview(int reviewid) throws Exception {
		return jdbcTemplate.update(SQL_DELETEREVIEW, reviewid);
	}

	// t_reviewから該当店舗の全口コミ情報を削除
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int deleteallreview(int shopid) throws Exception {
		return jdbcTemplate.update(SQL_DELETEALLREVIEW, shopid);
	}
	
	// t_imageから該当口コミIDに紐づいた画像ファイル情報を削除
	
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int deleteimage(int reviewid) throws Exception {
		return jdbcTemplate.update(SQL_DELETEIMAGE, reviewid);
	}
	
	// t_imageから該当店舗コードに紐づいた画像ファイル情報を削除
	
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int deleteallimage(int shopid) throws Exception {
		return jdbcTemplate.update(SQL_DELETEALLIMAGE, shopid);
	}
	
	/**
	 * 改行コードを<br />タグに変換した情報を返却する。<br>
	 * @param s 入力文字列
	 * @return 変換後の文字列を返却します。
	 */
	public static String nl2br(String s) {
	    return nl2br(s, true);
	}

	/**
	 * 改行コードを<br />、または、<br>タグに変換した情報を返却する。<br>
	 * @param s 入力文字列
	 * @param is_xhtml XHTML準拠の改行タグの使用する場合はtrueを指定します。
	 * @return 変換後の文字列を返却します。
	 */
	public static String nl2br(String s, boolean is_xhtml) {
	    if (s == null || "".equals(s)) {
	        return "";
	    }
	    String tag = is_xhtml ? "<br />" : "<br>";
	    return s.replaceAll("\\r\\n|\\n\\r|\\n|\r", tag);
	}

}
