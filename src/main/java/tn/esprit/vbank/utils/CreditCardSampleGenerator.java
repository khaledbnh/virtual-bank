package tn.esprit.vbank.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;

import com.google.zxing.WriterException;

public class CreditCardSampleGenerator {

	public static void main(String[] args) throws IOException, WriterException {
		InputStream in = new ClassPathResource("creditcardsamples/platinum_card_front.png").getInputStream();
		BufferedImage image = ImageIO.read(in);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);

		String nom = "Brotherhood card";
		g.setFont(new Font("Consolas", Font.PLAIN, 100));
		g.drawString(nom, 130, 1225);

		String uuid = "1234 1234 1234 1234";
		g.drawString(uuid, 250, 850);

		String date = "02/22";
		g.setFont(new Font("Consolas", Font.PLAIN, 50));
		g.drawString(date, 945, 1025);

		byte[] qrCodeImage = QRCodeGenerator.getQRCodeImage("Test", 300, 300);

		QRCodeGenerator.generateQRCodeImage("Test", 300, 300, "C:\\Users\\kbenhamouda\\Desktop\\RES\\qr.png");
		BufferedImage qr = ImageIO.read(new File("C:\\Users\\kbenhamouda\\Desktop\\RES\\qr.png" ));
		g.drawImage(qr, 196, 100, null);

		ImageIO.write(image, "png", new File("C:\\Users\\kbenhamouda\\Desktop\\RES\\front.png"));
		System.out.println("Image Created");
	}

//	public static void generateCreditCardSampl(Carte carte) throws IOException {
//		InputStream in = new ClassPathResource("creditcardsamples/platinum_card_front.png").getInputStream();
//		BufferedImage image = ImageIO.read(in);
//		Graphics2D g = (Graphics2D) image.getGraphics();
//
//		String key = "Sample";
//		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(671, 1200, 1000, 100);
//		g.setFont(new Font("Arial Black", Font.BOLD, 20));
//		g.drawString(key, 671, 1200);
//		ImageIO.write(image, "png", new File("C:\\Users\\kbenhamouda\\Desktop\\res"));
//		System.out.println("Image Created");
//
//	}

}
