package wam.app.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import wam.app.util.SessionUtil;

/**
 * @Class Name : LoginCheckInterceptor.java
 * @Description : session 여부 점검
 * @author 박이정
 * @since 2019.11.07
 * @version 1.0
 * @see
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor  {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("<<<LoginCheckInterceptor>>>");
		logger.debug(" Request RequestURI \t:" + request.getRequestURI()+"?"+request.getQueryString());
        String requestUri = request.getRequestURI();
		// login점검
		if (!SessionUtil.sessionExist(request)) {
			logger.debug("session check failure!!");
			if (requestUri.contains("/api"))
				response.sendRedirect(request.getContextPath()+"/api/login/nosession");
			else
				response.sendRedirect(request.getContextPath()+"/login/page");
			return false;
    	}

    	return true;
    }
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }
}
