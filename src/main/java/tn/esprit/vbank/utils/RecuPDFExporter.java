package tn.esprit.vbank.utils;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.vbank.utils.dto.Recu;
 
 
public class RecuPDFExporter {
    private Recu recu;
     
    public RecuPDFExporter(Recu recu) {
        this.recu = recu;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10.0f);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nom Client", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Type Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Num Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Compte Source", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Type Operation", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Compte Destinataire", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Nom Destinataire", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Montant", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Reference", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
    	table.addCell(recu.getNomClient());
    	table.addCell(recu.getTypeIdentiteClient());
        table.addCell(recu.getNumIdentiteClient());
    	if (!recu.getNumCompteSource().isEmpty()) {
    		table.addCell(recu.getNumCompteSource());
    	} else {
    		table.addCell("*");
    	}
    	table.addCell(recu.getTypeTransaction());
    	if (!recu.getNumCompteDestinataire().isEmpty()) {
    		table.addCell(recu.getNumCompteDestinataire());
    		table.addCell(recu.getNomDestinataire());
    	} else {
    		table.addCell("*");
    		table.addCell("*");
    	}
    	table.addCell(recu.getMontant());
        table.addCell(recu.getTimestamp());
        table.addCell(recu.getReference());
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.LEDGER);
        PdfWriter.getInstance(document, response.getOutputStream());
        response.setContentType("application/octet-stream");
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10.0f);
        font.setSize(10);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Recu", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
    }
}