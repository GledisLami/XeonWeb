package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Porosia;
import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.repositories.PorosiaRepository;
import com.Xeon.XeonWeb.repositories.ProcesiRepository;
import com.Xeon.XeonWeb.repositories.ProjektiRepository;
import com.Xeon.XeonWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PorosiaService {

    @Autowired
    private PorosiaRepository porosiaRepository;

    @Autowired
    private ProjektiRepository projektiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcesiRepository procesiRepository;


    public List<Porosia> getAllPorosia() {
        return porosiaRepository.findAll();
    }

    public void savePorosi(Integer userId, String comments) {
        Porosia porosia = new Porosia();
        porosia.setUserId(userId);
        porosia.setComments(comments);
        porosia.setStatusi(0);
        porosiaRepository.save(porosia);
        //pasi ruhet porosia, popollojme nje objekt projekti dhe e ruajme ne databaze
        Projekti projekti = new Projekti();
        projekti.setComments("");
        projekti.setAfati(0);
        projekti.setPorosiaId(porosia.getId());
        projekti.setUserId(userId);
        //pasi nuk e dime per ke inxhinier eshte, e vendosim id 111
//        projekti.setUser_id(111);
        projektiRepository.save(projekti);
    }

    @Transactional
    public void miratoPorosi(Integer id) {
        if (porosiaRepository.findById(id).isPresent()) {
            porosiaRepository.findById(id).get().setStatusi(1);
        }
    }

    public Optional<Porosia> findById(Integer id){
        return porosiaRepository.findById(id);
    }

    @Transactional
    public void updatePorosia(Integer porosiaId, String comments, Integer userId){
        Porosia porosia = porosiaRepository.findById(porosiaId).
                orElseThrow(() -> new IllegalStateException(
                "Porosia nr " + porosiaId + " nuk ekziston"));
        if (comments != null && comments.length() > 0) {
            porosia.setComments(comments);
        }
        if (userRepository.findById(userId).isPresent()) {
            porosia.setUserId(userId);
        }
        /* Mund te shtohet me vone
        if (porosiaRequest.getStatusi() == 1 || porosiaRequest.getStatusi() == 0) {
            porosia.setStatusi(porosiaRequest.getStatusi());
        }
         */
    }

    @Transactional
    public void deletePorosia(Integer id){
        porosiaRepository.deleteById(id);
    }

    //ne momenitn qe fshihet nje porosi do fshihet projekti dhe proceset perkatese
    @Transactional
    public void deletePorosiProjektProcese(Integer id){
        for (int i = 0; i < procesiRepository.findByProjektId(id).size(); i++) {
            procesiRepository.deleteById(procesiRepository.findByProjektId(id).get(i).get().getId());
        }
        projektiRepository.deleteById(projektiRepository.findByPorosiaId(id).get().getId());
        porosiaRepository.deleteById(id);
    }
}