//package com.Xeon.XeonWeb.configs;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collection;
//
//public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        for (GrantedAuthority grantedAuthority : authorities) {
//            String authorityName = grantedAuthority.getAuthority();
//            if (authorityName.equals("ADMIN")) {
//                redirectStrategy.sendRedirect(request, response, "/admin");
//                return;
//            } else if (authorityName.equals("ENGINEER")) {
//                redirectStrategy.sendRedirect(request, response, "/inxhinier");
//                return;
//            } else if (authorityName.equals("FINANCE")) {
//                redirectStrategy.sendRedirect(request, response, "/financa");
//                return;
//            } else if (authorityName.equals("OPERATOR")) {
//                redirectStrategy.sendRedirect(request, response, "/operatori");
//                return;
//            }
//        }
//
//        // If the user has no role, redirect to the default home page
//        redirectStrategy.sendRedirect(request, response, "/home");
//    }
//}
//
