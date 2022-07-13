package tn.esprit.vbank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.vbank.entities.Notification;
import tn.esprit.vbank.services.INotificationService;

@Controller
public class NotificationRestController {

	@Autowired
	private INotificationService notificationService;

	@GetMapping(value = "/recupererNotification/{id}")
	public ResponseEntity recupererNotification(@PathVariable Long id) {
		Notification notification = null;
		try {
			notification = notificationService.recupererNotification(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(notification);
	}

	@GetMapping(value = "/listNotifications")
	public ResponseEntity listNotifications() {
		List<Notification> notifications = new ArrayList<>();
		try {
			notifications = notificationService.listNotifications();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(notifications);
	}

	@PostMapping(value = "/ajouterNotification")
	public ResponseEntity ajouterNotification(@RequestBody Notification notification) {
		Notification notificationPostSave = null;
		try {
			notificationPostSave = notificationService.ajouterNotification(notification);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(notificationPostSave);
	}

	@PutMapping(value = "/modifierNotification")
	public ResponseEntity updateNotification(@RequestBody Notification notification) {
		Notification notificationPostSave = null;
		try {
			notificationPostSave = notificationService.modifierNotification(notification);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(notificationPostSave);
	}

	@DeleteMapping(value = "/supprimerNotification/{id}")
	public ResponseEntity deleteNotification(@PathVariable Long id) {
		try {
			notificationService.supprimerNotification(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Notification supprimer");
	}

}
