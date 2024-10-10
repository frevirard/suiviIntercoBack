package com.example.creato.objets.projet;

import java.security.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    Long countByPole(String pole);

    Long countByStatu(String statu);

    Long countByAssignee(String Assignee);
}
