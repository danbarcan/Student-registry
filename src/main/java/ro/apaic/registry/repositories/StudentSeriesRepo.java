package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.StudentSeries;

import java.util.Set;

public interface StudentSeriesRepo extends CrudRepository<StudentSeries, Integer> {
    Set<StudentSeries> findAll();

    StudentSeries findStudentSeriesById(Integer id);

    StudentSeries findStudentSeriesByStudentSeriesName(String studentSeriesName);
}
