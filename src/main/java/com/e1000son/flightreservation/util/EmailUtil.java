package com.e1000son.flightreservation.util;

import com.e1000son.flightreservation.services.ReservationServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {
    @Value("${com.e1000son.flightreservation.itinerary.email.subject}")
    public String EMAIL_SUBJECT;
    @Value("${com.e1000son.flightreservation.itinerary.email.body}")
    public String EMAIL_BODY;
    @Autowired
    private JavaMailSender sender;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    public void sendItinerary(String toAddress, String filePath){
        LOGGER.info("Inside sendItinerary(). Address {toAddress}, FilePath {filePath}");
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(EMAIL_SUBJECT);
            messageHelper.setText(EMAIL_BODY);
            messageHelper.addAttachment("Itinerário", new File(filePath));

            sender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary(): " + e);
        }

    }
}
