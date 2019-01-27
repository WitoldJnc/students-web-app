package webStudents.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webStudents.models.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value = "select id, first_name, last_name, group_number from students natural join school_groups", nativeQuery = true)
    List<Object> findWithGroupNumber();

    List<Object> findByFirstName(String fname);

}
