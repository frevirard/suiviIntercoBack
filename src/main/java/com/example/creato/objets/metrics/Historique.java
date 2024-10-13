package com.example.creato.objets.metrics;

import java.util.List;

public class Historique {
    public int id;
    public String name;
    public List<Long> data;

    public Historique(int id, String name, List<Long> data) {
        this.name = name;
        this.data = data;
    }
}
