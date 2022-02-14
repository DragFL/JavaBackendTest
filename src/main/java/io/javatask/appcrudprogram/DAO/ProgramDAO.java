package io.javatask.appcrudprogram.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javatask.appcrudprogram.entities.Program;

public interface ProgramDAO extends JpaRepository<Program,Long> {}
