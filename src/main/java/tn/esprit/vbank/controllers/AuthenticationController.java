package tn.esprit.vbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vbank.entities.AuthenticationRequest;
import tn.esprit.vbank.entities.AuthenticationResponse;
import tn.esprit.vbank.entities.User;
import tn.esprit.vbank.security.JwtUtil;
import tn.esprit.vbank.security.UserDetailsServiceImpl;
import tn.esprit.vbank.services.impl.UserServiceImpl;
import tn.esprit.vbank.utils.EmailSenderService;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestParam String email) throws Exception {
        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "token= " + response;
        }
        User user = userService.getUserByEmail(email);
        try {
            emailSenderService.sendEmail(user.getEmail(), "Password Recovery", "Please find below the password reset token:\n" + response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not delivered");
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/resetPassword")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {
        return userService.resetPassword(token, password);
    }
}
