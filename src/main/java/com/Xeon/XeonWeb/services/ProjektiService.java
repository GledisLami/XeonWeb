package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Porosia;
import com.Xeon.XeonWeb.entities.Procesi;
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
public class ProjektiService {

    @Autowired
    private ProjektiRepository projektiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcesiRepository procesiRepository;

    @Autowired
    private PorosiaRepository porosiaRepository;

    public List<Projekti> getAllProjekte() {
        List<Projekti> lista = projektiRepository.findAll();

        for (int i =0; i < lista.size(); i++){
            Integer porosiaIdTemp = lista.get(i).getPorosiaId();
            Porosia porosiaTemp = porosiaRepository.findById(porosiaIdTemp).get();
            if (porosiaTemp.getStatusi()!=1){
                lista.remove(i);
                i--;
            }
        }

        return lista;
    }
    //projekti ruhe automatikisht kur krijohet nje porosi
//    public void saveProjekt(Projekti projekti){
//        projektiRepository.save(projekti);
//    }

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
    //todo: fshij te tere proceset
    @Transactional
    public void deleteProjekti(Integer id){
        projektiRepository.deleteById(id);
    }

    @Transactional
    public void updateTime(Integer projektiId){
        List<Optional<Procesi>> procesiList = procesiRepository.findByProjektId(projektiId);
        //nqs nuk gjendet thjesht mos bej set kohen sepse eshte null
        if (!projektiRepository.findById(projektiId).isPresent()) {
            return;
        }
        Projekti projekti = projektiRepository.findById(projektiId).get();
        int totalKoha = 0;
        for (Optional<Procesi> procesi : procesiList) {
            if (procesi.isPresent() && procesi.get().getFazaId() != 2) {
                totalKoha += procesi.get().getKoha();
            }
        }
        projekti.setAfati(totalKoha);
    }
}
