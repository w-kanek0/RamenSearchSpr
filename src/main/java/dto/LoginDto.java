package dto;

import java.io.Serializable;

public class LoginDto implements Serializable {
	private String userid = null;		// ユーザーID
	private String password = null;		// パスワード
	private String username = "";		// ユーザー名
	private String email = null;		// メールアドレス
	
	
	public LoginDto() {}
	
	// ユーザー名がない場合(ユーザー名は任意のため)
	public LoginDto(String userid, String password, String email){
		this.userid = userid;
		this.password = password;
		this.email = email;
	}
	
	// ユーザー名がある場合
	public LoginDto(String userid, String password, String username, String email){
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.email = email;
	}

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
