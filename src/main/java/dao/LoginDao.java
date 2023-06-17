package dao;

import dto.LoginDto;

public interface LoginDao {
	
	// 
	public LoginDto logincheck(String userid, String password) throws Exception;
	public int usercheck(String userid) throws Exception;
	public int emailcheck(String email) throws Exception;
	public int insertuser(LoginDto dto) throws Exception;
	
}
