package jcms.repository;

import jcms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByUsername(String username);
    User findByEmail(String email);
    User removeById(Integer id);
}
