package per.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.member.UserVO;

@Service
public class UserSvc {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void updateUserByMe(UserVO param) {
		sqlSession.delete("updateUserByMe", param);
	}
	
	public void updateUserPassword(UserVO param) {
		sqlSession.delete("updateUserPassword",param);
	}
	
	/*
	 * 사용자
	 */
	 public UserVO selectUserOne(String param) {
		 return sqlSession.selectOne("selectUserOne", param);
	 }
}
