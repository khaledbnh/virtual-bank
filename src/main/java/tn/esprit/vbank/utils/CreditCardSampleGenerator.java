package tn.esprit.vbank.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;

import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.vbank.entities.Carte;

public class CreditCardSampleGenerator {

	private static byte[] generateCreditCardSample(String name, String uuid, String dateExpiration, String typeCarte) {
		String data = name + ";" + uuid + ";" + dateExpiration;
		InputStream in = null;
		try {
			in = new ClassPathResource("creditcardsamples/" + typeCarte.toLowerCase() + "_card_front.png")
					.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage image = null;
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);

		g.setFont(new Font("Consolas", Font.PLAIN, 30));
		g.drawString(name, 30, 310);

		g.drawString(uuid, 60, 215);

		g.setFont(new Font("Consolas", Font.PLAIN, 15));
		g.drawString("02/22", 235, 255);

		byte[] qrCodeImage = null;
		try {
			qrCodeImage = QRCodeGenerator.getQRCodeImage(data, 90, 90);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage qr = null;
		try {
			qr = ImageIO.read(new ByteArrayInputStream(qrCodeImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(qr, 41, 20, null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static void generateCreditCardSample(String name, Carte carte, String path) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path + File.separator + "sample.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		try {
			Image image = Image.getInstance(generateCreditCardSample(name, String.valueOf(carte.getUuid()),
					carte.getDateExpiration().toString(), carte.getType()));
			document.add(image);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
	}
}
