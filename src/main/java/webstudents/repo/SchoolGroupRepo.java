package webstudents.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webstudents.models.SchoolGroup;

import java.util.List;

@Repository
public interface SchoolGroupRepo extends JpaRepository<SchoolGroup, Integer> {


}
