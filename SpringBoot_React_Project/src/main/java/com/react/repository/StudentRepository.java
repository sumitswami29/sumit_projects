package com.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.react.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
