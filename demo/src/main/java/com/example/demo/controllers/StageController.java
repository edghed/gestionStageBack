package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.StageRepository;
import com.example.demo.entities.Stage;

@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageRepository stageRepository;

    // Récupérer tous les stages
    @GetMapping
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    // Ajouter un nouveau stage
    @PostMapping
    public Stage createStage(@RequestBody Stage stage) {
        return stageRepository.save(stage);
    }

    // Récupérer un stage par ID
    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        return stageRepository.findById(id)
            .map(stage -> ResponseEntity.ok(stage))
            .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour un stage
    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage updatedStage) {
        return stageRepository.findById(id)
            .map(existingStage -> {
                existingStage.setTitle(updatedStage.getTitle());
                existingStage.setDescription(updatedStage.getDescription());
                existingStage.setDateDebut(updatedStage.getDateDebut());
                existingStage.setDateFin(updatedStage.getDateFin());
                Stage savedStage = stageRepository.save(existingStage);
                return ResponseEntity.ok(savedStage);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un stage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        return stageRepository.findById(id)
            .map(stage -> {
                stageRepository.delete(stage);
                return ResponseEntity.ok().<Void>build();  // Ici, nous utilisons ResponseEntity<Void>
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
}
