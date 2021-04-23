package per.board;

import java.util.List;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import per.common.FileUtil;
import per.common.FileVO;



@Controller
public class BoardCtr {

	@Autowired
	private BoardSvc boardSvc;
	
	//게시판 글 리스트 보기 
	@RequestMapping(value="boardList")
	public String BoardList(HttpServletRequest req, BoardSearchVO bo, ModelMap modelMap) {
		
		
//		String userno = req.getSession().getAttribute("userid").toString();
//		System.out.println("userno : " + userno);
		
		bo.pageCalculate(boardSvc.selectBoardCount(bo));
	
		List<?> listView = boardSvc.selectBoardList(bo);
		
		
		
		modelMap.addAttribute("bo",bo);
		modelMap.addAttribute("lv",listView);
		
		
		return "board/boardList";
	}
	//게시판 글쓰기 페이지이동 
	@RequestMapping(value="test_writing")
	public String BoardWrite() {
		System.out.println("컨트롤 들어옴 !!!!!");
		return "board/test_writing";
	}
	
	//게시판 글저장 
	@RequestMapping(value="boardSave")
	public String BoardSave(HttpServletRequest req, BoardVO bv) {
		
		String userno = req.getSession().getAttribute("userid").toString();
		bv.setWriter_id(userno);
		
		String fileno[] = req.getParameterValues("fileno");
		FileUtil fs = new FileUtil();
		List<FileVO> fl = fs.saveAllFiles(bv.getUploadfile());
		
		
		for(int i=0; i<fl.size(); i++) {
			System.out.println("fileList :"+ fl.toString());
		}
		
		boardSvc.insertContent(bv, fl, fileno);
		return "board/boardList";
		
	}
	//게시판 디테일 글 보기 및 댓글 보기 
	@RequestMapping(value = "boardDetail" )
    public String boardRead(HttpServletRequest request, ModelMap modelMap, BoardVO bv, CommentVO cv) {
		
        String board_id = request.getParameter("boardid");
        System.out.println("boarde id : "+board_id);
        bv=boardSvc.SelectDetail(board_id);
        List<?> CommentView = boardSvc.selectCommentList(board_id);
        System.out.println("여기는 디테일컨트롤러~~~~~11111111111");
        modelMap.addAttribute("bv",bv);
        modelMap.addAttribute("cl",CommentView);
        return "board/boardDetail";
    }
	//게시판 디테일 댓글 입력  
	@RequestMapping(value ="boardComment")
	public String boardComment(HttpServletRequest request, ModelMap modelMap, CommentVO cv) {
		System.out.println("여기는 댓글을 입력하는 컨트롤러~~~~11111111111");
		boardSvc.insertComment(cv);
		modelMap.addAttribute("cv",cv);
		return "redirect:/boardDetail?boardid="+cv.getMid();
	}
	//게시판 디테일 삭제
	@RequestMapping(value = "remove")
	public String boardDetailRemove(HttpServletRequest request, ModelMap modelMap) {
		 String board_id = request.getParameter("boardid");
		 System.out.println("board_id : "+ board_id);
		 boardSvc.boardDetailRemove(board_id);
		return "redirect:/boardList";
	}
	//댓글 삭제
	@RequestMapping(value = "Commnetremove")
	public String CommentDelete(HttpServletRequest request, ModelMap modelMap, CommentVO cv) {
		String comment_id = request.getParameter("id");
		String Detail_id = request.getParameter("mid");
		System.out.println("Comment_id : " + comment_id);
		boardSvc.DeleteComment(comment_id);
		modelMap.addAttribute("Comment_remove", "바르고 고운말 사용합시다(댓글삭제 완료)");
		modelMap.addAttribute("Detail_id", Detail_id);
		
		return "common/msg_Comment_remove";
	}
}
