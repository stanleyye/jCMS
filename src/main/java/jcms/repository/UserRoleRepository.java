package jcms.repository;

import jcms.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	@Query(
		value = "SELECT user_role.* FROM user_role ur LEFT OUTER JOIN user u " +
						"ON ur.user_id = u.id WHERE u.username = ?1 LIMIT 1",
		nativeQuery = true
	)
	UserRole findByForeignKeyUserUsername(String username);
}
