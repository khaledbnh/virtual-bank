package tn.esprit.vbank.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.enums.TypeNotification;
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

	@Autowired
	private Configuration cfg;

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
		TypeNotification typeNotification = notification.getTypeNotification();
		String sujet = StringUtils.capitalize(typeNotification.toString().toLowerCase().replace('_', ' '));
		String contenu = getContenu(typeNotification, data);
		notifer(sujet, contenu, notification.getPieceJointe(), notification.getUser());
	}

	private String getContenu(TypeNotification typeNotification, Map<String, String> data) {
		Template template = getTemplate(typeNotification);
		return fillTemplate(template, data);
	}

	private Template getTemplate(TypeNotification typeNotification) {
		Template template = null;
		try {
			template = cfg.getTemplate(typeNotification.toString().toLowerCase());
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return template;
	}

	private String fillTemplate(Template template, Map<String, String> data) {
		StringWriter stringWriter = new StringWriter();
		try {
			template.process(data, stringWriter);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
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
