package pack.ramen;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Handles requests for the application home page.
 *
 */
public class RamenForm {

	private String shopid;					// 店舗コード
	private String name;			// 店舗名
	private String s_name;			// 店舗名(検索条件指定用)
	private String genre;				// ジャンルID
	private String s_genre;				// ジャンルID(検索条件指定用)
	private String area;				// エリア
	private String s_area;				// エリア検索条件指定用)
	private String value;					// 評価(1～5)
	private String s_value;					// 評価(1～5)検索条件指定用)
	private String equality;			// 等号(=)、不等号(<=, >=)を格納
	private MultipartFile uploadfile = null;	// 写真ファイル情報(アップロード処理に必要)
	
	private String userid;				// ユーザーID
	private String reviewid;				// レビューID
	private String reviewtitle;			// レビュータイトル
	private String review;				// レビュー内容
	
	private List<MultipartFile> uploadfiles = null;	// 口コミに投稿する写真ファイル情報(複数あり。アップロード処理に必要)
	
	
	private String 	opentime;			// 開店時間
	private String 	s_opentime;			// 開店時間(検索条件指定用)
	private String 	closetime;			// 閉店時間
	private String 	opendate;			// オープン日
	private String 	s_opendatestart;	// オープン日(検索範囲開始)
	private String 	s_opendateend;		// オープン日(検索範囲終了)
	private String visitday;			// 来訪日
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getS_genre() {
		return s_genre;
	}
	public void setS_genre(String s_genre) {
		this.s_genre = s_genre;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getS_area() {
		return s_area;
	}
	public void setS_area(String s_area) {
		this.s_area = s_area;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getS_value() {
		return s_value;
	}
	public void setS_value(String s_value) {
		this.s_value = s_value;
	}
	public String getEquality() {
		return equality;
	}
	public void setEquality(String equality) {
		this.equality = equality;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getReviewid() {
		return reviewid;
	}
	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}
	public String getReviewtitle() {
		return reviewtitle;
	}
	public void setReviewtitle(String reviewtitle) {
		this.reviewtitle = reviewtitle;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public List<MultipartFile> getUploadfiles() {
		return uploadfiles;
	}
	public void setUploadfiles(List<MultipartFile> uploadfiles) {
		this.uploadfiles = uploadfiles;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getS_opentime() {
		return s_opentime;
	}
	public void setS_opentime(String s_opentime) {
		this.s_opentime = s_opentime;
	}
	public String getClosetime() {
		return closetime;
	}
	public void setClosetime(String closetime) {
		this.closetime = closetime;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getS_opendatestart() {
		return s_opendatestart;
	}
	public void setS_opendatestart(String s_opendatestart) {
		this.s_opendatestart = s_opendatestart;
	}
	public String getS_opendateend() {
		return s_opendateend;
	}
	public void setS_opendateend(String s_opendateend) {
		this.s_opendateend = s_opendateend;
	}
	public String getVisitday() {
		return visitday;
	}
	public void setVisitday(String visitday) {
		this.visitday = visitday;
	}
	
	
}
