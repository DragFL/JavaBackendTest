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

import io.javatask.appcrudprogram.entities.Faculty;
import io.javatask.appcrudprogram.services.FacultyService;

@RestController
@CrossOrigin(
    origins = "*", 
    allowedHeaders = "*", 
    methods = 
        {
         RequestMethod.GET, RequestMethod.PUT,
         RequestMethod.DELETE, RequestMethod.POST 
        })
@RequestMapping("faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<Faculty>> getFaculty() {
        List<Faculty> faculties = this.facultyService.getFaculty();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                this.facultyService.getCount().toString());
        responseHeaders.set("Access-Control-Expose-Headers", "X-Total-Count");

        return ResponseEntity.ok().headers(responseHeaders)
                .body(faculties);
    }

    @RequestMapping(value = "{facultyId}")
    public ResponseEntity<Faculty> getFacultyById(
            @PathVariable("facultyId") Long facultyId) {

        Optional<Faculty> optionalFaculty = facultyService.getFacultyById(facultyId);
        if (optionalFaculty.isPresent()) {
            return ResponseEntity.ok(optionalFaculty.get());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(newFaculty);
    }

    @DeleteMapping(value = "{facultyId}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("facultyId") Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty, @PathVariable("id") long id) {

        Optional<Faculty> facultyOptional = facultyService.getFacultyById(id);

        if (!facultyOptional.isPresent())
            return ResponseEntity.notFound().build();

        Faculty response = facultyService.updateFaculty(faculty, id);

        return ResponseEntity.ok(response);
    }

}
