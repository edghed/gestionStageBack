package com.example.demo.controllers;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.StageRepository;
import com.example.demo.Repositories.StudentRepository;
import com.example.demo.entities.Stage;
import com.example.demo.entities.Student;

@RestController

public class EntityController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StageRepository stageRepository;

    // Obtenir un Student par son ID
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Obtenir un Stage par son ID
   /* @GetMapping("/stages/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageRepository.findById(id);
        return stage.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    } */

    // Créer un Student (avec GET pour utiliser l'URL)
    @GetMapping("/students/create")
    public ResponseEntity<Student> createStudent(@RequestParam String firstName, @RequestParam String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    // Créer un Stage (avec GET pour utiliser l'URL)
    @GetMapping("/stages/create")
    public ResponseEntity<Stage> createStage(@RequestParam String title, 
                                              @RequestParam String description, 
                                              @RequestParam Date dateDebut, 
                                              @RequestParam Date dateFin) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setDescription(description);
        stage.setDateDebut(dateDebut);
        stage.setDateFin(dateFin);
        stageRepository.save(stage);
        return ResponseEntity.ok(stage);
    }
}
