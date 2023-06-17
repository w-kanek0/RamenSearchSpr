package cnt;

public class Constant {
	/******** ↓キー **********/
	/** データベース接続 **/
	// PostgreSQLの場合
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
	public static final String JDBC_USER = "postgres";
	public static final String JDBC_PASS = "m0squ1t0gat0";

	// MySQLの場合
/*	
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String JDBC_CONNECTION = "jdbc:mysql://localhost:3306/ramenDB";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PASS = "m0squ1t0gat0";
*/
	
	// ラーメン店情報登録時のサムネイル画像のアップロード先
	public static final String UPLOAD_THUMBNAIL_PATH = "C:\\workspaceEE\\RamenSearchSpr\\src\\main\\webapp\\static\\upload\\thumbnail\\";
	
	// 口コミ投稿時の画像のアップロード先
	public static final String UPLOAD_REVIEW_PATH = "C:\\workspaceEE\\RamenSearchSpr\\src\\main\\webapp\\static\\upload\\review\\";
	
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
