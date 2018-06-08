package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.StudentGroup;
import ro.apaic.registry.repositories.StudentGroupsRepo;
import ro.apaic.registry.repositories.StudentSeriesRepo;
import ro.apaic.registry.repositories.StudyDomainsRepo;

import java.util.Set;

@RestController
public class StudentGroupsController {

    @Autowired
    private StudentGroupsRepo studentGroupsRepo;

    @Autowired
    private StudentSeriesRepo studentSeriesRepo;

    @GetMapping("/studentGroups")
    public ResponseEntity<Set<StudentGroup>> getStudentGroups() {
        return new ResponseEntity<>(studentGroupsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/studentGroup")
    public ResponseEntity<StudentGroup> getStudentGroup(@RequestParam final int id) {
        StudentGroup studentGroup = studentGroupsRepo.findStudentGroupById(id);
        if (studentGroup != null) {
            return new ResponseEntity<>(studentGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/studentGroup/add")
    public ResponseEntity<StudentGroup> addStudentGroup(@RequestParam final String studentGroupName, @RequestParam final int studySeriesId) {
        StudentGroup studentGroup = StudentGroup
                .builder()
                .studentGroupName(studentGroupName)
                .studentSeries(studentSeriesRepo.findStudentSeriesById(studySeriesId))
                .build();
        studentGroupsRepo.save(studentGroup);
        return new ResponseEntity<>(studentGroup, HttpStatus.OK);
    }

    @GetMapping("/studentGroup/delete")
    public ResponseEntity<String> deleteStudentGroup(@RequestParam int id) {
        StudentGroup studentGroup = studentGroupsRepo.findStudentGroupById(id);
        if (studentGroup != null) {
            studentGroupsRepo.delete(studentGroup);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
