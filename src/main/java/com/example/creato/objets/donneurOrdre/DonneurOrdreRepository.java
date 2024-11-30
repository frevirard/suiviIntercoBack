package com.example.creato.objets.donneurOrdre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneurOrdreRepository extends JpaRepository<DonneurOrdre, Long> {

}
