package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Faculty;

import java.util.Set;

public interface FacultiesRepo extends CrudRepository<Faculty, Integer> {
    Set<Faculty> findAll();

    Faculty findFacultyById(Integer id);

    Faculty findFacultyByFacultyName(String facultyName);
}
