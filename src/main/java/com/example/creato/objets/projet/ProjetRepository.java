package com.example.creato.objets.projet;

import java.security.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    Long countByPole(String pole);

    Long countByStatu(String statu);

    Long countByAssignee(String Assignee);

    Long countByCategorie(String Assignee);

    // Double findAverageNbjourByCategorie(String Categorie);

    @Query("SELECT AVG(p.nbJour) FROM Projet p WHERE p.categorie = :category")
    Double findAverageNbjourByCategorie(@Param("category") String category);
}
