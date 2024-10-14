package com.example.demo;

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
       
        studentRepository.save(new Student("John", "Doe"));
        studentRepository.save(new Student("Jane", "Smith"));

      
        stageRepository.save(new Stage("Software Engineering", "Stage in software engineering department"));
        stageRepository.save(new Stage("Data Science", "Stage in data science team"));
    }
}
