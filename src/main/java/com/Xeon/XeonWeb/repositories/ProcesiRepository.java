package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.Procesi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcesiRepository extends JpaRepository<Procesi, Integer> {
    Optional<Procesi> findByPershkrimi(String pershkrimi);

    Optional<Procesi> findById(Integer id);

    List<Optional<Procesi>> findByProjektId(Integer id);
}
