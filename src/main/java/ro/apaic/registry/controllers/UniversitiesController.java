package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.University;
import ro.apaic.registry.repositories.UniversitiesRepo;

import java.util.Set;

@RestController
public class UniversitiesController {

    @Autowired
    private UniversitiesRepo universitiesRepo;

    @GetMapping("/universities")
    public ResponseEntity<Set<University>> getUniversities() {
        return new ResponseEntity<>(universitiesRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/university")
    public ResponseEntity<University> getUniversity(@RequestParam final int id) {
        University university = universitiesRepo.findUniversityById(id);
        if (university != null) {
            return new ResponseEntity<>(university, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/university/add")
    public ResponseEntity<University> addUniversity(@RequestParam final String universityName) {
        University university = University.builder().universityName(universityName).build();
        universitiesRepo.save(university);
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    @GetMapping("/university/delete")
    public ResponseEntity<String> deleteUniversity(@RequestParam int id) {
        University university = universitiesRepo.findUniversityById(id);
        if (university != null) {
            universitiesRepo.delete(university);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
