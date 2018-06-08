package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.StudentSeries;
import ro.apaic.registry.repositories.StudentSeriesRepo;
import ro.apaic.registry.repositories.StudyYearsRepo;

import java.util.Set;

@RestController
public class StudentSeriesController {

    @Autowired
    private StudentSeriesRepo studentSeriesRepo;

    @Autowired
    private StudyYearsRepo studyYearsRepo;

    @GetMapping("/studentSeries")
    public ResponseEntity<Set<StudentSeries>> getStudentSeries() {
        return new ResponseEntity<>(studentSeriesRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/studentSeriesById")
    public ResponseEntity<StudentSeries> getStudentSeries(@RequestParam final int id) {
        StudentSeries studentSeries = studentSeriesRepo.findStudentSeriesById(id);
        if (studentSeries != null) {
            return new ResponseEntity<>(studentSeries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/studentSeries/add")
    public ResponseEntity<StudentSeries> addStudentSeries(@RequestParam final String studentSeriesName, @RequestParam final int studyYearId) {
        StudentSeries studentSerie = StudentSeries
                .builder()
                .studentSeriesName(studentSeriesName)
                .studyYear(studyYearsRepo.findStudyYearById(studyYearId))
                .build();
        studentSeriesRepo.save(studentSerie);
        return new ResponseEntity<>(studentSerie, HttpStatus.OK);
    }

    @GetMapping("/studentSeries/delete")
    public ResponseEntity<String> deleteStudentSeries(@RequestParam int id) {
        StudentSeries studentSeries = studentSeriesRepo.findStudentSeriesById(id);
        if (studentSeries != null) {
            studentSeriesRepo.delete(studentSeries);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
