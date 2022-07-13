package tn.esprit.vbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.IUserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        User user = null;
        try {
            user = userService.getUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(value = "/getUsers")
    public ResponseEntity getUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userService.getUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity ajouterAbonnement(@RequestBody User user) {
        User userPostSave = null;
        try {
            userPostSave = userService.addUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userPostSave);
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity updateUser(@RequestBody User user) {
        User userPostSave = null;
        try {
            userPostSave = userService.updateUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(userPostSave);
    }

    @DeleteMapping(value = "/supprimerAbonnement/{id}")
    public ResponseEntity deleteAbonnement(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
