package ro.apaic.registry.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.apaic.registry.enitites.Specialization;
import ro.apaic.registry.enitites.StudentGroup;

import java.util.Set;

public interface StudentGroupsRepo extends CrudRepository<StudentGroup, Integer> {
    Set<StudentGroup> findAll();

    StudentGroup findStudentGroupById(Integer id);

    StudentGroup findStudentGroupByStudentGroupName(String studentGroupName);
}
