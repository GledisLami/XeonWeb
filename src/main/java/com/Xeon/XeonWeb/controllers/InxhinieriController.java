package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.services.ProcesiService;
import com.Xeon.XeonWeb.services.ProjektiService;
import com.Xeon.XeonWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inxhinieri")
public class InxhinieriController {

    @Autowired
    ProjektiService projektiService;

    @Autowired
    UserService userService;

    @Autowired
    private ProcesiService procesiService;


    @GetMapping
    public List<Projekti> getAllProjekte() {
        return projektiService.getAllProjekte();
    }


    //Nuk na duhe nje metode krijim projekti pasi krijohet automatikisht kur krijohet nje porosi
//    @PostMapping("/save")
//    public String saveProjekt(Principal auth, Projekti projekti){
//        Integer userId = userService.findByUsername(auth.getName()).get().getId();
//        projekti.setUserId(userId);
//        projektiService.saveProjekt(projekti);
//        return "Projekti u shtua me sukses";
//    }

    //mund te behet me parameter ne varesi te kerkeses se frontendit
    @DeleteMapping("/delete")
    public String deleteProjekti(@RequestParam Integer id){
        try {
            projektiService.deleteProjekti(id);
        } catch (Exception e) {
            return "Projekti me id: " + id + " nuk u gjet";
        }
        return "Projekti me id: " + id + " u fshi me sukses";
    }

    //mund te behet dhe update/{id} por meqe nuk ka nevoje per id ne url e kam bere me parametra
    @PutMapping("/update")
    public String updateProjekt(@RequestParam Integer id,
                                @RequestParam(required = false) String comment,
                                @RequestParam(required = false) Integer userId) {
        //TODO: create a projektrequest class and use that instead of the parameters
        try {
            projektiService.updateProjekt(id, comment, userId);
        } catch (Exception e) {
            return "Projekti me id: " + id + " nuk u gjet";
        }
        return "Projekti me id: " + id + " u ndryshua me sukses";
    }

    //lista e proceseve nen nje projekt
    @GetMapping("/proceset")
    public List<Optional<Procesi>> getAllProceset(@RequestParam Integer projektiId) {
        return procesiService.getAllProceset(projektiId);
    }

    @PostMapping("/proceset/save")
    public String saveProces(Principal auth,
                             @RequestParam Integer projektiId,
                             @RequestParam String pershkrimi,
                             @RequestParam Integer koha,
                             @RequestParam String makineria,
                             @RequestParam Integer sasia,
                             @RequestParam String tipiProcesit) {

//        if (!(userService.findByUsername(auth.getName()).isPresent())) {
//            return "Porosia nuk u ruajt";
//        }
        Integer userId = userService.findByUsername(auth.getName()).get().getId();
        procesiService.saveProces(userId, projektiId, pershkrimi,
                koha, makineria, sasia, tipiProcesit);
        return "Procesi u shtua me sukses";
    }

    @PostMapping("/proceset/saveTest")
    public String saveProcesTest(@RequestParam Integer userId,
                                 @RequestParam Integer projektiId,
                                 @RequestParam String pershkrimi,
                                 @RequestParam Integer koha,
                                 @RequestParam String makineria,
                                 @RequestParam Integer sasia,
                                 @RequestParam String tipiProcesit) {

        procesiService.saveProces(userId, projektiId, pershkrimi,
                koha, makineria, sasia, tipiProcesit);
        return "Procesi u shtua me sukses";
    }

    //update dhe delete nuk duan projektId pasi id e procesit eshte unike
    @PutMapping("/proceset/update")
    public String updateProces(@RequestParam Integer procesiId,
                               @RequestParam(required = false) String pershkrimi,
                               @RequestParam(required = false) Integer koha,
                               @RequestParam(required = false) String makineria,
                               @RequestParam(required = false) String tipiProcesit,
                               @RequestParam(required = false) Integer sasia) {
        try {
            procesiService.updateProcesi(procesiId, pershkrimi, koha, makineria, tipiProcesit, sasia);
            return "Procesi u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/proceset/delete")
    public String deleteProces(@RequestParam Integer procesiId) {
        try {
            procesiService.deleteProcesi(procesiId);
            return "Procesi u fshi me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
