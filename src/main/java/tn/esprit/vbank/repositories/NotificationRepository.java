package tn.esprit.vbank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.vbank.entities.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
