package webstudents.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webstudents.models.Discipline;

import java.util.List;

@Repository
public interface DisciplineRepo extends JpaRepository<Discipline, Integer> {


}
