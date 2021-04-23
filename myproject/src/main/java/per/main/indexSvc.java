package per.main;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class indexSvc {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<?> selectRecentNews(String sql){
		return sqlSession.selectList("selectRecentNews",sql);
	}

	public imgVO SelectView(String pid) {
		
		return sqlSession.selectOne("selectView", pid);
	}

	public List<?> imgCommentList(String pid) {
		
		return sqlSession.selectList("imgCommentList", pid);
	}

	public void insert_imgComment(img_commentVO icv) {
		
		sqlSession.insert("insert_imgComment",icv);
	}
}
