package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Student;
public interface StudentRepository extends JpaRepository<Student, Long> 

{

}

