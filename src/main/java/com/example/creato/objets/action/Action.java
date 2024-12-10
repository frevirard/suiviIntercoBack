package com.example.creato.objets.action;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DACTIONINTERCO")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String titre;
    public String description;
    public String categorie;
    public Boolean notification;
    public String donneurOrdre;

    public String getDonneurOrdre() {
        return donneurOrdre;
    }

}
