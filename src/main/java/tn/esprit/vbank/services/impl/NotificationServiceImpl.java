package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.repositories.NotificationRepository;
import tn.esprit.vbank.services.INotificationService;

@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

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

}
