package org.sang;

import com.aotain.login.support.Myfilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Test26SecurityApplication {
	/**
	 * 权限系统的地址
	 */
	@Value("${service.sso.uri}")
	private String ssoUrl;
	/**
	 * 当前系统在权限系统的部署id
	 */
	@Value("${service.local.deployid}")
	private String deployid;
	/**
	 * 登录的白名单
	 */
	@Value("${service.sso.whiteuri}")
	private String whiteuri;

	public static void main(String[] args) {
		SpringApplication.run(Test26SecurityApplication.class, args);
	}

	/**
	 * 单点登录拦截器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean ssoFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new Myfilter());
		registration.addInitParameter("SSOURL", ssoUrl);
		registration.addInitParameter("DEPLOYID", deployid);
		registration.addInitParameter("WHITEURI", whiteuri);
		registration.addUrlPatterns("*");
		registration.setName("Myfilter");
		registration.setOrder(100);
		return registration;
	}

//	@Autowired
//	AuthenticationManager authenticationManager;
	/**
	 * SpringSecurityFilter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean securityFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new SpringSecurityFilter());
		registration.addUrlPatterns("*");
		registration.setName("SpringSecurityFilter");
		registration.setOrder(101);
		return registration;
	}
}
