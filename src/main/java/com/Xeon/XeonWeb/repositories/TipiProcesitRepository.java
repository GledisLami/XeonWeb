package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.TipiProcesit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipiProcesitRepository extends JpaRepository<TipiProcesit, Integer> {
    Optional<TipiProcesit> findByTipiProcesit(String tipiProcesit);

    Optional<TipiProcesit> findById(Integer id);
}
