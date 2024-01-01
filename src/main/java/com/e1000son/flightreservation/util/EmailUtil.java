package com.e1000son.flightreservation.util;

import com.e1000son.flightreservation.services.ReservationServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    public void sendItinerary(String toAddress, String filePath){
        LOGGER.info("Inside sendItinerary(). Address {toAddress}, FilePath {filePath}");
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject("Itinerário do seu voo");
            messageHelper.setText("Por favor, encontre o seu itinerário em anexo.");
            messageHelper.addAttachment("Itinerário", new File(filePath));

            sender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary(): " + e);
        }

    }
}
