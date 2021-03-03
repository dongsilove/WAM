package wam.app;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import wam.app.util.SessionUtil;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String loginId = SessionUtil.getLoginId(request);

		return Optional.ofNullable(loginId);

	}
}
