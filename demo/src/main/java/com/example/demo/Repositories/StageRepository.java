package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
