package org.sang;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Louis Lee
 * @Desc
 * @Date 02/03/2018
 */
public class SpringSecurityFilter implements Filter {

    public SpringSecurityFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        //设置角色，多个，在这个理改变角色的字符串就会改变页面的显示，角色一定要用'ROLE_'开头
        Collection roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //用户名密码随便填，因为我使用的是security的权限管理，这里我没有去sso中获取用户信息，只是做一个测试
        UsernamePasswordAuthenticationToken authRequest = new
                UsernamePasswordAuthenticationToken("aaa", "bbb", roles);
        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authRequest);
        // Create a new session and add the security context.
        HttpSession session = ((HttpServletRequest) request).getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        arg2.doFilter(request, arg1);
    }


    public void destroy() {
    }
}
