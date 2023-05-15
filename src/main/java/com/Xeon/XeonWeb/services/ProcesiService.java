package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesiService {

    @Autowired
    private ProcesiRepository procesiRepository;
    @Autowired
    private ProjektiRepository projektiRepository;
    @Autowired
    private MakineriaRepository makineriaRepository;
    @Autowired
    private TipiProcesitRepository tipiProcesitRepository;
    @Autowired
    private ProjektiService projektiService;


    public List<Optional<Procesi>> getAllProceset(Integer projektId) {
        return procesiRepository.findByProjektId(projektId);
    }

    //ruan nje proces te ri. anash shfaqet kush e krijoi(userId)
    //projektiId eshte id e projektit ku do te shtohet procesi
    public void saveProces(Integer userId, Integer projektiId, String procesi, Integer koha, String makineria) {
        Procesi procesiObj = new Procesi();
        procesiObj.setProcesi(procesi);
        procesiObj.setKoha(koha);
        //we don't check it is a dropdown box, so it is always present
        procesiObj.setMakineriaId(makineriaRepository.findByMakineria(makineria).get().getId());
        procesiObj.setTipiProcesitId(tipiProcesitRepository.
                findByTipiProcesit(procesi).get().getId());
        //kur krijohet nje proces 1 eshte kodi per ne pritje
        procesiObj.setFazaId(1);
        procesiObj.setProjektId(projektiId);
        procesiObj.setUserId(userId);
        procesiRepository.save(procesiObj);
        projektiService.updateTime(projektiId);
    }

    public Procesi getProcesi(Integer id) {
        if (procesiRepository.findById(id).isPresent()) {
            return procesiRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public void deleteProcesi(Integer id) {
        procesiRepository.deleteById(id);
    }

    @Transactional
    public void updateProcesi(Integer procesiId, String procesi, Integer koha, String makineria, String tipiProcesit) {
        if (procesiRepository.findById(procesiId).isPresent()) {
            Procesi procesObj = procesiRepository.findById(procesiId).get();
            if (procesi != null) {
                procesObj.setProcesi(procesi);
            }
            if (koha != null) {
                procesObj.setKoha(koha);
            }
            if (makineriaRepository.findByMakineria(makineria).isPresent()) {
                procesObj.setMakineriaId(makineriaRepository.findByMakineria(makineria).get().getId());
            }
            if (tipiProcesitRepository.findByTipiProcesit(tipiProcesit).isPresent()) {
                procesObj.setTipiProcesitId(tipiProcesitRepository.findByTipiProcesit(tipiProcesit).get().getId());
            }
        }
    }

    @Transactional
    public void updateFaza(Integer procesId, Integer fazaId){
        if(procesiRepository.findById(procesId).isPresent() && fazaId != null && fazaId >=0 && fazaId <= 3){
            Procesi proces = procesiRepository.findById(procesId).get();
            proces.setFazaId(fazaId);
        }
    }
}
