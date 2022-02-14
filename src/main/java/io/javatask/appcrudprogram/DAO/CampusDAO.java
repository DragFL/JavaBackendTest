package io.javatask.appcrudprogram.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javatask.appcrudprogram.entities.Campus;

public interface CampusDAO extends JpaRepository<Campus,Long> {
    long count() ;
}
