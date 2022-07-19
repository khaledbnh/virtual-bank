package tn.esprit.vbank.services.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import tn.esprit.vbank.entities.Abonnement;
import tn.esprit.vbank.entities.Carte;
import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.repositories.NotificationRepository;
import tn.esprit.vbank.services.INotificationService;

@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Override
	public Notification recupererNotification(Long id) {
		return notificationRepository.findById(id).get();
	}

	@Override
	public List<Notification> listNotifications() {
		return (List<Notification>) notificationRepository.findAll();
	}

	@Override
	public Notification ajouterNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public Notification modifierNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public void supprimerNotification(Long id) {
		notificationRepository.deleteById(id);
	}

	@Override
	public void notifier(Notification notification, Map<String, String> data) {
		String sujet;
		switch (notification.getTypeNotification()) {
		case AFFECTATION_ABONNEMENT:
			break;
		case MODIFICATION_ABONNEMENT:
			break;
		case ANNULATION_ABONNEMENT:
			break;
		}
//		notification.getTypeNotification()
//		notification.getPieceJointe()
//		notification.getUser()
//		notifier(Typeno)
	}

	private void notifer(String sujet, String contenu, String pieceJointe, User user) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		String email = user.getEmail();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(sujet);
			mimeMessageHelper.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
			mimeMessageHelper.setTo(new InternetAddress(email));
			mimeMessageHelper.setText(contenu);
			if (pieceJointe != null && pieceJointe.isEmpty()) {
				FileSystemResource file = new FileSystemResource(new File(pieceJointe));
				mimeMessageHelper.addAttachment(file.getFilename(), file);
			}
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
