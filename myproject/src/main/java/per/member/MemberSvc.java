package per.member;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;    
        
    
    public UserVO selectMember4Login(LoginVO param) {
        return sqlSession.selectOne("selectMember4Login", param);
    }
    
    public void insertMemberSignUp(UserVO param) {
    	sqlSession.insert("insertMemberSingUp", param);
    }

	public int idCheck(String userID) {
		
		return sqlSession.selectOne("idCheck", userID);
	}

//    public void insertLogIn(String param) {
//        sqlSession.insert("insertLogIn", param);
//    }
//
//    public void insertLogOut(String param) {
//        sqlSession.insert("insertLogOut", param);
//    }
    
//    public UserVO searchMember(String usernm) {
//    	return sqlSession.selectOne("searchMember",usernm);
//    }
}
