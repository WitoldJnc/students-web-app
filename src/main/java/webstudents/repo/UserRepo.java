package webstudents.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import webstudents.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
