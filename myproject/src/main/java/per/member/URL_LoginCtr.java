package per.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class URL_LoginCtr {

	@Autowired
	private MemberSvc memberSvc;

	@RequestMapping(value = "N_callback")
	public String NaverCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws UnsupportedEncodingException {
		String clientId = "rlFR0rpeR2g_ZZhqAhdl";
		String clientSecret = "ispVjO4Es4";
		String code = request.getParameter("code");
		String redirectURI = URLEncoder.encode("http://localhost:8080/myproject/N_callback", "UTF-8");
		String state = request.getParameter("state");

		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL=" + apiURL);

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");

				System.out.println("acc_to: " + access_token);
				System.out.println("refresh_token : " + refresh_token);

				Naverinfo(request, response, access_token); // Naverinfo라는 메소드에 값들을 전달하여 정보값을 받아올꺼임!
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");

		modelMap.addAttribute("msg_login_o", name + "님 네이버 로그인 되셨습니다.");

		return "common/msg_login_o";
	}

	// Naver 사용자 정보 받아오기
	public void Naverinfo(HttpServletRequest request, HttpServletResponse response, String access_token) {

		String reqURL = "https://openapi.naver.com/v1/nid/me";
		String name = "";
		String email = "";
		String id = "";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer " + access_token);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();

			System.out.println(inputLine);
			// 여기서 사용자 정보들이 json형태로 받아와짐

			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject naver_account = (JSONObject) obj;

				naver_account = (JSONObject) jsonObj.get("response");

				id = (String) naver_account.get("id");
				name = (String) naver_account.get("name");
				email = (String) naver_account.get("email");
				// 받아오는 정보 값들

				HttpSession session = request.getSession();
				session.setAttribute("userName", name);
				session.setAttribute("userid", id);
				System.out.println("id : " + id);
				System.out.println("이름 : " + name);
				System.out.println("메일 : " + email);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// 나는야 카카오 로그인
	@RequestMapping(value = "K_callback")
	public String KakaoCallback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws UnsupportedEncodingException {
		String clientId = "ac428383bebd48ab6b11875b0217d9c9"; // ﻿REST API키!
		String code = request.getParameter("code");
		System.out.println("code : " + code);
		String redirectURI = URLEncoder.encode("http://localhost:8080/myproject/K_callback", "UTF-8");

		String apiURL;
		apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL=" + apiURL);

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");

				System.out.println("acc_to: " + access_token);
				System.out.println("refresh_token : " + refresh_token);

				Kakaoinfo(request, response, access_token); // kakaoinfo에 값들을 전달하여 본인 정보를 받아오는 곳
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");

		modelMap.addAttribute("msg_login_o", name + "님 카카오 로그인 되셨습니다.");

		return "common/msg_login_o";
	}
	
	//카카오 정보 받아오기
	public void Kakaoinfo(HttpServletRequest request, HttpServletResponse response, String access_token) {
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String name = "";
		String email = "";
		long id = 0;  //id값이 long형태임 (String이나 int는 안됨)

		try {
			URL url = new URL(reqURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Bearer " + access_token);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
            System.out.println(inputLine); //여기서 json형태로 정보값들을 받아옴

			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject properties = (JSONObject) obj;
				JSONObject kakao_account = (JSONObject) obj;

				properties = (JSONObject) jsonObj.get("properties");
				kakao_account = (JSONObject) jsonObj.get("kakao_account");

				id = (long) jsonObj.get("id");
				name = (String) properties.get("nickname");
				email = (String) kakao_account.get("email");

				HttpSession session =request.getSession();
				session.setAttribute("userid", id);
				session.setAttribute("userName", name);
				
				System.out.println("properties" + properties);
				System.out.println("id : " + id);
				System.out.println("이름 : " + name);
				System.out.println("메일 : " + email);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
