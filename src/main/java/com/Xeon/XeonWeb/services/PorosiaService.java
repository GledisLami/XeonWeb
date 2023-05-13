package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Porosia;
import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.repositories.PorosiaRepository;
import com.Xeon.XeonWeb.repositories.ProjektiRepository;
import com.Xeon.XeonWeb.requests.PorosiaRequest;
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

    public List<Porosia> getAllPorosia() {
        return porosiaRepository.findAll();
    }

    public void savePorosi(Integer userId, PorosiaRequest porosiaRequest){
        Porosia porosia = new Porosia();
        porosia.setUserId(userId);
        porosia.setComments(porosiaRequest.getComments());
        porosia.setStatusi(0);
        porosiaRepository.save(porosia);
        //pasi ruhet porosia, popollojme nje objekt projekti dhe e ruajme ne databaze
        Projekti projekti = new Projekti();
        projekti.setComments("");
        projekti.setAfati(0);
        projekti.setPorosiaId(porosia.getId());
        //pasi nuk e dime per ke inxhinier eshte, e vendosim id 111
        projekti.setUserId(111);
        projektiRepository.save(projekti);
    }

    @Transactional
    public void miratoPorosi(Integer id){
        porosiaRepository.findById(id).get().setStatusi(1);
    }

    public Optional<Porosia> findById(Integer id){
        return porosiaRepository.findById(id);
    }

    @Transactional
    public void updatePorosia(PorosiaRequest porosiaRequest){
        Porosia porosia = porosiaRepository.findById(porosiaRequest.getId()).
                orElseThrow(() -> new IllegalStateException(
                "Porosia nr " + porosiaRequest.getId() + " nuk ekziston"));
        if (porosiaRequest.getComments() != null && porosiaRequest.getComments().length() > 0) {
            porosia.setComments(porosiaRequest.getComments());
        }
        if (porosiaRequest.getStatusi() == 1 || porosiaRequest.getStatusi() == 0) {
            porosia.setStatusi(porosiaRequest.getStatusi());
        }
    }

    @Transactional
    public void deletePorosia(Integer id){
        porosiaRepository.deleteById(id);
    }
}