package com.example.creato.objets.projet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJETSINTERCO")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String categorie;
    public String titre;
    public String details;
    public String assignee;
    public String imgSrc;
    public String support;
    public String statu;
    public Long progression;
    public String pole;
    public String dateDebut;
    public String nbJour;
    public String commentaire;
}
