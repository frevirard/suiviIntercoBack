package com.example.creato.mailSender;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class EnvoiMail {

    public void envoiMailNbJourRéussi(String mess, String messDeux, String email) {
        String host = "smtp.gmail.com";
        String port = "587"; // Port pour TLS
        final String username = "lendysfactory@gmail.com"; // Votre adresse email
        final String password = "erdb ljrl ykax rhnq"; // Votre mot de passe (ou un mot de passe d'application)

        // Configuration des propriétés de la connexion SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Activer TLS

        String htmlContent = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <h1 style="color: #4CAF50;">Welcome to Our Service!</h1>
                    <p>We are excited to have you on board. Here's what you can expect:</p>
                    <ul>
                      <li>Fast and reliable service</li>
                      <li>24/7 customer support</li>
                      <li>Exclusive offers and discounts</li>
                    </ul>
                    <p style="font-size: 16px;">Feel free to <a href="https://example.com" style="color: #4CAF50;">visit our website</a> to learn more.</p>
                    <footer style="margin-top: 20px; font-size: 14px; color: #777;">
                      <p>Best regards,</p>
                      <p>The Example Team</p>
                    </footer>
                  </body>
                </html>
                """;
        String htmlContentDeux = "<html>" +
                "<body style=\"font-family: Arial, sans-serif; color: #333;\">" +
                "<h1 style=\"color: #fa896b;\"> " + mess + "</h1>" +
                "<p>Ceci est un message provenant de l'application de suivi des projets Lendys: <strong>"
                + "intercolendys.web.app" + "</strong>.</p>" + "<br>" + "<p>" + messDeux + " </p>" +
                "<p style=\"font-size: 16px;\">Cliquez <a href=\"https://intercolendys.web.app\" style=\"color: #fa896b;\">ici</a> Pour en savoir plus.</p>"
                +
                "<footer style=\"margin-top: 20px; font-size: 14px; color: #777;\">" +
                "<p>Merci d'utiliser les outils internes!</p>" +
                "</footer>" +
                "</body>" +
                "</html>";

        // Création de la session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Notification suivi Action");

            MimeMultipart multipart = new MimeMultipart("alternative");

            // Add HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContentDeux, "text/html");
            multipart.addBodyPart(htmlPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            // message.setText(mess);

            // // Envoi du message
            // Transport.send(message);

            System.out.println("Email envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public class envoiMailNbJourRéussi {
    }

    public void envoiMailCreationDeCompte(String mess, String messDeux, String email) {
        String host = "smtp.gmail.com";
        String port = "587"; // Port pour TLS
        final String username = "lendysfactory@gmail.com"; // Votre adresse email
        final String password = "erdb ljrl ykax rhnq"; // Votre mot de passe (ou un mot de passe d'application)

        // Configuration des propriétés de la connexion SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Activer TLS

        String htmlContentDeux = "<html>" +
                "<body style=\"font-family: Arial, sans-serif; color: #333;\">" +
                "<h1 style=\"color: #fa896b;\"> " + mess + "</h1>" +
                "<p>Ceci est un message provenant de l'application de suivi des projets Lendys: <strong>"
                + "intercolendys.web.app" + "</strong>.</p>" + "<br>" + "<p>" + messDeux + " </p>" +
                "<p style=\"font-size: 16px;\">Suivez le lien <a href=\"https://intercolendys.web.app\" style=\"color: #fa896b;\">ici</a> Pour en savoir plus.</p>"
                +
                "<footer style=\"margin-top: 20px; font-size: 14px; color: #777;\">" +
                "<p>Merci d'utiliser les outils internes!</p>" +
                "</footer>" +
                "</body>" +
                "</html>";

        // Création de la session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Création de compte");

            MimeMultipart multipart = new MimeMultipart("alternative");

            // Add HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContentDeux, "text/html");
            multipart.addBodyPart(htmlPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            // message.setText(mess);

            // // Envoi du message
            // Transport.send(message);

            System.out.println("Email envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
