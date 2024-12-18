package com.example.creato.objets.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.objets.projet.Projet;
import com.example.creato.objets.projet.ProjetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

        metrics.projetsOuverts = this.projetRepository.countByStatu("Ouvert");
        metrics.projetsEncours = this.projetRepository.countByStatu("En cours");
        metrics.projetsPause = this.projetRepository.countByStatu("Pause");
        metrics.projetsCloture = this.projetRepository.countByStatu("Cloture");

        metrics.projetsTF = this.projetRepository.countByPole("Transformation Finance");
        metrics.projetsConso = this.projetRepository.countByPole("Consolidation");
        metrics.projetsCompta = this.projetRepository.countByPole("Compta");
        metrics.projetsOperation = this.projetRepository.countByPole("FSO");
        metrics.projetsPilotage = this.projetRepository.countByPole("Pilotage et Performance");
        metrics.projetsRisque = this.projetRepository.countByPole("Risque et Actuariat");
        metrics.totalProjet = metrics.projetsOuverts + metrics.projetsCloture + metrics.projetsEncours
                + metrics.projetsPause;
        return metrics;
    }

    @GetMapping("historique")
    public List<Historique> getHistorique() {
        List<Projet> projets = this.projetRepository.findAll();
        List<String> months = new ArrayList<String>();

        months.add("JANUARY");
        months.add("FEBRUARY");
        months.add("MARCH");
        months.add("APRIL");
        months.add("MAY");
        months.add("JUNE");
        months.add("JULY");
        months.add("AUGUST");
        months.add("SEPTEMBER");
        months.add("OCTOBER");
        months.add("NOVEMBER");
        months.add("DECEMBER");

        ZonedDateTime givenDateTime = ZonedDateTime.parse("2024-03-02T23:00:00.000Z", DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime currentDateTime = ZonedDateTime.now(givenDateTime.getZone());
        int currentYear = currentDateTime.getYear();
        int previousYear = currentYear - 1;
        int befPreviousYear = previousYear - 1;
        List<Long> serieCurrentYear = new ArrayList(Arrays.asList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
        List<Long> seriePreviousYear = new ArrayList(Arrays.asList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
        List<Long> serieBeforePrevYear = new ArrayList(Arrays.asList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));

        for (Projet projet : projets) {
            ZonedDateTime time = ZonedDateTime.parse(projet.dateDebut, DateTimeFormatter.ISO_DATE_TIME);

            try {
                if (time.getYear() == currentYear) {

                    for (int i = 0; i < 12; i++) {

                        if (time.getMonth().name().equals(months.get(i))) {
                            serieCurrentYear.set(i, serieCurrentYear.get(i) + Long.valueOf(1));
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            // previous year
            try {
                if (time.getYear() == previousYear) {
                    for (int i = 0; i < 12; i++) {
                        if (time.getMonth().name().equals(months.get(i))) {
                            seriePreviousYear.set(i, seriePreviousYear.get(i) + Long.valueOf(1));
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            // bevPRevYEar

            try {
                if (time.getYear() == befPreviousYear) {
                    for (int i = 0; i < 12; i++) {
                        if (time.getMonth().name().equals(months.get(i))) {
                            serieBeforePrevYear.set(i, serieBeforePrevYear.get(i) + Long.valueOf(1));
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        Historique currentYearHistorique = new Historique(1, String.valueOf(currentYear), serieCurrentYear);
        Historique previousYearHistorique = new Historique(2, String.valueOf(previousYear), seriePreviousYear);
        Historique befPreviousYearHistorique = new Historique(3, String.valueOf(befPreviousYear), serieBeforePrevYear);

        List<Historique> historiques = new ArrayList();
        historiques.add(currentYearHistorique);
        historiques.add(previousYearHistorique);
        historiques.add(befPreviousYearHistorique);

        return historiques;

    }

    @GetMapping("/nbCategorie/{cat}")
    public Long getNbCategorie(@PathVariable String cat) {
        return this.projetRepository.countByCategorie(cat);
    }

    @GetMapping("/moyenneTempsCategorie/{cat}")
    public Double getmoyenneTempsCategorie(@PathVariable String cat) {
        Double result = this.projetRepository.findAverageNbjourByCategorie(cat);
        if (result == null) {
            return (double) 0;
        }
        return Math.round(result * (double) 100) / (double) 100;
    }
}
