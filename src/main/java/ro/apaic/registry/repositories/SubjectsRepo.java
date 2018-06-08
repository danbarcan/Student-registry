package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Subject;

import java.util.Set;

public interface SubjectsRepo extends CrudRepository<Subject, Integer> {
    Set<Subject> findAll();

    Subject findSubjectById(Integer id);

    Subject findSubjectBySubjectName(String subjectName);
}
