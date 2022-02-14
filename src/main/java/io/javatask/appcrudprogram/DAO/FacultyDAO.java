package io.javatask.appcrudprogram.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javatask.appcrudprogram.entities.Faculty;

public interface FacultyDAO extends JpaRepository<Faculty,Long>{}
