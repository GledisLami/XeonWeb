package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.Porosia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PorosiaRepository extends JpaRepository<Porosia, Integer> {
    Optional<Porosia> findById(Integer id);
}
