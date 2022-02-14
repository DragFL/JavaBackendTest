package io.javatask.appcrudprogram.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatask.appcrudprogram.DAO.ProgramDAO;
import io.javatask.appcrudprogram.entities.Program;

@Service
public class ProgramService {
    @Autowired
    private ProgramDAO programDAO;

    //Get all faculties
    public List<Program> getProgram(){
        return programDAO.findAll();
    }

    //Get especific program
    public Optional<Program> getProgramById(long programId){
        return programDAO.findById(programId);
    }

    //Create program
    public Program createProgram(Program program){
        return programDAO.save(program);
    }

    //Delete program

    public void deleteProgram(long programId ){
        programDAO.deleteById(programId);
    }

    //Update program

    public Program updateProgram (Program program,long id){
        program.setId(id);
        return programDAO.save(program);
    }

    //get count
    public Long getCount(){
        return programDAO.count();
    }
}