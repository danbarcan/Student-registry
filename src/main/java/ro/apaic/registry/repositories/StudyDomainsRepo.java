package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.StudyDomain;

import java.util.Set;

public interface StudyDomainsRepo extends CrudRepository<StudyDomain, Integer> {
    Set<StudyDomain> findAll();

    StudyDomain findStudyDomainById(Integer id);

    StudyDomain findStudyDomainByStudyDomainName(String studyDomainName);
}
