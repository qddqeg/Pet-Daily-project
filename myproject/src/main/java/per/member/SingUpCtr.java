package per.member;



import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.faces.annotation.RequestMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SingUpCtr {
	@Autowired
	private MemberSvc memberSvc;
	
	/**
	 * 회원가입화면
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "signUp")
	public String memberSignUp(HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
		
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
		
		
		return "member/signUp";
	}
	/**
	 * 회원가입처리
	 */
	@RequestMapping(value ="signUpChk")
	public String memberSignUpChk(HttpServletRequest request,HttpServletResponse response, UserVO vo, ModelMap modelMap) {
		
			memberSvc.insertMemberSignUp(vo);
			modelMap.addAttribute("msg_signUp", vo.getUserName()+"님 Pet Daily에 오신걸 환영합니다.");
			return "common/msg_signUp";
		}
		
	/**
	 * 아이디체크
	 */
	@RequestMapping(value = "idCheck")
	   public String overlap(HttpServletRequest request, ModelMap modelMap,
	         @RequestParam(value = "userID", defaultValue = "") String userID) {

	      int check = memberSvc.idCheck(userID);
	      modelMap.addAttribute("check", check);
	      modelMap.addAttribute("userID", userID);
	      return "member/idCheck";
	   }
	}
	

