package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Specialization;

import java.util.Set;

public interface SpecializationsRepo extends CrudRepository<Specialization, Integer> {
    Set<Specialization> findAll();

    Specialization findSpecializationById(Integer id);

    Specialization findSpecializationBySpecializationName(String specializationName);
}
