package pack.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LoginDao;
import dto.LoginDto;

@Service
public class LoginService {
	@Autowired
	private LoginDao dao;

	/**
	 * 該当の店舗及びユーザーで口コミが登録されている件数を取得(二重登録防止のため)
	 * @param userid, password
	 * @return LoginDaoクラスのlogincheckの戻り値
	 * @throws Exception
	 */	
	public LoginDto findByIdAndPass(String userid, String password) throws Exception {
		return dao.logincheck(userid, password);
	}
	
	/**
	 * ユーザー登録時指定のユーザーIDが登録済みか確認
	 * @param userid
	 * @return LoginDaoクラスのusercheckの戻り値
	 * @throws Exception
	 */	
	public int checkIfUserRegistered(String userid) throws Exception {
		return dao.usercheck(userid);
	}
	
	/**
	 * ユーザー登録時指定のメールアドレスが登録済みか確認
	 * @param userid
	 * @return LoginDaoクラスのusercheckの戻り値
	 * @throws Exception
	 */	
	public int checkIfEmailRegistered(String email) throws Exception {
		return dao.emailcheck(email);
	}
	
	/**
	 * ユーザー登録を実施
	 * @param LoginDto
	 * @return LoginDaoクラスのinsertuserの戻り値
	 * @throws Exception
	 */	
	public int insertUserInfo(LoginDto dto) throws Exception {
		return dao.insertuser(dto);
	}
}
