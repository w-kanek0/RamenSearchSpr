package pack.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.LoginDao;
import dto.LoginDto;

@Repository
public class LoginDaoImpl implements LoginDao {

	//JdbcTemplateクラスをAutowiredでDI
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL
	// 入力されたIDとパスワードの組み合わせが存在するか確認するSQL
	private final String SQL_SELECT_BY_ID_AND_PASS = 
		"SELECT user_id, password, username, email FROM m_user WHERE user_id = ? and password = ?";

	// 登録しようとしているユーザーが取得済みか確認するSQL
	private final String SQL_COUNT_USER = "SELECT count(*) as user_count FROM m_user WHERE user_id = ?";
	
	// 登録しようとしているユーザーが取得済みか確認するSQL
	private final String SQL_COUNT_EMAIL = "SELECT count(*) as email_count FROM m_user WHERE email = ?";

	// ユーザー情報を新規登録するSQL
	private final String SQL_INSERT_USER = "INSERT INTO m_user (user_id, password, username, email)"
			+ " VALUES (?, ?, ?, ?)";
	
	
	// ログインチェック用メソッド
	@Override
	public LoginDto logincheck(String userid, String password) throws Exception {
		List<LoginDto> userList = jdbcTemplate.query(
			SQL_SELECT_BY_ID_AND_PASS, new Object[] {userid, password}, new RowMapper<LoginDto>() {
				public LoginDto mapRow(ResultSet rs, int rowNum) throws SQLException{
					LoginDto dto = new LoginDto();
					
					dto.setUserid(rs.getString("user_id"));
					dto.setPassword(rs.getString("password"));
					dto.setUsername(rs.getString("username"));
					dto.setEmail(rs.getString("email"));
					
					return dto;
				}
			}
		);
		
		// userListが空の場合(一致する情報がない場合)Null, 空でない場合LoginDtoを返す
		return userList.isEmpty() ? null: userList.get(0);
	}
	
	// 登録しようとしているユーザーが存在するか確認
	@Override
	public int usercheck(String userid) throws Exception {
		// queryForMapメソッドでSQLを実行し、結果MapのListで受け取る。
		// SQL文の ? の部分に当てはめる値を一緒に与える。
		Map<String, Object> getCount 
				= jdbcTemplate.queryForMap(SQL_COUNT_USER, new Object[]{ userid });

		int count = ((Number) getCount.get("user_count")).intValue();
		
		return count;
	}
	
	// 登録しようとしているメールアドレスが存在するか確認
	@Override
	public int emailcheck(String email) throws Exception {
		// queryForMapメソッドでSQLを実行し、結果MapのListで受け取る。
		// SQL文の ? の部分に当てはめる値を一緒に与える。
		Map<String, Object> getCount 
				= jdbcTemplate.queryForMap(SQL_COUNT_EMAIL, new Object[]{ email });

		int count = ((Number) getCount.get("email_count")).intValue();
		
		return count;
	}
	
	// ユーザー情報をm_userテーブルに登録
	@Override
	@Transactional(rollbackFor = Exception.class)	// 例外が発生したら処理をロールバック
	public int insertuser(LoginDto dto) throws Exception {
		return jdbcTemplate.update(SQL_INSERT_USER, 
				new Object[] {dto.getUserid(), dto.getPassword(), dto.getUsername(),  dto.getEmail()});

	}
}
