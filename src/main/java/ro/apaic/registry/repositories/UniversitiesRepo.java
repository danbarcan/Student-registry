package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.University;

import java.util.Set;

public interface UniversitiesRepo extends CrudRepository<University, Integer> {
    Set<University> findAll();

    University findUniversityById(Integer id);

    University findUniversityByUniversityName(String universityName);
}
