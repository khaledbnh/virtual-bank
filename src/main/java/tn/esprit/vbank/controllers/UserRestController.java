package tn.esprit.vbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vbank.entities.Admin;
import tn.esprit.vbank.entities.Client;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.services.IUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private IUserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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
    public ResponseEntity addUser(@RequestBody User user) {
        return getResponseEntity(user);
    }

    @PostMapping(value = "/addClient")
    public ResponseEntity addClient(@RequestBody Client client) {
        return getResponseEntity(client);
    }

    @PostMapping(value = "/addAdmin")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        return getResponseEntity(admin);
    }

    private ResponseEntity<? extends Serializable> getResponseEntity(User user) {
        User userPostSave = null;
        try {
            if (userService.getUserByEmail(user.getEmail()) != null) {
                throw new Exception("A user is already registered with this email");
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userPostSave = userService.addUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userPostSave);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
        User userPostSave = null;
        try {
            user.setId(id);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userPostSave = userService.updateUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(userPostSave);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
