package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.Semester;
import ro.apaic.registry.repositories.SemestersRepo;

import java.util.Set;

@RestController
public class SemestersController {

    @Autowired
    private SemestersRepo semestersRepo;

    @GetMapping("/semesters")
    public ResponseEntity<Set<Semester>> getSemesters() {
        return new ResponseEntity<>(semestersRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/semester")
    public ResponseEntity<Semester> getSemester(@RequestParam final int id) {
        Semester semester = semestersRepo.findSemesterById(id);
        if (semester != null) {
            return new ResponseEntity<>(semester, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/semester/add")
    public ResponseEntity<Semester> addSemester(@RequestParam final String semName, @RequestParam final int semNo) {
        Semester semester = Semester.builder().semNo(semNo).semName(semName).build();
        semestersRepo.save(semester);
        return new ResponseEntity<>(semester, HttpStatus.OK);
    }

    @GetMapping("/semester/delete")
    public ResponseEntity<String> deleteSemester(@RequestParam int id) {
        Semester semester = semestersRepo.findSemesterById(id);
        if (semester != null) {
            semestersRepo.delete(semester);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
