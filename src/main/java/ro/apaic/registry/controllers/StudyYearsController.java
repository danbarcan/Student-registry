package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.StudyYear;
import ro.apaic.registry.repositories.SpecializationsRepo;
import ro.apaic.registry.repositories.StudyYearsRepo;

import java.util.Set;

@RestController
public class StudyYearsController {

    @Autowired
    private StudyYearsRepo studyYearsRepo;

    @Autowired
    private SpecializationsRepo specializationsRepo;

    @GetMapping("/studyYears")
    public ResponseEntity<Set<StudyYear>> getStudyYears() {
        return new ResponseEntity<>(studyYearsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/studyYear")
    public ResponseEntity<StudyYear> getStudyYear(@RequestParam final int id) {
        StudyYear studyYear = studyYearsRepo.findStudyYearById(id);
        if (studyYear != null) {
            return new ResponseEntity<>(studyYear, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/studyYear/add")
    public ResponseEntity<StudyYear> addStudyYear(@RequestParam final int year, @RequestParam final int specializationId) {
        StudyYear studyYear = StudyYear
                .builder()
                .studyYear(year)
                .specialization(specializationsRepo.findSpecializationById(specializationId))
                .build();
        studyYearsRepo.save(studyYear);
        return new ResponseEntity<>(studyYear, HttpStatus.OK);
    }

    @GetMapping("/studyYear/delete")
    public ResponseEntity<String> deleteStudyYear(@RequestParam int id) {
        StudyYear studyYear = studyYearsRepo.findStudyYearById(id);
        if (studyYear != null) {
            studyYearsRepo.delete(studyYear);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
