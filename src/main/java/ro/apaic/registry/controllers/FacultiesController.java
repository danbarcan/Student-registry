package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.Faculty;
import ro.apaic.registry.repositories.FacultiesRepo;
import ro.apaic.registry.repositories.UniversitiesRepo;

import java.util.Set;

@RestController
public class FacultiesController {

    @Autowired
    private FacultiesRepo facultiesRepo;

    @Autowired
    private UniversitiesRepo universitiesRepo;

    @GetMapping("/faculties")
    public ResponseEntity<Set<Faculty>> getFaculties() {
        return new ResponseEntity<>(facultiesRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getFaculty(@RequestParam final int id) {
        Faculty faculty = facultiesRepo.findFacultyById(id);
        if (faculty != null) {
            return new ResponseEntity<>(faculty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/faculty/add")
    public ResponseEntity<Faculty> addFaculty(@RequestParam final String facultyName, @RequestParam final int universityId) {
        Faculty faculty = Faculty.builder().facultyName(facultyName).university(universitiesRepo.findUniversityById(universityId)).build();
        facultiesRepo.save(faculty);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @GetMapping("/faculty/delete")
    public ResponseEntity<String> deleteFaculty(@RequestParam int id) {
        Faculty faculty = facultiesRepo.findFacultyById(id);
        if (faculty != null) {
            facultiesRepo.delete(faculty);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
