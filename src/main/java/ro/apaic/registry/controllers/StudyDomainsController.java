package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.StudyDomain;
import ro.apaic.registry.repositories.FacultiesRepo;
import ro.apaic.registry.repositories.StudyDomainsRepo;

import java.util.Set;

@RestController
public class StudyDomainsController {

    @Autowired
    private StudyDomainsRepo studyDomainsRepo;

    @Autowired
    private FacultiesRepo facultiesRepo;

    @GetMapping("/studyDomains")
    public ResponseEntity<Set<StudyDomain>> getStudyDomains() {
        return new ResponseEntity<>(studyDomainsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/studyDomain")
    public ResponseEntity<StudyDomain> getStudyDomain(@RequestParam final int id) {
        StudyDomain studyDomain = studyDomainsRepo.findStudyDomainById(id);
        if (studyDomain != null) {
            return new ResponseEntity<>(studyDomain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/studyDomain/add")
    public ResponseEntity<StudyDomain> addStudyDomain(@RequestParam final String studyDomainName, @RequestParam final int facultyId) {
        StudyDomain studyDomain = StudyDomain
                .builder()
                .studyDomainName(studyDomainName)
                .faculty(facultiesRepo.findFacultyById(facultyId))
                .build();
        studyDomainsRepo.save(studyDomain);
        return new ResponseEntity<>(studyDomain, HttpStatus.OK);
    }

    @GetMapping("/studyDomain/delete")
    public ResponseEntity<String> deleteStudyDomain(@RequestParam int id) {
        StudyDomain studyDomain = studyDomainsRepo.findStudyDomainById(id);
        if (studyDomain != null) {
            studyDomainsRepo.delete(studyDomain);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
