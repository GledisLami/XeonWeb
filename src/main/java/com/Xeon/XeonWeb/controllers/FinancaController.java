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
@RequestMapping("/financa")
public class FinancaController {

    //TODO: create json formats for all mappings that have multiple parameters

    @Autowired
    private PorosiaService porosiaService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Porosia> getAllPorosia() {
        return porosiaService.getAllPorosia();
    }

    @PostMapping("/save")
    public String savePorosi(Principal auth,
                             @RequestParam String comments) {

//        if (!(userService.findByUsername(auth.getName()).isPresent())) {
//            return "Porosia nuk u ruajt";
//        }
        Integer userId = userService.findByUsername(auth.getName()).get().getId();
        porosiaService.savePorosi(userId, comments);
        return "Porosia nr u ruajt me sukses";
        //TODO: return id e porosise me anen e nje query qe kthen id te entry me recent ne databaze
        //select scope_identity() as id
    }

    //for adding a new porosia object
    @PostMapping("/saveTest")
    public String savePorosi(@RequestParam Integer userId,
                             @RequestParam String comments) {
        /*
        {
             "userId": 1,
            "comments": "test"
        }
         */
        porosiaService.savePorosi(userId, comments);
        return "Porosia nr  "+ userId  +"u ruajt me sukses";
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
    public String updatePorosia(@RequestParam Integer porosiaId,
                                @RequestParam(required = false) String comments,
                                @RequestParam(required = false) Integer userId) {
        try {
            porosiaService.updatePorosia(porosiaId, comments, userId);
            return "Porosia nr "+ porosiaId + " u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}