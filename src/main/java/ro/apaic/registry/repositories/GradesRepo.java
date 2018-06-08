package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Faculty;
import ro.apaic.registry.enitites.Grade;
import ro.apaic.registry.enitites.Student;
import ro.apaic.registry.enitites.Subject;

import java.util.Set;

public interface GradesRepo extends CrudRepository<Grade, Integer> {
    Set<Grade> findAll();

    Set<Grade> findGradesByStudent(Student student);

    Set<Grade> findGradesBySubject(Subject subject);

    Grade findGradeById(Integer id);
}
