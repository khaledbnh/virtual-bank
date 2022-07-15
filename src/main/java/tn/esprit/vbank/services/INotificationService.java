package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Notification;

public interface INotificationService {

	Notification recupererNotification(Long id);

	List<Notification> listNotifications();

	public Notification ajouterNotification(Notification notification);

	Notification modifierNotification(Notification notification);

	void supprimerNotification(Long id);

}
