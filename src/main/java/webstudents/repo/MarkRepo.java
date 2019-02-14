package webstudents.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webstudents.models.Mark;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Integer> {
}
