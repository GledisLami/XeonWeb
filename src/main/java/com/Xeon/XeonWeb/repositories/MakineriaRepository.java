package com.Xeon.XeonWeb.repositories;

import com.Xeon.XeonWeb.entities.Makineria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MakineriaRepository extends JpaRepository<Makineria, Integer> {
    Optional<Makineria> findByMakineria(String makineria);

    Optional<Makineria> findById(Integer id);
}
