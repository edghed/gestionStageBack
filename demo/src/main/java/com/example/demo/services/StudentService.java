package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.StudentRepository;
import com.example.demo.entities.Student;

@Service 
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setEmail(updatedStudent.getEmail());
            return studentRepository.save(existingStudent);
        });
    }

    public boolean deleteStudent(Long id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return true;
        }).orElse(false);
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
       
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Student not found with email: " + username));
    }
}