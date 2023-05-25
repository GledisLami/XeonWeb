package com.Xeon.XeonWeb.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "redirect:/admin.html";
            } else if (authority.getAuthority().equals("ROLE_FINANCA")) {
                return "redirect:/financa.html";
            }
            else if (authority.getAuthority().equals("ROLE_STUDIO")) {
                return "redirect:/studio.html";
            }
            else if (authority.getAuthority().equals("ROLE_OPERATOR")) {
                return "redirect:/operatori.html";
            }
        }
        // Default redirect if the user has no matching role
        return "redirect:/default.html";
    }
}

