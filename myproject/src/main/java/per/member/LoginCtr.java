package per.member;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import per.board.BoardSearchVO;



@Controller
public class LoginCtr {    
    private static final Integer cookieExpire = 60 * 60 * 24 * 30; // 1 month
    
    @Autowired
    private MemberSvc memberSvc;
    
    /**
     * 로그인화면.
     */
    @RequestMapping(value = "login")
    public String memberLogin(HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException{
//        String    userid = get_cookie("sid", request);    
//        modelMap.addAttribute("userid", userid);
    	
//  이거슨 Naver로그인 URL 전송 하는곳
    	SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String N_clientId = "rlFR0rpeR2g_ZZhqAhdl";
		String N_redirectURI = URLEncoder.encode("http://localhost:8080/myproject/N_callback", "UTF-8");
//ex) http://localhost:8080/Trade_Project/N_callback
		String N_apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		N_apiURL += "&client_id=" + N_clientId;
		N_apiURL += "&redirect_uri=" + N_redirectURI;
		N_apiURL += "&state=" + state;
		HttpSession session = request.getSession();
		session.setAttribute("state", state);

		modelMap.addAttribute("N_apiURL", N_apiURL);
    	
		
	//  이거슨 Kakao 로그인 URL 전송 하는곳
		String K_clientId = "ac428383bebd48ab6b11875b0217d9c9";
		String K_redirectURI = URLEncoder.encode("http://localhost:8080/myproject/K_callback", "UTF-8");
		String K_apiURL = "https://kauth.kakao.com/oauth/authorize?response_type=code";
		K_apiURL += "&client_id=" + K_clientId;
		K_apiURL += "&redirect_uri=" + K_redirectURI;
		modelMap.addAttribute("K_apiURL", K_apiURL);
		
        return "member/login";
    }
    
    /**
     * 로그인 처리.
     */
    @RequestMapping(value = "memberLoginChk")
    public String memberLoginChk(HttpServletRequest request,HttpServletResponse response, LoginVO loginInfo, ModelMap modelMap) {

        UserVO mdo = memberSvc.selectMember4Login(loginInfo);
        
        if (mdo ==  null) {
            modelMap.addAttribute("msg_login_x", "ID 또는 패스워드가 틀렸습니다.");
            return "common/msg_login_x";
        }else{
//        memberSvc.insertLogIn(mdo.userID); //로그인 누가 나갔고 들어왔는지 관리를 하기위한 구문 
        
        HttpSession session = request.getSession();
        
        session.setAttribute("userid", mdo.userID);
        session.setAttribute("userpw",mdo.userPASS);
        session.setAttribute("userEmail", mdo.userEmail);
        session.setAttribute("userName", mdo.userName);
        
        modelMap.addAttribute("msg_login_o", mdo.userName+"님 환영합니다.");
        
        return "common/msg_login_o";
        }
       
    }   
    
    /**
     * 로그아웃.
     */
    @RequestMapping(value = "Logout")
    public String memberLogout(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession();
        
//        String userno = session.getAttribute("userno").toString();
        
//        memberSvc.insertLogOut(userno); //로그인 누가 나갔고 들어왔는지 관리를 하기위한 구문
        modelMap.addAttribute("msg_logout", "로그아웃 되셨습니다.");
        session.removeAttribute("userid"); 
        session.removeAttribute("userrole");        
        session.removeAttribute("userno");        
        session.removeAttribute("usernm");
        
      
        return "common/msg_logout";
    }
//    //사용자 조회
//    @RequestMapping(value ="searchMember")
//    public String searchMember(HttpServletRequest request, BoardSearchVO bo,ModelMap modelMap) {
//    	HttpSession session = request.getSession();
//    	return "redirect:/searchMember";
//    }
    
    /** 
     * 사용자가 관리자페이지에 접근하면 오류 출력.
     */
//    @RequestMapping(value = "noAuthMessage")
//    public String noAuthMessage(HttpServletRequest request) {
//        return "common/noAuth";
//    }
  
    /*
     * -------------------------------------------------------------------------
     */
    
    
    
//    /**
//     * 쿠키 저장.     
//     */
//    public static void set_cookie(String cid, String value, HttpServletResponse res) {
//
//        Cookie ck = new Cookie(cid, value);
//        ck.setPath("/");
//        ck.setMaxAge(cookieExpire);
//        res.addCookie(ck);        
//    }

//    /**
//     * 쿠키 가져오기.     
//     */
//    public static String get_cookie(String cid, HttpServletRequest request) {
//        String ret = "";
//
//        if (request == null) {
//            return ret;
//        }
//        
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return ret;
//        }
//        
//      for (Cookie ck : cookies) {
//           if (ck.getName().equals(cid)) {
//                ret = ck.getValue();
//                
//               ck.setMaxAge(cookieExpire);
//                break; 
//            }
//          }
//        return ret; 
//    }

}
