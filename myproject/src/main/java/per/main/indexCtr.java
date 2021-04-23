package per.main;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class indexCtr {

	@Autowired
	private indexSvc indexSvc;
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest req, ModelMap modelMap, imgVO imgVO) {
//		String userno = req.getSession().getAttribute("userid").toString();
		
		String sql = "";
		String category=req.getParameter("category"); 
		System.out.println("category : "+category);
		if(category != null && !category.equals("")) {
			if(category.equals("1")) {
				sql="where content like '%견'";
				
			}else if(category.equals("2") ) {
				sql="where category ='4'";
			}
		}
		System.out.println("sql : " +sql);
		List<?> listView = indexSvc.selectRecentNews(sql);
		modelMap.addAttribute("lv",listView);
	
		return "main/index";
	}
	//이미지 디테일 보기 및 댓글 보기 
	@RequestMapping(value = "view")
	public String view(HttpServletRequest request, ModelMap modelMap, imgVO iv) {
		String pid = request.getParameter("pid");
		System.out.println("pid : "+pid);
		iv=indexSvc.SelectView(pid);
		List<?> CommentView = indexSvc.imgCommentList(pid);

	    modelMap.addAttribute("commetlist",CommentView);
		modelMap.addAttribute("ph",iv);
		return "main/view";
	}
	//이미지 댓글 입력 
	@RequestMapping(value ="imgComment")
	public String imgComment(HttpServletRequest request, ModelMap modelMap, img_commentVO icv) {
		indexSvc.insert_imgComment(icv);
		modelMap.addAttribute("icv",icv);
		
		return "redirect:/view?pid="+icv.getPid();
	}
}