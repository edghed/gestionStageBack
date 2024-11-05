package com.example.demo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Repositories.StageRepository;
import com.example.demo.Repositories.StudentRepository;
import com.example.demo.entities.Stage;
import com.example.demo.entities.Student;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StageRepository stageRepository;

    @Override
    public void run(String... args) throws Exception {
       
        studentRepository.save(new Student("John", "Doe", "jhondoe@gmail.com","password1"));
        studentRepository.save(new Student("Jane", "Smith", "janesmith@gmail.com","password2"));

        // Déclaration et initialisation de SimpleDateFormat pour le format "dd-MM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Ajout d'un stage avec des dates en utilisant le formatage correct
        stageRepository.save(new Stage("Software Engineering", "Stage in software engineering department",
            new Date(dateFormat.parse("01-02-2024").getTime()),
            new Date(dateFormat.parse("01-06-2024").getTime())));

        stageRepository.save(new Stage("Data Science", "Stage in data science team",
            new Date(dateFormat.parse("18-02-2024").getTime()),
            new Date(dateFormat.parse("18-06-2024").getTime())));
    }
}
