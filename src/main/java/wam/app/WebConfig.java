package wam.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import wam.app.common.LoginCheckInterceptor;

@Configuration
@EnableJpaAuditing( modifyOnCreate = false )
public class WebConfig implements WebMvcConfigurer {

    /*
     * 로그인 인증 Interceptor 설정
     */
    @Autowired
    LoginCheckInterceptor loginCheckInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
		        .addPathPatterns("/api/**")
		        .addPathPatterns("/*/page")
        		.excludePathPatterns("/api/login/**") //로그인 쪽은 제외
        		.excludePathPatterns("/login/**") //로그인 쪽은 제외
                ;
    }
    
    /*
     * 로그인 사용자로 registId, modifyId 설정
     */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
