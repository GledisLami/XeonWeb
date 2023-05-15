package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.services.ProcesiService;
import com.Xeon.XeonWeb.services.ProjektiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operatori")
public class OperatoriController {

    @Autowired
    private ProjektiService projektiService;

    @Autowired
    private ProcesiService procesiService;


    //mund te bejme nje response custom qe operatori te shohe me pak info
    //do e diskutojme me vone me frontendin
    @GetMapping
    public List<Projekti> getAllProjektet() {
        return projektiService.getAllProjekte();
    }

    @GetMapping("/{projektiId}/proceset")
    public List<Optional<Procesi>> getAllProceset(@PathVariable Integer projektiId) {
        return procesiService.getAllProceset(projektiId);
    }

    //update dhe delete nuk duan projektId pasi id e procesit eshte unike
    @PutMapping("/proceset/nis")
    public String nisProces(@RequestParam Integer procesiId) {
        try {
            procesiService.updateFaza(procesiId, 1);
            return "Procesi u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/proceset/perfundo")
    public String perfundoProces(@RequestParam Integer procesiId) {
        try {
            procesiService.updateFaza(procesiId, 2);
            return "Procesi u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
