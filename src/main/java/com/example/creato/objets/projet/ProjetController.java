package com.example.creato.objets.projet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.auth.MessageResponse;
import com.example.creato.mailSender.EnvoiMail;
import com.example.creato.objets.action.Action;
import com.example.creato.objets.action.ActionRepository;
import com.example.creato.objets.donneurOrdre.DonneurOrdre;
import com.example.creato.objets.donneurOrdre.DonneurOrdreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/actions")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    DonneurOrdreRepository donneurOrdreRepository;

    @PostMapping("add")
    public Projet postMethodName(@RequestBody Projet projet) {
        // TODO: process POST request
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

        Optional<Action> action = this.actionRepository.findById(projet.idAction);
        try {
            if (action.isPresent()) {
                Optional<DonneurOrdre> ordonneur = this.donneurOrdreRepository
                        .findByNomComplet(action.get().getDonneurOrdre());
                if (ordonneur.isPresent() && action.get().notification) {
                    try {
                        EnvoiMail mail = new EnvoiMail();
                        mail.envoiMailNbJourRéussi(
                                "Suivi de l'action " + action.get().titre,
                                " Pris en charge par: <strong>" + projet.assignee
                                        + " </strong> <br> Progression: <strong>" + projet.progression
                                        + "% </strong> <br> Commentaire: "
                                        + projet.commentaire,
                                ordonneur.get().email);
                    } catch (Exception e) {
                        System.out.println("email non valide");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Envoie de mail non effectué");
        }

        System.out.println("sauvegardé");
        return this.projetRepository.save(projet);
    }

    @GetMapping("getAll")
    public List<Projet> getMethodName() {
        return this.projetRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public MessageResponse deleteProjet(@PathVariable Long id) {
        this.projetRepository.deleteById(id);
        return new MessageResponse("Success");
    }

}
