package webstudents.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webstudents.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
