package com.example.creato.objets.donneurOrdre;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneurOrdreRepository extends JpaRepository<DonneurOrdre, Long> {

    public Optional<DonneurOrdre> findByNomComplet(String nomComplet);
}
