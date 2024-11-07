// src/main/java/com/example/demo/controllers/AuthController.java
package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Student student) {
        /*
         * if (studentService.loadUserByUsername(student.getEmail())) != null {
         * return ResponseEntity.badRequest().body("Cet email est déjà utilisé.");
         * }
         */
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentService.saveStudent(student);
        return ResponseEntity.ok("Inscription réussie !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Requête de connexion reçue pour : " + loginRequest.getEmail());

        Student student = (Student) studentService.loadUserByUsername(loginRequest.getEmail());
        if (student == null) {
            System.out.println("Utilisateur non trouvé pour l'email : " + loginRequest.getEmail());
            return ResponseEntity.badRequest().body("Email ou mot de passe incorrect.");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
            System.out.println("Mot de passe incorrect pour l'email : " + loginRequest.getEmail());
            return ResponseEntity.badRequest().body("Email ou mot de passe incorrect.");
        }

        String role = "STUDENT";
        String secretKey = "az38";
        String token = Jwts.builder()
                .setSubject(student.getEmail())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        System.out.println("Token généré : " + token);
        return ResponseEntity.ok(new LoginResponse(token, role));
    }

}
