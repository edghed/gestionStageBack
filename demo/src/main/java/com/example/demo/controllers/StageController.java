package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.StageRepository;
import com.example.demo.entities.Stage;

@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageRepository stageRepository;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }
}
