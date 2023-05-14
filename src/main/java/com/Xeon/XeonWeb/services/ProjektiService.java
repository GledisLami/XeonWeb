package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Porosia;
import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.entities.Projekti;
import com.Xeon.XeonWeb.repositories.ProcesiRepository;
import com.Xeon.XeonWeb.repositories.ProjektiRepository;
import com.Xeon.XeonWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProjektiService {

    @Autowired
    private ProjektiRepository projektiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcesiRepository procesiRepository;

    public List<Projekti> getAllProjekte() {
        return projektiRepository.findAll();
    }

    public void saveProjekt(Projekti projekti){
        projektiRepository.save(projekti);
    }

    @Transactional
    public void updateProjekt(Integer id,String comment, Integer userId) {
        //id finds the project in the database, comment and userId can be modified
        Optional<Projekti> projekti = projektiRepository.findById(id);
        if (projekti.isPresent()) {
            if (comment != null && comment.length() > 0) {
                projekti.get().setComments(comment);
            }

            if (userId != null && userRepository.existsById(userId)) {
                projekti.get().setUserId(userId);
            }
        }
    }

    @Transactional
    public void deleteProjekti(Integer id){
        projektiRepository.deleteById(id);
    }

    @Transactional
    public void updateTime(Integer projektiId){
        List<Optional<Procesi>> procesiList = procesiRepository.findByProjektId(projektiId);
        Projekti projekti = projektiRepository.findById(projektiId).get();
        for(Optional<Procesi> procesi : procesiList){
            projekti.setAfati(projekti.getAfati() + procesi.get().getKoha());
        }
    }
}
