package io.javatask.appcrudprogram.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatask.appcrudprogram.DAO.FacultyDAO;
import io.javatask.appcrudprogram.entities.Faculty;

@Service
public class FacultyService {
    @Autowired
    private FacultyDAO facultyDAO;

    //Get all faculties
    public List<Faculty> getFaculty(){
        return facultyDAO.findAll();
    }

    //Get especific faculty
    public Optional<Faculty> getFacultyById(long facultyId){
        return facultyDAO.findById(facultyId);
    }

    //Create faculty
    public Faculty createFaculty(Faculty faculty){
        return facultyDAO.save(faculty);
    }

    //Delete faculty

    public void deleteFaculty(long facultyId ){
        facultyDAO.deleteById(facultyId);
    }

    //Update faculty

    public Faculty updateFaculty (Faculty faculty,long id){
        faculty.setId(id);
        return facultyDAO.save(faculty);
    }

    //get count
    public Long getCount(){
        return facultyDAO.count();
    }
}