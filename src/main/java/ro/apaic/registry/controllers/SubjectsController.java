package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.Subject;
import ro.apaic.registry.repositories.StudyYearsRepo;
import ro.apaic.registry.repositories.SubjectsRepo;

import java.util.Set;

@RestController
public class SubjectsController {

    @Autowired
    private SubjectsRepo subjectsRepo;

    @Autowired
    private StudyYearsRepo studyYearsRepo;

    @GetMapping("/subjects")
    public ResponseEntity<Set<Subject>> getSubjects() {
        return new ResponseEntity<>(subjectsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<Subject> getSubject(@RequestParam final int id) {
        Subject subject = subjectsRepo.findSubjectById(id);
        if (subject != null) {
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/subject/add")
    public ResponseEntity<Subject> addSubject(@RequestParam final String subjectName, @RequestParam final int studyYearId) {
        Subject subject = Subject
                .builder()
                .subjectName(subjectName)
                .studyYear(studyYearsRepo.findStudyYearById(studyYearId))
                .build();
        subjectsRepo.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/subject/delete")
    public ResponseEntity<String> deleteSubject(@RequestParam int id) {
        Subject subject = subjectsRepo.findSubjectById(id);
        if (subject != null) {
            subjectsRepo.delete(subject);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
