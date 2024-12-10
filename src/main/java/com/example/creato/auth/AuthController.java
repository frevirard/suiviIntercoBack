package com.example.creato.auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.jwt.JwtTokenUtil;
import com.example.creato.mailSender.EnvoiMail;
import com.example.creato.users.CustomUserDetailsService;
import com.example.creato.users.Role;
import com.example.creato.users.RoleRepository;
import com.example.creato.users.User;
import com.example.creato.users.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationProvider authenticationManager;

    @Autowired
    JwtTokenUtil jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        String jwt = jwtUtils.generateToken(loginRequest.getUsername());
        JwtResponse reponse = new JwtResponse();
        reponse.jwt = jwt;
        User user = this.customUserDetailsService.loadUser(loginRequest.getUsername());
        if (!user.getAccountVerified()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Compte non verifié");
            try {
                EnvoiMail mail = new EnvoiMail();
                mail.envoiMailCreationDeCompte("Hello, " + user.getNom() + " " + user.getPrenom(),
                        "Vous avez tenté de vous connecté alors que votre compte n'a pas été approuvé par l'administrateur",
                        user.getEmail());
            } catch (Exception e) {
                System.out.println("envoie mail non effectué");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        reponse.userName = loginRequest.getUsername();
        reponse.nom = user.getNom();
        reponse.prenoms = user.getPrenom();
        reponse.avatar = user.getAvatar();
        EnvoiMail mail = new EnvoiMail();
        mail.envoiMailNbJourRéussi(loginRequest.getUsername() + " s'est connecté",
                user.getNom() + " " + user.getPrenom(), "frevirard@gmail.com");
        return ResponseEntity.status(HttpStatus.OK).body(reponse);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody SignUpRequest signUpRequest) {
        Map<String, String> errorMessage = new HashMap<>();

        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            errorMessage.put("message", "Nom d'utilisateur déjà pris!");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            errorMessage.put("message", "Email déjà pris!");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {

            errorMessage.put("message", "Mot de passe non conforme");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
        }
        // Create new user's account
        User user = new User();
        user.setAccountVerified(false);
        user.setEmail(signUpRequest.getEmail());
        user.setPassWord(this.encoder.encode(signUpRequest.getPassword()));
        Role role = this.roleRepository.findByName("ADMIN");
        user.setRole(role);
        // Role role = this.roleRepository.findByName(signUpRequest.getRole());
        user.setNom(signUpRequest.getNom());
        user.setAvatar("assets/images/profile/user-0.jpg");
        user.setPrenom(signUpRequest.getPrenoms());
        user.setRole(role);
        user.setUserName(signUpRequest.getUsername());
        user.setAccountVerified(false);

        userRepository.save(user);
        try {
            EnvoiMail mail = new EnvoiMail();
            mail.envoiMailCreationDeCompte("Hello, " + user.getNom() + " " + user.getPrenom(),
                    "Votre compte a été créé et doit être validé par un administrateur.", user.getEmail());
        } catch (Exception e) {
            System.out.println("envoie mail non effectué");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
