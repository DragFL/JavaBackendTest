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

import io.javatask.appcrudprogram.entities.Program;
import io.javatask.appcrudprogram.services.ProgramService;

@RestController
@CrossOrigin(
    origins = "*", 
    allowedHeaders = "*", 
    methods = 
        {
         RequestMethod.GET, RequestMethod.PUT,
         RequestMethod.DELETE, RequestMethod.POST 
        })
@RequestMapping("program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping
    public ResponseEntity<List<Program>> getProgram() {
        List<Program> faculties = this.programService.getProgram();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                this.programService.getCount().toString());
        responseHeaders.set("Access-Control-Expose-Headers", "X-Total-Count");

        return ResponseEntity.ok().headers(responseHeaders)
                .body(faculties);
    }

    @RequestMapping(value = "{programId}")
    public ResponseEntity<Program> getProgramById(
            @PathVariable("programId") Long programId) {

        Optional<Program> optionalProgram = programService.getProgramById(programId);
        if (optionalProgram.isPresent()) {
            return ResponseEntity.ok(optionalProgram.get());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        Program newProgram = programService.createProgram(program);
        return ResponseEntity.ok(newProgram);
    }

    @DeleteMapping(value = "{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable("programId") Long programId) {
        programService.deleteProgram(programId);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Program> updateProgram(@RequestBody Program program, @PathVariable("id") long id) {

        Optional<Program> programOptional = programService.getProgramById(id);

        if (!programOptional.isPresent())
            return ResponseEntity.notFound().build();

        Program response = programService.updateProgram(program, id);

        return ResponseEntity.ok(response);
    }

}
