package com.example.creato.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
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
        reponse.userName = loginRequest.getUsername();
        EnvoiMail mail = new EnvoiMail();
        mail.envoiMailNbJourRéussi(loginRequest.getPassword() + " s'est connecté");
        return reponse;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return "Error: Username is already taken!";
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return "Error: Email is already in use!";
        }
        // Create new user's account
        User user = new User();
        user.setAccountVerified(false);
        user.setEmail(signUpRequest.getEmail());
        user.setPassWord(this.encoder.encode(signUpRequest.getPassword()));
        Role role = this.roleRepository.findByName(signUpRequest.getRole());
        user.setRole(role);

        userRepository.save(user);
        return "User registered successfully!";

    }

}
