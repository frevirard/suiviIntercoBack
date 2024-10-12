package com.example.creato.objets.projet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.auth.MessageResponse;
import com.example.creato.objets.employee.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/actions")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    @PostMapping("add")
    public Projet postMethodName(@RequestBody Projet projet) {
        // TODO: process POST request
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
