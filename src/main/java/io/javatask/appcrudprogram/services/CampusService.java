package io.javatask.appcrudprogram.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatask.appcrudprogram.DAO.CampusDAO;
import io.javatask.appcrudprogram.entities.Campus;

@Service
public class CampusService {
    @Autowired
    private CampusDAO campusDAO;

    //Get all campuses
    public List<Campus> getCampus(){
        return campusDAO.findAll();
    }

    //Get especific campus
    public Optional<Campus> getCampusById(long campusId){
        return campusDAO.findById(campusId);
    }

    //Create campus
    public Campus createCampus(Campus campus){
        return campusDAO.save(campus);
    }

    //Delete campus

    public void deleteCampus(long campusId ){
        campusDAO.deleteById(campusId);
    }

    //Update campus

    public Campus updateCampus (Campus campus,long id){
        campus.setId(id);
        return campusDAO.save(campus);
    }

    //get count
    public Long getCount(){
        return campusDAO.count();
    }
}