package io.javatask.appcrudprogram.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javatask.appcrudprogram.entities.Campus;
import io.javatask.appcrudprogram.services.CampusService;

@RestController
@CrossOrigin(
    origins = "*", 
    allowedHeaders = "*", 
    methods = 
        {
         RequestMethod.GET, RequestMethod.PUT,
         RequestMethod.DELETE, RequestMethod.POST 
        })
@RequestMapping("campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @GetMapping
    public ResponseEntity<List<Campus>> getCampus() {
        List<Campus> campuses = this.campusService.getCampus();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                this.campusService.getCount().toString());
        responseHeaders.set("Access-Control-Expose-Headers", "X-Total-Count");

        return ResponseEntity.ok().headers(responseHeaders)
                .body(campuses);
    }

    @RequestMapping(value = "{campusId}")
    public ResponseEntity<Campus> getCampusById(
            @PathVariable("campusId") Long campusId) {

        Optional<Campus> optionalCampus = campusService.getCampusById(campusId);
        if (optionalCampus.isPresent()) {
            return ResponseEntity.ok(optionalCampus.get());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping
    public ResponseEntity<Campus> createCampus(@RequestBody Campus campus) {
        Campus newCampus = campusService.createCampus(campus);
        return ResponseEntity.ok(newCampus);
    }

    @DeleteMapping(value = "{campusId}")
    public ResponseEntity<Void> deleteCampus(@PathVariable("campusId") Long campusId) {
        campusService.deleteCampus(campusId);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Campus> updateCampus(@RequestBody Campus campus, @PathVariable("id") long id) {

        Optional<Campus> campusOptional = campusService.getCampusById(id);

        if (!campusOptional.isPresent())
            return ResponseEntity.notFound().build();

        Campus response = campusService.updateCampus(campus, id);

        return ResponseEntity.ok(response);
    }

}
