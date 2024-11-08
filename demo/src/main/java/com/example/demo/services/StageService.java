package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Stage;
import com.example.demo.repositories.StageRepository;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Optional<Stage> getStageById(Long id) {
        return stageRepository.findById(id);
    }

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Optional<Stage> updateStage(Long id, Stage updatedStage) {
        return stageRepository.findById(id).map(existingStage -> {
            existingStage.setTitle(updatedStage.getTitle());
            existingStage.setDescription(updatedStage.getDescription());
            existingStage.setDateDebut(updatedStage.getDateDebut());
            existingStage.setDateFin(updatedStage.getDateFin());
            return stageRepository.save(existingStage);
        });
    }

    public boolean deleteStage(Long id) {
        return stageRepository.findById(id).map(stage -> {
            stageRepository.delete(stage);
            return true;
        }).orElse(false);
    }
}