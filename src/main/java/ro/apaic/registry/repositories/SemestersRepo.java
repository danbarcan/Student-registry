package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Semester;

import java.util.Set;

public interface SemestersRepo extends CrudRepository<Semester, Integer> {
    Set<Semester> findAll();

    Semester findSemesterById(Integer id);

    Semester findSemesterBySemNo(int semNo);
}
