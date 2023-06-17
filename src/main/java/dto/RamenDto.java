package dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class RamenDto {

	private int shopid;					// 店舗コード
	private String shopName;			// 店舗名
	private int genreid;				// ジャンルID
	private String genreName;			// ジャンル名
	private String area;				// エリア
	private Date openday;			// オープン日
	private Time opentime;				// 開店時間
	private Time closetime;				// 閉店時間
	private int value;					// 評価(1～5)
	private String valueLabel;			// 評価(☆～☆☆☆☆☆)
	private String equality;			// 等号(=)、不等号(<=, >=)を格納
	private Timestamp registerdate;		// 登録日時
	private Timestamp lastupdate;		// 最終更新日時
	private String filename = "";		// 写真ファイル名
	
	private String userid;					// ユーザーID
	private int reviewid;				// レビューID
	private Date visitday;				// 来訪日
	private String reviewTitle;			// レビュータイトル
	private String review;				// レビュー内容
	private String reviewBr;				// レビュー内容(改行コードを<br>タグに変換)
	private int reviewCount;				// 自ユーザーがお店に投稿したレビュー件数。
	
	private int fileid;					// 写真ファイルID
	private List<String> filenames;		// 写真ファイル名
	
	private String inputOpenday;		// 登録、更新画面から入力されたオープン日
	private String inputOpendayStart;		// 検索画面から入力された検索開始オープン日
	private String inputOpendayEnd;		// 検索画面から入力された検索終了オープン日
	private String inputOpentime;		// 登録、更新画面から入力された開店時間
	private String inputClosetime;		// 登録、更新画面から入力された閉店時間
	private String inputVisitday;		// 登録、更新画面から入力された来訪日
	private String returnMsg;			// 処理が無事完了したか確認するメッセージ。
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getGenreid() {
		return genreid;
	}
	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getOpenday() {
		return openday;
	}
	public void setOpenday(Date openday) {
		this.openday = openday;
	}
	public Time getOpentime() {
		return opentime;
	}
	public void setOpentime(Time opentime) {
		this.opentime = opentime;
	}
	public Time getClosetime() {
		return closetime;
	}
	public void setClosetime(Time closetime) {
		this.closetime = closetime;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getValueLabel() {
		return valueLabel;
	}
	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}
	public String getEquality() {
		return equality;
	}
	public void setEquality(String equality) {
		this.equality = equality;
	}
	public Timestamp getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(Timestamp registerdate) {
		this.registerdate = registerdate;
	}
	public Timestamp getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public Date getVisitday() {
		return visitday;
	}
	public void setVisitday(Date visitday) {
		this.visitday = visitday;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReviewBr() {
		return reviewBr;
	}
	public void setReviewBr(String reviewBr) {
		this.reviewBr = reviewBr;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public List<String> getFilenames() {
		return filenames;
	}
	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}
	public String getInputOpenday() {
		return inputOpenday;
	}
	public void setInputOpenday(String inputOpenday) {
		this.inputOpenday = inputOpenday;
	}
	public String getInputOpendayStart() {
		return inputOpendayStart;
	}
	public void setInputOpendayStart(String inputOpendayStart) {
		this.inputOpendayStart = inputOpendayStart;
	}
	public String getInputOpendayEnd() {
		return inputOpendayEnd;
	}
	public void setInputOpendayEnd(String inputOpendayEnd) {
		this.inputOpendayEnd = inputOpendayEnd;
	}
	public String getInputOpentime() {
		return inputOpentime;
	}
	public void setInputOpentime(String inputOpentime) {
		this.inputOpentime = inputOpentime;
	}
	public String getInputClosetime() {
		return inputClosetime;
	}
	public void setInputClosetime(String inputClosetime) {
		this.inputClosetime = inputClosetime;
	}
	public String getInputVisitday() {
		return inputVisitday;
	}
	public void setInputVisitday(String inputVisitday) {
		this.inputVisitday = inputVisitday;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	
}
