package com.e1000son.flightreservation.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath){
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject("Itinerário do seu voo");
            messageHelper.setText("Por favor, encontre o seu itinerário em anexo.");
            messageHelper.addAttachment("Itinerário", new File(filePath));

            sender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
