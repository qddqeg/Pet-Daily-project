package per.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import per.common.FileVO;

@Service
public class BoardSvc {

	private DataSourceTransactionManager dtm;
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public Integer selectBoardCount(BoardSearchVO param) { // board row 카운트
		
		return sqlSession.selectOne("selectBoardCount",param);
	}
	
	public List<?> selectBoardList(BoardSearchVO param){ // board list 출력
		
		return sqlSession.selectList("selectBoardList",param);
	}

	public void insertContent(BoardVO bv, List<FileVO> fl, String[] fileno) {
		DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
		TransactionStatus ts = dtm.getTransaction(dtd);
		String getID =Integer.toString(bv.getId());
		
		sqlSession.insert("insertcontent", bv);
		
		for(FileVO f : fl) {
			f.setParentPK(getID);
			sqlSession.insert("insertFile", f);
		}
		
	}

	public BoardVO SelectDetail(String board_id) {
		
		return sqlSession.selectOne("selectDetail",board_id);
	}

	public void insertComment(CommentVO cv) {
		
		sqlSession.insert("insertComment", cv);
		
	}

	public List<?> selectCommentList(String board_id) {
		
		return sqlSession.selectList("selectCommentList", board_id);
	}

	public void boardDetailRemove(String board_id) {
		
		sqlSession.delete("DeleteBoardDetail",board_id);
	}

	public void DeleteComment(String comment_id) {
		
		sqlSession.delete("DeleteComment",comment_id);
		
	}
	
//	public BoardVO selectBoardRead(String brdno){
//		
//		return sqlSession.selectOne("selectBoardRead",brdno);
//	}
}
