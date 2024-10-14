package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
