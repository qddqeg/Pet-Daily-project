package per.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import per.common.UtilEtc;
import per.user.UserSvc;


@Controller
public class MemberCtr {
	@Autowired
	private UserSvc userSvc;
	
	@Autowired
	private MemberSvc memberSvc;
	
	 @RequestMapping(value = "/memberForm")
	    public String memberForm(HttpServletRequest request, ModelMap modelMap) {
		 System.out.println("memberform aaaaaaaaaaaaaaaaaaaaaaaa");
	        String save = request.getParameter("save");

	        String userno = request.getSession().getAttribute("userno").toString();
	        
	        UserVO userInfo = userSvc.selectUserOne(userno);
	        
	        modelMap.addAttribute("userInfo", userInfo);
	        modelMap.addAttribute("save", save);
	        
	        return "member/memberForm";
	    }
	    
	    /**
	     * 사용자 저장.
	     */
	    @RequestMapping(value = "/userSave")
	    public String userSave(HttpServletRequest request, ModelMap modelMap, UserVO userInfo) {
	        String userno = request.getSession().getAttribute("userid").toString();
	        userInfo.setUserID(userno);
	        

	        userSvc.updateUserByMe(userInfo);

	        return "redirect:/memberForm?save=OK";
	    }
	    
	    /**
	     * 비밀번호 변경.
	     */
	    @RequestMapping(value = "/changePWSave")
	    public void changePWSave(HttpServletRequest request, HttpServletResponse response, UserVO userInfo) {
	        String userno = request.getSession().getAttribute("userno").toString();
	        userInfo.setUserID(userno);
	        
	        userSvc.updateUserPassword(userInfo);

	        UtilEtc.responseJsonValue(response,"OK");
	    }
}
