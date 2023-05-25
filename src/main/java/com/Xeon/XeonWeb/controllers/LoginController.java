package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

//    @GetMapping("/login")
//    public String login(Authentication authentication) {
//        for (GrantedAuthority authority : authentication.getAuthorities()){
//            if (authority.getAuthority().equals("ADMIN")){
//                return "redirect:file:///C:/Users/Gledis/Desktop/fwd/admin.html";
//            }
//            else if (authority.getAuthority().equals("FINANCA")){
//                return "redirect:file:///C:/Users/Gledis/Desktop/fwd/financa.html";
//            }
//            else if (authority.getAuthority().equals("STUDIO")){
//                return "redirect:file:///C:/Users/Gledis/Desktop/fwd/studio.html";
//            }
//            else return "redirect:file:///C:/Users/Gledis/Desktop/fwd/operatori.html";
//        }
//        return "redirect:/default.html";
//    }
}
