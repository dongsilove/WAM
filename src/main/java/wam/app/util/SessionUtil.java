package wam.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import wam.app.w.user.TAuUser;

/**
 * @Class Name : SessionUtil.java
 * @Description : 세션관리
 * @author 박이정
 * @since 2020. 02. 20
 * @version 1.0
 * @see
 * 
 */
public class SessionUtil {

	/**
	 * [FRONT] loginInfo정보를 session에서 가져온다.
	 * @param request
	 * @return LoginInfo
	 */
	public static TAuUser getLoginInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) return null;
		TAuUser loginInfo = (TAuUser) session.getAttribute("loginInfo");
		
		return loginInfo;
	}

	
	/**
	 * loginId정보를 가져온다.
	 * @param request
	 * @return LoginInfo
	 */
	public static String getLoginId(HttpServletRequest request) {
		
		TAuUser loginInfo = getLoginInfo(request);
		if (loginInfo == null) return null;
		String loginId = loginInfo.getUserId();
		return loginId;
		
	}

	/**
	 * session에 넣어둔 loginAuth(메뉴권한)정보 검색하여 권한 있는 메뉴만 가져온다.
	 * @param request, menuList
	 * @return List<Map<String, Object>> menuList
	 */
	public static List<Map<String, Object>> getAuthMenu(HttpServletRequest request, List<Map<String, Object>> menuList) {
		HttpSession session = request.getSession();
		if (session == null) return null;

		@SuppressWarnings("unchecked")
		Map<String, String> authMenuMap = (Map<String, String>) session.getAttribute("loginAuth");
		if (authMenuMap == null) {
			System.out.println("SessionUtil.getAuthMenu : session에 저장된 loginAuth 가져오기 실패");
			return null;
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		int j = 0;

		for (int i = 0; i < menuList.size(); i++) {
			Map<String, Object> map = menuList.get(i);
			if (authMenuMap.containsKey(map.get("MENU_CDE"))) {
				resultList.add(j, map);
				j++;
			}
		}
		return resultList;
	}

	/**
	 * [FRONT] 로그인 여부 점검
	 * @param request
	 * @return ServletContext
	 */
	public static boolean sessionExist(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		} else if (session.getAttribute("loginId") ==  null) {
			System.out.println("session loginId is null");
			return false;
		} else {
			TAuUser loginInfo = (TAuUser) session.getAttribute("loginInfo");
			System.out.println("loginInfo.getUserId() : " + loginInfo.getUserId());
			return true;
		}
	}

	/**
	 * 
	 * @param request
	 * @param context
	 * @return ServletContext
	 */
	public static ServletContext getServletContext(String context) {
		HttpSession session = getRequest().getSession();
		if (session == null)
			return null;
		ServletContext servletContext = session.getServletContext().getContext(context);
		return servletContext;
	}

	/**
	 * 
	 * @param request
	 * @return ServletContext
	 */
	public static ServletContext getServletContext() {
		return getServletContext("/");
	}

	public static void setAttribute(String context, String args0, Object args1) {
		ServletContext servletContext = getServletContext(context);
		if (servletContext == null) return;
		servletContext.setAttribute(args0, args1);
	}

	public static void setAttribute(String args0, Object args1) {
		setAttribute("/", args0, args1);
	}

	public static Object getAttribute(String context, String args0) {
		ServletContext servletContext = getServletContext(context);
		if (servletContext == null)	return null;
		return servletContext.getAttribute(args0);
	}
	
	public static Object getAttribute(String args0) {
		return getAttribute("/", args0);
	}
	
	public static void removeAttribute(String context, String args0) {
		ServletContext servletContext = getServletContext(context);
		servletContext.removeAttribute(args0);
	}

	public static void removeAttribute(String args0) {
		removeAttribute("/", args0);
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	

}
