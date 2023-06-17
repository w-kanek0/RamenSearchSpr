package pack.login;

public class LoginForm {
	private String userid = null;		// ユーザーID
	private String password = null;		// パスワード
	private String username = null;		// ユーザー名
	private String email = null;		// メールアドレス

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
