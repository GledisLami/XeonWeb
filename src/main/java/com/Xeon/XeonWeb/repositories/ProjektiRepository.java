package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.Projekti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjektiRepository extends JpaRepository<Projekti, Integer> {
    Optional<Projekti> findById(Integer id);

    Optional<Projekti> findByPorosiaId(Integer id);
}
