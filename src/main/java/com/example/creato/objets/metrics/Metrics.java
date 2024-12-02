package com.example.creato.objets.metrics;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "METRICSINTERCO")
public class Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long projetsEncours;
    public Long projetsOuverts;
    public Long projetsPause;
    public Long projetsCloture;
    public Long projetsTF;
    public Long projetsRisque;
    public Long projetsConso;
    public Long projetsCompta;
    public Long projetsOperation;
    public Long projetsPilotage;
    public Long totalProjet;
    public Long moyenneInterco;
    public Long moyeneProgression;
    public Long pourcentageNouveau;
}
