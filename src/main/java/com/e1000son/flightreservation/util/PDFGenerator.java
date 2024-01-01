package com.e1000son.flightreservation.util;

import com.e1000son.flightreservation.entities.Reservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    public void generateItinerary(Reservation reservation, String filePath){
        LOGGER.info("Inside generateItinerary()");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(generateTable(reservation));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("Exception inside generateItinerary(): " + e);
        }
    }

    private PdfPTable generateTable(Reservation reservation){
        PdfPTable table = new PdfPTable(2);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Itinerário do voo"));
        cell.setColspan(2);
        table.addCell(cell);

        //Criando uma linha em branco
        cell = new PdfPCell(new Phrase(" "));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Detalhes do voo"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Airlines");
        table.addCell(reservation.getFlight().getOperatingAirLines());

        table.addCell("Cidade de partida");
        table.addCell(reservation.getFlight().getDepartureCity());

        table.addCell("Cidade de chegada");
        table.addCell(reservation.getFlight().getArrivalCity());

        table.addCell("Número do voo");
        table.addCell(reservation.getFlight().getFlightNumber());

        table.addCell("Data de partida");
        table.addCell(String.valueOf(reservation.getFlight().getDateOfDeparture()));

        table.addCell("Hora do voo");
        table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());

        cell = new PdfPCell(new Phrase("Detalhes do Passageiro"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Nome");
        table.addCell(reservation.getPassenger().getFirstName());

        table.addCell("Apelido");
        table.addCell(reservation.getPassenger().getLastName());

        table.addCell("Email");
        table.addCell(reservation.getPassenger().getEmail());

        table.addCell("Phone");
        table.addCell(reservation.getPassenger().getPhone());

        return table;
    }
}
