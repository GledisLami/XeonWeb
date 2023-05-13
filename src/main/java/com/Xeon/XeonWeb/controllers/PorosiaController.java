package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.entities.Porosia;
import com.Xeon.XeonWeb.requests.PorosiaRequest;
import com.Xeon.XeonWeb.services.PorosiaService;
import com.Xeon.XeonWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/porosia")
public class PorosiaController {

    @Autowired
    private PorosiaService porosiaService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Porosia> getAllPorosia() {
        return porosiaService.getAllPorosia();
    }

    @PostMapping("/save")
    public String savePorosi(Principal auth, @RequestBody PorosiaRequest porosiaRequest) {

//        if (!(userService.findByUsername(auth.getName()).isPresent())) {
//            return "Porosia nuk u ruajt";
//        }
        Integer userId = userService.findByUsername(auth.getName()).get().getId();
        porosiaService.savePorosi(userId, porosiaRequest);
        return "Porosia nr u ruajt me sukses";
        //TODO: return id e porosise me anen e nje query qe kthen id te entry me recent ne databaze
        //select scope_identity() as id
    }

    @DeleteMapping("/delete")
    public String deletePorosia(@RequestParam Integer id) {
        try {
            porosiaService.deletePorosia(id);
            return "Porosia nr "+ id + " u fshi me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public String updatePorosia(PorosiaRequest porosiaRequest) {
        try {
            porosiaService.updatePorosia(porosiaRequest);
            return "Porosia nr "+ porosiaRequest.getId() + " u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}