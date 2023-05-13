package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.services.ProjektiService;
import com.Xeon.XeonWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projekti")
public class ProjektiController {

    @Autowired
    ProjektiService projektiService;

    @Autowired
    UserService userService;

    @GetMapping
    public List<Projekti> getAllProjekte() {
        return projektiService.getAllProjekte();
    }

    @PostMapping
    public String saveProjekt(Principal auth, Projekti projekti){
        Integer userId = userService.findByUsername(auth.getName()).get().getId();
        projektiService.saveProjekt(projekti);
        return "Projekti u shtua me sukses";
    }

    @DeleteMapping
    public String deleteProjekti(Integer id){
        try {
            projektiService.deleteProjekti(id);
        } catch (Exception e) {
            return "Projekti me id: " + id + " nuk u gjet";
        }
        return "Projekti me id: " + id + " u fshi me sukses";
    }

    @PutMapping
    public String updateProjekt(Integer id,String comment, Integer userId) {
        try {
            projektiService.updateProjekt(id, comment, userId);
        } catch (Exception e) {
            return "Projekti me id: " + id + " nuk u gjet";
        }
        return "Projekti me id: " + id + " u ndryshua me sukses";
    }
}
