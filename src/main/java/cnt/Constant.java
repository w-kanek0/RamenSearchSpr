package cnt;

public class Constant {

	
	// ラーメン店情報登録時のサムネイル画像のアップロード先
	public static final String UPLOAD_THUMBNAIL_PATH = "/opt/apache-tomcat/webapps/RamenSearch/static/upload/thumbnail/";
	
	// 口コミ投稿時の画像のアップロード先
	public static final String UPLOAD_REVIEW_PATH = "/opt/apache-tomcat/webapps/RamenSearch/static/upload/review/";
	
	// ユーザー登録エラー時のメッセージ
	// 登録済みの場合
	public static final String USER_ALREADY_REGISTERED = "このユーザーIDは登録済みです。";
	public static final String EMAIL_ALREADY_REGISTERED = "このメールアドレスは登録済みです。";
	public static final String USER_REGISTER_ERROR = "このユーザーIDの登録に失敗しました。";
	
	// ログインエラー時のメッセージ
	public static final String WRONG_USERID_PASS = "ユーザーIDまたはパスワードが違います。";
	public static final String  LOGIN_ERROR = "ログインに失敗しました。";	// ユーザー認証時に例外が発生した場合
	
	// ログアウト時のメッセージ
	public static final String LOGOUT_COMPLETE = "ログアウトされました。";
}
