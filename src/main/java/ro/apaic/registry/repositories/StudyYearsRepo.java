package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.StudyYear;

import java.util.Set;

public interface StudyYearsRepo extends CrudRepository<StudyYear, Integer> {
    Set<StudyYear> findAll();

    StudyYear findStudyYearById(Integer id);

    StudyYear findStudyYearByStudyYear(Integer studyYear);
}
