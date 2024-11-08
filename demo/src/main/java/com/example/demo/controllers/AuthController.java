package com.example.demo.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.User;
import com.example.demo.services.StudentService;
import com.example.demo.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Déclaration de la clé secrète pour le JWT
    private static final String SECRET_KEY = "az38";

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setRole("Role_Student");
        userService.saveUser(user);
        return ResponseEntity.ok("Inscription réussie !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String rawPassword = loginRequest.getPassword();

        if (username == null || rawPassword == null) {
            System.out.println("Nom d'utilisateur ou mot de passe non fourni");
            return ResponseEntity.badRequest().body("Nom d'utilisateur ou mot de passe manquant.");
        }

        System.out.println("Tentative de connexion pour l'utilisateur : " + username);

        // Vérification de l'utilisateur dans la base
        User user = (User) userService.loadUserByUsername(username);
        if (user == null) {
            System.out.println("Utilisateur non trouvé : " + username);
            return ResponseEntity.badRequest().body("Utilisateur non trouvé.");
        }

        System.out.println("Utilisateur trouvé. Vérification du mot de passe...");

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            System.out.println("Mot de passe incorrect pour : " + username);
            return ResponseEntity.badRequest().body("Mot de passe incorrect.");
        }

        System.out.println("Mot de passe correct. Génération du token...");

        String role = user.getRole();
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valable 24h
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        System.out.println("Connexion réussie pour : " + username + ", Token généré : " + token);

        return ResponseEntity.ok(new LoginResponse(token, role));
    }

    @PutMapping("/users/role")
    @PreAuthorize("hasAuthority('Role_Admin')")
    public ResponseEntity<String> updateUserRole(@RequestParam String email, @RequestBody String newRole) {
        if (!newRole.equals("Role_Admin") && !newRole.equals("Role_Student")) {
            return ResponseEntity.badRequest().body("Rôle non valide.");
        }

        Optional<User> optionalUser = userService.findByUsername(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(newRole);
            userService.saveUser(user);
            return ResponseEntity.ok("Rôle mis à jour avec succès !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
