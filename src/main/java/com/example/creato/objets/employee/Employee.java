package com.example.creato.objets.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEEINTERCO")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String nom;
    public String prenoms;
    public String avatar;
    public String pole;
    public String statu;
    public String dateInterco;
    public String carence;
    public String posteOccupe;
    public String email;
    public String mobile;
    public Long projets;
    public String nomComplet;
}
