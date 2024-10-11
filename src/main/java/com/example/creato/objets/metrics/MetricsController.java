package com.example.creato.objets.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.objets.projet.ProjetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    @Autowired
    ProjetRepository projetRepository;

    @GetMapping("/countByPole")
    public Long getCountByPole(@RequestParam String param) {
        return this.projetRepository.countByPole(param);
    }

    @GetMapping("/countByStatu")
    public Long getCountByStatu(@RequestParam String param) {
        return this.projetRepository.countByStatu(param);
    }

    @GetMapping("/countByAssigne")
    public Long getMethodName(@RequestParam String param) {
        return this.projetRepository.countByAssignee(param);
    }

    @GetMapping("/getMetrics")
    public Metrics getAllMetrics() {

        Metrics metrics = new Metrics();

        metrics.projetOuverts = this.projetRepository.countByStatu("Ouvert");
        metrics.projetsEncours = this.projetRepository.countByStatu("En cours");
        metrics.projetsCloture = this.projetRepository.countByStatu("Cloture");

        metrics.projetsTF = this.projetRepository.countByPole("Transformation Finance");
        metrics.projetsConso = this.projetRepository.countByPole("Consolidation");
        metrics.projetsCompta = this.projetRepository.countByPole("Compta");
        metrics.projetsOperation = this.projetRepository.countByPole("FSO");
        metrics.projetsPilotage = this.projetRepository.countByPole("Pilotage et Performance");
        metrics.projetsRisque = this.projetRepository.countByPole("Risque et Actuariat");
        metrics.totalProjet = metrics.projetOuverts + metrics.projetsCloture + metrics.projetsEncours;
        return metrics;
    }

}
