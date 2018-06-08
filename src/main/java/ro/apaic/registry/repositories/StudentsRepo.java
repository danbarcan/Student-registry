package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Student;

import java.util.Set;

public interface StudentsRepo extends CrudRepository<Student, Integer> {
    Set<Student> findAll();

    Student findStudentById(Integer id);

    Student findStudentByFirstName(String firstName);
}
