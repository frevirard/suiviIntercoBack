package com.example.creato.tachePlanifiee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.creato.mailSender.EnvoiMail;
import com.example.creato.objets.projet.Projet;
import com.example.creato.objets.projet.ProjetRepository;
import java.util.List;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class TachePlanifiee {

    @Autowired
    ProjetRepository projetRepository;

    // Exécution toutes les 5 secondes
    @Scheduled(cron = "0 01 00 * * ?")
    public void executeTask() {
        List<Projet> projets = this.projetRepository.findAll();
        for (Projet projet : projets) {
            try {
                EnvoiMail mail = new EnvoiMail();
                this.actuNbJour(projet);
                mail.envoiMailNbJourRéussi("Rafraichissement nb jour effectué pour " + projet.titre);
            } catch (Exception e) {
                // TODO: handle exception
                EnvoiMail mail = new EnvoiMail();
                mail.envoiMailNbJourRéussi("Rafraichissement nb jour échoué pour " + projet.titre);
            }
        }
    }

    @Scheduled(cron = "0 00 05 * * ?")
    public void executeTaskTwo() {
        List<Projet> projets = this.projetRepository.findAll();
        for (Projet projet : projets) {
            try {
                this.actuNbJour(projet);
            } catch (Exception e) {
                // TODO: handle exception
                EnvoiMail mail = new EnvoiMail();
                mail.envoiMailNbJourRéussi("Rafraichissement nb jour échoué pour " + projet.titre);
            }
        }
    }

    // @Scheduled(cron = "0 03 22 * * ?")
    // public void executeTaskThree() {
    // EnvoiMail mail = new EnvoiMail();

    // System.out.println("La fuck.");
    // }

    private void actuNbJour(Projet projet) {
        if (projet.statu != "Cloture") {
            String isoDateTime = projet.dateDebut;
            try {
                ZonedDateTime givenDateTime = ZonedDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);
                ZonedDateTime currentDateTime = ZonedDateTime.now(givenDateTime.getZone());
                projet.nbJour = ChronoUnit.DAYS.between(givenDateTime, currentDateTime);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        this.projetRepository.save(projet);
    }

}
