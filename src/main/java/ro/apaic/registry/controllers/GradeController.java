package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.Grade;
import ro.apaic.registry.repositories.GradesRepo;
import ro.apaic.registry.repositories.StudentsRepo;
import ro.apaic.registry.repositories.SubjectsRepo;

import java.util.Set;

@RestController
public class GradeController {

    @Autowired
    private GradesRepo gradesRepo;

    @Autowired
    private StudentsRepo studentsRepo;

    @Autowired
    private SubjectsRepo subjectsRepo;

    @GetMapping("/grades")
    public ResponseEntity<Set<Grade>> getGrades() {
        return new ResponseEntity<>(gradesRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/grade")
    public ResponseEntity<Grade> getGrade(@RequestParam final int id) {
        Grade grade = gradesRepo.findGradeById(id);
        if (grade != null) {
            return new ResponseEntity<>(grade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/grade/add")
    public ResponseEntity<Grade> addGrade(@RequestParam final int gradeEval, @RequestParam final int studentId, @RequestParam final int subjectId) {
        Grade grade = Grade
                .builder()
                .grade(gradeEval)
                .student(studentsRepo.findStudentById(studentId))
                .subject(subjectsRepo.findSubjectById(subjectId))
                .build();
        gradesRepo.save(grade);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping("/grade/delete")
    public ResponseEntity<String> deleteGrade(@RequestParam int id) {
        Grade grade = gradesRepo.findGradeById(id);
        if (grade != null) {
            gradesRepo.delete(grade);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
