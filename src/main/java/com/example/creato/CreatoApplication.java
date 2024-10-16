package com.example.creato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.creato.mailSender.EnvoiMail;

@SpringBootApplication
@EnableScheduling
public class CreatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreatoApplication.class, args);
		// EnvoiMail mail = new EnvoiMail();
		// mail.envoiMailNbJourRéussi("Démarrage appli réussie");
	}

}
