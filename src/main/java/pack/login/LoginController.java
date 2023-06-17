package pack.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cnt.Constant;
import dto.LoginDto;
import pack.ramen.RamenController;

@Transactional
@Controller
public class LoginController {
	
	// ユーザー情報テーブル用サービス
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RamenController ramenController;
	
	// セッションに接続
	@Autowired
	HttpSession session;
	
	@RequestMapping("/Login")
	public String login(Model model, @ModelAttribute("loginForm")LoginForm loginForm) {
		
		// ログインIDを取得
		String userid = loginForm.getUserid();
				
		// パスワードを取得
		String password = loginForm.getPassword();
		
		LoginDto dto = null;
		try {
			// 入力されたユーザーID、パスワードが登録済みか確認。
			dto = loginService.findByIdAndPass(userid, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// ユーザーIDが未登録、またはIDかパスワードが間違っていた場合
		if(dto == null) {
			model.addAttribute("errorMsg", Constant.WRONG_USERID_PASS);
			return "jsp/login";
		} else {
			session.setAttribute("user", dto);
			return ramenController.ramenlistall(model);
		}
	}
	
	@RequestMapping("/Logout")
	public String logout(Model model) {
		LoginDto dto = (LoginDto)session.getAttribute("user");	// 一旦セッションを取得
		session.invalidate();	// セッションをクリア
		
		model.addAttribute("errorMsg", Constant.LOGOUT_COMPLETE);
		
		// ログイン画面に戻る
		return "jsp/login";
	}
	
	@RequestMapping("/Signup")
	public String signup(Model model) {
		// ユーザー登録画面を呼び出す
		return "jsp/signup";
	}
	
	@RequestMapping("/RegisterUser")
	public String registeruser(Model model, @ModelAttribute("RegisterUserForm")LoginForm loginForm) {

		LoginDto dto = null;
		
		// ユーザー名が空の場合
		if(loginForm.getUsername() == null) {
			dto = new LoginDto(loginForm.getUserid(), loginForm.getPassword(), loginForm.getEmail());
		} else {
			dto = new LoginDto(loginForm.getUserid(), loginForm.getPassword(), loginForm.getUsername(), loginForm.getEmail());
		}
		
		try {
			// 登録しようとしているユーザーが既に登録済みか確認
			if(loginService.checkIfUserRegistered(dto.getUserid()) > 0) {
				model.addAttribute("errorMsg", Constant.USER_ALREADY_REGISTERED);
				return "jsp/signup";
			}
			
			// 登録しようとしているメールアドレスが既に登録済みか確認
			if(loginService.checkIfEmailRegistered(dto.getEmail()) > 0) {
				model.addAttribute("errorMsg", Constant.EMAIL_ALREADY_REGISTERED);
				return "jsp/signup";
			}
			// ユーザー登録実施
			loginService.insertUserInfo(dto);
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", Constant.USER_REGISTER_ERROR);
			return "jsp/signup";
		}
		
		// ユーザー登録完了画面を呼び出す
		return "jsp/confirmsignup";
	}
}
