package com.Xeon.XeonWeb.services;

import com.Xeon.XeonWeb.entities.Procesi;
import com.Xeon.XeonWeb.repositories.MakineriaRepository;
import com.Xeon.XeonWeb.repositories.ProcesiRepository;
import com.Xeon.XeonWeb.repositories.ProjektiRepository;
import com.Xeon.XeonWeb.repositories.TipiProcesitRepository;
import com.Xeon.XeonWeb.requests.ProcesiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcesiService {

    final ProcesiRepository procesiRepository;
    final ProjektiRepository projektiRepository;
    final MakineriaRepository makineriaRepository;
    final TipiProcesitRepository tipiProcesitRepository;


    @Autowired
    public ProcesiService(ProcesiRepository procesiRepository, ProjektiRepository projektiRepository,
                          MakineriaRepository makineriaRepository, TipiProcesitRepository tipiProcesitRepository) {
        this.procesiRepository = procesiRepository;
        this.projektiRepository = projektiRepository;
        this.makineriaRepository = makineriaRepository;
        this.tipiProcesitRepository = tipiProcesitRepository;
    }

    public List<Procesi> getAllProceset() {
        return procesiRepository.findAll();
    }

    public void saveProces(Integer userId, ProcesiRequest procesiRequest) {
        Procesi procesi = new Procesi();
        procesi.setProcesi(procesiRequest.getProcesi());
        procesi.setKoha(procesiRequest.getKoha());
        //we don't check it is a dropdown box, so it is always present
        procesi.setMakineriaId(makineriaRepository.findByMakineria(procesiRequest.getMakineria()).get().getId());
        procesi.setTipiProcesitId(tipiProcesitRepository.
                findByTipiProcesit(procesiRequest.getTipiProcesit()).get().getId());
        //kur krijohet nje proces 0 eshte kodi per ne pritje
        procesi.setFazaId(0);
        procesi.setProjektId(procesiRequest.getProcesiId());
        procesiRepository.save(procesi);
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
    public void updateProcesi(ProcesiRequest procesiRequest) {
        if (procesiRepository.findById(procesiRequest.getProcesiId()).isPresent()) {
            Procesi proces = procesiRepository.findById(procesiRequest.getProcesiId()).get();
            if (procesiRequest.getProcesi() != null) {
                proces.setProcesi(procesiRequest.getProcesi());
            }
            if (procesiRequest.getKoha() != null) {
                proces.setKoha(procesiRequest.getKoha());
            }
            if (makineriaRepository.findByMakineria(procesiRequest.getMakineria()).isPresent()) {
                proces.setMakineriaId(makineriaRepository.findByMakineria(procesiRequest.getMakineria()).get().getId());
            }
            if (tipiProcesitRepository.findByTipiProcesit(procesiRequest.getTipiProcesit()).isPresent()) {
                proces.setTipiProcesitId(tipiProcesitRepository.findByTipiProcesit(procesiRequest.getTipiProcesit()).get().getId());
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
