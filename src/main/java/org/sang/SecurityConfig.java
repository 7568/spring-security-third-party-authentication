package org.sang;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Louis Lee
 * @Desc
 * @Date 05/03/2018
 */
@Configuration      // 声明为配置类
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        //让所有的请求都通过，即忽略security的登陆拦截
        http.authorizeRequests().anyRequest().permitAll();
    }

}
