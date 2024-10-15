package com.example.creato.mailSender;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvoiMail {

    public void envoiMailNbJourRéussi(String mess) {
        String host = "smtp.gmail.com";
        String port = "587"; // Port pour TLS
        final String username = "frevirard"; // Votre adresse email
        final String password = "wfpn zcne wwoa hxls"; // Votre mot de passe (ou un mot de passe d'application)

        // Configuration des propriétés de la connexion SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Activer TLS

        // Création de la session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("frevirard@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("frevirard@gmail.com"));
            message.setSubject("Test Email depuis Java");
            message.setText(mess);

            // Envoi du message
            Transport.send(message);

            System.out.println("Email envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public class envoiMailNbJourRéussi {
    }

}
