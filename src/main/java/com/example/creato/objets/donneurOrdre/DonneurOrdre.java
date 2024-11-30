package com.example.creato.objets.donneurOrdre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DONNEURORDREINTERCO")
public class DonneurOrdre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String nom;
    public String prenoms;
    public String avatar;
    public String pole;
    public String nomComplet;
    public String email;
}
