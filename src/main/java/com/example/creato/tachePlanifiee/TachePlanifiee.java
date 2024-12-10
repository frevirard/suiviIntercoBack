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
        EnvoiMail mail = new EnvoiMail();
        List<Projet> projets = this.projetRepository.findAll();
        for (Projet projet : projets) {
            try {
                this.actuNbJour(projet);
            } catch (Exception e) {
                // TODO: handle exception
                mail.envoiMailNbJourRéussi("Rafraichissement nb jours échoué pour " + projet.titre, "test",
                        "frevirard@gmail.com");
            }
        }

        // mail.envoiMailNbJourRéussi("Rafraichissement nb jours effectué pour tous les
        // dossiers à Minuit");
    }

    @Scheduled(cron = "0 00 05 * * ?")
    public void executeTaskTwo() {
        EnvoiMail mail = new EnvoiMail();
        List<Projet> projets = this.projetRepository.findAll();
        for (Projet projet : projets) {
            try {
                this.actuNbJour(projet);
            } catch (Exception e) {
                // TODO: handle exception
                mail.envoiMailNbJourRéussi("Rafraichissement nb jours échoué pour " + projet.titre, "test",
                        "frevirard@gmail.com");
            }
        }

        // mail.envoiMailNbJourRéussi("Rafraichissement nb jours effectué pour tous les
        // dossiers à 5h ");
    }

    // @Scheduled(cron = "0 03 22 * * ?")
    // public void executeTaskThree() {
    // EnvoiMail mail = new EnvoiMail();

    // System.out.println("La fuck.");
    // }

    private void actuNbJour(Projet projet) {
        if (!projet.statu.equals("Cloture")) {
            String isoDateTime = projet.dateDebut;
            try {
                ZonedDateTime givenDateTime = ZonedDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);
                ZonedDateTime currentDateTime = ZonedDateTime.now(givenDateTime.getZone());
                projet.nbJour = ChronoUnit.DAYS.between(givenDateTime, currentDateTime);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else if (projet.nbJour == null || projet.nbJour == 0) {
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
