package com.example.creato.objets.metrics;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long projetsEncours;
    public Long projetOuverts;
    public Long projetsCloture;
    public Long projetsTF;
    public Long projetsIFRS;
    public Long projetsConso;
    public Long projetsCompta;
    public Long projetsOperation;
    public Long projetsPilotage;
    public Long pourcentageNouveau;
}
