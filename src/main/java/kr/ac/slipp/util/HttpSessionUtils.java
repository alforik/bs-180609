package kr.ac.slipp.util;

import javax.servlet.http.HttpSession;

import kr.ac.slipp.domain.User;

public class HttpSessionUtils {

	public static final String USER_SESSION_KEY = "sessionedUser";
	
	// 로그인 유무 판단
	public static boolean isLoginUser( HttpSession session) {
		Object sessionedUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		if( sessionedUser == null ) {
			return false;
		}
		return true;
	}
	
	public static User getUserFromSession( HttpSession session) {
		if( !isLoginUser(session)) {
			return null;
		}
		//User sesseionedUser = (User)session.getAttribute(USER_SESSION_KEY);
		
		return (User)session.getAttribute(USER_SESSION_KEY);
	}
}
