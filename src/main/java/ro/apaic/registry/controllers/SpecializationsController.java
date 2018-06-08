package ro.apaic.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.apaic.registry.enitites.Specialization;
import ro.apaic.registry.repositories.SpecializationsRepo;
import ro.apaic.registry.repositories.StudyDomainsRepo;

import java.util.Set;

@RestController
public class SpecializationsController {

    @Autowired
    private SpecializationsRepo specializationsRepo;

    @Autowired
    private StudyDomainsRepo studyDomainsRepo;

    @GetMapping("/specializations")
    public ResponseEntity<Set<Specialization>> getSpecializations() {
        return new ResponseEntity<>(specializationsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/specialization")
    public ResponseEntity<Specialization> getSpecialization(@RequestParam final int id) {
        Specialization specialization = specializationsRepo.findSpecializationById(id);
        if (specialization != null) {
            return new ResponseEntity<>(specialization, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/specialization/add")
    public ResponseEntity<Specialization> addSpecialization(@RequestParam final String specializationName, @RequestParam final int studyDomainId) {
        Specialization specialization = Specialization
                .builder()
                .specializationName(specializationName)
                .studyDomain(studyDomainsRepo.findStudyDomainById(studyDomainId))
                .build();
        specializationsRepo.save(specialization);
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @GetMapping("/specialization/delete")
    public ResponseEntity<String> deleteSpecialization(@RequestParam int id) {
        Specialization specialization = specializationsRepo.findSpecializationById(id);
        if (specialization != null) {
            specializationsRepo.delete(specialization);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
