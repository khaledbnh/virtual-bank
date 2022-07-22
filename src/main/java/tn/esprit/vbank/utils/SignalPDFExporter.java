package tn.esprit.vbank.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.vbank.entities.Post;
import tn.esprit.vbank.entities.Signal;
import tn.esprit.vbank.entities.User;

public class SignalPDFExporter {

	private Set<Signal> listSignals;

	public SignalPDFExporter(Set<Signal> listSignals) {
		this.listSignals = listSignals;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.blue);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Signal ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Signal type", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Signal content", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("User Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("User Email", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Signal signal : listSignals) {
			table.addCell(String.valueOf(signal.getIdSignal()));
			table.addCell(signal.getSignalType());
			table.addCell(signal.getContent());
			table.addCell(signal.getUser().getUsername());
			table.addCell(signal.getUser().getEmail());
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException, URISyntaxException {
		Document document = new Document(PageSize.A4);
		//PdfWriter.getInstance(document, response.getOutputStream());
		Signal signal=listSignals.iterator().next();
		Post post=signal.getPost();
		
		PdfWriter.getInstance(document, new FileOutputStream("C:\\document\\PostReport"+post.getIdPost()+".pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);
		
		Paragraph p = new Paragraph("Post Report", font);

		p.setAlignment(Paragraph.ALIGN_CENTER);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 11, Color.BLACK);
		document.add(p);
		Chunk chunk = new Chunk("Post Title : " +post.getTitle() + "\n", font2);
		Chunk chunk2 = new Chunk("Post Content : "+post.getBody()+ "\n", font2);
		document.add(chunk);
		document.add(chunk2);
		if(post.getPhoto()!=null) {
			//Path path = Paths.get(ClassLoader.getSystemResource(post.getPhoto()).toURI());
			Path path = new File(post.getPhoto()).toPath();
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			BufferedImage outputImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
			img.setWidthPercentage(10);

			document.add(img);
		}

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

		SendMail.mailreport(post.getIdPost().toString());

	}
}