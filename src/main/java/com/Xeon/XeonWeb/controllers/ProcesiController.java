package com.Xeon.XeonWeb.controllers;

import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.requests.ProcesiRequest;
import com.Xeon.XeonWeb.services.ProcesiService;
import com.Xeon.XeonWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/procesi")
public class ProcesiController {

    @Autowired
    private ProcesiService procesiService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Procesi> getAllProceset() {
        return procesiService.getAllProceset();
    }

    @PostMapping
    public String saveProces(Principal auth, @RequestBody ProcesiRequest procesiRequest) {

        Integer userId = userService.findByUsername(auth.getName()).get().getId();
        procesiService.saveProces(userId, procesiRequest);
        return "Procesi u shtua me sukses";
    }

    @PutMapping
    public String updateProces(ProcesiRequest procesiRequest) {
        try {
            procesiService.updateProcesi(procesiRequest);
            return "Procesi u ndryshua me sukses";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
