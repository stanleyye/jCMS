package jcms.repository;

import jcms.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	@Query(
		value = "SELECT ur.* FROM user_role AS ur LEFT OUTER JOIN user AS u " +
						"ON ur.user_id = u.id WHERE u.username = ?1 LIMIT 1",
		nativeQuery = true
	)
	UserRole findByForeignKeyUserUsername(String username);
}
